package prj5;


/**
 * Tests Methods in the MonthData class
 * @version 12/01/2023
 */
public class MonthDataTest extends student.TestCase
{
    private MonthData data;
    /**
     * Sets up test methods 
     */
    public void setUp()
    {
        data = new MonthData("January", 1, 1, 3, 1, 2);
    }
    
    /**
     * Tests the method totalEngRate to make sure it works as intended
     */
    public void testTotalEngRate()
    {
        assertEquals(data.tradEngRate(), 66.66, 0.01);
        data = new MonthData("January", 1, 1, 6, 1, 1);
        assertEquals(data.tradEngRate(), 33.33, 0.01);
    }
    
    /**
     * Tests the method rateByReach to make sure it works as intended
     */
    public void testRateByReach()
    {
        assertEquals(data.reachEngRate(), 100, 0.01);
        data = new MonthData("January", 1, 1, 6, 1, 1);
        assertEquals(data.reachEngRate(), 200, 0.01);
    }
    
    
}
