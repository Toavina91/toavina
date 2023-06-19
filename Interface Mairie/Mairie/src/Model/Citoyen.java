package Model;
import java.util.*;

/**
 * 
 */
public class Citoyen {
	
    public long id;

    
    public String nom;

 
    public String prenom;

 
    public Date dateNaiss;

   
    public char sexe;

 
    public Mairie mairie;

    
    public long idConjoint;
    
    

    public Citoyen(long idt, String p, String n, Date d, char s, long idtC) {
    	id = idt;
    	nom = p;
    	prenom = n;
    	dateNaiss = d;
    	sexe = s;
    	idConjoint = idtC;
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
    
    public String getPrenomNomConjoint() {
        long idConjoint = this.getidConjoint();
        if (idConjoint == 0) {
            return "Pas de conjoint";
        } else {
            Citoyen conjoint = mairie.rechercheCitoyen(idConjoint);
            if (conjoint == null) {
                return "Conjoint inconnu";
            } else {
                return conjoint.getPrenom() + " " + conjoint.getNom();
            }
        }
   }

    
    public void setIdConjoint(long idConjoint) {
        this.idConjoint = idConjoint;
    }


    
    

}