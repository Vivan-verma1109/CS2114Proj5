
package prj5;

/**
 * Tests methods in the ComparatorName class

 */
public class ComparatorNameTest extends student.TestCase
{
    private ComparatorName myComparatorName;
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

        LinkedList<MonthData> monthList = new LinkedList<MonthData>();

        monthList.add(januaryData);
        monthList.add(februaryData);
        monthList.add(marchData);

        prime = new Influencer("sullivanking", "Sullivan King", "US", "Music",
            monthList);
        less = new Influencer("sullivanking", "Bullivan King", "US", "Music",
            monthList);
        equal = new Influencer("sullivanking", "Sullivan King", "US", "Music",
            monthList);
        more = new Influencer("sullivanking", "Wullivan King", "US", "Music",
            monthList);

        myComparatorName = new ComparatorName();
    }


    /**
     * Tests ComparatorName.compare(Influencer, Influencer) by ensuring the
     * correct sign integer is returned
     */
    public void testCompare()
    {
        assertTrue(myComparatorName.compare(prime, less) > 0);
        assertEquals(myComparatorName.compare(prime, equal), 0);
        assertTrue(myComparatorName.compare(prime, more) < 0);
    }
}
