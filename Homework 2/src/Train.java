/*
Anthony Pizzulli
111990335
R08
 */

public class Train {

    private Train next;
    private Train previous;
    private int trainNumber;
    private String destination;
    private int arrivalTime;
    private int transferTime;


    /**
     * Brief: This is the default constructor for the Train class
     */
    public Train(){

    }


    /**
     * Brief: This is the getter method for the 'next' Train (after the cursor).
     * @return Train: The 'next' value of the current Train.
     */
    public Train getNext(){ return this.next; }

    /**
     * This is the setter method for the 'next' Train (after the cursor).
     * @param nextTrain: The new Train to set the 'next' value to.
     */
    public void setNext( Train nextTrain ) { this.next = nextTrain; }

    /**
     * Brief: This is the getter method for the 'previous' Train (before the cursor).
     * @return Train: The 'previous' value of the current Train.
     */
    public Train getPrev(){ return this.previous; }

    /**
     * Brief: This is the setter method for the 'previous' Train (before the cursor).
     * @param prevTrain: The new Train to set the 'previous' value to.
     */
    public void setPrev( Train prevTrain ){ this.previous = prevTrain; }

    /**
     * Brief: This is the getter method for the given Train's 'trainNumber.'
     * @return int: The 'trainNumber' of the current Train value (at the cursor).
     */
    public int getTrainNumber(){ return this.trainNumber; }

    /**
     * Brief: This is the setter method for the given Train's 'trainNumber.'
     * @param n: The new value to set the 'trainNumber' to.
     */
    public void setTrainNumber( int n ){this.trainNumber = n;}

    /**
     * Brief: This is the getter method for the the given Train's 'destination.'
     * @return String: The current Train's 'destination.'
     */
    public String getDestination(){ return this.destination; }


    /**
     * Brief: This is the getter method for the given Train's 'arrivalTime.'
     * @return int: The current Train's 'arrivalTime.'
     */
    public int getArrivalTime(){ return this.arrivalTime; }


    /**
     * Brief: This is the getter method for the given Train's 'transferTime.'
     * @return int: The given Train's 'transferTime' (in minutes).
     */
    public int getTransferTime(){ return this.transferTime; }

    /**
     * Brief: This is the setter method for the given Train's 'transferTime.'
     * @param t: The given Train's 'transferTime' (in minutes).
     */
    public void setTransferTime(int t){ this.transferTime = t;}
    /**
     * Brief:
     * @param next
     * @param prev
     * @param trainNum
     * @param dest
     * @param arrivTime
     * @param transfTime
     */
    public Train( Train next, Train prev, int trainNum, String dest, int arrivTime, int transfTime){
        this.next = next;
        this.previous = prev;
        this.trainNumber = trainNum;
        this.destination = dest;
        this.arrivalTime = arrivTime;
        this.transferTime = transfTime;
    }

    /**
     * Brief: This is the equals method for the Train object, which determines whether two Trains are equal based on their
     *        'trainNumbers.'
     * @param o: The Train object to compare the given Train to.
     * @return boolean: The result of comparing the Train objects; if they have the same 'trainNumber,' the method returns true,
     *                  and it returns false otherwise.
     */
   @Override public boolean equals(Object o){
        boolean same = false;
        if( o instanceof Train){
            Train t = (Train)o;
            same = ( this.trainNumber == t.trainNumber );
        }
        return same;
    }

    /**
     * Brief: This method builds a String describing the given Train object based on its attributes.
     * @return String: Formatted Train attributes of current Train.
     */
    @Override public String toString(){
        String returnStr =  "";
        returnStr += "Selected Train:\n";
        returnStr += "     Train Number: " + this.getTrainNumber() + "\n";
        returnStr += "     Train Destination: " + this.getDestination() + "\n";
        returnStr += "     Arrival Time: " + this.getArrivalTime() + "\n";
        returnStr += "     Departure Time: " + (this.getArrivalTime() + this.getTransferTime()) +"\n";
        return returnStr;
    }

}
