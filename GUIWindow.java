package prj5;

import cs2.*;
import java.awt.Color;
import java.text.DecimalFormat;


public class GUIWindow
{
    private InfluencerCalculator calculator;
    private Button quitButton;
    private Button sortName;
    private Button sortEngagement;
    private Button tradEngagement;
    private Button reachEngagement;
    private Button january;
    private Button february;
    private Button march;
    private Button quarter;
    private Window window;
    private TextShape topText;
    private TextShape midText;
    private TextShape lowText;
    private boolean sortingByName;

    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    private static final int BAR_WIDTH = 50;
    private static final int BAR_SPACE = 150;
    private static final int MAX_BAR_HEIGHT = 200;

    private static final int TEXT_HEIGHT = 20;

    // ----------------------------------------------------------
    /**
     * Create a new GUIWindow object.
     * 
     * @param calc
     *            calc name for influencer calculator
     */
    public GUIWindow(InfluencerCalculator calc)
    {
        calculator = calc;
        sortingByName = false;

        // Window is created
        window = new Window("Social Media Vis");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Button to sort by channel name
        sortName = new Button("Sort by Channel Name");
        window.addButton(sortName, WindowSide.NORTH);
        sortName.onClick(this, "clickedSortName");

        // Button to sort by engagement rate
        sortEngagement = new Button("Sort by Engagement Rate");
        window.addButton(sortEngagement, WindowSide.NORTH);
        sortEngagement.onClick(this, "clickedSortEngagement");

        // Button to quit the window
        quitButton = new Button("Quit");
        window.addButton(quitButton, WindowSide.NORTH);
        quitButton.onClick(this, "clickedQuit");

        // Button to change the engagement rate to traditional
        tradEngagement = new Button("Traditional Engagement Rate");
        window.addButton(tradEngagement, WindowSide.WEST);
        tradEngagement.onClick(this, "clickedTradEngagement");

        // Button to change the engagement rate to reach
        reachEngagement = new Button("Reach Engagement Rate");
        window.addButton(reachEngagement, WindowSide.WEST);
        reachEngagement.onClick(this, "clickedReachEngagement");

        // Button to change the time period to January
        january = new Button("January");
        window.addButton(january, WindowSide.SOUTH);
        january.onClick(this, "clickedJanuary");

        // Button to change the time period to February
        february = new Button("February");
        window.addButton(february, WindowSide.SOUTH);
        february.onClick(this, "clickedFebruary");

        // Button to change the time period to March
        march = new Button("March");
        window.addButton(march, WindowSide.SOUTH);
        march.onClick(this, "clickedMarch");

        // Button to change the time period to the first quarter
        quarter = new Button("First Quarter (Jan - March)");
        window.addButton(quarter, WindowSide.SOUTH);
        quarter.onClick(this, "clickedQuarter");

        // Draws the first set of bars

        draw();

    }


    private void draw()
    {
        window.removeAllShapes();

        if (sortingByName)
        {
            calculator.sortName();
        }
        else
        {
            calculator.sortEngagement();
        }

        updateShapes();
        updateTexts();
    }


    /**
     * Draws shapes according to the current sort
     */
    private void updateShapes()
    {

        int maxHeight = 0;

        // Determines the maximum value for the current engagement
        for (int i = 0; i < calculator.getList().getLength(); i++)
        {
            Influencer currInfluencer = calculator.getList().getEntry(i);
            int height = (int)calculator.getEngagementForInfluencer(
                currInfluencer);
            maxHeight = Math.max(height, maxHeight);
        }

        for (int i = 0; i < calculator.getList().getLength(); i++)
        {
            // Influencer being looked at
            Influencer currInfluencer = calculator.getList().getEntry(i);
            int height = (int)calculator.getEngagementForInfluencer(
                currInfluencer);

            // Ensures height can't be negative
            height = Math.max(height, 0);

            // Scales everything according to the maximum value
            height = height * MAX_BAR_HEIGHT / maxHeight;

            // Because the color needs to be consistent for the channel, the
            // color is derived from the first three characters of the channel
            // name
            int red = 2 * currInfluencer.getChannelName().charAt(0);
            int green = 2 * currInfluencer.getChannelName().charAt(1);
            int blue = 2 * currInfluencer.getChannelName().charAt(2);

            // New bar is created and added to the window
            Shape newBar = new Shape(BAR_SPACE * (i + 1), (2 * WINDOW_HEIGHT
                / 3) - height, BAR_WIDTH, height, new Color(red, green, blue));

            window.addShape(newBar);
        }
    }


