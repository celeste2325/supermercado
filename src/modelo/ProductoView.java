package modelo;

import java.util.Vector;

public class ProductoView {
	private String codigo;
	private String descripcion;
	private String marca;
	private double precio;
	private int stock;
	private int stockMinimo;
	private int pedidoDeReposicion;
	private String estado;
	private Proveedor proveedor;

	public ProductoView(String codigo, String descripcion, String marca, double precio, int stock, int stockMinimo,
			int pedidoDeReposicion, Proveedor proveedor) {

		this.codigo = codigo;
		this.descripcion = descripcion;
		this.marca = marca;
		this.precio = precio;
		this.stock = stock;
		this.stockMinimo = stockMinimo;
		this.pedidoDeReposicion = pedidoDeReposicion;
		this.proveedor = proveedor;
		this.estado = "activo";
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getPedidoDeReposicion() {
		return pedidoDeReposicion;
	}

	public void setPedidoDeReposicion(int pedidoDeReposicion) {
		this.pedidoDeReposicion = pedidoDeReposicion;
	}


	public Vector<String> getValues() {
		Vector<String> datos = new Vector<String>();
		datos.add(this.codigo.toString());

		return datos;
	}

}
