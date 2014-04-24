import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    // Globals Variables 
	public static final boolean DEBUGGING = true;   // Debugging
    public static final int MAX_LOCALES = 8;        // Total number of of locations.
    public static  ListLoc currentLocation = null;  // Location where the player starts. 
    public static String command;                   // What the player types as he or she plays the game.
    public static boolean stillPlaying = true;      // Controls the game loop.
    public static Locale[] locations;               // An uninitialized array.
    public static int moves = 0;                    // Counter of the player's moves.
    public static int score = 0;                    // Players Scores
    public static double token = 0;					// form of money in the game
    public static float achievement = 0;              // Players move ratios 
    public static ShopList lm1 = new ShopList();     // Shop List Class linking 
    public static  Item [] playerItem;              // Player items that they can use
    public static  Item [] inventory= new Item[4];  // An array of items that the player took.
    public static ShopItem [] ShopInventory = new ShopItem [600]; //Arrays to hold items at the magicshoppe
    public static int takenItem = 0;                // How many items you have
    public static int takenMagicItem = 0;
    // Global Location Variables
    public static ListLoc li0=null; 
    public static ListLoc li1=null; 
    public static ListLoc li2=null; 
    public static ListLoc li3=null; 
    public static ListLoc li4=null; 
    public static ListLoc li5=null; 
    public static ListLoc li6=null; 
    public static ListLoc li7=null; 
    public static ListLoc li8=null; 


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
                    if (startLocation== 0) currentLocation = li0;
                    if (startLocation== 1) currentLocation = li1;
                    if (startLocation== 2) currentLocation = li2;
                    if (startLocation== 3) currentLocation = li3;
                    if (startLocation== 4) currentLocation = li4;
                    if (startLocation== 5) currentLocation = li5;
                    if (startLocation== 6) currentLocation = li6;
                    if (startLocation== 7) currentLocation = li7;
                    if (startLocation== 8) currentLocation = li8;
                    
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
        stillPlaying = true;   // 
        
     // Make the list manager.
        lm1.setName("Magic Items");
        lm1.setDesc("These are some of my favorite things.");

        //location instances of the Locale class and Inventory instances of the Item Class.
        Locale loc0 = new Locale(0);
        loc0.setName("Dorm Room");
        loc0.setDesc("This is where the player lives. ");
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

       
        
         // player items 
        playerItem = new Item[9];
        playerItem[0] = item0;  // Map
        playerItem[1] = item1; //Library
        playerItem[2] = item2; //Knife
        playerItem[3] = item3; //Spoon
        playerItem[4] = item4; //Food
        playerItem[5] = item5;  // Jacket
        playerItem[6] = item6;  //Book
        playerItem[7] = item7;  //Burgers
        playerItem[8] = item8;  //Fishes



        
        
        
