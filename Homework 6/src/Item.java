/*
Anthony Pizzulli
111990335
R08
 */
import java.io.Serializable;
public class Item implements Serializable {

    private String itemCode;
    private String name;
    private int qtyInStore;
    private int averageSalesPerDay;
    private int onOrder;
    private int arrivalDay;
    private double price;

    /**
     * Brief: Default constructor for Item class
     */
    public Item(){

    }

    /**
     * Brief: Constructor for Item class, initializes each instance variable
     * @param i String: itemCode
     * @param n String: name
     * @param q int: qtyInStore
     * @param a int: averageSalesPerDay
     * @param o int: onOrder
     * @param aD int: arrivalDay
     * @param p double: price
     */
    public Item( String i, String n, int q, int a, int o, int aD, double p){
        this.itemCode = i;
        this.name = n;
        this.qtyInStore = q;
        this.averageSalesPerDay = a;
        this.onOrder = o;
        this.arrivalDay = aD;
        this.price = p;
    }

    /**
     * Brief: Setter method for itemCode instance variable
     * @param itemCode String: updated value
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }


    /**
     * Brief: Getter method for itemCode instance variable
     * @return String: itemCode of given Item
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Brief: Getter method for name instance variable
     * @return String: name of given Item
     */
    public String getName() {
        return name;
    }

    /**
     * Brief: Setter method for name instance variable
     * @param name String: Updated value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Brief: Getter method for qtyInStore instance variable
     * @return int: qtyInStore of given Item
     */
    public int getQtyInStore() {
        return qtyInStore;
    }

    /**
     * Brief: Setter method for qtyInStore instance variable
     * @param qtyInStore int: Updated value
     */
    public void setQtyInStore(int qtyInStore) {
        this.qtyInStore = qtyInStore;
    }

    /**
     * Brief: Getter method for averageSalesPerDay instance variable
     * @return int: averageSalesPerDay of given Item
     */
    public int getAverageSalesPerDay() {
        return averageSalesPerDay;
    }

    /**
     * Brief: Setter method for averageSalesPerDay instance variable
     * @param averageSalesPerDay int: Updated value
     */
    public void setAverageSalesPerDay(int averageSalesPerDay) {
        this.averageSalesPerDay = averageSalesPerDay;
    }

    /**
     * Brief: Getter method for onOrder instance variable
     * @return int: onOrder of given Item
     */
    public int getOnOrder() {
        return onOrder;
    }

    /**
     * Brief: Setter method for onOrder instance variable
     * @param onOrder int: Updated value
     */
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    /**
     * Brief: Getter method for arrivalDay instance variable
     * @return int: arrivalDay of given Item
     */
    public int getArrivalDay() {
        return arrivalDay;
    }

    /**
     * Brief: Setter method for arrivalDay instance variable
     * @param arrivalDay int: Updated value
     */
    public void setArrivalDay(int arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    /**
     * Brief: Getter method for
     * @return double: price of given Item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Brief: Setter method for price instance variable
     * @param price double: Updated value
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * Brief: toString method for Item class which compiles a String display of the given Item's instance variables
     * @return String: Neatly formatted representation of the given Item
     */
    @ Override public String toString(){

       String returnString = String.format("%-9s   %-21s%-11d%2d%8.2f%11d%16d\n", this.getItemCode(), this.getName(), this.getQtyInStore(), this.getAverageSalesPerDay(), this.getPrice(), this.getOnOrder(), this.getArrivalDay());

        return returnString;
    }

}



