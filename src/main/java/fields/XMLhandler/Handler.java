package fields.XMLhandler;

import fields.Characters.NPC.Guard;
import fields.Characters.NPC.Maid;
import fields.Characters.Player.Player;
import fields.Doors.Door;
import fields.Items.Item;
import fields.Items.Key;
import fields.Items.Thing;
import fields.Rooms.Game;
import fields.Rooms.Room;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {
    private Room room;
    private Thing thing;
    private Player player;
    public static Game gameData;

    // Constructor to send all the data to the game class
    public Handler(Game gameData) {
        Handler.gameData = gameData;
    }

    // Start Document
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    // Start Element
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        switch (qName) {
            case "room" -> {
                // Getting the room data from the XML file.
                String roomName = attributes.getValue("name");
                String roomDescription = attributes.getValue("description");
                // Creating a new room.
                room = new Room(roomName, roomDescription);
                // Adding the room to the game data to manage all the rooms.
                gameData.addRoom(room);
            }
            case "door" -> {
                // Getting the door data from the XML file.
                String doorName = attributes.getValue("name");
                String doorDescription = attributes.getValue("description");
                boolean isLocked = Boolean.parseBoolean(attributes.getValue("locked"));
                String direction = attributes.getValue("direction");
                String otherRoom = attributes.getValue("other_room");
                // Creating a new door.
                Door door = new Door(doorName, doorDescription, isLocked);
                // Adding the door to gameData.
                gameData.addDoor(door);
                // By the help of the game data we are going to get all the data for the other room by its name.
                Room secondRoom = gameData.getRoomByName(otherRoom);
                // Setting the doors for the rooms by the direction.
                // Setting the otherRoom on the other side of the door.
                if (direction.equalsIgnoreCase("north")) {
                    room.setNorthDoor(door);
                    if (secondRoom != null)
                        secondRoom.setSouthDoor(door);
                } else if (direction.equalsIgnoreCase("south")) {
                    room.setSouthDoor(door);
                    if (secondRoom != null)
                        secondRoom.setNorthDoor(door);
                } else if (direction.equalsIgnoreCase("east")) {
                    room.setEastDoor(door);
                    if (secondRoom != null)
                        secondRoom.setWestDoor(door);
                } else if (direction.equalsIgnoreCase("west")) {
                    room.setWestDoor(door);
                    if (secondRoom != null)
                        secondRoom.setEastDoor(door);
                } else
                    System.out.println("Unable to detect Direction....");
                // Setting the rooms on both sides of the door.
                door.setRoom(room);
                door.setRoom(secondRoom);
            }
            case "maid" -> {
                // Getting the maid data from the XML file.
                String maidName = attributes.getValue("name");
                String maidDescription = attributes.getValue("description");
                boolean isCleaning = Boolean.parseBoolean(attributes.getValue("cleaning"));
                boolean maidStopAction = Boolean.parseBoolean(attributes.getValue("stopAction"));
                // Creating a new maid.
                Maid maid = new Maid(maidName, maidDescription, isCleaning, maidStopAction);
                // Adding the maid to the room.
                room.addCharacter(maid);
                gameData.addCharacter(maid);
                gameData.addMaid(maid);
            }
            case "guard" -> {
                // Getting the guard data from the XML file.
                String guardName = attributes.getValue("name");
                String guardDescription = attributes.getValue("description");
                boolean isSleeping = Boolean.parseBoolean(attributes.getValue("sleeping"));
                boolean guardStopAction = Boolean.parseBoolean(attributes.getValue("stopAction"));
                // Creating a new guard.
                Guard guard = new Guard(guardName, guardDescription, isSleeping, guardStopAction);
                // Adding the guard to the room.
                room.addCharacter(guard);
                gameData.addCharacter(guard);
                gameData.addGuard(guard);
            }
            case "item" -> {
                // Getting the item data from the XML file.
                String itemName = attributes.getValue("name");
                String itemDescription = attributes.getValue("description");
                double itemWeight = Double.parseDouble(attributes.getValue("weight"));
                boolean isCarryable = Boolean.parseBoolean(attributes.getValue("carryable"));
                boolean isHidden = Boolean.parseBoolean(attributes.getValue("hidden"));
                // Creating a new item.
                if (isHidden) {
                    Thing subItem = new Thing(itemName, itemDescription, itemWeight, isCarryable, isHidden);
                    thing.addSubItem(subItem);
                } else {
                    thing = new Thing(itemName, itemDescription, itemWeight, isCarryable, isHidden);
                    // Adding the item to the room.
                    room.addItem(thing);
                    gameData.addItem(thing);
                }
            }
            case "key" -> {
                // Getting the item key from the XML file.
                String keyName = attributes.getValue("name");
                String keyDescription = attributes.getValue("description");
                double keyWeight = Double.parseDouble(attributes.getValue("weight"));
                boolean keyCarryable = Boolean.parseBoolean(attributes.getValue("carryable"));
                boolean keyHidden = Boolean.parseBoolean(attributes.getValue("hidden"));
                String keyDoorName = attributes.getValue("door");
                // Creating a new key.
                Key key = new Key(keyName, keyDescription, keyWeight, keyCarryable, keyHidden, keyDoorName);
                // Adding the key to the main item.
                thing.addSubItem(key);
            }
            case "player" -> {
                // Getting the player data from the XML file.
                String playerName = attributes.getValue("name");
                String playerDescription = attributes.getValue("description");
                // Creating the player.
                player = new Player(playerName, playerDescription);
                // Adding the player to the starting room.
                room.addCharacter(player);
                gameData.addCharacter(player);
            }
        }
    }


    // End Element
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    // End Document
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public Player getPlayer(){
        return player;
    }


}
