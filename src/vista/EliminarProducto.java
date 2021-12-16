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

public class EliminarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField codigoProducto;

	/**
	 * Create the frame.
	 */
	public EliminarProducto() {
		setTitle("Eliminar Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 366, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnBuscarProducto = new JButton("Buscar Producto");
		JLabel lblNewLabel = new JLabel("C\u00F3digo del producto:");
		lblNewLabel.setBounds(10, 14, 170, 14);
		contentPane.add(lblNewLabel);

		JButton btnEliminarProducto = new JButton("Eliminar Producto");
		JButton btnEliminarOtroProducto = new JButton("Eliminar otro producto");
		btnEliminarOtroProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// resetear formulario
				codigoProducto.setText("");
				btnEliminarOtroProducto.setVisible(false);
				btnEliminarProducto.setVisible(true);
				btnEliminarProducto.setEnabled(false);
				btnBuscarProducto.setEnabled(true);
			}
		});

		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// intercambio de botones
				btnEliminarOtroProducto.setVisible(true);
				btnEliminarProducto.setVisible(false);

				AdministracionSupermercado.getSupermercado().eliminarProducto(codigoProducto.getText());
				JOptionPane.showMessageDialog(null, "El producto se elimino exitosamente", "Eliminar producto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnEliminarProducto.setBounds(10, 80, 328, 23);
		btnEliminarProducto.setEnabled(false);
		contentPane.add(btnEliminarProducto);

		codigoProducto = new JTextField();
		codigoProducto.setBounds(168, 11, 170, 20);
		contentPane.add(codigoProducto);
		codigoProducto.setColumns(10);

		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoView producto = AdministracionSupermercado.getSupermercado()
						.buscarProductoView(codigoProducto.getText());

				if (producto != null) {
					btnEliminarProducto.setEnabled(true);
					btnBuscarProducto.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "El producto no existe", "Producto inexistente",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscarProducto.setBounds(10, 48, 328, 23);
		contentPane.add(btnBuscarProducto);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(10, 114, 328, 23);
		contentPane.add(btnSalir);

		btnEliminarOtroProducto.setBounds(10, 80, 328, 23);
		btnEliminarOtroProducto.setVisible(false);
		contentPane.add(btnEliminarOtroProducto);
	}
}
