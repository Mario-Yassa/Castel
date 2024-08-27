package fields.Doors;

import fields.Inspectable.Inspectable;
import fields.Rooms.Game;
import fields.Rooms.Room;
import fields.XMLhandler.Handler;

public class Door implements Inspectable {

    // Instance Variables
    private final String name;
    private final String description;
    private Room room1, room2;
    private boolean isLocked;
    Game gameData;

    // Constructor to make door by getting name, description and if it is locked or not.

    /**
     * Make the constructor for the Doors that you have in the room.
     * @param name The name of the door.
     * @param description The description of the door.
     * @param isLocked Determine if the door is closed or not.
     */
    public Door(String name, String description, boolean isLocked){
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        gameData = Handler.gameData;
    }

    public String getName() {
        return name;
    }

    // Display if the door is looked or not
    public boolean getIsLocked(){
        return isLocked;
    }

    // The methode to set the room for each door
    /**
     * This methode sets the room for the door and each door needs two rooms (one on each side).
     * @param room The room is the place where the player will find the Character(s), Door(s), Item(s), and the rest of the things for the game.
     */
    public void setRoom(Room room){
        if (room1 == null) {
            room1 = room;
        }
        else if (room1 == room)
            System.out.println("We already have this door (" + name + ") for this room");
        else {
            room2 = room;
        }
    }

    // To get the room on the other side of the door.
    public String getOtherRoom(Room room){
        if(room1 != null & room2 != null){
            if(room1 == room)
                return room2.getName();
            if(room2 == room)
                return room1.getName();
        }
        return "No Room in this Direction";
    }

    public boolean checkDoorInRoom(Room room){
        return room == room1 || room == room2;
    }

    // To lock the door.
    public void lock(){
        int checkLock = 0;
        String name1 = this.getName();
        for(Door d : gameData.getDoors()){
            String name2 = d.getName();
            if(name1.equalsIgnoreCase(name2) & !d.isLocked){
                d.isLocked = true;
                checkLock++;
            }
        }
        if(checkLock == 2){
            System.out.println("The door is now locked.\n");
        }

    }

    // To unlock the door.
    public void unlock(){
        int checkUnlock = 0;
        String name1 = this.getName();
        for(Door d : gameData.getDoors()){
            String name2 = d.getName();
            if(name1.equalsIgnoreCase(name2) & d.isLocked){
                d.isLocked = false;
                checkUnlock++;
            }
        }
        if(checkUnlock == 2){
            System.out.println("The door is now unlocked.\n");
        }

    }


    // toString method to display Character name.
    @Override
    public String toString() {
        return "Door name: " + name;
    }

    // inspectString method to display the Door name and description.
    public String inspectString() {
        StringBuilder doorData = new StringBuilder();
        doorData.append("Door name: ").append(name).append("\nDoor description: ").append(description);
        if(isLocked){
            doorData.append("\nThe door is locked\n");
        } else{
            System.out.println("\nThe door is unlocked\n");
        }

        return doorData.toString();
    }


}

