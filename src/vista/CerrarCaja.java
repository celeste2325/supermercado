package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.AdministracionSupermercado;
import modelo.CajaView;

public class CerrarCaja extends JFrame {

	private JPanel contentPane;
	private JTextField numeroDeCaja;
	private JTextField nombreDelCajero;
	private JTextField año;
	private JTextField mes;
	private JTextField dia;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public CerrarCaja() {
		setTitle("Cerrar caja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 445, 281);
		// setBounds(100, 100, 445, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		año = new JTextField();
		año.setBounds(40, 19, 62, 20);
		contentPane.add(año);
		año.setColumns(10);

		mes = new JTextField();
		mes.setBounds(159, 19, 62, 20);
		contentPane.add(mes);
		mes.setColumns(10);

		dia = new JTextField();
		dia.setBounds(280, 19, 62, 20);
		contentPane.add(dia);
		dia.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Año:");
		lblNewLabel_3.setBounds(10, 22, 48, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Mes:");
		lblNewLabel.setBounds(126, 22, 48, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("Dia:");
		lblNewLabel_4.setBounds(241, 22, 48, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNumeroDeCaja = new JLabel("Numero de caja:");
		lblNumeroDeCaja.setBounds(10, 58, 144, 14);
		contentPane.add(lblNumeroDeCaja);

		JLabel lblNewLabel_1 = new JLabel("Nombre cajero:");
		lblNewLabel_1.setBounds(10, 94, 144, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Saldo inicial en efectivo:");
		lblNewLabel_2.setBounds(10, 130, 161, 14);
		contentPane.add(lblNewLabel_2);

		numeroDeCaja = new JTextField();
		numeroDeCaja.setBounds(159, 50, 96, 20);
		contentPane.add(numeroDeCaja);
		numeroDeCaja.setColumns(10);

		nombreDelCajero = new JTextField();
		nombreDelCajero.setBounds(159, 88, 161, 20);
		contentPane.add(nombreDelCajero);
		nombreDelCajero.setColumns(10);

		JTextArea saldoInicialEnEfectivo = new JTextArea();
		saldoInicialEnEfectivo.setBounds(159, 126, 127, 22);
		contentPane.add(saldoInicialEnEfectivo);

		JTextArea saldoFinalEnEfectivo = new JTextArea();
		saldoFinalEnEfectivo.setBounds(159, 166, 127, 22);
		contentPane.add(saldoFinalEnEfectivo);

		saldoFinalEnEfectivo.setEnabled(false);
		saldoInicialEnEfectivo.setEnabled(false);

		JLabel lblNewLabel_5 = new JLabel("Saldo final en efectivo");
		lblNewLabel_5.setBounds(10, 166, 144, 14);
		contentPane.add(lblNewLabel_5);

		// setea la fecha para q el usuario no tenga q ingresarlos
		año.setText(LocalDate.now().getYear() + "");
		mes.setText(LocalDate.now().getMonthValue() + "");
		dia.setText(LocalDate.now().getDayOfMonth() + "");

		// inhabilita los campos para que no se puedan editar
		año.setEnabled(false);
		mes.setEnabled(false);
		dia.setEnabled(false);

		JButton btnInformeDeVentasRealizadas = new JButton("Informe de ventas realizadas");
		btnInformeDeVentasRealizadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cerrar ventana cierre de caja
				dispose();
				int numeroCaja = castearDatoInt(numeroDeCaja.getText(), "El nro de caja debe ser numerico",
						"Error al ingresar número de caja");
				CajaView caja = AdministracionSupermercado.getSupermercado().buscarArqueoDeCajaView(numeroCaja,
						LocalDate.now(), nombreDelCajero.getText());
				if (caja != null) {

					// le paso por parametro la caja de la cual va a hacer el informe
					VentasRealizadasAlCierrreDeCaja vistaVentasRealizadasAlCierreDeCaja = new VentasRealizadasAlCierrreDeCaja(
							caja);
					vistaVentasRealizadasAlCierreDeCaja.setVisible(true);
				}
			}
		});
		btnInformeDeVentasRealizadas.setBounds(6, 208, 265, 23);
		btnInformeDeVentasRealizadas.setVisible(false);
		contentPane.add(btnInformeDeVentasRealizadas);

		JButton btnCerrarCaja = new JButton("Cerrar caja");
		btnCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nroCaja = castearDatoInt(numeroDeCaja.getText(), "El campo es obligatorio y debe ser númerico",
						"error al ingresar numero de caja");
				

				if (nroCaja != 0 || !nombreDelCajero.getText().isEmpty()) {
					CajaView caja = AdministracionSupermercado.getSupermercado().buscarArqueoDeCajaView(nroCaja,
							LocalDate.now(), nombreDelCajero.getText());
					if (caja != null) {
						if (!AdministracionSupermercado.getSupermercado().ventaSinFinalizar()) {
							
							AdministracionSupermercado.getSupermercado().cerrarCaja(nroCaja);
							
							JOptionPane.showMessageDialog(null, "La caja fue cerrada", "Cerrar caja",
									JOptionPane.INFORMATION_MESSAGE);

							// devolver saldo inicial y final
							saldoInicialEnEfectivo.setText(caja.getSaldoInicialEnEfectivo() + "");
							caja.actualizarSaldoEnEfectivo();
							saldoFinalEnEfectivo.setText(caja.getSaldoFinalEnEfectivo() + "");

							// al cerrar la caja se elimina el boton cerrar caja
							btnCerrarCaja.setVisible(false);
							
							// deshabilita botones y campo
							numeroDeCaja.setEnabled(false);
							nombreDelCajero.setEnabled(false);
							
							// agrega el boton para ver informe de ventas
							btnInformeDeVentasRealizadas.setVisible(true);
							btnInformeDeVentasRealizadas.setBounds(6, 208, 265, 23);
							
							// al agregar el boton ver informe de ventas modifica la ubicacion del boton salir
							btnSalir.setBounds(292, 208, 127, 23);	
						} else {
							JOptionPane.showMessageDialog(null, "Se encuentra una venta sin finalizar",
									"error al cerrar caja", JOptionPane.ERROR_MESSAGE);
						}
						

					} else {
						// mismo cajero que abre la caja debe cerrarla
						JOptionPane.showMessageDialog(null, "Verifique el numero de la caja y el nombre del cajero",
								"error al cerrar caja", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Los campos son obligatorios", "error al cerrar caja",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnCerrarCaja.setBounds(17, 208, 189, 23);
		contentPane.add(btnCerrarCaja);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(223, 208, 189, 23);
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
}
