import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Pipe.SourceChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Ejercicio1 {
	
	static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	static Map<String, Integer> agenda = new HashMap<String, Integer>();

	public static void main(String[] args) throws IOException {
		pedirNombre();
		int opcion = 0;
		
		do {
			
			System.out.println("*****Agenda de contactos*****\n [1]Agendar a un amigo\n [2]Consultar el numero de amigos que hay agendados\n [3]Consultar el telefono de un amigo\n [4]Modificar el telefono de un amigo\n [5]Consultar a quien pertenece un número de teléfono\n [6]Borrar a un ex-amido de la agenda\n [7]Mostrar el contenido de una agenda\n [8]Mostrar solo los nombres de los amigos\n [9]Ordenar agenda por nombres\n [10]Ordenar agenda por telefonos\n [11]SALIR");
			
			opcion = pedirOpcion();
			
			switch (opcion) {
			case 1:
				agendarAmigo();
				break;
			case 2:
				consultarNumTotal();
				break;
			case 3:
				consultarTelAmigo();
				break;
			case 4:
				modificarTelAmigo();
				break;
			case 5:
				consultarPertenenciaNumero();
				break;
			case 6:
				borrarAmigo();
				break;
			case 7:
				mostrarContenidoAgenda();
				break;
			case 8:
				mostrarNombres();
				break;
			case 9:
				ordenarPorNombre();
				break;
			case 10:
				ordenarPorNumero();
				break;

			default:
				break;
			}
			
		} while (opcion != 11);

	}
	
	//Metodo para pedir el nombre
	private static String pedirNombre() throws IOException {
		
		String nombre = "";
		
		while (nombre.isEmpty()) {
			System.out.print("Nombre amigo: ");
			nombre = entrada.readLine();
			
			if (nombre.isEmpty()) {
				System.out.println("ERROR: El nombre no puede estar vacio");
			}
		}
		
		return nombre;
	}
	
	//Metodo para pedir numero de telefono
	private static int pedirNumTel() throws IOException {
		
		boolean valida = false;
		int numTel = 111111111;
		
		while (!valida) {
			try {
				System.out.print("Numero de telefono: ");
				numTel = Integer.parseInt(entrada.readLine());
				
				if (numTel >= 111111111 && numTel <= 999999999) {
					valida = true;
				}else {
					System.out.println("ERROR: Debes introducir un numero valido de 9 digitos");
				}
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Debes introducir un numero valido de 9 digitos");
			} 
		}
		
		return numTel;
	}

	//Metodo para pedir la opcion
	private static int pedirOpcion() throws IOException {
		int opcion = 0;
		boolean valida = false;
		
		while (!valida) {
			try {
				
				System.out.print("¿Que quieres hacer?: ");
				opcion = Integer.parseInt(entrada.readLine());
				
				if (opcion >= 1 && opcion <= 11) {
					valida = true;
				}else {
					System.out.println("ERROR: debes introducir un número entre 1 y 11");
				}
				
			} catch (NumberFormatException e) {
				System.out.println("ERROR: debes introducir un valor numérico");
			}
		}
		
		return opcion;
	}
	
	//Metodo para agendar a un amigo
	private static void agendarAmigo() throws IOException { //1
		String nombre;
		boolean numValido = false;
		int numero = 0;
		
		System.out.print("Dime el nombre del contacto: ");
		nombre = entrada.readLine();
		
		while (!numValido) {
			try {
				
				System.out.print("Dime el número del contacto: ");
				numero = Integer.parseInt(entrada.readLine());
				
				if (numero >= 111111111 && numero <= 999999999) {
					numValido = true;
				}else {
					System.out.println("ERROR: el número tiene que ser de 9 cifras");
				}
				
			} catch (NumberFormatException e) {
				System.out.println("ERROR: debes introducir un número de teléfono");
			}
		}
		
		agenda.put(nombre, numero);
		
		System.out.println("*****Contacto añadido correctamente*****");
		System.out.println();
		
	}
	
	//Metodo para consultar el numero de amigos que hay
	private static void consultarNumTotal() { //2
		
		if (agenda.isEmpty()) {
			System.out.println("La agenda está vacia");
			System.out.println();
			return;
		}
		
		int total = agenda.size();
		
		System.out.println("En la agenda tienes un total de " + total + " amigos");
		
	}
	
	//Metodo para consultar el telefono de un amigo
	private static void consultarTelAmigo() throws IOException { //3
		if (agenda.isEmpty()) {
			System.out.println("La agenda está vacia");
			System.out.println();
			return;
		}
		
		System.out.print("Nombre amigo: ");
		String nombre = entrada.readLine();
		boolean encontrado = false;
		
		for (Entry<String, Integer> amigo : agenda.entrySet()) {
			if (amigo.getKey().equals(nombre)) {
				System.out.println(nombre + " tiene el teléfono: " + amigo.getValue());
				encontrado = true;
				break;
			}
		}
		
		if (!encontrado) {
			System.out.println("No se ha encontrado a " + nombre + " en la agenda");
		}
		
	}
	
	//Metodo para modificar el telefono de un amigo
	private static void modificarTelAmigo() throws IOException { //4
		if (agenda.isEmpty()) {
			System.out.println("La agenda está vacia");
			System.out.println();
			return;
		}
		
		String nombre = pedirNombre();
		
		if (agenda.containsKey(nombre)) {
			
			int nuevoTel = pedirNumTel();
			agenda.put(nombre, nuevoTel);
			System.out.println("*****Numero cambiado correctamente*****");
			
		}else {
			
			System.out.println("No se ha encontrado a " + nombre + " en la agenda");
			System.out.println();
			
		}
	}
	
	//Consultar a quien pertenece un numero de telefono
	private static void consultarPertenenciaNumero() throws IOException { //5
		
		if (agenda.isEmpty()) {
			System.out.print("La agenda esta vacia");
			System.out.println();
			return;
		}
		
		int numTel = pedirNumTel();
		boolean encontrado = false;
		
		for (Entry<String, Integer> amigo : agenda.entrySet()) {
			if (amigo.getValue().equals(numTel)) {
				System.out.println("El numero " + numTel + " pertenece a " + amigo.getKey());
				System.out.println();
				encontrado = true;
				break;
			}
		}
		
		if (!encontrado) {
			System.out.println("No se ha encontrado el numero " + numTel + " en la agenda");
			System.out.println();
		}
		
	}
	
	//Metodo para borrar a un amigo de la agenda
	private static void borrarAmigo() throws IOException { //6
		
		
	}
	
	//Metodo para mostrar el contenido de la agenda
	private static void mostrarContenidoAgenda() { //7
		
		
	}
	
	//Metodo para mostrar los nombres de los agendados
	private static void mostrarNombres() { //8
		
		
	}
	
	//Metodo para ordenar por nombre
	private static void ordenarPorNombre() { //9
		
		
	}

	//Metodo para ordenar por numero
	private static void ordenarPorNumero() { //10
		
		
	}
}
