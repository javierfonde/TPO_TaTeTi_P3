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
			
			int fila = 3;
			do{
				System.out.println("Ingrese el numero de fila (0, 1 o 2)");
				fila = scan.nextInt();
			}while(fila < 0 && fila > 2);
			
			
			int columna = 3;
			do {
				System.out.println("Ingrese el numero de columna (0, 1 o 2)");
				columna = scan.nextInt();
			}while(columna < 0 && columna > 2);
			
			esCorrecto = tateti.jugar(fila, columna);
			
		} while (!esCorrecto);
		
		if(tateti.finalizoJuego() == 0) juegaMaquina();
		
	}

	public static void juegaMaquina() {
		
		if(tateti.finalizoJuego() == 0) pedirNumero();
		
	}
	
}
