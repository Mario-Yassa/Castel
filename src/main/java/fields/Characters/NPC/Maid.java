package fields.Characters.NPC;

public class Maid extends NPC{

    private boolean cleaning;

    // Constructor for creating a Maid with a name and description.
    /**
     * Make the constructor for the Maid that you have in the game.
     * @param name The name of the Maid
     * @param description The description of the Maid
     */
    public Maid(String name, String description, boolean cleaning, boolean stopAction) {
        super(name, description, stopAction);
        this.cleaning = cleaning;
    }

    // Returning if the Maid is cleaning or not.
    public boolean isCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public boolean getStopAction(){
        return stopAction;
    }

    public void setStopAction(boolean stopAction){
        this.stopAction = stopAction;
    }

    public String inspectString(){
        StringBuilder maidData = new StringBuilder();
        maidData.append("Maid:-\n").append(super.inspectString());
        if (isCleaning()) {
            maidData.append("\nThe maid is cleaning\n");
        } else {
            maidData.append("\nThe maid isn't cleaning\n");
        }
        return maidData.toString();
    }


}
