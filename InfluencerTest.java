
package prj5;

/**
 * Tests methods in the Influencer class
 * 
 * @version 12/01/2023
 */
public class InfluencerTest extends student.TestCase
{

    private Influencer myInfluencer;
    private MonthData januaryData;
    private MonthData aprilData;
    private LinkedList<MonthData> monthList = new LinkedList<MonthData>();

    /**
     * Initializes an Influencer to test and gives it data
     */
    public void setUp()
    {
        januaryData = new MonthData("January", 1, 2, 3, 4, 5);
        MonthData februaryData = new MonthData("February", 2, 4, 6, 8, 10);
        MonthData marchData = new MonthData("March", 3, 6, 9, 12, 15);
        aprilData = new MonthData("April", 4, 8, 12, 16, 20);

        monthList.add(januaryData);
        monthList.add(februaryData);
        monthList.add(marchData);
        monthList.add(aprilData);

        myInfluencer = new Influencer("sullivanking", "Sullivan King", "US",
            "Music", monthList);

    }


    /**
     * Tests Influencer.getUsername() by ensuring the correct String is returned
     */
    public void testGetUsername()
    {
        assertEquals(myInfluencer.getUsername(), "sullivanking");
    }


    /**
     * Tests Influencer.getChannelName() by ensuring the correct String is
     * returned
     */
    public void testGetChannelName()
    {
        assertEquals(myInfluencer.getChannelName(), "Sullivan King");
    }


    /**
     * Tests Influencer.getCountry() by ensuring the correct String is returned
     */
    public void testGetCountry()
    {
        assertEquals(myInfluencer.getCountry(), "US");
    }


    /**
     * Tests Influencer.getMainTopic() by ensuring the correct String is
     * returned
     */
    public void testGetMainTopic()
    {
        assertEquals(myInfluencer.getMainTopic(), "Music");
    }


    /**
     * Tests Influencer.getAccountData() by ensuring the correct
     * LinkedList<MonthData> is returned
     */
    public void testGetAccountData()
    {
        assertEquals(myInfluencer.getAccountData(), monthList);
    }


    /**
     * Tests Influencer.getDataForMonth() by ensuring the correct MonthData is
     * returned based on the passed String
     */
    public void testGetDataForMonth()
    {
        assertEquals(myInfluencer.getDataForMonth("January"), januaryData);
        assertEquals(myInfluencer.getDataForMonth("April"), aprilData);

        Exception exception = null;

        try
        {
            assertEquals(myInfluencer.getDataForMonth("May"), "No data");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests Influencer.getTotalLikes() by ensuring the sum of the likes for
     * January, February, and March is returned
     */
    public void testGetTotalLikes()
    {
        assertEquals(myInfluencer.getTotalLikes(), 6);
    }


    /**
     * Tests Influencer.getTotalPosts() by ensuring the sum of the posts for
     * January, February, and March is returned
     */
    public void testGetTotalPosts()
    {
        assertEquals(myInfluencer.getTotalPosts(), 12);
    }


    /**
     * Tests Influencer.getTotalFollowers() by ensuring the sum of the followers
     * for January, February, and March is returned
     */
    public void testGetTotalFollowers()
    {
        assertEquals(myInfluencer.getTotalFollowers(), 9);
    }


    /**
     * Tests Influencer.getTotalComments() by ensuring the sum of the comments
     * for January, February, and March is returned
     */
    public void testGetTotalComments()
    {
        assertEquals(myInfluencer.getTotalComments(), 24);
    }


    /**
     * Tests Influencer.getTotalViews() by ensuring the sum of the views for
     * January, February, and March is returned
     */
    public void testGetTotalViews()
    {
        assertEquals(myInfluencer.getTotalViews(), 30);
    }


    /**
     * Tests Influencer.getQuarterReachEngagement() by ensuring the correct
     * result
     * of the reach engagement formula is returned
     */
    public void testGetQuarterReachEngagement()
    {
        assertEquals(myInfluencer.getQuarterReachEngagement(), 100.0, 0.01);
        
    }


    /**
     * Tests Influencer.getQuarterTraditionalEngagement() by ensuring the
     * correct result of the traditional engagement formula is returned
     */
    public void testGetQuarterTraditionalEngagement()
    {
        assertEquals(myInfluencer.getQuarterTraditionalEngagement(), 333.33,
            0.01);
    }


    /**
     * Tests Influencer.equals(Object) with
     * itself
     * a null Object
     * a non-Influencer Object
     * an Influencer with a different username
     * an Influencer with a different channel name
     * an Influencer with a different country
     * an Influencer with a different main topic
     * an Influencer with equivalent attributes
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals()
    {
        Influencer testInfluencer = null;

        // With itself
        assertTrue(myInfluencer.equals(myInfluencer));

        // With null
        assertFalse(myInfluencer.equals(testInfluencer));

        // With a String
        assertFalse(myInfluencer.equals("Not an Influencer"));

        // Differing usernames
        testInfluencer = new Influencer("cametek", "Sullivan King", "US",
            "Music", monthList);
        assertFalse(myInfluencer.equals(testInfluencer));

        // Differing channel names
        testInfluencer = new Influencer("sullivanking", "Camellia", "US",
            "Music", monthList);
        assertFalse(myInfluencer.equals(testInfluencer));

        // Differing countries
        testInfluencer = new Influencer("sullivanking", "Sullivan King", "JP",
            "Music", monthList);
        assertFalse(myInfluencer.equals(testInfluencer));

        // Differing main topics
        testInfluencer = new Influencer("sullivanking", "Sullivan King", "US",
            "Gaming", monthList);
        assertFalse(myInfluencer.equals(testInfluencer));

        // Equivalent Influencers
        testInfluencer = new Influencer("sullivanking", "Sullivan King", "US",
            "Music", monthList);
        assertTrue(myInfluencer.equals(testInfluencer));

    }

}
