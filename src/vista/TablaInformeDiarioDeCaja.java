package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.AdministracionSupermercado;
import modelo.CajaView;

public class TablaInformeDiarioDeCaja extends JFrame {

	private JPanel contentPane;
	private JTextField dia;
	private JTextField mes;
	private JTextField año;
	private JLabel lblNewLabel_3;
	private JTextField nombre_cajero;
	private JLabel lblNewLabel_4;
	private JTextField numero_caja;
	private JButton btnConsultarInformeDiarioDeCAja;
	private JTable table;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	JPanel panel = null;
	Vector<Vector<String>> datos = null;
	Vector<String> columnNames = null;
	LocalDate fecha = LocalDate.now();
	private JButton btnSalir;
	private JButton btnModificarDatos;
	private JLabel lblSaldoFinal;
	private JLabel lblSaldoInicial;

	/**
	 * Create the frame.
	 */
	public TablaInformeDiarioDeCaja() {
		setTitle("Informe diario de caja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 594, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel_1 = new JLabel("Dia:");
		lblNewLabel_1.setBounds(11, 11, 48, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Mes:");
		lblNewLabel.setBounds(177, 11, 48, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Año:");
		lblNewLabel_2.setBounds(343, 11, 48, 14);
		contentPane.add(lblNewLabel_2);

		dia = new JTextField();
		dia.setBounds(70, 8, 96, 20);
		contentPane.add(dia);
		dia.setColumns(10);

		mes = new JTextField();
		mes.setBounds(236, 8, 96, 20);
		contentPane.add(mes);
		mes.setColumns(10);

		año = new JTextField();
		año.setBounds(402, 8, 96, 20);
		contentPane.add(año);
		año.setColumns(10);

		lblNewLabel_3 = new JLabel("Nombre del cajero:");
		lblNewLabel_3.setBounds(11, 61, 155, 14);
		contentPane.add(lblNewLabel_3);

		nombre_cajero = new JTextField();
		nombre_cajero.setBounds(166, 58, 178, 20);
		contentPane.add(nombre_cajero);
		nombre_cajero.setColumns(10);

		lblNewLabel_4 = new JLabel("Numero de caja:");
		lblNewLabel_4.setBounds(368, 64, 108, 14);
		contentPane.add(lblNewLabel_4);

		numero_caja = new JTextField();
		numero_caja.setBounds(472, 61, 96, 20);
		contentPane.add(numero_caja);
		numero_caja.setColumns(10);

		panel = new JPanel();
		panel.setBounds(11, 292, 556, 270);
		contentPane.add(panel);

		JTextArea saldoInicial = new JTextArea();
		saldoInicial.setBounds(102, 259, 178, 22);
		contentPane.add(saldoInicial);
		saldoInicial.setVisible(false);

		JTextArea SaldoFinal = new JTextArea();
		SaldoFinal.setBounds(394, 259, 178, 22);
		contentPane.add(SaldoFinal);
		SaldoFinal.setVisible(false);

		// fecha actual y no se pueden editar
		dia.setText(fecha.getDayOfMonth() + "");
		mes.setText(fecha.getMonthValue() + "");
		año.setText(fecha.getYear() + "");
		dia.setEnabled(false);
		mes.setEnabled(false);
		año.setEnabled(false);

		btnConsultarInformeDiarioDeCAja = new JButton("Consultar informe diario de caja");
		btnConsultarInformeDiarioDeCAja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombre_cajero.setEnabled(false);
				numero_caja.setEnabled(false);
				btnModificarDatos.setEnabled(true);
				btnConsultarInformeDiarioDeCAja.setEnabled(false);

				int numeroDeCaja = 0;
				CajaView caja = null;

				try {
					numeroDeCaja = Integer.parseInt(numero_caja.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "el campo número de caja solo admite números");
				}

				if (numeroDeCaja != 0 && !nombre_cajero.getText().isEmpty()) {

					datos = AdministracionSupermercado.getSupermercado().generarInformeDiarioDeCaja(fecha,
							nombre_cajero.getText(), numeroDeCaja);
					caja = AdministracionSupermercado.getSupermercado().buscarCajaViewParaInformeAlCierre(numeroDeCaja);

				} else {
					JOptionPane.showMessageDialog(null, "todos los campos son obligatorios");
				}

				if (!datos.isEmpty()) {
					
					//si la caja esta abierta
					if (caja != null) {
						mostrarTabla();

						if (caja != null) {
							saldoInicial.setVisible(true);
							SaldoFinal.setVisible(true);
							saldoInicial.setEnabled(false);
							SaldoFinal.setEnabled(false);
							saldoInicial.setText(caja.getSaldoInicialEnEfectivo() + "");
							caja.actualizarSaldoEnEfectivo();
							SaldoFinal.setText(caja.getSaldoFinalEnEfectivo() + "");
						}
						
						
					} else {
						JOptionPane.showMessageDialog(null, "La caja ingresada aun se encuentra abierta",
								"Error al ingresar número de caja", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No hay informacion para mostrar con los datos ingresados",
							"Error al mostrar ventas", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnConsultarInformeDiarioDeCAja.setBounds(11, 114, 557, 23);
		contentPane.add(btnConsultarInformeDiarioDeCAja);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(11, 198, 558, 23);
		contentPane.add(btnSalir);

		btnModificarDatos = new JButton("Modificar datos ingresados");
		btnModificarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(100, 100, 594, 273);
				panel.removeAll();
				nombre_cajero.setText("");
				numero_caja.setText("");
				nombre_cajero.setEnabled(true);
				numero_caja.setEnabled(true);
				btnConsultarInformeDiarioDeCAja.setEnabled(true);
				btnModificarDatos.setEnabled(false);
			}
		});
		btnModificarDatos.setBounds(11, 156, 557, 23);
		contentPane.add(btnModificarDatos);

		lblSaldoFinal = new JLabel("Saldo final:");
		lblSaldoFinal.setBounds(303, 264, 88, 14);
		contentPane.add(lblSaldoFinal);

		lblSaldoInicial = new JLabel("Saldo inicial:");
		lblSaldoInicial.setBounds(5, 264, 108, 14);
		contentPane.add(lblSaldoInicial);

	}

	public void mostrarTabla() {

		// modifica para que muestre la tabla
		setBounds(100, 100, 620, 615);
		panel.setBounds(5, 292, 600, 270);

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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

	}
}
