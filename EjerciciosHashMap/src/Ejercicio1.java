import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1 {
	
	static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int opcion = 0;
		
		System.out.println("*****Agenda de contactos*****\n [1]Agendar a un amigo\n [2]Consultar el numero de amigos que hay agendados\n [3]Consultar el telefono de un amigo\n [4]Modificar el telefono de un amigo\n [5]Consultar a quien pertenece un número de teléfono\n [6]Borrar a un ex-amido de la agenda\n [7]Mostrar el contenido de una agenda\n [8]Mostrar solo los nombres de los amigos\n [9]Ordenar agenda por nombres\n [10]Ordenar agenda por telefonos\n [11]SALIR");
		
		opcion = pedirOpcion();

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

}