    // Draws text according to the current sort
    private void updateTexts()
    {

        // Text describing the current period and sort
        topText = new TextShape(40, TEXT_HEIGHT, "");
        midText = new TextShape(40, 2 * TEXT_HEIGHT, "");

        String sortingType = sortingByName
            ? "Sorting by Name"
            : "Sorting by Engagement Rate";

        lowText = new TextShape(40, 60, sortingType);

        window.addShape(topText);
        window.addShape(midText);
        window.addShape(lowText);

        // Determines the period to display as text
        switch (calculator.getPeriod())
        {
            case InfluencerCalculator.QUARTER:
                topText.setText("First Quarter (Jan - March)");
                break;
            default:
                topText.setText(calculator.getPeriod());
                break;
        }

        // Determines the engagement rate to display as text
        switch (calculator.getType())
        {
            case InfluencerCalculator.REACH:
                midText.setText("Reach Engagement Rate");
                break;

            case InfluencerCalculator.TRADITIONAL:
                midText.setText("Traditional Engagement Rate");
                break;

            default:
                break;
        }

        // Displays text for each influencer
        for (int i = 0; i < calculator.getList().getLength(); i++)
        {
            Influencer currInfluencer = calculator.getList().getEntry(i);

            String channelName = currInfluencer.getChannelName();

            // Rounds things off to one decimal place
            DecimalFormat format = new DecimalFormat("#.#");

            double engagementRate = calculator.getEngagementForInfluencer(
                currInfluencer);

            // Text shape for the name of the influencer
            TextShape name = new TextShape(BAR_SPACE * (i + 1), (2
                * WINDOW_HEIGHT / 3), "");

            window.addShape(name);
            name.setText(channelName);

            // Text shape for the displayed engagement rate
            TextShape rate = new TextShape(BAR_SPACE * (i + 1), (2
                * WINDOW_HEIGHT / 3) + TEXT_HEIGHT, "");

            window.addShape(rate);

            // Catches the scenario where an invalid engagement rate is
            // calculated
            if (engagementRate < 0)
            {
                rate.setText("N/A");
            }
            else
            {
                rate.setText(format.format(engagementRate).toString());
            }

        }

    }


    /**
     * Sorts the Influencers by channel name
     * 
     * @param button
     *            Button that, when clicked, calls this method
     * 
     */
    public void clickedSortName(Button button)
    {
        calculator.sortName();
        sortingByName = true;

        draw();
    }


    /**
     * Manages the actions when the button is clicked
     * 
     * @param button
     *            Button that, when clicked, calls this method
     */
    public void clickedSortEngagement(Button button)
    {
        calculator.sortEngagement();
        sortingByName = false;
        draw();
        lowText.setText("Sorting by Engagement Rate");
    }


    /**
     * Closes the window
     * 
     * @param button
     *            Button that, when clicked, calls this method
     */

    public void clickedQuit(Button button)
    {
        System.exit(0);
    }


    /**
     * Sets the displayed engagement rate to traditional engagement
     * 
     * @param button
     *            Button that, when clicked, calls this method
     */
    public void clickedTradEngagement(Button button)
    {
        calculator.setType(InfluencerCalculator.TRADITIONAL);
        draw();
    }


    /**
     * Sets the displayed engagement rate to reach engagement
     * 
     * @param button
     *            Button that, when clicked, calls this method
     */
    public void clickedReachEngagement(Button button)
    {
        calculator.setType(InfluencerCalculator.REACH);
        draw();
    }


    /**
     * Displays data for the month of January
     * 
     * @param button
     *            Button that, when clicked, calls this method
     */
    public void clickedJanuary(Button button)
    {
        calculator.setPeriod(InfluencerCalculator.JANUARY);
        draw();
    }


    /**
     * Displays data for the month of February
     * 
     * @param button
     *            Button that, when clicked, calls this method
     */
    public void clickedFebruary(Button button)
    {
        calculator.setPeriod(InfluencerCalculator.FEBRUARY);
        draw();
    }


    // ----------------------------------------------------------
    /**
     * Displays data for the month of March
     * 
     * @param button
     *            Button that, when clicked, calls this method
     */
    public void clickedMarch(Button button)
    {
        calculator.setPeriod(InfluencerCalculator.MARCH);
        draw();
    }


    /**
     * Displays data for the first quarter
     * 
     * @param button
     *            Button that, when clicked, calls this method
     */
    public void clickedQuarter(Button button)
    {
        calculator.setPeriod(InfluencerCalculator.QUARTER);
        draw();
    }

}
