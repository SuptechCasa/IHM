package fenetres;

import java.awt.BorderLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;

public class Fenetre extends JFrame {
public Fenetre(Dimension dimension, Color color) {
	setSize(dimension);
	setLocationRelativeTo(null);
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
    centre.setBackground(Color.WHITE);
    centre.setBorder(panelBorder);
    
    JTextField nom=new JTextField();
    JButton btn=new JButton("Ajouter");
    centre.add(nom);centre.add(btn);
    

    // Add panels to the window
    getContentPane().add(nord, BorderLayout.NORTH);
    getContentPane().add(sud, BorderLayout.SOUTH);
    getContentPane().add(est, BorderLayout.EAST);
    getContentPane().add(ouest, BorderLayout.WEST);
    getContentPane().add(centre, BorderLayout.CENTER);

	setVisible(true);
}
}
