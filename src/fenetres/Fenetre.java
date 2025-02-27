package fenetres;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame {
public Fenetre(Dimension dimension, Color color) {
	setSize(dimension);
	setLocationRelativeTo(null);//Centrer la fenêtre
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setBackground(color);
	//Définir la disposition
	getContentPane().setLayout(new BorderLayout());
	//Créer des panneaux
	JPanel nord=new JPanel();nord.setBackground(Color.blue);
	JPanel sud=new JPanel();sud.setBackground(Color.yellow);
	JPanel est=new JPanel();est.setBackground(Color.green);
	JPanel ouest=new JPanel();ouest.setBackground(Color.RED);    ouest.setPreferredSize(new Dimension(150, getHeight()));
	JPanel centre=new JPanel();centre.setBackground(Color.WHITE);
	//Ajouter les panneaux à la fenêtrehhh
	getContentPane().add(nord,BorderLayout.NORTH);
	getContentPane().add(sud,BorderLayout.SOUTH);
	getContentPane().add(est,BorderLayout.EAST);
	getContentPane().add(ouest,BorderLayout.WEST);
	getContentPane().add(centre,BorderLayout.CENTER);
	setVisible(true);
}
}
