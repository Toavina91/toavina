package controleur;
import Model.Naissance;
import Model.Mairie;
import Model.Citoyen;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

public class MairieController {

    private Mairie mairie;
    
    private String nomMariee;
    
    private JList<Citoyen> listeCitoyens;


    public MairieController(Mairie mairie) {
       this.mairie = mairie;
    }
    



    public void creerCitoyen() {
    	
    	String identifiantPereStr = JOptionPane.showInputDialog(null, "Entrez l'identifiant du premier parent : ");
    	long identifiantPere = Long.parseLong(identifiantPereStr);
    	String identifiantMereStr = JOptionPane.showInputDialog(null, "Entrez l'identifiant de deuxième parent : ");
    	long identifiantMere = Long.parseLong(identifiantMereStr);

        


        Citoyen parent1 = mairie.rechercheCitoyen(identifiantPere);
        Citoyen parent2 = mairie.rechercheCitoyen(identifiantMere);


        if (parent1 == null || parent2 == null) {
            JOptionPane.showMessageDialog(null, "Impossible de trouver un identifiant, veuillez réesayer");
            return;
        }
        
        if (parent1.getSexe() == 'F' && parent2.getSexe()== 'F') {
        	JOptionPane.showMessageDialog(null, "Deux femmes ne peuvent pas donner naissance à un enfant.");
            return;
        }
        
        if (parent1.getSexe() == 'M' && parent2.getSexe() == 'M') {
        	JOptionPane.showMessageDialog(null, "Deux hommes ne peuvent pas donner naissance à un enfant.");
            return;
        }

        String prenom = JOptionPane.showInputDialog(null, "Entrez le prénom de l'enfant : ");
        String nom = JOptionPane.showInputDialog(null, "Entrez le nom de famille de l'enfant : ");

        String sexeStr = JOptionPane.showInputDialog(null, "Entrez le sexe de l'enfant (M/F) : ");
        char sexe = sexeStr.toUpperCase().charAt(0);

        
        
        Citoyen enfant = new Citoyen(mairie.nvId(), prenom, nom, new Date(), sexe, 0);

        
        mairie.ajouterCitoyen(enfant);
        mairie.ajouterCitoyenm(enfant);


        Naissance naissance = new Naissance(new Date());
        mairie.ajouterNaissance(naissance);


        JOptionPane.showMessageDialog(null, "La naissance a été enregistrée avec succès. Félicitations aux parents pour cet heureux évènement !");

    }
    
    
    public void ajouterCitoyen() {

    	long identifiant = mairie.getListeCitoyens().size() + 1;
    	String prenom = JOptionPane.showInputDialog(null, "Entrez le prénom du nouveau citoyen  : ");
    	String nom = JOptionPane.showInputDialog(null, "Entrez le nom du nouveau citoyen  : ");
    	Date date = new Date();
        String sexeStr = JOptionPane.showInputDialog(null, "Entrez le sexe du citoyen (M/F) : ");
        char sexe = sexeStr.toUpperCase().charAt(0);
        String id = JOptionPane.showInputDialog(null, "Entrez l'identifiant du conjoint si il se trouve dans la mairie : ");
        long idC = Long.parseLong(id);
        
        Citoyen nvcitoyen = new Citoyen(identifiant, nom, prenom, date, sexe, idC);

        mairie.ajouterCitoyen(nvcitoyen);
        JOptionPane.showMessageDialog(null, "Bienvenu dans notre ville " + nvcitoyen.getPrenom() + " ! ");
        	
        


    }


	
	
	public void afficherEtat(long id) {
	    Citoyen citoyen = mairie.rechercheCitoyen(id);
	    if (citoyen == null) {
	        JOptionPane.showMessageDialog(null, "Le citoyen avec l'identifiant " + id + " n'existe pas.");
	        return;
	    }

	    if (citoyen.getidConjoint() == 0) {
	        if (citoyen.getSexe() == 'F') {
	            JOptionPane.showMessageDialog(null, citoyen.getPrenom() + " " + citoyen.getNom() + " est une femme née le " + citoyen.getDateNaissance() + " qui n'est pas marié.");
	        } else {
	            JOptionPane.showMessageDialog(null, citoyen.getPrenom() + " " + citoyen.getNom() + " est un homme né le " + citoyen.getDateNaissance() + " qui n'est pas mariée.");
	        }
	    } else {
	        Citoyen conjoint = mairie.rechercheCitoyen(citoyen.getidConjoint());
	        if (citoyen.getSexe() == 'F') {
	            JOptionPane.showMessageDialog(null, citoyen.getPrenom() + " " + citoyen.getNom() + " est une femme née le " + citoyen.getDateNaissance() + " mariée à " + (conjoint.getSexe() == 'F' ? conjoint.getPrenom() + " " + conjoint.getNom() : conjoint.getPrenom()) + " " + conjoint.getNom());
	        } else {
	            JOptionPane.showMessageDialog(null, citoyen.getPrenom() + " " + citoyen.getNom() + " est un homme né le " + citoyen.getDateNaissance() + " marié à " + conjoint.getPrenom() + " " + conjoint.getNom());
	        }
	    }
	}


    
    
