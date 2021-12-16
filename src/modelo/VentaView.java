package modelo;

import java.util.ArrayList;

public class VentaView {
	private int numero;
	private static int ultimoNumero = 1;
	private double totalCompra;
	private Caja caja;
	private ArrayList<ItemVenta> itemsVenta;

	public VentaView(Caja caja, double totalCompra, ArrayList<ItemVenta> itemsVenta) {
		this.numero = VentaView.ultimoNumero++;
		this.caja = caja;
		this.totalCompra = totalCompra;
		this.itemsVenta = itemsVenta;
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

	public int getNroCaja() {
		return this.caja.getNumero();
	}
}
