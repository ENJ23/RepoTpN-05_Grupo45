package ar.edu.unju.escmi.poo.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.poo.dominio.Alumno;
import ar.edu.unju.escmi.poo.dominio.Bibliotecario;
import ar.edu.unju.escmi.poo.dominio.Usuario;
import ar.edu.unju.escmi.poo.exceptions.UsuarioNoRegistradoException;

public class CollectionUsuario {
	
	public static List<Usuario> usuarios = new ArrayList<>();
	
	
	static Scanner scanner = new Scanner(System.in);
	static int idIncremental = 4;
	
	public static void precargarUsuarios() {
		usuarios.add(new Bibliotecario(1, "Juan", "Pérez", "juan.perez@example.com","legajo1"));
		usuarios.add(new Alumno(2, "María", "González", "maria.gonzalez@example.com","3RO Informática","4000"));
		usuarios.add(new Alumno(3, "Carlos", "López", "carlos.lopez@example.com","2DO Informatica", "2000"));
	}
	
	public static int sumarId() {
    	return idIncremental++;
    }
	
	public static void registrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public static Alumno buscarUsuario(String lu) throws UsuarioNoRegistradoException {
		for (Usuario usuario : usuarios) {
			if(Alumno.class.isInstance(usuario)) {
			Alumno alumnoBuscado = (Alumno) usuario;
			if (alumnoBuscado.getLu().equalsIgnoreCase(lu)) {
				return alumnoBuscado;
			}
			}
				
		}			
		throw new UsuarioNoRegistradoException("El usuario no está registrado");
	}
	
	public static Bibliotecario buscarBibliotecario(String legajo) throws UsuarioNoRegistradoException {
		for (Usuario bib : usuarios) {
			if(Bibliotecario.class.isInstance(bib)) {
			Bibliotecario bibBuscado = (Bibliotecario) bib;
			if (bibBuscado.getLegajo().equals(legajo)) {
				return bibBuscado;
				}
			}
				
		}			
		throw new UsuarioNoRegistradoException("El usuario no está registrado");
	}
	
}