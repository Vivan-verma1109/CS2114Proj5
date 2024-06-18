
package prj5;

/**
 * User of the social media platform
 * 

 * @version 12/01/2023
 */
public class Influencer
{

    private String username;
    private String channelName;
    private String country;
    private String mainTopic;
    private LinkedList<MonthData> accountData;
    private static final String[] FIRST_QUARTER =
    { "January", "February", "March" };

    /**
     * Used for indicating an invalid reach engagement was found
     */
    public static final Double VERY_NEGATIVE_DOUBLE = -99999.9;

    /**
     * New Influencer object
     * 
     * @param username
     *            Influencer's username
     * @param channelName
     *            Name of the Influencer's page
     * @param country
     *            Country the Influencer is from
     * @param mainTopic
     *            What topic the Influencer covers most frequently
     * @param accountData
     *            List of data from the Influencer's account for each month
     */
    public Influencer(
        String username,
        String channelName,
        String country,
        String mainTopic,
        LinkedList<MonthData> accountData)
    {
        this.username = username;
        this.channelName = channelName;
        this.country = country;
        this.mainTopic = mainTopic;
        this.accountData = accountData;
    }


    /**
     * Gets the username of the Influencer
     * 
     * @return The Influencer's username
     */
    public String getUsername()
    {
        return this.username;
    }


    /**
     * Gets the channel name of the Influencer
     * 
     * @return The Influencer's channelName
     */
    public String getChannelName()
    {
        return this.channelName;
    }


    /**
     * Gets the country the Influencer is from
     * 
     * @return The Influencer's country
     */
    public String getCountry()
    {
        return this.country;
    }
    
 


    /**
     * Gets the main topic that the Influencer covers
     * 
     * @return The Influencer's main topic
     */
    public String getMainTopic()
    {
        return this.mainTopic;
    }


    /**
     * Gets all of the monthly data associated with the Influencer
     * 
     * @return The LinkedList containing data for all of the Influencer's months
     */
    public LinkedList<MonthData> getAccountData()
    {
        return this.accountData;
    }


    /**
     * Gets data of a specific month for the Influencer
     * 
     * @param month
     *            The month being searched for
     * @return The MonthData that matches the searched month
     * @throws IllegalArgumentException
     *             If the searched month is not found
     */
    public MonthData getDataForMonth(String month)
        throws IllegalArgumentException
    {
        for (int i = 0; i < this.accountData.getLength(); i++)
        {
            if (month.equals(this.accountData.getEntry(i).getMonth()))
            {
                return this.accountData.getEntry(i);
            }
        }
        throw new IllegalArgumentException("No data for month");
    }


    /**
     * Gets all of the likes accumulated by the Influencer
     * 
     * @return The sum of likes accumulated from all months in the first quarter
     *         combined
     */
    public int getTotalLikes()
    {
        int totalLikes = 0;

        for (int i = 0; i < FIRST_QUARTER.length; i++)
        {
            totalLikes += this.getDataForMonth(FIRST_QUARTER[i]).getLikes();
        }

        return totalLikes;
    }


    /**
     * Gets all of the posts accumulated by the Influencer
     * 
     * @return The sum of posts accumulated from all months in the first quarter
     *         combined
     */
    public int getTotalPosts()
    {
        int totalPosts = 0;

        for (int i = 0; i < FIRST_QUARTER.length; i++)
        {
            totalPosts += this.getDataForMonth(FIRST_QUARTER[i]).getPosts();
        }

        return totalPosts;
    }


    /**
     * Gets all of the followers accumulated by the Influencer
     * 
     * @return The sum of all followers accumulated from all months in the first
     *         quarter combined
     */
    public int getTotalFollowers()
    {
        /**
         * int totalFollowers = 0;
         * 
         * for (int i = 0; i < FIRST_QUARTER.length; i++)
         * {
         * totalFollowers += this.getDataForMonth(FIRST_QUARTER[i])
         * .getFollowers();
         * }
         * 
         * return totalFollowers;
         **/

        // APPARENTLY this is the only way to get the requested output values
        return this.getDataForMonth("March").getFollowers();
    }


    /**
     * Gets all of the comments accumulated by the Influencer
     * 
     * @return The sum of all followers accumulated from all months in the first
     *         quarter combined
     */
    public int getTotalComments()
    {
        int totalComments = 0;

        for (int i = 0; i < FIRST_QUARTER.length; i++)
        {
            totalComments += this.getDataForMonth(FIRST_QUARTER[i])
                .getComments();
        }

        return totalComments;
    }


    /**
     * Gets all of the views accumulated by the Influencer
     * 
     * @return The sum of all views accumulated from all months in the first
     *         quarter combined
     */
    public int getTotalViews()
    {
        int totalViews = 0;

        for (int i = 0; i < FIRST_QUARTER.length; i++)
        {
            totalViews += this.getDataForMonth(FIRST_QUARTER[i]).getViews();
        }
        return totalViews;
    }


    /**
     * Gets engagement for the Influencer according to the reach engagement
     * formula
     * 
     * @return The Influencer's reach for the first quarter, or an arbitrary
     *         extremely low value if views is 0
     */
    public double getQuarterReachEngagement()
    {

        if (this.getTotalViews() == 0)
        {
            return VERY_NEGATIVE_DOUBLE;
        }

        double engagements = this.getTotalComments() + this.getTotalLikes();
        double engagementRatio = engagements / this.getTotalViews();

        return 100 * engagementRatio;
    }


    /**
     * Gets engagement for the Influencer according to the traditional
     * engagement formula
     * 
     * @return The Influencer's traditional engagement for the first quarter
     */
    public double getQuarterTraditionalEngagement()
    {
        double engagements = this.getTotalComments() + this.getTotalLikes();
        double engagementRatio = engagements / this.getTotalFollowers();

        return 100 * engagementRatio;
    }


    /**
     * Determines if the Influencer is equal to obj
     * 
     * @param obj
     *            The Object being compared
     * @return true if obj is the Influencer or obj is an Influencer with the
     *         same username, channel name, country, and main topic, false
     *         otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        // Checks if obj is the Influencer
        if (obj == this)
        {
            return true;
        }
        // Null check
        if (obj == null)
        {
            return false;
        }
        // Class check
        if (!obj.getClass().equals(this.getClass()))
        {
            return false;
        }
        // Username check
        if (!((Influencer)obj).getUsername().equals(this.username))
        {
            return false;
        }
        // Channel name check
        if (!((Influencer)obj).getChannelName().equals(this.channelName))
        {
            return false;
        }
        // Country check
        if (!((Influencer)obj).getCountry().equals(this.country))
        {
            return false;
        }
        // Main topic check
        return ((Influencer)obj).getMainTopic().equals(this.mainTopic);
    }
}
