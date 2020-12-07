/*
Anthony Pizzulli
111990335
R08
 */
import java.io.*;
import java.util.Scanner;

public class GroceryDriver {


    /**
     * Brief: Main method driver for GroceryDriver class which processes user input, calls proper methods from the HashedGrocery class and catches necessary Exceptions
     * @param args
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input="";

        HashedGrocery hG = new HashedGrocery();
        try {
             FileInputStream file = new FileInputStream("grocery.obj");
             ObjectInputStream objectInput  = new ObjectInputStream(file);
             hG = (HashedGrocery) objectInput.readObject();
             objectInput.close();
            System.out.println("HashedGrocery is loaded from grocery.obj.\n");
        } catch(IOException e) {
            System.out.println("Grocery.obj does not exist. Creating new HashedGrocery object...");
        }catch(java.lang.ClassNotFoundException c){

        }
        System.out.println("Business Day " + hG.getBusinessDay() + ".");

        while ( !(input.equals("q")) ){
            System.out.println("\nMenu:\n" +
                    " \n" +
                    "(L) Load item catalog    \n" +
                    "(A) Add items              \n" +
                    "(B) Process Sales      \n" +
                    "(C) Display all items\n" +
                    "(N) Move to next business day  \n" +
                    "(Q) Quit ");
            System.out.println("\nEnter option: ");
             input = in.nextLine().toLowerCase();

            switch( input ){
                case "l":
                    System.out.println("Enter file to load: ");
                    String fileName = in.nextLine();
                    try{
                        hG.addItemCatalog(fileName);
                    }catch(FileNotFoundException f){
                        System.out.println("File cannot be found.");
                    }
                    break;
                case "a":
                    System.out.println("Enter item code: ");
                    String iCode = in.nextLine();
                    System.out.println("Enter item name: ");
                    String iName = in.nextLine();
                    System.out.println("Enter Quantity in store: ");
                    int q = Integer.parseInt(in.nextLine());
                    System.out.println("Enter Average sales per day: ");
                    int avg = Integer.parseInt(in.nextLine());
                    System.out.println("Enter price: ");
                    double p = Double.parseDouble(in.nextLine());
                    Item i = new Item(iCode, iName, q, avg, 0, 0, p);
                    try{
                        hG.addItem(i);
                    }catch(ItemAlreadyExistsException it){
                        System.out.println(i.getItemCode() + ": Cannot add item as item code already exists.");
                    }
                    break;
                case "b":
                    System.out.println("Enter filename: ");
                    String s = in.nextLine();
                    try{
                        hG.processSales(s);
                    }catch(FileNotFoundException f){
                        System.out.println("File cannot be found.");
                    }
                    break;
                case "c":
                    System.out.println(hG.toString());
                    break;
                case "n":
                    hG.nextBusinessDay();
                    break;
                case "q":
                    try {
                        FileOutputStream file = new FileOutputStream("grocery.obj");
                        ObjectOutputStream objOut = new ObjectOutputStream(file);
                        objOut.writeObject(hG);
                        objOut.close();
                    }catch( java.io.IOException ie){

                    }

                    System.out.println("\nHashedGrocery is saved in grocery.obj.\n");
                    System.out.println("Program terminating normally...");
                    break;
                default:
                    break;
            }
        }

    }
}
