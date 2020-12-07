/*
Anthony Pizzulli
111990335
R08
 */

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
public class TransplantDriver implements Serializable{

    public static final String DONOR_FILE = "donors.txt";
    public static final String RECIPIENT_FILE = "recipients.txt";


    /**
     * Brief: Main driver class for TransplantGraph and Patient, processes input, output and interaction with user
     * @param args
     */
    public static void main(String[] args)  {

        Scanner in = new Scanner(System.in);
        String input = "";
        TransplantGraph tG = new TransplantGraph();
        try {
            FileInputStream f = new FileInputStream("transplant.obj");
            ObjectInputStream objIn = new ObjectInputStream(f);
            tG = (TransplantGraph) objIn.readObject();
            objIn.close();
            System.out.println("Loading data from transplant.obj...\n");
        }catch( IOException io ) {
            System.out.println("transplant.obj does not exist. Creating new TransplantGraph object...\n");
            tG = buildFromFiles(DONOR_FILE, RECIPIENT_FILE);
            System.out.println("Loading data from 'recipients.txt'...");
            System.out.println("Loading data from 'donors.txt'...\n");
        }catch(java.lang.ClassNotFoundException cl){

        }

            while(!(input.equals("q"))){
                System.out.println("Menu:\n" +
                        "    (LR) - List all recipients\n" +
                        "    (LO) - List all donors\n" +
                        "    (AO) - Add new donor\n" +
                        "    (AR) - Add new recipient\n" +
                        "    (RO) - Remove donor\n" +
                        "    (RR) - Remove recipient\n" +
                        "    (SR) - Sort recipients\n" +
                        "    (SO) - Sort donors\n" +
                        "    (Q) - Quit\n");
                System.out.println("Please select an option: ");

                input = in.nextLine().toLowerCase();
                switch(input){

                    case "lr":
                        tG.printAllRecipients();
                        break;
                    case "lo":
                        tG.printAllDonors();
                        break;
                    case "ao":
                        System.out.println("Please enter the organ donor name: ");
                        String name = in.nextLine();
                        System.out.println("Please enter the organs " + name + " is donating: ");
                        String organ = in.nextLine();
                        System.out.println("Please enter the blood type of John Doe: ");
                        BloodType bT = new BloodType(in.nextLine());
                        System.out.println("Please enter the age of John Doe: ");
                        int age = Integer.parseInt(in.nextLine());
                        Patient newPatient = new Patient( name, organ, age, bT, tG.getDonors().size(), true);
                        tG.addDonor(newPatient);
                        System.out.println("The organ donor with ID " +  newPatient.getID() + " was successfully added to the donor list!\n");
                        break;
                    case "ar":
                        System.out.println("Please enter new recipient's name: ");
                        String pName = in.nextLine();
                        System.out.println("Please enter the recipient's blood type: ");
                        BloodType bLT = new BloodType(in.nextLine());
                        System.out.println("Please enter the recipient's age: ");
                        int n = Integer.parseInt(in.nextLine());
                        System.out.println("Please enter the organ needed: ");
                        String o = in.nextLine();
                        Patient pa = new Patient(pName, o, n, bLT, tG.getRecipients().size(), false);
                        tG.addRecipient(pa);
                        System.out.println(pName + " is now on the organ transplant waitlist!\n");
                        break;
                    case "ro":
                        System.out.println("Please enter the name of the organ donor to remove: ");
                        String nn = in.nextLine();
                        try{
                            tG.removeDonor(nn.toLowerCase());
                        }catch(PatientDoesNotExistException e){
                            System.out.println("Patient cannot be found in database.");
                        }
                        System.out.println(nn + " was removed from the organ donor list.\n");
                        break;
                    case "rr":
                        System.out.println("Please enter the name of the recipient to remove: ");
                        String na = in.nextLine();
                        try{
                            tG.removeRecipient(na.toLowerCase());
                            System.out.println(na + " was removed from the organ transplant waitlist.\n");
                        }catch(PatientDoesNotExistException p) {
                            System.out.println("Patient cannot be found in database.\n");
                        }
                        break;
                    case "sr":
                        String inP= "";
                        while( !(inP.toLowerCase().equals("q")) ){
                            System.out.println("(I) Sort by ID\n" +
                                    "(N) Sort by Number of Donors\n" +
                                    "(B) Sort by Blood Type\n" +
                                    "(O) Sort by Organ Needed\n" +
                                    "(Q) Back to Main Menu\n");
                            System.out.println("Please select an option: ");
                            inP = in.nextLine();
                            switch( inP.toLowerCase() ){
                                case "i":
                                    tG.getRecipients().sort(Patient::compareTo);
                                    tG.printAllRecipients();
                                    break;
                                case "n":
                                    Collections.sort(tG.getRecipients(), new NumConnectionsComparator());
                                    tG.printAllRecipients();
                                    break;
                                case "b":
                                    Collections.sort(tG.getRecipients(), new BloodTypeComparator());
                                    tG.printAllRecipients();
                                    break;
                                case "o":
                                    Collections.sort(tG.getRecipients(), new OrganComparator());
                                    tG.printAllRecipients();
                                    break;
                                case "q":
                                    Collections.sort(tG.getRecipients(), Patient::compareTo);
                                    break;
                                default:
                                    break;
                            }
                        }

                        break;
                    case "so":
                        System.out.println("(I) Sort by ID\n" +
                                "(N) Sort by Number of Donors\n" +
                                "(B) Sort by Blood Type\n" +
                                "(O) Sort by Organ Needed\n" +
                                "(Q) Back to Main Menu\n");
                        System.out.println("Please select an option: ");

                        String inPu = in.nextLine();
                        switch( inPu.toLowerCase() ){
                            case "i":
                                tG.getDonors().sort(Patient::compareTo);
                                tG.printAllDonors();
                                break;
                            case "n":
                                Collections.sort(tG.getDonors(), new NumConnectionsComparator());
                                tG.printAllDonors();
                                break;
                            case "b":
                                Collections.sort(tG.getDonors(), new BloodTypeComparator());
                                tG.printAllDonors();
                                break;
                            case "o":
                                Collections.sort(tG.getDonors(), new OrganComparator());
                                tG.printAllDonors();
                                break;
                            case "q":
                                Collections.sort(tG.getDonors(), Patient::compareTo);
                                tG.printAllDonors();
                                break;
                            default:
                                break;
                        }
                        break;
                    case "q":
                        try{
                            FileOutputStream fO = new FileOutputStream("transplant.obj");
                            ObjectOutputStream out = new ObjectOutputStream(fO);
                            out.writeObject(tG);
                            out.close();
                        }catch(java.io.IOException io){

                        }
                        System.out.println("Writing data to transplant.obj...\n");
                        System.out.println("Program terminating normally...");
                        break;

                    default:
                        break;
                }

            }

        }

