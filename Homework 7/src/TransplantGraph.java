/*
Anthony Pizzulli
111990335
R08
 */
import java.io.Serializable;
import java.util.ArrayList;


public class TransplantGraph implements Serializable {


    public static final int MAX_PATIENTS = 100;
    private ArrayList<Patient> donors = new ArrayList<>(MAX_PATIENTS);
    private ArrayList<Patient> recipients = new ArrayList<>(MAX_PATIENTS);
    private boolean[][] connections = new boolean[MAX_PATIENTS][MAX_PATIENTS];

    /**
     * Brief: Getter method for donors ArrayList
     * @return ArrayList<Patient>: The ArrayList containing recipients in the given TransplantGraph
     */
    public ArrayList<Patient> getDonors(){
        return this.donors;
    }

    /**
     * Brief: Getter method for donors ArrayList
     * @return ArrayList<Patient>: The ArrayList containing donors in the given TransplantGraph
     */
    public ArrayList<Patient> getRecipients(){
        return this.recipients;
    }

    /**
     * Brief: Getter method for connections matrix
     * @return boolean[][]: The matrix containing boolean values regarding whether the recipient at the given column
     *                      and the donor at the given row have compatible blood types
     */
    public boolean[][] getConnections(){
        return this.connections;
    }

    /**
     * Brief: Method used to add a Patient to the recipient list and process the connections matrix to account for this new addition
     * @param patient Patient: The new Patient to be added
     */
    public void addRecipient(Patient patient){
        recipients.add(patient);
        processConnections();
    }

    /**
     * Brief: Method used to add a Patient to the donor list and process the connections matrix to account for this new addition
     * @param patient Patient: The new Patient to be added
     */
    public void addDonor( Patient patient){
        donors.add(patient);
        processConnections();
    }

    /**
     * Brief: Method used to remove the Patient with the given name from the recipient list
     * @param name String: The name of the Patient to be removed
     * @throws PatientDoesNotExistException: Thrown if the given Patient does not exist in the recipient list
     */
    public void removeRecipient(String name) throws PatientDoesNotExistException{

        boolean found = false;
        int removeIndex = 0;
        Patient patientToBeRemoved = new Patient();
        for( Patient p: recipients ){
            if ( p.getName().toLowerCase().equals(name) ) {
                patientToBeRemoved = p;
                found = true;
                break;
            }
        }

        if(!found)
            throw new PatientDoesNotExistException();

        removeIndex = recipients.indexOf(patientToBeRemoved);
        recipients.remove(patientToBeRemoved);
        for( int row = 0; row < donors.size(); row++ ){
            int i = 0;
            if( (removeIndex + 1) != recipients.size() ) {
                for( i = removeIndex; i < recipients.size(); i++) {
                    connections[row][i] = connections[row][i + 1];
                    recipients.get(i).setID(i);
                }
            }
            else {
                connections[row][i] = false;
            }
        }

    }

    /**
     * Brief: Method used to remove the Patient with the given name from the donor list
     * @param name String: Name of the Patient to be removed
     * @throws PatientDoesNotExistException: Thrown if the given Patient does not exist in the donor list
     */
    public void removeDonor(String name) throws PatientDoesNotExistException{
        int removeIndex = 0;
        boolean found = false;
        Patient DonorToBeRemoved = new Patient();
        for( Patient p: donors ){
            if ( p.getName().toLowerCase().equals(name) ) {
                DonorToBeRemoved = p;
                found = true;
                break;
            }
        }
        if(!found)
            throw new PatientDoesNotExistException();

        removeIndex = donors.indexOf(DonorToBeRemoved);
        donors.remove(DonorToBeRemoved);
        for( int col = 0; col < recipients.size(); col++  ){
            int i = 0;
            if( (removeIndex + 1) != donors.size() ) {
                for( i = removeIndex; i < donors.size(); i++) {
                    connections[i][col] = connections[i+1][col];
                    donors.get(i).setID(i);
                }
            }
            else
                connections[i][col] = false;
        }
    }

    /**
     * Brief: Method used to process compatibility for each recipient and donor in the lists and update the connections matrix
     *        (called after a new Patient is added)
     */
    public void processConnections(){
        int maxDonorID = donors.size();
        int maxRecipientID = recipients.size();

        for( int row = 0; row < maxDonorID; row++ ){
            for( int col = 0; col < maxRecipientID; col++){
                BloodType recipientBloodType = recipients.get(col).getBloodType();
                BloodType donorBloodType = donors.get(row).getBloodType();
                String recipientOrgan = recipients.get(col).getOrgan().toLowerCase();
                String donorOrgan = donors.get(row).getOrgan().toLowerCase();
                connections[row][col] = BloodType.isCompatible(recipientBloodType, donorBloodType) && (recipientOrgan.equals(donorOrgan));
                if( connections[row][col] ){
                    recipients.get(col).setNumConnections(recipients.get(col).getNumConnections()+1);
                    donors.get(row).setNumConnections(donors.get(row).getNumConnections()+1);
                }
            }
        }

    }

    /**
     * Brief: Method to print formatted table with each Patient's information in recipient list
     */
    public void printAllRecipients(){

        ArrayList<Integer> donorIDs = new ArrayList<>();
        String s = "Index | Recipient Name     | Age | Organ Needed  | Blood Type | Donor ID\n" +
                "========================================================================\n";
        for( Patient p: recipients ){

            donorIDs.clear();
            int currentRecipientID = p.getID();
            int maxDonorID = donors.size();
            int maxRecID = recipients.size();

            for( int row = 0; row <= maxDonorID; row++ ){
                if( connections[row][currentRecipientID] ) {
                    donorIDs.add(row);
                }
            }
            s+= p.toString();
            for( int i: donorIDs){
                s += i;
                if( donorIDs.indexOf(i) != (donorIDs.size()-1) )
                    s+= ", ";
            }
            s+= "\n";
        }
        System.out.println(s);
    }

    /**
     * Brief: Method used to print formatted table with each Patient's information in donor list
     */
    public void printAllDonors(){

        String s = "Index | Donor Name         | Age | Organ Donated | Blood Type | Recipient IDs\n" +
                "=============================================================================\n";
        for(Patient p: donors){

            ArrayList<Integer> recipIDs = new ArrayList<>();
            int maxRecip = recipients.get(recipients.size()-1).getID();
            int id = p.getID();

            for( int col = 0; col <= maxRecip; col++ ){
                if( connections[id][col] ) {
                    recipIDs.add(col);
                }
            }

            s+= p.toString();
            for( int n: recipIDs ){
                s += n;
                if( recipIDs.indexOf(n) != (recipIDs.size()-1) )
                    s += ", ";
            }
            s+="\n";
        }
        System.out.println(s);
    }
}
