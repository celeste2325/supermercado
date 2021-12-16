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

public class CrearProducto extends JFrame {

	private JPanel contentPane;
	private JTextField codigo;
	private JTextField cuit_proveedor;
	private JTextField marca;
	private JTextField stock;
	private JTextField stockMinimo;
	private JTextField ped_reposicion;
	private JTextField descripcion;
	private JTextField precio;

	/**
	 * Create the frame.
	 */
	public CrearProducto() {
		setTitle("Crear Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(10, 19, 142, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Marca:");
		lblNewLabel_1.setBounds(10, 85, 142, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(10, 151, 142, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Stock:");
		lblNewLabel_3.setBounds(10, 184, 142, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Stock minimo");
		lblNewLabel_4.setBounds(10, 217, 142, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Pedido de reposicion:");
		lblNewLabel_5.setBounds(10, 250, 142, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Descripcion:");
		lblNewLabel_6.setBounds(10, 118, 142, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Cuit proveedor:");
		lblNewLabel_7.setBounds(10, 52, 142, 14);
		contentPane.add(lblNewLabel_7);

		codigo = new JTextField();
		codigo.setBounds(142, 15, 142, 20);
		contentPane.add(codigo);
		codigo.setColumns(10);

		cuit_proveedor = new JTextField();
		cuit_proveedor.setBounds(142, 49, 142, 20);
		contentPane.add(cuit_proveedor);
		cuit_proveedor.setColumns(10);

		marca = new JTextField();
		marca.setBounds(142, 83, 142, 20);
		contentPane.add(marca);
		marca.setColumns(10);

		stock = new JTextField();
		stock.setBounds(142, 185, 60, 20);
		contentPane.add(stock);
		stock.setColumns(10);

		stockMinimo = new JTextField();
		stockMinimo.setBounds(142, 219, 60, 20);
		contentPane.add(stockMinimo);
		stockMinimo.setColumns(10);

		ped_reposicion = new JTextField();
		ped_reposicion.setBounds(142, 253, 60, 20);
		contentPane.add(ped_reposicion);
		ped_reposicion.setColumns(10);

		descripcion = new JTextField();
		descripcion.setBounds(142, 117, 171, 20);
		contentPane.add(descripcion);
		descripcion.setColumns(10);

		precio = new JTextField();
		precio.setBounds(142, 151, 96, 20);
		contentPane.add(precio);
		precio.setColumns(10);

		JButton btnVerifCodigo = new JButton("Verificar codigo");
		btnVerifCodigo.setBounds(326, 15, 160, 23);
		contentPane.add(btnVerifCodigo);

		JButton btnConsultarCuit = new JButton("Consultar cuit");
		btnConsultarCuit.setBounds(326, 48, 160, 23);
		contentPane.add(btnConsultarCuit);

		JButton btnCrearProducto = new JButton("Crear Producto");
		btnCrearProducto.setBounds(142, 291, 160, 23);
		contentPane.add(btnCrearProducto);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(326, 291, 160, 23);
		contentPane.add(btnSalir);

		// deshabilitar botones
		btnConsultarCuit.setEnabled(false);
		btnCrearProducto.setEnabled(false);

		deshabilitarCampos();

		btnVerifCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!codigo.getText().isEmpty()) {
					ProductoView producto = AdministracionSupermercado.getSupermercado()
							.buscarProductoView(codigo.getText());
					if (producto == null) {

						// deshabilita campo para que no puedo editar el codigo
						codigo.setEnabled(false);

						// deshabilita boton al validar codigo del producto
						btnVerifCodigo.setEnabled(false);

						// habilitr campos y boton despues de validar codigo
						btnConsultarCuit.setEnabled(true);
						cuit_proveedor.setEnabled(true);

					} else {
						JOptionPane.showMessageDialog(null, "Ya existe un producto con mismo código",
								"error al ingresar codigo", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El campo código es obligatorio", "error al verificar codigo",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		btnConsultarCuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!cuit_proveedor.getText().isEmpty()) {

					ProveedorView proveedor = AdministracionSupermercado.getSupermercado()
							.buscarProveedorView(cuit_proveedor.getText());

					if (proveedor != null) {

						// habilita campos y boton despues de validar cuit
						habilitarCampos();
						btnCrearProducto.setEnabled(true);

						// deshabilita boton al validar cuit
						btnConsultarCuit.setEnabled(false);

						// deshabilita campo para que no puedo editar el cuit
						cuit_proveedor.setEnabled(false);

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

		btnCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cantPrecio = 0.0;
				int cantStock = castearDatos(stock.getText(),
						"El stock es un campo obligatorio, debe ser numérico y entero", "error al ingresar precio");
				int cantStockMinimo = castearDatos(stockMinimo.getText(),
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

				if (cantPrecio != 0.0 && cantStock != 0 && cantStockMinimo != 0 && cantPedidoDeReposicion != 0) {
					if (marca.getText().isEmpty() || descripcion.getText().isEmpty()) {

						JOptionPane.showMessageDialog(null, "Todos los campos son requeridos",
								"error al crear el producto", JOptionPane.ERROR_MESSAGE);

					} else {
						// crea el producto
						AdministracionSupermercado.getSupermercado().crearProductoView(codigo.getText(),
								descripcion.getText(), marca.getText(), cantPrecio, cantStock, cantStockMinimo,
								cantPedidoDeReposicion, cuit_proveedor.getText());

						JOptionPane.showMessageDialog(null, "El producto fue creado", "Crear producto",
								JOptionPane.INFORMATION_MESSAGE);

						// habilita boton para validar nuevo código
						btnVerifCodigo.setEnabled(true);

						// habilita campo para que se ingrese nuevo cuit
						codigo.setEnabled(true);

						// deshabilita campos para validar nuevo cuit
						deshabilitarCampos();
						btnCrearProducto.setEnabled(false);
						limpiarCampos();

					}
				}

			}
		});

		btnSalir.addActionListener(new ActionListener() {
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
		stockMinimo.setEnabled(false);
		ped_reposicion.setEnabled(false);
	}

	private void habilitarCampos() {
		cuit_proveedor.setEnabled(true);
		marca.setEnabled(true);
		descripcion.setEnabled(true);
		precio.setEnabled(true);
		stock.setEnabled(true);
		stockMinimo.setEnabled(true);
		ped_reposicion.setEnabled(true);

	}

	private void limpiarCampos() {
		codigo.setText("");
		cuit_proveedor.setText("");
		marca.setText("");
		descripcion.setText("");
		precio.setText("");
		stock.setText("");
		stockMinimo.setText("");
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
