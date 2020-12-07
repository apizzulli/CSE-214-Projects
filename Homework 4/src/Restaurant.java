/*
Anthony Pizzulli
111990335
R08
 */
import java.util.LinkedList;
import java.util.Queue;

public class Restaurant extends LinkedList<Customer>{

    private boolean isFull;
    private int maxCustomers;


    public Queue <Customer> queue = new LinkedList<>();


    /**
     * Brief: This is the default constructor for the Restaurant class
     */
    public Restaurant(){
    }


    /**
     * Brief: Enqueue method for the Restaurant class, which calls upon the add method given in the LinkedList Java API
     * @param c Customer: The customer to be enqueued to the given Restaurant
     */
    public void enqueue( Customer c ){
        queue.add(c);
    }

    /**
     * Brief: Dequeue method for the Restaurant class, which calls upon the remove method given in the LinkedList Java API
     * @return Customer: The Customer at the front of the queue which has now been removed
     */
    public Customer dequeue(){
        Customer c = queue.remove();
        return c;
    }

    /**
     * Brief: Peek method for the Restaurant class
     * @return Customer: The Customer at the front of the queue, to be removed next
     */
    public Customer peek(){
        Customer d = queue.element();
        return d;
    }

    /**
     * Brief: isEmpty method for the Restaurant class, which determines whether or not the given Restaurant contains any customers
     * @return boolean: True if the given Restaurant contains 0 Customers and false otherwise
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    /**
     * Brief: toString method for the Restaurant class
     * @return String: A neatly formatted representation of the given Restaurant with each Customer accounted for
     */
    public String toString(){
        String returnS = "{";
        for( Customer c: queue){//for every customer in the restaurant
            returnS+=c.toString() + ", ";
        }
        returnS+="}\n";
        return returnS;
    }

    /**
     * Brief: Size method for the Restaurant class
     * @return int: The size (number of elements) of the given restaurant
     */
    public int size(){
        return queue.size();
    }

    /**
     * Brief: This method is used to decrement the remaining time for each Customer to finish eating (by 5 minutes)
     */
    public void timeIncrement( ){
        for( Customer c: queue){
            c.setTempTime( c.getTempTime() - 5 );
        }
    }


    /**
     * Brief: This method is used to determine which Customers are finished eating and if so, assemble the proper output String
     * @return String: The neatly formatted output for the given Customer when they are finished eating, consisting of
     *                 their customerNumber and priceOfFood
     */
    public String timeCheckQueue(){
        String s ="";

        if( queue.size() > 0) {
            for (Customer c : queue) {
                if (c.getTempTime() == 0) {
                    s += "Customer #" + c.getCustomerNumber() + " has enjoyed their food! $" + c.getPriceOfFood() + " profit.\n";
                }

            }
        }
        return s;
    }

}


