package ar.edu.unju.escmi.poo.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.poo.collections.CollectionLibro;
import ar.edu.unju.escmi.poo.collections.CollectionPrestamo;
import ar.edu.unju.escmi.poo.collections.CollectionUsuario;
import ar.edu.unju.escmi.poo.dominio.Alumno;
import ar.edu.unju.escmi.poo.dominio.Bibliotecario;
import ar.edu.unju.escmi.poo.dominio.Libro;
import ar.edu.unju.escmi.poo.dominio.Prestamo;
import ar.edu.unju.escmi.poo.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.poo.exceptions.LibroNoEncontradoException;
import ar.edu.unju.escmi.poo.exceptions.UsuarioNoRegistradoException;
import ar.edu.unju.escmi.poo.util.FechaUtil;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		CollectionLibro.precargarLibros();
		CollectionUsuario.precargarUsuarios();
		int opcion = 0;
		
		do {
			try {
			System.out.println("Menú de Opciones");
			System.out.println("----------------");
			System.out.println
			("1 - Registrar libro.\n" + 
			 "2 - Registrar usuario.\n" +
			 "3 - Préstamo de libro.\n" +
			 "4 - Devolución de libro.\n" +
			 "5 - Listar libros. \n" + 
			 "6 - Salir");
			System.out.println("Ingrese una opción");
			
			
			opcion = scanner.nextInt();
			scanner.nextLine();
			
			}catch(InputMismatchException e) {
				System.out.println("Opcion Inválida. Intente de nuevo ");
				scanner.nextLine();
				opcion = 999;
			}
						
			switch(opcion) {
			
			case 1:	
				try {
					System.out.println("Ingresa tu legajo, para verificar que eres un bibliotecario: ");
					String legajoBib = scanner.nextLine();
					Bibliotecario bibBuscado = CollectionUsuario.buscarBibliotecario(legajoBib);
					if (bibBuscado != null) {
						
						System.out.println("Legajo Confirmado. Tiene permiso para registrar el libro");
						int idLibro = CollectionLibro.sumarId();
						System.out.println("Ingrese el titulo: ");
						String tituloLibro = scanner.nextLine();
						System.out.println("Ingrse el nombre del autor: ");
						String autorLibro = scanner.nextLine();
						System.out.println("Ingrese el isbn: ");
						String isbnLibro = scanner.nextLine();
						boolean estadoLibro = true;
						
						Libro nuevoLibro = new Libro(idLibro,tituloLibro,autorLibro,isbnLibro,estadoLibro);
					CollectionLibro.registrarLibro(nuevoLibro);
					}
				} catch (UsuarioNoRegistradoException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
					break;
			case 2:
				try {
					System.out.println("¿Desea ingresar un bibliotecario o un alumno? (Ingrese 'alumno' o 'bibliotecario')");
					String tipo = scanner.nextLine();
					if (tipo.equalsIgnoreCase("alumno") || tipo.equalsIgnoreCase("bibliotecario")) {
					int idUsuario = CollectionUsuario.sumarId();
					System.out.println("Ingrese el nombre: ");
					String nombreUser = scanner.nextLine();
					System.out.println("Ingrse el apellido: ");
					String apellidoUser = scanner.nextLine();
					System.out.println("Ingrese el email: ");
					String emailUser = scanner.nextLine();
						if (tipo.toLowerCase().equals("bibliotecario")) {
							System.out.println("Ingrese el legajo: ");
							String legajoBib = scanner.nextLine();
							Bibliotecario nuevoBibliotecario = new Bibliotecario(idUsuario,nombreUser,apellidoUser,emailUser,legajoBib);
							CollectionUsuario.registrarUsuario(nuevoBibliotecario);
							System.out.println("Bibliotecario añadido con éxito");
						}else if (tipo.toLowerCase().equals("alumno")){
							System.out.println("Ingrese el curso: ");
							String cursoAlumn = scanner.nextLine();
							System.out.println("Ingrse el Nº de libreta universitaria");
							String luAlumn = scanner.nextLine();
							Alumno nuevoAlumno = new Alumno(idUsuario,nombreUser,apellidoUser,emailUser,cursoAlumn,luAlumn);
							CollectionUsuario.registrarUsuario(nuevoAlumno);
							System.out.println("Alumno añadido con éxito");
						}else {
							System.out.println("Algo falló en la ejecucion. Intentelo de nuevo");
						}
					}else {
						System.out.println("Algo falló en la ejecución. Intentelo de nuevo.");
					}
				}catch(Exception e) {
					System.out.println("Algo falló en la ejecucion. Intentelo de nuevo: " + e.getMessage());
				}
					break;
					
			case 3: 
				LocalDate fechaPeticion = LocalDate.now();
				System.out.println("Ingrese la fecha de devolucion del libro: (dd/MM/YYYY)");
				String fechaStr = scanner.nextLine();
				
				try {
		            LocalDate fechaDevolucion = FechaUtil.convertirStringLocalDate(fechaStr);
		            System.out.println("La fecha de entrega es: " + fechaDevolucion);
		        
				
				System.out.println("Ingrse el id del Libro a prestar: ");
				int idLibroBuscado = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Ingrese la libreta universitaria del alumno: ");
				String luSolicitante= scanner.nextLine();

				Libro libroBuscado = CollectionLibro.buscarLibros(idLibroBuscado);
				Alumno alumnoBuscado = CollectionUsuario.buscarUsuario(luSolicitante);
				if (libroBuscado != null && CollectionLibro.isDisponible(libroBuscado)) {
					if (alumnoBuscado != null) {
						int idPrestamo = CollectionPrestamo.sumarId();
						Prestamo nuevoPrestamo = new Prestamo(idPrestamo,fechaPeticion,fechaDevolucion,libroBuscado,alumnoBuscado);
						CollectionPrestamo.crearPrestamo(nuevoPrestamo);
						nuevoPrestamo.getLibro().setEstado(false);
						System.out.println("Ya puede llevarse su libro...");
					}

				}		
				
				}catch (DateTimeParseException e) {
		            System.out.println(e.getMessage());
		        }catch (InputMismatchException e) {
		        	System.out.println("Dato ingresado incorrectamente.Intentelo de nuevo");
		        } catch (UsuarioNoRegistradoException e) {
		        	System.out.println(e.getMessage());
				} catch (LibroNoEncontradoException e) {
					System.out.println(e.getMessage());
				} catch (LibroNoDisponibleException e) {
					System.out.println(e.getMessage());
				}
					
					break;
					
			case 4: 
				try {
					System.out.println("Ingrese el id del libro devuelto: ");
					int idDevuelto = scanner.nextInt();
					scanner.nextLine();
					CollectionPrestamo.devolverPrestamo(idDevuelto);
				}catch(LibroNoEncontradoException e) {
					System.out.println("Libro no encontrado");
					e.printStackTrace();
				}catch(Exception e) {
					System.out.println("Algo salió mal. Intentelo de nuevo");
					
				}
					break;
			
			case 5:
					CollectionLibro.listarLibros();
					break;
			case 6: 
					System.out.println("Saliendo del programa...");
					break;
					

			default:
				
				System.out.println("Numero fuera de rango [1-5]");
				break;
				
			}
				
		}while (opcion != 6);
		scanner.close();
	}


}