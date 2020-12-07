/*
Anthony Pizzulli
111990335
R08
 */
import java.io.*;
import java.util.Hashtable;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
public class HashedGrocery implements Serializable{

    private int businessDay;
    private Hashtable<String,Object>hashTable = new Hashtable<String,Object>();

    /**
     * Brief: Getter method for businessDay variable
     * @return int: businessDay variable
     */
    public int getBusinessDay(){
        return businessDay;
    }

    /**
     * Brief: Default constructor for HashedGrocery class, which sets the businessDay static variable to 1
     */
    public HashedGrocery(){
        this.businessDay = 1;
    }

    /**
     * Brief: Method to manually add a single Item to the hashTable
     * @param item Item: Item to be added to the hashTable
     * @throws ItemAlreadyExistsException: Thrown if the given Item already exists in the hashTable
     */
    public void addItem(Item item) throws ItemAlreadyExistsException{

        String key = item.getItemCode();
        if( this.hashTable.containsKey(key)) {
            throw new ItemAlreadyExistsException();
        }
        else {
            this.hashTable.put(key, item);
            System.out.println( item.getItemCode() + ": " + item.getName() + " is added to inventory." );
        }

    }

    /**
     * Brief: Method to load and process a JSON file containing Items to the hashTable
     * @param filename String: The name of the file to be processed
     * @throws FileNotFoundException: Thrown if the given file is not found in the directory
     */
    public void addItemCatalog(String filename)throws FileNotFoundException{

        File f = new File(filename);
        if( !(f.exists()) ){
            throw new FileNotFoundException();
        }

        JSONParser parser = new JSONParser();
        try{
            FileInputStream fileStream = new FileInputStream(filename);
            InputStreamReader inputReader = new InputStreamReader(fileStream);
            JSONArray objsInStream = new JSONArray();
            try{
                objsInStream = (JSONArray) parser.parse(inputReader);
            }catch( java.io.IOException i){
                System.out.println("IO Exception.");
            }catch( org.json.simple.parser.ParseException p){
                System.out.println("Parse Exception.");
            }

            for( int i = 0; i < objsInStream.size(); i++ ){
                JSONObject obj = (JSONObject) objsInStream.get(i);
                Item newItem = new Item( (String)obj.get("itemCode"), (String)obj.get("itemName"), Integer.parseInt((String)obj.get("qtyInStore")),
                        Integer.parseInt((String)obj.get("avgSales")), Integer.parseInt((String)obj.get("amtOnOrder")), 0, Double.parseDouble((String)obj.get("price")) );
                System.out.println(newItem.getItemCode() + ": " + newItem.getName() + " is added to inventory." );
                if( !(this.hashTable.contains(newItem))) {
                    this.hashTable.put(newItem.getItemCode(), newItem);
                }

            }
        }catch(FileNotFoundException fe) {
            System.out.println("File cannot be found.");
        }
    }


    /**
     * Brief: Method to load and process a JSON file containing sales information and update Items in the hashTable based on the given data
     * @param filename String: The name of the file to be processed
     * @throws FileNotFoundException: Thrown if the given file is not found in the directory
     */
    public void processSales(String filename)throws FileNotFoundException{
        File f = new File(filename);
        if( !(f.exists()) )
            throw new FileNotFoundException();
        FileInputStream fs = new FileInputStream(filename);
        JSONParser parser = new JSONParser();
        InputStreamReader iR = new InputStreamReader(fs);
        JSONArray objs = new JSONArray();
        try{
            objs = (JSONArray) parser.parse(iR);
        }catch( java.io.IOException i){
            System.out.println("IO Exception.");
        }catch( org.json.simple.parser.ParseException p){
            System.out.println("Parse Exception.");
        }
        String returnString = "";

        for( int i = 0; i < objs.size(); i++ ) {
            JSONObject o = (JSONObject)objs.get(i);
            String key = (String)o.get("itemCode");
            int currentQtySold = Integer.parseInt((String)o.get("qtySold"));
            if( this.hashTable.containsKey(key) ){
                Item toBeUpdated = (Item)this.hashTable.get(key);
                if( currentQtySold > toBeUpdated.getQtyInStore() ){
                    returnString += toBeUpdated.getItemCode() + ": Not enough stock for sale. Not updated.\n";
                }
                else{
                    toBeUpdated.setQtyInStore( toBeUpdated.getQtyInStore()- currentQtySold );
                    if( toBeUpdated.getQtyInStore() < (3 * toBeUpdated.getAverageSalesPerDay()) ){
                        int amToRestock = (2 * toBeUpdated.getAverageSalesPerDay());
                        toBeUpdated.setOnOrder(amToRestock);
                        toBeUpdated.setArrivalDay(this.businessDay + 3);
                    }
                    returnString += toBeUpdated.getItemCode() + ": " + currentQtySold + " units of " + toBeUpdated.getName() + " are sold.";
                    if( toBeUpdated.getOnOrder() > 0 ){
                        returnString += " Order has been placed for " + toBeUpdated.getOnOrder() + " more units.\n";
                    }
                    else
                        returnString += "\n";
                }
            }
            else
                returnString += key + ": Cannot buy as it is not in the grocery store.\n";
        }
        System.out.println(returnString);
    }

    /**
     * Brief: Method to update businessDay by 1, check if any orders have arrived and if so updates those Items based on their orders
     */
    public void nextBusinessDay(){
        this.businessDay += 1;
        System.out.println("\nAdvancing business day...");
        System.out.println("Business Day " + businessDay + ".");
        String arrivedOrders = "";
        boolean anyOrdersArrived = false;
        for(String key: hashTable.keySet()) {
            Item i = (Item)this.hashTable.get(key);
            if( i.getArrivalDay() == businessDay && i.getOnOrder() != 0) {
                arrivedOrders += i.getItemCode() + ": " + i.getOnOrder() + " units of " + i.getName() + ".\n";
                i.setQtyInStore( i.getQtyInStore() + i.getOnOrder() );
                i.setOnOrder(0);
                i.setArrivalDay(0);
                anyOrdersArrived = true;
            }
        }
        if( !anyOrdersArrived)
            System.out.println("\nNo orders have arrived.\n");
        else
            System.out.println("\nOrders have arrived for:\n\n" + arrivedOrders);
    }

    /**
     * Brief: toString method for HashedGrocery class which compiles a String display of all Items in the hashTable
     * @return String: A neatly formatted table consisting of all Items in the hashTable and their instance variables
     */
    @Override public String toString() {
        String r = "\nItem code   Name                Qty   AvgSales   Price    OnOrder    ArrOnBusDay\n" +
                "--------------------------------------------------------------------------------\n";
        for(String key:hashTable.keySet()) {
           Item i = (Item)this.hashTable.get(key);
            r+=i.toString();
        }
        return r;
    }

}
