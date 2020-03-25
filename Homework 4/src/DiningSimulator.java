import com.sun.deploy.xml.CustomParser;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sun.awt.image.ImageWatched;
import sun.plugin2.message.FocusTransitionEventMessage;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Scanner;
public class DiningSimulator {

    public DiningSimulator(){

    }
    private int chefs;
    private int duration;
    private double arrivalProb;
    private int maxCustomerSize;
    private int numRestaurants;
    private int customersLost;
    private int totalServiceTime;
    private int customersServed;
    private int profit;
    public static int avgTimeToReduce = 5;

    private static int randInt(int minVal, int maxVal){
        int r = (maxVal-minVal)+1;
        return (int)(Math.random()*r)+minVal;
    }

    public double simulate(){
        double d = 0.0;
        return d;
    }


    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        do{
            System.out.println( "Starting simulator...");
            DiningSimulator s = new DiningSimulator();
            System.out.println("Enter the number of restaurants: ");
            s.numRestaurants = Integer.parseInt(in.nextLine());
            System.out.println("Enter the maximum number of customers a restaurant can serve: ");
            s.maxCustomerSize = Integer.parseInt(in.nextLine());
            System.out.println("Enter the arrival probability of a customer: ");
            s.arrivalProb = Double.parseDouble(in.nextLine());
            System.out.println("Enter the number of chefs: ");
            s.chefs = Integer.parseInt(in.nextLine());
            System.out.println("Enter the number of simulation units: ");
            s.duration = Integer.parseInt(in.nextLine());

            ArrayList<Restaurant> restaurants = new ArrayList<>();
            Queue<Customer> customersInLine = new LinkedList<>();
            double numCustomersExpected = s.arrivalProb * 3 * s.duration;
            int customerRoll = 0;
            int numCounter = 0;
            int numCustThatLeft = 0;

            if(s.chefs != 3){
                int r = s.chefs -3;

                //if( r < 1 )
                    //timeReduction
                //timeReduction = r*5;
            }


            for(int i = 0; i < s.numRestaurants; i++){
                restaurants.add(new Restaurant());
            }



            for( int i = 1; i <= s.duration; i++) {//TIME COUNTER: EACH

                System.out.println("\nTime: " + i);


                ArrayList<Customer> temp = new ArrayList<>();
                for( Restaurant re: restaurants ) {
                    Queue<Customer> q = re.queue;
                    System.out.print(re.timeCheckQueue());
                    Iterator<Customer> iterator = q.iterator();
                    while( iterator.hasNext() ){
                        Customer customer = iterator.next();
                        if( customer.getTempTime() == 0 ){
                            temp.add(customer);
                            s.profit+=customer.getPriceOfFood();
                            s.customersServed++;
                            s.totalServiceTime += customer.getTimeToServe();
                        }
                    }
                    if( !(temp.isEmpty())  ){
                        for(int n = 0; n < temp.size(); n++){
                            Customer c = temp.get(n);
                            q.remove(c);
                        }
                    }
                }



                int restaurantIndex = 1;

                for (Restaurant r : restaurants) {
                    numCounter = 0;

                    int count = 0;
                    int j = 0;
                    double roll = 0;
                    while( count < 3){
                        roll = Math.random();
                        if (roll < s.arrivalProb){
                            j++;
                        }
                        count++;
                    }

                    while (numCounter < j) {//Loop to add customers to the Line based on their assigned restaurant
                        Customer c = new Customer();
                        Customer.setTotalCustomers(Customer.getTotalCustomers() + 1);
                        System.out.println("Customer #" + Customer.getTotalCustomers() + " has entered Restaurant " + restaurantIndex + ".");
                        c.setAssignedRestaurant(restaurantIndex);
                        c.setCustomerNumber(Customer.getTotalCustomers());
                        customersInLine.add(c);
                        numCounter++;
                    }
                    restaurantIndex++;

                }
                while( !(customersInLine.isEmpty()) ){//Loop to remove customers from the line one by one and have them either sit or leave
                    Customer current = customersInLine.remove();
                    if( restaurants.get(current.getAssignedRestaurant()-1).size() < s.maxCustomerSize) {
                        restaurants.get(current.getAssignedRestaurant()-1).enqueue(current);
                        Customer.setTotalCustomers(Customer.getTotalCustomers() + 1);
                        double random = Math.random();
                        if( random < .2 ) {
                            current.setFood("Cheeseburger");
                            current.setPriceOfFood(15);
                            int def = 25;
                            int q = 0;
                            int rem = 3-s.chefs;
                            q = rem*5;
                            current.setTimeToServe(15+(def+q));
                            current.setTempTime(15+(def+q));
                        }
                        else if( random < .4 && random > .2 ) {
                            current.setFood("Steak");
                            current.setPriceOfFood(25);
                            int def = 30;
                            int q = 0;
                            int rem = 3-s.chefs;
                            q = rem*5;
                            current.setTimeToServe(15+(def+q));
                            current.setTempTime(15+(def+q));
                        }
                        else if( random < .6 && random > .4) {
                            current.setFood("Grilled Cheese");
                            current.setPriceOfFood(10);
                            int def = 15;
                            int q = 0;
                            int rem = 3-s.chefs;
                            q = rem*5;
                            current.setTimeToServe(15+(def+q));
                            current.setTempTime(15+(def+q));
                        }
                        else if ( random < .8 && random > .6) {
                            current.setFood("Chicken Tenders");
                            current.setPriceOfFood(10);
                            int def = 25;
                            int q = 0;
                            int rem = 3-s.chefs;
                            q = rem*5;
                            current.setTimeToServe(15+(def+q));
                            current.setTempTime(15+(def+q));
                        }
                        else {
                            current.setFood("Chicken Wings");
                            current.setPriceOfFood(20);
                            int def = 30;
                            int q = 0;
                            int rem = 3-s.chefs;
                            q = rem*5;
                            current.setTimeToServe(15+(def+q));
                            current.setTempTime(15+(def+q));
                        }
                        System.out.println("Customer #" + current.getCustomerNumber() + " has been seated with the order \"" + current.getFood() + "\".");
                        current.setTimeArrived(i);

                        numCounter++;


                    }
                    else {
                        System.out.println("Customer # " + current.getCustomerNumber() + " cannot be seated! They have left the restaurant.");
                        Customer.setTotalCustomers(Customer.getTotalCustomers() - 1);
                        numCustThatLeft++;
                    }

                }

                for(int c = 1; c<=s.numRestaurants; c++) {
                    System.out.print("R" + c + ": " + restaurants.get(c-1).toString() ) ;
                }

                for( Restaurant r: restaurants){
                    r.timeIncrement();
                }

               // for( Restaurant r: restaurants ){
                    //for( Customer c: r)
                //}


            }
            double avgCusTime = (double)s.totalServiceTime/(double)s.customersServed;



            System.out.println("\nTotal customer time: " + s.totalServiceTime + " minutes");
            System.out.println("Total customers served: " + s.customersServed);
            System.out.printf("Average customer time lapse: %.2f minutes per order\n", avgCusTime);
            System.out.println("Total Profit: $" + s.profit);
            System.out.println("Customers that left: " + numCustThatLeft);
            System.out.println("Do you want to try another simulation? (y/n): ");
        }while( !(in.nextLine().toLowerCase().equals("n")) );


        System.out.println("Program terminating normally...");
    }

}
