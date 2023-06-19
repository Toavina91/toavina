package Model;
import java.util.*;


public class Divorce {
	
	
    public Date dateDivorce;
    
    public Mariage mariage;
    

    public Divorce(Date d) {
    	dateDivorce = d;
    }

    
    public void divorcer(Mariage x) {
    }
    
    
    public Date getDate() {
    	return dateDivorce;
    }
    

}