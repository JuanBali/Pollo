import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

ME ENCANTAAAA

public class Bubibau extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	private String usuario = "root";
	private String pw = "Abece12tres";
	private String bd = "Programacion_JuanBalian";
	private String parche = "?autoReconnect=true&useSSL=false";
	private String url = "jdbc:mysql://Sec-Lab23-Doc:3306/"+bd+parche;
	private java.sql.Connection con = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bubibau frame = new Bubibau();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bubibau() {
		try{
			con = DriverManager.getConnection(url,usuario,pw);
			
			if(con != null){
				System.out.println("conexion exitosa");
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(71, 68, 54, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(64, 114, 79, 20);
		contentPane.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(172, 65, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 111, 146, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(camposVacios()){
					JOptionPane.showMessageDialog(null, "Hay que escribir en los campos");
					return;
				}
				
				String usu = textField.getText();
				System.out.println(usu);
				
				String passwd = String.valueOf(passwordField.getPassword());
				System.out.println(passwd);
				
				if(!estaEnDatabase(usu)){
					mostrarMensaje("Ese usuario no esta registrado loco!!");
					return;
				}
				
				if(!ingresar(usu, passwd)){
					mostrarMensaje("Contraseña incorrecta!!");
					return;
				}
				
				mostrarMensaje("Usuario ingresado correctamente wey!");
			}
		});
		btnNewButton.setBounds(64, 185, 115, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(camposVacios()){
					JOptionPane.showMessageDialog(null, "Hay que escribir en los campos");
					return;
				}
				
				//Registrarse monkey
				String usu = textField.getText();
				System.out.println(usu);
				
				String passwd = String.valueOf(passwordField.getPassword());
				System.out.println(passwd);
				
				if(estaEnDatabase(usu)){
					mostrarMensaje("Ese usuario ya esta loco!!");
					return;
				}
				
				agregarADatabase(usu, passwd);
				mostrarMensaje("Usuario correctamente registrado!!!!!!!");
			}
		});
		btnNewButton_1.setBounds(227, 185, 115, 29);
		contentPane.add(btnNewButton_1);
	}
	
	boolean camposVacios(){
		return textField.getText().isEmpty() || passwordField.getText().isEmpty();
	}
	
	boolean estaEnDatabase(String nombre){
		try{
			Statement cmd = con.createStatement();

			ResultSet rs = cmd.executeQuery("SELECT * FROM User where UserName like '" + nombre + "'");
			if(rs.next()){
				return true;
			}
		} catch (SQLException e1) {
		}
		return false;
	}
	
	void agregarADatabase(String nombre, String passwd){
		try{
			Statement cmd = con.createStatement();

			String query = "INSERT INTO `programacion_juanbalian`.`user` (`UserName`, `Passwd`) VALUES ('"+  nombre + "', '" + passwd + "')";
			
			cmd.executeUpdate(query);
		} catch (SQLException e1) {
		}
	}
	
	boolean ingresar(String nombre, String passwd){
		try{
			Statement cmd = con.createStatement();

			ResultSet rs = cmd.executeQuery("SELECT * FROM User where UserName like '" + nombre + "'");
			if(rs.next()){
				if(rs.getString("Passwd").equals(passwd)){
					return true;
				}
			}
		} catch (SQLException e1) {
		}
		return false;
	}
	
	void mostrarMensaje(String mensaje){
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
