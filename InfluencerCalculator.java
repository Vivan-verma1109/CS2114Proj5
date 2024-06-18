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
 * Handles sorting and getting data by period
 * 
 * @author Darren Hsu (darrenshu)
 * @author Nami Jain (jainnami)
 * @author Sam Shumaker (sshumak)
 * @author Vivan Verma (vivanv)
 * @version 12/01/2023
 */
public class InfluencerCalculator
{
    private LinkedList<Influencer> influencerList;
    private String period;
    private String type;

    /**
     * Used for changing the engagement sort to reach engagement
     */
    public static final String REACH = "reach";
    /**
     * Used for changing the engagement sort to traditional engagement
     */
    public static final String TRADITIONAL = "trad";
    /**
     * Used for changing the period being searched to the first quarter
     */
    public static final String QUARTER = "quarter";
    /**
     * Used for changing the period being searched to January
     */
    public static final String JANUARY = "January";
    /**
     * Used for changing the period being searched to February
     */
    public static final String FEBRUARY = "February";
    /**
     * Used for changing the period being searched to March
     */
    public static final String MARCH = "March";

    // ----------------------------------------------------------
    /**
     * Create a new InfluencerCalculator object.
     * 
     * @param influencerList
     *            list of influencers
     */
    public InfluencerCalculator(LinkedList<Influencer> influencerList)
    {
        this.influencerList = influencerList;
        this.sortName();
        period = QUARTER;
        type = REACH;

    }


    // ----------------------------------------------------------
    /**
     * Sorts the list of influencers by name given a particular period of time
     */
    public void sortName()
    {
        ComparatorName compare = new ComparatorName();
        influencerList.sort(compare);
    }


    // ----------------------------------------------------------
    /**
     * Sorts the list of influencers by engagement rate given a particular
     * period of time and type of engagement
     */
    public void sortEngagement()
    {
        // Reach engagement
        if (type.equals(REACH))
        {
            ComparatorEngagementReach compare = new ComparatorEngagementReach(
                period);
            influencerList.sort(compare);
        }
        // Traditional Engagement
        else
        {
            ComparatorEngagementTraditional compare =
                new ComparatorEngagementTraditional(period);
            influencerList.sort(compare);
        }
    }


    // ----------------------------------------------------------
    /**
     * Setter method for period of interested time
     * 
     * @param newPeriod
     *            the time that they are interested in
     */
    public void setPeriod(String newPeriod)
    {
        period = newPeriod;
    }


    /**
     * Gets an engagement value depending on the current period and engagement
     * type
     * 
     * @param influencer
     *            Influencer being searched for
     * @return proper engagement value from influencer
     */
    public double getEngagementForInfluencer(Influencer influencer)
    {
        if (this.type.equals(TRADITIONAL))
        {
            if (this.period.equals(QUARTER))
            {
                // Quarterly traditional engagement
                return influencer.getQuarterTraditionalEngagement();
            }
            // Month traditional engagement
            return influencer.getDataForMonth(period).tradEngRate();
        }
        // Quarterly reach engagement
        if (this.period.equals(QUARTER))
        {
            return influencer.getQuarterReachEngagement();
        }
        // Month reach engagement
        return influencer.getDataForMonth(period).reachEngRate();

    }


    // ----------------------------------------------------------
    /**
     * Setter method for type of engagement
     * 
     * @param newType
     *            the type of engagement
     */
    public void setType(String newType)
    {
        type = newType;
    }


    // ----------------------------------------------------------
    /**
     * Getter method for the list.
     * 
     * @return the influencerList
     */
    public LinkedList<Influencer> getList()
    {
        return influencerList;
    }


    // ----------------------------------------------------------
    /**
     * Getter method for the type
     * 
     * @return the type
     */
    public String getType()
    {
        return type;
    }


    /**
     * Getter method for the period
     * 
     * @return the period
     */
    public String getPeriod()
    {
        return period;
    }
}
