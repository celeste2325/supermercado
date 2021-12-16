package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.AdministracionSupermercado;
import modelo.CajaView;
import modelo.ProductoView;
import modelo.VentaView;

public class IniciarVenta extends JFrame {

	private JPanel contentPane;
	private JTextField nombreDelCajero;
	private JTextField fecha;
	private JLabel lblNewLabel_2;
	private JTextField nroDeVenta;
	private JTextField nroDeCaja;
	private JButton btnSalir;
	private JButton btnCalcularTotal;
	private int numeroDeVenta;

	/**
	 * Create the frame.
	 */
	public IniciarVenta() {
		setTitle("Iniciar venta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 407, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNumeroDeCaja = new JLabel("N° de caja:");
		lblNumeroDeCaja.setBounds(10, 11, 144, 14);
		contentPane.add(lblNumeroDeCaja);

		JLabel lblNewLabel_1 = new JLabel("Nombre cajero:");
		lblNewLabel_1.setBounds(10, 98, 144, 14);
		contentPane.add(lblNewLabel_1);

		nombreDelCajero = new JTextField();
		nombreDelCajero.setBounds(116, 95, 130, 20);
		contentPane.add(nombreDelCajero);
		nombreDelCajero.setColumns(10);

		fecha = new JTextField();
		fecha.setBounds(116, 56, 130, 20);
		contentPane.add(fecha);
		fecha.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Fecha:");
		lblNewLabel_3.setBounds(10, 59, 48, 14);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_2 = new JLabel("Numero de venta:");
		lblNewLabel_2.setBounds(10, 144, 144, 14);
		contentPane.add(lblNewLabel_2);

		nroDeVenta = new JTextField();
		nroDeVenta.setBounds(116, 141, 130, 20);
		contentPane.add(nroDeVenta);
		nroDeVenta.setColumns(10);

		nroDeCaja = new JTextField();
		nroDeCaja.setBounds(116, 8, 130, 20);
		contentPane.add(nroDeCaja);
		nroDeCaja.setColumns(10);

		JButton btnEscanearProductos = new JButton("Escanear producto");
		btnEscanearProductos.setBounds(21, 191, 163, 23);
		contentPane.add(btnEscanearProductos);

		JButton btnBuscarCaja = new JButton("Buscar caja");
		btnBuscarCaja.setBounds(251, 7, 130, 23);
		contentPane.add(btnBuscarCaja);

		deshabilitarCampos();
		btnEscanearProductos.setEnabled(false);

		btnSalir = new JButton("Salir");
		
		btnSalir.setBounds(205, 234, 163, 23);
		contentPane.add(btnSalir);

		btnCalcularTotal = new JButton("Calcular total");
		btnCalcularTotal.setBounds(21, 234, 163, 23);
		btnCalcularTotal.setEnabled(false);
		contentPane.add(btnCalcularTotal);

		JButton btnFinalizarEscaneo = new JButton("Finalizar escaneo");
		btnFinalizarEscaneo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCalcularTotal.setEnabled(true);
				btnEscanearProductos.setEnabled(false);
				btnFinalizarEscaneo.setEnabled(false);
			}

		});
		btnFinalizarEscaneo.setBounds(205, 191, 163, 23);
		contentPane.add(btnFinalizarEscaneo);

		btnBuscarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroDeCaja = castearDatoInt(nroDeCaja.getText(), "El numero de caja debe ser numerico",
						"Error al ingresar el numero de caja");

				if (numeroDeCaja != 0) {
					CajaView caja = AdministracionSupermercado.getSupermercado().buscarCajaView(numeroDeCaja);
					if (caja != null) {

						numeroDeVenta = AdministracionSupermercado.getSupermercado().IniciarVenta(numeroDeCaja);

						// inicia venta con datos de la caja
						fecha.setText(caja.getFecha() + "");
						nombreDelCajero.setText(caja.getNombreCajero());
						nroDeVenta.setText(numeroDeVenta + "");

						// deshabilita botones
						btnBuscarCaja.setEnabled(false);
						nroDeCaja.setEnabled(false);

						// habilita escaneo de productos
						btnEscanearProductos.setEnabled(true);

					} else {
						JOptionPane.showMessageDialog(null, "La caja no esta abierta", "Error al buscar caja",
								JOptionPane.ERROR_MESSAGE);
						nroDeCaja.setText("");

					}

				}
			}
		});
		
		

		btnEscanearProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroDeCaja = castearDatoInt(nroDeCaja.getText(), "El numero de caja debe ser numerico",
						"Error al ingresar el numero de caja");

				String codigoProducto = JOptionPane.showInputDialog("Ingresa el código del producto");
				String cantidadProducto = JOptionPane.showInputDialog("Ingresa la cantidad");

				int cantProducto = castearDatoInt(cantidadProducto, "La cantidad del producto debe ser numerico",
						"Error al ingresar cantidad");

				if (numeroDeCaja != 0) {
					ProductoView producto = AdministracionSupermercado.getSupermercado()
							.buscarProductoView(codigoProducto);

					if (cantProducto != 0) {
						if (producto != null) {

							AdministracionSupermercado.getSupermercado().procesarVenta(codigoProducto, cantProducto,
									numeroDeVenta);

						} else {
							JOptionPane.showMessageDialog(null, "El producto es inexistente",
									"Error al verificar código de producto", JOptionPane.ERROR_MESSAGE);
						}

					}

				}

			}
		});

		btnCalcularTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// para traer --> total de compra de la venta
				VentaView nuevaVenta = AdministracionSupermercado.getSupermercado().buscarVentaView(numeroDeVenta);

				String[] opciones = { "Cobrar venta", "Cancelar" };
				int resultVenta = JOptionPane.showOptionDialog(null,
						"El total de la venta es:  " + nuevaVenta.getTotalCompra() + "", "Total venta",
						JOptionPane.OK_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones, null);

				if (JOptionPane.OK_OPTION == resultVenta) {

					CobrarVenta vistaCobrarVenta = new CobrarVenta(numeroDeVenta);
					vistaCobrarVenta.setVisible(true);

				} 
				dispose();

			}
		});

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdministracionSupermercado.getSupermercado().cancelarVenta(numeroDeVenta);
				dispose();
			}
		});
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

	private void deshabilitarCampos() {
		fecha.setEnabled(false);
		nombreDelCajero.setEnabled(false);
		nroDeVenta.setEnabled(false);
	}
}
