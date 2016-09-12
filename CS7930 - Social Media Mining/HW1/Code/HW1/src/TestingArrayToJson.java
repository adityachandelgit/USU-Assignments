/**
 * Created by Aditya on 1/24/2016.
 */

class Person {

    String first_name;
    String last_name;

    public Person(String first, String last) {
        first_name = first;
        last_name = last;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}


public class TestingArrayToJson {

    Person p = new Person("Aditya", "C");
    Person p1 = new Person("Sam", "Daniels");




}
