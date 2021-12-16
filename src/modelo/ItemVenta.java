package modelo;

import java.util.Vector;

public class ItemVenta {
	private Producto producto;
	private int cantidad;

	public ItemVenta(Producto producto, int cantidad) {

		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean validarProducto(String codigo) {
		if (producto.eresElProducto(codigo)) {
			return true;
		}
		return false;
	}

	// calcula el total por producto
	public double obtenerTotalCompra() {
		return producto.getPrecio() * cantidad;
	}

	public Vector<String> getProductos() {
		Vector<String> datos = new Vector<String>();
		datos.add(this.producto.getCodigo() + "");
		datos.add(this.producto.getDescripcion() + "");
		datos.add(this.producto.getMarca() + "");
		datos.add(this.producto.getPrecio() + "");
		datos.add(this.getCantidad() + "");

		return datos;
	}

	public void actualizaElStock() {
		producto.setStock(producto.getStock() - getCantidad());

	}

}
