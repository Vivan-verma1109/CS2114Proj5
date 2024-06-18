
package prj5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Tests Methods in the LinkedList class
 * 

 * @version 12/01/2023
 */
public class LinkedListTest extends student.TestCase
{
    private LinkedList<String> myLinkedList;

    /**
     * Initializes the LinkedList being tested
     */
    public void setUp()
    {
        myLinkedList = new LinkedList<String>();
    }


    /**
     * Tests LinkedList.getLength() by verifying the correct value is returned
     * for an empty LinkedList and a not empty LinkedList
     */
    public void testGetLength()
    {
        assertEquals(myLinkedList.getLength(), 0);
        myLinkedList.add("Entry");
        assertEquals(myLinkedList.getLength(), 1);
    }


    /**
     * Tests LinkedList.add(T) by ensuring additions are added and null can't be
     * added
     */
    public void testAdd()
    {
        assertEquals(myLinkedList.getLength(), 0);
        myLinkedList.add("Entry");
        assertEquals(myLinkedList.getLength(), 1);
        myLinkedList.add("Entry2");
        assertEquals(myLinkedList.getLength(), 2);

        Exception exception = null;

        try
        {
            myLinkedList.add(null);
            myLinkedList.add(100, "fame");
            myLinkedList.add(-2, "Fame");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        
        myLinkedList.add("E");
        myLinkedList.add(0, "gg");
        myLinkedList.add(1, "G");
        myLinkedList.add(2, "AF");
        myLinkedList.add("f");
        assertEquals(myLinkedList.getEntry(0), "gg");
        assertEquals(myLinkedList.getEntry(1), "G");

        assertNotNull(exception);
    }


    /**
     * Tests LinkedList.add(T, int) by ensuring additions are added at the right
     * index, an invalid index can't be passed, and null can't be added
     */
    public void testAddWithIndex()
    {
        assertEquals(myLinkedList.getLength(), 0);

        Exception exception = null;

        try
        {
            myLinkedList.add(1, "Entry");
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        myLinkedList.add(0, "Entry");
        assertEquals(myLinkedList.getLength(), 1);

        myLinkedList.add(1, "Entry2");
        assertEquals(myLinkedList.getLength(), 2);

        myLinkedList.add(1, "Entry3");
        assertEquals(myLinkedList.getLength(), 3);

        myLinkedList.add(0, "Entry4");
        assertEquals(myLinkedList.getLength(), 4);

        assertEquals(myLinkedList.getEntry(0), "Entry4");
        assertEquals(myLinkedList.getEntry(1), "Entry");
        assertEquals(myLinkedList.getEntry(2), "Entry3");
        assertEquals(myLinkedList.getEntry(3), "Entry2");

    }


    /**
     * Tests LinkedList.isEmpty() by ensuring the correct truth value is
     * returned for empty and not empty LinkedLists
     */
    public void testIsEmpty()
    {
        assertTrue(myLinkedList.isEmpty());
        myLinkedList.add("Entry");
        assertFalse(myLinkedList.isEmpty());
    }


    /**
     * Tests LinkedList.remove(T) by ensuring the first matching Entry is
     * removed
     */
    public void testRemoveData()
    {
        assertFalse(myLinkedList.remove("Entry"));

        myLinkedList.add("Entry");
        assertEquals(myLinkedList.getLength(), 1);
        assertTrue(myLinkedList.remove("Entry"));
        assertEquals(myLinkedList.getLength(), 0);

        myLinkedList.add("Entry");
        myLinkedList.add("Entry2");
        myLinkedList.add("Entry3");
        assertEquals(myLinkedList.getLength(), 3);

        assertTrue(myLinkedList.remove("Entry"));
        assertTrue(myLinkedList.remove("Entry3"));
        assertEquals(myLinkedList.getLength(), 1);

        myLinkedList.add("Entry3");
        assertEquals(myLinkedList.getLength(), 2);

        assertTrue(myLinkedList.remove("Entry3"));
        assertEquals(myLinkedList.getLength(), 1);

    }


    /**
     * Tests LinkedList.remove(int) by ensuring the correct Node is removed and
     * invalid indexes cannot be removed
     */
    public void testRemoveIndex()
    {
        Exception exception = null;

        try
        {
            assertEquals(myLinkedList.remove(-2), "Entry");
            assertEquals(myLinkedList.remove(0), "Entry");
            myLinkedList.remove(0);


        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        exception = null;

        try
        {
            assertEquals(myLinkedList.remove(500), "Entry");
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        exception = null;

        myLinkedList.add("Entry");
        assertEquals(myLinkedList.getLength(), 1);

        try
        {
            assertEquals(myLinkedList.remove(1), "Entry");

        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        assertEquals(myLinkedList.remove(0), "Entry");
        assertEquals(myLinkedList.getLength(), 0);

        myLinkedList.add("Entry");
        myLinkedList.add("Entry2");
        myLinkedList.add("Entry3");
        myLinkedList.add("Entry4");

        assertEquals(myLinkedList.remove(0), "Entry");
        assertEquals(myLinkedList.remove(1), "Entry3");
        assertEquals(myLinkedList.remove(1), "Entry4");
        assertEquals(myLinkedList.remove(0), "Entry2");

        assertEquals(myLinkedList.getLength(), 0);

    }


    /**
     * Tests LinkedList.clear() by verifying all entries disappear
     */
    public void testClear()
    {
        myLinkedList.add("Entry");
        myLinkedList.add("Entry2");
        myLinkedList.add("Entry3");

        assertEquals(myLinkedList.getLength(), 3);
        assertEquals(myLinkedList.getEntry(1), "Entry2");

        myLinkedList.clear();
        assertEquals(myLinkedList.getLength(), 0);

        Exception exception = null;
        try
        {
            assertEquals(myLinkedList.getEntry(1), "Entry");
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }


    /**
     * Tests LinkedList.getEntry(int) by ensuring the correct entry is returned
     * and invalid indexes can't be retrieved
     */
    public void testGetEntry()
    {
        myLinkedList.add("Entry2");
        myLinkedList.add("Entry1");
        myLinkedList.add("Entry4");
        myLinkedList.add("Entry3");

        Exception exception = null;
        try
        {
            assertEquals(myLinkedList.getEntry(10), "Entry");
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }
        assertNotNull(exception);

        exception = null;
        try
        {
            assertEquals(myLinkedList.getEntry(-1), "Entry");
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }
        assertNotNull(exception);

        assertEquals(myLinkedList.getEntry(0), "Entry2");
        assertEquals(myLinkedList.getEntry(2), "Entry4");

    }


    /**
     * Tests LinkedList.replace(int, T) by ensuring invalid indexes can't be
     * replaced and the correct nodes are replaced
     */
    public void testReplace()
    {
        myLinkedList.add("Entry2");
        myLinkedList.add("Entry1");
        myLinkedList.add("Entry4");
        myLinkedList.add("Entry3");

        Exception exception = null;
        try
        {
            myLinkedList.replace(-1, "weoigiwoe");
            myLinkedList.replace(100, "weoigiwoe");
            myLinkedList.replace(4, "weoigiwoe");
           

        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        myLinkedList.replace(0, "word");
        assertEquals(myLinkedList.getEntry(0), "word");
        myLinkedList.replace(1, "name");
        assertEquals(myLinkedList.getEntry(1), "name");

    }


    /**
     * Tests LinkedList.toArray() by ensuring the correct array is returned
     */
    public void testToArray()
    {
        myLinkedList.add("Entry2");
        myLinkedList.add("Entry1");
        myLinkedList.add("Entry4");
        myLinkedList.add("Entry3");
        Object[] array =
        { "Entry2", "Entry1", "Entry4", "Entry3" };
        assertTrue(Arrays.equals(myLinkedList.toArray(), array));
        Object[] array2 =
        { "Entry1", "Entry4", "Entry3" };
        assertFalse(Arrays.equals(myLinkedList.toArray(), array2));
    }


    /**
     * Tests LinkedList.sort(Comparator) by ensuring the list is sorted by the
     * confines of the Comparator. A Comparator that sorts Strings in a
     * descending manner is used
     */
    public void testSort()
    {
        myLinkedList.add("Entry2");
        myLinkedList.add("Entry1");
        myLinkedList.add("Entry4");
        myLinkedList.add("Entry3");

        myLinkedList.sort(new StringComparator());

        assertEquals(myLinkedList.getEntry(0), "Entry4");
        assertEquals(myLinkedList.getEntry(1), "Entry3");
        assertEquals(myLinkedList.getEntry(2), "Entry2");
        assertEquals(myLinkedList.getEntry(3), "Entry1");

        myLinkedList.clear();

        myLinkedList.add("Entry1");
        myLinkedList.add("Entry2");
        myLinkedList.add("Entry3");
        myLinkedList.add("Entry4");

        myLinkedList.sort(new StringComparator());

        assertEquals(myLinkedList.getEntry(0), "Entry4");
        assertEquals(myLinkedList.getEntry(1), "Entry3");
        assertEquals(myLinkedList.getEntry(2), "Entry2");
        assertEquals(myLinkedList.getEntry(3), "Entry1");
    }


    /**
     * Tests LinkedList.contains(T) by ensuring the correct truth value is
     * returned
     */
    public void testContains()
    {
        assertFalse(myLinkedList.contains("Entry"));

        myLinkedList.add("Entry");
        assertTrue(myLinkedList.contains("Entry"));
        assertFalse(myLinkedList.contains("Entry2"));

        myLinkedList.add("Entry2");
        myLinkedList.add("Entry3");

        assertTrue(myLinkedList.contains("Entry"));
        assertTrue(myLinkedList.contains("Entry2"));
        assertFalse(myLinkedList.contains("Entry4"));
    }


    /**
     * Tests LinkedList.equals(Object) by ensuring the correct truth value is
     * returned when testing with
     * Itself
     * Null
     * A non-LinkedList object
     * An empty LinkedList
     * A LinkedList with a different amount of elements
     * A LinkedList with elements in a different order
     * An equivalent LinkedList
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals()
    {
        myLinkedList.add("Entry1");
        myLinkedList.add("Entry2");

        LinkedList<String> testLinkedList = null;

        // With itself
        assertTrue(myLinkedList.equals(myLinkedList));

        // With null
        assertFalse(myLinkedList.equals(testLinkedList));

        // With a String object
        assertFalse(myLinkedList.equals("Not a LinkedList"));

        // With an empty LinkedList
        testLinkedList = new LinkedList<String>();
        assertFalse(myLinkedList.equals(testLinkedList));

        // With an unequal amount of elements
        testLinkedList.add("Entry2");
        assertFalse(myLinkedList.equals(testLinkedList));

        // With the wrong elements
        testLinkedList.add("Entry2");
        assertFalse(myLinkedList.equals(testLinkedList));

        // With the right elements in the wrong order
        testLinkedList.remove(0);
        testLinkedList.add("Entry1");
        assertFalse(myLinkedList.equals(testLinkedList));

        // With the right elements in the right order
        testLinkedList.remove(0);
        testLinkedList.add("Entry2");
        assertTrue(myLinkedList.equals(testLinkedList));

    }

    /**
     * Used for testing LinkedList.sort(Comparator)
     * 
     * @author Darren Hsu (darrenshu)
     * @author Nami Jain (jainnami)
     * @author Sam Shumaker (sshumak)
     * @author Vivan Verma (vivanv)
     * @version 12/01/2023
     */
    private class StringComparator implements Comparator<String>
    {

        /**
         * Just returns an inverted comparison between the two Strings
         * 
         * @param string1
         *            first String being compared
         * @param string2
         *            second String being compared
         * @return Negative comparison between string1 and string2 as sort
         *         should be descending
         */
        @Override
        public int compare(String string1, String string2)
        {
            return -string1.compareTo(string2);
        }

    }
}
