package main;

import java.util.Scanner;

import objetos.Tateti;

public class Ejecucion {

	static Tateti tateti;
	
	static Scanner scan;
	
	public static void main(String[] args) {

		scan = new Scanner(System.in);
		
		tateti = new Tateti();
		
		tateti.inicializar();
		
		boolean arrancaJugador = preguntarQuienArranca();
		
		tateti.turno(arrancaJugador);
		
		if(tateti.esTurnoPersona()) {
			
			pedirNumero();
			
		}else {
			
			juegaMaquina();
			
		}
		
		
	}
	
	private static boolean preguntarQuienArranca() {
		
		System.out.println("Si quiere que comience el jugador ingrese: 1");
		
		System.out.println("Si quiere que comience la computadora ingrese: 2");
		
		int quienIngresa = scan.nextInt();
		
		return quienIngresa == 1;
		
	}

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
		
		if(estadoPartida == " ") {
			
			juegaMaquina();
			
		}else {
			
			finPartida();
			
		}
		
		
	}

	public static void juegaMaquina() {
		
		tateti.jugarComputadora();
		
		if(tateti.finalizoJuego() == " ") {
			
			pedirNumero();
			
		} else {
			
			finPartida();
			
		}
		
	}
	
	public static void finPartida() {
		
		if (tateti.finalizoJuego()== tateti.getJugador()) {
			
			System.out.println("~~~~~FELICIDADES GANASTE~~~~~");
			
		}else if(tateti.finalizoJuego()== tateti.getComputadora()){
			
			System.out.println("~~~~~PERDISTE~~~~~");
			
		}else if(tateti.finalizoJuego()== tateti.getEmpate()){
			
			System.out.println("~~~~~EMPATARON~~~~~");
			
		}
	}
}
