/**
 * This class represents the value part of a key-value pair in a hash table.
 * It encapsulates a student's information including their name, surname, and group.
 */
public class Student {
    String name;
    String surname;
    String group;

    /**
     * Constructs a new Student with the specified name, surname, and group.
     * @param name The first name of the student.
     * @param surname The surname of the student.
     * @param group The group to which the student belongs.
     */
    public Student(String name, String surname, String group) {
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    /**
     * Compares this student to the specified object.
     * The result is true if and only if the argument is not null and is a Student object
     * that has the same name, surname, and group as this object.
     * @param o the object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        if (!name.equals(student.name)) return false;
        if (!surname.equals(student.surname)) return false;
        return group.equals(student.group);
    }

    /**
     * Returns a hash code for this student.
     * The hash code is computed as a sum of the hash codes of the student's name, surname,
     * and group, using 31 as a multiplier, which is a common choice in hash code generation.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + group.hashCode();
        return result;
    }

    /**
     * Returns a string representation of the student, containing the name, surname, and group.
     * @return a string representation of the student.
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}