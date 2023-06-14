import java.util.Iterator;
import java.util.Scanner;

/**
 * Represents the warehouse application. Including the dialog with the user.
 *
 * @author Candidate: 10043
 * @version 2022-12-13
 */

public class WarehouseApp
{
    private static final String VERSION = "WarehouseApp Version 3.13";

    private static final int PRINT_ALL_ITEMS = 1;
    private static final int FIND_ITEM_BY_ITEM_NUMBER = 2;
    private static final int ADD_ITEM = 3;
    private static final int REMOVE_ITEM_BY_ITEM_NUMBER = 4;
    private static final int CHANGE_ITEM = 5;
    private static final int EXIT = 6;

    private static final int PROVIDE_ADD_ITEM_INFORMATION = 1;
    private static final int GO_BACK_TO_MAIN_MENU_FROM_ADD_ITEM_WARNING = 2;

    private static final int CHANGE_ITEM_DESCRIPTION = 1;
    private static final int CHANGE_ITEM_PRICE = 2;
    private static final int CHANGE_ITEM_BRAND_NAME = 3;
    private static final int CHANGE_ITEM_WEIGHT = 4;
    private static final int CHANGE_ITEM_LENGTH = 5;
    private static final int CHANGE_ITEM_HEIGHT = 6;
    private static final int CHANGE_ITEM_WIDTH = 7;
    private static final int CHANGE_ITEM_COLOUR = 8;
    private static final int CHANGE_ITEM_QUANTITY = 9;
    private static final int CHANGE_ITEM_CATEGORY = 10;
    private static final int GO_BACK_TO_MAIN_MENU_FROM_CHANGE_ITEM_MENU = 11;
    
    private static final int CHANGE_BASE_PRICE = 1;
    private static final int CHANGE_DISCOUNT = 2;
    private static final int GO_BACK_TO_CHANGE_ITEN_MENU_FROM_CHANGE_PRICE_MENU = 3;

    private static final int INCREASE_ITEM_STOCK = 1;
    private static final int DECREASE_ITEM_STOCK = 2;
    private static final int GO_BACK_TO_CHANGE_ITEM_MENU_FROM_CHANGE_ITEM_STOCK_MENU = 3;

    private static final String MAIN_MENU_NAME = "Main Menu";
    private static final String CHANGE_ITEM_MENU_NAME = "Change Item Menu";
    private static final String CHANGE_ITEM_PRICE_MENU_NAME = "Change Item Price Menu";
    private static final String CHANGE_ITEM_QUANTITY_MENU_NAME = "Change Item Quantity Menu";
    private static final String CHANGE_ITEM_CATEGORY_MENU_NAME = "Change Item Category Menu";

    private ItemRegister itemRegister;

    /**
     * Constructs a sample of WarehouseApp.
     */
    public WarehouseApp()
    {
        this.itemRegister = new ItemRegister();
    }

    /**
     * Seperate method to fill the item register with default items.
     */
    public void fill()
    {
        this.fillWarehouseWithDefaultItems();
    }

    /**
     * A helper method used for testing the class.
     * This method fills the item register list with pre-made items.
     * The method should be removed when the class if fully implemented.
     */
    private void fillWarehouseWithDefaultItems()
    {
        this.itemRegister.addItem(new Item("H1-NT", "Red tile for the floor", 
                                            30, 0, "Saga", 40, 50, 60, 70, "Red", 
                                            120, CategoryAlternatives.OTHER));
        this.itemRegister.addItem(new Item("R2-D2",  "Blue tile for the floor",
                                            40, 0, "Astromech", 50, 60, 70, 80, "Blue", 
                                            240, CategoryAlternatives.SKIRTING_BOARDS));
        this.itemRegister.addItem(new Item("C-3PO", "Yellow tile for the floor",
                                            50, 0, "Protocol", 60, 70, 80, 90, "Yellow", 
                                            480, CategoryAlternatives.DOORS));
        this.itemRegister.addItem(new Item("BB-8", "Orange tile for the floor",
                                            60, 0, "Astromech", 70, 80, 90, 100, "Orange", 
                                            960, CategoryAlternatives.FLOOR_LAMINATES));
        this.itemRegister.addItem(new Item("BB-9E", "Black tile for the floor",
                                            70, 0, "Astromech", 80, 90, 100, 110, "Black", 
                                            1920, CategoryAlternatives.WINDOWS));
        this.itemRegister.addItem(new Item("K-2SO", "Black tile for the floor",
                                            80, 0, "Security", 90, 100, 110, 120, "Black", 
                                            3840, CategoryAlternatives.LUMBER));
    }

    /**
     * Asks the user for a text input. If the input is blank, 
     * the request for a new input is sent again.
     *
     * @param userOutput the output of the scanner.
     * @return the input the user gave the scanner.
     */
    private String setInputString(String userOutput)
    {
        Scanner userInput = new Scanner(System.in);
        String input = "";
        while (input.isBlank())
        {
            System.out.println(userOutput);
            input = userInput.nextLine();
        }
        return input;
    }

    /**
     * Asks the user for a number input. If the input is not of a 
     * double value, an error message is returned, and the user 
     * may enter a new value.
     *
     * @param userOutput the output of the scanner.
     * @return the input the user gave the scanner.
     */
    private double setInputDouble(String userOutput)
    {
        Scanner userInput = new Scanner(System.in);
        Double input = -1.0;
        while (input <= 0)
        {
            System.out.println(userOutput);
            if (userInput.hasNextDouble())
            {
                input = userInput.nextDouble();
            } else
            {
                System.out.println("\nPlease enter a positive number.\n");
            }
            userInput.nextLine();
        }
        return input;
    }

