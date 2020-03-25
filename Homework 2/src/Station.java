import sun.jvm.hotspot.debugger.posix.elf.ELFSectionHeader;
/*
Anthony Pizzulli
111990335
R08
 */

import java.util.Scanner;
public class Station {

    private Track head;
    private Track tail;
    private Track cursor;
    public static double numTracks;
    public boolean added;

    /**
     * Brief: This is the default constructor for the Station class. It sets all pointers to null.
     */
    public Station(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Brief: This is the getter method for the Track cursor.
     * @return: The Track at the current cursor.
     */
    public Track getCursor(){
        return this.cursor;
    }

    /**
     * Brief: This method adds the 'newTrack' to its spot in the Track List based on its Track Number.
     * @param newTrack: The Track to be added to the List
     * @throws TrackAlreadyExistsException: Thrown if there is already a Track in the Station List with the given Track number.
     */
    public void addTrack(Track newTrack) throws TrackAlreadyExistsException{

        if( !(this.head instanceof Track) ){
            head = newTrack;
            tail = newTrack;
            cursor = newTrack;
            this.added = true;
        }
         else if( this.head.getTrackNumber() == newTrack.getTrackNumber() || this.tail.getTrackNumber() == newTrack.getTrackNumber() ){
            throw new TrackAlreadyExistsException();
        }

        else if( newTrack.getTrackNumber() > this.tail.getTrackNumber() ){
            this.tail.setNext(newTrack);
            newTrack.setPrevious(this.tail);
            this.tail = newTrack;
            this.cursor = newTrack;
            this.added = true;
        }
        else if( newTrack.getTrackNumber() < this.head.getTrackNumber() ){
            newTrack.setNext(this.head);
            this.head.setPrevious(newTrack);
            this.head = newTrack;
            this.cursor = newTrack;
            this.added = true;
        }

        else{
            this.cursor = this.head;
            while( (this.cursor.getNext()!=(null)) ){
                if( this.cursor.getTrackNumber() == newTrack.getTrackNumber() ){
                    throw new TrackAlreadyExistsException();
                }
                if( this.cursor.getTrackNumber() < newTrack.getTrackNumber() ){
                    if( this.cursor.getNext().getTrackNumber() > newTrack.getTrackNumber() ){
                        newTrack.setPrevious(this.cursor);
                        newTrack.setNext(this.cursor.getNext());
                        this.cursor.getNext().setPrevious(newTrack);
                        this.cursor.setNext(newTrack);
                        this.cursor = newTrack;
                        added = true;
                        break;
                    }
                    else{
                        this.cursor = this.cursor.getNext();
                    }
                }
                else{
                    this.cursor = this.cursor.getNext();
                }
            }
        }
    }

    /**
     * Brief: This method removes the selected Track from the Station List, and if applicable, sets the cursor to the Track following the one being removed.
     * @return: The Track that has been removed from the Station List.
     */
    public Track removeSelectedTrack() {

        Track removed = new Track();

        if( (this.cursor instanceof Track )){
            //CASE: removing tail
            if( this.cursor.equals(this.tail) && !(this.cursor.equals(this.head)) ){
                removed = this.cursor;
                this.tail.getPrevious().setNext(null);
                this.tail = this.tail.getPrevious();
                this.cursor = this.tail;
            }
            //CASE: removing head
            else if( this.cursor.equals(this.head) && !(this.cursor.equals(this.tail)) ){
                removed = this.cursor;
                this.head.getNext().setPrevious(null);
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
                this.cursor.getNext().setPrevious(this.cursor.getPrevious());
                this.cursor.getPrevious().setNext(this.cursor.getNext());
                this.cursor = this.cursor.getNext();
            }
            numTracks--;
        }
        else
            removed = null;

        return removed;
    }

    /**
     * This method prints the Track at the current cursor.
     * @throws NoExistingTrackException: Thrown if there is no Track at the cursor (i.e. the cursor is null).
     */
    public void printSelectedTrack() {
        Track temppCursor = this.cursor;
        if( temppCursor instanceof Track){
            System.out.printf("Track %d (%.2f%% Utilization Rate):\nSelected  Train Number     Train Destination         Arrival Time     Departure Time  \n" +
                    "-------------------------------------------------------------------------------------\n", temppCursor.getTrackNumber(), temppCursor.getUtilizationRate()  );
            if( numTracks == 1 ){
                    System.out.println(temppCursor.toString());
                }

            else if( numTracks > 1){
                while( temppCursor.getNext()!= null){
                    System.out.println(temppCursor.toString());
                    temppCursor = temppCursor.getNext();
                }
            }
        }
    }

    /**
     * Brief: This method builds a String consisting of a formatted List of the Station object.
     * @return String: Formatted List of Tracks in the Station.
     */
    @Override public String toString(){

        String station = "";
        if(cursor instanceof Track){
            System.out.printf("Track %d (%.2f%% Utilization Rate):\nSelected  Train Number     Train Destination         Arrival Time     Departure Time  \n" +
                    "-------------------------------------------------------------------------------------\n", cursor.getTrackNumber(), cursor.getUtilizationRate()  );
            if( cursor.getNumTrains() < 2 ){
                station+=(cursor.toString());
            }
            else
                cursor = head;
            while( (cursor.getCursor().getNext() != null) ){
                station +=(cursor.getCursor().toString());
                cursor.selectNextTrain();
            }
        }
            return station;
        }


    /**
     * Brief: Moves the selected Track cursor to the node with the same trackNumber as the given trackToSelect parameter (if it exists).
     * @param trackToSelect: The Track to select (based on its 'trackNumber')
     * @return: If the return is true, there exists a Track with the given 'trackNumber' and the cursor updates to its position. Otherwise, the selected Track object
     * remains the same.
     * @throws TrackDoesNotExistException: Thrown if there is no Track found
     */
    public boolean selectTrack(int trackToSelect) {
        Track tempCursor = new Track();
        boolean found = false;
        if( this.head instanceof Track ){
            tempCursor = this.cursor;
            this.cursor = this.head;
            while( this.cursor.getNext()!=null ){
                if( this.cursor.getTrackNumber() == trackToSelect ){
                    this.cursor = this.cursor;
                    found = true;
                    break;
                }
                else
                    this.cursor = this.cursor.getNext();
            }
        }
        if(!found){
            this.cursor = tempCursor;
        }
        return found;

    }

    /**
     * Brief: The main method serves as the driver for the Linked Lists, handles input from the user, and prints the menu.
     * @param args
     */
    public static void main( String []args){

        Train nextTrain = new Train();
        Train previousTrain = new Train();
        String input = "";
        Scanner userInput = new Scanner(System.in);
        //Track trackOne = new Track();
        Station s = new Station();

        while( !(input.toLowerCase().equals("q")) ){
            System.out.print("|-----------------------------------------------------------------------------|\n" +
                    "| Train Options                       | Track Options                         |\n" +
                    "|    A. Add new Train                 |    TA. Add Track                      |\n" +
                    "|    N. Select next Train             |    TR. Remove selected Track          |\n" +
                    "|    V. Select previous Train         |    TS. Switch Track                   |\n" +
                    "|    R. Remove selected Train         |   TPS. Print selected Track           |\n" +
                    "|    P. Print selected Train          |   TPA. Print all Tracks               |\n" +
                    "|-----------------------------------------------------------------------------|\n" +
                    "| Station Options                                                             |\n" +
                    "|   SI. Print Station Information                                             |\n" +
                    "|    Q. Quit                                                                  |\n" +
                    "|-----------------------------------------------------------------------------|\n");
            System.out.println("Choose an operation: ");
             input = userInput.nextLine();

            switch( input.toLowerCase() ){

                case "a":

                    System.out.println("Enter train number: ");
                    int n = Integer.parseInt(userInput.nextLine());
                    System.out.println("Enter train destination: ");
                    String t = userInput.nextLine();
                    System.out.println("Enter train arrival time: ");
                    int time = Integer.parseInt(userInput.nextLine());
                    System.out.println("Enter train transfer time: ");
                    int tt = Integer.parseInt(userInput.nextLine());
                    if(tt == 60){
                        tt = 100;
                    }
                    Train newTrain = new Train(nextTrain, previousTrain, n, t, time, tt);
                    try{
                        if( s.cursor instanceof Track ) {
                            s.cursor.addTrain(newTrain);
                            System.out.println("Train No. " + newTrain.getTrainNumber() + " to " + newTrain.getDestination() + " added to Track " + s.cursor.getTrackNumber() + "." );
                            s.cursor.numTrains++;
                            s.cursor.setUtilizationRate((s.cursor.numTrains/1440)*1000);
                        }
                        else
                            throw new NoExistingTrackException();
                    }
                    catch (InvalidArrivalTimeException i){
                        System.out.println("Train not added: Invalid arrival time.");
                        break;
                    }
                    catch (NoExistingTrackException no){
                        System.out.println("Train not added: There is no Track to add the Train to!");
                        break;
                    }
                    catch(TrainAlreadyExistsException tl){
                        System.out.println("Train not added: There is already a Train with that number!");
                    }
                    break;

                case "n":

                    boolean b = s.cursor.selectNextTrain();
                    if(b){
                        System.out.println("Cursor has been moved to next train.");
                    }
                    else
                        System.out.println("Selected train not updated: Already at end of Track list.");
                    break;

                case "v":

                    boolean c = s.cursor.selectPrevTrain();
                    if(c) {
                        System.out.println("Cursor has been moved to previous train.");
                    }
                    else {
                        System.out.println("Selected train not updated: At front of Track list.");
                    }
                    break;

                case "r":
                    Train r = s.cursor.removeSelectedTrain();
                    System.out.println("Train No. " + r.getTrainNumber() + " to " + r.getDestination() + " has been removed from Track " + s.cursor.getTrackNumber() + "." );
                    s.cursor.numTrains--;
                    s.cursor.setUtilizationRate((s.cursor.numTrains/1440)*1000);
                    break;

                case "p":
                    try{
                        s.cursor.printSelectedTrain();
                    }
                    catch( NoExistingTrainException nt){
                        System.out.println("No train at current selection.");
                    }
                    break;

                case "ta":

                    System.out.println("Enter track number: ");
                    int i = Integer.parseInt(userInput.nextLine());
                    Track tr = new Track(i);
                    try{
                        s.addTrack(tr);
                        System.out.println("Track " + i + " added to the Station.");
                        numTracks++;
                    }
                    catch (TrackAlreadyExistsException ta){
                        System.out.println("Track not added: Track " + tr.getTrackNumber() + " already exists.");
                    }
                    break;

                case "tr":

                    Track rem = s.removeSelectedTrack();
                    if( !(rem instanceof Track) )
                        System.out.println("No Track selected.");
                    else
                        System.out.println("Closed Track " + rem.getTrackNumber());
                    break;

                case "ts":

                    System.out.println("Enter the Track number: ");
                    int j = Integer.parseInt(userInput.nextLine());

                        boolean be = s.selectTrack(j);
                        if(be)
                            System.out.println("Switched to Track " + j + ".");
                        else
                            System.out.println("Could not switch to Track " + j + ": Track " + j + " does not exist.");

                    break;

                case "tps":

                    s.printSelectedTrack();
                    break;

                case "tpa":

                    if (s.cursor instanceof Track) {
                        if( s.numTracks > 1 ){
                            while( s.cursor.getNext()!=null) {
                                s.printSelectedTrack();
                            }
                        }
                        else
                            s.printSelectedTrack();;
                    }

                    break;

                case "si":

                    if(s.numTracks == 1){
                        System.out.println("Station (1 track):");
                        System.out.printf("\tTrack %d: %d trains arriving (%.2f%% Utilization Rate)\n", s.cursor.getTrackNumber(), (int)s.cursor.numTrains, s.cursor.getUtilizationRate()  );
                    }
                    else if( numTracks >1 ){
                        System.out.println("Station (" + (int)s.numTracks + " tracks):");
                        while( s.cursor.getNext()!=null  ){
                            System.out.printf("\tTrack %d: %d trains arriving (%.2f%% Utilization Rate)\n", s.cursor.getTrackNumber(), (int)s.cursor.numTrains, s.cursor.getUtilizationRate());
                            s.cursor = s.cursor.getNext();
                        }
                    }

                    break;

                case "q":
                    System.out.println("Program terminating normally...");
                    break;
                default:
                    break;

            }
        }
    }
}
