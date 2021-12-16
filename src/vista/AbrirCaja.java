package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.AdministracionSupermercado;
import modelo.CajaView;

public class AbrirCaja extends JFrame {

	private JPanel contentPane;
	private JTextField numeroDeCaja;
	private JTextField nombreDelCajero;
	private JTextField saldoInicialEnEfectivo;
	private JTextField año;
	private JTextField mes;
	private JTextField dia;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public AbrirCaja() {
		setTitle("Abrir caja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNumeroDeCaja = new JLabel("Numero de caja:");
		lblNumeroDeCaja.setBounds(10, 75, 144, 14);
		contentPane.add(lblNumeroDeCaja);

		JLabel lblNewLabel_1 = new JLabel("Nombre cajero:");
		lblNewLabel_1.setBounds(10, 110, 144, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Saldo inicial en efectivo:");
		lblNewLabel_2.setBounds(10, 147, 161, 14);
		contentPane.add(lblNewLabel_2);

		numeroDeCaja = new JTextField();
		numeroDeCaja.setBounds(147, 72, 96, 20);
		contentPane.add(numeroDeCaja);
		numeroDeCaja.setColumns(10);

		nombreDelCajero = new JTextField();
		nombreDelCajero.setBounds(147, 106, 161, 20);
		contentPane.add(nombreDelCajero);
		nombreDelCajero.setColumns(10);

		saldoInicialEnEfectivo = new JTextField();
		saldoInicialEnEfectivo.setBounds(147, 144, 96, 20);
		contentPane.add(saldoInicialEnEfectivo);
		saldoInicialEnEfectivo.setColumns(10);

		año = new JTextField();
		año.setBounds(60, 8, 62, 20);
		contentPane.add(año);
		año.setColumns(10);

		mes = new JTextField();
		mes.setBounds(211, 7, 62, 20);
		contentPane.add(mes);
		mes.setColumns(10);

		dia = new JTextField();
		dia.setBounds(372, 8, 62, 20);
		contentPane.add(dia);
		dia.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Año:");
		lblNewLabel_3.setBounds(10, 10, 48, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Mes:");
		lblNewLabel.setBounds(177, 11, 48, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("Dia:");
		lblNewLabel_4.setBounds(339, 11, 48, 14);
		contentPane.add(lblNewLabel_4);

		// setea los campos fecha, mes y año para q el usuario no tenga q ingresarlos
		año.setText(LocalDate.now().getYear() + "");
		mes.setText(LocalDate.now().getMonthValue() + "");
		dia.setText(LocalDate.now().getDayOfMonth() + "");

		// inhabilita los campos
		año.setEnabled(false);
		mes.setEnabled(false);
		dia.setEnabled(false);

		JButton btnAbrirCaja = new JButton("Abrir caja");

		btnAbrirCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cantSaldoInicialEnEfectivo = castearDatoDouble(saldoInicialEnEfectivo.getText(),
						"El campo saldo inicial en efectivo debe ser numerico y decimal",
						"Error al ingresar el saldo inicial en efectivo");
				int nroCaja = castearDatoInt(numeroDeCaja.getText(), "El campo es obligatorio y debe ser númerico",
						"error al ingresar numero de caja");

				// los campos son obligatorios
				if (nroCaja != 0 || !nombreDelCajero.getText().isEmpty() || cantSaldoInicialEnEfectivo != 0) {

					CajaView caja = AdministracionSupermercado.getSupermercado().buscarArqueoDeCajaView(nroCaja,
							LocalDate.now(), nombreDelCajero.getText());

					// se crea si no hay una abierta con mismo datos
					if (caja == null) {
						AdministracionSupermercado.getSupermercado().abrirCaja(LocalDate.now(), nroCaja,
								nombreDelCajero.getText(), cantSaldoInicialEnEfectivo);
						JOptionPane.showMessageDialog(null,
								"Abierto el arqueo de caja, recuerde solo se puede realizar 1 por día", "Abrir caja",
								JOptionPane.INFORMATION_MESSAGE);
						
						// al cerrar la caja se oculta el boton cerrar caja
						btnAbrirCaja.setVisible(false);
						
						// centro el boton salir
						btnSalir.setBounds(158, 183, 130, 23);

					} else {
						JOptionPane.showMessageDialog(null, "Ya se realizo un arqueo de caja con mismo numero o fecha",
								"Error al abrir caja", JOptionPane.ERROR_MESSAGE);
						limpiarCampos();

					}

				} else {
					JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error al abrir caja",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		btnAbrirCaja.setBounds(147, 183, 130, 23);
		contentPane.add(btnAbrirCaja);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(304, 183, 130, 23);
		contentPane.add(btnSalir);

	}

	private int castearDatoInt(String valor, String mensaje, String mensajeError) {
		int intValor = 0;
		try {
			intValor = Integer.parseInt(valor);

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, mensaje, mensajeError, JOptionPane.ERROR_MESSAGE);
		}
		return intValor;
	}

	private double castearDatoDouble(String valor, String mensaje, String mensajeError) {
		double doubleValor = 0.0;
		try {
			doubleValor = Double.parseDouble(valor);

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, mensaje, mensajeError, JOptionPane.ERROR_MESSAGE);
		}
		return doubleValor;
	}

	public void limpiarCampos() {
		numeroDeCaja.setText("");
		nombreDelCajero.setText("");
		saldoInicialEnEfectivo.setText("");

	}

}
