import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class deita {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			JOptionPane.showMessageDialog(null, "registro existoso");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		String usuario = "root";
		String pw = "Abece12tres";
		String bd = "Programacion_JuanBalian";
		
		String parche = "?autoReconnect=true&useSSL=false";
		
		String url = "jdbc:mysql://Sec-Lab23-Doc:3306/"+bd+parche;
		
		java.sql.Connection con = null;
		
		try{
			con = DriverManager.getConnection(url,usuario,pw);
			
			if(con != null){
				JOptionPane.showMessageDialog(null, "coneccion exitosa");
			}
	
			ResultSet rs = null;
			Statement cmd = con.createStatement();
			
			rs = cmd.executeQuery("SELECT * FROM User");
			
			while(rs.next()){
				int id = rs.getInt("UserID");
				String nombre = rs.getString(2);
			
				JOptionPane.showMessageDialog(null, nombre + " - " + id);
			}

			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
