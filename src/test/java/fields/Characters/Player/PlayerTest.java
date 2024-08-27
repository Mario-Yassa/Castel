package fields.Characters.Player;

import fields.Characters.NPC.Guard;
import fields.Characters.NPC.Maid;
import fields.Doors.Door;
import fields.Items.Item;
import fields.Main.main;
import fields.Rooms.Game;
import fields.Rooms.Room;

import fields.XMLhandler.Handler;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;


public class PlayerTest {
    private final Game gameData = new Game();
    Handler handler;
    private Player player;

    @Before
    public void setUp(){
        // Parsing the xml file to test the data from it.
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            InputStream input = main.class.getClassLoader().getResourceAsStream("GameData.xml");
            if (input == null) {
                throw new FileNotFoundException("File is NOT found");
            }
            SAXParser saxParser = spf.newSAXParser();
            handler = new Handler(gameData);
            saxParser.parse(input, handler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        player = gameData.getPlayerByName();
        assertNotNull(player);
    }

    @Test
    public void testCheckPlayerInRoom(){
        Room room = new Room("Room","");
        assertFalse(room.checkPlayerInRoom(player));
        room.addCharacter(player);
        assertTrue(room.checkPlayerInRoom(player));
    }

    @Test
    public void testCheck2Players(){
        Room room = new Room("Room","");
        room.addCharacter(player);
        assertFalse(room.check2Players(player));
        room.addCharacter(player);
        assertTrue(room.check2Players(player));
    }

    @Test
    public void testRoomConnections(){

        // Initializing the Main variables for the test
        Room room = gameData.getRoomByName("Laundry");
        Door door1, door2;
        Room room1, room2;

        // Get the North Door and check the rooms forward and backward.
        if (room.getNorthDoor() != null){
            door1 = room.getNorthDoor();
            room1 = gameData.getRoomByName(door1.getOtherRoom(room));
            door2 = room1.getSouthDoor();
            room2 = gameData.getRoomByName(door2.getOtherRoom(room1));
            // door1 == door2
            assertEquals(door1,door2);
            // room == room2
            assertEquals(room,room2);
        }
        // Get the South Door and check the rooms forward and backward.
        if (room.getSouthDoor() != null){
            door1 = room.getSouthDoor();
            room1 = gameData.getRoomByName(door1.getOtherRoom(room));
            door2 = room1.getNorthDoor();
            room2 = gameData.getRoomByName(door2.getOtherRoom(room1));
            // door1 == door2
            assertEquals(door1,door2);
            // room == room2
            assertEquals(room,room2);
        }
        // Get the East Door and check the rooms forward and backward.
        if (room.getEastDoor() != null){
            door1 = room.getEastDoor();
            room1 = gameData.getRoomByName(door1.getOtherRoom(room));
            door2 = room1.getWestDoor();
            room2 = gameData.getRoomByName(door2.getOtherRoom(room1));
            // door1 == door2
            assertEquals(door1,door2);
            // room == room2
            assertEquals(room,room2);
        }
        // Get the West Door and check the rooms forward and backward.
        if (room.getWestDoor() != null){
            door1 = room.getWestDoor();
            room1 = gameData.getRoomByName(door1.getOtherRoom(room));
            door2 = room1.getEastDoor();
            room2 = gameData.getRoomByName(door2.getOtherRoom(room1));
            // door1 == door2
            assertEquals(door1,door2);
            // room == room2
            assertEquals(room,room2);
        }

    }

    @Test
    public void testMoveNorth(){
        Room previousRoom;
        Room newRoom;

        // check the normal case.
        previousRoom = player.getRoom();
        player.moveNorth();
        newRoom = player.getRoom();
        assertEquals("Dungeon Antechamber",newRoom.getName());
        assertNotEquals(previousRoom,newRoom);

        // Check NOT having the player.
        assertFalse(previousRoom.checkPlayerInRoom(player));
        // Check having the player.
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check the normal case.
        player.setRoom(gameData.getRoomByName("Laundry"));
        previousRoom = player.getRoom();
        player.moveNorth();
        newRoom = player.getRoom();
        assertEquals("Bake House",newRoom.getName());
        assertNotEquals(previousRoom,newRoom);

        // Check NOT having the player.
        assertFalse(previousRoom.checkPlayerInRoom(player));
        // Check having the player.
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check when there is no north door.
        player.setRoom(gameData.getRoomByName("Dungeon Antechamber"));
        previousRoom = player.getRoom();
        player.moveNorth();
        newRoom = player.getRoom();
        assertEquals(previousRoom, newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check when the door is locked.
        player.setRoom(gameData.getRoomByName("Gallery"));
        previousRoom = player.getRoom();
        previousRoom.addCharacter(player);
        previousRoom.addCharacter(player);
        player.moveNorth();
        newRoom = player.getRoom();
        assertEquals(previousRoom, newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));
    }

    @Test
    public void testMoveSouth(){
        Room previousRoom;
        Room newRoom;

        // check when there is no south door.
        previousRoom = player.getRoom();
        player.moveSouth();
        newRoom = player.getRoom();
        assertEquals(previousRoom,newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check the normal case.
        player.setRoom(gameData.getRoomByName("Dungeon Antechamber"));
        previousRoom = player.getRoom();
        player.moveSouth();
        newRoom = player.getRoom();
        assertEquals("Dungeon Cell",newRoom.getName());
        assertNotEquals(previousRoom,newRoom);

        // Check NOT having the player.
        assertFalse(previousRoom.checkPlayerInRoom(player));
        // Check having the player.
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check when the door is locked.
        player.setRoom(gameData.getRoomByName("Main Entry"));
        previousRoom = player.getRoom();
        player.moveSouth();
        newRoom = player.getRoom();
        assertEquals(previousRoom,newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check when the door is locked.
        player.setRoom(gameData.getRoomByName("Gallery"));
        previousRoom = player.getRoom();
        player.moveSouth();
        newRoom = player.getRoom();
        assertEquals(previousRoom,newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check when the door is locked.
        player.setRoom(gameData.getRoomByName("Lady's Apartment"));
        previousRoom = player.getRoom();
        player.moveSouth();
        newRoom = player.getRoom();
        assertEquals(previousRoom,newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

    }

    @Test
    public void testMoveEast(){
        Room previousRoom;
        Room newRoom;

        // check when there is no east door.
        previousRoom = player.getRoom();
        player.moveEast();
        newRoom = player.getRoom();
        assertEquals(previousRoom,newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check when the door is locked.
        player.setRoom(gameData.getRoomByName("Dungeon Antechamber"));
        previousRoom = player.getRoom();
        player.moveEast();
        newRoom = player.getRoom();
        assertEquals(previousRoom,newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check the normal case.
        player.setRoom(gameData.getRoomByName("Butler Pantry"));
        previousRoom = player.getRoom();
        player.moveEast();
        newRoom = player.getRoom();
        assertNotEquals(previousRoom,newRoom);
        assertEquals("Bake House",newRoom.getName());

        // Check NOT having the player.
        assertFalse(previousRoom.checkPlayerInRoom(player));
        // Check having the player.
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));


    }

    @Test
    public void testMoveWest(){
        Room previousRoom;
        Room newRoom;

        // check when there is no west door.
        previousRoom = player.getRoom();
        player.moveWest();
        newRoom = player.getRoom();
        assertEquals(previousRoom,newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check when the door is locked.
        player.setRoom(gameData.getRoomByName("Dungeon Stairs"));
        previousRoom = player.getRoom();
        player.moveWest();
        newRoom = player.getRoom();
        assertEquals(previousRoom,newRoom);

        // Check having the player.
        assertTrue(previousRoom.checkPlayerInRoom(player));
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check the normal case.
        player.setRoom(gameData.getRoomByName("Bake House"));
        previousRoom = player.getRoom();
        player.moveWest();
        newRoom = player.getRoom();
        assertNotEquals(previousRoom,newRoom);
        assertEquals("Butler Pantry",newRoom.getName());

        // Check NOT having the player.
        assertFalse(previousRoom.checkPlayerInRoom(player));
        // Check having the player.
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));

        // check the normal case.
        player.setRoom(gameData.getRoomByName("Great Parlor"));
        previousRoom = player.getRoom();
        player.moveWest();
        newRoom = player.getRoom();
        assertNotEquals(previousRoom,newRoom);
        assertEquals("Ale Cellar",newRoom.getName());

        // Check NOT having the player.
        assertFalse(previousRoom.checkPlayerInRoom(player));
        // Check having the player.
        assertTrue(newRoom.checkPlayerInRoom(player));

        // Check 2 players in the same room.
        assertFalse(previousRoom.check2Players(player));
        assertFalse(newRoom.check2Players(player));
    }

    @Test
    public void testEscape(){
        // Escaping from the wrong room.
        assertFalse(player.escape());

        // Escaping from the Exit room.
        player.setRoom(gameData.getRoomByName("Exit"));
        assertTrue(player.escape());
    }

    @Test
    public void testPickupItem(){
        player.setRoom(gameData.getRoomByName("Dungeon Antechamber"));

        // Try to pickup NOT carry-able thing.
        assertFalse(player.pickupItem("wooden chair"));

        // Try to pickup hidden thing.
        assertFalse(player.pickupItem("Dungeon Stair Key"));

        // Try to pickup subItem.
        Item item = gameData.getItemByName("wooden chair");
        item.makeVisible();
        assertTrue(player.pickupItem("Dungeon Stair Key"));

        // Try to pickup Item from another room.
        assertFalse(player.pickupItem("Dirty Laundry Pile"));

        // Picking the item more than once.
        assertFalse(player.pickupItem("Dungeon Stair Key"));

        // Try to pick something strange.
        assertFalse(player.pickupItem("newItem"));
    }

    @Test
    public void testDropITem(){
        // Try to drop item that the player doesn't have.
        assertFalse(player.dropItem("Dungeon Stair Key"));

        // Try to drop something strange.
        assertFalse(player.dropItem("newItem"));

        // Trying the normal case.
        player.setRoom(gameData.getRoomByName("Dungeon Antechamber"));
        Item item = gameData.getItemByName("wooden chair");
        item.makeVisible();
        player.pickupItem("Dungeon Stair Key");
        assertTrue(player.dropItem("Dungeon Stair Key"));

    }

    @Test
    public void testLock(){
        Room room;
        Item item;

        // Try to lock a null door.
        room = gameData.getRoomByName("Dungeon Antechamber");
        player.setRoom(room);
        assertFalse(player.lockDoor("      "));

        // Try to lock the door without the key.
        room = gameData.getRoomByName("Dungeon Antechamber");
        player.setRoom(room);
        assertFalse(player.lockDoor("Dungeon Stair Door"));

        // Try to lock a locked door.
        room = gameData.getRoomByName("Dungeon Antechamber");
        player.setRoom(room);
        assertFalse(player.lockDoor("Dungeon Stair Door"));

        // Try to lock the door with the key, outside the room.
        room = gameData.getRoomByName("Dungeon Antechamber");
        player.setRoom(room);
        item = gameData.getItemByName("wooden chair");
        item.makeVisible();
        player.pickupItem("Dungeon Stair Key");
        // Change the player room.
        player.setRoom(gameData.getRoomByName("Dungeon Cell"));
        assertFalse(player.lockDoor("Dungeon Stair Door"));

        // Try to lock a door with the key.
        Door door = gameData.getDoorByName("Dungeon Stair Door");
        door.unlock();
        player.setRoom(room);
        assertTrue(player.lockDoor("Dungeon Stair Door"));

    }

    @Test
    public void testUnlock(){
        Room room;
        Item item;

        // Try to unlock a null door.
        room = gameData.getRoomByName("Dungeon Antechamber");
        player.setRoom(room);
        assertFalse(player.unlockDoor("      "));

        // Try to unlock the door without the key.
        room = gameData.getRoomByName("Dungeon Antechamber");
        player.setRoom(room);
        assertFalse(player.unlockDoor("Dungeon Stair Door"));

        // Try to unlock the door with the key, outside the room.
        room = gameData.getRoomByName("Dungeon Antechamber");
        player.setRoom(room);
        item = gameData.getItemByName("wooden chair");
        item.makeVisible();
        player.pickupItem("Dungeon Stair Key");
        // Change the player room.
        player.setRoom(gameData.getRoomByName("Dungeon Cell"));
        assertFalse(player.unlockDoor("Dungeon Stair Door"));

        // Try to unlock a door with the key.
        room = gameData.getRoomByName("Dungeon Antechamber");
        player.setRoom(room);
        item = gameData.getItemByName("wooden chair");
        item.makeVisible();
        player.pickupItem("Dungeon Stair Key");
        assertTrue(player.unlockDoor("Dungeon Stair Door"));

        // Try to unlock an unlocked door.
        assertFalse(player.unlockDoor("Dungeon Stair Door"));
    }

    @Test
    public void testIsGuardAnnoyed(){
        Guard guard;


        // Try true sleeping guard with true caughtChance.
        guard = gameData.getGuardByName("Sleeping Guard");
        assertFalse(player.isGuardAnnoyed(guard, true));
        // Check changing sleeping and stopAction.
        assertFalse(guard.isSleeping());
        assertTrue(guard.getStopAction());

        // Try true sleeping guard with false caughtChance.
        guard = gameData.getGuardByName("Sleeping Guard");
        guard.setSleeping(true);
        assertFalse(player.isGuardAnnoyed(guard, false));

        // Try false sleeping guard with false caughtChance.
        guard = gameData.getGuardByName("Alonzo");
        assertFalse(player.isGuardAnnoyed(guard, false));

        // Try false sleeping guard with true caughtChance. End by catching the player.
        guard = gameData.getGuardByName("Bethany");
        assertTrue(player.isGuardAnnoyed(guard, true));
        // Check that the player's list is empty.
        assertTrue(player.checkClear());
        // Check that stopAction is false.
        assertFalse(guard.getStopAction());

        // Try a null guard with true caughtChance.
        guard = gameData.getGuardByName("    ");
        assertFalse(player.isGuardAnnoyed(guard, true));

    }

    @Test
    public void testIsMaidAnnoyed(){
        Maid maid;

        // Try true cleaning maid with true caughtChance. End by catching the player.
        maid = gameData.getMaidByName("Adam");
        assertTrue(player.isMaidAnnoyed(maid,true));
        // Check that the player's list is empty.
        assertTrue(player.checkClear());
        // Check that stopAction is false.
        assertFalse(maid.getStopAction());

        // Try false cleaning maid with false caughtChance.
        maid = gameData.getMaidByName("Jane");
        assertFalse(player.isMaidAnnoyed(maid,false));
        // Check changing
        assertTrue(maid.isCleaning());
        assertTrue(maid.getStopAction());

        // Try true cleaning maid with false caughtChance.
        maid = gameData.getMaidByName("Adam");
        assertFalse(player.isMaidAnnoyed(maid,false));

        // Try false cleaning maid with true caughtChance.
        maid = gameData.getMaidByName("Jane");
        maid.setCleaning(false);
        assertFalse(player.isMaidAnnoyed(maid,true));

        // Try a null maid with true caughtChance.
        maid = gameData.getMaidByName("    ");
        assertFalse(player.isMaidAnnoyed(maid,true));

    }

    @Test
    public void testRoom(){
        // Test finding the rooms with the BST.
        // Try the exact letters.
        String roomName = "Bake House";
        Room room = gameData.getRoomByName("Bake House");
        assertEquals(roomName, room.getName());

        // Try all letters in the lower case.
        roomName = "Bake House";
        room = gameData.getRoomByName("bake house");
        assertEquals(roomName, room.getName());

        // Try all letters in the upper case.
        roomName = "Bake House";
        room = gameData.getRoomByName("BAKE HOUSE");
        assertEquals(roomName, room.getName());

        // Try a null room.
        room = gameData.getRoomByName("room");
        assertNull(room);
    }

    @Test
    public void testCheatLook(){
        String roomName;

        // Test all with true cheatMode.
        assertTrue(player.cheatLook("all", true));

        // Test all with false cheatMode.
        assertFalse(player.cheatLook("all", false));

        // Test roomName with true cheatMode.
        roomName = "Bake House";
        assertTrue(player.cheatLook(roomName, true));

        // Test roomName with false cheatMode.
        assertFalse(player.cheatLook(roomName, false));

        // Test null room with true cheatMode.
        roomName = "room";
        assertFalse(player.cheatLook(roomName, true));
    }

}

