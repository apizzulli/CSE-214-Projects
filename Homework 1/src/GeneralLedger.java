/*
Anthony Pizzulli
111990335
R08
 */

public class GeneralLedger extends Throwable {

    public static final int MAX_TRANSACTIONS = 50;
    private double totalDebitAmount = 0.0;
    private double totalCreditAmount = 0.0;
    public static double netWorth = 0.0;
    public static int numElements = 0;
    public static int targetIndex = 0;
    public static int newNumElements = 0;
    public static boolean added = false;
    public static boolean deleted = false;


    private Transaction[] ledger;

    /**
     * brief: This method is the constructor for the GeneralLedger class. It instantiates a ledger with
     *        50 (the value of MAX_TRANSACTIONS) elements.
     */
    public GeneralLedger() {
        ledger = new Transaction[MAX_TRANSACTIONS];
    }

    /**
     * brief: This method is provided in order to access the GeneralLedger.
     * @return Transaction[]: It returns the GeneralLedger, which is an array of Transaction objects.
     */
    public Transaction[] getLedger() {
        return ledger;
    }

    /**
     * brief: This method gives the totalDebitAmount, which keeps track of the amount of deposits in Jack's account.
     * @return double: The double returned is the value of totalDebitAmount, which has private access in the GeneralLedger
     *                 class.
     */
    public double getTotalDebitAmount() {
        return totalDebitAmount;
    }

    /**
     * brief: This method sets the totalDebitAmount, which keeps track of the amount of deposits in Jack's account.
     * @param d double: This double is the desired amount to set the totalDebitAmount to.
     */
    public void setTotalDebitAmount(double d) {
        this.totalDebitAmount = d;
    }

    /**
     * brief: This method gives the totalCreditAmount, which keeps track of the amount of credits (or withdrawals)
     *        in Jack's account.
     * @return double: The double returned is the value of totalCreditAmount, which has private access in the GeneralLedger
     *                 class.
     */
    public double getTotalCreditAmount() {
        return totalCreditAmount;
    }

    /**
     *brief: This method sets the totalCreditAmount, which keeps track of the amount of credits (or withdrawals) in Jack's
     *       account.
     * @param d double: This double is the desired amount to set the totalCreditAmount to.
     */
    public void setTotalCreditAmount(double d) {
        this.totalCreditAmount = d;
    }

    /**
     * brief: This method takes a transaction argument and adds it to the General Ledger based on its date. It contains
     *        several iterative if-statements to keep the array sorted.
     * @param newTransaction: the Transaction created based upon the user's input for its date, amount and description
     * @throws FullGeneralLedgerException This exception is thrown if the General Ledger already contains 50 elements. 50 is the max number of elements that the General Ledger can hold.
     * @throws InvalidTransactionException This exception is thrown if the date of the Transaction object passed to the method is
     *                                      invalid, or if the amount of the transaction object is equal to 0.
     * @throws TransactionAlreadyExistsException This exception is thrown if the user is attempting to add a Transaction to the GeneralLedger
     *                                            that is already there.
     */

    public void addTransaction(Transaction newTransaction) throws FullGeneralLedgerException, InvalidTransactionException, TransactionAlreadyExistsException {

        try{
            if( exists(newTransaction) )
                throw new TransactionAlreadyExistsException();
        }
        catch(IllegalArgumentException e){

        }

        if (numElements == 50) {
            throw new FullGeneralLedgerException();
        }

        if (newTransaction.getAmount() == 0) {
            throw new InvalidTransactionException();
        }
        if (newTransaction.getDate().length() != 10 || newTransaction.getYear() < 1900 || newTransaction.getYear() > 2050
                || newTransaction.getMonth() < 1 || newTransaction.getMonth() > 12 || newTransaction.getDay() < 1 || newTransaction.getDay() > 30) {
            throw new InvalidTransactionException();
        }

        for (int i = 0; i < MAX_TRANSACTIONS; i++) {

            added = false;

            if (numElements == 0) {
                this.ledger[0] = newTransaction;
                added = true;
                break;
            }

            else if (newTransaction.getYear() < this.ledger[i].getYear()) {
                moveTransactions(newTransaction, i);
            }
            else if (newTransaction.getYear() == this.ledger[i].getYear()) {
                if (newTransaction.getMonth() < this.ledger[i].getMonth()) {
                    moveTransactions(newTransaction, i);
                }
                else if (newTransaction.getMonth() == this.ledger[i].getMonth()) {
                    if (newTransaction.getDay() < this.ledger[i].getDay()) {
                        moveTransactions(newTransaction, i);
                    }
                    else if( newTransaction.getYear() == this.ledger[numElements-1].getYear() && newTransaction.getMonth() == this.ledger[numElements-1].getMonth()
                            && newTransaction.getDay() == this.ledger[numElements-1].getDay()) {
                        this.ledger[numElements] = newTransaction;
                        added = true;
                    }
                    else if( newTransaction.getDay()== this.ledger[i].getDay() ){
                        for (int n = numElements; 0 < n; n--) {
                            if (n == i+1)
                                break;
                            this.ledger[n] = this.ledger[n - 1];
                        }
                        this.ledger[i+1] = newTransaction;
                        added = true;
                    }
                }
                else if( newTransaction.getMonth() > this.ledger[numElements-1].getMonth() ){
                    this.ledger[numElements] = newTransaction;
                    added = true;
                }
                else
                    continue;
            }
            else if (newTransaction.getYear() >= this.ledger[numElements - 1].getYear()) {
                this.ledger[numElements] = newTransaction;
                added = true;
            }
            if (added)
                break;
        }


        if (added) {
            numElements++;
            if( newTransaction.getAmount() < 0){
                setTotalCreditAmount(getTotalCreditAmount() + newTransaction.getAmount());
            }
            else {
                setTotalDebitAmount(getTotalDebitAmount() + newTransaction.getAmount());
            }
            netWorth = getTotalDebitAmount() + getTotalCreditAmount();
        }
    }

