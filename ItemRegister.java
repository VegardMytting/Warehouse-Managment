import java.util.HashMap;
import java.util.Iterator;

/**
 * Represents an item register responsible for
 * holding all fitting items, using a {@code HashMap}-class.
 *
 * <p>The Item Register class will have the functions:
 * <ul>
 *  <li>Add an item to the item register</li>
 *  <li>Remove an item from the item register, by their respected item number</li>
 *  <li>Search for an item form the item register, by their respected item number</li>
 * </ul>
 * </p>
 *
 * @author Candidate: 10043
 * @version 2022-12-13
 */
public class ItemRegister
{
    private HashMap<String, Item> listOfItems;

    /**
     * Constructs an empty item register list.
     */
    public ItemRegister()
    {
        this.listOfItems = new HashMap<>();
    }
   
    /**
     * Adds an item to the item register.
     *
     * @param item the item that is to be added to the register.
     */
    public void addItem(Item item)
    {
        this.listOfItems.put(item.getItemNumber(), item);
    }
    
    /**
     * Removes an item from the item register, by the item's item number that is to be removed.
     *
     * <p>
     * If the item exist in the register, the item is removed and {@code true} is returned.
     * If the item does not exist {@code false} is returned.
     * </p>
     *
     * @param item the item that is to be removed from the item regiser.
     * @return {@code true} if the item was successfullt removed. 
     *         If the item does not exist {@code false} is returned.
     */
    public boolean removeItem(Item item)
    {
        return this.listOfItems.values().remove(item);
    }
    
    /**
     * Returns the Iterator to the collection of items.
     *
     * @return the Iterator to the collection of items.
     */
    public Iterator<Item> getIterator()
    {
        return this.listOfItems.values().iterator();
    }
    
    /**
     * Returns the first item found in the item register matching
     * the given itemnumber recieved by the user.
     *
     * @param itemNumber the item number of the item to be found.
     * @return the item matching the given item number. If no items were 
     *         found nor matched with the given iten number, 
     *         {@code null} is returned.
     */
    public boolean removeItemByItemNumber(String itemNumber)
    {
        boolean successfullRemoval = false;
        
        Iterator<Item> it = this.listOfItems.values().iterator();
        while (it.hasNext())
        {
            Item item = it.next();
            if (itemNumber.equalsIgnoreCase(item.getItemNumber()))
            {
                it.remove();
                successfullRemoval = true;
            }
        }
        return successfullRemoval;
    }
    
    /**
     * Returns the first item found in the item register matching
     * the item number given by the parameter.
     *
     * @param itemNumber the item number of the item to be found.
     * @return the item matching of the given item number. If no
     *         items were found with the matching item number, 
     *         {@code null} is returned.
     */
    public Item getItemByItemNumber(String itemNumber)
    {
        if (itemNumber == null || itemNumber.isBlank())
        {
            return null;
        }
        
        Item foundItem = null;
        boolean notFound = true;
        
        Iterator<Item> it = this.listOfItems.values().iterator();
        while (notFound && it.hasNext())
        {
            Item item = it.next();
            if (item.getItemNumber().equalsIgnoreCase(itemNumber.trim()))
            {
                foundItem = item;
                notFound = false;
            }
        }
        return foundItem;
    }
}