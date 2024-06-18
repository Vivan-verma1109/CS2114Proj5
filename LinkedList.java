
package prj5;

import java.util.Comparator;
import list.ListInterface;

/**
 * Stores objects in an ordered, linked fashion
 * 

 * @version 12/01/2023
 * @param <T>
 *            Type the LinkedList is storing
 */
public class LinkedList<T> implements ListInterface<T>
{

    private int size;
    private Node<T> head;

    /**
     * New LinkedList Object
     */
    public LinkedList()
    {
        head = null;
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * add an obj to list
     * 
     * @param obj
     *            to add
     */
    @Override
    public void add(T obj)
    {
        // check if the object is null
        if (obj == null)
        {
            throw new IllegalArgumentException("Object is null");
        }

        Node<T> current = head;

        // empty stack case
        if (isEmpty())
        {
            head = new Node<T>(obj);
        }

        // other cases
        else
        {
            while (current.getNextNode() != null)
            {
                current = current.getNextNode();
            }
            current.setNextNode(new Node<T>(obj));
        }
        size++;
    }


    // ----------------------------------------------------------
    /**
     * add an obj to list
     * 
     * @param obj
     *            to add
     * @param index
     *            where to add
     */
    @Override
    public void add(int index, T obj)
    {
        // obj is null
        if (obj == null)
        {
            throw new IllegalArgumentException("Object is null");
        }

        // check if the index is out of bounds
        if ((index < 0) || (index > size))
        {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node<T> current = head;

        // List is empty
        if (isEmpty())
        {
            head = new Node<T>(obj);
        }
        else
        {
            // Added to beginning of List
            if (index == 0)
            {
                Node<T> newNode = new Node<T>(obj);
                newNode.setNextNode(head);
                head = newNode;
            }
            // Added to the correct index
            else
            {
                int currentIndex = 0;
                while (current != null)
                {
                    if ((currentIndex + 1) == index)
                    {
                        Node<T> nextNext = current.getNextNode();
                        Node<T> newNode = new Node<T>(obj);
                        current.setNextNode(newNode);
                        newNode.setNextNode(nextNext);
                        break;

                    }
                    current = current.getNextNode();
                    currentIndex++;
                }
            }
        }
        size++;
    }


    // ----------------------------------------------------------
    /**
     * clear all
     */
    @Override
    public void clear()
    {
        head = null;
        size = 0;

    }


    // ----------------------------------------------------------
    /**
     * contains an entry or not
     * 
     * @param entry
     *            to check for
     */
    @Override
    public boolean contains(T entry)
    {
        // List is empty
        if (this.isEmpty())
        {
            return false;
        }
        // Item is at the front of the list
        if (head.getData().equals(entry))
        {
            return true;
        }
        // Item is somewhere else

        Node<T> next = head;
        while (next != null)
        {
            if (next.getData().equals(entry))
            {
                return true;
            }
            next = next.getNextNode();
        }

        // Item is not found
        return false;
    }


    // ----------------------------------------------------------
    /**
     * get entry at an index
     * 
     * @param index
     *            to remove for
     * @return T of what is removed
     */

    @Override
    public T getEntry(int index)
    {
        // Index is out of bounds
        if (index > size - 1 || index < 0)
        {
            throw new IndexOutOfBoundsException(
                "Linked list does not contain anything there");
        }

        // Beginning of List
        if (index == 0)
        {
            return head.getData();
        }

        // Anywhere else in the List
        Node<T> node = head;
        int traversed = 0;
        while (traversed != index)
        {
            node = node.getNextNode();
            traversed++;
        }
        return node.getData();
    }


    // ----------------------------------------------------------
    /**
     * get length
     * 
     * @return length of list
     */
    @Override
    public int getLength()
    {
        return size;
    }


    /**
     * check if it is empty or not
     * 
     * @return True if the LinkedList is empty
     */
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }


    // ----------------------------------------------------------
    /**
     * remove the node at a given index
     * 
     * @return The removed entry
     */
    @Override
    public T remove(int index)
    {
        // if the index is invalid or the list is empty
        if (index < 0 || index > size - 1 || head == null)
        {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        // If the searched index is the beginning of the list
        if (index == 0)
        {
            T data = head.getData();
            head = head.getNextNode();
            size--;
            return data;
        }

        // If the searched index is anywhere else
        Node<T> current = head;
        int currentIndex = 0;

        while (current.getNextNode() != null)
        {
            if ((currentIndex + 1) == index)
            {
                Node<T> storage = current.getNextNode();
                Node<T> newNext = storage.getNextNode();
                current.setNextNode(newNext);
                size--;
                return storage.getData();
            }
            current = current.getNextNode();
            currentIndex++;
        }

        // if the element was never found, this also handles empty case
        throw new IndexOutOfBoundsException("Index is out of bounds");
    }


    // ----------------------------------------------------------
    /**
     * remove a specific entry and return T or F
     * 
     * @param entry
     *            to remove
     * @return true if the removal was successful
     */
    public boolean remove(T entry)
    {

        if (head == null)
        {
            return false;
        }

        Node<T> current = head;

        // account for matching head
        if (entry.equals(current.getData()))
        {
            head = head.getNextNode();
            size--;
            return true;
        }

        if (getEntry(size - 1) == entry && contains(entry))
        {
            Node<T> temp = current.getNextNode();
            while (temp.getNextNode() != null)
            {
                temp = temp.getNextNode();
            }
            current.setNextNode(null);
            size--;
            return true;
        }

        // account for 2+ size
        while (size >= 2 && (current.getNextNode() != null))
        {
            if ((entry.equals(current.getNextNode().getData())))
            {
                if (current.getNextNode().getNextNode() != null)
                {
                    current.setNextNode(current.getNextNode().getNextNode());
                }
                size--;
                return true;
            }
            current = current.getNextNode();
        }

        // Object does not exist
        return false;
    }


    // ----------------------------------------------------------
    /**
     * return the size of the list
     * 
     * @return size
     */
    public int getSize()
    {
        return size;
    }


    /**
     * Replaces the element at position index with entry
     * 
     * @param index
     *            The position being replaced
     * @param entry
     *            The new element
     * @return The element that was replaced
     */
    @Override
    public T replace(int index, T entry)
    {

        // Invalid index
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        // Beginning of List
        if (index == 0)
        {
            head.setData(entry);
            return entry;
        }

        // Anywhere else in the list
        Node<T> temp = head;
        for (int i = 0; i < index; i++)
        {
            temp = temp.getNextNode();
        }
        temp.setData(entry);
        return entry;
    }


    /**
     * Converts the LinkedList to an array
     * 
     * @return The LinkedList in array form
     */
    @Override
    public Object[] toArray()
    {
        Object[] array = new Object[this.size];

        Node<T> current = head;
        int count = 0;
        while (current != null)
        {
            array[count] = current.getData();
            current = current.getNextNode();
            count++;
        }

        return array;
    }


    // ----------------------------------------------------------
    /**
     * Sorts the LinkedList in ascending order based on the compare
     * 
     * @param compare
     *            The Comparator that sorts the List
     */
    public void sort(Comparator<T> compare)
    {
        for (int i = 1; i < size; i++)
        {
            T key = this.getEntry(i);
            int j = i - 1;
            while (j >= 0 && compare.compare(this.getEntry(j), key) > 0)
            {
                this.replace(j + 1, this.getEntry(j));
                j--;
            }
            this.replace(j + 1, key);
        }
    }


    /**
     * Determines if obj is equal to the LinkedList
     * 
     * @return true if obj is a LinkedList that has all elements in the same
     *         order, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        // Check if the LinkedList is the same LinkedList
        if (obj == this)
        {
            return true;
        }
        // Null check
        if (obj == null)
        {
            return false;
        }
        // Class check
        if (this.getClass() == obj.getClass())
        {
            @SuppressWarnings("unchecked")
            LinkedList<T> other = ((LinkedList<T>)obj);

            // Size check
            if (other.getSize() == this.getSize())
            {
                Node<T> current = head;
                Node<T> otherCurrent = other.head;
                // All elements check
                while (current != null)
                {
                    if (!current.getData().equals(otherCurrent.getData()))
                    {
                        return false;
                    }
                    current = current.getNextNode();
                    otherCurrent = otherCurrent.getNextNode();
                }
                return true;
            }
        }

        return false;
    }

}
