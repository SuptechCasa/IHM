package fenetres;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TestFenetre extends JFrame{
	public TestFenetre() {
		setSize(new Dimension(400, 300));
		setLocationRelativeTo(null);//Centrer la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.cyan);	
		//Créer un JTable
		//Etape1: Créer le modèle
		DefaultTableModel model=new DefaultTableModel(
				new Object[][] {{"1","Hassan",23},{"2","Meriem",24}},
				new Object[] {"id","nom","age"});
		//Etape2: Mettre le modèle dans JTable
		JTable table=new JTable(model);
		//Etape3: Mettre JTable dans JScrollPane
		JScrollPane tableau=new JScrollPane(table);
		//Etape4: Ajouter ScrollPane à JFrame
		getContentPane().add(tableau);
		
		//Ajouter un menu contextuel
		JPopupMenu menuContextuel=new JPopupMenu();
		JMenuItem supprimer=new JMenuItem("Supprimer");
		JMenuItem modifier=new JMenuItem("Modifier");
		menuContextuel.add(modifier);menuContextuel.add(supprimer);
		//Afficher le menu contextuel suite au clic sur le tableau
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==3) {
				menuContextuel.show(e.getComponent(), e.getX(),e.getY() );
				int rowIndex=table.rowAtPoint(e.getPoint());
				table.setRowSelectionInterval(rowIndex, rowIndex);
				}
			}
		});
		
		supprimer.addActionListener(e->{
			System.out.println("Supprimer:"+table.getValueAt(table.getSelectedRow(), 0));
		});
		modifier.addActionListener(e->{
			System.out.println("Modifier:"+table.getValueAt(table.getSelectedRow(), 0));
		});
		setVisible(true);
	}

}
