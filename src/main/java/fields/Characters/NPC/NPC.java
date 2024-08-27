package fields.Characters.NPC;

import fields.Characters.Character;
import fields.Characters.Player.Player;
import fields.Rooms.Game;
import fields.Rooms.Room;
import fields.XMLhandler.Handler;

public abstract class NPC extends Character {

    Game gameData;
    Room defaultRoom;
    boolean stopAction;

    // Constructor for creating a Non Player Character with a name and description.
    public NPC(String name, String description, Boolean stopAction) {
        super(name, description);
        this.stopAction = stopAction;
        gameData = Handler.gameData;
    }

    private Room getDefaultRoom(){
        return gameData.getRoomByName("Dungeon Cell");
    }

     public void isAnnoyed(Player player){
        defaultRoom = getDefaultRoom();
        player.setRoom(defaultRoom);
        defaultRoom.addCharacter(player);
     }
}
