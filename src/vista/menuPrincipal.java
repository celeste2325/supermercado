package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class menuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuPrincipal frame = new menuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public menuPrincipal() {
		setTitle("Menú pricipal del Sistema de gestion del supermercado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		menuBar.setBackground(new Color(204, 204, 255));
		menuBar.setBounds(100, 100, 420, 31);
		contentPane.add(menuBar, BorderLayout.NORTH);

		JMenu mnProveedores = new JMenu("Administrar proveedores");
		menuBar.add(mnProveedores);

		JMenuItem mntmCrearProveedor = new JMenuItem("Crear proveedor");

		mntmCrearProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProveedor ventanaCrearProveedor = new CrearProveedor();
				ventanaCrearProveedor.setVisible(true);
			}
		});
		mnProveedores.add(mntmCrearProveedor);

		JMenuItem mntmModificarProveedor = new JMenuItem("Modificar proveedor");
		mntmModificarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarProveedor ventanaModificarProveedor = new ModificarProveedor();
				ventanaModificarProveedor.setVisible(true);
			}
		});
		mnProveedores.add(mntmModificarProveedor);

		JMenuItem mntmEliminarProveedor = new JMenuItem("Eliminar proveedor");
		mntmEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarProveedor vistaEliminarProveedor = new EliminarProveedor();
				vistaEliminarProveedor.setVisible(true);
			}
		});
		mnProveedores.add(mntmEliminarProveedor);

		JMenu mnNewMenu = new JMenu("Administrar productos");
		mnNewMenu.setBackground(new Color(51, 204, 51));
		menuBar.add(mnNewMenu);

		JMenuItem mntmCrear_1 = new JMenuItem("Crear producto");
		mntmCrear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProducto vistaCrearProducto = new CrearProducto();
				vistaCrearProducto.setVisible(true);
			}
		});
		mnNewMenu.add(mntmCrear_1);

		JMenuItem mntmModificar_1 = new JMenuItem("Modificar producto");
		mntmModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarProducto vistaModificarProducto = new ModificarProducto();
				vistaModificarProducto.setVisible(true);
			}
		});
		mnNewMenu.add(mntmModificar_1);

		JMenuItem mntmEliminarProducto = new JMenuItem("Eliminar producto");
		mntmEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarProducto vistaEliminarProducto = new EliminarProducto();
				vistaEliminarProducto.setVisible(true);
			}
		});
		mnNewMenu.add(mntmEliminarProducto);

		JMenuItem mntmProductosSinStock = new JMenuItem("Consultar productos sin stock");
		mntmProductosSinStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductosSinStock vistaProductosSinStock = new ProductosSinStock();
				vistaProductosSinStock.pack();
				vistaProductosSinStock.setVisible(true);

			}
		});
		mnNewMenu.add(mntmProductosSinStock);

		JMenu mnAbrirCaja = new JMenu("Caja");
		menuBar.add(mnAbrirCaja);

		JMenuItem mntmAbrirCcaja = new JMenuItem("Abrir caja");
		mntmAbrirCcaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirCaja vistaAbrirCaja = new AbrirCaja();
				vistaAbrirCaja.setVisible(true);

			}
		});
		mnAbrirCaja.add(mntmAbrirCcaja);

		JMenuItem mntmCerrarCaja = new JMenuItem("Cerrar caja");
		mntmCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CerrarCaja vistaCerrarCaja = new CerrarCaja();
				vistaCerrarCaja.setVisible(true);
			}
		});
		mnAbrirCaja.add(mntmCerrarCaja);

		JMenuItem mntmInformeDeCaja = new JMenuItem("Informe diario de caja");
		mntmInformeDeCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaInformeDiarioDeCaja tableInformeCaja = new TablaInformeDiarioDeCaja();
				tableInformeCaja.setVisible(true);
			}
		});
		mnAbrirCaja.add(mntmInformeDeCaja);

		JMenu mnRegistrarVenta = new JMenu("Venta");
		menuBar.add(mnRegistrarVenta);

		JMenuItem mntmIniciarVenta = new JMenuItem("Iniciar venta");
		mntmIniciarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarVenta vistaIniciarVenta = new IniciarVenta();
				vistaIniciarVenta.setVisible(true);
			}
		});
		mnRegistrarVenta.add(mntmIniciarVenta);

		JMenuItem mntmConsultarVentas = new JMenuItem("Consultar ventas");
		mntmConsultarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarVenta vistaConsultarVenta = new ConsultarVenta();
				vistaConsultarVenta.setVisible(true);
			}
		});
		mnRegistrarVenta.add(mntmConsultarVentas);
	}

}
