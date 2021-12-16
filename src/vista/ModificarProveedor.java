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

public class ModificarProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField razonSocial;
	private JTextField domicilio;
	private JTextField telefono;
	private JTextField email;
	private JButton btnModificarProveedor;
	private JButton btnConsultarCuit;
	private JLabel lblConsultarCuit;
	private JTextField cuit;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public ModificarProveedor() {
		setTitle("Modificar proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblModificarRazonSocialProveedor = new JLabel("Razon social:");
		lblModificarRazonSocialProveedor.setBounds(10, 100, 78, 14);
		contentPane.add(lblModificarRazonSocialProveedor);

		JLabel lblModificarDomicilioProveedor = new JLabel("Domicilio:");
		lblModificarDomicilioProveedor.setBounds(10, 157, 78, 14);
		contentPane.add(lblModificarDomicilioProveedor);

		JLabel lblModificarTelefonoProveedor = new JLabel("Tel\u00E9fono");
		lblModificarTelefonoProveedor.setBounds(10, 214, 78, 14);
		contentPane.add(lblModificarTelefonoProveedor);

		JLabel lblModificarEmailProveedor = new JLabel("Email:");
		lblModificarEmailProveedor.setBounds(10, 271, 78, 14);
		contentPane.add(lblModificarEmailProveedor);

		razonSocial = new JTextField();
		razonSocial.setBounds(108, 96, 170, 20);
		contentPane.add(razonSocial);
		razonSocial.setColumns(10);

		domicilio = new JTextField();
		domicilio.setBounds(108, 154, 249, 20);
		contentPane.add(domicilio);
		domicilio.setColumns(10);

		telefono = new JTextField();
		telefono.setBounds(108, 212, 131, 20);
		contentPane.add(telefono);
		telefono.setColumns(10);

		email = new JTextField();
		email.setBounds(108, 270, 180, 20);
		contentPane.add(email);
		email.setColumns(10);

		lblConsultarCuit = new JLabel("Cuit:");
		lblConsultarCuit.setBounds(10, 43, 48, 14);
		contentPane.add(lblConsultarCuit);

		cuit = new JTextField();
		cuit.setBounds(108, 38, 96, 20);
		contentPane.add(cuit);
		cuit.setColumns(10);

		btnModificarProveedor = new JButton("Modificar proveedor");
		btnModificarProveedor.setBounds(62, 313, 163, 23);
		btnModificarProveedor.setEnabled(false);
		contentPane.add(btnModificarProveedor);

		btnConsultarCuit = new JButton("Consultar cuit");
		btnConsultarCuit.setBounds(270, 39, 163, 23);
		contentPane.add(btnConsultarCuit);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(270, 313, 163, 23);
		contentPane.add(btnCancelar);

		// deshabilita campos hasta que se valide el cuit
		deshabilitarCampos();

		btnConsultarCuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// si ingreso un valor numerico
				if (!cuit.getText().isEmpty()) {
					ProveedorView proveedor = AdministracionSupermercado.getSupermercado()
							.buscarProveedorView(cuit.getText());
					if (proveedor != null) {
						
						// muestra datos a modificar
						razonSocial.setText(proveedor.getRazonSocial());
						telefono.setText(proveedor.getTelefono());
						domicilio.setText(proveedor.getDomicilio());
						email.setText(proveedor.getEmail());

						// deshabilita boton y campo al validar cuit
						btnConsultarCuit.setEnabled(false);
						cuit.setEnabled(false);
						btnModificarProveedor.setEnabled(true);

						// habilita campos al validar cuit
						habilitarCampos();

					} else {
						JOptionPane.showMessageDialog(null, "El proveedor no existe o se encuentra inactivo",
								"error al verificar cuit", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El campo cuit es obligatorio");
				}

			}
		});

		btnModificarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!cuit.getText().isEmpty()) {

					ProveedorView proveedor = AdministracionSupermercado.getSupermercado()
							.buscarProveedorView(cuit.getText());

					if (proveedor != null) {
						AdministracionSupermercado.getSupermercado().modificarProveedor(cuit.getText(),
								razonSocial.getText(), telefono.getText(), domicilio.getText(), email.getText());

						JOptionPane.showMessageDialog(null, "Datos modificados", "Proveedor modificado",
								JOptionPane.INFORMATION_MESSAGE);

						// deshabilita campos para validar nuevo cuit
						deshabilitarCampos();

						// limpia campos para agregar datos de nuevo proveedor
						limpiarCampos();

						// resetea formulario
						btnConsultarCuit.setEnabled(true);
						btnModificarProveedor.setEnabled(false);
						cuit.setEnabled(true);
					} else {
						JOptionPane.showConfirmDialog(null, "No existe proveedor con ese código",
								"Error al modificar el proveedor", JOptionPane.ERROR);
					}

				} else {
					JOptionPane.showMessageDialog(null, "El campo cuit es obligatorio");
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
