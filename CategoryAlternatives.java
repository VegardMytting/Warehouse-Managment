/**
 * Represents all the category alternatives for the categorization of an item.
 * Default categories that can be used:
 *
 * <p>
 *  <li>1) Other</li>
 *  <li>2) Floor laminates</li>
 *  <li>3) Doors</li>
 *  <li>4) Windows</li>
 *  <li>5) Skirting boards</li>
 *  <li>6) Lumber</li>
 * </p>
 *
 * @author Candidate: 10043
 * @version 2022-12-13
 */
public enum CategoryAlternatives
{
    /**
     * The item category for other.
     */
    OTHER(1, "Other"),

    /**
     * The item category for floor laminates.
     */
    FLOOR_LAMINATES(2, "Floor laminates"), 

    /**
     * The item category for doors.
     */
    DOORS(3, "Doors"), 

    /**
     * The item category for windows.
     */
    WINDOWS(4, "Windows"),

    /**
     * The item category for skirting boards.
     */
    SKIRTING_BOARDS(5, "Skirting boards"), 

    /**
     * The item category for lumber.
     */
    LUMBER(6, "Lumber");

    private final int id;
    private final String name;
    
    /**
     * Constructs what will be responsible for all categories.
     *
     * @param Id the identification (Id) of the category.
     * @param name the name of the category.
     */
    private CategoryAlternatives(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    /**
     * Returns the identification (id) of the category.
     *
     * @return returns the identification (id) of the category.
     */
    public int getCategoryId()
    {
        return this.id;
    }

    /**
     * Returns the name of the category.
     *
     * @return returns the name of the category as a text.
     */
    public String getCategoryName()
    {
        return this.name;
    }

    /**
     * Returns the category by its respected identifcation (id).
     *
     * @param id the identification (id) of the category.
     * @return returns the category by its respected identification (id).
     */
    public static CategoryAlternatives getCategoryById(int id)
    {
        CategoryAlternatives[] categories = CategoryAlternatives.values();
        CategoryAlternatives returnCategory = null;

        for (int i = 0; i < categories.length && returnCategory == null; i++)
        {
            if (categories[i].getCategoryId() == id)
            {
                returnCategory = categories[i];
            }
        }
        return returnCategory;
    }
}