    /**
     * Asks the user for a whole number input. If the input is not of a 
     * integer value, an error message is returned, and the user may 
     * enter a new value.
     *
     * @param userOutput the output of the scanner.
     * @return the input the user gave the scanner.
     */
    private int setInputInt(String userOutput)
    {
        Scanner userInput = new Scanner(System.in);
        int input = -1;
        while (input < 0)
        { 
            System.out.println(userOutput);
            if (userInput.hasNextInt())
            {
                input = userInput.nextInt();  
            } else
            {
                System.out.println("\nPlease enter a whole, positive number.\n");
            }
            userInput.nextLine();
        }
        return input;
    }

    /**
     * "Starts" the application by presenting a menu for the user.
     * The user can then choose from the menu, and for each choice
     * the user takes, a task will be performed, and the menu will
     * display again.
     *
     * <p>When the user wants to exit the application, the user may
     * choose the "Exit"-alternative in the menu, and the application
     * will be terminated.
     * </p>
     */
    public void start()
    {
        boolean finished = false;
        while (!finished)
        {   
            int selectedMenu = this.showMenu();
            switch (selectedMenu)
            {
                case PRINT_ALL_ITEMS:
                    this.printAllItems();
                    break;
                    
                case FIND_ITEM_BY_ITEM_NUMBER:
                    this.findItemByItemNumber();
                    break;
                    
                case ADD_ITEM:
                    this.addItem();
                    break;
                    
                case REMOVE_ITEM_BY_ITEM_NUMBER:
                    this.removeItemByItemNumber();
                    break;
                    
                case CHANGE_ITEM:
                    System.out.println("\nSending you to 'Change Item Menu'...");
                    this.changeItem();
                    break;
                    
                case EXIT:
                    System.out.println("\nThank you for using the WarehouseApp!\n\n");
                    finished = true;
                    break;
                    
                default:
                    System.out.println("\nSorry, you seem to have entered something different "
                                        + "than a whole number between 1 and 6."
                                        + "\nPlease try again.");
            }
        }
    }

    /**
     * Displays the menu for the user, followed by awaiting the menu choice
     * choosen by the user, which then is returned.
     *
     * @return the menu choice of the user as en integer. 
     *         If the user has enterered an invalid choice 0 is returned.
     */
    private int showMenu()
    {
        int selectedMenu = setInputInt("\n\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "\nWelcome to the " + VERSION + "!\n"
                                        + "\n*** " + MAIN_MENU_NAME 
                                        + " ***\n"
                                        + "\n" + PRINT_ALL_ITEMS 
                                        + ") List all items"
                                        + "\n" + FIND_ITEM_BY_ITEM_NUMBER 
                                        + ") Find item by item number"
                                        + "\n" + ADD_ITEM 
                                        + ") Add item"
                                        + "\n" + REMOVE_ITEM_BY_ITEM_NUMBER 
                                        + ") Remove item by item number"
                                        + "\n" + CHANGE_ITEM 
                                        + ") Change item"
                                        + "\n" + EXIT 
                                        + ") Exit the application"
                                        + "\n\nPlease enter a whole number between 1 and 6 "
                                        + "represented by the menu above."
                                        + "\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------");
    
        return selectedMenu;    
    }
    
    /**
     * Prints the item's item number for every item in the warehouse.
     */
    private void showItemNumberMenu()
    {
        Iterator<Item> it = this.itemRegister.getIterator();
        System.out.print("\n\nList of avaible items: \n");
        while (it.hasNext())
        {
            Item item = it.next();
            System.out.println("Item: " + item.getItemNumber());
        }
    }

    /**
     * Prints all item details of one item.
     *
     * @param item the item that will have all its details printed.
     */
    private void printItemDetails(Item item)
    {   
        System.out.println("\n----------------------------------------"
                            + "\nItem Number: " + item.getItemNumber()
                            + "\nItem Description: " + item.getItemDescription()
                            + "\nItem Base Price: " + item.getItemPriceWithoutDiscount() 
                            + " " + Measurements.PRICE_MEASUREMENT.getAbbreviatedMeasurements()
                            + "\nItem Discount: " + item.getItemDiscount() + "%"
                            + "\nItem Price With Discount: " + item.getItemPriceWithDiscount() 
                            + " " +  Measurements.PRICE_MEASUREMENT.getAbbreviatedMeasurements()
                            + "\nItem Brand Name: " + item.getItemBrandName()
                            + "\nItem Weight: " + item.getItemWeight() 
                            + " " + Measurements.WEIGHT_MEASUREMENT.getAbbreviatedMeasurements()
                            + "\nItem Length: " + item.getItemLength() 
                            + " " + Measurements.LENGTH_MEMASUREMENT.getAbbreviatedMeasurements()
                            + "\nItem Height: " + item.getItemHeight() 
                            + " " + Measurements.HEIGHT_MEASUREMENT.getAbbreviatedMeasurements()
                            + "\nItem Width: " + item.getItemWidth() 
                            + " " + Measurements.WIDTH_MEASUREMENT.getAbbreviatedMeasurements()
                            + "\nItem Colour: " + item.getItemColour()
                            + "\nItem Quantity: " + item.getItemQuantity() 
                            + " " + Measurements.QUANTITY_MEASUREMENT.getExpandedMeasurements()
                            + "\nItem Category: " + item.getItemCategory().getCategoryName()
                            + "\n----------------------------------------");
    }
    
