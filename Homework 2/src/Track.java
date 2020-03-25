/*
Anthony Pizzulli
111990335
R08
 */

public class Track extends Exception{

    private Train head;
    private Train tail;
    private Train cursor;
    private Track next;
    private Track previous;
    public static int departureTime;
    private int trackNumber;
    private double utilizationRate;
    public static double numTrains;

    //the last possible time a train can depart is at 2359 hours and the earliest a train can arrive is at 0000 hours.


    /**
     * brief: This is the default constructor for the Track class; it sets all pointers to nukl and all numerical values to
     * 0.
     */
    public Track() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.next = null;
        this.previous = null;
        this.trackNumber = 0;
        this.utilizationRate = 0.0;
    }

    /**
     * brief: This is the secondary constructor which constructs a Track object with the given Track number
     * (used for 'TA' menu option).
     * @param i: The numerical value for the Track number
     */
    public Track(int i){
        this.trackNumber = i;
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.next = null;
        this.previous = null;
    }

    /**
     * Brief: This is the setter method for the Track cursor.
     * @param cursor: The new cursor to be set
     */
    public void setCursor( Train cursor) { this.cursor = cursor; }

    /**
     * Brief: This is the getter method for the Track cursor.
     * @return: The Track cursor
     */
    public Train getCursor(){ return cursor; }

    /**
     * Brief: This is the getter method for the 'next' Track (after the cursor).
     * @return: The 'next' value of the Track cursor.
     */
    public Track getNext(){ return this.next; }

    /**
     * Brief: This is the setter method for the 'next' Track (after the cursor).
     * @param a: The new Track to set the 'next' value to.
     */
    public void setNext(Track a){ this.next = a;}

    /**
     * Brief: This is the getter method for the 'previous' Track (before the cursor).
     * @return: The 'previous' value of the Track cursor.
     */
    public Track getPrevious(){return this.previous;}

    /**
     * Brief: This is the setter method for the 'previous' Track (before the cursor).
     * @param b: The new Track to set the 'previous' value to.
     */
    public void setPrevious(Track b){ this.previous = b;}

    /**
     * Brief: This is the getter method for the trackNumber (number of Tracks).
     * @return: The number of Tracks at the current Track cursor.
     */
    public int getTrackNumber(){ return this.trackNumber; }

    /**
     * Brief: This is the getter method for the utilizationRate.
     * @return: The utilization rate as a percentage, which is (numTrains/1440)*1000.
     */
    public double getUtilizationRate(){return this.utilizationRate;}

    /**
     * Brief: This is the setter method for the utilizationRate.
     * @param d: The new value for the utilizationRate.
     */
    public void setUtilizationRate( double d ){ this.utilizationRate = d;}

    /**
     * Brief: This is the getter method for the numTrains.
     * @return: numTrains, which is the number of Trains in the current Track List.
     */
    public double getNumTrains(){return this.numTrains;}

    /**
     * Brief: This method adds the 'newTrain' to its sorted spot in the LinkedList of Trains, based on its
     * arrival time.
     * @param newTrain: The Train that is to be added to the List
     * @throws InvalidArrivalTimeException: Thrown if the time value of the given 'newTrain' is invalid
     * @throws NoExistingTrackException: Thrown if there is no Track to add the Train to
     */
    public void addTrain(Train newTrain) throws InvalidArrivalTimeException, NoExistingTrackException, TrainAlreadyExistsException {

        boolean added = false;

        if (newTrain.getArrivalTime() < 0 || newTrain.getArrivalTime() > 2359 || newTrain.getArrivalTime()%100 > 50) {
            throw new InvalidArrivalTimeException();
        }
        if( Station.numTracks == 0 )
            throw new NoExistingTrackException();

        if ( !(this.head instanceof Train) ) {
            this.head = newTrain;
            this.cursor = newTrain;
            this.tail = newTrain;
        }
        else if (newTrain.getArrivalTime() > this.tail.getArrivalTime()) {
            if( this.tail.getTrainNumber() == newTrain.getTrainNumber() ) {
                throw new TrainAlreadyExistsException();
            }
            this.tail.setNext(newTrain);
            newTrain.setPrev(this.tail);
            this.tail = newTrain;
            this.cursor = newTrain;
            added = true;
        }
        else if (newTrain.getArrivalTime() < this.head.getArrivalTime()) {
            if( this.head.getTrainNumber() == newTrain.getTrainNumber() ) {
                throw new TrainAlreadyExistsException();
            }
            newTrain.setNext(this.head);
            this.head.setPrev(newTrain);
            this.head = newTrain;
            added = true;
            this.cursor = newTrain;
        }
        else {
            this.cursor = this.head;
            while (this.cursor.getNext() != null) {
                if( this.cursor.getTrainNumber() == newTrain.getTrainNumber() ) {
                    throw new TrainAlreadyExistsException();
                }
                else{
                    if (this.cursor.getArrivalTime() < newTrain.getArrivalTime()) {
                        if (this.cursor.getNext().getArrivalTime() > newTrain.getArrivalTime()) {
                            newTrain.setPrev(this.cursor);
                            newTrain.setNext(this.cursor.getNext());
                            this.cursor.getNext().setPrev(newTrain);
                            this.cursor.setNext(newTrain);
                            this.cursor = newTrain;
                            added = true;
                        }
                        else {
                            this.cursor = this.cursor.getNext();
                        }
                    }
                    else {
                        this.cursor = this.cursor.getNext();
                    }
                }

            }
        }

    }

    /**
     * Brief: This method removes the selected Train from the Track List, and if applicable, sets the cursor to the Train
     * following the one being removed.
     * @return: The Train that has been removed from the Track List
     */
    public Train removeSelectedTrain(){

        Train removed = new Train();

        if( (this.cursor instanceof Train) ) {
            //CASE: removing tail
            if( this.cursor.equals(this.tail) && !(this.cursor.equals(this.head)) ){
                removed = this.cursor;
                this.tail.getPrev().setNext(null);
                this.tail = this.tail.getPrev();
                this.cursor = this.tail;
            }
            //CASE: removing head
            else if( this.cursor.equals(this.head) && !(this.cursor.equals(this.tail)) ){
                removed = this.cursor;
                this.head.getNext().setPrev(null);
                this.head = this.head.getNext();
                this.cursor = this.head;
            }
            //CASE: removing
            //CASE: removing only existing Train
            else if( this.cursor.equals(this.head) && this.cursor.equals(this.tail) ){
                removed = this.cursor;
                this.cursor = null;
                this.head = null;
                this.tail = null;
            }
            //CASE: Train is between two others
            else {
                removed = this.cursor;
                this.cursor.getNext().setPrev(this.cursor.getPrev());
                this.cursor.getPrev().setNext(this.cursor.getNext());
                this.cursor = this.cursor.getNext();
            }
        }
        else
            removed = null;

        return removed;
    }

    /**
     * Brief: This method sets the cursor to the next Train in the Track List.
     * @return: If the method returns false, the cursor could not be moved forward. Therefore either the Track
     * List is empty, or the current Train is the 'tail' of the List. If it returns true, the cursor was advanced successfully.
     */
    public boolean selectNextTrain(){

        boolean d = false;
        if( this.cursor instanceof Train && !(this.cursor.equals(this.tail)) ){
            if( this.cursor.getNext() != null ) {
                this.cursor = this.cursor.getNext();
                d = true;
            }
        }
        return d;
    }

    /**
     * Brief: This method sets the cursor to the previous Train in the Track List.
     * @return: If the method returns false, the cursor could not be moved backward. Therefore either the Track List is
     * empty, or the current Train is the 'head' of the List. If it returns true, the cursor was retracted successfully.
     */
    public boolean selectPrevTrain() {

        boolean boo = false;
        if(this.cursor instanceof Train && !(this.cursor.equals(this.head)) ){
            if (this.cursor.getPrev() != null){
                this.cursor = this.cursor.getPrev();
                boo = true;
            }
        }

        return boo;

    }

    /**
     * Brief: This method prints the Train at the current cursor.
     * @throws NoExistingTrainException: Thrown if there is no Train selected (i.e. Track List cursor is null).
     */
    public void printSelectedTrain() throws NoExistingTrainException{

        if( this.head.equals(null) )
            System.out.println("Cannot print train; Track list is empty.");
        else
            if( this.cursor instanceof Train) {
                System.out.println(this.getCursor().toString());
            }
            else
                throw new NoExistingTrainException();
    }

    /**
     * Brief: This method builds a String consisting of a formatted List of the current Track object.
     * @return: The method returns the formatted List of the current Track.
     */
     public String toString(){
        String tracks = "";
        Train temp = this.cursor;
        Train tempCursor = this.head;
        if( tempCursor instanceof Train ){
            if( numTrains == 1 ){
                tracks += String.format("    *%12d           %-6s                     %6d      %12d\n", tempCursor.getTrainNumber(), tempCursor.getDestination(), tempCursor.getArrivalTime(), (tempCursor.getTransferTime()+tempCursor.getArrivalTime()) );
                //("    *          " + this.getCursor().getTrainNumber()+ "                    " + this.getCursor().getDestination() + "                  " + this.getCursor().getArrivalTime() + "                "  + this.getCursor().getTransferTime() + "\n");
            }
            else if( numTrains > 1){
                while ( tempCursor.getNext() != null ) {
                    if( tempCursor.equals(temp) && tempCursor!=null){
                        tracks += String.format("    *%12d           %-6s                     %6d      %12d\n", tempCursor.getTrainNumber(), tempCursor.getDestination(), tempCursor.getArrivalTime(),(tempCursor.getTransferTime()+tempCursor.getArrivalTime()));
                        tempCursor = tempCursor.getNext();
                    }
                    else {
                        tracks += String.format("     %12d           %-6s                     %6d      %12d\n", tempCursor.getTrainNumber(), tempCursor.getDestination(), tempCursor.getArrivalTime(), (tempCursor.getTransferTime()+tempCursor.getArrivalTime()));
                        //("                   " + this.getCursor().getTrainNumber()+ "           " + this.getCursor().getDestination() + "                  " + this.getCursor().getArrivalTime() + "                "  + this.getCursor().getTransferTime() + "\n");
                        tempCursor = tempCursor.getNext();
                    }

                }
            }
        }

        return tracks;
    }
}
