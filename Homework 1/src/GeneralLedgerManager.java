/*
Anthony Pizzulli
111990335
R08
 */
import java.util.InputMismatchException;
import java.util.Scanner;
public class GeneralLedgerManager extends GeneralLedger  {

    public static double d = 0.0;

    /**
     *brief: This is the driver class for the GeneralLedger. It prints the menu, and facilitates
     *       interaction with the user (input). It contains the switch case which determines what
     *       actions to performed based on the user's input.
     * @param args
     */
    public static void main(String[] args){

        Scanner userInput = new Scanner(System.in);
        String input = new String();
        GeneralLedger gl = new GeneralLedger();
        GeneralLedger backup = new GeneralLedger();
        String date;
        boolean found = false;
        while( !(input.toLowerCase().equals("q"))  ){

            System.out.print("\n(A) Add Transaction\n(G) Get Transaction\n(R) Remove Transaction\n" +
                    "(P) Print Transactions in General Ledger\n" +
                    "(F) Filter by Date\n" +
                    "(L) Look for Transaction\n" +
                    "(S) Size\n" +
                    "(B) Backup\n" +
                    "(PB) Print Transactions in Backup\n" +
                    "(RB) Revert to Backup\n" +
                    "(CB) Compare Backup with Current\n" +
                    "(PF) Print Financial Information\n" +
                    "(Q) Quit\n");

            System.out.println("Enter a selection: ");
            input = userInput.nextLine();

            switch (input.toLowerCase())  {

                case "a" :

                    System.out.println("Enter date: ");
                    date = userInput.nextLine();
                    System.out.println("Enter amount ($): ");
                    double amount = Double.parseDouble(userInput.nextLine()); //userInput.nextDouble();
                    System.out.println("Enter Description: ");
                    String desc = userInput.nextLine();
                    Transaction t = new Transaction(date, amount, desc);

                    try{
                        gl.addTransaction(t);
                        System.out.println("Transaction successfully added to the general ledger.");
                    }catch (FullGeneralLedgerException f){
                        System.out.println("There is no more room in the General Ledger to record additional transactions.");
                    }
                    catch (InvalidTransactionException i){
                        System.out.println("The transaction format is invalid.");
                    }
                    catch( TransactionAlreadyExistsException tr){
                        System.out.println("Transaction not added: Transaction already exists in the general ledger.");
                    }
                    catch( InputMismatchException i){
                        System.out.println("Invalid input.");
                    }
                    catch( NumberFormatException n){
                        break;
                    }
                    break;

                case "g":

                    System.out.println("Enter Position: ");
                    int position = Integer.parseInt(userInput.next());
                    try{
                        gl.getTransaction(position);
                    }
                    catch( InvalidLedgerPositionException i){
                        System.out.println("No such transaction.");
                        break;
                    }

                    if( gl.getLedger()[position-1] != null){
                        System.out.print("No.    Date          Debit    Credit    Description\n------------------------------" +
                                "---------------------------------------------------------------------\n");
                        d = gl.getLedger()[position - 1].getAmount();
                        if (d >= 0) {
                            System.out.printf("%d      %s\t%6.2f              %-11s", position, gl.getLedger()[position - 1].getDate(), Math.abs(d), gl.getLedger()[position - 1].getDescription());
                        } else if (d < 0) {
                            System.out.printf("%d      %s\t          %6.2f     %-11s", position, gl.getLedger()[position - 1].getDate(), Math.abs(d), gl.getLedger()[position - 1].getDescription());
                        }
                    }

                    break;

                case "r":

                    System.out.println("Enter Position: ");
                    int p = Integer.parseInt(userInput.next());
                    try{
                        gl.removeTransaction(p);
                    }catch( InvalidLedgerPositionException i){
                        System.out.println("Transaction not removed: No such transaction in the general ledger.");
                    }
                    if(deleted) {
                        System.out.println("Transaction has been successfully removed from the general ledger.");
                    }
                    break;

                case "p":
                    if( numElements > 0) {
                        gl.printAllTransactions();
                    }
                    else
                        System.out.println("No transactions currently in the general ledger.");
                    break;

                case "f":
                    System.out.println("Enter Date: ");
                    String d = userInput.next();
                    filter(gl, d);
                    break;

                case "l":
                    System.out.println("Enter Date: ");
                    String da = userInput.nextLine();
                    System.out.println("Enter Amount ($): ");
                    double am = Double.parseDouble(userInput.nextLine());
                    System.out.println("Enter Description: ");
                    String des = userInput.nextLine();
                    Transaction target = new Transaction(da, am, des);
                    try{
                         found = gl.exists(target);
                    } catch( IllegalArgumentException i){
                        System.out.println("The given object is not a valid Transaction object.");
                    }
                    if (found) {
                        System.out.print("No.    Date          Debit    Credit    Description\n------------------------------" +
                                "---------------------------------------------------------------------\n");

                            double c = target.getAmount();
                            if (c >= 0) {
                                System.out.printf("%d      %s\t%6.2f              %-1s\n", targetIndex+1, target.getDate(), c, target.getDescription());
                            }
                            else if (c < 0) {
                                System.out.printf("%d      %s\t          %6.2f    %-1s\n", targetIndex+1, target.getDate(), Math.abs(c), target.getDescription());
                            }
                        else
                            break;
                    }


                    break;

                case "s":
                    System.out.println("There are " + gl.size() + " transactions currently in the general ledger.");
                    break;

                case "b":

                    Object o = gl.clone();
                    backup = (GeneralLedger)o;
                    System.out.println("Created a backup of the current general ledger.");
                    break;

                case "pb":
                    double n = 0.0;
                    System.out.print("No.    Date          Debit    Credit    Description\n------------------------------" +
                            "---------------------------------------------------------------------\n");
                    for (int i = 0; i < newNumElements; i++) {
                        if( backup.getLedger()[i] != null){
                            n = backup.getLedger()[i].getAmount();
                            if( n >= 0) {
                                System.out.printf("%d      %s\t%6.2f              %-11s\n", i+1, backup.getLedger()[i].getDate(), Math.abs(n), backup.getLedger()[i].getDescription());
                            }
                            else if(n < 0){
                                System.out.printf("%d      %s\t          %6.2f     %-11s\n", i+1, backup.getLedger()[i].getDate(), Math.abs(n), backup.getLedger()[i].getDescription());
                            }
                        }
                        else
                            break;
                    }

                    break;

                case "rb":
                    for(int i = 0; i < newNumElements; i++){
                        gl.getLedger()[i] = backup.getLedger()[i];
                    }
                    numElements = newNumElements;
                    System.out.println("General ledger successfully reverted to the backup copy.");
                    break;

                case "cb":
                    boolean same = true;
                    if( numElements == newNumElements ){
                        for(int i = 0; i<newNumElements; i++){
                            if( backup.getLedger()[i]!=null && gl.getLedger()[i]!=null){
                                if( !(gl.getLedger()[i].equals(backup.getLedger()[i])) )
                                    same = false;
                            }
                        }
                    }
                    else
                        same = false;

                    if(same) {
                        System.out.println("The current general ledger is the same as the backup copy.");
                    }
                    else {
                        System.out.println("The current general ledger is NOT the same as the backup copy.");
                    }
                    break;

                case "pf":
                    System.out.println("Financial Data for Jack's Account\n" +
                            "---------------------------------------------------------------------------------------------------");
                    System.out.printf("     Assets: $%.2f\n" +
                                      "Liabilities: $%.2f\n" +
                                      "  Net Worth: $%.2f\n", gl.getTotalDebitAmount(), Math.abs(gl.getTotalCreditAmount()), netWorth );
                    break;

                case "q":
                    System.out.println("Program terminating successfully...");
                    break;

                default:
                        break;
            }
        }

    }
}




