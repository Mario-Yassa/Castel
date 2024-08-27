
        // Assignment #7 Documentation \\

// Add stopAction attributes to the xml file
// Read it by the handler
// Add it to the guard and maid through the NPC constructor
// Make setter and getter for the stopAction variable
// Make setter for isCleaning and isSleeping
// Make caughtChance() take an int called limit, this is for raising the chance of getting caught to be 50%
// Add tester in TestIsAnnoyed for the guards and maids to make sure that the methode changes the sleeping/cleaning variable and stopAction too.



        // Assignment #5 Design \\


// Moving NPCs
        /*
         * public boolean moveNPC(int counter);
         * The way I hope:
         * We will have a timer and each 5 min the NPCs will switch the rooms by getOtherRoom().
         * In the timer case: no need for the parameter.
         *
         * By a simple way:
         * making a counter in the player class to count the number of rooms that the player visited.
         * When the player visit 3 rooms then the NPCs will switch the rooms by getOtherRoom().
         *
         * While moving the NPCs the player can't be moving.
         *
         * This methode will be in the NPC class. It is accessible by the player class only.
         * But it will call methods from the room, door class.
         *
         * We need to add a variable in the player class called roomCounter. when it is 3 then guards will move and the counter will be 0.
         *
         * How the NPCs will move:
         * every door will call getOtherRoom() and if we have more than 4 NPCs. then we will have one in each side if we have 4 sides.
         * if less there less than 4 so the NPCs will move one in a cycle North then South then East then West if no room in one of the directions just skip it.
         *
         */


// stop the NPCs action.
        /*
         * public boolean stopAction();
         *
         * How this method works:
         * when there is a sleeping guard or cleaning maid,
         * If the player woke up (by inspecting anything in the room) one of the guards then this guard then inspecting any item will get chance 50% to see the player.
         * for the maid the player can stop the maid from cleaning (by inspecting anything in the room) then inspecting any item will get chance 50% to see the player.
         *
         * In the xml file we will add in the guard variable, attributes (boolean) for wokeUp.
         * In the xml file we will add in the maid variable, attributes (boolean) for stopCleaning.
         *
         * This methode will be in the NPC class. and will be called in the player class.
         *
         *
         */


// Hide the player
        /*
         * public boolean hide();
         *
         * It will be in the not carry able items. The player has to hide while the NPCs are switching rooms.
         * As the timer is counting before the 5 min ends the player shouldn't be visible.
         * All the carry able items can't be a place for the player to hide.
         *
         * This methode will be in the player class. It is accessible by the item class and the room class.
         *
         * In the xml file we will add in the item variable, attributes (boolean) for hiding the player.
         * We will add in the xml file player variable, attributes (boolean) for visible.
         *
         */



        // Assignment #2 Design \\


// public String healthBar(int health);     Done✅
    // to print the health bar for the player
    // return "[" + (*).repeat(health) + "]";
    // just accessible by the player class

// public boolean seeThePlayer();       Done✅
    // player will be sent to the starting room by returnBack() function in the player class.
    // This will happen if the cleaning is false or wakeup is true
    // then they are going to call player.returnBack
    // accessible by the NPC class and can call the player class

// public int returnBack(int health);       Done✅
    // returning the player back to the main room to start over again when he gets caught.
    // if the health = zero then game over else return health -= 1
    // call the character setRoom() and send him back to dungeon
    // accessible by the player class and called by isAnnoyed() from the NPC class

// public void move(String direction);      Done✅
    // use the room toString to see where can the user moves
    // then use the direction and move in the direction to check the items
    // accessible by the game, player, items and room class

// public void pickItem(Item item);     Done✅
    // if it's carry-able then check the total weight for the player's arrayList<item> then add the item
    // call item class and accessible by player class

// public void dropItem(Item item):     Done✅
    // to drop item from the player's arrayList
    // call item class and accessible by the player class

// public boolean seeItems();   Done✅
    // when the player checks the place where the item is located then we are going to change the hidden from false to true.
    // accessible by the item class and the room class and the player class

// public boolean checkTheWeight(); Done✅
    // when the player asks to pickItem() then first we need to check the total weight before adding the item to the ArrayList
    // accessible by the item class and the player class in pickItem() function

// public boolean openDoor(Key key);    Done✅
    // when the player find the key then he can open the door.
    // So we need to check that this is the right key for this door by checking the keyDoorName and the doorName
    // then player.setRoom(Room otherRoom)
    // accessible by the door class, Room class and the key class







        //  Additional  \\

// Test maid when he sees the player?   Done✅
// can/NOT pick the item if it's hidden?    Done✅

// Ignore the spaces in the commands like look and quit?

// when picking up the item --> remove it from the room and when dropping it put it in the room where you dropped it.
// Look (see if hidden items show up now)
// When getting Game over then quit.





// Questions
// 1. What if: pickup:Dirty Laundry Pile --> it's carry-able, but it got sub items in it.



