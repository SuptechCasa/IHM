package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Etudiant;

public class EtudiantService {
private final String url="jdbc:mysql://localhost:3306/suptech2";
private final String user="root";
private final String password="";

//Méthode pour se connecter ) la base de données
private Connection getConnection() throws SQLException {
	return DriverManager.getConnection(url,user,password);
}

//Ajouter un étudiant
public int addEtudiant(Etudiant e) throws SQLException {
	String query="insert into etudiant (nom,prenom,sexe,filiere)"
			+ "values (?,?,?,?)";
	Connection conn=getConnection();
	PreparedStatement stmt=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
	stmt.setString(1, e.getNom());
	stmt.setString(2, e.getPrenom());
	stmt.setString(3, e.getSexe());
	stmt.setString(4, e.getFiliere());
	stmt.executeUpdate();
	int id=-1;
    ResultSet generatedKeys = stmt.getGeneratedKeys();
    if (generatedKeys.next()) {
        id = generatedKeys.getInt(1); // Récupération de la clé
	}
	stmt.close();
	conn.close();
	return id;
	
}

public void deleteEtudiant(String id) throws SQLException {
	String query="delete from etudiant where id="+id;
	Connection conn=getConnection();
	PreparedStatement stmt=conn.prepareStatement(query);
	stmt.executeUpdate();
	stmt.close();
	conn.close();
}

public List<Etudiant> getAllEtudiants() throws SQLException{
	List<Etudiant> etudiants=new ArrayList<>();
	String query="select * from etudiant";
	Connection conn=getConnection();
	Statement stmt=conn.createStatement();
	ResultSet rs= stmt.executeQuery(query);
	while(rs.next()) {
		Etudiant etudiant=new Etudiant(
				rs.getInt("id"),
				rs.getString("nom"), 
				rs.getString("prenom"), 
				rs.getString("sexe"), 
				rs.getString("filiere"));
		etudiants.add(etudiant);
	}
	return etudiants;
}

public void updateEtudiant(Etudiant e) throws SQLException {
	String query="update etudiant set nom=?,prenom=?,sexe=?,filiere=? where id="+e.getId();
	Connection conn=getConnection();
	PreparedStatement stmt=conn.prepareStatement(query);
	stmt.setString(1, e.getNom());
	stmt.setString(2, e.getPrenom());
	stmt.setString(3, e.getSexe());
	stmt.setString(4, e.getFiliere());
	stmt.executeUpdate();
	stmt.close();
	conn.close();
}
}
