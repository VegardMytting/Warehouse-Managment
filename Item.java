import java.lang.Math;

/**
 * Represents an item by the 
 * respected registernumber, the description, price, brand name,
 * weight, length, height, colur, quantity and its category.
 *
 * <p>
 * All mesurements will by default be mesured in SI-unites, such as
 * meters (m) and kilogram (kg). the item price will be by
 * default set to be the norwegian kroner (NOK).
 * </p>
 *
 * @author Candidate: 10043
 * @version 2022-12-13
 */
public class Item
{
    private String itemNumber;
    private String itemDescription;
    private int itemPrice;
    private int itemDiscount;
    private String itemBrandName;
    private double itemWeight;
    private double itemLength;
    private double itemHeight;
    private double itemWidth;
    private String itemColour;
    private int itemQuantity;
    private CategoryAlternatives itemCategory;

    /**
     * Constructs what will be represented as an item.
     *
     * @param itemNumber the item number of the item.
     * @param itemDescription the description of the item.
     * @param itemPrice the price of the item.
     * @param itemDiscount the discount of the item.
     * @param itemBrandName the brand name of the item.
     * @param itemWeight the weight of the item.
     * @param itemLength the length of the item.
     * @param itemHeight the height of the item.
     * @param itemWidth the width of the item.
     * @param itemColour the colour of the item.
     * @param itemQuantity the quantity of the item.
     * @param itemCategory the category of the item.
     */
    public Item(String itemNumber, String itemDescription,
                int itemPrice, int itemDiscount, String itemBrandName, double itemWeight, 
                double itemLength, double itemHeight, double itemWidth, String itemColour, 
                int itemQuantity, CategoryAlternatives itemCategory)
    {
        setItemNumber(itemNumber);
        setItemDescription(itemDescription);
        setItemPrice(itemPrice);
        setItemDiscount(itemDiscount);
        setItemBrandName(itemBrandName);
        setItemWeight(itemWeight);
        setItemLength(itemLength);
        setItemHeight(itemHeight);
        setItemWidth(itemWidth);
        setItemColour(itemColour);
        setItemQuantity(itemQuantity);
        setItemCategory(itemCategory);
    }

    /**
     * Sets the item number of the item as a text.
     *
     * @param newItemNumber the item number of the item as a text.
     */
    private void setItemNumber(String newItemNumber)
    {
        if (newItemNumber.isBlank())
        {
            this.itemNumber = "INVALID_ITEM_NUMBER";
        } else
        {
            this.itemNumber = newItemNumber.trim();
        }
    }
    
    /**
     * Sets the description of the item as a text.
     *
     * @param newItemDescription the description of the item as a text.
     */
    public void setItemDescription(String newItemDescription)
    {
        if (newItemDescription.isBlank())
        {
            this.itemDescription = "INVALID_DESCRIPTION";
        } else
        {
            this.itemDescription = newItemDescription.trim();
        }
    }

    /**
     * Sets the price of the item as a positive value in norwegian kroner (NOK).
     *
     * @param newItemPrice the price of the item as a potitive value in norwegian kroner (NOK).
     */
    public void setItemPrice(int newItemPrice)
    {
        if (newItemPrice >= 0)
        {
            this.itemPrice = newItemPrice;
        }
    }
    
    /**
     * Sets the discount of the item as a whole, number percentage.
     *
     * @param newItemDiscount the discount of the item as a whole, number percentage.
     */
    public void setItemDiscount(int newItemDiscount)
    {
        if (newItemDiscount >= 0 && newItemDiscount <= 100)
        {
            this.itemDiscount = newItemDiscount;
        }
    }

    /**
     * Sets the brand name of the item as a text.
     *
     * @param newItemBrandName the brand name of the item as a text.
     */
    public void setItemBrandName(String newItemBrandName)
    {
        if (newItemBrandName.isBlank())
        {
            this.itemBrandName = "INVALID_BRAND_NAME";
        } else
        {
            this.itemBrandName = newItemBrandName.trim();
        }
    }
    
    /**
     * Sets the weight of the item as a positive value in kilogram (kg).
     *
     * @param newItemWeight the weight of the item as a positive value in kilogram (kg).
     */
    public void setItemWeight(double newItemWeight)
    {
        if (newItemWeight > 0)
        {
            this.itemWeight = newItemWeight;
        }
    }
    
    /**
     * Sets the length of the item as a positive value in meters (m).
     *
     * @param newItemLength the length of the item as a positive value in meters (m).
     */
    public void setItemLength(double newItemLength)
    {
        if (newItemLength > 0)
        {
            this.itemLength = newItemLength;
        }
    }
    
