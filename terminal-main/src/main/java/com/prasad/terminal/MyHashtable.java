package com.prasad.terminal;

import java.io.*;
import java.util.*;

/**
 * MyHashtable collision list.
 */
class MyHashtableEntry<K,V> {
    int hash;
    K key;
    V value;
    MyHashtableEntry<K,V> next;
    MyHashtableEntry<K,V> previous;

    @Override
    protected MyHashtableEntry<K,V> clone() {
        MyHashtableEntry<K,V> entry = new MyHashtableEntry<K,V>();
        entry.hash = hash;
        entry.key = key;
        entry.value = value;
        entry.next = (next != null) ? next.clone() : null;
        return entry;
    }
}

/**
 * This MyHashtable is similar to the java.util.Hashtable. The difference here is
 * that this myHashtable can be navigated with first, next, previous, last, etc. methods.
 *
 * This class implements a hashtable, which maps keys to values. Any
 * non-<code>null</code> object can be used as a key or as a value.
 * <p>
 * To successfully store and retrieve objects from a hashtable, the
 * objects used as keys must implement the <code>hashCode</code>
 * method and the <code>equals</code> method.
 * <p>
 * An instance of <code>MyHashtable</code> has two parameters that
 * affect its efficiency: its <i>capacity</i> and its <i>load
 * factor</i>. The load factor should be between 0.0 and 1.0. When
 * the number of entries in the hashtable exceeds the product of the 
 * load factor and the current capacity, the capacity is increased by 
 * calling the <code>rehash</code> method. Larger load factors use 
 * memory more efficiently, at the expense of larger expected time 
 * per lookup. 
 * <p>
 * If many entries are to be made into a <code>MyHashtable</code>, 
 * creating it with a sufficiently large capacity may allow the 
 * entries to be inserted more efficiently than letting it perform 
 * automatic rehashing as needed to grow the table. 
 * <p>
 * This example creates a hashtable of numbers. It uses the names of 
 * the numbers as keys:
 * <p><blockquote><pre>
 *     MyHashtable numbers = new MyHashtable();
 *     numbers.put("one", new Integer(1));
 *     numbers.put("two", new Integer(2));
 *     numbers.put("three", new Integer(3));
 * </pre></blockquote>
 * <p>
 * To retrieve a number, use the following code:
 * <p><blockquote><pre>
 *     Integer n = (Integer)numbers.get("two");
 *     if (n != null) {
 *         System.out.println("two = " + n);
 *     }
 * </pre></blockquote>
 *
 * @author Arthur van Hoff
 * @version 1.41, 01/28/97
 * @see     java.lang.Object#equals(java.lang.Object)
 * @see     java.lang.Object#hashCode()
 * @see     com.prasad.terminal.MyHashtable#rehash()
 * @since JDK1.0
 */
public class MyHashtable<K,V> extends Dictionary<K,V> implements Cloneable, java.io.Serializable {
    /**
     * The hash table data.
     */
    private transient MyHashtableEntry<K,V> table[];

    /**
     * The total number of entries in the hash table.
     */
    private transient int count;

    /**
     * Rehashes the table when count exceeds this threshold.
     */
    private int threshold;

    /**
     * The load factor for the hashtable.
     */
    private float loadFactor;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 1421746759512286392L;

