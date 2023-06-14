/**
 * Represents the starting point of the application.
 * The only function of this class is to keep the class
 * method <b>main()</b>.
 *
 * @author Candidate: 10043
 * @version 2022-12-13
 */
public class Main
{
    /**
     * The main starting point of the application. The operating system
     * of the computer expects to find a publicly available method it can
     * call without having to create objects first.
     *
     * @param args an fixed size array of Strings holding arguments provided
     *          from the command line durung the startup of the application.
     *          In this application, args is not being used, and can be ignored.
     */
    public static void main(String[] args)
    {
        WarehouseApp warehouseApp = new WarehouseApp();

        warehouseApp.fill();
        warehouseApp.start();
    }
}