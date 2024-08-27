package fields.Items;

public class Key extends Item{
    private final String doorKeyName;

    /**
     * Make the constructor for the Item that you have in the room.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param weight The weight of the item.
     * @param carryable Is this item can be carried or not.
     * @param hidden Is this item visible or not.
     * @param doorKeyName The name of the door that this key open
     */
    public Key(String name, String description, double weight, boolean carryable, boolean hidden, String doorKeyName){
        super(name, description, weight, carryable, hidden);
        this.doorKeyName = doorKeyName;
    }
    public String getDoorKeyName(){
        return doorKeyName;
    }


}
