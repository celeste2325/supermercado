package vista;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.AdministracionSupermercado;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductosSinStock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public ProductosSinStock() {
		setTitle("Productos sin stock");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 763, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(100, 100, 763, 349);
		contentPane.add(panel);

		// Array de ‘String’ con los titulos de las columnas
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Código");
		columnNames.add("Descripción");
		columnNames.add("Stock");
		columnNames.add("Stock mínimo");
		columnNames.add("Pedido de Reposición");

		// trae datos del controlador
		Vector<Vector<String>> datos = AdministracionSupermercado.getSupermercado().vectorProductosSinStock();

		// Crea un scrollpanel y se agrega a la tabla
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 763, 349);
		panel.add(scrollPane);

		TableModel tableModel = new DefaultTableModel(datos, columnNames);
		table = new JTable(datos, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(700, 80));
		table.setModel(tableModel);
		table.setBounds(100, 100, 763, 349);
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
