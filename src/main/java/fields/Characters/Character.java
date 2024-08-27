package fields.Characters;

import fields.Inspectable.Inspectable;
import fields.Rooms.Room;

public abstract class Character implements Inspectable {

    // Instance variables
    private final String name;
    private final String description;
    private Room room;


    // Constructor for creating a Character with a name and description.

    /**
     * Make the constructor for the Characters that you have in the game.
     * @param name The name of the character
     * @param description The description of the character
     */
    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Method to set the Room for the Character.

    /**
     * This methode sets the room for a new Character to help him knows where is he located.
     * @param room The room is the place where the player will find the Character(s), Door(s), Item(s), and the rest of the things for the game.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    // to return the room
    public Room getRoom(){
        return room;
    }

    // toString method to display Character name.
    @Override
    public String toString() {
        return name;
    }

    // inspectString method to display Character name and the description.
    public String inspectString() {
        return "Name: " + name + "\nDescription: " + description;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
}