    /**
     * Sets the height of the item as a positive value in meters (m).
     *
     * @param newItemHeight the height of the item as a positive value in meters (m).
     */
    public void setItemHeight(double newItemHeight)
    {
        if (newItemHeight > 0)
        {
            this.itemHeight = newItemHeight;
        }
    }
    
    /**
     * Sets the width of the item as a positive value in meters (m).
     *
     * @param newItemWidth the width of the item as a positive value in meters (m).
     */
    public void setItemWidth(double newItemWidth)
    {
        if (newItemWidth > 0)
        {
            this.itemWidth = newItemWidth;
        }
    }
    
    /**
     * Sets the colour of the item.
     *
     * @param newItemColour the colour of the item.
     */
    public void setItemColour(String newItemColour)
    {
        if (newItemColour.isBlank())
        {
            this.itemColour = "INVALID_COLOUR";
        } else
        {
            this.itemColour = newItemColour.trim();
        }
    }
    
    /**
     * Sets the quantity of the item as a positive value.
     *
     * @param newItemQuantity the quantity of the item as a positive value.
     */
    public void setItemQuantity(int newItemQuantity)
    {
        if (newItemQuantity >= 0)
        {
            this.itemQuantity = newItemQuantity;
        }
    }
    
    /**
     * Sets the category of the item.
     *
     * @param newItemCategory the category of the item is determined by the value of the category, 
     *        raninging from 1 to 4. If the category is not valid, {@code OTHER} will be returned.
     */
    public void setItemCategory(CategoryAlternatives newItemCategory)
    {
        if (newItemCategory == null)
        {
            this.itemCategory = CategoryAlternatives.OTHER;
        } else
        {
            this.itemCategory = newItemCategory;
        }
    }

    /**
     * Returns the item number of the item.
     *
     * @return the item number of the item.
     */
    public String getItemNumber()
    {
        return this.itemNumber;
    }
    
    /**
     * Returns the description of the item.
     *
     * @return the description of the item.
     */
    public String getItemDescription()
    {
        return this.itemDescription;
    }

    /**
     * Returns the price of the item as a potitive value in 
     * the norwegian korner (NOK) without a discount.
     *
     * @return the price of the item as a positive value in 
     *         the norwegian kroner (NOK) without a discount.
     */
    public double getItemPriceWithoutDiscount()
    {
        return this.itemPrice;
    }

    /**
     * Returns the discount of the item as a whole, number percentage.
     *
     * @return the discount of the item as a whole, number percentage.
     */
    public int getItemDiscount()
    {
        return this.itemDiscount;
    }

    /**
     * Returns the price of the item as a potitive value in 
     * the norwegian korner (NOK) with a discount.
     *
     * @return the price of the item as a positive value in 
     *         the norwegian kroner (NOK) with a discount.
     */
    public double getItemPriceWithDiscount()
    {
        double totalItemDiscount = 1.0 - (this.itemDiscount / 100.0);
        return Math.round(this.itemPrice * totalItemDiscount);
    }

    /**
     * Returns the brand name of the item.
     *
     * @return the brand name of the item.
     */
    public String getItemBrandName()
    {
        return this.itemBrandName;
    }
    
    /**
     * Returns the weight of the item as a positive value in kilograms(kg).
     *
     * @return the weight of the item as a positive value in kilograms(kg).
     */
    public double getItemWeight()
    {
        return this.itemWeight;
    }
    
    /**
     * Returns the length of the item as a positive value in menters(m).
     *
     * @return the length of the item as a positive value in meters(m).
     */
    public double getItemLength()
    {
        return this.itemLength;
    }
    
    /**
     * Returns the height of the item as a positive value in meters(m).
     *
     * @return the height of the item as a positive value in meters(m).
     */
    public double getItemHeight()
    {
        return this.itemHeight;
    }
    
    /**
     * Returns the width of the item as a positive value in meters(m).
     *
     * @return the width of the item as a positive value in meters(m).
     */
    public double getItemWidth()
    {
        return this.itemWidth;
    }
    
    /**
     * Returns the colour of the item.
     *
     * @return the colour of the item.
     */
    public String getItemColour()
    {
        return this.itemColour;
    }
    
    /**
     * Returns the quantity of the item as a positive value.
     *
     * @return the quantity of the item as a positive value.
     */
    public int getItemQuantity()
    {
        return this.itemQuantity;
    }
    
    /**
     * Returns the category of the item.
     *
     * @return the category of the item.
     */
    public CategoryAlternatives getItemCategory()
    {
        return this.itemCategory;
    }
}