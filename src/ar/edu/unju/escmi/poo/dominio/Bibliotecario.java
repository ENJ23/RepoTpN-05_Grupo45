package ar.edu.unju.escmi.poo.dominio;

public class Bibliotecario extends Usuario {
	
private String legajo;

	
	
	public Bibliotecario(int id, String nombre, String apellido, String email, String legajo) {
        super(id, nombre, apellido, email);
        this.legajo = legajo;
    }

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	@Override
	public void mostrarDatos() {
		// TODO Auto-generated method stub
		System.out.println("Bibliotecario [legajo=" + legajo + ", id=" + id + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + email + "]");
	}
	

}
