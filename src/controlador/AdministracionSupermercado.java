package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import modelo.Caja;
import modelo.CajaView;
import modelo.ItemVenta;
import modelo.Producto;
import modelo.ProductoView;
import modelo.Proveedor;
import modelo.ProveedorView;
import modelo.Venta;
import modelo.VentaView;

public class AdministracionSupermercado {
	private ArrayList<Proveedor> proveedores;
	private ArrayList<Producto> productos;
	private ArrayList<Caja> cajas;
	private ArrayList<Venta> ventas;
	private static AdministracionSupermercado supermercado;

	private AdministracionSupermercado() {

		this.proveedores = new ArrayList<>();
		this.productos = new ArrayList<>();
		this.ventas = new ArrayList<>();
		this.cajas = new ArrayList<>();
		getCargaInicial();
	}

	private void getCargaInicial() {
		Proveedor prov = crearProveedor("456", "bello", "catamarca607", "1151756161", "celeste2325");
		Producto prod1 = crearProducto("456", "leche 0 lactosa", "leche", 2000.0, 60, 10, 1000, "456");
		Producto prod2 = crearProducto("458", "leche 0 grasa", "leche2", 3000, 5, 10, 500, "456");
		abrirCaja(LocalDate.now(), 456, "celeste", 20000);
	}

	// patron singleton me permite crear una sola instancia
	public static AdministracionSupermercado getSupermercado() {
		if (supermercado == null)
			supermercado = new AdministracionSupermercado();
		return supermercado;
	}

