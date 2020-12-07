/*
Anthony Pizzulli
111990335
R08
 */
import java.io.Serializable;
public class BloodType implements Serializable {

    private String bloodType;


    /**
     * Brief: Default constructor for BloodType class
     * @param s String: String value of blood type (either A, B, AB, or O)
     */
    public BloodType( String s ){
        this.bloodType = s;
    }

    /**
     * Brief: Getter method for bloodType instance variable
     * @return String: Given Patient's blood type (given as a String)
     */
    public String getBloodType(){
        return this.bloodType;
    }

    /**
     * Brief: Setter method for bloodType instance variable
     * @param s String: Updated blood type for given Patient
     */
    public void setBloodType( String s){
        this.bloodType = s;
    }

    /**
     * Brief: Method to determine compatibility of two given Patients' blood types
     * @param recipient Patient: Patient who is to receive an organ and blood from the donor
     * @param donor Patient: Patient who is to donate an organ and blood to the recipient
     * @return boolean: True if the recipient & donor are compatible and false otherwise
     */
    public static boolean isCompatible(BloodType recipient, BloodType donor){

        String recipientType = recipient.getBloodType().toLowerCase();
        String donorType = donor.getBloodType().toLowerCase();
        boolean returnB = false;
        if( recipientType.equals("o")){
            if( donorType.equals("o") ){
                returnB = true;
            }
        }
        else if( recipientType.equals("a") ){
            if( donorType.equals("o") || donorType.equals("a")) {
                returnB = true;
            }
        }
        else if( recipientType.equals("b")){
            if( donorType.equals("o") || donorType.equals("b")){
                returnB = true;
            }
        }
        else if( recipientType.equals("ab")) {
            returnB = true;
        }
        return returnB;
    }
}
