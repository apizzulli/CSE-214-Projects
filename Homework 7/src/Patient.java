/*
Anthony Pizzulli
111990335
R08
 */
import java.io.Serializable;

public class Patient implements Serializable {

    private String name;
    private String organ;
    private int age;
    private BloodType bloodType;
    private int ID;
    private boolean isDonor;
    private int numConnections;

    /**
     * Brief: Default constructor for Patient class
     */
    public Patient(){

    }

    /**
     * Brief: Alternate constructor for Patient class
     * @param n String: name instance variable
     * @param o String: organ instance variable
     * @param a int: age instance variable
     * @param b BloodType: bloodType instance variable
     * @param i int: ID instance variable
     * @param bi boolean: isDonor instance variable
     */
    public Patient( String n, String o, int a, BloodType b, int i, boolean bi){
        this.name = n;
        this.organ = o;
        this.age = a;
        this.bloodType = b;
        this.ID = i;
        this.isDonor = bi;
    }

    /**
     * Brief: Additional alternate constructor for Patient class (includes numConnections instance variable)
     * @param n String: name instance variable
     * @param o String: organ instance variable
     * @param a int: age instance variable
     * @param b BloodType: bloodType instance variable
     * @param i int: ID instance variable
     * @param bi boolean: isDonor instance variable
     * @param nC int: numConnections instance variable
     */
    public Patient( String n, String o, int a, BloodType b, int i, boolean bi, int nC){
        this.name = n;
        this.organ = o;
        this.age = a;
        this.bloodType = b;
        this.ID = i;
        this.isDonor = bi;
        this.numConnections = nC;
    }

    /**
     * Getter method for name instance variable
     * @return String: Given Patient's name
     */
    public String getName() {
        return name;
    }

    /**
     * Brief: Setter method for name instance variable
     * @param name String: Updated name for given Patient
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Brief: Getter method for organ instance variable
     * @return String: Given Patient's organ to be donated
     */
    public String getOrgan() {
        return organ;
    }

    /**
     * Brief: Setter method for organ instance variable
     * @param organ String: Updated organ for given Patient
     */
    public void setOrgan(String organ) {
        this.organ = organ;
    }

    /**
     * Brief: Getter method for age instance variable
     * @return int: Given Patient's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Brief: Setter method for age instance variable
     * @param age int: Updated age for given Patient
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Brief: Getter method for ID instance variable
     * @return int: Given Patient's ID number
     */
    public int getID() {
        return ID;
    }

    /**
     * Brief: Setter method for ID instance variable
     * @param ID int: Updated ID number for given Patient
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Brief: Getter method for isDonor instance variable
     * @return boolean: True if the given Patient is an organ donor and false otherwise
     */
    public boolean isDonor(){
        return(this.isDonor);
    }

    /**
     * Brief: Getter method for bloodType instance variable
     * @return BloodType: Given Patient's blood type (returns BloodType type)
     */
    public BloodType getBloodType(){
        return this.bloodType;
    }


    /**
     * Brief: Getter method for numConnections instance variable
     * @return int: Number of connections the given Patient has to other recipients/donors
     */
    public int getNumConnections(){
        return this.numConnections;
    }

    /**
     * Brief: Setter method for numConnections instance variable
     * @param n int: Updated numConnections for given Patient
     */
    public void setNumConnections( int n){
        this.numConnections = n;
    }


    /**
     * Brief: compareTo method for Patient class, uses ID numbers for comparison
     * @param o Object: Patient that the given Patient is to be compared to
     * @return int: 0 if the IDs are equal, 1 if the given Patient's ID is greater and -1 otherwise
     */
     public int compareTo(Object o){
        Patient p = (Patient)o;
        int r = 0;
        if( this.ID < p.getID() )
            r = -1;
        else if( this.ID == p.getID() )
            r = 0;
        else if( this.ID > p.getID() )
            r = 1;
        return r;
    }

    /**
     * Brief: toString method for Patient class
     * @return String: Formatted instance variables of given Patient to be used in a table
     */
    public String toString(){
        String rS = String.format("   %d  | %-19s| %d  |  %-13s|      %-6s| ", this.ID, this.name, this.age, this.organ, this.bloodType.getBloodType() );
        return rS;
    }
}
