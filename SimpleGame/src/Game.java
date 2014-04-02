import java.util.Scanner;

public class Game {

    // Globals Variables 
    public static final boolean DEBUGGING = true;   // Debugging
    public static final int MAX_LOCALES = 8;        // Total number of of locations.
    public static int currentLocale = 0;            // Location where the player starts. 
    public static String command;                   // What the player types as he or she plays the game.
    public static boolean stillPlaying = true;      // Controls the game loop.
    public static Locale[] locations;               // An uninitialized array.
    public static int[][] nav;                     // An uninitialized array of type int int.
    public static int moves = 0;                    // Counter of the player's moves.
    public static int score = 0;                    // Players Scores
    public static float achievement = 0;              // Players move ratios 
    public static  Item [] playerItem;              // Player items that they can use
    public static  Item [] inventory= new Item[4];  // An array of items that the player took.
    public static int takenItem = 0;                // How many items you have

    public static void main(String[] args) {
        if (DEBUGGING) {
            // Display the command line args.
           
            System.out.println("Title of Game: Adventure Quest at Marist");
            System.out.println("Player wakes up in the middle of a dorm room. And follows his to do list!");

            for (int i = 0; i < args.length; i++) {
                System.out.println(i + ":" + args[i]);
            }
        }

        if (args.length > 0) {
            try {
                int startLocation = Integer.parseInt(args[0]);
                if (startLocation >= 0 && startLocation <= MAX_LOCALES) {
                    currentLocale = startLocation;
                }
            } catch (NumberFormatException ex) {   
                System.out.println("Not a valid starting point: " + args[0]);
                if (DEBUGGING) {
                    System.out.println(ex.toString());
                }
            }
        }

        // Game Starts Here
        init();
        updateDisplay();

        // Game Loop
        while (stillPlaying) {
            getCommand();
            navigate();
            updateDisplay();
        }

        // When the player hits "Q" or "q" this code text will display
        System.out.println("Thank you for playing.");
    }

    private static void init() {



        command = new String();
        stillPlaying = true;   // TODO: Do we need this?

        //location instances of the Locale class and Inventory instances of the Item Class.
        Locale loc0 = new Locale(0);
        loc0.setName("Dining");
        loc0.setDesc("Dining Room");
        loc0.setNext("You can only go east or south."); // where user can move to from currentLocation.

        Item item0 = new Item(0, "MAP");
        item0.setName("This is a Map");
        item0.setDesc("You can use this map for further guidance");
        item0.setItem(loc0);

        Locale loc1 = new Locale(1);
        loc1.setName("Lounge");
        loc1.setDesc("You can study here also socialize with your friends. ");
        loc1.setNext("You can only go west, east or south");

        Item item1 = new Item(1,"Library");
        item1.setName("Library");
        item1.setDesc("You can rents and get books here");
        item1.setItem(loc1);


        Locale loc2 = new Locale(2);
        loc2.setName("Storage Room");
        loc2.setDesc("You have found a Knife");
        loc2.setNext("You can go only west or south.");

        Item item2 = new Item(2,"Knife");
        item2.setName("Knife");
        item2.setDesc("This knife is very sharp be careful.");
        item2.setItem(loc2);

        Locale loc3 = new Locale(3);
        loc3.setName("Bathroom");
        loc3.setDesc("You are now in the bathroom");
        loc3.setNext("To get out of the bathroom you have to go north.");

        Item item3 = new Item(3,"Spoon");
        item3.setName("You have a spoon now");
        item3.setDesc("You can finally eat with the spoon");
        item3.setItem(loc3);

        Locale loc4 = new Locale(4);
        loc4.setName("Magic Shoppe");
        loc4.setDesc("This is where you can get all the items.");
        loc4.setNext("You can go in any direction");

        Item item4 = new Item(4,"Food");
        item4.setName("Cup, Yougurt, Pizza and a Fork");
        item4.setDesc("You can get the get these yummy treats from the Magic Shoppe ");
        item4.setItem(loc4);

        Locale loc5 = new Locale(5);
        loc5.setName("Gym");
        loc5.setDesc("Great place to get fit ");
        loc5.setNext("You can only go north, south or west.");

        Item item5 = new Item(5,"Jacket");
        item5.setName("Jacket");
        item5.setDesc("If its cold you can wear your jacket. ");
        item5.setItem(loc5);

        Locale loc6 = new Locale(6);
        loc6.setName("Hancock");
        loc6.setDesc("Welcome to the place of Computer Science");
        loc6.setNext("You can go only north and east");

        Item item6 = new Item(6,"Bike");
        item6.setName("Bike");
        item6.setDesc("You can use this bike to get to places faster.");
        item6.setItem(loc6);

        Locale loc7 = new Locale(7);
        loc7.setName("McDonalds");
        loc7.setDesc("You are at the McDonalds");
        loc7.setNext("You can go only north, west or east.");

        Item item7= new Item(7,"Menu Item Number 2");
        item7.setName("Cheese Burger, Fries and a Drink.");
        item7.setDesc("You can only go north");
        item7.setItem(loc7);

        Locale loc8 = new Locale(8);
        loc8.setName("Hudson");
        loc8.setDesc("You have a great view from here.");
        loc8.setNext("You can go only north or west.");

        Item item8 = new Item(8,"Fishes");
        item8.setName("You can fish here but its too cold. ");
        item8.setDesc("You leave by hitting Q");
        item8.setItem(loc8);

        
        // play items 
        playerItem = new Item[9];
        playerItem[0] = item0;  // Map
        playerItem[1] = item1; //Library
        playerItem[2] = item2; //Knife
        playerItem[3] = item3; //Spoon
        playerItem[4] = item4; //Food
        playerItem[5] = item5;  // Jacket
        playerItem[6] = item6;  //Boke
        playerItem[7] = item7;  //Burgers
        playerItem[8] = item8;  //Fishes



        //location arrays 
        locations = new Locale[9];
        locations[0] = loc0; 
        locations[1] = loc1; 
        locations[2] = loc2; 
        locations[3] = loc3; 
        locations[4] = loc4; 
        locations[5] = loc5; 
        locations[6] = loc6; 
        locations[7] = loc7; 
        locations[8] = loc8; 


        //navigation matrix.
        nav = new int[][]{
                                  /* N   S   W   E */
                                  /* 0   1   2   3 */
            /* nav[0] for loc 0 */ {-1, 3, -1, 1},
            /* nav[1] for loc 1 */ {-1, 4, 0, 2},
            /* nav[2] for loc 2 */ {-1, 5, 1, -1},
            /* nav[3] for loc 3 */ { 0, 6, -1, 4},
            /* nav[4] for loc 4 */ { 1, 7, 3, 5},
            /* nav[5] for loc 5 */ { 2, 8, 4, -1},
            /* nav[6] for loc 6 */ { 3, -1, -1, 7},
            /* nav[7] for loc 7 */ { 4, -1, 6, 8},
            /* nav[8] for loc 8 */ { 5, -1, 7, -1},
        };
    }




