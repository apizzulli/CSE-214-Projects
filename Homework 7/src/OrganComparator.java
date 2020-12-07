/*
Anthony Pizzulli
111990335
R08
 */

import java.util.Comparator;

public class OrganComparator implements Comparator<Patient> {

    /**
     * Brief: Compare method for OrganComparator
     * @param a Patient: First Patient to be compared
     * @param b Patient: Second Patient with which to compare the first
     * @return int: Returns 0 if the given Patients are to donate the same organ, 1 if the organ the first Patient
     *              is donating comes first alphabetically and -1 otherwise (utilizes java's default String compareTo method)
     */
    public int compare( Patient a, Patient b ){
        return( a.getOrgan().toLowerCase().compareTo(b.getOrgan().toLowerCase()) );
    }
}