    /**
     * Activates the "List All Items" alternative from the menu.
     * 
     * <p>Prints all items to the console window.
     */
    private void printAllItems()
    {   
        Iterator<Item> it = this.itemRegister.getIterator();
        System.out.println("\nList of items in the warehouse:\n");
        while (it.hasNext())
        {
            Item item = it.next();
            this.printItemDetails(item);
        }
        System.out.println("\nPlease scroll upwards to view more items.");
    }

    /**
     * Prints all categories by their id and name.
     *
     * @return the list of categories.
     */
    private String listAllCategories()
    {
        String listedCategories = ("\n" + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.OTHER.getCategoryId()
                                ).getCategoryId() 
                                + ") " + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.OTHER.getCategoryId()
                                ).getCategoryName()
                                + "\n" + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.FLOOR_LAMINATES.getCategoryId()
                                ).getCategoryId() 
                                + ") " + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.FLOOR_LAMINATES.getCategoryId()
                                ).getCategoryName()
                                + "\n" + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.DOORS.getCategoryId()
                                ).getCategoryId() 
                                + ") " + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.DOORS.getCategoryId()
                                ).getCategoryName()
                                + "\n" + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.WINDOWS.getCategoryId()
                                ).getCategoryId() 
                                + ") " + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.WINDOWS.getCategoryId()
                                ).getCategoryName()
                                + "\n" + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.SKIRTING_BOARDS.getCategoryId()
                                ).getCategoryId() 
                                + ") " + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.SKIRTING_BOARDS.getCategoryId()
                                ).getCategoryName()
                                + "\n" + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.LUMBER.getCategoryId()
                                ).getCategoryId() 
                                + ") " + CategoryAlternatives.getCategoryById(
                                CategoryAlternatives.LUMBER.getCategoryId()
                                ).getCategoryName());
        
        return listedCategories;
    }

    /**
     * Activates the "Find item by item number" alternative from the menu.
     * 
     * <p>The user is requestet to provide the item number of the item
     * that is to be found. If the item exist in the register, it is
     * to be presented for the user.
     */
    private void findItemByItemNumber()
    {
        this.showItemNumberMenu();
        String itemNumber = setInputString(
                                            "\nPlease enter the item number "
                                            + "of the item to be found:");
        Item itemToFind = this.itemRegister.getItemByItemNumber(itemNumber);

        if (itemToFind == null)
        {
            System.out.println(
                                "Sorry, could not find an item with the item number: " 
                                + itemNumber + ".");
        } else
        { 
            System.out.println("\n" 
                                + itemToFind.getItemNumber() 
                                + " was succesfully found!"
                                + "\nThe item that has the item number " 
                                + itemToFind.getItemNumber() 
                                + " is:");
            this.printItemDetails(itemToFind);
        }
    }

    /**
     * Activates the "Add item" alternative from the menu.
     *
     * <p>Requests necessary information input from the user to be able
     * to create a new item that is to be added to the warehouse.
     */
    private void addItem()
    {
        boolean finished = false;
        while (!finished)
        {
            int selectedMenu = this.showAddItemWarning();
            switch (selectedMenu)
            {
                case PROVIDE_ADD_ITEM_INFORMATION:
                    this.provideAddItemInformation();
                    finished = true;
                    System.out.println("\nSending you back to 'Main Menu'...");
                    break;

                case GO_BACK_TO_MAIN_MENU_FROM_ADD_ITEM_WARNING:
                    finished = true;
                    System.out.println("\nSending you back to 'Main Menu'...");
                    break;

                default:
                    System.out.println("\nSorry, you seem to have entered something different " 
                                        + "than a whole number between 1 and 2."
                                        + "\nPlease try again!");
            }
        }
    }

    /**
     * Displays the 'Add Item Warning' message for the user, followed by 
     * awaiting the menu choice choosen by the user, which then is returned.
     *
     * @return The menu choice of the user as en integer. 
     *         If the user has enterered an invalid choice 0 is returned.
     */
    private int showAddItemWarning()
    {
        int selectedMenu = setInputInt("\n\n*** WARNING: ***"
                                        + "\n\nOnce you start the 'Add item' alternative, " 
                                        + "you can not stop until a valid "
                                        + "item is added to the warehouse register."
                                        + "\nAre you sure you want to add an item?"
                                        + "\n\nPlease choose a whole number between 1 and 2, " 
                                        + "represented by the menu bellow to continue: "
                                        + "\n" + PROVIDE_ADD_ITEM_INFORMATION 
                                        + ") Yes, add new item to the register"
                                        + "\n" + GO_BACK_TO_MAIN_MENU_FROM_ADD_ITEM_WARNING 
                                        + ") No, go back to 'Main Menu'");

        return selectedMenu;
    }

    /**
     * Adds a new item to the item register.
     *
     * <p>The user is required to provide the nessecary information 
     * input from the user to be ableto create a new item that is 
     * to be added to the warehouse.
     */
    private void provideAddItemInformation()
    {
        System.out.println("\n\nPlease provide the following information about the item to add: ");
        
        String itemNumber = setInputString("\nPlease enter the item number for the new item: ");

        boolean itemNumberInputNotValid = true;
        while (itemNumberInputNotValid)
        {
            if (itemRegister.getItemByItemNumber(itemNumber) != null)
            {
                itemNumber = setInputString("\n" + itemNumber + " already exist."
                                            + "\nPlease try again, with a new item number!"
                                            + "\n\nPlease enter a new item" 
                                            + " number for the new item: ");
            } else
            {
                itemNumberInputNotValid = false;
            }
        }
        
        String itemDescription = setInputString(
                                            "\nPlease enter a description to " + itemNumber + ": ");
        
        int itemPrice = setInputInt(
                                "\nPlease enter the price, in " 
                                + Measurements.PRICE_MEASUREMENT
                                .getExpandedMeasurements() 
                                + " and as a whole number, to " + itemNumber + ": ");

        int itemDiscount = setInputInt(
                                        "\nPlease enter a whole number "
                                        + "between 0 and 100, representing "
                                        + "the dicount percentage, to " + itemNumber + ":");
        
        boolean itemDiscountInputNotValid = true;
        while (itemDiscountInputNotValid)
        {
            if (itemDiscount < 0 || itemDiscount > 100)
            {
                itemDiscount = setInputInt(
                    "\nSorry, you seem to have entered something different " 
                    + "than a whole number between 0 and 100."
                    + "\nPlease try again with a new input!"
                    + "\n\nPlease enter a new whole number "
                    + "between 0 and 100, representing "
                    + "the dicount percentage, to " + itemNumber + ":");
            } else
            {
                itemDiscountInputNotValid = false;
            }
        }
        
        String itemBrandName = setInputString(
                                            "\nPlease enter the brand name, to " 
                                            + itemNumber + ": ");
        
        double itemWeight = setInputDouble(
                                        "\nPlease enter the weight, in " 
                                        + Measurements.WEIGHT_MEASUREMENT
                                        .getExpandedMeasurements() 
                                        + " and as a number, to " + itemNumber + ": ");
        
        double itemLength = setInputDouble(
                                        "\nPlease enter the length, in " 
                                        + Measurements.LENGTH_MEMASUREMENT
                                        .getExpandedMeasurements() 
                                        + " and as a number, to " + itemNumber + ": ");
        
        double itemHeight = setInputDouble(
                                        "\nPlease enter the height, in " 
                                        + Measurements.HEIGHT_MEASUREMENT
                                        .getExpandedMeasurements() 
                                        + " and as a number, to " + itemNumber + ": ");
        
        double itemWidth = setInputDouble(
                                        "\nPlease enter the width, in " 
                                        + Measurements.WIDTH_MEASUREMENT
                                        .getExpandedMeasurements() 
                                        + " and as a number, to " + itemNumber + ": ");
        
        String itemColour = setInputString(
                                        "\nPlease enter the colour to " 
                                        + itemNumber + ": ");
        
        int itemQuantity = setInputInt(
                                    "\nPlease enter the quantity, as a whole number, you have of " 
                                    + itemNumber + ": ");

        CategoryAlternatives[] categories = CategoryAlternatives.values();
        int itemCategory = setInputInt(
                                    "\nPlease choose a number bewteen 1 and " 
                                    + categories.length 
                                    + " out of one of the listed,  "
                                    + "as a whole number, to be the category of " 
                                    + itemNumber + ": \n"
                                    + this.listAllCategories());

        boolean categoryInputNotValid = true;
        while (categoryInputNotValid)
        {
            
            if (itemCategory < 1 || itemCategory > categories.length)
            {
                itemCategory = setInputInt(
                                        "\nSorry, you seem to have entered something different " 
                                        + "than a whole number between 1 and "  
                                        + categories.length + "."
                                        + "\nPlease try again with a new input!"
                                        + "\n\nPlease enter a new category input for " 
                                        + itemNumber + ":\n"
                                        + this.listAllCategories());
            } else
            {
                categoryInputNotValid = false;
            }
        }

        Item item = new Item(itemNumber, itemDescription, itemPrice, itemDiscount, itemBrandName, 
                            itemWeight, itemLength, itemHeight, itemWidth, itemColour, 
                            itemQuantity, CategoryAlternatives.getCategoryById(itemCategory));
        this.itemRegister.addItem(item);

        System.out.println("\nThe new item to be added to the warehouse is: ");
        this.printItemDetails(item);
    }

    /**
     * Activates the "Remove item by item number" alternative from the menu.
     * 
     * <p>The user is requestet to provide the item number of the item
     * that is to be removed. If the item exist in the register, it is
     * to be removed from the register.
     */
    private void removeItemByItemNumber()
    {
        this.showItemNumberMenu();
        String itemNumber = setInputString(
                                            "\nPlease enter the item number " 
                                            + "of the item to be removed: ");
        Item itemToDelete = this.itemRegister.getItemByItemNumber(itemNumber);
        
        if (itemToDelete == null)
        {
            System.out.println(
                                "\n\nSorry, could not find an item with the item number: " 
                                + itemNumber + ".");
        } else
        {
            if (this.itemRegister.removeItem(itemToDelete))
            {
                System.out.println(
                                    "\n" + itemToDelete.getItemNumber() 
                                    + " was successfully removed.");
            } else
            {
                System.out.println(
                                    "\n" + itemToDelete.getItemNumber() 
                                    + " could not be removed.");
            }
        }
    }
    
    /**
     * Activates the "Change item" alternative from the menu.
     * 
     * <p>The user requested to choose one item from the item register to edit. If the 
     * item is avaible, the user is requested to decide what to be changed by the item,
     * to then be able to edit the decision the user made. The item will then be changed.
     */
    private void changeItem()
    {
        this.showItemNumberMenu();
        String itemNumber = setInputString(
                                            "\nPlease enter the item number " 
                                            + "of the item to be changed: ");
        this.changeItemMenu(itemNumber);
    }

    /**
     * Displays the 'Change Item Menu' for the user, followed by 
     * awaiting the menu choice choosen by the user, which then is returned.
     *
     * @param itemNumber the item number of the item to be changed.
     * @return The menu choice of the user as en integer. 
     *         If the user has enterered an invalid choice 0 is returned.
     */
    private int showChangeItemMenu(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        int selectedMenu = setInputInt("\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "\n*** " + CHANGE_ITEM_MENU_NAME + " ***\n"
                                        + "\n" + CHANGE_ITEM_DESCRIPTION 
                                        + ")  Change item description"
                                        + "\n" + CHANGE_ITEM_PRICE 
                                        + ")  Change item price"
                                        + "\n" + CHANGE_ITEM_BRAND_NAME 
                                        + ")  Change item brand name"
                                        + "\n" + CHANGE_ITEM_WEIGHT 
                                        + ")  Change item weight"
                                        + "\n" + CHANGE_ITEM_LENGTH 
                                        + ")  Change item length"
                                        + "\n" + CHANGE_ITEM_HEIGHT 
                                        + ")  Change item height"
                                        + "\n" + CHANGE_ITEM_WIDTH 
                                        + ")  Change item width"
                                        + "\n" + CHANGE_ITEM_COLOUR 
                                        + ")  Change item colour"
                                        + "\n" + CHANGE_ITEM_QUANTITY 
                                        + ")  Change item quantity"
                                        + "\n" + CHANGE_ITEM_CATEGORY 
                                        + ") Change item category" + "\n" 
                                        + GO_BACK_TO_MAIN_MENU_FROM_CHANGE_ITEM_MENU 
                                        + ") Go back to 'Main Menu'"
                                        + "\n\nPlease choose a whole number between 1 and 10 " 
                                        + "represented by the menu above, to change " 
                                        + itemToChange.getItemNumber() + "," + "\nand press " 
                                        + GO_BACK_TO_MAIN_MENU_FROM_CHANGE_ITEM_MENU 
                                        + ", as a whole number, to return to the previous menu."
                                        + "\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------");
        return selectedMenu;
    }

    /**
     * Activates the 'Change Item' alternative from the 'Main Menu'.
     *
     * @param itemNumber the item number of the item to be changed.
     */
    private void changeItemMenu(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        if (itemToChange == null)
        {
            System.out.println(
                                "\n\nSorry, could not find an item "
                                + "with the item number: " 
                                + itemNumber + ".");
        } else
        {
            boolean finished = false;
            while (!finished)
            {
                this.printItemDetails(itemToChange);
                int selectedMenu = this.showChangeItemMenu(itemNumber);
                switch (selectedMenu)
                {
                    case CHANGE_ITEM_DESCRIPTION:
                        this.changeItemDescription(itemNumber);
                        break;

                    case CHANGE_ITEM_PRICE:
                        System.out.println("\nSending you to 'Change Item Price Menu'...");
                        this.changeItemPrice(itemNumber);
                        break;

                    case CHANGE_ITEM_BRAND_NAME:
                        this.changeItemBrandName(itemNumber);
                        break;

                    case CHANGE_ITEM_WEIGHT:
                        this.changeItemWeight(itemNumber);
                        break;

                    case CHANGE_ITEM_LENGTH:
                        this.changeItemLength(itemNumber);
                        break;

                    case CHANGE_ITEM_HEIGHT:
                        this.changeItemHeight(itemNumber);
                        break;

                    case CHANGE_ITEM_WIDTH:
                        this.changeItemWidth(itemNumber);
                        break;

                    case CHANGE_ITEM_COLOUR:
                        this.changeItemColour(itemNumber);
                        break;

                    case CHANGE_ITEM_QUANTITY:
                        System.out.println("\nSending you to 'Change Item Quantity Menu'...");
                        this.changeItemQuantity(itemNumber);
                        break;

                    case CHANGE_ITEM_CATEGORY:
                        System.out.println("\nSending you to 'Change Item Category Menu'...");
                        this.changeItemCategory(itemNumber);
                        break;

                    case GO_BACK_TO_MAIN_MENU_FROM_CHANGE_ITEM_MENU:
                        finished = true;
                        System.out.println("\nSending you back to 'Main Menu'...");
                        break;

                    default:
                        System.out.println(
                                            "\nSorry, you seem to have entered something " 
                                            + " different than a whole number between 1 and 11."
                                            + "\nPlease try again!");
                }
            }             
        }
    }

    /**
     * Changed the description of an item as a text.
     *
     * @param itemNumber the item number of the item to have its description changed.
     */
    private void changeItemDescription(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        String newItemDescription = setInputString("\n\nPlease enter the new description for " 
                                                    + itemToChange.getItemNumber() + ":");

        if (newItemDescription.equals(itemToChange.getItemDescription()))
        {
            System.out.println("\n" + newItemDescription 
                                + " is already the description of this item!");
        } else
        {
            itemToChange.setItemDescription(newItemDescription);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s description was changed to:\n" 
                                + itemToChange.getItemDescription()); 
        }
    }

    /**
     * Displays the 'Change Item Price Menu' for the user, followed by 
     * awaiting the menu choice choosen by the user, which then is returned.
     *
     * @return the menu choice of the user as en integer. 
     *         If the user has enterered an invalid choice 0 is returned.
     */
    private int showChangeItemPriceMenu(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        int selectedMenu = setInputInt("\n\n------------------------------" 
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "\n*** " + CHANGE_ITEM_PRICE_MENU_NAME + " ***\n"
                                        + "\n" + CHANGE_BASE_PRICE + ") Change base price"
                                        + "\n" + CHANGE_DISCOUNT + ") Change discount"
                                        + "\n" + GO_BACK_TO_CHANGE_ITEN_MENU_FROM_CHANGE_PRICE_MENU 
                                        + ") Go back to 'Change Item Menu'"
                                        + "\n\nPlease choose a whole number between 1 and 2 " 
                                        + "represented by the menu above, to change " 
                                        + itemToChange.getItemNumber() + ","
                                        + "\nand press " 
                                        + GO_BACK_TO_CHANGE_ITEN_MENU_FROM_CHANGE_PRICE_MENU 
                                        + ", as a whole number, to return to the previous menu."
                                        + "\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------");

        return selectedMenu;
    }

    /**
     * Activates the 'Change Item Price' alternative from the 'Change Item Menu'.
     *
     * @param itemNumber the item number of the item to have its price changed.
     */
    private void changeItemPrice(String itemNumber)
    {
        boolean finished = false;
        while (!finished)
        {
            int selectedMenu = showChangeItemPriceMenu(itemNumber); 
            switch (selectedMenu)
            {
                case CHANGE_BASE_PRICE:
                    this.changeBasePrice(itemNumber);
                    break;

                case CHANGE_DISCOUNT:
                    this.changeDiscount(itemNumber);
                    break;

                case GO_BACK_TO_CHANGE_ITEN_MENU_FROM_CHANGE_PRICE_MENU:
                    finished = true;
                    System.out.println("\nSending you back to 'Change Item Menu'...");
                    break;

                default:
                    System.out.println("\nSorry, you seem to have entered something different " 
                                        + "than a whole number between 1 and 3."
                                        + "\nPlease try again!");
            }
        }
    }

    /**
     * changes the base price of an item as a text.
     *
     * @param itemNumber the item number of the item to have its base price changed.
     */
    private void changeBasePrice(String itemNumber)
    {   
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        int newItemBasePrice = setInputInt("please enter the new price for " 
                                            + itemToChange.getItemNumber() + ", in " 
                                            + Measurements.PRICE_MEASUREMENT
                                            .getExpandedMeasurements() 
                                            + " and as a whole, positive number:");

        if (newItemBasePrice == itemToChange.getItemPriceWithDiscount())
        {
            System.out.println("\n" + newItemBasePrice + " " 
                                + Measurements.PRICE_MEASUREMENT
                                .getAbbreviatedMeasurements() 
                                + " is already the price of this item!");   
        } else
        {
            itemToChange.setItemPrice(newItemBasePrice);
            System.out.println("\n\n" + itemToChange.getItemNumber() 
                                + "'s base price was changed to : " 
                                + itemToChange.getItemPriceWithoutDiscount() + " " 
                                + Measurements.PRICE_MEASUREMENT.getAbbreviatedMeasurements()
                                + "\n" + itemToChange.getItemNumber() 
                                + "'s new price is now:\n" 
                                + itemToChange.getItemPriceWithDiscount() 
                                + " " + Measurements.PRICE_MEASUREMENT
                                .getAbbreviatedMeasurements()); 
        }
    } 

    /**
     * Changed the discount of an item as a whole, positive number between 
     * 0 and 100 (as discount percentage). If invalid input is entered, 
     * an error message is returned and the user may try again.
     *
     * @param itemNumber the item number of the item to be changed.
     */
    private void changeDiscount(String itemNumber)
    {   
        Item itemToChange = this.itemRegister
                            .getItemByItemNumber(itemNumber);
        int newItemDiscount = setInputInt("Please enter the new discount for " 
                                            + itemToChange.getItemNumber() 
                                            + " as a whole, positive number percantage:");

        if (newItemDiscount < 0 || newItemDiscount > 100)
        {
            System.out.println("\nSorry, " + newItemDiscount 
                                + "% is an invalid discount!"
                                + "\nPlease try again.");
        } else
        {
            if (newItemDiscount == itemToChange.getItemDiscount())
            {
                System.out.println("\n" + newItemDiscount 
                                    + "% is already the discount of this item!");
            } else
            {
                itemToChange.setItemDiscount(newItemDiscount);
                System.out.println("\n\n" + itemToChange.getItemNumber() 
                                    + "'s discount was changed to: " 
                                    + newItemDiscount + "%"
                                    + "\n" + itemToChange.getItemNumber() 
                                    + "'s new price is now:\n" 
                                    + itemToChange.getItemPriceWithDiscount() 
                                    + " " + Measurements.PRICE_MEASUREMENT
                                    .getAbbreviatedMeasurements());
            }
        }
    }

    /**
     * Changed the brand name of an item as a text.
     *
     * @param itemNumber the item number of the item to have its brand name changed.
     */
    private void changeItemBrandName(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        String newItemBrandName = setInputString("\n\nPlease enter the new brand name for " 
                                                    + itemToChange.getItemNumber() + ":");

        if (newItemBrandName.equals(itemToChange.getItemBrandName()))
        {
            System.out.println("\n" + newItemBrandName 
                                + " is already the brand name of this item!");
        } else
        {
            itemToChange.setItemBrandName(newItemBrandName);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s brand name was changed to:\n" 
                                + itemToChange.getItemBrandName()); 
        }
    }

    /**
     * Changes the weight of an item as a number.
     *
     * @param itemNumber the item number of the item to have its weight changed.
     */
    private void changeItemWeight(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        double newItemWeight = setInputDouble("\n\nPlease enter the new weight for " 
                                                + itemToChange.getItemNumber() + ", in " 
                                                + Measurements.WEIGHT_MEASUREMENT
                                                .getExpandedMeasurements() 
                                                + " and as a positive number:");

        if (newItemWeight == itemToChange.getItemWeight())
        {
            System.out.println("\nSorry, but" + newItemWeight + " " 
                                + Measurements.WEIGHT_MEASUREMENT
                                .getAbbreviatedMeasurements() 
                                + " is already the weight of this item!");
        } else
        {
            itemToChange.setItemWeight(newItemWeight);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s weight was changed to:\n" 
                                + itemToChange.getItemWeight() + " " 
                                + Measurements.WEIGHT_MEASUREMENT
                                .getAbbreviatedMeasurements());
        }
    }

    /**
     * Changes the length of an item as a number.
     *
     * @param itemNumber the item number of the item to have its length changed.
     */
    private void changeItemLength(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        double newItemLength = setInputDouble("\n\nPlease enter the new length for " 
                                                + itemToChange.getItemNumber() + ", in " 
                                                + Measurements.LENGTH_MEMASUREMENT
                                                .getExpandedMeasurements() 
                                                + " and as a positive number:");

        if (newItemLength == itemToChange.getItemLength())
        {
            System.out.println("\nSorry, but" + newItemLength + " " 
                                + Measurements.LENGTH_MEMASUREMENT
                                .getAbbreviatedMeasurements() 
                                + " is already the length of this item!");
        } else
        {
            itemToChange.setItemLength(newItemLength);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s length was changed to:\n" 
                                + itemToChange.getItemLength() + " " 
                                + Measurements.LENGTH_MEMASUREMENT
                                .getAbbreviatedMeasurements());
        } 
    }

    /**
     * Changes the heght of an item as a number.
     *
     * @param itemNumber the item number of the item to have its height changed.
     */
    private void changeItemHeight(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        double newItemHeight = setInputDouble("\n\nPlease enter the new height for " 
                                                + itemToChange.getItemNumber() 
                                                +  ", in " + Measurements.HEIGHT_MEASUREMENT
                                                .getExpandedMeasurements() 
                                                + " and as a positive number:");

        if (newItemHeight == itemToChange.getItemHeight())
        {
            System.out.println("\nSorry, but" + newItemHeight + " " 
                                + Measurements.HEIGHT_MEASUREMENT
                                .getAbbreviatedMeasurements() 
                                + " is already the height of this item!");
        } else
        {
            itemToChange.setItemHeight(newItemHeight);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s height was changed to:\n"
                                + itemToChange.getItemHeight() + " " 
                                + Measurements.HEIGHT_MEASUREMENT
                                .getAbbreviatedMeasurements()); 
        }
    }

    /**
     * Changes the width of the item as a number.
     *
     * @param itemNumber the item number of the item to have its width changed.
     */
    private void changeItemWidth(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        double newItemWidth = setInputDouble("\n\nPlease enter the new width for " 
                                            + itemToChange.getItemNumber() +  ", in " 
                                            + Measurements.WIDTH_MEASUREMENT
                                            .getExpandedMeasurements() 
                                            + " and as a positive number:");

        if (newItemWidth == itemToChange.getItemWidth())
        {
            System.out.println("\nSorry, but" + newItemWidth + " " 
                                + Measurements.WIDTH_MEASUREMENT
                                .getAbbreviatedMeasurements() 
                                + " is already the width of this item!");
        } else
        {
            itemToChange.setItemWidth(newItemWidth);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s width was changed to:\n" 
                                + itemToChange.getItemWidth() + " " 
                                + Measurements.WIDTH_MEASUREMENT
                                .getAbbreviatedMeasurements());  
        }
    }

    /**
     * Changes the coolour of the item as a text.
     *
     * @param itemNumber the item number of the item to have its colour changed.
     */
    private void changeItemColour(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        String newItemColour = setInputString("\n\nPlease enter the new colour for " 
                                                + itemToChange.getItemNumber() + ":");

        if (newItemColour.equals(itemToChange.getItemColour()))
        {
            System.out.println("\n" + newItemColour 
                                + " is already the colour of this item!");
        } else
        {
            itemToChange.setItemColour(newItemColour);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s colour was changed to:\n" 
                                + itemToChange.getItemColour());
        }
    }
    
    /**
     * Activates the 'Change Item Quantity' alternative from the 'Change Item Menu'.
     *
     * @param itemNumber the item number of the item to have its quantity changed.
     */
    private void changeItemQuantity(String itemNumber)
    {
        boolean finished = false;
        while (!finished)
        {
            int selectedMenu = showChangeItemQuantityMenu(itemNumber);
            switch (selectedMenu)
            {
                case INCREASE_ITEM_STOCK:
                    this.increaseItemStock(itemNumber);
                    break;
                    
                case DECREASE_ITEM_STOCK:
                    this.decreaseItemStock(itemNumber);
                    break;

                case GO_BACK_TO_CHANGE_ITEM_MENU_FROM_CHANGE_ITEM_STOCK_MENU:
                    finished = true;
                    System.out.println("\nSending you back to 'Change Item Menu'...");
                    break;

                default:
                    System.out.println("\nSorry, you seem to have entered something different " 
                                        + "than a whole number between 1 and 3."
                                        + "\nPlease try again!");
            }
        }
    }

    /**
     * Displays the 'Change Item Quantity Menu' for the user, followed by 
     * awaiting the menu choice choosen by the user, which then is returned.
     *
     * @return the menu choice of the user as en integer. 
     *         If the user has enterered an invalid choice 0 is returned.
     */
    private int showChangeItemQuantityMenu(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        int selectedMenu = setInputInt("\n\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "\n*** " + CHANGE_ITEM_QUANTITY_MENU_NAME + " ***\n"
                                        + "\n" + INCREASE_ITEM_STOCK 
                                        + ") Increase item stock"
                                        + "\n" + DECREASE_ITEM_STOCK 
                                        + ") Decrease item stock"
                                        + "\n" 
                                        + GO_BACK_TO_CHANGE_ITEM_MENU_FROM_CHANGE_ITEM_STOCK_MENU 
                                        + ") Go back to 'Change Item Menu'"
                                        + "\n\nPlease choose a whole, " 
                                        + "positive number between 1 and 2 " 
                                        + "represented by the manu above, to change " 
                                        + itemToChange.getItemNumber() + ","
                                        + "\nand press " 
                                        + GO_BACK_TO_CHANGE_ITEM_MENU_FROM_CHANGE_ITEM_STOCK_MENU 
                                        + ", as a whole number, to return to the previous menu."
                                        + "\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------");

        return selectedMenu;
    }

    /**
     * Adds a choosen amount of units of an item to the warehosue as a whole number.
     *
     * @param itemNumber the item number of the item to have its units increased.
     */
    private void increaseItemStock(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        int increaseItemStock = setInputInt("\n\nPlease enter the amount of " 
                                            + "items that were added to the "
                                            + "warehouse as a whole, positive number, " 
                                            + " for " + itemToChange.getItemNumber() + ": ");

        if (increaseItemStock <= 0)
        {
            System.out.println("\nSorry, you can't add " + increaseItemStock 
                                + " " + Measurements.QUANTITY_MEASUREMENT
                                .getExpandedMeasurements() + " to the warehouse."
                                + "\nPlease try again with a larger, positive number!");
        } else
        {
            itemToChange.setItemQuantity(itemToChange.getItemQuantity() + increaseItemStock);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s quantity was updated to:\n" 
                                + itemToChange.getItemQuantity() + " " 
                                + Measurements.QUANTITY_MEASUREMENT
                                .getExpandedMeasurements());
        }
    }

    /**
     * takes a choosen amount of units of an item from the warehouse as a whole number.
     *
     * @param itemNumber the item number of the item to have its units decreased.
     */
    private void decreaseItemStock(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        int decreaseItemStock = setInputInt("\n\nPlease enter the amount of items " 
                                            + "that were removed from " 
                                            + "the warehouse as a whole, positive number, for " 
                                            + itemToChange.getItemNumber() + " : ");

        if (decreaseItemStock > itemToChange.getItemQuantity())
        {
            System.out.println("\nSorry, you can't remove " + decreaseItemStock 
                                + " " + Measurements.QUANTITY_MEASUREMENT
                                .getExpandedMeasurements() + " from the warehouse."
                                + "\nPlease try again with a smaller, positive number!");
        } else
        {
            itemToChange.setItemQuantity(itemToChange.getItemQuantity() - decreaseItemStock);
            System.out.println("\n" + itemToChange.getItemNumber() 
                                + "'s quantity was updated to:\n" 
                                + itemToChange.getItemQuantity() + " " 
                                + Measurements.QUANTITY_MEASUREMENT
                                .getExpandedMeasurements());
        }
    }

    /**
     * Displays the 'Change Item Category Menu' for the user, followed by 
     * awaiting the menu choice choosen by the user, which then is returned.
     *
     * @param itemNumber the item number of the item to have its category changed.
     * @return the menu choice of the user as en integer. 
     *         If the user has enterered an invalid choice, 0 is returned.
     */
    private int showChangeItemCategoryMenu(String itemNumber)
    {
        CategoryAlternatives[] categories = CategoryAlternatives.values();
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        int selectedMenu = setInputInt("\n\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "\n*** " + CHANGE_ITEM_CATEGORY_MENU_NAME + "***\n"
                                        + this.listAllCategories()
                                        + "\n" + (categories.length + 1) 
                                        + ") Go back to 'Change Item Menu'"
                                        + "\n\nPlease enter the new category from the menu above, " 
                                        + "as a whole number between 1 and " + categories.length 
                                        + ", to change " + itemToChange.getItemNumber() + "."
                                        + "\nPress " + (categories.length + 1) 
                                        + ", as a whole number, to return to the previous menu."
                                        + "\n------------------------------"
                                        + "------------------------------"
                                        + "------------------------------"
                                        + "------------------------------");
        return selectedMenu;
    }

    /**
     * Activates the 'Change Category' alternative from the 'Change Item Menu'
     * Changes the category of an item by the category value as a whole number.
     *
     * @param itemNumber the item number of the item to have its category changed.
     */
    private void changeItemCategory(String itemNumber)
    {
        Item itemToChange = this.itemRegister.getItemByItemNumber(itemNumber);
        boolean finished = false;
        while (!finished)
        {
            CategoryAlternatives[] categories = CategoryAlternatives.values();
            int newItemCategory = showChangeItemCategoryMenu(itemNumber);
            if (newItemCategory < 1 || newItemCategory > (categories.length + 1))
            {
                System.out.println("\nSorry, you seem to have entered something different " 
                                    + "than a number between 1 and "  
                                    + (categories.length + 1) + "."
                                    + "\nPlease try again!");
            } else if (newItemCategory == (categories.length + 1))
            {
                System.out.println("\nSending you back to 'Change Item Menu'...");
                finished = true;
            } else
            {
                itemToChange.setItemCategory(CategoryAlternatives.getCategoryById(newItemCategory));
                System.out.println("\n" + itemToChange.getItemNumber() 
                                    + "'s category was set to:\n" 
                                    + CategoryAlternatives.getCategoryById(newItemCategory)
                                    .getCategoryName());
            }
        }
    }
}