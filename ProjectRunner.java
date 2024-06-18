package prj5;

import java.io.IOException;
import java.text.DecimalFormat;

// -------------------------------------------------------------------------
/**

 */
public class ProjectRunner
{

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param args
     * @throws IOException
     *             exception thrown when needed
     */
    public static void main(String[] args) throws IOException
    {
        boolean showConsole = true;
        boolean showGUI = true;

        InputFileReader reader;

        // If an argument is found, reads that file. Otherwise, reads the
        // default file
        if (args.length > 0)
        {
            reader = new InputFileReader(args[0]);
        }
        else
        {
            reader = new InputFileReader("SampleInput1_2023.csv");
        }

        // New InfluencerCalculator to handle calculations
        InfluencerCalculator calc = new InfluencerCalculator(reader.getList());

        if (showConsole)
        {

            // Rounds everything to once decimal place
            DecimalFormat format = new DecimalFormat("#.#");

            // Sorts alphabetically by name
            calc.sortName();

            // Prints the name and traditional engagement rate of each
            // Influencer
            for (int i = 0; i < calc.getList().getLength(); i++)
            {
                Influencer curr = calc.getList().getEntry(i);
                System.out.println(curr.getChannelName());
                System.out.print("traditional: ");
                System.out.println(format.format(curr
                    .getQuarterTraditionalEngagement()));
                System.out.println("==========");
            }

            // Changes the InfluencerCalculator's sort method to reach
            // engagement
            calc.setType(InfluencerCalculator.REACH);
            calc.sortEngagement();

            System.out.println("**********");
            System.out.println("**********");

            // Prints the name and reach engagement rate of each Influencer
            for (int i = 0; i < calc.getList().getLength(); i++)
            {

                Influencer curr = calc.getList().getEntry(i);

                System.out.println(curr.getChannelName());
                System.out.print("reach: ");

                double minValue = Influencer.VERY_NEGATIVE_DOUBLE;

                // Checks the Influencer has an invalid reach engagement
                if (curr.getQuarterReachEngagement() == minValue)
                {
                    System.out.println("N/A");
                }
                else
                {
                    System.out.println(format.format(curr
                        .getQuarterReachEngagement()));
                }
                System.out.println("==========");
            }

        }
        if (showGUI)
        {
            @SuppressWarnings("unused")
            GUIWindow window = new GUIWindow(calc);
        }

    }

}
