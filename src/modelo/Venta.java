package modelo;

import java.util.ArrayList;
import java.util.Vector;

public class Venta {

	private int numero;
	private static int ultimoNumero = 1;
	private double totalCompra;
	private Caja caja;
	private ArrayList<ItemVenta> itemsVenta;
	private String estado;

	public Venta(Caja caja) {
		this.numero = Venta.ultimoNumero++;
		this.caja = caja;
		this.itemsVenta = new ArrayList<>();
		this.estado = "abierta";
	}
	
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getNumeroDeVenta() {
		return numero;
	}

	public void setNumeroDeVenta(int numeroDeVenta) {
		this.numero = numeroDeVenta;
	}

	public double getTotalCompra() {
		return totalCompra;
	}

	public void setTotal(double total) {
		this.totalCompra = total;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public ArrayList<ItemVenta> getItemsVenta() {
		return itemsVenta;
	}

	public void setItemsVenta(ArrayList<ItemVenta> itemsVenta) {
		this.itemsVenta = itemsVenta;
	}

//agrega producto y cantidad a la venta y va sumando el precio al total de la compra.
	public void addItems(ItemVenta item) {
		this.itemsVenta.add(item);
		this.totalCompra += item.obtenerTotalCompra();

	}
	
	public boolean ventaAbierta() {
		if (this.estado.equalsIgnoreCase("abierta")) {
			return true;
		}
		return false;
	}

	public VentaView geVentaView() {
		return new VentaView(caja, totalCompra, itemsVenta);
	}

	public int getNroCaja() {
		return this.caja.getNumero();
	}

	public Vector<String> getVectorVenta() {
		Vector<String> datos = new Vector<String>();
		datos.add(this.caja.getFecha() + "");
		datos.add(this.caja.getNumero() + "");
		datos.add(this.numero + "");
		datos.add(this.caja.getNombreCajero());
		datos.add(this.totalCompra + "");

		return datos;
	}

	public boolean eresLaVenta(int nroDeVenta) {
		if (this.numero == nroDeVenta) {
			return true;
		}

		return false;
	}

	public void actualizarStock() {
		for (ItemVenta item : itemsVenta) {
			item.actualizaElStock();
		}

	}

}
