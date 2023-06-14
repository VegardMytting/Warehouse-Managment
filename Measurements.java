/**
 * Represents all the measurements for an item.
 * Default measurement that can be used:
 *
 * <p>
 *  <li>Price measurement as norwegian kroner</li>
 *  <li>Weight measurement as kilograms</li>
 *  <li>Length measurement as meters</li>
 *  <li>Height measurement as meters</li>
 *  <li>Width measurement as meters</li>
 *  <li>Quantity measurement as units</li>
 * </p>
 *
 * @author Candidate: 10043
 * @version 2022-12-13
 */
public enum Measurements
{   
    /**
     * The measurment for the item price.
     */
    PRICE_MEASUREMENT("norwegian kroner", "NOK"),

    /**
     * The measurment for the item weight.
     */
    WEIGHT_MEASUREMENT("kilograms", "kg"),

    /**
     * The measurment for the item length.
     */
    LENGTH_MEMASUREMENT("meters", "m"),

    /**
     * The measurment for the item height.
     */
    HEIGHT_MEASUREMENT("meters", "m"),

    /**
     * The measurment for the item width.
     */
    WIDTH_MEASUREMENT("meters", "m"),

    /**
     * The measurment for the item quantity.
     */
    QUANTITY_MEASUREMENT("units", "");
    
    private final String expandedMeasurements;
    private final String abbreviatedMeasurements;
    
    /**
     * Constructs what will be responsible for all measurements.
     *
     * @param expandedMeasurements the expanded version of the item measurement.
     * @param abbreviatedMeasurements the abbreviation version of the item measurement.
     */
    private Measurements(String expandedMeasurements, String abbreviatedMeasurements)
    {
        this.expandedMeasurements = expandedMeasurements;
        this.abbreviatedMeasurements = abbreviatedMeasurements;
    }
    
    /**
     * Returns the expanded vesion of the measurement.
     *
     * @return returns the expended vesrion of the meaurement.
     */
    public String getExpandedMeasurements()
    {
        return this.expandedMeasurements;
    }
    
    /**
     * Returns the abbreviated version of the measurement.
     *
     * @return returns the abbreviated version of the measurement.
     */
    public String getAbbreviatedMeasurements()
    {
        return this.abbreviatedMeasurements;
    }
}
