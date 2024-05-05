/**
 * This class represents a key in a hash table.
 * It is designed to hold a unique code that acts as the key for hash table entries.
 */
public class MyTestingClass {

    /**
     * The code representing the key value.
     */
    private String code;

    /**
     * Constructs a new instance of MyTestingClass with the specified code.
     * @param code The unique code that represents this key.
     */
    public MyTestingClass(String code) {
        this.code = code;
    }

    /**
     * Generates a hash code for this key.
     * The hash code is generated using a custom method.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        if (code != null)
            for (int i = 0; i < code.length(); i++)
                hash = 31 * hash + code.charAt(i);

        return hash;
    }
}