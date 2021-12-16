package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Caja {
	private LocalDate fecha;
	private int numero;
	private String nombreCajero;
	private double saldoInicialEnEfectivo;
	private double saldoFinalEnEfectivo;
	private String estado;
	private ArrayList<Venta> ventasRealizadas;

	public Caja(int numero, LocalDate fecha, String nombreCajero, double saldoInicialEnEfectivo) {

		this.fecha = fecha;
		this.numero = numero;
		this.nombreCajero = nombreCajero;
		this.saldoInicialEnEfectivo = saldoInicialEnEfectivo;
		this.estado = "Abierta";
		this.ventasRealizadas = new ArrayList<>();

	}

	public ArrayList<Venta> getVentasRealizadas() {
		return ventasRealizadas;
	}

	public void setVentasRealizadas(ArrayList<Venta> ventasRealizadas) {
		this.ventasRealizadas = ventasRealizadas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombreCajero() {
		return nombreCajero;
	}

	public void setNombreCajero(String nombreCajero) {
		this.nombreCajero = nombreCajero;
	}

	public double getSaldoInicialEnEfectivo() {
		return saldoInicialEnEfectivo;
	}

	public void setSaldoInicialEnEfectivo(double saldoInicialEnEfectivo) {
		this.saldoInicialEnEfectivo = saldoInicialEnEfectivo;
	}

	public double getSaldoFinalEnEfectivo() {
		return saldoFinalEnEfectivo;
	}

	public void setSaldoFinalEnEfectivo(double saldoFinalEnEfectivo) {
		this.saldoFinalEnEfectivo = saldoFinalEnEfectivo;
	}

	public boolean existeElArqueoDeCaja(int nroDeCaja, LocalDate fecha, String nombreDelCajero) {
		if (this.numero == nroDeCaja && this.fecha.isEqual(fecha)
				&& this.nombreCajero.equalsIgnoreCase(nombreDelCajero)) {
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
	
	public boolean eresLaCajaParaInformeAlCierre(int nroDeCaja) {
		if (this.numero == nroDeCaja && this.getEstado().equalsIgnoreCase("cerrada")) {
			return true;
		}
		return false;
	}
	
	public boolean esLaMismaFecha(LocalDate fecha) {
		if (this.fecha.isEqual(fecha)) {
			return true;
		} 
		return false;
		
	}
	
	public boolean esElMismoCajero(String nombreDelCajero) {
		if (this.nombreCajero.equalsIgnoreCase(nombreDelCajero)) {
			return true;
		}
		return false;
	}
	
	public boolean esElMismoNumeroDeCaja(int nroDeCaja) {
		if (this.numero == nroDeCaja) {
			return true;
		}
		return false;
	}

	public void addVentasRealizadas(Venta venta) {
		this.ventasRealizadas.add(venta);
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

	public boolean esValidoParaInforme(LocalDate fecha, String nombreCajero, int nroCaja) {

		return this.fecha != null && this.fecha.equals(fecha) && this.nombreCajero.equalsIgnoreCase(nombreCajero)
				&& this.numero == nroCaja;
	}

	public CajaView getView() {
		return new CajaView(numero, fecha, nombreCajero, saldoInicialEnEfectivo, ventasRealizadas);
	}

}
