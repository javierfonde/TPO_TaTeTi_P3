package main;

import java.util.Scanner;

import objetos.Tateti;

public class Ejecucion {

	static Tateti tateti;
	
	static Scanner scan;
	
	public static void main(String[] args) {

		scan = new Scanner(System.in);
		
		// dependiendo del valor del booleano que retorna jugar, ver si pedir de nuevo o no
		
		// preguntar si finalizo el juego luego de jugar y jugarMaquina
		
		tateti = new Tateti();
		
		tateti.inicializar();
		
		tateti.turno(true);
		
		if(tateti.esTurnoPersona()) {
			pedirNumero();	
		}else {
			juegaMaquina();
		}
		
		
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
			
			
			esCorrecto = tateti.jugar(fila, columna);
			
		} while (!esCorrecto);
		
		if(tateti.finalizoJuego() == 0) juegaMaquina();
		
	}

	public static void juegaMaquina() {
		
		if(tateti.finalizoJuego() == 0) pedirNumero();
		
	}
	
}
