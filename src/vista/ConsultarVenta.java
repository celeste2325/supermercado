package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.AdministracionSupermercado;

public class ConsultarVenta extends JFrame {

	private JPanel contentPane;
	private JTextField dia;
	private JTextField nombre_cajero;
	private JTextField numero_caja;
	private JLabel lblMes;
	private JLabel lblDia;
	private JLabel lblCajero;
	private JLabel lblNumeroDeCaja;
	private JTextField mes;
	private JLabel lblAño;
	private JTextField año;
	private JTable table;
	private JScrollPane scrollPane;
	LocalDate fecha = null;
	private JMenuBar menuBar;
	JPanel panel = null;
	Vector<Vector<String>> datos = null;
	Vector<String> columnNames = null;

	/**
	 * Create the frame.
	 */
	public ConsultarVenta() {
		setTitle("Consultar ventas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 584, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblDia = new JLabel("Dia:");
		lblDia.setBounds(10, 69, 48, 14);
		contentPane.add(lblDia);

		lblCajero = new JLabel("Cajero:");
		lblCajero.setBounds(10, 106, 48, 14);
		contentPane.add(lblCajero);

		lblNumeroDeCaja = new JLabel("Numero de caja:");
		lblNumeroDeCaja.setBounds(10, 145, 107, 14);
		contentPane.add(lblNumeroDeCaja);

		dia = new JTextField();
		dia.setBounds(99, 63, 107, 20);
		contentPane.add(dia);
		dia.setColumns(10);

		nombre_cajero = new JTextField();
		nombre_cajero.setBounds(99, 103, 154, 20);
		contentPane.add(nombre_cajero);
		nombre_cajero.setColumns(10);

		numero_caja = new JTextField();
		numero_caja.setBounds(99, 142, 154, 20);
		contentPane.add(numero_caja);
		numero_caja.setColumns(10);

		lblMes = new JLabel("Mes:");
		lblMes.setBounds(217, 66, 48, 14);
		contentPane.add(lblMes);

		mes = new JTextField();
		mes.setBounds(246, 63, 107, 20);
		contentPane.add(mes);
		mes.setColumns(10);

		lblAño = new JLabel("Año:");
		lblAño.setBounds(374, 66, 48, 14);
		contentPane.add(lblAño);

		año = new JTextField();
		año.setBounds(402, 63, 107, 20);
		contentPane.add(año);
		año.setColumns(10);

		panel = new JPanel();
		panel.setBounds(-16, 369, 556, 256);
		contentPane.add(panel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		menuBar.setBackground(new Color(204, 204, 255));
		menuBar.setBounds(445, 23, 95, 31);
		contentPane.add(menuBar, BorderLayout.NORTH);

		JMenu mnNewMenu = new JMenu("Busqueda por:");
		mnNewMenu.setSize(196, 29);
		menuBar.setBounds(10, 10, 95, 31);

		menuBar.add(mnNewMenu);

		JMenuItem mntmFiltrarPorFecha = new JMenuItem("Fecha");
		JMenuItem mntmFiltroPorNombreDelCajero = new JMenuItem("Nombre del cajero");
		JMenuItem mntmFiltroPorNumeroDeCaja = new JMenuItem("Numero de caja");

		JButton btnNuevaBusqueda = new JButton("Realizar nueva busqueda");
		btnNuevaBusqueda.setBounds(10, 10, 196, 31);
		contentPane.add(btnNuevaBusqueda);
		btnNuevaBusqueda.setVisible(false);

		JButton btnConsultarVentas = new JButton("Consultar ventas");
		btnConsultarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnConsultarVentas.setEnabled(false);
				btnNuevaBusqueda.setVisible(true);
				panel.setVisible(true);

				int numeroDeCaja = 0;

				// si esta visible significa que selecciono el filtro por caja
				if (lblNumeroDeCaja.isVisible()) {

					try {
						numeroDeCaja = Integer.parseInt(numero_caja.getText());
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "el campo número de caja solo admite números");
					}
					if (numeroDeCaja != 0) {
						datos = AdministracionSupermercado.getSupermercado().consultarVentas(numeroDeCaja);
					}
				}
				
				// si esta visible significa que selecciono el filtro por fecha
				if (lblDia.isVisible()) {

					try {
						int day = Integer.parseInt(dia.getText());
						int month = Integer.parseInt(mes.getText());
						int year = Integer.parseInt(año.getText());
						fecha = LocalDate.of(year, month, day);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "La fecha ingresada es incorrecta");
					}

					if (fecha != null) {
						// datos de las ventas
						datos = AdministracionSupermercado.getSupermercado().consultarVentas(fecha);

					}
				}
				// si esta visible significa que selecciono el filtro por nombre del cajero
				if (lblCajero.isVisible()) {

					if (!nombre_cajero.getText().isEmpty()) {
						datos = AdministracionSupermercado.getSupermercado().consultarVentas(nombre_cajero.getText());
					}
				}

				if (!datos.isEmpty()) {
					mostrarTabla();

				} else {
					JOptionPane.showMessageDialog(null, "No hay ventas para mostrar", "Error al montrar ventas",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnConsultarVentas.setBounds(10, 189, 243, 23);
		contentPane.add(btnConsultarVentas);

		btnNuevaBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshabilitarCampos();
				limpiarCampos();
				panel.removeAll();
				panel.setVisible(false);
				menuBar.setVisible(true);
				btnConsultarVentas.setVisible(false);
				btnConsultarVentas.setEnabled(true);
				btnNuevaBusqueda.setVisible(false);

			}
		});

		mntmFiltrarPorFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// deshabilitar menu
				menuBar.setVisible(false);
				btnNuevaBusqueda.setVisible(true);

				// habilitar filtro fecha
				lblDia.setVisible(true);
				lblMes.setVisible(true);
				lblAño.setVisible(true);
				dia.setVisible(true);
				mes.setVisible(true);
				año.setVisible(true);
				btnConsultarVentas.setVisible(true);
				btnConsultarVentas.setBounds(10, 111, 499, 23);
				setBounds(100, 100, 617, 221);

			}
		});

		mnNewMenu.add(mntmFiltrarPorFecha);

		mntmFiltroPorNombreDelCajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// deshabilitar menu
				menuBar.setVisible(false);
				btnNuevaBusqueda.setVisible(true);

				// habilitar filtro por cajero
				lblCajero.setVisible(true);
				nombre_cajero.setVisible(true);
				btnConsultarVentas.setVisible(true);
				lblCajero.setBounds(10, 72, 48, 14);
				nombre_cajero.setBounds(99, 69, 154, 20);
				btnConsultarVentas.setBounds(10, 111, 499, 23);

			}
		});
		mnNewMenu.add(mntmFiltroPorNombreDelCajero);

		mntmFiltroPorNumeroDeCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// deshabilitar menu
				menuBar.setVisible(false);
				btnNuevaBusqueda.setVisible(true);

				// habilitar filtro por numero de caja
				lblNumeroDeCaja.setVisible(true);
				numero_caja.setVisible(true);
				btnConsultarVentas.setVisible(true);
				lblNumeroDeCaja.setBounds(10, 69, 107, 14);
				numero_caja.setBounds(151, 66, 154, 20);
				btnConsultarVentas.setBounds(10, 102, 499, 23);
			}
		});
		mnNewMenu.add(mntmFiltroPorNumeroDeCaja);

		// deshabilitar campos antes de seleccionar filtro
		deshabilitarCampos();
		btnConsultarVentas.setVisible(false);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(317, 10, 196, 31);
		contentPane.add(btnSalir);

	}

	public void mostrarTabla() {

		// modifica para que muestre la tabla
		setBounds(100, 100, 584, 508);
		panel.setBounds(10, 223, 600, 232);

		// Array de ‘String’ con los titulos de las columnas de productos vendidos
		columnNames = new Vector<String>();
		columnNames.add("Fecha");
		columnNames.add("Número de caja");
		columnNames.add("Número de venta");
		columnNames.add("Nombre del cajero");
		columnNames.add("Total");

		// Creamos un scrollpanel y se lo agregamos a la tabla
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 600, 349);
		panel.add(scrollPane);

		TableModel tableModel = new DefaultTableModel(datos, columnNames);
		table = new JTable(datos, columnNames);
		table.setVisible(true);
		table.setPreferredScrollableViewportSize(new Dimension(600, 80));
		table.setModel(tableModel);
		table.setBounds(100, 100, 600, 349);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

	}

	public void deshabilitarCampos() {
		lblDia.setVisible(false);
		lblMes.setVisible(false);
		lblAño.setVisible(false);
		lblCajero.setVisible(false);
		lblNumeroDeCaja.setVisible(false);
		dia.setVisible(false);
		mes.setVisible(false);
		año.setVisible(false);
		nombre_cajero.setVisible(false);
		numero_caja.setVisible(false);

	}

	public void limpiarCampos() {
		dia.setText("");
		mes.setText("");
		año.setText("");
		nombre_cajero.setText("");
		numero_caja.setText("");
	}
}
