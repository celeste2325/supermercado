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
import modelo.ProveedorView;

public class CrearProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField cuit;
	private JTextField razonSocial;
	private JTextField domicilio;
	private JTextField telefono;
	private JTextField email;

	/**
	 * Create the frame.
	 */
	public CrearProveedor() {
		setTitle("Crear proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCuitProveedor = new JLabel("Cuit:");
		lblCuitProveedor.setBounds(10, 31, 66, 14);
		contentPane.add(lblCuitProveedor);

		JLabel lblRazonSocialProveedor = new JLabel("Razon Social:");
		lblRazonSocialProveedor.setBounds(10, 76, 104, 14);
		contentPane.add(lblRazonSocialProveedor);

		JLabel lblDomicilioProveedor = new JLabel("Domicilio:");
		lblDomicilioProveedor.setBounds(10, 121, 66, 14);
		contentPane.add(lblDomicilioProveedor);

		JLabel lblTelefonoProveedor = new JLabel("Telefono: ");
		lblTelefonoProveedor.setBounds(10, 166, 66, 14);
		contentPane.add(lblTelefonoProveedor);

		JLabel lblEmailProveedor = new JLabel("Email:");
		lblEmailProveedor.setBounds(10, 211, 66, 14);
		contentPane.add(lblEmailProveedor);

		cuit = new JTextField();
		cuit.setBounds(124, 26, 127, 20);
		contentPane.add(cuit);
		cuit.setColumns(10);

		razonSocial = new JTextField();
		razonSocial.setBounds(124, 72, 180, 20);
		contentPane.add(razonSocial);
		razonSocial.setColumns(10);

		domicilio = new JTextField();
		domicilio.setBounds(124, 118, 263, 20);
		contentPane.add(domicilio);
		domicilio.setColumns(10);

		telefono = new JTextField();
		telefono.setBounds(124, 164, 127, 20);
		contentPane.add(telefono);
		telefono.setColumns(10);

		email = new JTextField();
		email.setBounds(124, 210, 180, 20);
		contentPane.add(email);
		email.setColumns(10);

		// deshabilitar campos hasta que se valide el cuit
		deshabilitarCampos();

		JButton btnConsultarCuit = new JButton("Consultar cuit");
		btnConsultarCuit.setBounds(274, 25, 134, 23);
		contentPane.add(btnConsultarCuit);

		JButton btnCrearProveedor = new JButton("Crear proveedor");
		btnCrearProveedor.setBounds(70, 264, 134, 23);
		btnCrearProveedor.setEnabled(false);
		contentPane.add(btnCrearProveedor);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(274, 264, 134, 23);
		contentPane.add(btnCancelar);

		btnConsultarCuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!cuit.getText().isEmpty()) {
					ProveedorView proveedor = AdministracionSupermercado.getSupermercado()
							.buscarProveedorView(cuit.getText());
					if (proveedor == null) {
						
						// deshabilita boton y campo al validar cuit
						btnConsultarCuit.setEnabled(false);
						cuit.setEnabled(false);

						// habilita campos y boton despues de validar cuit
						habilitarCampos();
						btnCrearProveedor.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Ya existe un proveedor con mismo cuit",
								"error al verificar cuit", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El campo cuit es obligatorio");
				}

			}

		});
		btnCrearProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// todos los campos son requeridos
				if (razonSocial.getText().isEmpty() || domicilio.getText().isEmpty() || telefono.getText().isEmpty()
						|| email.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Todos los campos son requeridos",
							"error al crear el proveedor", JOptionPane.ERROR_MESSAGE);

				} else {
					// crea el proveedor
					AdministracionSupermercado.getSupermercado().crearProveedorView(cuit.getText(),
							razonSocial.getText(), domicilio.getText(), telefono.getText(), email.getText());

					JOptionPane.showMessageDialog(null, "El proveedor fue creado", "Crear proveedor",
							JOptionPane.INFORMATION_MESSAGE);

					// resetea formulario
					btnConsultarCuit.setEnabled(true);
					cuit.setEnabled(true);
					deshabilitarCampos();
					limpiarCampos();
				}

			}

		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cierra la ventana
				dispose();
			}
		});
	}

	private void deshabilitarCampos() {
		razonSocial.setEnabled(false);
		domicilio.setEnabled(false);
		telefono.setEnabled(false);
		email.setEnabled(false);
	}

	private void habilitarCampos() {
		razonSocial.setEnabled(true);
		domicilio.setEnabled(true);
		telefono.setEnabled(true);
		email.setEnabled(true);
	}

	private void limpiarCampos() {
		cuit.setText("");
		razonSocial.setText("");
		domicilio.setText("");
		telefono.setText("");
		email.setText("");
	}
}
