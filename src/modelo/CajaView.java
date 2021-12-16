package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class CajaView {
	private LocalDate fecha;
	private int numero;
	private String nombreCajero;
	private double saldoInicialEnEfectivo;
	private double saldoFinalEnEfectivo;
	private String estado;
	private ArrayList<Venta> ventasRealizadas;

	public CajaView(int numero, LocalDate fecha, String nombreCajero, double saldoInicialEnEfectivo,
			ArrayList<Venta> ventasRealizadas) {

		this.fecha = fecha;
		this.numero = numero;
		this.nombreCajero = nombreCajero;
		this.saldoInicialEnEfectivo = saldoInicialEnEfectivo;
		this.estado = "Abierta";
		this.ventasRealizadas = ventasRealizadas;

	}

	public LocalDate getFecha() {
		return fecha;
	}

	public int getNumero() {
		return numero;
	}

	public String getNombreCajero() {
		return nombreCajero;
	}

	public double getSaldoInicialEnEfectivo() {
		return saldoInicialEnEfectivo;
	}

	public double getSaldoFinalEnEfectivo() {
		return saldoFinalEnEfectivo;
	}

	public String getEstado() {
		return estado;
	}

	public ArrayList<Venta> getVentasRealizadas() {
		return ventasRealizadas;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setNombreCajero(String nombreCajero) {
		this.nombreCajero = nombreCajero;
	}

	public void setSaldoInicialEnEfectivo(double saldoInicialEnEfectivo) {
		this.saldoInicialEnEfectivo = saldoInicialEnEfectivo;
	}

	public void setSaldoFinalEnEfectivo(double saldoFinalEnEfectivo) {
		this.saldoFinalEnEfectivo = saldoFinalEnEfectivo;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setVentasRealizadas(ArrayList<Venta> ventasRealizadas) {
		this.ventasRealizadas = ventasRealizadas;
	}

	public boolean existeElArqueoDeCaja(int nroDeCaja, LocalDate fecha, String nombreDelCajero) {
		if ((this.numero == nroDeCaja && this.fecha.equals(fecha)) && this.nombreCajero == nombreDelCajero) {
			return true;
		}
		return false;
	}

	public boolean eresLaCaja(int nroDeCaja) {
		if (this.numero == nroDeCaja && this.getEstado().equalsIgnoreCase("Abierta")) {
			return true;
		}
		return false;
	}

	private double totalVentasRealizadas() {
		double totalVentas = 0.0;
		for (Venta venta : ventasRealizadas) {
			totalVentas += venta.getTotalCompra();
		}
		return totalVentas;
	}

	public void actualizarSaldoEnEfectivo() {
		this.setSaldoFinalEnEfectivo(this.saldoInicialEnEfectivo + totalVentasRealizadas());

	}

	public Vector<Vector<String>> vectorVentasRalizadasAlCierreDeCaja() {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		for (Venta venta : ventasRealizadas) {
			vector.add(venta.getVectorVenta());

		}
		return vector;

	}


}