    /**
     * Constructs a new, empty hashtable with the specified initial
     * capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hashtable.
     * @param      loadFactor        a number between 0.0 and 1.0.
     * @exception IllegalArgumentException  if the initial capacity is less
     *               than or equal to zero, or if the load factor is less than
     *               or equal to zero.
     * @since JDK1.0
     */
    public MyHashtable(int initialCapacity, float loadFactor) {
        if ((initialCapacity <= 0)
            || (loadFactor <= 0.0)) {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        table = new MyHashtableEntry[initialCapacity];
        threshold = (int) (initialCapacity * loadFactor);
    }

    /**
     * Constructs a new, empty hashtable with the specified initial capacity
     * and default load factor.
     *
     * @param   initialCapacity   the initial capacity of the hashtable.
     * @since JDK1.0
     */
    public MyHashtable(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    /**
     * Constructs a new, empty hashtable with a default capacity and load
     * factor.
     *
     * @since JDK1.0
     */
    public MyHashtable() {
        this(101, 0.75f);
    }

    /**
     * Returns the number of keys in this hashtable.
     *
     * @return the number of keys in this hashtable.
     * @since JDK1.0
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Tests if this hashtable maps no keys to values.
     *
     * @return  <code>true</code> if this hashtable maps no keys to values;
     *          <code>false</code> otherwise.
     * @since JDK1.0
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns an enumeration of the keys in this hashtable.
     *
     * @return an enumeration of the keys in this hashtable.
     * @see     java.util.Enumeration
     * @see     com.prasad.terminal.MyHashtable#elements()
     * @since JDK1.0
     */
    @Override
    public synchronized Enumeration<K> keys() {
        return new MyHashtableEnumerator(table, true);
    }

    /**
     * Returns an enumeration of the values in this hashtable.
     * Use the Enumeration methods on the returned object to fetch the elements
     * sequentially.
     *
     * @return an enumeration of the values in this hashtable.
     * @see     java.util.Enumeration
     * @see    com.prasad.terminal.MyHashtable#keys()
     * @since JDK1.0
     */
    @Override
    public synchronized Enumeration<V> elements() {
        return new MyHashtableEnumerator(table, false);
    }

    /**
     * Tests if some key maps into the specified value in this hashtable.
     * This operation is more expensive than the <code>containsKey</code>
     * method.
     *
     * @param      value   a value to search for.
     * @return     <code>true</code> if some key maps to the
     *             <code>value</code> argument in this hashtable;
     *             <code>false</code> otherwise.
     * @exception NullPointerException  if the value is <code>null</code>.
     * @see       com.prasad.terminal.MyHashtable#containsKey(java.lang.Object)
     * @since JDK1.0
     */
    public synchronized boolean contains(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        MyHashtableEntry tab[] = table;
        for (int i = tab.length; i-- > 0; ) {
            for (MyHashtableEntry e = tab[i]; e != null; e = e.next) {
                if (e.value.equals(value))
                    return true;
            }
        }
        return false;
    }

    /**
     * Tests if the specified object is a key in this hashtable.
     *
     * @param   key   possible key.
     * @return  <code>true</code> if the specified object is a key in this
     *          hashtable; <code>false</code> otherwise.
     * @see    com.prasad.terminal.MyHashtable#contains(java.lang.Object)
     * @since JDK1.0
     */
    public synchronized boolean containsKey(K key) {
        MyHashtableEntry<K,V> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (MyHashtableEntry<K,V> e = tab[index]; e != null; e = e.next) {
            if ((e.hash == hash)
                && e.key.equals(key))
                return true;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped in this hashtable.
     *
     * @param   key   a key in the hashtable.
     * @return the value to which the key is mapped in this hashtable;
     *          <code>null</code> if the key is not mapped to any value in
     *          this hashtable.
     * @see    com.prasad.terminal.MyHashtable#put(java.lang.Object, java.lang.Object)
     * @since JDK1.0
     */
    @Override
    public synchronized V get(Object key) {
        MyHashtableEntry<K,V> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (MyHashtableEntry<K,V> e = tab[index]; e != null; e = e.next) {
            if ((e.hash == hash)
                && e.key.equals(key))
                return e.value;
        }
        return null;
    }

    private int currentIndex;
    private MyHashtableEntry<K,V> currentEntry;

    public synchronized V first() {
        if (table != null) {
            int iMax = table.length;
            for (int i = iMax - 1; i >= 0; i--) {
                if (table[i] != null
                    && table[i].value != null) {
                    currentIndex = i;
                    currentEntry = table[i];
                    return table[i].value;
                }
            }
        }
        return null;
    }

    public synchronized V last() {
        if (table == null)
            return null;
        else {
            int iMax = table.length;
            for (int i = 0; i < iMax; i++) {
                if (table[i] != null) {
                    currentIndex = i;
                    currentEntry = table[i];
                    while (currentEntry.next != null)
                        currentEntry = currentEntry.next;
                    currentIndex = i;
                    return currentEntry.value;
                }
            }
            return null;
        }
    }

    public synchronized V next() {
        if (table == null)
            return null;
        else {
            if (currentEntry != null
                && currentEntry.next != null) {
                currentEntry = currentEntry.next;
                return currentEntry.value;
            } else {
                for (int i = currentIndex - 1; i >= 0; i--) {
                    MyHashtableEntry entry = table[i];
                    if (entry == null)
                        continue;
                    currentIndex = i;
                    currentEntry = entry;
                    return currentEntry.value;
                }
                return null;
            }
        }
    }

    public synchronized V previous() {
        if (table == null)
            return null;
        else {
            if (currentEntry != null) {
                if (currentEntry.previous != null) {
                    currentEntry = currentEntry.previous;
                    return currentEntry.value;
                } else {
                    int iMax = table.length;
                    for (int i = currentIndex + 1; i < iMax; i++) {
                        MyHashtableEntry entry = table[i];
                        if (entry == null)
                            continue;
                        currentEntry = entry;
                        while (currentEntry.next != null)
                            currentEntry = currentEntry.next;
                        currentIndex = i;
                        return currentEntry.value;
                    }
                    return null;
                }
            } else
                return null;
        }
    }

    public synchronized V current() {
        if (table == null)
            return null;
        else {
            if (currentEntry != null)
                return currentEntry.value;
            else
                return null;
        }
    }

    /**
     * Rehashes the contents of the hashtable into a hashtable with a
     * larger capacity. This method is called automatically when the
     * number of keys in the hashtable exceeds this hashtable's capacity
     * and load factor.
     *
     * @since JDK1.0
     */
    protected void rehash() {
        int oldCapacity = table.length;
        MyHashtableEntry oldTable[] = table;

        int newCapacity = oldCapacity * 2 + 1;
        MyHashtableEntry newTable[] = new MyHashtableEntry[newCapacity];

        threshold = (int) (newCapacity * loadFactor);
        table = newTable;

        //System.out.println("rehash old=" + oldCapacity + ", new=" + newCapacity + ", thresh=" + threshold + ", count=" + count);

        for (int i = oldCapacity; i-- > 0; ) {
            for (MyHashtableEntry old = oldTable[i]; old != null; ) {
                MyHashtableEntry e = old;
                old = old.next;

                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
                e.next = newTable[index];
                newTable[index] = e;
            }
        }
    }

    /**
     * Maps the specified <code>key</code> to the specified
     * <code>value</code> in this hashtable. Neither the key nor the
     * value can be <code>null</code>.
     * <p>
     * The value can be retrieved by calling the <code>get</code> method
     * with a key that is equal to the original key.
     *
     * @param      key     the hashtable key.
     * @param      value   the value.
     * @return the previous value of the specified key in this hashtable,
     *             or <code>null</code> if it did not have one.
     * @exception NullPointerException  if the key or value is
     *               <code>null</code>.
     * @see     java.lang.Object#equals(java.lang.Object)
     * @see    com.prasad.terminal.MyHashtable#get(java.lang.Object)
     * @since JDK1.0
     */
    @Override
    public synchronized V put(K key, V value) {
        // Make sure the value is not null
        if (value == null)
            return remove(key);

        // Makes sure the key is not already in the hashtable.
        MyHashtableEntry<K,V> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (MyHashtableEntry<K,V> e = tab[index]; e != null; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                V old = e.value;
                e.value = value;
                return old;
            }
        }

        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded
            rehash();
            return put(key, value);
        }

        // Creates the new entry.
        MyHashtableEntry e = new MyHashtableEntry();
        e.hash = hash;
        e.key = key;
        e.value = value;
        e.next = tab[index];
        if (tab[index] != null)
            tab[index].previous = e;
        tab[index] = e;
        count++;
        return null;
    }

	/**
     * Removes the key (and its corresponding value) from this
     * hashtable. This method does nothing if the key is not in the hashtable.
     *
     * @param   key   the key that needs to be removed.
     * @return the value to which the key had been mapped in this hashtable,
     *          or <code>null</code> if the key did not have a mapping.
     * @since JDK1.0
     */
	@Override
    public synchronized V remove(Object key) {
        MyHashtableEntry<K,V> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (MyHashtableEntry<K,V> e = tab[index], prev = null; e != null; prev = e, e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                if (prev != null) {
                    if (e == currentEntry)
                        currentEntry = ((e.next != null) ? e.next : prev);

                    prev.next = e.next;
                    e.next.previous = prev;
                } else {
                    if (e == currentEntry)
                        currentEntry = e.next;
                    tab[index] = e.next;
                }
                count--;
                return e.value;
            }
        }
        return null;
    }

    /**
     * Clears this hashtable so that it contains no keys.
     *
     * @since JDK1.0
     */
    public synchronized void clear() {
        MyHashtableEntry<K,V> tab[] = table;
        for (int index = tab.length; --index >= 0; )
            tab[index] = null;
        count = 0;
        currentEntry = null;
    }

    /**
     * Creates a shallow copy of this hashtable. The keys and values
     * themselves are not cloned.
     * This is a relatively expensive operation.
     *
     * @return a clone of the hashtable.
     * @since JDK1.0
     */
    @Override
    public synchronized MyHashtable<K,V> clone() {
        try {
            MyHashtable<K,V> t = (MyHashtable) super.clone();
            t.table = new MyHashtableEntry[table.length];
            for (int i = table.length; i-- > 0; ) {
                t.table[i] = (table[i] != null)
                    ? table[i].clone() : null;
            }
            return t;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    /**
     * Returns a rather long string representation of this hashtable.
     *
     * @return a string representation of this hashtable.
     * @since JDK1.0
     */
    @Override
    public synchronized String toString() {
        int max = size() - 1;
        StringBuffer buf = new StringBuffer();
        Enumeration k = keys();
        Enumeration e = elements();
        buf.append("{");

        for (int i = 0; i <= max; i++) {
            String s1 = k.nextElement().toString();
            String s2 = e.nextElement().toString();
            buf.append(s1 + "=" + s2);
            if (i < max)
                buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    /**
     * WriteObject is called to save the state of the hashtable to a stream.
     * Only the keys and values are serialized since the hash values may be
     * different when the contents are restored.
     * iterate over the contents and write out the keys and values.
     */
    private synchronized void writeObject(java.io.ObjectOutputStream s)
        throws IOException {
        // Write out the length, threshold, loadfactor
        s.defaultWriteObject();

        // Write out length, count of elements and then the key/value objects
        s.writeInt(table.length);
        s.writeInt(count);
        for (int index = table.length - 1; index >= 0; index--) {
            MyHashtableEntry entry = table[index];

            while (entry != null) {
                s.writeObject(entry.key);
                s.writeObject(entry.value);
                entry = entry.next;
            }
        }
    }

    /**
     * readObject is called to restore the state of the hashtable from
     * a stream.  Only the keys and values are serialized since the
     * hash values may be different when the contents are restored.
     * Read count elements and insert into the hashtable.
     */
    private synchronized void readObject(java.io.ObjectInputStream s)
        throws IOException, ClassNotFoundException {
        // Read in the length, threshold, and loadfactor
        s.defaultReadObject();

        // Read the original length of the array and number of elements
        int origlength = s.readInt();
        int elements = s.readInt();

        // Compute new size with a bit of room 5% to grow but
        // No larger than the original size.  Make the length
        // odd if it's large enough, this helps distribute the entries.
        // Guard against the length ending up zero, that's not valid.
        int length = (int) (elements * loadFactor) + (elements / 20) + 3;
        if (length > elements && (length & 1) == 0)
            length--;
        if (origlength > 0 && length > origlength)
            length = origlength;

        table = new MyHashtableEntry[length];
        count = 0;

        // Read the number of elements and then all the key/value objects
        for (; elements > 0; elements--) {
            K key = (K)s.readObject();
            V value = (V)s.readObject();
            put(key, value);
        }
    }
}

/**
 * A hashtable enumerator class.  This class should remain opaque
 * to the client. It will use the Enumeration interface.
 */
class MyHashtableEnumerator implements Enumeration {
    boolean keys;
    int index;
    MyHashtableEntry table[];
    MyHashtableEntry entry;

    MyHashtableEnumerator(MyHashtableEntry table[], boolean keys) {
        this.table = table;
        this.keys = keys;
        this.index = table.length;
    }

    @Override
    public boolean hasMoreElements() {
        if (entry != null)
            return true;

        while (index-- > 0) {
            if ((entry = table[index]) != null)
                return true;
        }
        return false;
    }

    @Override
    public Object nextElement() {
        if (entry == null) {
            while ((index-- > 0)
                && ((entry = table[index]) == null))
                ;
        }
        if (entry != null) {
            MyHashtableEntry e = entry;
            entry = e.next;
            return keys ? e.key : e.value;
        }
        throw new NoSuchElementException("MyHashtableEnumerator");
    }
}
