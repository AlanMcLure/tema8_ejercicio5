package tema8_ejercicio5;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

class ConnectionSingleton {
	private static Connection con;

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3307/tienda";
		String user = "alumno";
		String password = "alumno";
		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection(url, user, password);
		}
		return con;
	}
}

public class Tema8_ejercicio5 {

	private JFrame frmTemaEjercicio;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldCodigo;
	private JTextField textFieldPrecio;
	private JTextField textFieldUnidades;
	private JTextField textFieldNombre;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tema8_ejercicio5 window = new Tema8_ejercicio5();
					window.frmTemaEjercicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tema8_ejercicio5() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTemaEjercicio = new JFrame();
		frmTemaEjercicio.setTitle("Tema 8 Ejercicio 5");
		frmTemaEjercicio.setBounds(100, 100, 1100, 500);
		frmTemaEjercicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTemaEjercicio.getContentPane().setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Código");
		model.addColumn("Nombre");
		model.addColumn("Precio");
		model.addColumn("Unidades");
		
		try {
			Connection con = ConnectionSingleton.getConnection();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM producto");
			while (rs.next()) {
				Object[] row = new Object[4];
				row[0] = rs.getInt("cod");
				row[1] = rs.getString("nombre");
				row[2] = rs.getInt("precio");
				row[3] = rs.getInt("unidades");
				model.addRow(row);
			}
			
			rs.close();
			stmt.close();			
			//con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		table = new JTable(model);
		table.setBounds(74, 61, 387, 147);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		frmTemaEjercicio.getContentPane().add(table);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(74, 61, 387, 117);
		frmTemaEjercicio.getContentPane().add(scrollPane);

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(198, 24, 117, 25);
		frmTemaEjercicio.getContentPane().add(btnMostrar);

		JLabel lblNuevoProducto = new JLabel("Nuevo producto:");
		lblNuevoProducto.setBounds(12, 202, 117, 15);
		frmTemaEjercicio.getContentPane().add(lblNuevoProducto);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(42, 229, 70, 15);
		frmTemaEjercicio.getContentPane().add(lblCodigo);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(42, 256, 70, 15);
		frmTemaEjercicio.getContentPane().add(lblPrecio);

		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(112, 227, 114, 19);
		frmTemaEjercicio.getContentPane().add(textFieldCodigo);
		textFieldCodigo.setColumns(10);

		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(112, 254, 114, 19);
		frmTemaEjercicio.getContentPane().add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setBounds(245, 254, 76, 15);
		frmTemaEjercicio.getContentPane().add(lblUnidades);

		textFieldUnidades = new JTextField();
		textFieldUnidades.setColumns(10);
		textFieldUnidades.setBounds(329, 254, 114, 19);
		frmTemaEjercicio.getContentPane().add(textFieldUnidades);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(245, 227, 70, 15);
		frmTemaEjercicio.getContentPane().add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(329, 227, 114, 19);
		frmTemaEjercicio.getContentPane().add(textFieldNombre);

		JButton btnAnyadir = new JButton("Añadir");
		btnAnyadir.setBounds(185, 285, 117, 25);
		frmTemaEjercicio.getContentPane().add(btnAnyadir);

		JLabel lblBorrarProducto = new JLabel("Borrar producto:");
		lblBorrarProducto.setBounds(12, 356, 137, 15);
		frmTemaEjercicio.getContentPane().add(lblBorrarProducto);

		JLabel lblEligeProducto = new JLabel("Elige producto:");
		lblEligeProducto.setBounds(42, 391, 117, 15);
		frmTemaEjercicio.getContentPane().add(lblEligeProducto);

		JComboBox comboBoxProducto = new JComboBox();
		comboBoxProducto.setBounds(177, 386, 70, 24);
		frmTemaEjercicio.getContentPane().add(comboBoxProducto);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(298, 386, 117, 25);
		frmTemaEjercicio.getContentPane().add(btnEliminar);

		JLabel lblActualizarPrecio = new JLabel("Actualizar precio:");
		lblActualizarPrecio.setBounds(506, 71, 137, 15);
		frmTemaEjercicio.getContentPane().add(lblActualizarPrecio);

		JLabel lblEligeProducto_1 = new JLabel("Elige producto:");
		lblEligeProducto_1.setBounds(540, 105, 114, 15);
		frmTemaEjercicio.getContentPane().add(lblEligeProducto_1);

		JComboBox comboBoxProducto_1 = new JComboBox();
		comboBoxProducto_1.setBounds(658, 100, 70, 24);
		frmTemaEjercicio.getContentPane().add(comboBoxProducto_1);

		JLabel lblNuevoPrecio = new JLabel("Nuevo precio:");
		lblNuevoPrecio.setBounds(540, 142, 114, 15);
		frmTemaEjercicio.getContentPane().add(lblNuevoPrecio);

		textField = new JTextField();
		textField.setBounds(658, 140, 114, 19);
		frmTemaEjercicio.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnActualizarPrecio = new JButton("Actualizar precio");
		btnActualizarPrecio.setBounds(817, 115, 153, 25);
		frmTemaEjercicio.getContentPane().add(btnActualizarPrecio);

		JLabel lblIncrementarStock = new JLabel("Incrementar stock:");
		lblIncrementarStock.setBounds(506, 202, 137, 15);
		frmTemaEjercicio.getContentPane().add(lblIncrementarStock);

		JLabel lblEligeProducto_1_1 = new JLabel("Elige producto:");
		lblEligeProducto_1_1.setBounds(540, 229, 114, 15);
		frmTemaEjercicio.getContentPane().add(lblEligeProducto_1_1);

		JComboBox comboBoxProducto_1_1 = new JComboBox();
		comboBoxProducto_1_1.setBounds(712, 224, 70, 24);
		frmTemaEjercicio.getContentPane().add(comboBoxProducto_1_1);

		JLabel lblUnidadesAdquiridas = new JLabel("Unidades adquiridas:");
		lblUnidadesAdquiridas.setBounds(540, 271, 153, 15);
		frmTemaEjercicio.getContentPane().add(lblUnidadesAdquiridas);

		textField_1 = new JTextField();
		textField_1.setBounds(712, 269, 114, 19);
		frmTemaEjercicio.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnActualizarStock = new JButton("Actualizar stock");
		btnActualizarStock.setBounds(849, 249, 153, 25);
		frmTemaEjercicio.getContentPane().add(btnActualizarStock);

		JLabel lblVenta = new JLabel("Venta:");
		lblVenta.setBounds(506, 328, 70, 15);
		frmTemaEjercicio.getContentPane().add(lblVenta);

		JLabel lblProductoAVender = new JLabel("Producto a vender:");
		lblProductoAVender.setBounds(540, 356, 153, 15);
		frmTemaEjercicio.getContentPane().add(lblProductoAVender);

		JComboBox comboBoxProducto_1_1_1 = new JComboBox();
		comboBoxProducto_1_1_1.setBounds(702, 351, 70, 24);
		frmTemaEjercicio.getContentPane().add(comboBoxProducto_1_1_1);

		JLabel lblUnidadesAVender = new JLabel("Unidades a vender:");
		lblUnidadesAVender.setBounds(540, 391, 153, 15);
		frmTemaEjercicio.getContentPane().add(lblUnidadesAVender);

		textField_2 = new JTextField();
		textField_2.setBounds(702, 389, 114, 19);
		frmTemaEjercicio.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnVender = new JButton("Vender");
		btnVender.setBounds(849, 362, 117, 25);
		frmTemaEjercicio.getContentPane().add(btnVender);

		JLabel lblGananciasTotales = new JLabel("Ganancias totales:");
		lblGananciasTotales.setForeground(Color.GREEN);
		lblGananciasTotales.setBounds(635, 436, 137, 15);
		frmTemaEjercicio.getContentPane().add(lblGananciasTotales);

		JLabel lblGanancia = new JLabel("");
		lblGanancia.setForeground(Color.GREEN);
		lblGanancia.setBounds(797, 436, 70, 15);
		frmTemaEjercicio.getContentPane().add(lblGanancia);

		JLabel labelEuro = new JLabel("€");
		labelEuro.setForeground(Color.GREEN);
		labelEuro.setBounds(863, 436, 70, 15);
		frmTemaEjercicio.getContentPane().add(labelEuro);

		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					Connection con = ConnectionSingleton.getConnection();
					
					model.setRowCount(0);
					
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM producto");
					while (rs.next()) {
						Object[] row = new Object[4];
						row[0] = rs.getInt("cod");
						row[1] = rs.getString("nombre");
						row[2] = rs.getInt("precio");
						row[3] = rs.getInt("unidades");
						model.addRow(row);
					}		
					rs.close();
					stmt.close();				
					//con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = ConnectionSingleton.getConnection();

					PreparedStatement pstmtInsertProdcuto = con
							.prepareStatement("INSERT INTO producto VALUES (?, ?, ?, ?)");
					pstmtInsertProdcuto.setInt(1, Integer.parseInt(textFieldCodigo.getText()));
					pstmtInsertProdcuto.setString(2, textFieldNombre.getText());
					pstmtInsertProdcuto.setInt(3, Integer.parseInt(textFieldPrecio.getText()));
					pstmtInsertProdcuto.setInt(4, Integer.parseInt(textFieldUnidades.getText()));
					pstmtInsertProdcuto.executeUpdate();
					pstmtInsertProdcuto.close();
					
					btnMostrar.doClick();
					
					JOptionPane.showMessageDialog(null, "Producto añadido"); 

					//con.close();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = ConnectionSingleton.getConnection();

					PreparedStatement pstmtDeleteProducto = con
							.prepareStatement("DELETE FROM producto WHERE cod = ?");
					pstmtDeleteProducto.setInt(1,Integer.parseInt(comboBoxProducto.getSelectedItem().toString()));
					
					pstmtDeleteProducto.executeUpdate();
					pstmtDeleteProducto.close();
					
					btnMostrar.doClick();
					
					JOptionPane.showMessageDialog(null, "Producto eliminado"); 

					//con.close();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		try {
			Connection con = ConnectionSingleton.getConnection();
			
			btnMostrar.doClick();
			
			Statement stmtProducto = con.createStatement();
			ResultSet rsProducto = stmtProducto.executeQuery("SELECT cod FROM producto");
			while (rsProducto.next()) {
				int cod = rsProducto.getInt("cod");
				comboBoxProducto.addItem(String.valueOf(cod));
			}
			rsProducto.close();
			stmtProducto.close();
			con.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
