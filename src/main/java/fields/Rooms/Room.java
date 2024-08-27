// Mario Yassa
// CSC 241


package fields.Rooms;

import fields.Characters.Character;
import fields.Characters.Player.Player;
import fields.Doors.Door;
import fields.Inspectable.Inspectable;
import fields.Items.Item;

import java.util.ArrayList;

public class Room implements Inspectable {

    // Instance variables.
    private final String name;
    private final String description;
    private final ArrayList<Character> characters;
    private final ArrayList<Item> items;
    private Door Ndoor, Sdoor, Edoor, Wdoor;


    // Constructor for creating a Room with a name and description.
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        characters = new ArrayList<>();
        items = new ArrayList<>();
    }

    // Method to add a Character to the Room.
    /**
     * This methode takes a Character parameter. Then sets the room for the character and adds the character to an ArrayList.
     * @param character The characters that you may find in your way .
     */
    public void addCharacter(Character character) {
        // Set the Room reference for the Character
        character.setRoom(this);
        // Add the Character to the list of characters in the Room
        characters.add(character);
    }

    // Remove a character from the room.
    public void removeCharacter(Character character){
        characters.remove(character);
    }

    public ArrayList<Character> getCharacters(){
        return characters;
    }

    public boolean checkPlayerInRoom(Player player){
        for(Character character : characters){
            if(player == character){
                return true;
            }
        }
        return false;
    }

    public boolean check2Players(Player player){
        int counter = 0;
        for(Character character : characters){
            if(player == character){
                counter++;
            }
        }
        if (counter >= 2){
            return true;
        }
        return false;
    }

    // Method to add an Item to the Room.

    /**
     * This methode takes an Item parameter. Then sets the room for the Item and adds the item to an ArrayList.
     * @param item The items that you may find in your way .
     */
    public void addItem(Item item) {
        // Set the Room reference for the Item
        item.setRoom(this);
        // Add the Characters to the list of items in the Room
        items.add(item);
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    // Setting the doors for the room.

    public void setNorthDoor(Door northDoor) {
        Ndoor = northDoor;
    }

    public Door getNorthDoor() {
        return Ndoor;
    }

    public void setSouthDoor(Door southDoor) {
        Sdoor = southDoor;
    }

    public Door getSouthDoor() {
        return Sdoor;
    }

    public void setEastDoor(Door eastDoor) {
        Edoor = eastDoor;
    }

    public Door getEastDoor() {
        return Edoor;
    }

    public void setWestDoor(Door westDoor) {
        Wdoor = westDoor;
    }

    public Door getWestDoor() {
        return Wdoor;
    }


    public String getName(){
        return name;
    }

    // This methode Shows the other side of the door by getting the first room.
    public String showOtherSides(){
        StringBuilder otherSides = new StringBuilder();
        if(Ndoor != null) {
            otherSides.append("\n- North -\n Room Name: ").append(Ndoor.getOtherRoom(this));
        } if (Sdoor != null) {
            otherSides.append("\n- South -\n Room Name: ").append(Sdoor.getOtherRoom(this));
        } if (Edoor != null) {
            otherSides.append("\n- East -\n Room Name: ").append(Edoor.getOtherRoom(this));
        } if (Wdoor != null) {
            otherSides.append("\n- West -\n Room Name: ").append(Wdoor.getOtherRoom(this));
        }
        return otherSides.toString();
    }

    // toString method to display Room information.
    @Override
    public String toString() {
        StringBuilder roomString = new StringBuilder();
        roomString.append("Room's Name: ").append(name);
        roomString.append("\n-----------------------------------------------------------------------------");
        roomString.append("\nRoom's Description: ").append(description);
        roomString.append("\n-----------------------------------------------------------------------------");

        if(characters.isEmpty()) {
            roomString.append("\nThe Room has NO Characters.");
            roomString.append("\n-----------------------------------------------------------------------------");
        }

        // Loops through the list of Characters and append their information to the output
        else{
            roomString.append("\nCharacters:-\n");
            for (Character character : characters) {
                roomString.append("Character Name: ").append(character).append("\n");
            }
            roomString.append("-----------------------------------------------------------------------------");
        }

        if(items.isEmpty()) {
            roomString.append("\nThe Room has NO Items.\n");
            roomString.append("-----------------------------------------------------------------------------");
        }

        // Loops through the list of Characters and append their information to the output
        else{
            roomString.append("\nItems:-\n");
            for (Item item : items) {
                roomString.append(item.toString()).append("\n");
            }
            roomString.append("-----------------------------------------------------------------------------");
        }

        // Displaying Doors
        roomString.append("\nDoor:-");
        if(Ndoor != null)
            roomString.append("\n - North - \n").append(Ndoor);
        if(Sdoor != null)
            roomString.append("\n - South - \n").append(Sdoor);
        if(Edoor != null)
            roomString.append("\n - East - \n").append(Edoor);
        if(Wdoor != null)
            roomString.append("\n - West - \n").append(Wdoor);

        roomString.append("\n-----------------------------------------------------------------------------");

        // Display other room
        roomString.append("\nOther Rooms:-");
        roomString.append(this.showOtherSides());
        roomString.append("\n-----------------------------------------------------------------------------\n");

        return roomString.toString();
    }


    public String look() {
        StringBuilder roomString = new StringBuilder();
        roomString.append("Room's Name: ").append(name);
        roomString.append("\n-----------------------------------------------------------------------------");
        roomString.append("\nRoom's Description: ").append(description);
        roomString.append("\n-----------------------------------------------------------------------------");

        if(characters.isEmpty()) {
            roomString.append("\nThe Room has NO Characters.");
            roomString.append("\n-----------------------------------------------------------------------------");
        }

        // Loops through the list of Characters and append their information to the output
        else{
            roomString.append("\nCharacters:-\n");
            for (Character character : characters) {
                roomString.append("Character Name: ").append(character).append("\n");
            }
            roomString.append("-----------------------------------------------------------------------------");
        }

        if(items.isEmpty()) {
            roomString.append("\nThe Room has NO Items.\n");
            roomString.append("-----------------------------------------------------------------------------");
        }

        // Loops through the list of Characters and append their information to the output
        else{
            roomString.append("\nItems:-\n");
            for (Item item : items) {
                roomString.append(item.look()).append("\n");
            }
            roomString.append("-----------------------------------------------------------------------------");
        }

        // Displaying Doors
        roomString.append("\nDoor:-");
        if(Ndoor != null)
            roomString.append("\n - North - \n").append(Ndoor);
        if(Sdoor != null)
            roomString.append("\n - South - \n").append(Sdoor);
        if(Edoor != null)
            roomString.append("\n - East - \n").append(Edoor);
        if(Wdoor != null)
            roomString.append("\n - West - \n").append(Wdoor);

        roomString.append("\n-----------------------------------------------------------------------------");

        // Display other room
        roomString.append("\nOther Rooms:-");
        roomString.append(this.showOtherSides());
        roomString.append("\n-----------------------------------------------------------------------------\n");

        return roomString.toString();
    }
    // inspectString method to display the Room name and description.
    public String inspectString() {
        return "Room: " + name + "\nDescription: " + description + "\n";
    }


}
