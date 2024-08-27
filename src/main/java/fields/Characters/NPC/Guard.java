// Mario Yassa
// CSC 241

package fields.Characters.NPC;

public class Guard extends NPC{

    private boolean sleeping;

    // Constructor for creating a Guard with a name and description
    /**
     * Make the constructor for the Guard that you have in the game.
     * @param name The name of the Guard
     * @param description The description of the Guard
     */
    public Guard(String name, String description, boolean sleeping, boolean stopAction) {
        super(name, description, stopAction);
        this.sleeping = sleeping;
    }

    // Returns if the guard is sleeping or not.
    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public boolean getStopAction(){
        return stopAction;
    }

    public void setStopAction(boolean stopAction){
        this.stopAction = stopAction;
    }

    public String inspectString(){
        StringBuilder guardData = new StringBuilder();
        guardData.append("Guard:-\n").append(super.inspectString());
        if (isSleeping()) {
            guardData.append("\nThe guard is sleeping\n");
        } else {
            guardData.append("\nThe guard isn't sleeping\n");
        }
        return guardData.toString();
    }

}