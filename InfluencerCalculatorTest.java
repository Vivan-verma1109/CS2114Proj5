
package prj5;

import student.TestCase;

/**
 * Tests Methods in the LinkedList class
 * 

 * @version 12/01/2023
 */
public class InfluencerCalculatorTest extends TestCase
{
    private InfluencerCalculator calc;
    private LinkedList<Influencer> list;
    private Influencer one;
    private Influencer two;
    private Influencer three;

    /**
     * Sets up test methods
     */
    public void setUp()
    {
        // Constructor for MonthData is in the format String month, int likes,
        // int posts, int followers, int comments, int views
        list = new LinkedList<Influencer>();
        LinkedList<MonthData> accountData = new LinkedList<MonthData>();
        MonthData data = new MonthData("January", 1, 1, 3, 1, 2);
        accountData.add(data);
        data = new MonthData("February", 11, 11, 3, 11, 1);
        accountData.add(data);
        data = new MonthData("March", 111, 111, 3, 111, 3);
        accountData.add(data);
        one = new Influencer("ccc", "nameOne", "USA", "Music", accountData);
        accountData = new LinkedList<MonthData>();
        data = new MonthData("January", 2, 2, 2, 2, 6);
        accountData.add(data);
        data = new MonthData("February", 22, 22, 2, 22, 4);
        accountData.add(data);
        data = new MonthData("March", 222, 222, 2, 222, 2);
        accountData.add(data);
        two = new Influencer("bbb", "nametwo", "Britain", "Gaming",
            accountData);
        accountData = new LinkedList<MonthData>();
        data = new MonthData("January", 3, 3, 6, 3, 3);
        accountData.add(data);
        data = new MonthData("February", 33, 33, 6, 33, 9);
        accountData.add(data);
        data = new MonthData("March", 333, 333, 6, 333, 6);
        accountData.add(data);
        three = new Influencer("aaa", "nameTree", "France", "Sports",
            accountData);

        list.add(one);
        list.add(two);
        list.add(three);

        calc = new InfluencerCalculator(list);
    }


    /**
     * Tests the method sortName to make sure it works as intended
     */
    public void testSortName()
    {

        list = calc.getList();
        calc.sortName();
        assertEquals(list.getEntry(0), one);
        assertEquals(list.getEntry(1), three);
        assertEquals(list.getEntry(2), two);
    }


    /**
     * Tests the method sortEngagement to make sure it works as intended
     */
    public void testSortEngagement()
    {
        calc.setType(InfluencerCalculator.TRADITIONAL);
        calc.sortEngagement();
        list = calc.getList();
        assertEquals(list.getEntry(0), two);
        assertEquals(list.getEntry(1), three);
        assertEquals(list.getEntry(2), one);

        calc.setType(InfluencerCalculator.REACH);

        list = calc.getList();
        calc.sortEngagement();
        assertEquals(list.getEntry(0), two);
        assertEquals(list.getEntry(1), three);
        assertEquals(list.getEntry(2), one);
    }


    /**
     * Tests the method setPeriod to make sure it works as intended
     */
    public void testSetPeriod()
    {

        calc.setPeriod(InfluencerCalculator.QUARTER);
        list = calc.getList();
        calc.sortEngagement();
        assertEquals(list.getEntry(0), one);
        assertEquals(list.getEntry(1), three);
        assertEquals(list.getEntry(2), two);

        calc.setPeriod(InfluencerCalculator.JANUARY);
        list = calc.getList();
        calc.sortEngagement();
        assertEquals(list.getEntry(0), three);
        assertEquals(list.getEntry(1), one);
        assertEquals(list.getEntry(2), two);

        calc.setPeriod(InfluencerCalculator.FEBRUARY);
        list = calc.getList();
        calc.sortEngagement();
        assertEquals(list.getEntry(0), one);
        assertEquals(list.getEntry(1), two);
        assertEquals(list.getEntry(2), three);
    }


    /**
     * Tests the method setType to make sure it works as intended
     */
    public void testSetType()
    {
        calc.setType(InfluencerCalculator.TRADITIONAL);
        calc.sortEngagement();
        list = calc.getList();
        assertEquals(list.getEntry(0), two);
        assertEquals(list.getEntry(1), three);
        assertEquals(list.getEntry(2), one);

        calc.setType(InfluencerCalculator.REACH);

        list = calc.getList();
        calc.sortEngagement();
        assertEquals(list.getEntry(0), two);
        assertEquals(list.getEntry(1), three);
        assertEquals(list.getEntry(2), one);
    }


    /**
     * Tests InfluencerCalculator.getEngagementForInfluencer(Influencer) with
     * all four possible combinations of engagement type and period
     */
    public void testGetEngagementForInfluencer()
    {
        calc.setType(InfluencerCalculator.TRADITIONAL);
        calc.setPeriod(InfluencerCalculator.QUARTER);

        assertEquals(calc.getEngagementForInfluencer(one), 8200.00, 0.01);

        calc.setPeriod(InfluencerCalculator.FEBRUARY);

        assertEquals(calc.getEngagementForInfluencer(one), 733.33, 0.01);

        calc.setType(InfluencerCalculator.REACH);
        calc.setPeriod(InfluencerCalculator.QUARTER);

        assertEquals(calc.getEngagementForInfluencer(one), 4100.00, 0.01);

        calc.setPeriod(InfluencerCalculator.FEBRUARY);

        assertEquals(calc.getEngagementForInfluencer(one), 2200.00, 0.01);
    }
}
