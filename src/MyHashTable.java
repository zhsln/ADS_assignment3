public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        /**
         * Constructor for creating a new HashNode.
         * @param key The key stored in this node.
         * @param value The value associated with the key.
         */
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;

    /**
     * Default number of chains.
     */
    private int M = 11;

    /**
     * Number of key-value pairs.
     */
    private int size;

    /**
     * Constructs an empty MyHashTable with a default number of chains.
     */
    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Constructs an empty MyHashTable with a specified number of chain.
     * @param M The number of chains.
     */
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Generates the hash code for a key.
     * @param key The key to hash.
     * @return The index in the bucket array.
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % M; // formula from the lecture.
    }

    /**
     * Inserts a key-value pair into the hash table.
     * @param key The key to insert.
     * @param value The value associated with the key.
     */
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;
    }

    /**
     * Retrieves a value associated with a key.
     * @param key The key whose value is to be fetched.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.value.equals(key))
                return head.value;

            head = head.next;
        }

        return null;
    }

    /**
     * Removes the node associated with the key.
     * @param key The key of the node to be removed.
     * @return The value associated with the removed key, or null if the key was not found.
     */
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key))
                break;

            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;

        size--;

        if (prev != null)
            prev.next = head.next;

        else
            chainArray[index] = head.next;

        return head.value;
    }

    /**
     * Checks if the hash table contains a specific value.
     * @param value The value to search for in the hash table.
     * @return true if the value is found, otherwise false.
     */
    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value))
                    return true;

                head = head.next;
            }
        }

        return false;
    }

    /**
     * Retrieves the key associated with a specific value.
     * @param value The value whose key is to be retrieved.
     * @return The key associated with the specified value, or null if the value is not found.
     */
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value))
                    return head.key;

                head = head.next;
            }
        }

        return null;
    }

    /**
     * Prints how many elements each bucket have.
     * Method was created for test.
     */
    public void printDistribution() {
        System.out.println("Distribution of elements in hash table buckets:");
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                count++;
                node = node.next;
            }

            System.out.println("Bucket " + (i + 1) + " has " + count + " elements.");
        }
    }
}