package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.AdministracionSupermercado;
import modelo.CajaView;

public class VentasRealizadasAlCierrreDeCaja extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public VentasRealizadasAlCierrreDeCaja(CajaView caja) {
		setTitle("Ventas realizadas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(100, 100, 571, 349);
		contentPane.add(panel);

		// Array de ‘String’ con los titulos de las columnas
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Fecha");
		columnNames.add("Nro Caja");
		columnNames.add("Número de Venta");
		columnNames.add("Cajero");
		columnNames.add("Total");

		// trae ventas realizadas en la caja 
		Vector<Vector<String>> datos = AdministracionSupermercado.getSupermercado()
				.vectorVentasRalizadasAlCierreDeCajaView(caja);

		// Creamos un scrollpanel y se lo agregamos a la tabla
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 571, 349);
		panel.add(scrollPane);

		TableModel tableModel = new DefaultTableModel(datos, columnNames);
		table = new JTable(datos, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 80));
		table.setModel(tableModel);
		table.setBounds(100, 100, 571, 349);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scrollPane.setViewportView(table);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnSalir);

	}

}
