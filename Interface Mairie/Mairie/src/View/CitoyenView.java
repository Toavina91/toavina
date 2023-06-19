package View;
import controleur.MairieController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Model.*;

public class CitoyenView extends JFrame {
	
    private Timer timer; // Pour la gestion du défilement des images
    private int currentImageIndex = 0; // Index de l'image actuelle
    private ImageIcon[] images = { // Tableau contenant les chemins des images à afficher
        new ImageIcon("images/1.jpg"),
        new ImageIcon("images/2.jpg"),
        new ImageIcon("images/3.jpg")
    };
    private JLabel backgroundLabel; // JLabel pour afficher les images de fond

    public CitoyenView() {

        super("Registre civil");

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        backgroundLabel = new JLabel("", images[0], JLabel.CENTER);
        add(backgroundLabel, BorderLayout.CENTER);


        JButton citoyenButton = new JButton("Citoyens");
        JButton mariageButton = new JButton("Mariages");
        JButton naissanceButton = new JButton("Naissances");
        JButton divorceButton = new JButton("Divorces");
        JButton etatButton = new JButton("Etat d'une personne");
        
        
        

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2)); 

        buttonsPanel.setOpaque(false);
        buttonsPanel.add(citoyenButton);
        buttonsPanel.add(mariageButton);
        buttonsPanel.add(naissanceButton);
        buttonsPanel.add(divorceButton);
        buttonsPanel.add(etatButton);
        add(buttonsPanel, BorderLayout.NORTH);
        citoyenButton.setForeground(Color.BLACK);
        mariageButton.setForeground(Color.BLACK);
        naissanceButton.setForeground(Color.BLACK);
        divorceButton.setForeground(Color.BLACK);
        etatButton.setForeground(Color.BLACK);
        citoyenButton.setBackground(Color.BLUE);
        mariageButton.setBackground(Color.BLUE);
        naissanceButton.setBackground(Color.WHITE);
        divorceButton.setBackground(Color.RED);
        etatButton.setBackground(Color.RED);
        Font font = new Font("Comic Sans Ms", Font.ITALIC, 14);
        citoyenButton.setFont(font);
        mariageButton.setFont(font);
        naissanceButton.setFont(font);
        divorceButton.setFont(font);
        etatButton.setFont(font);
        
        




        // Créer le timer pour le défilement des images
        timer = new Timer(3000, new ActionListener() { // Changement toutes les 3 secondes
            @Override
            public void actionPerformed(ActionEvent e) {
                currentImageIndex = (currentImageIndex + 1) % images.length; // Incrémenter l'index de l'image courante
                backgroundLabel.setIcon(images[currentImageIndex]); // Changer l'icône de l'image de fond
            }
        });
        timer.start(); // Démarrer le timer
        
        Mairie mairie = new Mairie();
        MairieController mairieController = new MairieController(mairie);

        naissanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mairieController.creerCitoyen();
            }
        });
        

        
        citoyenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mairieController.afficherCitoyenTable();
            }
        });

                             
                              

        mariageButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               mairieController.marierCitoyens();
           }
        });

   
        

        
        divorceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String idS = JOptionPane.showInputDialog(null, "Entrez l'identifiant de la personne à divorcer : ");
            	long id = Long.parseLong(idS);
                mairieController.divorcerCitoyen(id);
            }
        });
        
        etatButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {     		
         		String idS = JOptionPane.showInputDialog(null, "Entrez l'identifiant de la personne  : ");
            	long id = Long.parseLong(idS);
        		mairieController.afficherEtat(id);
              	}
        });
        
        JButton quitterButton = new JButton("Quitter et sauvegarder");
        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mairieController.sauvegarderCitoyens();
                dispose();
            }
        });
        add(quitterButton, BorderLayout.SOUTH);

        
        
        
        setVisible(true);
    }
    




}
     