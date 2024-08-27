package fields.Rooms;

import fields.Characters.Character;
import fields.Characters.NPC.Guard;
import fields.Characters.NPC.Maid;
import fields.Characters.Player.Player;
import fields.Doors.Door;
import fields.Items.Item;
import BST.BST;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Item> items;
    private final ArrayList<Door> doors;
    private final ArrayList<Maid> maids;
    private final ArrayList<Guard> guards;
    private final ArrayList<Character> characters;
    private Player player;
    private final BST<String, Room> bstRooms;
    // Creating an ArrayList to store all the rooms of the castle.
    public Game(){
        bstRooms = new BST<String, Room>();
        doors = new ArrayList<>();
        items = new ArrayList<>();
        maids = new ArrayList<>();
        guards = new ArrayList<>();
        characters = new ArrayList<>();
    }


    // Adding a room with all its data to an ArrayList to get the neighbours for it.
    public void addRoom(Room room){
        bstRooms.insert(room.getName().toLowerCase(), room);
    }


    public void addDoor(Door door){
        doors.add(door);
    }

    public ArrayList<Door> getDoors(){
        return doors;
    }
    public Door getDoorByName(String doorName){
        for(Door door: doors){
            if(door.getName().equalsIgnoreCase(doorName)){
                return door;
            }
        }
        return null;
    }

    // Adding an Item with all its data to an ArrayList to get it later.
    public void addItem(Item item) {
        items.add(item);
    }

    // Getting the Item by just the name.
    public Item getItemByName(String itemName){
        for(Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
            for(Item subItem : item.getSubItems()){
                if (subItem.getName().equalsIgnoreCase(itemName)) {
                    return subItem;
                }
            }
        }
        return null;
    }


    // Getting the room by just the name.
    public Room getRoomByName(String roomName){

        roomName = roomName.toLowerCase();
        if(bstRooms.find(roomName) != null){
            return (bstRooms.find(roomName));
        }
        return null;
    }

    // Adding the maid to get it in the inspectString().
    public void addMaid(Maid maid) {
        maids.add(maid);
    }

    // Getting the Maid by name.
    public Maid getMaidByName(String maidName) {
        Maid maid;
        for(Maid maid1 : maids){
            if(maid1.getName().equalsIgnoreCase(maidName)){
                maid = maid1;
                return maid;
            }
        }
        return null;
    }

    // Adding the guard to get it in the inspectString().
    public void addGuard(Guard guard) {
        guards.add(guard);
    }

    // Getting the Guard by name.
    public Guard getGuardByName(String guardName) {
        Guard guard;
        for(Guard guard1 : guards){
            if(guard1.getName().equalsIgnoreCase(guardName)){
                guard = guard1;
                return guard;
            }
        }
        return null;
    }

    // To have all the characters in one list including the player.
    public void addCharacter(Character character){
        characters.add(character);
    }

    public Player getPlayerByName() {
        Character character;
        for(Character character1 : characters){
            if(character1.getName().equalsIgnoreCase("Aubrey")){
                character = character1;
                return (Player) character;
            }
        }
        return null;
    }

    public String toString(){
        StringBuilder data = new StringBuilder();
        for(Room room : bstRooms)
            data.append(room).append("\n");
        return data.toString();
    }


}
