package fenetres;

import java.awt.BorderLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import model.Etudiant;
import service.EtudiantService;

public class Fenetre extends JFrame {
public Fenetre(Dimension dimension, Color color) throws SQLException {
	EtudiantService etudiantService=new EtudiantService();
	
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
    sudPanel.setLayout(new BorderLayout());
    JButton ajouterBTN=new JButton("Ajouter");ajouterBTN.setPreferredSize(new Dimension(80, 30));
    JButton modifierBTN=new JButton("Modifier");modifierBTN.setPreferredSize(new Dimension(80, 30));
    JPanel cPanel=new JPanel();
    estPanel.setLayout(new BorderLayout());
    estPanel.add(cPanel,BorderLayout.CENTER);
    modifierBTN.setVisible(false);
    cPanel.setPreferredSize(new Dimension(100, 202));
    cPanel.add(modifierBTN);
    estPanel.add(ajouterBTN,BorderLayout.SOUTH);
    estPanel.add(cPanel,BorderLayout.CENTER);
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
    JLabel lId=new JLabel();
    lId.setVisible(false);
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
    		new Object[][] {},
    		new Object[] {"Id","Nom","Prénom","Sexe","filière"}
    		);
    JTable table=new JTable(tableModel);
    JScrollPane scrolPane=new JScrollPane(table);
    scrolPane.setPreferredSize(new Dimension(300, 100));
    sudPanel.add(scrolPane);
    
    //Remplir le tableau
    etudiantService.getAllEtudiants().forEach(etudiant->{
    	tableModel.addRow(etudiant.toObjectArray());
    });
    
    JPopupMenu popupMenu=new JPopupMenu();
    JMenuItem menuSupprimer=new JMenuItem("Supprimer");
    JMenuItem menuModifier=new JMenuItem("Modifier");
    menuSupprimer.addActionListener(e->System.out.println(table.getSelectedRow()));
    popupMenu.add(menuModifier);popupMenu.add(menuSupprimer);
    table.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton()==3) {
			popupMenu.show(e.getComponent(), e.getX(),e.getY() );
			int rowIndex=table.rowAtPoint(e.getPoint());
			table.setRowSelectionInterval(rowIndex, rowIndex);
			}
		}
	});
    
    menuModifier.addActionListener(e->{
    	int ligne=table.getSelectedRow();
    	nom.setText(table.getValueAt(ligne, 1).toString());
    	prenom.setText(table.getValueAt(ligne, 2).toString());
    	lId.setText(table.getValueAt(ligne, 0).toString());
    	if (table.getValueAt(ligne, 3).equals("F")) {
    		F.setSelected(true);
    	}else {
    		M.setSelected(true);
    	}
    	filiere.setSelectedItem(table.getValueAt(ligne, 4).toString());
    	ajouterBTN.setText("Annuler");
    	modifierBTN.setVisible(true);
    	table.setRowSelectionAllowed(false);
    	
    });
    
    menuSupprimer.addActionListener(e->{
    	int rowIndex=table.getSelectedRow();
    	String id=table.getValueAt(rowIndex, 0).toString();
    	tableModel.removeRow(rowIndex);
    	try {
			etudiantService.deleteEtudiant(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    });
    
    ajouterBTN.addActionListener(new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ajouterBTN.getText().equals("Annuler")) {
				ajouterBTN.setText("Ajouter");
				modifierBTN.setVisible(false);
				nom.setText("");prenom.setText("");
				return;
			}
			String vnom=nom.getText();
			String vprenom=prenom.getText();
			String vSexe=(F.isSelected())?"F":"M";
			String vFiliere=filiere.getSelectedItem().toString();
			try {
				Etudiant etudiant=new Etudiant(0, vnom, vprenom, vSexe, vFiliere);
				int id=etudiantService.addEtudiant(etudiant);
				tableModel.addRow(new Object[]{id,vnom,vprenom,vSexe,vFiliere});
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
    
    modifierBTN.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int vId=Integer.parseInt(lId.getText());
			String vnom=nom.getText();
			String vprenom=prenom.getText();
			String vSexe=(F.isSelected())?"F":"M";
			String vFiliere=filiere.getSelectedItem().toString();
			Etudiant etudiant=new Etudiant(vId, vnom, vprenom, vSexe, vFiliere);
			try {
				etudiantService.updateEtudiant(etudiant);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			table.setValueAt(lId.getText(), table.getSelectedRow(), 0);
			table.setValueAt(nom.getText(), table.getSelectedRow(), 1);
			table.setValueAt(prenom.getText(), table.getSelectedRow(), 2);
			table.setValueAt((F.isSelected())?"F":"M", table.getSelectedRow(), 3);
			table.setValueAt(filiere.getSelectedItem().toString(), table.getSelectedRow(), 4);
			ajouterBTN.setText("Ajouter");
			modifierBTN.setVisible(false);
		}
	});
    
    setVisible(true);
}
}