//location set up
        li0= new ListLoc(loc0);
        li1= new ListLoc(loc1);
        li2= new ListLoc(loc2);
        li3= new ListLoc(loc3);
        li4= new ListLoc(loc4);
        li5= new ListLoc(loc5);
        li6= new ListLoc(loc6);
        li7= new ListLoc(loc7);
        li8= new ListLoc(loc8);
      
        //set up the links for navigation
        
        li0.setSouth(li3);
        li0.setEast(li1);
        
        
        li1.setSouth(li4);
        li1.setWest(li0);
        li1.setEast(li2);
        
        li2.setSouth(li5);
        li2.setWest(li1);
        
        li3.setSouth(li6);
        li3.setNorth(li0);
        li3.setEast(li4);
        
        li4.setNorth(li1);
        li4.setSouth(li7);
        li4.setWest(li3);
        li4.setEast(li5);
        
        li5.setNorth(li2);
        li5.setSouth(li8);
        li5.setWest(li4);
        
        li6.setNorth(li3);
        li6.setEast(li7);
        
        li7.setNorth(li4);
        li7.setWest(li6);
        li7.setEast(li8);
        
        
        li8.setNorth(li5);
        li8.setEast(li7);
        
   
      

        if (currentLocation==null) currentLocation = li0; 

        final String fileName = "magicitems.txt";
        readMagicItemsFromFile(fileName, lm1);

        // Display the list of items.
        System.out.println(lm1.toString());
        
        
    }
    
 // Private
    //
    private static ShopItem sequentialSearch(ShopList lm,
                                             String target) {
    	final String fileName = "magicitems.txt";
        readMagicItemsFromFile(fileName, lm1);

        ShopItem retVal = null;
        System.out.println("Searching for " + target + ".");
        int counter = 0;
        ShopItem currentItem = new ShopItem();
        currentItem = lm.getHead();
        boolean isFound = false;
        while ( (!isFound) && (currentItem != null) ) {
            counter = counter +1;
            if (currentItem.getName().equalsIgnoreCase(target)) {
                // We found it!
                isFound = true;
                retVal = currentItem;
                if (token>currentItem.getCost()) {
                token = token-currentItem.getCost();
                System.out.println("You have Purchased " + currentItem + "from the Magic Shop." + "You currently have" + token);	
                }
            } else {
                // Keep looking.
                currentItem = currentItem.getNext();
            
            }
        }
        if (isFound) {
            System.out.println("Found " + target + " after " + counter + " comparisons.");
            return  currentItem;
        } else {
            System.out.println("Could not find " + target + " in " + counter + " comparisons.");
        }

        return retVal;
    }


    private static void readMagicItemsFromFile(String fileName,
                                               ShopList lm) {
        File myFile = new File(fileName);
        try {
            Scanner input = new Scanner(myFile);
            while (input.hasNext()) {
                // Read a line from the file.
                String itemName = input.nextLine();

                // Construct a new list item and set its attributes.
                ShopItem fileItem = new ShopItem();
                fileItem.setName(itemName);
                fileItem.setCost(Math.random() * 100);
                fileItem.setNext(null); // Still redundant. Still safe.

                // Add the newly constructed item to the list.
                lm.add(fileItem);
            }
            // Close the file.
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. " + ex.toString());
        }
    }




      


    private static void updateDisplay() {
        System.out.println(currentLocation.getThisLocale().getName());
        System.out.println(currentLocation.getThisLocale().getDesc());
        System.out.println(currentLocation.getThisLocale().getNext());

       if (!playerItem [currentLocation.getThisLocale().getId()].getHasTaken()) {
        System.out.println(playerItem[currentLocation.getThisLocale().getId()].getName());
        System.out.println(playerItem[currentLocation.getThisLocale().getId()].getDesc());



      }
    }

    private static void getCommand() {
        System.out.print("[" + moves + " moves, score " + score + "," + " achievement " + achievement + " Token: " + token + "]");
        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }

    private static void navigate() {
        final ListLoc INVALID = null;
        int dir = -1;  

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

        if (dir > -1) {   // This means  set.

        	ListLoc newLocation = null;
        	if (dir==0) newLocation = currentLocation.getNorth();
        	if (dir==1) newLocation = currentLocation.getSouth();
        	if (dir==3) newLocation = currentLocation.getEast();
        	if (dir==2) newLocation = currentLocation.getWest();
            if (newLocation == INVALID) {
                System.out.println("You can't go that way.");
            } else {
                currentLocation = newLocation;
                moves = moves + 1;
                if (!currentLocation.getThisLocale().getHasVisited())
                {
                    score = score + 5;
                    token = token + 20;
                     currentLocation.getThisLocale().setHasVisited(true);
                }

                achievement = (float)score / moves;

             // Ask player for an item.
                if (currentLocation.getThisLocale().getId()==4){
                	
                
                
                Scanner inputReader = new Scanner(System.in);
                System.out.print("What item would you like? ");
                String targetItem = new String();
                targetItem = inputReader.nextLine();
                
                //TODO: You take what you you read from the console (targetItem)
                //and create an Item Object which then you can add to your inventory.
                
                System.out.println();

                ShopItem li = new ShopItem();
                li = sequentialSearch(lm1, targetItem);
                if (li != null) {
                	int id = inventory.length;
                	Item[] temp = new Item[id + 1];   
                	
                	for(int i = 0; i < inventory.length; i++){
                		temp[i] = inventory[i];
                	}
                	
                	Item newItem = new Item(id, targetItem);
                	temp[id] = newItem;
                	
                	inventory = temp;
                	
                	
                    System.out.println(li.toString());
                }
            }
            }   
                
            }
        }
    

    private static void help() {
        System.out.println("The commands are as follows:");
        System.out.println("   n/north");
        System.out.println("   s/south");
        System.out.println("   e/east");
        System.out.println("   w/west");
        System.out.println("   m/map");
        System.out.println("   i/inventory");
        System.out.println("   t/take");
        System.out.println("   q/quit");
    }

    private static void map() {


        int currentLocale = 0;
		if (playerItem[currentLocale].getHasTaken()) {

        System.out.println("[" + li0.getThisLocale().getName() + " ]-----[" + li1.getThisLocale().getName() + "]-------[" + li2.getThisLocale().getName() + "]    ");
        System.out.println("      |              |              |     ");
        System.out.println("      |              |              |        ");
        System.out.println("[" + li3.getThisLocale().getName() + "]-----[" + li4.getThisLocale().getName() + "]-----[" + li5.getThisLocale().getName() + "] ");
        System.out.println("      |              |              |               ");
        System.out.println("      |              |              |        ");
        System.out.println("[" + li6.getThisLocale().getName() + " ]-----[" + li7.getThisLocale().getName() + "]---------[" + li8.getThisLocale().getName() + "]       ");

            playerItem[currentLocale].setHasTaken(true);
    }
  }
      


    private static void take() {

	  	Item itemToTake = playerItem[currentLocation.getThisLocale().getId()]; 
              if (!playerItem[currentLocation.getThisLocale().getId()].getHasTaken()) {

               System.out.println( itemToTake.getName());

                inventory[takenItem] =itemToTake;
                takenItem += 1;

                  playerItem[currentLocation.getThisLocale().getId()].setHasTaken(true);
            }
        }

   private static void invt() {
	   int inventorySize = inventory.length;
            System.out.println("You have " + inventorySize + "in your inventory" );
            System.out.println("Your inventory includes: ");
            
       for (int i = 0; i <inventorySize; i++) {
           System.out.print(i + ":" + inventory[i]);
       }

       System.out.println(" ");
   }
   


    private static void quit() {
        stillPlaying = false;
    }
}
