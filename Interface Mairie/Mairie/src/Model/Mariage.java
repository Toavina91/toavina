package Model;
import java.util.*;


public class Mariage {
	
    public Date dateMariage;


    public Citoyen homme;


    public Citoyen femme;


    public Mairie mairie;


    public Divorce divorce;


    public Mariage(Date d, Citoyen h, Citoyen f, Mairie m) {
    	dateMariage = d;
    	homme = h;
    	femme = f;
    	mairie = m;
    }
    
    
    
    
    
    
    public boolean mariageEnCours() {
    	return true;
    }
    
    
    
    public Date getDateMariage() {
    	return dateMariage;
    }
    
    public Citoyen getEpoux() {
    	return homme;
    }
    
    public Citoyen getEpouse() {
    	return femme;
    }
    
    public Mairie getMairie() {
    	return mairie;
    }
    
    
    public void changerNom(Femme f) {
 
    }


    

}