    /**
     * Brief: Method to process information from input file and populate the database
     * @param donorFile String: The supplied "DONOR_FILE," which contains information regarding the donors
     * @param recipientFile String: The supplied "RECIPIENT_FILE," which contains information regarding the recipients
     * @return TransplantGraph: The TransplantGraph constructed from the two files, which will now contain ArrayLists
     *                          recipients and donors and the connections matrix
     */
    public static TransplantGraph buildFromFiles(String donorFile, String recipientFile) {

        TransplantGraph transGraph = new TransplantGraph();
        ArrayList<Patient> tempRecips = new ArrayList<Patient>();
        ArrayList<Patient> tempDonors = new ArrayList<Patient>();
        Scanner scanner = new Scanner(System.in);

        //PROCESSING RECIPIENTS{
        try {
            scanner = new Scanner(new File(recipientFile));
        } catch (FileNotFoundException f) {
            System.out.println("Cannot find file 'RECIPIENT_FILE'");
        }
        while (scanner.hasNextLine()) {
            String[] recips = scanner.nextLine().split(",");
            Patient p = new Patient(recips[1].substring(1), recips[3].substring(1), Integer.parseInt(recips[2].substring(1)), new BloodType(recips[4].substring(1)), Integer.parseInt(recips[0]), false);
            transGraph.getRecipients().add(p);
        }
        //}

        //PROCESSING DONORS{
        try{
            scanner = new Scanner(new File(donorFile));
        }catch(FileNotFoundException f){
            System.out.println("Cannot find 'DONOR_FILE'");
        }
        while(scanner.hasNextLine()){
            String [] donors = scanner.nextLine().split(",");
            Patient d = new Patient( donors[1].substring(1), donors[3].substring(1), Integer.parseInt(donors[2].substring(1)), new BloodType(donors[4].substring(1)), Integer.parseInt(donors[0]) , true);
            transGraph.getDonors().add(d);
        }
        //}
        transGraph.processConnections();
        return transGraph;
    }
}



