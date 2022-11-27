package main;

import java.util.Scanner;

import objetos.Tateti;

public class Ejecucion {

	static Tateti tateti;
	
	static Scanner scan;
	
	public static void main(String[] args) {

		// inicializamos un scanner para tomar los valores por teclado 
		scan = new Scanner(System.in);
		
		// inicializamos un objeto tateti para poder jugar
		tateti = new Tateti();
		
		// llamamos a inicializar para poner todas las opciones en vacio
		tateti.inicializar();
		
		// preguntamos por consola si quiere que comience el jugador o la computadora
		boolean arrancaJugador = preguntarQuienArranca();
		
		// le damos a tateti el valor de la pregunta de quien arranca
		tateti.turno(arrancaJugador);
		
		// verificamos quien comienza y llamamos al jugador o a la computadora segun dependa el caso
		if(tateti.esTurnoPersona()) {	
			pedirNumero();
		}else {
			juegaMaquina();
		}
		
		
	}
	
	private static boolean preguntarQuienArranca() {
		
		int quienIngresa = 0; 
		
		do {
			
			System.out.println("Si quiere que comience el jugador ingrese: 1");
			
			System.out.println("Si quiere que comience la computadora ingrese: 2");
			
			quienIngresa = scan.nextInt();
			
		}while(quienIngresa != 1 && quienIngresa != 2);
		
		return quienIngresa == 1;
		
	}

	/*
	 * solicitamos que el jugador ingrese la fila y columna donde quiere ingresar su ficha
	 * verificamos que sea una posición valida
	 * si es invalida le pedimos que reingrese
	 * si es valida ingresamos su ficha en el tablero
	 * mostramos el tablero
	 * luego verificamos si se puede seguir jugando
	 * si se puede llamamos a que juegue la maquina
	 * si no, mostramos el resultado final de la partida
	 */
	public static void pedirNumero() {
		
		boolean esCorrecto = false;
		
		do {
			
			System.out.println("Ingrese el numero de fila (1,2 o 3)");
			
			int fila = scan.nextInt()-1;
			
			while(fila < 0 || fila > 2) {
				
				System.out.println("ERROR. Ingrese el numero de fila VALIDO (1,2 o 3)");
				
				fila = scan.nextInt()-1;
				
			}
			
			System.out.println("Ingrese el numero de columna (1,2 o 3)");
			
			int columna = scan.nextInt()-1;
			
			while(columna < 0 || columna > 2) {
				
				System.out.println("ERROR.Ingrese el numero de columna VALIDO (1,2 o 3)");
				
				columna = scan.nextInt()-1;
				
			}
			
			esCorrecto = tateti.jugarJugador(fila, columna);
			
		} while (!esCorrecto);
		
		String estadoPartida= tateti.finalizoJuego();
		
		if(estadoPartida == tateti.getVacio()) {
			
			juegaMaquina();
			
		}else {
			
			tateti.finPartida();
			
		}
		
		
	}
	
	/*
	 * hacemos jugar a la maquina
	 * luego verificamos si se puede seguir jugando
	 * si se puede llamamos a que juegue el jugador
	 * si no, mostramos el resultado final de la partida
	 */

	public static void juegaMaquina() {
		
		tateti.jugarComputadora();
		
		if(tateti.finalizoJuego() == tateti.getVacio()) {
			
			pedirNumero();
			
		} else {
			
			tateti.finPartida();
			
		}
		
	}
	
	
}
