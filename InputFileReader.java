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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads data from files and generates Influencers
 * 
 * @author Darren Hsu (darrenshu)
 * @author Nami Jain (jainnami)
 * @author Sam Shumaker (sshumak)
 * @author Vivan Verma (vivanv)
 * @version 12/01/2023
 */
public class InputFileReader
{
    private LinkedList<Influencer> influencerList;

    private static final String[] MONTHS =
    { "January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December" };

    // ----------------------------------------------------------
    /**
     * Create a new InputFileReader object.
     * 
     * @param fileName
     *            the name of the file to read
     * @throws FileNotFoundException
     *             if the file specified by fileName is not found
     */
    public InputFileReader(String fileName) throws FileNotFoundException
    {
        influencerList = processList(fileName);

        @SuppressWarnings("unused")
        InfluencerCalculator calculator = new InfluencerCalculator(
            influencerList);
    }


    /**
     * Processes an input file into a LinkedList of Influencers
     * 
     * @param fileName
     *            Name of the input file
     * @return a LinkedList<Influencer> derived from the input file
     * @throws FileNotFoundException
     *             if the input file is not found
     */
    public LinkedList<Influencer> processList(String fileName)
        throws FileNotFoundException
    {
        LinkedList<Influencer> derivedList = new LinkedList<Influencer>();

        // Scanner to read through input file
        File inputFile = new File(fileName);
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine())
        {
            String nextLine = scanner.nextLine();
            String[] args = nextLine.split(",\\s*");

            // Data for Influencer and Month Data
            String month = args[0];

            String username = args[1];
            String name = args[2];
            String country = args[3];
            String topic = args[4];

            int likes = toInt(args[5]);
            int posts = toInt(args[6]);
            int followers = toInt(args[7]);
            int comments = toInt(args[8]);
            int views = toInt(args[9]);

            boolean validMonth = false;

            // Ensures the month is actually a month of the year
            for (int i = 0; i < MONTHS.length; i++)
            {
                if (month.equals(MONTHS[i]))
                {
                    validMonth = true;
                }
            }

            if (!validMonth)
            {
                continue;
            }

            // Newly created Influencer and MonthData
            Influencer derivedInfluencer = new Influencer(username, name,
                country, topic, new LinkedList<MonthData>());
            MonthData newMonthData = new MonthData(month, likes, posts,
                followers, comments, views);

            if (derivedList.contains(derivedInfluencer))
            {
                // If the created Influencer already exist in the LinkedList
                // being created, appends the new MonthData to the existing
                // Influencer
                int index = 0;
                while (!derivedList.getEntry(index).equals(derivedInfluencer))
                {
                    index++;
                }
                derivedList.getEntry(index).getAccountData().add(newMonthData);
            }
            else
            {
                // If the created Influencer does not already exist, appends the
                // newly created MonthData to the newly created Influencer and
                // appends the newly created Influencer to the LinkedList being
                // created
                derivedInfluencer.getAccountData().add(newMonthData);
                derivedList.add(derivedInfluencer);
            }

        }

        scanner.close();

        return derivedList;
    }


    /**
     * Converts a String into an int
     * 
     * @param str
     *            String to be converted
     * @return Integer value of str, or 0 if an int could not be parsed
     */
    private int toInt(String str)
    {

        try
        {
            return Integer.parseInt(str);
        }
        catch (Exception e)
        {
            return 0;
        }
    }


    // ----------------------------------------------------------
    /**
     * Getter method for the influencer list
     * 
     * @return the influencer list
     */
    public LinkedList<Influencer> getList()
    {
        return influencerList;
    }
}