    private static void updateDisplay() {
        System.out.println(locations[currentLocale].getName());
        System.out.println(locations[currentLocale].getDesc());
        System.out.println(locations[currentLocale].getNext());

       if (!playerItem[currentLocale].getHasTaken()) {
        System.out.println(playerItem[currentLocale].getName());
        System.out.println(playerItem[currentLocale].getDesc());



      }
    }

    private static void getCommand() {
        System.out.print("[" + moves + " moves, score " + score + "," + " achievement " + achievement + "] ");
        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }

    private static void navigate() {
        final int INVALID = -1;
        int dir = INVALID;  

        if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n")) {
            dir = 0;
        } else if (command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s")) {
            dir = 1;
        } else if (command.equalsIgnoreCase("west") || command.equalsIgnoreCase("w")) {
            dir = 2;
        } else if (command.equalsIgnoreCase("east") || command.equalsIgnoreCase("e")) {
            dir = 3;
        } else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
            quit();
        } else if (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h")) {
            help();
        } else if (command.equalsIgnoreCase("take") || command.equalsIgnoreCase("t")) {
            take();
        } else if (command.equalsIgnoreCase("inventory") || command.equalsIgnoreCase("i")) {
            invt();
        } else if (command.equalsIgnoreCase("map") || command.equalsIgnoreCase("m")) {
            map();
        } else {
            System.out.println(" You entered an invalid command");



        }; 

        if (dir > -1) {   // This means a dir was set.
            int newLocation = nav[currentLocale][dir];
            if (newLocation == INVALID) {
                System.out.println("You can't go that way.");
            } else {
                currentLocale = newLocation;
                moves = moves + 1;
                if (!locations[currentLocale].getHasVisited())
                {
                    score = score + 5;
                    locations[currentLocale].setHasVisited(true);
                }

                achievement = (float)score / moves;

            }
        }
    }

    private static void help() {
        System.out.println("The commands are as follows:");
        System.out.println("   n/north");
        System.out.println("   s/south");
        System.out.println("   q/quit");
    }

    private static void map() {


            if (playerItem[currentLocale].getHasTaken()) {

            System.out.println("[" + locations[0].getName() + " ][" + locations[1].getName() + "][" + locations[2].getName() + "]    ");
            System.out.println("[" + locations[3].getName() + "][" + locations[4].getName() + "][" + locations[5].getName() + "] ");
            System.out.println("[" + locations[6].getName() + " ][" + locations[7].getName() + "][" + locations[8].getName() + "]       ");

                playerItem[currentLocale].setHasTaken(true);
        }
      }


    private static void take() {

              if (!playerItem[currentLocale].getHasTaken()) {

               System.out.println( playerItem[currentLocale].getName());

                inventory[takenItem] = playerItem[currentLocale];
                takenItem = takenItem + 1;

                  playerItem[currentLocale].setHasTaken(true);
            }
        }

   private static void invt() {
            System.out.println("You have " + takenItem + "in your inventory" );
            System.out.println("Your inventory includes: ");
       for (int i = 0; i <takenItem; i++) {
           System.out.print(i + ":" + inventory[i]);
       }

       System.out.println(" ");
   }

    private static void quit() {
        stillPlaying = false;
    }
}
