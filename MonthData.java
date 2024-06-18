
package prj5;

/**
 * Holds data from Influencers for specific months
 * 
 * @version 12/01/2023
 */
public class MonthData
{

    private String month;
    private int likes;
    private int posts;
    private int followers;
    private int comments;
    private int views;

    // ----------------------------------------------------------
    /**
     * Create a new MonthData object.
     * 
     * @param month
     *            of the year
     * @param likes
     *            total
     * @param posts
     *            total
     * @param followers
     *            total
     * @param comments
     *            total
     * @param views
     *            total
     */
    public MonthData(
        String month,
        int likes,
        int posts,
        int followers,
        int comments,
        int views)
    {
        this.month = month;
        this.likes = likes;
        this.posts = posts;
        this.followers = followers;
        this.comments = comments;
        this.views = views;
    }


    // ----------------------------------------------------------
    /**
     * return month
     * 
     * @return month
     */
    public String getMonth()
    {
        return month;
    }


    // ----------------------------------------------------------
    /**
     * return likes
     * 
     * @return likes
     */
    public int getLikes()
    {
        return likes;
    }


    // ----------------------------------------------------------
    /**
     * get # of posts
     * 
     * @return posts
     */
    public int getPosts()
    {
        return posts;
    }


    // ----------------------------------------------------------
    /**
     * return follower count
     * 
     * @return followers
     */
    public int getFollowers()
    {
        return followers;
    }


    // ----------------------------------------------------------
    /**
     * return comments
     * 
     * @return comments
     */
    public int getComments()
    {
        return comments;
    }


    // ----------------------------------------------------------
    /**
     * return # of views
     * 
     * @return views
     */
    public int getViews()
    {
        return views;
    }


    // ----------------------------------------------------------
    /**
     * calculate the total engagement rate using a formula
     * 
     * @return the totalEngRate
     */
    public double tradEngRate()
    {
        return ((comments + likes) / Double.valueOf(followers)) * 100;
    }


    // ----------------------------------------------------------
    /**
     * calculate the RateByReach using a formula
     * 
     * @return the rateByReach
     */
    public double reachEngRate()
    {
        // Case where views are zero and a divide by zero would occur
        if (views == 0)
        {
            return Influencer.VERY_NEGATIVE_DOUBLE;
        }

        return ((comments + likes) / Double.valueOf(views)) * 100;
    }
}
