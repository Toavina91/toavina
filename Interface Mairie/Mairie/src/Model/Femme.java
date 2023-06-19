package Model;
import java.util.*;

/**
 * 
 */
public class Femme extends Citoyen {
	
    public Citoyen citoyen;


    public Mariage mariage;


    public Femme(long idt, String p, String n, Date d, char s, long idtC) {
    	super(idt, p, n, d, s, idtC);
    }


    public Vector<Naissance> listNaissance = new Vector<Naissance>();
    
    
    public String getNom() {
    	return nom;
    }
    
    public String getPrenom() {
    	return prenom;
    }
    
    public long getIdentifiant() {
    	return id;
    }
    
    public Date getDateNaissance() {
    	return dateNaiss;
    }
    
    public char getSexe() {
    	return sexe;
    }
    
    public long getidConjoint() {
    	return idConjoint;
    }

}