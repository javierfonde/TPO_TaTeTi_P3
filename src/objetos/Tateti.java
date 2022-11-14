package objetos;

import interfaces.ITateti;

public class Tateti implements ITateti{

	int[][] tablero = new int[3][3];
	
	// para saber quien tiene el turno, usamos este booleando, si es true es el turno del jugador, si es el false es de la cpu
	boolean turnoDeJugador = false;
	
	@Override
	public void inicializar() {
		
		// inicializamos el tablero en 0, ya que vamos a representar al jugador con el valor 1 y a la cpu con el 2
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tablero[i][j] = 0;
			}
		}
		
		
		
	}

	@Override
	public void turno(boolean esPrimeroJugador) {
		
		turnoDeJugador = esPrimeroJugador;
		
	}

	/* 
	* los parametros de entrada son la fila y la columna donde quiere poner su ficha
	* 1 - 2 - 3
	* 4 - 5 - 6
	* 7 - 8 - 9
	*/
	
	@Override
	public boolean jugar(int posicionF, int posicionC) {
		
		if(tablero[posicionF][posicionC] == 0) {
			
			tablero[posicionF][posicionC] = 1;
			
			mostrarTablero();
			
			return true;
			
		}
		
		System.err.println("La posición elegida ya fue utilizada");
			
		return false;
		
	}
	
	public void jugarMaquina() {
		
		if(true) {
			System.out.println("ENCONTRÓ ESTRATEGIA GANADORA");
		}
		
		System.out.println("MOSTRAR EL TABLERO");
		
	}
	
	// si devuelve 0 no hay ganador, si es 1 o 2 es quien gano
	public int finalizoJuego() {
		
		int ganador = 0;
		
		// recorre todas las filas
		for (int i = 0; i < 3; i++) {
			if(tablero[i][0]!=0 && tablero[i][0]==tablero[i][1] && tablero[i][1]==tablero[i][2]) {
				return tablero[i][0];
			}
		}	
		
		// recorre todas las columnas
		for (int j = 0; j < 3; j++) {
			if(tablero[0][j]!=0 && tablero[0][j]==tablero[1][j] && tablero[1][j]==tablero[2][j]) {
				return tablero[0][j];
			}
		}	
		
		// diagonal izquierda a derecha, desde arriba hacia abajo
		if(tablero[0][0]!=0 && tablero[0][0]==tablero[1][1] && tablero[1][1]==tablero[2][2]) {
			return tablero[0][0];
		}
		
		//diagonal derecha a izquierda, desde arriba hacia abajo
		if(tablero[0][2]!=0 && tablero[0][2]==tablero[1][1] && tablero[1][1]==tablero[2][0]) {
			return tablero[0][2];
		}
		
		return ganador;
	}
	
	public void mostrarTablero() {
		System.out.println("----------");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + tablero[i][j] + " ");
				if(j==2) System.out.println();
			}
		}
		System.out.println("----------");
	}
	
	public boolean esTurnoPersona() {
		return this.turnoDeJugador;
	}

}
