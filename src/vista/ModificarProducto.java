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
import modelo.ProductoView;
import modelo.ProveedorView;

public class ModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField codigo;
	private JTextField cuit_proveedor;
	private JTextField marca;
	private JTextField descripcion;
	private JTextField precio;
	private JTextField stock;
	private JTextField stock_minimo;
	private JTextField ped_reposicion;
	private JButton btnVerificarCodigo;
	private JButton btnConsultarCuit;
	private JButton btnModificarProducto;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public ModificarProducto() {
		setTitle("Modificar producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(10, 21, 161, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cuit proveedor:");
		lblNewLabel_1.setBounds(10, 56, 161, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Marca: ");
		lblNewLabel_2.setBounds(10, 91, 161, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Descripcion:");
		lblNewLabel_3.setBounds(10, 126, 161, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Precio:");
		lblNewLabel_5.setBounds(10, 161, 161, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Stock:");
		lblNewLabel_6.setBounds(10, 196, 161, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Stock minimo:");
		lblNewLabel_7.setBounds(10, 231, 161, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Pedido de reposicion:");
		lblNewLabel_8.setBounds(10, 266, 161, 14);
		contentPane.add(lblNewLabel_8);

		codigo = new JTextField();
		codigo.setBounds(141, 22, 96, 20);
		contentPane.add(codigo);
		codigo.setColumns(10);

		cuit_proveedor = new JTextField();
		cuit_proveedor.setBounds(141, 56, 60, 20);
		contentPane.add(cuit_proveedor);
		cuit_proveedor.setColumns(10);

		marca = new JTextField();
		marca.setBounds(141, 90, 142, 20);
		contentPane.add(marca);
		marca.setColumns(10);

		descripcion = new JTextField();
		descripcion.setBounds(141, 124, 171, 20);
		contentPane.add(descripcion);
		descripcion.setColumns(10);

		precio = new JTextField();
		precio.setBounds(141, 158, 96, 20);
		contentPane.add(precio);
		precio.setColumns(10);

		stock = new JTextField();
		stock.setBounds(141, 192, 60, 20);
		contentPane.add(stock);
		stock.setColumns(10);

		stock_minimo = new JTextField();
		stock_minimo.setBounds(141, 226, 60, 20);
		contentPane.add(stock_minimo);
		stock_minimo.setColumns(10);

		ped_reposicion = new JTextField();
		ped_reposicion.setBounds(141, 260, 60, 20);
		contentPane.add(ped_reposicion);
		ped_reposicion.setColumns(10);

		btnVerificarCodigo = new JButton("Verificar codigo");
		btnVerificarCodigo.setBounds(322, 17, 161, 23);
		contentPane.add(btnVerificarCodigo);

		btnConsultarCuit = new JButton("Consultar cuit");
		btnConsultarCuit.setBounds(322, 52, 161, 23);
		contentPane.add(btnConsultarCuit);

		btnModificarProducto = new JButton("Modificar producto");
		btnModificarProducto.setBounds(142, 302, 161, 23);
		contentPane.add(btnModificarProducto);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(322, 302, 161, 23);
		contentPane.add(btnCancelar);

		// deshabilita botones
		btnConsultarCuit.setEnabled(false);
		btnModificarProducto.setEnabled(false);
		
		// deshabilita campos hasta que se valide el cuit
		deshabilitarCampos();

		btnVerificarCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!codigo.getText().isEmpty()) {
					ProductoView producto = AdministracionSupermercado.getSupermercado()
							.buscarProductoView(codigo.getText());
					if (producto != null) {

						// deshabilita campo para que no puedo editar el codigo
						codigo.setEnabled(false);

						// deshabilita boton al validar codigo del producto
						btnVerificarCodigo.setEnabled(false);

						// habilita campos y boton despues de validar codigo
						btnConsultarCuit.setEnabled(true);
						cuit_proveedor.setEnabled(true);

					} else {
						JOptionPane.showMessageDialog(null, "El producto no existe o se encuentra inactivo",
								"error al verificar cuit", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El campo codigo es obligatorio");
				}

			}

		});

		btnConsultarCuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!cuit_proveedor.getText().isEmpty()) {
					ProveedorView proveedor = AdministracionSupermercado.getSupermercado()
							.buscarProveedorView(cuit_proveedor.getText());
					if (proveedor != null) {

						ProductoView producto = AdministracionSupermercado.getSupermercado()
								.buscarProductoView(codigo.getText());
						marca.setText(producto.getMarca());
						descripcion.setText(producto.getDescripcion());
						precio.setText(producto.getPrecio() + "");
						stock.setText(producto.getStock() + "");
						stock_minimo.setText(producto.getStockMinimo() + "");
						ped_reposicion.setText(producto.getPedidoDeReposicion() + "");

						// habilita campos y boton despues de validar cuit
						habilitarCampos();
						btnModificarProducto.setEnabled(true);
						// para que no busque otro proveedor y asi no se pisen los datos devueltos
						btnConsultarCuit.setEnabled(false);

					} else {

						String[] opciones = { "Crear proveedor", "Cancelar" };
						int resultVentana = JOptionPane.showOptionDialog(null,
								"El proveedor no existe o se encuentra inactivo", "Error al validar cuit",
								JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, opciones, null);

						if (JOptionPane.OK_OPTION == resultVentana) {
							dispose();
							CrearProveedor vistaCrearProveedor = new CrearProveedor();
							vistaCrearProveedor.setVisible(true);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "El campo cuit es obligatorio");
				}
			}
		});

		btnModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoView producto = AdministracionSupermercado.getSupermercado()
						.buscarProductoView(codigo.getText());

				double cantPrecio = 0.0;

				int cantStock = castearDatos(stock.getText(),
						"El stock es un campo obligatorio, debe ser numérico y entero", "error al ingresar precio");
				int cantStockMinimo = castearDatos(stock_minimo.getText(),
						"El stock mínimo es un campo obligatorio, debe ser numérico y entero",
						"error al ingresar precio");
				int cantPedidoDeReposicion = castearDatos(ped_reposicion.getText(),
						"El pedido de reposición es un campo obligatorio, debe ser numérico y entero",
						"error al ingresar precio");

				try {
					cantPrecio = Double.parseDouble(precio.getText());

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,
							"El precio es un campo obligatorio, debe ser numérico y decimal",
							"error al ingresar precio", JOptionPane.ERROR_MESSAGE);
				}

				if (producto != null) {

					ProveedorView proveedor = AdministracionSupermercado.getSupermercado()
							.buscarProveedorView(cuit_proveedor.getText());

					if (proveedor != null) {
						if (cantPrecio != 0 && cantPrecio != 0 && cantPrecio != 0 && cantPrecio != 0
								&& cantPrecio != 0) {
							
							// muestra datos a modificar
							AdministracionSupermercado.getSupermercado().modificarProducto(codigo.getText(),
									descripcion.getText(), marca.getText(), cantPrecio, cantStock, cantStockMinimo,
									cantPedidoDeReposicion, cuit_proveedor.getText());

							// deshabilita campos para validar nuevo cuit
							deshabilitarCampos();

							// limpia campos para agregar datos de nuevo proveedor
							limpiarCampos();

							// resetea formulario
							btnConsultarCuit.setEnabled(true);
							btnVerificarCodigo.setEnabled(true);
							btnModificarProducto.setEnabled(false);
							codigo.setEnabled(true);

						}

					} else {

						String[] opciones = { "Crear proveedor", "Cancelar" };
						int resultVentana = JOptionPane.showOptionDialog(null,
								"El proveedor no existe o se encuentra inactivo", "Error al validar cuit",
								JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, opciones, null);

						if (JOptionPane.OK_OPTION == resultVentana) {
							dispose();
							CrearProveedor vistaCrearProveedor = new CrearProveedor();
							vistaCrearProveedor.setVisible(true);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "El producto no existe o se encuentra inactivo",
							"error al verificar cuit", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	private void deshabilitarCampos() {
		cuit_proveedor.setEnabled(false);
		marca.setEnabled(false);
		descripcion.setEnabled(false);
		precio.setEnabled(false);
		stock.setEnabled(false);
		stock_minimo.setEnabled(false);
		ped_reposicion.setEnabled(false);
	}

	private void habilitarCampos() {
		cuit_proveedor.setEnabled(true);
		marca.setEnabled(true);
		descripcion.setEnabled(true);
		precio.setEnabled(true);
		stock.setEnabled(true);
		stock_minimo.setEnabled(true);
		ped_reposicion.setEnabled(true);

	}

	private void limpiarCampos() {
		codigo.setText("");
		cuit_proveedor.setText("");
		marca.setText("");
		descripcion.setText("");
		precio.setText("");
		stock.setText("");
		stock_minimo.setText("");
		ped_reposicion.setText("");
	}

	private int castearDatos(String valor, String mensaje, String mensajeError) {
		int intValor = 0;
		try {
			intValor = Integer.parseInt(valor);

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, mensaje, mensajeError, JOptionPane.ERROR_MESSAGE);
		}
		return intValor;
	}

}
