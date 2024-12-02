package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Reporte;

public class DataReporte {
	
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
 public boolean  guardar(Reporte rep) {
	 boolean guarda=false;
	 
	 try {
       ps=cn.prepareStatement("insert into reporte values (?,?,?,?,?,true)");
		ps.setInt(1,rep.getIdReporte());
		ps.setString(2, rep.getLugarAfectado());
		ps.setString(3, rep.getLaEntreCalle1());
		ps.setString(4, rep.getLaEntreCalle2());
		ps.setDate(5, rep.getFechaDeReporte());
		ps.setBoolean(6, rep.isActivo());
		int guardado=ps.executeUpdate();
		if(guardado>0) {
			guarda=true;
			JOptionPane.showMessageDialog(null,"guardado");
		}else {
			JOptionPane.showMessageDialog(null,"no guardado");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		JOptionPane.showMessageDialog(null,"Error al guardar"+e);
	}
	 
	return guarda;
	 
 }
 public Reporte consultar(int idRep) {
	 Reporte rep=new Reporte();
	 String sql="select * from Reporte where idReporte=?";
	 try {
		ps=cn.prepareStatement(sql);
		ps.setInt(1, idRep);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			rep.setIdReporte(rs.getInt(1));
			rep.setLugarAfectado(rs.getString(2));
			rep.setLaEntreCalle1(rs.getString(3));
			rep.setLaEntreCalle2(rs.getString(4));
			rep.setFechaDeReporte(rs.getDate(5));
			
		}else {
			JOptionPane.showMessageDialog(null, "reporte no existente");
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		JOptionPane.showMessageDialog(null,"error al consultar"+ e);
	}
	 
	return rep;
	 
 }
 public boolean actualizar(Reporte rep) {
boolean actuali=false;
String sql="update Reporte set ,lugarAfectado=?,laEntreCalle=?,laYcalle=?,FechaDeReporte=? where idReporte=? ";
try {
	ps=cn.prepareStatement(sql);
	ps.setInt(5,rep.getIdReporte());
	ps.setDate(4,rep.getFechaDeReporte());
	ps.setString(3, rep.getLaEntreCalle2());
	ps.setString(2, rep.getLaEntreCalle1());
	ps.setString(1, rep.getLugarAfectado());
	int act=ps.executeUpdate();
	if(act>0) {
		actuali=true;
		JOptionPane.showMessageDialog(null,"actualizado");
		
	}else {
		JOptionPane.showMessageDialog(null, "no actualizado");
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	//e.printStackTrace();
	JOptionPane.showMessageDialog(null,"error al actualizar"+ e);
}

return actuali;

	 
 }
 public boolean eliminarReporte(int idrep) {
	 boolean elimi=false;
	 String sql ="update Reporte  set activo=false where idReporte=?";
	 try {
		ps=cn.prepareStatement(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	}
	return elimi;
	 
 }
}