    /**
     * brief: This method supplies the corresponding arguments to the deleteElement method. It ensures that the position value
     *        is legitimate (i.e. greater than the number of Transactions in the General Ledger and not less than 1), it sets the
     *        totalDebitAmount and totalCreditAmount to the correct values, i.e. subtracts the amount of the Transaction
     *        from the corresponding value.
     * @param position: integer value representing the position in General Ledger to be deleted. The method removeTransaction
     *                  supplies a value of position-1 to the deleteElement method, because the array is 0-indexed.
     * @throws InvalidLedgerPositionException This exception is thrown if there is no element in the General Ledger
     *                                         at the given position, or if the position given is less than 1 or greater than 50.
     */
    public void removeTransaction(int position) throws InvalidLedgerPositionException {
        if (position > numElements || position < 1)
            throw new InvalidLedgerPositionException();
        if( this.ledger[position-1] != null) {
            netWorth -= this.ledger[position - 1].getAmount();
        }
        deleteElement(this.ledger, position - 1);
        numElements--;
        deleted = true;

    }

    /**
     * brief: This method essentially moves all elements that are to the right of the desired element to the left, replacing
     *        the space left behind.
     * @param t: the array of Transaction items for the deletion to be performed upon, in this case the GeneralLedger
     * @param n: the index (in the array) of the Transaction to be deleted
     */
    public static void deleteElement(Transaction[] t, int n) {
        if (t[n + 1] != null) {
            for (int i = n; t[i] != null; i++) {
                t[i] = t[i + 1];
            }
        } else
            t[n] = null;
    }


    /**
     * brief: This method returns the Transaction object located at the given position in the GeneralLedger.
     * @param position: This parameter is used to determine which element to retrieve from the General Ledger.
     *                  Since arrays are 0-indexed, when working with the array itself, the value of position
     *                  must be decremented by 1.
     * @return Transaction: The value returned by this method is the Transaction located at the given location in
     *                      the GeneralLedger.
     * @throws InvalidLedgerPositionException This exception is thrown if the position passed by the user is either
     *                                         greater than the number of elements in the GeneralLedger, or less than 1.
     */
    public Transaction getTransaction(int position) throws InvalidLedgerPositionException {

        if ( position > numElements || position < 1)
            throw new InvalidLedgerPositionException();
        Transaction target = new Transaction();
        if (this.ledger[position - 1] != null)
            target = this.ledger[position - 1];
        return target;
    }

