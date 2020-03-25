import sun.awt.image.ImageWatched;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Restaurant extends LinkedList<Customer>{

    private boolean isFull;
    private int maxCustomers;

    public Queue <Customer> queue = new LinkedList<>();


    public Restaurant(){
    }


    public void enqueue( Customer c ){
        queue.add(c);
    }

    public Customer dequeue(){
        Customer c = queue.remove();
        return c;
    }


    public Customer peek(){
        Customer d = queue.element();
        return d;
    }


    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public String toString(){
        String returnS = "{";
        for( Customer c: queue){//for every customer in the restaurant
            returnS+=c.toString() + ", ";
        }
        returnS+="}\n";
        return returnS;
    }

    public int size(){
        return queue.size();
    }

    public void timeIncrement( ){
        for( Customer c: queue){
            c.setTempTime( c.getTempTime() - 5 );
        }
    }


    public String timeCheckQueue( ){
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
    public int getCustomersNumbers(){
        int u = 0;
        for( Customer c: queue){
            u = c.getCustomerNumber();
        }
        return u;
    }

    public void removeFinished(){
        for( Customer c: queue){
            if( c.getTimeToServe() == 0){
                queue.remove(c);
            }
        }
    }


    /*public ArrayList<Restaurant> timeCheck( Restaurant r ){
        String str = "";
        for ( Customer c: queue ){
            if( c.getTimeToServe() == 0 && queue.peek()!=null ){
                str += "Customer #" + c.getCustomerNumber() + " has enjoyed their food! $" + c.getPriceOfFood() + "profit." ;
                queue.remove();
            }
        }
        return str;
    }*/
}


