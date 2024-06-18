
package prj5;

import java.util.Comparator;

/**
 * Compares influencers based on traditional engagement rates
 * 
 */
public class ComparatorEngagementTraditional implements Comparator<Influencer>
{

    private String period;

    // ----------------------------------------------------------
    /**
     * Create a new ComparatorName object.
     * 
     * @param newPeriod
     *            The period of data to sort
     */
    public ComparatorEngagementTraditional(String newPeriod)
    {
        period = newPeriod;
    }


    /**
     * Returns a comparison of the two Influencers' traditional engagement rates
     * 
     * @param o1
     *            First Influencer being compared
     * @param o2
     *            Second Influencer being compared
     * @return a negative value if o1 has a greater traditional engagement rate
     *         as sorting should be descending
     */
    @Override
    @SuppressWarnings("unused")
    public int compare(Influencer o1, Influencer o2)
    {
        // If comparing for the entire quarter
        if (period.equals(InfluencerCalculator.QUARTER))
        {
            return -(int)(o1.getQuarterTraditionalEngagement() - o2
                .getQuarterTraditionalEngagement());
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

        return -(int)(data1.tradEngRate() - data2.tradEngRate());
    }

}