    /**
     * brief: This method searches through the GeneralLedger to find the Transaction objects with the same
     *        date value as the parameter.
     * @param generalLedger: This parameter is the GeneralLedger (array of Transaction objects) to search through.
     * @param date: This parameter is the date value (given as a String) that is to be searched for in the General Ledger.
     */
    public static void filter(GeneralLedger generalLedger, String date) {
        if( numElements > 0 ){
                System.out.print("No.    Date          Debit    Credit    Description\n------------------------------" +
                    "---------------------------------------------------------------------\n");
            for (int i = 0; i < numElements; i++) {
                if (generalLedger.getLedger()[i] != null ) {
                    if (generalLedger.getLedger()[i].getDate().equals(date)){
                        double d = generalLedger.getLedger()[i].getAmount();
                        if (d >= 0) {
                            System.out.printf("%d      %s\t%6.2f              %-1s\n", i + 1, generalLedger.getLedger()[i].getDate(), Math.abs(d), generalLedger.getLedger()[i].getDescription());
                        }
                        else if (d < 0) {
                            System.out.printf("%d      %s\t          %6.2f    %-1s\n", i + 1, generalLedger.getLedger()[i].getDate(), Math.abs(d), generalLedger.getLedger()[i].getDescription());
                        }
                    }

                }
                else
                    break;
            }
        }

    }

    /**
     * brief: This method creates an identical instance of the given GeneralLedger object, but it is a deep copy
     *        and therefore has a different memory address from the original. Subsequent changes to the original
     *        GeneralLedger will NOT affect this new GeneralLedger.
     * @return Object: This method returns a generic object, which must be casted to a GeneralLedger in order
     *                 to access its contents.
     */
    @ Override public Object clone (){
        newNumElements = numElements;
        GeneralLedger newGeneralLedger = new GeneralLedger();
        newGeneralLedger.setTotalDebitAmount( this.getTotalDebitAmount() );
        newGeneralLedger.setTotalCreditAmount( this.getTotalCreditAmount() );
        for( int i = 0; i<numElements; i++){
            Transaction t = new Transaction(this.ledger[i].getDate(), this.ledger[i].getAmount(), this.ledger[i].getDescription());
            newGeneralLedger.getLedger()[i] = t;
        }
        return newGeneralLedger;
    }

    /**
     * brief: This method iterates through the GeneralLedger and determines whether or not the Transaction object passed by
     *        the parameter is inside.
     * @param transaction: This parameter is the Transaction object that is to be found in the GeneralLedger.
     * @return boolean (same): This boolean is the return of the exists method. If the transaction object has been
     *                         found, the value returned is true.
     * @throws IllegalArgumentException This exception is thrown if any values passed to the Transaction object are invalid.
     */
    public boolean exists(Transaction transaction) throws IllegalArgumentException {
        boolean same = false;
        if (transaction.getDate().length() != 10 || transaction.getYear() < 1900 || transaction.getYear() > 2050
                || transaction.getMonth() < 1 || transaction.getMonth() > 12 || transaction.getDay() < 1 || transaction.getDay() > 30 || transaction.getAmount() == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < numElements; i++) {
            if (ledger[i].equals(transaction)) {
                same = true;
                targetIndex = i;
                break;
            }

        }
        return (same);
    }

    /**
     * brief: This method simply returns the numElements integer, which keeps track of how many Transactions are
     *        contained in the GeneralLedger.
     * @return int: The integer return tells how many Transactions are in the GeneralLedger.
     */
    public int size() {
        return this.numElements;
    }

    /**
     * brief: This method simply prints the top of the table to format the printed Transactions, and calls the
     *        toString method for the GeneralLedger.
     */
    public void printAllTransactions() {
        System.out.println("No.    Date          Debit    Credit    Description\n------------------------------" +
                "---------------------------------------------------------------------");
        System.out.print(this.toString());
    }

    /**
     * brief: This method concatenates a String which accounts for each Transaction in the GeneralLedger and formats
     *        each Transaction individually in the table.
     * @return: The string returned is the formatted table containing all Transactions in the GeneralLedger.
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < this.numElements; i++) {
            if (this.ledger[i] != null) {
                double d = this.ledger[i].getAmount();
                if (d >= 0) {
                    output += String.format("%d      %s\t%6.2f              %-1s\n", i + 1, this.ledger[i].getDate(), d, this.ledger[i].getDescription());
                }
                else if (d < 0) {
                    output += String.format("%d      %s\t          %6.2f    %-1s\n", i + 1, this.ledger[i].getDate(), Math.abs(d), this.ledger[i].getDescription());
                }
            } else
                break;
        }
        return output;
    }

    /**
     * brief: This is a helper method for the addTransactions method. It shifts elements to the right in order to leave
     *        space for the newTransaction.
     *
     * @param t: This parameter is the Transaction object that is to be added to the GeneralLedger.
     * @param track: This parameter is the index in the array where the newTransaction is to be placed.
     */
    public void moveTransactions( Transaction t, int track ){
        for (int n = numElements; 0 < n; n--) {
            if (n == track)
                break;
            ledger[n] = ledger[n - 1];
        }
        ledger[track] = t;
        added = true;
    }

}

