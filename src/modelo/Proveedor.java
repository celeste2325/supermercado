package modelo;

public class Proveedor {

	private String cuit;
	private String razonSocial;
	private String domicilio;
	private String telefono;
	private String email;
	private String estado;

	public Proveedor(String cuit, String razonSocial, String domicilio, String telefono, String email) {

		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
		this.estado = "activo";
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void modificar(String razonSocial, String telefono, String domicilio, String email) {
		this.razonSocial = razonSocial;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.email = email;

	}

	public boolean eresElProveedor(String cuit) {
		if (this.cuit.equalsIgnoreCase(cuit) && estasActivo()) {
			return true;
		}
		return false;

	}

	public boolean estasActivo() {
		if (this.estado.equalsIgnoreCase("activo")) {
			return true;
		}
		return false;
	}

	public ProveedorView getView() {
		return new ProveedorView(cuit, razonSocial, domicilio, telefono, email);
	}

}
