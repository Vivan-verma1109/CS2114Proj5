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
 * Individual point of data used in the LinkedList
 * 
 * @author Darren Hsu (darrenshu)
 * @author Nami Jain (jainnami)
 * @author Sam Shumaker (sshumak)
 * @author Vivan Verma (vivanv)
 * @version 12/01/2023
 * @param <T>
 *            The type of data being stored
 */
public class Node<T>
{

    private T data;
    private Node<T> next;

    /**
     * Create a new Node object.
     * 
     * @param data
     *            to create
     * @param next
     *            pointer
     */
    public Node(T data, Node<T> next)
    {
        this.data = data;
        this.next = next;
    }


    // ----------------------------------------------------------
    /**
     * Create a new Node object.
     * 
     * @param data
     *            to create with
     */
    public Node(T data)
    {
        this(data, null);
    }


    // ----------------------------------------------------------
    /**
     * set the next node
     * 
     * @param newNext
     *            node
     */
    public void setNextNode(Node<T> newNext)
    {
        this.next = newNext;
    }


    // ----------------------------------------------------------
    /**
     * getNextNode
     * 
     * @return next node
     */
    public Node<T> getNextNode()
    {
        return next;
    }


    // ----------------------------------------------------------
    /**
     * getData
     * 
     * @return the data at a point
     */
    public T getData()
    {
        return data;
    }


    // ----------------------------------------------------------
    /**
     * set data
     * 
     * @param data
     *            to be set
     */
    public void setData(T data)
    {
        this.data = data;
    }

}
