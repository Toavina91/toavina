package Model;
import java.util.*;


public class Mairie {
	


    public Mairie() {
    	Citoyen john = new Citoyen(1, "Mbappe", "John", new Date(), 'M', 2);
    	Citoyen jane = new Citoyen(2, "Mbappe", "Jane", new Date(), 'F', 1);
    	Citoyen emily = new Citoyen(6, "Vinicius", "Emily", new Date(), 'F', 7);
    	Citoyen andrew = new Citoyen(7, "Vinicius", "Andrew", new Date(), 'M', 6);
    	Citoyen samuel = new Citoyen(13, "Haaland", "Samuel", new Date(), 'M', 14);
    	Citoyen elizabeth = new Citoyen(14, "Haaland", "Elizabeth", new Date(), 'F', 13);
    	listCitoyens.add(john);
        listCitoyens.add(jane);
        listCitoyens.add(emily);
        listCitoyens.add(andrew);
        listCitoyens.add(samuel);
        listCitoyens.add(elizabeth);
        listCitoyens.add(new Citoyen(3, "Smith", "Bob", new Date(), 'M', 0));
        listCitoyens.add(new Citoyen(4, "Brown", "Alice", new Date(), 'F', 0));
        listCitoyens.add(new Citoyen(5, "Green", "David", new Date(), 'M', 0));
        listCitoyens.add(new Citoyen(8, "Monrad", "Grace", new Date(), 'F', 0));
        listCitoyens.add(new Citoyen(9, "Williams", "Ben", new Date(), 'M', 0));
        listCitoyens.add(new Citoyen(10, "Zidjiane", "Olivia", new Date(), 'F', 0));
        listCitoyens.add(new Citoyen(11, "King", "Martin", new Date(), 'M', 0));
        listCitoyens.add(new Citoyen(12, "Thatcher", "Margaret", new Date(), 'F', 0));
        listCitoyens.add(new Citoyen(15, "Roosevelt", "Franklin", new Date(), 'M', 0));
        listCitoyens.add(new Citoyen(16, "Anthony", "Susan", new Date(), 'F', 0));

        Calendar calendar = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        Date datembappe = calendar.getTime();
        listNaissance.add(new Naissance(datembappe));
        
        Calendar c = new GregorianCalendar(2000, Calendar.JULY, 12);
        Date datevini = c.getTime();
        listNaissance.add(new Naissance(datevini));
        
        Calendar ca = new GregorianCalendar(2000, Calendar.JULY, 21);
        Date datehaaland = ca.getTime();
        listNaissance.add(new Naissance(datehaaland));
        
        
        listCitoyens.add(new Citoyen(17, "Mbappe", "Kylian", datembappe, 'M', 0));
        listCitoyens.add(new Citoyen(18, "Vinicius", "Junior", datevini, 'M', 0));
        listCitoyens.add(new Citoyen(19, "Haaland", "Erling", datehaaland, 'M', 0));
        citoyenMairie.add(new Citoyen(17, "Mbappe", "Kylian", datembappe, 'M', 0));
        citoyenMairie.add(new Citoyen(18, "Vinicius", "Junior", datevini, 'M', 0));
        citoyenMairie.add(new Citoyen(19, "Haaland", "Erling", datehaaland, 'M', 0));
        
        marier( john, jane );
        marier( andrew, emily );
        marier( samuel, elizabeth );
       
             
    }
    
    
    
    
    public Vector<Citoyen> listCitoyens = new Vector<Citoyen>();


    public Vector<Mariage> listMariage = new Vector<Mariage>();


    public Vector<Naissance> listNaissance = new Vector<Naissance>();
    
    
    public Vector<Citoyen> citoyenMairie = new Vector<Citoyen>();
    

    
    
    
    
    public void ajouterCitoyen (Citoyen x) {
    	listCitoyens.add(x);
    }
    
    public void ajouterCitoyenm(Citoyen x) {
    	citoyenMairie.add(x);
    }
    
    public void retirerCitoyen (Citoyen x) {
    	listCitoyens.remove(x);
    	
    }
    public void ajouterMariage(Mariage x) {
    	listMariage.add(x);
    }
    
    public void retirerMariage(Mariage x) {
    	listMariage.remove(x);
    }

    
    public void ajouterNaissance(Naissance x) {
    	listNaissance.add(x);
    }
    public void marier(Citoyen a, Citoyen b) {
        Mariage mariage = new Mariage(new Date(), a, b, this);
        listMariage.add(mariage);
        a.idConjoint = b.getIdentifiant();
        b.idConjoint = a.getIdentifiant();
        if (a.getSexe()== 'F') {
        	a.nom = b.getNom();
        }
        else {
        	b.nom = a.getNom();
        }
    }
    
    

    


    
    
    public Vector<Citoyen> getListeCitoyens() {
    	return listCitoyens;
    }
    
    public Vector<Citoyen> getListeCitoyen() {
    	return citoyenMairie;
    }
    
    public Vector<Mariage> getListeMariage() {
    	return listMariage;
    }

    
    public Vector<Naissance> getListeNaissance() {
    	return listNaissance;
    }
    
    
    
    
    public Citoyen rechercheCitoyen(long id) {
        // Parcourir la liste des citoyens pour trouver celui qui correspond à l'identifiant
        for (Citoyen citoyen : listCitoyens) {
            if (citoyen.getIdentifiant() == (id)) {
                return citoyen;
            }
        }
        // Si aucun citoyen ne correspond à l'identifiant, retourner null
        return null;
    }

    
    public Mariage rechercheMariage(Citoyen epoux1) {
        for (Mariage mariage : listMariage) {
            if (mariage.getEpoux() == epoux1 || mariage.getEpouse() == epoux1) {
                return mariage;
            }
        }
        return null;
    }

    
    public Naissance rechercheNaissance (Citoyen x) {
    	return null;
    }
    
    
    public long nvId() {
        return listCitoyens.size()+1;
    }

	public void modifierIdConjoint(Citoyen homme, Citoyen femme) {
		// TODO Auto-generated method stub
		homme.setIdConjoint(femme.getIdentifiant());
		femme.setIdConjoint(homme.getIdentifiant());
	}


    
}
