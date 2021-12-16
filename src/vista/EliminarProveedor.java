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

public class EliminarProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField cuitProveedor;

	/**
	 * Create the frame.
	 */
	public EliminarProveedor() {
		setTitle("Eliminar Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 360, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Cuit proveedor:");
		lblNewLabel.setBounds(10, 11, 165, 14);
		contentPane.add(lblNewLabel);

		cuitProveedor = new JTextField();
		cuitProveedor.setBounds(132, 8, 199, 20);
		contentPane.add(cuitProveedor);
		cuitProveedor.setColumns(10);

		JButton btnBuscarProveedor = new JButton("Buscar Proveedor");
		JButton btnEliminarProveedor = new JButton("Eliminar Proveedor");
		JButton btnEiliminarOtroProveedor = new JButton("Eliminar otro proveedor");
		
		btnEiliminarOtroProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// resetear formulario
				cuitProveedor.setText("");
				btnEiliminarOtroProveedor.setVisible(false);
				btnEliminarProveedor.setVisible(true);
				btnEliminarProveedor.setEnabled(false);
				btnBuscarProveedor.setEnabled(true);
			}
		});
		btnEiliminarOtroProveedor.setBounds(10, 94, 321, 23);
		btnEiliminarOtroProveedor.setVisible(false);
		contentPane.add(btnEiliminarOtroProveedor);

		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// intercambio de botones
				btnEliminarProveedor.setVisible(false);
				btnEiliminarOtroProveedor.setVisible(true);

				AdministracionSupermercado.getSupermercado().eliminarProveedor(cuitProveedor.getText());
				JOptionPane.showMessageDialog(null, "El proveedor se elimino exitosamente", "Eliminar proveedor",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnEliminarProveedor.setBounds(10, 94, 321, 23);
		btnEliminarProveedor.setEnabled(false);
		contentPane.add(btnEliminarProveedor);

		btnBuscarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ProveedorView proveedor = AdministracionSupermercado.getSupermercado()
						.buscarProveedorView(cuitProveedor.getText());
				if (proveedor != null) {
					btnEliminarProveedor.setEnabled(true);
					btnBuscarProveedor.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "El proveedor no existe", "proveedor inexistente",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnBuscarProveedor.setBounds(10, 47, 321, 23);
		contentPane.add(btnBuscarProveedor);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(10, 137, 321, 23);
		contentPane.add(btnSalir);

	}

}
