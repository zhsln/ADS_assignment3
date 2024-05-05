/**
 * Class that created for test of MyHashTable class.
 */
public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

       // Creating and adding students to hash table.
        for (int i = 1; i <= 10000; i++) {
            String code = "Code" + i;
            MyTestingClass key = new MyTestingClass(code);

            String name = "Name" + i;
            String surname = "Surname" + i;
            String group = "Group" + (i % 10 + 1); // 10 groups for testing.
            Student value = new Student(name, surname, group);

            table.put(key, value);
        }

        table.printDistribution();
    }
}