	public ArrayList<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(ArrayList<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public ArrayList<Venta> getTodasLasVentas() {
		return ventas;
	}

	public void setTodasLasVentas(ArrayList<Venta> todasLasVentas) {
		this.ventas = todasLasVentas;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	private Proveedor crearProveedor(String cuit, String razonSocial, String domicilio, String telefono, String email) {
		Proveedor proveedor = buscarProveedor(cuit);

		if (proveedor == null) {
			proveedor = new Proveedor(cuit, razonSocial, domicilio, telefono, email);
			proveedores.add(proveedor);
		}
		return proveedor;

	}

	public ProveedorView crearProveedorView(String cuit, String razonSocial, String domicilio, String telefono,
			String email) {
		Proveedor proveedor = crearProveedor(cuit, razonSocial, domicilio, telefono, email);
		if (proveedor != null) {
			return proveedor.getView();

		}
		return null;
	}

	private Proveedor buscarProveedor(String cuit) {
		for (Proveedor proveedor : proveedores) {
			if (proveedor.eresElProveedor(cuit)) {
				return proveedor;
			}
		}
		return null;
	}

	public ProveedorView buscarProveedorView(String cuit) {
		Proveedor proveedor = buscarProveedor(cuit);
		if (proveedor != null) {
			return proveedor.getView();
		}
		return null;
	}

	public void modificarProveedor(String cuit, String razonSocial, String telefono, String domicilio, String email) {
		Proveedor proveedor = buscarProveedor(cuit);
		if (proveedor != null) {
			proveedor.modificar(razonSocial, telefono, domicilio, email);
		}
	}

	public void eliminarProveedor(String nroCuit) {
		Proveedor proveedor = buscarProveedor(nroCuit);

		if (tieneProductosAsociados(nroCuit)) {
			eliminarProveedor_logica(nroCuit);
		} else {
			 eliminarProveedor_fisica(proveedor);
		}
	}
	
	public void eliminarProveedor_fisica(Proveedor proveedor) {
		this.proveedores.remove(proveedor);
	}

	public boolean tieneProductosAsociados(String nroCuit) {
		Proveedor proveedor = buscarProveedor(nroCuit);
		for (Producto producto : productos) {
			if (proveedor.eresElProveedor(producto.getProveedor().getCuit())) {
				return true;
			}
		}
		return false;
	}

	// inactiva el proveedor
	public void eliminarProveedor_logica(String cuit) {
		Proveedor proveedor = buscarProveedor(cuit);
		if (proveedor != null) {
			proveedor.setEstado("inactivo");
		}
	}

	private Producto crearProducto(String codigo, String descripcion, String marca, double precio, int stock,
			int stockMinimo, int pedidoDeReposicion, String cuitProveedor) {

		Producto producto = buscarProducto(codigo);

		if (producto == null) {
			Proveedor proveedor = buscarProveedor(cuitProveedor);
			if (proveedor != null) {
				producto = new Producto(codigo, descripcion, marca, precio, stock, stockMinimo, pedidoDeReposicion,
						proveedor);
				productos.add(producto);
			}

		}
		return producto;
	}

	public ProductoView crearProductoView(String codigo, String descripcion, String marca, double precio, int stock,
			int stockMinimo, int pedidoDeReposicion, String cuitProveedor) {

		Producto producto = crearProducto(codigo, descripcion, marca, precio, stock, stockMinimo, pedidoDeReposicion,
				cuitProveedor);

		if (producto != null) {
			return producto.getProductoView();

		}

		return null;
	}

	private Producto buscarProducto(String codigo) {
		for (Producto producto : productos) {
			if (producto.eresElProducto(codigo)) {
				return producto;
			}

		}
		return null;
	}

	public ProductoView buscarProductoView(String codigo) {
		Producto producto = buscarProducto(codigo);
		if (producto != null) {
			return producto.getProductoView();

		}
		return null;
	}

	public void modificarProducto(String codigo, String descripcion, String marca, double precio, int stock,
			int stockMinimo, int pedidoDeReposicion, String cuitProveedor) {
		Producto producto = buscarProducto(codigo);
		Proveedor proveedor = buscarProveedor(cuitProveedor);
		if (producto != null && proveedor != null) {
			producto.modificar(descripcion, marca, precio, stock, stockMinimo, pedidoDeReposicion, proveedor);

		}
	}

	public void eliminarProducto(String codigo) {
		Producto producto = buscarProducto(codigo);
		if (tieneVentasAsociadas(codigo)) {
			eliminarProducto_logica(codigo);

		} else {
			eliminarProducto_fisica(producto);
		}

	}

	public boolean tieneVentasAsociadas(String codigo) {
		Producto producto = buscarProducto(codigo);
		for (Venta venta : this.ventas) {
			for (ItemVenta item : venta.getItemsVenta()) {
				if (producto.eresElProducto(item.getProducto().getCodigo())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void eliminarProducto_fisica(Producto producto) {
		this.productos.remove(producto);
	}

	public void eliminarProducto_logica(String codigo) {
		Producto producto = buscarProducto(codigo);
		if (producto != null) {
			producto.setEstado("inactivo");
		}

	}

	// productos sin stock
	public ArrayList<Producto> getProductosSinStock() {
		ArrayList<Producto> productosSinStock = new ArrayList<>();
		for (Producto producto : productos) {
			if (producto.tienesStockMenorAlMinimo()) {
				productosSinStock.add(producto);
			}
		}

		return productosSinStock;
	}
	

	// abrir caja

	public void abrirCaja(LocalDate fecha, int numeroDeCaja, String nombreCajero, double saldoInicialEnEfectivo) {
		Caja arqueoDeCaja = buscarArqueoDeCaja(numeroDeCaja, fecha, nombreCajero);
		if (arqueoDeCaja == null) {
			Caja nuevaCaja = new Caja(numeroDeCaja, fecha, nombreCajero, saldoInicialEnEfectivo);
			addCaja(nuevaCaja);

		}

	}
	
	public void addCaja(Caja caja) {
		cajas.add(caja);
	}

	private Caja buscarArqueoDeCaja(int nroDeCaja, LocalDate fecha, String nombreDelCajero) {
		for (Caja caja : cajas) {
			if (caja.existeElArqueoDeCaja(nroDeCaja, fecha, nombreDelCajero)) {
				return caja;
			}
		}
		return null;

	}

	public CajaView buscarArqueoDeCajaView(int nroDeCaja, LocalDate fecha, String nombreDelCajero) {
		Caja caja = buscarArqueoDeCaja(nroDeCaja, fecha, nombreDelCajero);
		if (caja != null) {
			return caja.getView();
		}
		return null;
	}

	private Caja buscarCaja(int nroDeCaja) {
		for (Caja caja : cajas) {
			if (caja.eresLaCaja(nroDeCaja)) {
				return caja;
			}
		}
		return null;

	}

	public CajaView buscarCajaView(int nroDeCaja) {
		Caja caja = buscarCaja(nroDeCaja);
		if (caja != null) {
			return caja.getView();
		}

		return null;

	}
	
	
	
	public CajaView buscarCajaViewParaInformeAlCierre(int nroDeCaja) {
		Caja caja = buscarCajaParaInformeAlCierre(nroDeCaja);
		if (caja != null) {
			return caja.getView();
		}

		return null;

	}
	
	//solo se muestra el informe una vez que la caja esta cerrada
		public Caja buscarCajaParaInformeAlCierre(int nroDeCaja) {
			for (Caja caja : cajas) {
				if (caja.eresLaCajaParaInformeAlCierre(nroDeCaja)) {
					return caja;
				}
			}
			return null;

		}

	// fin abrir caja

	// verifica que exista un arqueo abierto. Si es true inicia la venta
	public int IniciarVenta(int nroDeCaja) {
		Caja caja = buscarCaja(nroDeCaja);
		if (caja != null) {
			Venta nuevaVenta = new Venta(caja);
			addVenta(nuevaVenta);
			return nuevaVenta.getNumeroDeVenta();
		}
		return 0;
	}
	
	public void addVenta(Venta venta) {
		this.ventas.add(venta);
	}

	public void procesarVenta(String codProducto, int cantidad, int nroDeVenta) {
		Venta venta = buscarVenta(nroDeVenta);
		if (venta != null) {
			Producto producto = buscarProducto(codProducto);
			if (producto != null) {
				ItemVenta item = new ItemVenta(producto, cantidad);
				venta.addItems(item);
			}
		}

	}

	private Venta buscarVenta(int nroDeVenta) {
		for (Venta venta : ventas) {
			if (venta.eresLaVenta(nroDeVenta)) {
				return venta;
			}
		}
		return null;

	}

	public VentaView buscarVentaView(int nroDeVenta) {
		Venta venta = buscarVenta(nroDeVenta);
		if (venta != null) {
			return venta.geVentaView();

		}
		return null;
	}

	// recibe el efectivo y resta al total de la compra de la venta
	public double cobrarVenta(int nroDeVenta, double efectivoRecibido) {
		Venta venta = buscarVenta(nroDeVenta);
		if (venta != null) {
			return efectivoRecibido - venta.getTotalCompra();
		}
		return 0.0;

	}

	// en caso de que el cliente quiera cancelar la compra
	public void cancelarVenta(int nroDeVenta) {
		Venta venta = buscarVenta(nroDeVenta);
		if (venta != null) {
			eliminarVenta(venta);
		}

	}
	
	private void eliminarVenta(Venta venta) {
		this.ventas.remove(venta);
	}

	// resta los productos vendidos al stock de productos del supermercado.
	public void confirmarVenta(int nroDeVenta) {
		Venta venta = buscarVenta(nroDeVenta);
		if (venta != null) {
			venta.actualizarStock();
		}

	}

	// agrega la nueva venta a las ventasRealizadas en la caja
	public void registrarVenta(int nroDeVenta, int nroDeCaja) {
		Venta venta = buscarVenta(nroDeVenta);
		if (venta != null) {
			Caja caja = venta.getCaja();
			caja.actualizarSaldoEnEfectivo();
			caja.addVentasRealizadas(venta);
			venta.setEstado("cerrada");
		}
	}

	// Fin registrar venta

	// cerrar caja
	// verifica si la caja esta abierta. Si es true cambia el estado a cerrada e
	// informa las ventas realizadas en la caja, saldo inicial y final.
	public void cerrarCaja(int nroDeCaja) {

		Caja arqueoDeCaja = buscarCaja(nroDeCaja);
		if (arqueoDeCaja != null && !ventaSinFinalizar()) {
			arqueoDeCaja.actualizarSaldoEnEfectivo();
			arqueoDeCaja.setEstado("Cerrada");
			
		}
		
		//arqueoDeCaja.setEstado("Cerrada");
		//arqueoDeCaja.actualizarSaldoEnEfectivo();
		/*ArrayList<Object> values = new ArrayList<>();
		if (arqueoDeCaja != null && !ventaSinFinalizar()) {
			values.add(informeVentasRealizadas(arqueoDeCaja));
			values.add(arqueoDeCaja.getSaldoInicialEnEfectivo());
			arqueoDeCaja.actualizarSaldoEnEfectivo();
			values.add(arqueoDeCaja.getSaldoFinalEnEfectivo());*/
			//arqueoDeCaja.setEstado("Cerrada");

		//}
		//return values;
	}
	
	//no permite cerrar una caja si hay una venta abierta
	public boolean ventaSinFinalizar() {
		for (Venta venta : ventas) {
			if (venta.ventaAbierta()) {
				return true;
			}
		}
		return false;
	}

	private ArrayList<Venta> informeVentasRealizadas(Caja caja) {
		return caja.getVentasRealizadas();

	}
	// fin cerrar caja

	// Consultar ventas

	public Vector<Vector<String>> consultarVentas(LocalDate fecha) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		for (Venta venta : ventas) {
			if (venta.getCaja().esLaMismaFecha(fecha)) {
				vector.add(venta.getVectorVenta());
			}
		}
		return vector;
	}

	public Vector<Vector<String>> consultarVentas(String nombreCajero) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		for (Venta venta : ventas) {
			if (venta.getCaja().esElMismoCajero(nombreCajero)) {
				vector.add(venta.getVectorVenta());
			}
		}
		return vector;
	}

	public Vector<Vector<String>> consultarVentas(int nroCaja) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		for (Venta venta : ventas) {
			if (venta.getCaja().esElMismoNumeroDeCaja(nroCaja)) {
				vector.add(venta.getVectorVenta());
			}
		}
		return vector;
	}

	// Fin consultar ventas

	// informe diario caja
	public Vector<Vector<String>> generarInformeDiarioDeCaja(LocalDate fecha, String nombreCajero, int nroCaja) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		for (Caja caja : cajas) {
			if (caja.esValidoParaInforme(fecha, nombreCajero, nroCaja)) {
				for (Venta venta : caja.getVentasRealizadas()) {
					vector.add(venta.getVectorVenta());
				}

			}
		}
		return vector;

	}

	public Vector<Vector<String>> vectorProductosSinStock() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		for (int i = 0; i < getProductosSinStock().size(); i++) {
			vector.add(getProductosSinStock().get(i).getValues());
		}
		return vector;

	}

	// devuelve a la vista el vector de ventas realizadas al cierre de caja. la caja
	// que recibe es de tipo view
	public Vector<Vector<String>> vectorVentasRalizadasAlCierreDeCajaView(CajaView caja) {
		return caja.vectorVentasRalizadasAlCierreDeCaja();

	}

}
// fin informe diario caja
