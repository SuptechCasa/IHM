package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Fenetre extends JFrame {
    public Fenetre(Dimension dimension, Color color) {
        setSize(dimension);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Empêcher le redimensionnement
        getContentPane().setBackground(color);
        getContentPane().setLayout(new BorderLayout());

        // Définir une bordure pour mieux voir les panneaux
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Créer des panneaux
        JPanel nord = new JPanel();
        nord.setBackground(Color.blue);
        nord.setPreferredSize(new Dimension(getWidth(), 100));
        nord.setBorder(border);

        JPanel sud = new JPanel();
        sud.setBackground(Color.yellow);
        sud.setPreferredSize(new Dimension(getWidth(), 100));
        sud.setBorder(border);

        JPanel est = new JPanel();
        est.setBackground(Color.green);
        est.setPreferredSize(new Dimension(150, getHeight()));
        est.setBorder(border);

        JPanel ouest = new JPanel();
        ouest.setBackground(Color.RED);
        ouest.setPreferredSize(new Dimension(150, getHeight()));
        ouest.setBorder(border);

        JPanel centre = new JPanel();
        centre.setBackground(Color.WHITE);
        centre.setBorder(border);

        // Ajouter les panneaux à la fenêtre
        getContentPane().add(nord, BorderLayout.NORTH);
        getContentPane().add(sud, BorderLayout.SOUTH);
        getContentPane().add(est, BorderLayout.EAST);
        getContentPane().add(ouest, BorderLayout.WEST);
        getContentPane().add(centre, BorderLayout.CENTER);

        setVisible(true);
    }
}
