
package prj5;

/**
 * Tests methods in the ComparatorEngagementTraditional class

 * @version 12/01/2023
 */
public class ComparatorEngagementTraditionalTest extends student.TestCase
{
    private ComparatorEngagementTraditional myComparatorEngagementTraditional;
    private Influencer prime;
    private Influencer less;
    private Influencer equal;
    private Influencer more;

    /**
     * Initialized Influencers to compare
     */
    public void setUp()
    {
        MonthData januaryData = new MonthData("January", 1, 2, 3, 4, 5);
        MonthData februaryData = new MonthData("February", 2, 4, 6, 8, 10);
        MonthData marchData = new MonthData("March", 3, 6, 9, 12, 15);
        MonthData lessMarchData = new MonthData("March", 3, 6, 6, 12, 15);
        MonthData moreMarchData = new MonthData("March", 3, 6, 12, 12, 15);

        LinkedList<MonthData> monthList = new LinkedList<MonthData>();
        LinkedList<MonthData> lessMonthList = new LinkedList<MonthData>();
        LinkedList<MonthData> moreMonthList = new LinkedList<MonthData>();

        monthList.add(januaryData);
        monthList.add(februaryData);
        monthList.add(marchData);

        lessMonthList.add(januaryData);
        lessMonthList.add(februaryData);
        lessMonthList.add(lessMarchData);

        moreMonthList.add(januaryData);
        moreMonthList.add(februaryData);
        moreMonthList.add(moreMarchData);

        prime = new Influencer("sullivanking", "Sullivan King", "US", "Music",
            monthList);
        equal = new Influencer("sullivanking", "Sullivan King", "US", "Music",
            monthList);

        less = new Influencer("sullivanking", "Sullivan King", "US", "Music",
            lessMonthList);
        more = new Influencer("sullivanking", "Sullivan King", "US", "Music",
            moreMonthList);

        myComparatorEngagementTraditional = new ComparatorEngagementTraditional(
            InfluencerCalculator.QUARTER);
    }


    /**
     * Tests ComparatorEngagementTraditional.compare(Influencer, Influencer) by
     * ensuring the correct sign integer is returned and 0 is returned when data
     * is not found for a specific month
     */
    public void testCompare()
    {
        assertTrue(myComparatorEngagementTraditional.compare(prime, less) > 0);
        assertEquals(myComparatorEngagementTraditional.compare(prime, equal),
            0);
        assertTrue(myComparatorEngagementTraditional.compare(prime, more) < 0);

        myComparatorEngagementTraditional = new ComparatorEngagementTraditional(
            "March");

        assertTrue(myComparatorEngagementTraditional.compare(prime, less) > 0);
        assertEquals(myComparatorEngagementTraditional.compare(prime, equal),
            0);
        assertTrue(myComparatorEngagementTraditional.compare(prime, more) < 0);

        myComparatorEngagementTraditional = new ComparatorEngagementTraditional(
            "April");

        assertEquals(myComparatorEngagementTraditional.compare(prime, more), 0);

        prime.getAccountData().add(new MonthData("April", 4, 8, 12, 16, 20));

        assertEquals(myComparatorEngagementTraditional.compare(prime, more), 0);
    }
}
