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

import java.util.Comparator;

/**
 * Compares Influencers alphabetically based on Channel Name
 * 
 * @author Darren Hsu (darrenshu)
 * @author Nami Jain (jainnami)
 * @author Sam Shumaker (sshumak)
 * @author Vivan Verma (vivanv)
 * @version 12/01/2023
 */
public class ComparatorName implements Comparator<Influencer>
{

    /**
     * Create a new ComparatorName object
     */
    public ComparatorName()
    {
        // No fields to instantiate
    }


    /**
     * Compares two Influencers alphabetically by Channel Name
     * 
     * @param o1
     *            The first influencer being compared
     * @param o2
     *            the second influencer being compared
     * @return A negative value if o1's channel name comes before o2's in the
     *         alphabet
     */
    @Override
    public int compare(Influencer o1, Influencer o2)
    {
        String name1 = o1.getChannelName().toLowerCase();
        String name2 = o2.getChannelName().toLowerCase();
        return name1.compareTo(name2);
    }

}
