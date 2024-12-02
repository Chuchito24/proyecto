package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DataConexion {
Connection cn;
PreparedStatement ps;
public Connection conectar() {
	try {
		cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LimpiezaDrenajes","root","");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Error al conectar"+e);
	}
	return cn;
	
}
}
