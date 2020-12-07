/*
Anthony Pizzulli
111990335
R08
 */

import java.util.Comparator;
public class BloodTypeComparator implements Comparator<Patient> {

    /**
     * Brief: Compare method for BloodTypeComparator, which compares bloodTypes
     * @param a Patient: First Patient to be compared
     * @param b Patient: Second Patient with which to compare the first
     * @return int: Returns 0 if the given Patients have same blood type, 1 if first Patient's blood type comes first
     *              alphabetically and -1 otherwise (utilizes java's default String compareTo method)
     */
    public int compare( Patient a, Patient b ){
        String btA = a.getBloodType().getBloodType();
        String btB = b.getBloodType().getBloodType();
        return ( btA.compareTo(btB));
    }

}
