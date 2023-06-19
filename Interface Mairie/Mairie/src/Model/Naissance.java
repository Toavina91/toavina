package Model;
import java.util.*;


public class Naissance {
	
    public Date dateNaiss;

    
    public Homme homme;

 
    public Femme femme;


    public Citoyen[] citoyen;

  
    public Mairie mairie;

    

    public Naissance(Date d) {
    	dateNaiss = d;
    }
    
    
    
    public Date getDate() {
    	return dateNaiss;
    }
    
    public Homme getPere() {
    	return homme;
    }
    
    public Femme getMere() {
    	return femme;
    }

}