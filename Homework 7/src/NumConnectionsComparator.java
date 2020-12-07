/*
Anthony Pizzulli
111990335
R08
 */
import java.util.Comparator;

public class NumConnectionsComparator implements Comparator<Patient> {

    /**
     * Brief: Compare method for NumConnectionsComparator
     * @param a Patient: First Patient to be compared
     * @param b Patient: Second Patient with which to compare the first
     * @return int: Returns 0 if the given Patients have the same number of connections, 1 if the first Patient has a greater
     *              number of connections and -1 otherwise
     */
    @Override public int compare( Patient a, Patient b){
        if( a.getNumConnections() == b.getNumConnections() )
            return 0;
        else if( a.getNumConnections() > b.getNumConnections() )
            return 1;
        else
            return -1;
    }
}

