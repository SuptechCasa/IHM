package fenetres;

import java.awt.BorderLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Fenetre extends JFrame {
public Fenetre(Dimension dimension, Color color) {
	setSize(dimension);
	setLocationRelativeTo(null);//Centrer la fenêtre
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setBackground(color);
	//Définir la disposition
	getContentPane().setLayout(new BorderLayout());
	// Create a border for panels
    Border panelBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

    // Create panels with borders
    JPanel nord = new JPanel();
    nord.setBackground(Color.blue);
    nord.setPreferredSize(new Dimension(dimension.width, 50));
    nord.setBorder(panelBorder);

    JPanel sud = new JPanel();
    sud.setBackground(Color.yellow);
    sud.setPreferredSize(new Dimension(dimension.width, 50));
    sud.setBorder(panelBorder);

    JPanel est = new JPanel();
    est.setBackground(Color.green);
    est.setPreferredSize(new Dimension(100, dimension.height - 100));
    est.setBorder(panelBorder);

    JPanel ouest = new JPanel();
    ouest.setBackground(Color.RED);
    ouest.setPreferredSize(new Dimension(100, dimension.height - 100));
    ouest.setBorder(panelBorder);

    JPanel centre = new JPanel();
    centre.setLayout(new BorderLayout());
    JPanel centreContent=new JPanel();
    centreContent.setLayout(new GridLayout(4, 2, 0, 10));
    centre.add(centreContent,BorderLayout.CENTER);
    
    JPanel estPanel=new JPanel();centre.add(estPanel,BorderLayout.EAST);
    JPanel ouestPanel=new JPanel();centre.add(ouestPanel,BorderLayout.WEST);
    JPanel sudPanel=new JPanel();centre.add(sudPanel,BorderLayout.SOUTH);
    
    estPanel.setPreferredSize(new Dimension(100,100));
    ouestPanel.setPreferredSize(new Dimension(100,100));
    sudPanel.setPreferredSize(new Dimension(100,220));
    
    centre.setBackground(Color.WHITE);
    centre.setBorder(panelBorder);

    // Add panels to the window
    getContentPane().add(nord, BorderLayout.NORTH);
    getContentPane().add(sud, BorderLayout.SOUTH);
    getContentPane().add(est, BorderLayout.EAST);
    getContentPane().add(ouest, BorderLayout.WEST);
    getContentPane().add(centre, BorderLayout.CENTER);

    JLabel lnom=new JLabel("Nom"); 
    JLabel lprenom=new JLabel("Prénom"); 
    JLabel lsexe=new JLabel("Sexe"); 
    JLabel lfiliere=new JLabel("Filière");
    //Ajouter les composants
    JTextField nom=new JTextField();
    JTextField prenom=new JTextField();
    
    JRadioButton F=new JRadioButton("F");
    JRadioButton M=new JRadioButton("M");
    ButtonGroup sexeButtonGoup=new ButtonGroup();
    sexeButtonGoup.add(M);sexeButtonGoup.add(F);
    JPanel sexe=new JPanel();
    sexe.add(F);sexe.add(M);
    
    String[] options= {"Informatique","Biologie","Commerce"};
    JComboBox<String> filiere=new JComboBox<String>(options);
    
    centreContent.add(lnom);centreContent.add(nom);
    centreContent.add(lprenom);centreContent.add(prenom);
    centreContent.add(lsexe);centreContent.add(sexe);
    centreContent.add(lfiliere);centreContent.add(filiere);
    
    DefaultTableModel tableModel=new DefaultTableModel(
    		new Object[][] {{"HAKIMI","Achraf","M","Informatique"},{"BOUNOU","Yassine","M","Commerce"},{"AMRABET","Soufiane","M","Biologie"},{"ZIYECH","HAKIM","M","Informatique"}},
    		new Object[] {"Nom","Prénom","Sexe","filière"}
    		);
    JTable table=new JTable(tableModel);
    JScrollPane scrolPane=new JScrollPane(table);
    scrolPane.setPreferredSize(new Dimension(300, 100));
    sudPanel.add(scrolPane);
    
    setVisible(true);
}
}
