/*
Anthony Pizzulli
111990335
R08
 */
public class Transaction extends Object {

    private String date;
    private double amount;
    private String description;

    /**
     * brief: This is the constructor for the Transaction class; it takes a String, double and String (respectively)
     *        to construct a Transaction object with corresponding date, amount and description values.
     * @param date -- string: This parameter is the date value in the form "yyyy/mm/dd."
     * @param amount -- double: This parameter is the amount of the Transaction (must be non-zero).
     * @param description -- string: This parameter is the description of the Transaction.
     */
    public Transaction( String date, double amount, String description){
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Brief: This is the no arg (default) constructor for the Transaction class.
     */
    public Transaction(){}

    /**
     * brief: This method returns the String value corresponding to the date of the given Transaction object.
     * @return String: The String returned is the date of the Transaction, in the form "yyyy/mm/dd."
     */
    public String getDate(){
        return date;
    }

    /**
     * brief: This method returns the double value corresponding to the amount of the given Transaction object.
     * @return double: The double returned is the amount of the Transaction, which is either greater than or less than 0.
     */
    public double getAmount(){
        return amount;
    }

    /**
     * brief: This method returns the String value corresponding to the description of the given Transaction.
     * @return String: The String returned is the description of the Transaction.
     */
    public String getDescription(){
        return description;
    }

    /**
     *brief: This method extracts and returns the year given by the date value of the Transaction. It slices the String after the first
     *       "/."
     * @return int: The integer returned is the numerical year value given by the Transaction's date.
     */
    public int getYear(){
        return Integer.parseInt(date.substring(0,4));
    }

    /**
     *brief: This method extracts and returns the month given by the date value of the Transaction. It slices the String between the
     *       first and middle "/" characters.
     * @return int: The integer returned is the numerical month value given by the Transaction's date.
     */
    public int getMonth(){
        return Integer.parseInt(date.substring(5,7));
    }

    /**
     *brief: This method extracts and returns the day given by the date value of the Transaction. It slices the String after the
     *       last "/" character.
     * @return int: The integer returned is the numerical day value given by the Transaction's date.
     */
    public int getDay(){
        return Integer.parseInt(date.substring(8,10));
    }


    /**
     *brief: This method creates a copy of the given Transaction with the same attributes, but it is a deep copy
     *       and so any subsequent changes to the original object are NOT reflected in the new one.
     * @param t Transaction: This parameter is the Transaction that is to be cloned.
     * @return Object: The object returned is the cloned Transaction.
     */
    public Object clone( Transaction t){
        Transaction newT = new Transaction(t.date, t.amount, t.description);
        return newT;
    }

    /**
     *brief: This method tests for equality between Transaction objects. If they have the same attributes, they
     *       are equal.
     * @param obj Object: The parameter is the Transaction object being compared to this.Transaction.
     * @return boolean: If the return value is true, the objects are equal, and if it is false, the object is either not
     *                  an instance of Transaction or it is not equal to this.Transaction.
     */
    @Override public boolean equals(Object obj){
        if( obj instanceof Transaction ){
            Transaction t = (Transaction)obj;
            return t.getDate().equals(this.date) && t.getAmount()==(this.amount)
                    && t.getDescription().equals(this.description);
        }
        else
            return false;

    }

}
