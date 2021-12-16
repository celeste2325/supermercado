package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.AdministracionSupermercado;
import modelo.VentaView;

public class CobrarVenta extends JFrame {

	private JPanel contentPane;
	private JTextField montoEnEfectivoRecibido;
	private double montoRecibidoTotal = 0;

	/**
	 * Create the frame.
	 */
	public CobrarVenta(int numeroDeVenta) {
		setTitle("Cobrar venta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 525, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Monto en efectivo recibido:");
		lblNewLabel.setBounds(8, 12, 180, 17);
		contentPane.add(lblNewLabel);

		montoEnEfectivoRecibido = new JTextField();
		montoEnEfectivoRecibido.setBounds(198, 12, 170, 20);
		contentPane.add(montoEnEfectivoRecibido);
		montoEnEfectivoRecibido.setColumns(10);

		JButton btnCalcularVuelto = new JButton("Calcular vuelto");
		btnCalcularVuelto.setBounds(378, 11, 121, 23);
		contentPane.add(btnCalcularVuelto);

		JButton btnConfirmarVenta = new JButton("Confirmar la venta");
		btnConfirmarVenta.setBounds(8, 115, 415, 23);
		btnConfirmarVenta.setEnabled(false);
		contentPane.add(btnConfirmarVenta);

		JTextArea vueltoAEntregar = new JTextArea();
		vueltoAEntregar.setBounds(198, 53, 170, 22);
		vueltoAEntregar.setEnabled(false);
		contentPane.add(vueltoAEntregar);

		JLabel lblNewLabel_1 = new JLabel("Vuelto:");
		lblNewLabel_1.setBounds(8, 58, 133, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnCancelarVenta = new JButton("Cancelar venta");
		btnCancelarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministracionSupermercado.getSupermercado().cancelarVenta(numeroDeVenta);
				JOptionPane.showMessageDialog(null, "La venta ha sido cancelada", "Venta cancelada",
						JOptionPane.ERROR_MESSAGE);
				btnConfirmarVenta.setVisible(false);
				btnCancelarVenta.setVisible(false);
				AdministracionSupermercado.getSupermercado().cancelarVenta(numeroDeVenta);
				dispose();
			}
		});
		btnCancelarVenta.setBounds(8, 170, 415, 23);
		contentPane.add(btnCancelarVenta);

		btnCalcularVuelto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double montoRecibido = castearDatoDouble(montoEnEfectivoRecibido.getText(),
						"El campo monto recibido es obligatorio y numerico", "Error al ingresar monto");
				montoRecibidoTotal += montoRecibido;

				if (montoRecibido != 0) {

					double vuelto = AdministracionSupermercado.getSupermercado().cobrarVenta(numeroDeVenta,
							montoRecibidoTotal);

					if (vuelto >= 0) {
						btnConfirmarVenta.setEnabled(true);
						vueltoAEntregar.setText(vuelto + "");
					} else {
						String[] opciones = { "Recibir dinero", "Salir" };
						int cobrarVenta = JOptionPane.showOptionDialog(null,
								"Faltan " + vuelto * -1 + " para completar el pago", "Error al completar la venta",
								JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, opciones, null);

						if (JOptionPane.OK_OPTION == cobrarVenta) {
							montoEnEfectivoRecibido.setText("");
							vueltoAEntregar.setText("");
							montoRecibido += montoRecibido;
						} else {
							dispose();
						}
					}

				}
			}
		});

		btnConfirmarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentaView venta = AdministracionSupermercado.getSupermercado().buscarVentaView(numeroDeVenta);

				AdministracionSupermercado.getSupermercado().confirmarVenta(numeroDeVenta);
				AdministracionSupermercado.getSupermercado().registrarVenta(numeroDeVenta, venta.getNroCaja());
				;
				JOptionPane.showMessageDialog(null, "Venta confirmada");
				dispose();
				
				

			}
		});
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
}
