package fields.Characters.Player;

import fields.Characters.Character;
import fields.Characters.NPC.Guard;
import fields.Characters.NPC.Maid;
import fields.Doors.Door;
import fields.Items.Inventory;
import fields.Items.Item;
import fields.Items.Key;
import fields.Rooms.Game;
import fields.Rooms.Room;
import fields.XMLhandler.Handler;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {
    private int gettingCaught = 0;
    Game gameData;
    private final Inventory inventory;
    private Room currentRoom, newRoom;
    private String roomName;
    private Door northDoor, southDoor, eastDoor, westDoor;
    private Door door;
    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private boolean cheatMode;


    // Constructor for creating a Player with a name and description.
    /**
     * Make the constructor for the Characters that you have in the game.
     * @param name The name of the Player
     * @param description The description of the Player
     */
    public Player(String name, String description) {
        super(name, description);
        gameData = Handler.gameData;
        inventory = new Inventory();
        items = new ArrayList<>();
        characters = new ArrayList<>();
        cheatMode = false;
    }

    // Checking the chance of getting Caught.
    private boolean caughtChance(int limit){
        Random random = new Random();
        int chance = random.nextInt(0,101);
        return chance < limit;
    }


    // Returning the health bar
    public String getHealthBar(){
        int health = 5 - gettingCaught;
        if(health <= 0){
            return "Empty";
        }
        return "[" + ("*").repeat(health) + "]";
    }

    public void moveNorth() {
        // Getting this room.
        currentRoom = this.getRoom();
        // Getting the door.
        northDoor = currentRoom.getNorthDoor();
        if (northDoor != null) {
            // Check if the door is locked.
            if (northDoor.getIsLocked()) {
                // Check to have one player
                if(currentRoom.check2Players(this)){
                    currentRoom.removeCharacter(this);
                } else if (!currentRoom.checkPlayerInRoom(this)){
                    currentRoom.addCharacter(this);
                }
                System.out.println("The door is Locked you need the key :)\n");
            } else {
                // Get the other side and move the player.
                roomName = northDoor.getOtherRoom(currentRoom);
                newRoom = gameData.getRoomByName(roomName);
                currentRoom.removeCharacter(this);
                newRoom.addCharacter(this);

                // Check to have one player
                if(newRoom.check2Players(this)){
                    newRoom.removeCharacter(this);
                } else if (!newRoom.checkPlayerInRoom(this)){
                    newRoom.addCharacter(this);
                }

                System.out.println("Player moved SUCCESSFULLY\n");
            }
        } else {
            // Check to have one player
            if(currentRoom.check2Players(this)){
                currentRoom.removeCharacter(this);
            } else if (!currentRoom.checkPlayerInRoom(this)){
                currentRoom.addCharacter(this);
            }
            System.out.println("There is NO door in the north direction.\n");
        }
    }

    public void moveSouth(){
        // Getting this room.
        currentRoom = this.getRoom();
        // Getting the door.
        southDoor = currentRoom.getSouthDoor();
        if (southDoor != null) {
            // Check if the door is locked.
            if (southDoor.getIsLocked()) {
                // Check to have one player
                if(currentRoom.check2Players(this)){
                    currentRoom.removeCharacter(this);
                } else if (!currentRoom.checkPlayerInRoom(this)){
                    currentRoom.addCharacter(this);
                }

                System.out.println("The door is Locked you need the key :)\n");
            } else {
                // Get the other side and move the player.
                roomName = southDoor.getOtherRoom(currentRoom);
                newRoom = gameData.getRoomByName(roomName);
                currentRoom.removeCharacter(this);
                newRoom.addCharacter(this);

                // Check to have one player
                if(newRoom.check2Players(this)){
                    newRoom.removeCharacter(this);
                } else if (!newRoom.checkPlayerInRoom(this)){
                    newRoom.addCharacter(this);
                }

                System.out.println("Player moved SUCCESSFULLY\n");
            }
        } else {
            // Check to have one player
            if(currentRoom.check2Players(this)){
                currentRoom.removeCharacter(this);
            } else if (!currentRoom.checkPlayerInRoom(this)){
                currentRoom.addCharacter(this);
            }
            System.out.println("There is NO door in the south direction.\n");
        }
    }

    public void moveEast(){
        // Getting this room.
        currentRoom = this.getRoom();
        // Getting the door.
        eastDoor = currentRoom.getEastDoor();
        if (eastDoor != null) {
            // Check if the door is locked.
            if (eastDoor.getIsLocked()) {
                // Check to have one player
                if(currentRoom.check2Players(this)){
                    currentRoom.removeCharacter(this);
                } else if (!currentRoom.checkPlayerInRoom(this)){
                    currentRoom.addCharacter(this);
                }
                System.out.println("The door is Locked you need the key :)\n");
            } else {
                // Get the other side and move the player.
                roomName = eastDoor.getOtherRoom(currentRoom);
                newRoom = gameData.getRoomByName(roomName);
                currentRoom.removeCharacter(this);
                newRoom.addCharacter(this);

                // Check to have one player
                if(newRoom.check2Players(this)){
                    newRoom.removeCharacter(this);
                } else if (!newRoom.checkPlayerInRoom(this)){
                    newRoom.addCharacter(this);
                }

                System.out.println("Player moved SUCCESSFULLY\n");
            }
        } else {
            // Check to have one player
            if(currentRoom.check2Players(this)){
                currentRoom.removeCharacter(this);
            } else if (!currentRoom.checkPlayerInRoom(this)){
                currentRoom.addCharacter(this);
            }
            System.out.println("There is NO door in the east direction.\n");
        }
    }

    public void moveWest(){
        // Getting this room.
        currentRoom = this.getRoom();
        // Getting the door.
        westDoor = currentRoom.getWestDoor();
        if (westDoor != null) {
            // Check if the door is locked.
            if (westDoor.getIsLocked()) {
                // Check to have one player
                if(currentRoom.check2Players(this)){
                    currentRoom.removeCharacter(this);
                } else if (!currentRoom.checkPlayerInRoom(this)){
                    currentRoom.addCharacter(this);
                }
                System.out.println("The door is Locked you need the key :)\n");
            } else {
                // Get the other side and move the player.
                roomName = westDoor.getOtherRoom(currentRoom);
                newRoom = gameData.getRoomByName(roomName);
                currentRoom.removeCharacter(this);
                newRoom.addCharacter(this);

                // Check to have one player
                if(newRoom.check2Players(this)){
                    newRoom.removeCharacter(this);
                } else if (!newRoom.checkPlayerInRoom(this)){
                    newRoom.addCharacter(this);
                }

                System.out.println("Player moved SUCCESSFULLY\n");
            }

        } else {
            // Check to have one player
            if(currentRoom.check2Players(this)){
                currentRoom.removeCharacter(this);
            } else if (!currentRoom.checkPlayerInRoom(this)){
                currentRoom.addCharacter(this);
            }
            System.out.println("There is NO door in the west direction.\n");
        }
    }

    public boolean escape(){
        String escapeRoomName = this.getRoom().getName();
        if (escapeRoomName.equalsIgnoreCase("Exit")) {
            System.out.println("Congratulations!! - you did it :) ");
            return true;
        } else {
            System.out.println("Don't cheat you can't escape from here.\n");
        }
        return false;
    }

    public boolean pickupItem(String inputItemName){
        Item item = gameData.getItemByName(inputItemName);
        currentRoom = this.getRoom();
        // Getting the item inside the room.
        items = currentRoom.getItems();
//        boolean findTheItem = false;
        if (item != null) {
            // Loop throw the items inside the room.
            for (Item item1 : items) {
                // If the item is carry-able and inside the room then add it to the list.
                if (item == item1 && item.isCarryable() && !item.isHidden()) {
                    if(inventory.checkHavingItem(item)){
                        System.out.println("You already have this item.\n");
                        return false;
                    }
                    if(inventory.pickItem(item))
                        return true;
                }
                // Loop throw the sub-items.
                for (Item subItem : item1.getSubItems()) {
                    if (item == subItem && item.isCarryable() && !item.isHidden()) {
                        if(inventory.checkHavingItem(item)){
                            System.out.println("You already have this item.\n");
                            return false;
                        }
                        if(inventory.pickItem(subItem))
                            return true;
                    }
                }
            }
        }
        System.out.println("Can't pickup the item ;)\n");
        return false;
    }

    public boolean dropItem(String inputItemName){
        Item item = gameData.getItemByName(inputItemName);
        // Remove it from the player list.
        return inventory.dropItem(item);
    }

    public boolean checkClear(){
        return items.isEmpty();
    }

    public boolean unlockDoor(String doorName){
        door = gameData.getDoorByName(doorName);
        items = inventory.getInventoryList();
        boolean haveKey = false;

        if (door != null) {
            if(door.checkDoorInRoom(this.getRoom())){
                if(!door.getIsLocked()){
                    System.out.println("The door is already unlocked.\n");
                    return false;
                }
                // Checking if we have the key then unlock the door.
                for (Item item : items) {
                    if(item instanceof Key key){
                        String doorKeyName = key.getDoorKeyName();
                        if (doorName.equalsIgnoreCase(doorKeyName)) {
                            door.unlock();
                            return true;
                        }
                    }

                }

                if(!haveKey) {
                    System.out.println("You don't have the key.\n");
                    return false;
                }
            } else{
                System.out.println("You aren't beside the door.\n");
                return false;
            }


        }

        System.out.println("Can't find the door ;)\n");
        return false;
    }

    public boolean lockDoor(String doorName){
        door = gameData.getDoorByName(doorName);
        items = inventory.getInventoryList();
        boolean haveKey = false;

        if (door != null) {
            if(door.checkDoorInRoom(this.getRoom())) {
                if (door.getIsLocked()) {
                    System.out.println("The door is already locked.\n");
                    return false;
                }
                // Checking if we have the key then lock the door.
                for (Item item : items) {
                    if (item instanceof Key key) {
                        String doorKeyName = key.getDoorKeyName();
                        if (doorName.equalsIgnoreCase(doorKeyName)) {
                            door.lock();
                            return true;
                        }
                    }
                }

                if (!haveKey) {
                    System.out.println("You don't have the key.\n");
                    return false;
                }
            } else{
                System.out.println("You aren't beside the door.\n");
                return false;
            }

        }
        System.out.println("Can't find the door ;)\n");
        return false;

    }

    public boolean isMaidAnnoyed(Maid roomMaid, boolean caughtChance) {
        currentRoom = this.getRoom();
        if(roomMaid != null){
            if(!roomMaid.isCleaning()){
                // Changing the maid action.
                roomMaid.setCleaning(true);
                roomMaid.setStopAction(true);
            }

            else if(roomMaid.getStopAction() && caughtChance(51)){
                // Editing the health bar.
                gettingCaught++;
                // Calling maid isAnnoyed to send the player to the starting room.
                roomMaid.isAnnoyed(this);
                currentRoom.removeCharacter(this);

                if (this.getHealthBar().equalsIgnoreCase("Empty")) {
                    System.out.println("GAME OVER!!");
                    return true;
                } else {
                    System.out.println("Sorry the Maid got you. Be Careful next time :)\n");
                    inventory.clear();
                    return true;
                }
            }

            else if (roomMaid.isCleaning() && caughtChance) {
                // Editing the health bar.
                gettingCaught++;
                // Calling maid isAnnoyed to send the player to the starting room.
                roomMaid.isAnnoyed(this);
                currentRoom.removeCharacter(this);

                if (this.getHealthBar().equalsIgnoreCase("Empty")) {
                    System.out.println("GAME OVER!!");
                    return true;
                } else {
                    System.out.println("Sorry the Maid got you. Be Careful next time :)\n");
                    inventory.clear();
                    return true;
                }

            }
        }
        return false;
    }

    public boolean isGuardAnnoyed(Guard roomGuard, boolean caughtChance) {
        currentRoom = this.getRoom();
        if(roomGuard != null){
            if(roomGuard.isSleeping()){
                // Changing the guard action.
                roomGuard.setSleeping(false);
                roomGuard.setStopAction(true);
            }

            else if(roomGuard.getStopAction() && caughtChance(51)){
                // Editing the health bar.
                gettingCaught++;
                // Calling guard isAnnoyed to send the player to the starting room.
                roomGuard.isAnnoyed(this);
                currentRoom.removeCharacter(this);

                if (this.getHealthBar().equalsIgnoreCase("Empty")) {
                    System.out.println("GAME OVER!!");
                    return true;
                } else {
                    System.out.println("Sorry the Guard got you. Be Careful next time :)\n");
                    inventory.clear();
                    return true;
                }
            }

            else if (!roomGuard.isSleeping() && caughtChance) {
                // Editing the health bar.
                gettingCaught++;
                // Calling guard isAnnoyed to send the player to the starting room.
                roomGuard.isAnnoyed(this);
                currentRoom.removeCharacter(this);

                if (this.getHealthBar().equalsIgnoreCase("Empty")) {
                    System.out.println("GAME OVER!!");
                    return true;
                } else {
                    System.out.println("Sorry the Guard got you. Be Careful next time :)\n");
                    inventory.clear();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cheatLook(String roomName, boolean cheatMode){
        if(cheatMode){
            // Getting the room by its name;
            Room room = gameData.getRoomByName(roomName.toLowerCase());

            if (roomName.equalsIgnoreCase("all")) {
                System.out.println(gameData);
                return true;
            }

            else if (room != null) {
                System.out.println(room + "\n");
                return true;
            }

            else {
                System.out.println("We can't find the room.\n");
                return false;
            }
            // If the player in not on the cheating mode.
        }
        System.out.println("You can't use these commands.\n");
        return false;
    }

    public String look(String roomName){
        Room room = gameData.getRoomByName(roomName);
        if(room != null){
            return room.toString();
        }
        return null;
    }


    // The help message.
    private static String printHelpMessage(){
        StringBuilder helpMessage = new StringBuilder();
        // help section
        helpMessage.append("help >>> to print the help message.\n");
        // look message
        helpMessage.append("look >>> to print everything in the room from [Characters, Items, Doors].\n");
        // north message
        helpMessage.append("north >>> to move in the north direction and go to the other room.\n");
        // south message
        helpMessage.append("south >>> to move in the south direction and go to the other room.\n");
        // east message
        helpMessage.append("east >>> to move in the east direction and go to the other room.\n");
        // west message
        helpMessage.append("west >>> to move in the west direction and go to the other room.\n");
        // inventory message
        helpMessage.append("inventory >>> to print the items that the player has\n");
        // inspect message
        helpMessage.append("inspect:name >>> to print the inspectString for [Item, Door, Character].\n\t >>> You need to enter the name for the [Item, Door, Character].\n");
        // lock message
        helpMessage.append("lock:name >>> to lock a door if you have the key.\n\t >>> You need to enter the door name.\n");
        // lock message
        helpMessage.append("unlock:name >>> to unlock a door if you have the key.\n\t >>> You need to enter the door name.\n");
        // pick item message
        helpMessage.append("pickup:name >>> to pick the item and add it to the player's bag.\n\t >>> You need to enter the door name.\n");
        // drop item message
        helpMessage.append("drop:name >>> to drop the item and drop it from the player's bag.\n\t >>> You need to enter the door name.\n");
        // input style
        helpMessage.append("command:name -- Example: drop:itemName\n");
        // escape message
        helpMessage.append("escape >>> to escape from the castle if you can.\n");
        // exit message
        helpMessage.append("exit | quit >>> to quit the game.\n");
        // cheating mode
        helpMessage.append("cheatmode >>> to be able to ask to look for any room by its name or to see all the rooms.\n");
        helpMessage.append("\t>>> look:all | look:roomName\n");
        helpMessage.append("nocheatmode >>> prevent you from using the cheatmode.\n");
        return helpMessage.toString();
    }

    // "Play()" responsible for operating the whole game.
    public void play(Scanner scanner){

        System.out.println("\nWelcome to the Castle Game :)\n");
        System.out.println(printHelpMessage());
        System.out.println("Let's Start .....\n");
        while (true){

            System.out.print(" >>> ");
            String input = scanner.nextLine();

            // To quit the game.
            if (input.equalsIgnoreCase("quit") | input.equalsIgnoreCase("exit")) {
                System.out.println("Good bey ;)");
                break;
            }
            // To print the player items.
            else if (input.equalsIgnoreCase("inventory")) {
                System.out.println(inventory.printInventoryList());
            }
            // To ask for help.
            else if (input.equalsIgnoreCase("help")) {
                System.out.println(printHelpMessage());
            }
            // To move in the north direction.
            else if (input.equalsIgnoreCase("north")) {
                this.moveNorth();
            }
            // To move in the south direction.
            else if (input.equalsIgnoreCase("south")) {
                this.moveSouth();
            }
            // To move in the east direction.
            else if (input.equalsIgnoreCase("east")) {
                this.moveEast();
            }
            // To move in the west direction.
            else if (input.equalsIgnoreCase("west")) {
                this.moveWest();

            } else if (input.equalsIgnoreCase("look")) {
                currentRoom = this.getRoom();
                System.out.println(currentRoom.look());
            } else if (input.equalsIgnoreCase("escape")) {
                if(this.escape()){
                    return;
                }
            } else if(input.equalsIgnoreCase("cheatmode")){
                cheatMode = true;
                System.out.println("Now you are on the cheating mode.\n");
            } else if (input.equalsIgnoreCase("nocheatmode")) {
                cheatMode = false;
                System.out.println("Now you closed the cheating mode.\n");
            }

            // If the input contains (:).
            else if (input.contains(":")) {
                int colonIndex = input.indexOf(":");
                String newInput = input.substring(0, colonIndex);

                // To pickup item.
                if (newInput.equalsIgnoreCase("pickup")) {
                    // Getting the item by name.
                    String inputItemName = input.substring(colonIndex + 1);
                    this.pickupItem(inputItemName);
                }

                // To drop item.
                else if (newInput.equalsIgnoreCase("drop")) {
                    // Getting the item by name.
                    String inputItemName = input.substring(colonIndex + 1);
                    this.dropItem(inputItemName);
                }

                // To unlock the door if you have the key.
                else if (newInput.equalsIgnoreCase("unlock")) {
                    // Getting the door name.
                    String doorName = input.substring(colonIndex + 1);
                    this.unlockDoor(doorName);
                }

                // To lock the door if you have the key.
                else if (newInput.equalsIgnoreCase("lock")) {
                    // Getting the door name.
                    String doorName = input.substring(colonIndex + 1);
                    this.lockDoor(doorName);
                }

                // To inspect everything in the game.
                else if (newInput.equalsIgnoreCase("inspect")) {
                    // Getting the name from the input.
                    String inspectName = input.substring(colonIndex + 1);

                    Guard guard = gameData.getGuardByName(inspectName);
                    Maid maid = gameData.getMaidByName(inspectName);
                    Room room = gameData.getRoomByName(inspectName);
                    Door door = gameData.getDoorByName(inspectName);
                    Item item = gameData.getItemByName(inspectName);
                    boolean checkItemInRoom = false;

                    if (door != null) {
                        // Print the inspectString for the door.
                        System.out.println(door.inspectString());
                    } else if (room != null) {
                        // Print the inspectString for the room.
                        System.out.println(room.inspectString());
                    } else if (maid != null) {
                        // Print the inspectString for the maid.
                        System.out.println(maid.inspectString());
                    } else if (guard != null) {
                        // Print the inspectString for the guard.
                        System.out.println(guard.inspectString());
                    } else if (item != null) {

                        currentRoom = this.getRoom();
                        characters = currentRoom.getCharacters();
                        boolean checkToPrint = true;
                        checkItemInRoom = true;

                        for (Character character : characters) {
                            Guard roomGuard = gameData.getGuardByName(character.getName());
                            Maid roomMaid = gameData.getMaidByName(character.getName());

                            if (roomMaid != null) {
                                boolean caughtChance = caughtChance(21);
                                if(this.isMaidAnnoyed(roomMaid,caughtChance)){
                                    if(checkToEndTheGame()){
                                        return;
                                    }
                                    checkToPrint = false;
                                    checkItemInRoom = false;
                                }

                            } else if (roomGuard != null) {
                                boolean caughtChance = caughtChance(21);
                                if(this.isGuardAnnoyed(roomGuard,caughtChance)){
                                    if(checkToEndTheGame()){
                                        return;
                                    }
                                    checkToPrint = false;
                                    checkItemInRoom = false;
                                }
                            }

                        }
                        if (checkToPrint) {
                            items = currentRoom.getItems();
                            for (Item item1 : items) {
                                if (item1 == item) {
                                    // Making the subItems visible.
                                    item.makeVisible();
                                    // Print the inspectString for the item.
                                    System.out.println(item.inspectString());
                                    checkItemInRoom = false;
                                }

                                for (Item item2 : item1.getSubItems()) {
                                    if (item2 == item) {
                                        // Making the subItems visible.
                                        item.makeVisible();
                                        // Print the inspectString for the item.
                                        System.out.println(item.inspectString());
                                        checkItemInRoom = false;
                                    }
                                }
                            }

                        }

                    }
                    if (checkItemInRoom) {
                        System.out.println("Can't inspect your input ;(\n");
                    }

                } else if (newInput.equalsIgnoreCase("look")) {
                    // Having the string after the colon
                    String roomName = input.substring(colonIndex + 1);
                    cheatLook(roomName,cheatMode);
                }
                // If the user entered a strange comment with (:).
                else {
                    System.out.println("Can't detect the command.\nPlease try again ....\n");
                }

            }

            // If the user entered a strange comment.
            else {
                System.out.println("Can't detect the command.\nPlease try again ....\n");
            }

        }

    }

    private boolean checkToEndTheGame() {
        if(gettingCaught == 5)
            return true;
        return false;
    }


    public String toString(){
        return this.getName() + " | Health Bar: " + getHealthBar();
    }

}
