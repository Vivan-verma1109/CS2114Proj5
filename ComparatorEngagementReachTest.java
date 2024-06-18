// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Darren Hsu (darrenhsu)
// -- Nami Jain (jainnami)
// -- Sam Shumaker (sshumak)
// -- Vivan Verma (vivanv)
package prj5;

/**
 * Tests methods in the ComparatorEngagementReach class
 * 
 * @author Darren Hsu (darrenhsu)
 * @author Nami Jain (jainnami)
 * @author Sam Shumaker (sshumak)
 * @author Vivan Verma (vivanv)
 * @version 12/01/2023
 */
public class ComparatorEngagementReachTest extends student.TestCase
{
    private ComparatorEngagementReach myComparatorEngagementReach;
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
        MonthData lessMarchData = new MonthData("March", 3, 6, 9, 15, 15);
        MonthData moreMarchData = new MonthData("March", 3, 6, 9, 9, 15);

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

        myComparatorEngagementReach = new ComparatorEngagementReach(
            InfluencerCalculator.QUARTER);
    }


    /**
     * Tests ComparatorEngagementReach.compare(Influencer, Influencer) by
     * ensuring the correct sign integer is returned and 0 is returned when data
     * is not found for a specific month
     */
    public void testCompare()
    {
        assertTrue(myComparatorEngagementReach.compare(prime, less) > 0);
        assertEquals(myComparatorEngagementReach.compare(prime, equal), 0);
        assertTrue(myComparatorEngagementReach.compare(prime, more) < 0);

        myComparatorEngagementReach = new ComparatorEngagementReach("March");

        assertTrue(myComparatorEngagementReach.compare(prime, less) > 0);
        assertEquals(myComparatorEngagementReach.compare(prime, equal), 0);
        assertTrue(myComparatorEngagementReach.compare(prime, more) < 0);

        myComparatorEngagementReach = new ComparatorEngagementReach("April");

        assertEquals(myComparatorEngagementReach.compare(prime, more), 0);

        prime.getAccountData().add(new MonthData("April", 4, 8, 12, 16, 20));

        assertEquals(myComparatorEngagementReach.compare(prime, more), 0);
    }
}
