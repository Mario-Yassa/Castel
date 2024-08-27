package fields.Items;

import fields.Characters.Character;
import fields.Characters.Player.Player;
import fields.Rooms.Game;
import fields.Rooms.Room;
import fields.XMLhandler.Handler;

import java.util.ArrayList;

public class Inventory {

    private final double maxWeight = 25;
    private double weight = 0;
    private final ArrayList<Item> inventoryList;


    public Inventory() {
        inventoryList = new ArrayList<>();

    }

    // to pick the item.
    public boolean pickItem(Item item){
        weight = weight + item.getWeight();
        if(weight <= maxWeight){
            inventoryList.add(item);
            if(!item.getSubItems().isEmpty()){
                for(Item item1 : item.getSubItems()){
                    if(item1.isCarryable()){
                        inventoryList.add(item1);
                        weight += item1.getWeight();
                    }
                }
            }
            System.out.println("Item added SUCCESSFULLY\n");
            return true;
        }

        System.out.println("There is NO space in your bag.\n");
        weight = weight - item.getWeight();
        return false;

    }

    // To drop the item.
    public boolean dropItem(Item item){
        for(Item item1 : inventoryList){
            if(item == item1){
                inventoryList.remove(item);
                weight = weight - item.getWeight();
                System.out.println("Item removed SUCCESSFULLY\n");
                return true;
            }
        }
        System.out.println("You don't have this item.\n");
        return false;
    }

    // Check if the player has the item.
    public boolean checkHavingItem(Item item){
        for(Item item1 : inventoryList){
            if(item == item1){
                return true;
            }
        }
        return false;
    }

    public void clear() {
        inventoryList.clear();
    }

    // To print the player items.
    public String printInventoryList(){
        int counter = 1;
        StringBuilder playerItemsData = new StringBuilder();
        if(inventoryList.isEmpty()){
            return "The player has NO items.\n";
        } else {
            playerItemsData.append("You got Items with weight [").append(weight).append("] out of [").append(maxWeight).append("].\n");
            playerItemsData.append("The player items are:\n");
            for (Item item : inventoryList) {
                playerItemsData.append("\t – Item #").append(counter).append(" is ").append(item.getName());
                playerItemsData.append(" with weight [").append(item.getWeight()).append("].\n");
                counter++;
            }
        }
        return playerItemsData.toString();
    }

    // To return the playerItem list.
    public ArrayList<Item> getInventoryList(){
        return inventoryList;
    }


    public String toString(){
        StringBuilder data = new StringBuilder();
        data.append("Max weight is ").append(maxWeight).append(".\n");
        data.append("Your bag weight is ").append(weight).append(".\n");
        int counter = 1;
        if(inventoryList.isEmpty()){
            data.append("The player has no Items\n");
        }
        else {
            data.append("Items:- \n");
            for (Item item : inventoryList) {
                data.append(counter).append(". ").append(item).append("\n");
            }
        }
        return data.toString();
    }


}