    public void afficherCitoyenTable() {
        JFrame citoyenListFrame = new JFrame("Liste des citoyens");
        citoyenListFrame.setSize(600, 400);


        Object[][] rowData = getCitoyenData();
        String[] columnNames = {"Identifiant","Nom", "Prénom", "Date de naissance", "Sexe", "ID Conjoint"};

        JTable citoyenTable = new JTable(rowData, columnNames);

        citoyenListFrame.add(new JScrollPane(citoyenTable));


        JButton ajouterCitoyenButton = new JButton("Ajouter citoyen");
        ajouterCitoyenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterCitoyen();
                // Mettre à jour le tableau avec les données les plus récentes
                Object[][] newData = getCitoyenData();
                DefaultTableModel model = (DefaultTableModel) citoyenTable.getModel();
                model.setDataVector(newData, columnNames);
            }
        });
        citoyenListFrame.add(ajouterCitoyenButton, BorderLayout.SOUTH);

        citoyenListFrame.setVisible(true);
    }

    public Object[][] getCitoyenData() {
        List<Citoyen> citoyens = mairie.getListeCitoyens();
        Object[][] rowData = new Object[citoyens.size()][6];

        for (int i = 0; i < citoyens.size(); i++) {
            Citoyen citoyen = citoyens.get(i);
            rowData[i][0] = citoyen.getIdentifiant();
            rowData[i][1] = citoyen.getNom();
            rowData[i][2] = citoyen.getPrenom();
            rowData[i][3] = citoyen.getDateNaissance();
            rowData[i][4] = citoyen.getSexe();
            rowData[i][5] = citoyen.getidConjoint();
        }

        return rowData;
    }
    
    

    public void marierCitoyens() {
    	String idh = JOptionPane.showInputDialog(null, "Entrez l'identifiant du mari : ");
    	long id1 = Long.parseLong(idh);
    	String idf = JOptionPane.showInputDialog(null, "Entrez l'identifiant de la mairée : ");
    	long id2 = Long.parseLong(idf);
    	
    	Citoyen mari = mairie.rechercheCitoyen(id1);
    	Citoyen femme = mairie.rechercheCitoyen(id2);
    	


    	
    	if (mari.getidConjoint() != 0 || femme.getidConjoint() != 0) {
    		JOptionPane.showMessageDialog(null, "L'un des deux est déjà marié");
    		return;
    	}
    	
    	else if ((mari.getSexe() == 'M' && femme.getSexe() == 'M') || (mari.getSexe() == 'F' && femme.getSexe() == 'F')){
    		JOptionPane.showMessageDialog(null, "Le mariage est entre un homme et une femme.");
    		return;
    	}
    	
    	else if (mari.getSexe() == 'F' && femme.getSexe()== 'M' ) {
    		JOptionPane.showMessageDialog(null, "L'un des identifiants est incorrect.");
    		return;
    	}
    	else {
    		nomMariee = femme.getNom();
    		mairie.marier(mari, femme);

    	}
    	JOptionPane.showMessageDialog(null, "Félicitations aux jeunes mariés !");
    	
    	}

    public void divorcerCitoyen(long id) {
    	
    	Citoyen citoyen = mairie.rechercheCitoyen(id);
    	if (citoyen == null) {
    		JOptionPane.showMessageDialog(null, "Le citoyen avec l'identifiant " + id + " n'existe pas.");
    		return;
    	}
    	
    	
    	if (citoyen.getidConjoint() == 0) {
    		JOptionPane.showMessageDialog(null, citoyen.getPrenom() + " " + citoyen.getNom() + " n'est pas marié.");
    		return;
    	}
    	
    	
    	
    	long idConjoint = citoyen.getidConjoint();
    	citoyen.setIdConjoint(0);
    	Citoyen conjoint = mairie.rechercheCitoyen(idConjoint);
    	conjoint.setIdConjoint(0);
    	mairie.retirerMariage(mairie.rechercheMariage(citoyen));
    	if (citoyen.getSexe() == 'F') {
    		citoyen.nom = nomMariee;    		
    	}
    	else {
    		conjoint.nom = nomMariee;
    	}


    	
    	
    	// Rafraîchir la vue pour refléter les changements
    	JOptionPane.showMessageDialog(null, citoyen.getPrenom() + " " + citoyen.getNom() + " a été divorcé(e) de " + conjoint.getPrenom() + " " + conjoint.getNom() + ".");
    }
    
    
    public void sauvegarderCitoyens() {
        try {
            FileWriter writer = new FileWriter("citoyens.txt");
            List<Citoyen> citoyens = mairie.getListeCitoyens();

            for (Citoyen citoyen : citoyens) {
                String citoyenData = citoyen.getIdentifiant() + "," + citoyen.getNom() + "," + citoyen.getPrenom() + "," + citoyen.getDateNaissance() + "," + citoyen.getSexe() + "," + citoyen.getidConjoint() + "\n";
                writer.write(citoyenData);
            }

            writer.close();
            System.out.println("Liste des citoyens sauvegardée avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void restaurerCitoyens() {
        try {
            FileReader reader = new FileReader("citoyens.txt");
            DefaultListModel<Citoyen> listModel = (DefaultListModel<Citoyen>) listeCitoyens.getModel();
            listModel.clear();

            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] citoyenData = line.split(",");
                long id = Long.parseLong(citoyenData[0]);
                String nom = citoyenData[1];
                String prenom = citoyenData[2];
                Date date = parseDate(citoyenData[3]);
                char sexe = citoyenData[4].charAt(0);
                long idConjoint = Long.parseLong(citoyenData[5]);

                Citoyen citoyen = new Citoyen(id, nom, prenom, date, sexe, idConjoint);
                listModel.addElement(citoyen);
                mairie.ajouterCitoyen(citoyen);
            }

            reader.close();
            System.out.println("Liste des citoyens restaurée avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Date parseDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date attendue, par exemple "jour/mois/année"
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Ou lancez une exception appropriée en cas d'erreur de parsing
        }
    }
    





}

