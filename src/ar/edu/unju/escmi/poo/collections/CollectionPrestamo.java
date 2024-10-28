package ar.edu.unju.escmi.poo.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.poo.dominio.Libro;
import ar.edu.unju.escmi.poo.dominio.Prestamo;
import ar.edu.unju.escmi.poo.exceptions.LibroNoEncontradoException;

public class CollectionPrestamo {
	
	public static List<Prestamo> prestamos = new ArrayList<>();
	
	static Scanner scanner = new Scanner(System.in);
	static int idIncremental = 1;
	
	public static int sumarId() {
    	return idIncremental++;
    }
	
	public static void crearPrestamo(Prestamo prestamo) {
		prestamos.add(prestamo);
	}
	
	public static void devolverPrestamo(int id) throws LibroNoEncontradoException {
		Libro libroBuscado = CollectionLibro.buscarLibros(id);
		
			if(libroBuscado.isEstado()) {
				System.out.println("Este libro ya ha sido devuelto...");
			}else {
				for (Prestamo p : prestamos) {
					if(p.getLibro().getId() == id) {
						LocalDate fechaDevolucion = LocalDate.now();
						p.registrarDevolucion(fechaDevolucion);
						break;
					}
				}
			}
	}
	
}