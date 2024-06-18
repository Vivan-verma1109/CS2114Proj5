package prj5;

import java.util.Comparator;

/**
 * Compares Influencers based on reach engagement rates
 *
 * @author Darren Hsu (darrenshu)
 * @author Nami Jain (jainnami)
 * @author Sam Shumaker (sshumak)
 * @author Vivan Verma (vivanv)
 * @version 12/01/2023
 */
public class ComparatorEngagementReach implements Comparator<Influencer>
{

    private String period;

    // ----------------------------------------------------------
    /**
     * Create a new ComparatorName object.
     * 
     * @param newPeriod
     *            The period of data to sort
     */
    public ComparatorEngagementReach(String newPeriod)
    {
        period = newPeriod;
    }


    /**
     * Returns a comparison of the two Influencers' reach engagement rates
     * 
     * @param o1
     *            First Influencer being compared
     * @param o2
     *            Second Influencer being compared
     * @return a negative value if o1 has a greater reach engagement rate
     *         as sorting should be descending
     */
    @Override
    @SuppressWarnings("unused")
    public int compare(Influencer o1, Influencer o2)
    {
        // If comparing for the entire quarter
        if (period.equals(InfluencerCalculator.QUARTER))
        {
            return -(int)(o1.getQuarterReachEngagement() - o2
                .getQuarterReachEngagement());
        }
        // If comparing for a specific month
        MonthData data1;
        MonthData data2;
        try
        {
            data1 = o1.getDataForMonth(period);
            data2 = o2.getDataForMonth(period);
        }
        catch (IllegalArgumentException e)
        {
            return 0;
        }

        return -(int)(data1.reachEngRate() - data2.reachEngRate());
    }

}
