package fields.Items;

import fields.Inspectable.Inspectable;
import fields.Rooms.Room;

import java.util.ArrayList;

public abstract class Item implements Inspectable {


    // Instance Variables
    private final String name;
    private final String description;
    private final double weight;
    private boolean hidden;
    private final boolean carryable;
    private Room room;
    private final ArrayList<Item> subItems = new ArrayList<>();
    private Key key;

    // Constructor
    public Item(String name, String description, double weight, boolean carryable, boolean hidden) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.carryable = carryable;
        this.hidden = hidden;
    }

    public String getName(){
        return name;
    }

    // Method to add a sub-item to the ArrayList
    public void addSubItem(Item subItem) {
        subItems.add(subItem);
    }

    public void setHidden(Boolean hidden){
        this.hidden = hidden;
    }
    public ArrayList<Item> getSubItems() {
        return subItems;
    }

    public void makeVisible(){
        for(Item item : subItems){
            item.setHidden(false);
        }
    }

    // To show if the item is hidden or not. visible
    public boolean isHidden(){
        return hidden;
    }

    // To show if the item is carry-able or not.
    public boolean isCarryable(){
        return carryable;
    }

    public double getWeight() {
        return weight;
    }

    // Method to set the Room for the Item.
    /**
     * This methode sets the room for a new item to help the player knows where is it located.
     * @param room The room is the place where the player will find the Character(s), Door(s), Item(s), and the rest of the things for the game.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    // toString method to display Item name.
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append("Item name: ").append(name);
        if(!subItems.isEmpty()){
            data.append("\nSub-Item:-");
            for(Item item : subItems){
                if(!item.isHidden())
                    data.append("\n\t").append(item.getName());
            }
        }
        return data.toString();
    }


    public String look(){
        StringBuilder itemData = new StringBuilder();
        itemData.append("Item name: ").append(name);
        if(!subItems.isEmpty()){
            for(Item item : subItems){
                if(!item.isHidden())
                    itemData.append("\n\t\t- ").append(item.getName());
            }
        }
        return itemData.toString();
    }


    // inspectString method to display the Item name and description.
    public String inspectString() {
        StringBuilder itemData = new StringBuilder();
        itemData.append("Name: ").append(name);
        itemData.append("\nDescription: ").append(description);
        for(Item item : subItems){
            itemData.append("\nSub Item: ").append(item.getName());
        }
        itemData.append("\n");
        return itemData.toString();

    }





}
