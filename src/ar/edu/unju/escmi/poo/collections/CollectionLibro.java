package ar.edu.unju.escmi.poo.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.poo.dominio.Libro;
import ar.edu.unju.escmi.poo.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.poo.exceptions.LibroNoEncontradoException;

public class CollectionLibro {
	
	public static List<Libro> libros = new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);
	static int idIncremental = 6;
	
	public static void precargarLibros() {
		libros.add(new Libro(1, "Cien años de soledad", "Gabriel García Márquez", "978-3-16-148410-0", true));
		libros.add(new Libro(2, "Don Quijote de la Mancha", "Miguel de Cervantes", "978-3-16-148410-1", true));
		libros.add(new Libro(3, "El amor en los tiempos del cólera", "Gabriel García Márquez", "978-3-16-148410-2", false));
		libros.add(new Libro(4, "1984", "George Orwell", "978-3-16-148410-3", true));
		libros.add(new Libro(5, "La casa de los espíritus", "Isabel Allende", "978-3-16-148410-4", true));

	}
	
	
}