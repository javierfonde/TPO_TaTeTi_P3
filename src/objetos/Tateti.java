package objetos;

import interfaces.ITateti;

public class Tateti implements ITateti{

	String[][] tablero = new String[3][3];
	
	final String JUGADOR = "X";
	
	final String COMPUTADORA = "O";
	
	final String EMPATE = "E";
	
	final String VACIO = " ";
	
	boolean turnoDeJugador = false;
	
	@Override
	public void inicializar() {
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				this.tablero[i][j] = VACIO;
				
			}
			
		}
		
	}

	@Override
	public void turno(boolean esPrimeroJugador) {
		
		this.turnoDeJugador = esPrimeroJugador;
		
	}
	
	@Override
	public boolean jugarJugador(int posicionF, int posicionC) {
		
		if(this.tablero[posicionF][posicionC] == VACIO) {
			
			this.tablero[posicionF][posicionC] = JUGADOR;
			
			this.mostrarTablero();
			
			return true;
			
		}
		
		System.err.println("La posicion elegida ya fue utilizada");
			
		return false;
		
	}
	
	public void jugarComputadora() {
		
		int mejorScore = -10;
		
		int[] mejorMovimiento = new int[2];
		
		mejorMovimiento[0] = -1;
		
		mejorMovimiento[1] = -1;
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				String valor = this.tablero[i][j];
				
				if(valor == VACIO) {
					
					this.tablero[i][j] = COMPUTADORA;
					
					int score = this.minMax(false);
					
					this.tablero[i][j] = VACIO;
					
					if(score > mejorScore) {
						
						mejorScore = score;
						mejorMovimiento[0] = i;
						mejorMovimiento[1] = j;
						
					}
				}
			}
		}
		
		if(mejorMovimiento[0] != -1 && mejorMovimiento[1]!= -1) {
			
			this.tablero[mejorMovimiento[0]][mejorMovimiento[1]] = COMPUTADORA;
			
		}
		
		this.mostrarTablero();
		
	}
	
	// si devuelve 0 no hay ganador, si es 1 o 2 es quien gano
	public String finalizoJuego() {
		
		// recorre filas
		for (int i = 0; i < 3; i++) {
			if(tablero[i][0]!=" " && tablero[i][0]==tablero[i][1] && tablero[i][1]==tablero[i][2]) {
				return tablero[i][0];
			}
		}	
		
		// recorre columnas
		for (int j = 0; j < 3; j++) {
			if(tablero[0][j]!=" " && tablero[0][j]==tablero[1][j] && tablero[1][j]==tablero[2][j]) {
				return tablero[0][j];
			}
		}	
		
		// diagonal izquierda a derecha, desde arriba hacia abajo
		if(tablero[0][0]!=" " && tablero[0][0]==tablero[1][1] && tablero[1][1]==tablero[2][2]) {
			return tablero[0][0];
		}
		
		//diagonal derecha a izquierda, desde arriba hacia abajo
		if(tablero[0][2]!=" " && tablero[0][2]==tablero[1][1] && tablero[1][1]==tablero[2][0]) {
			return tablero[0][2];
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(tablero[i][j] == VACIO) {
					return VACIO;
				}
			}
		}
		
		return EMPATE;
		
	}
	
	public void mostrarTablero() {
		
		System.out.println("-----------");
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				if (j==2) {
					
					System.out.print(" "+ this.tablero[i][j] + "");
					System.out.println();
					
					if (i!=2) {

						System.out.println("---+---+---");
					
					}
					
				}else {
					
					System.out.print(" " + this.tablero[i][j] + " |");
				
				}
			}
			
		}
		
		System.out.println("-----------");
		
	}
	
	public boolean esTurnoPersona() {
		
		return this.turnoDeJugador;
		
	}

	private int minMax(boolean isMaximizing) {
		
		if(this.finalizoJuego() == JUGADOR) return -1;
		
		else if(this.finalizoJuego() == COMPUTADORA) return 1;
		
		else if(this.finalizoJuego() == EMPATE) return 0;
			
		if(isMaximizing) {
			
			int alfa = -10;
			
			for (int i = 0; i < 3; i++) {
				
				for (int j = 0; j < 3; j++) {
					
					String valor = this.tablero[i][j];
					
					if(valor == VACIO) {
						
						this.tablero[i][j] = COMPUTADORA;
						
						int score = this.minMax(false);
						
						this.tablero[i][j] = VACIO;
						
						if( score > alfa ) {
							
							alfa = score;
							
						}
					}
				}
			}
			
			return alfa;
			
		}else {
			
			int beta = 10;
			
			for (int i = 0; i < 3; i++) {
				
				for (int j = 0; j < 3; j++) {
					
					String valor = this.tablero[i][j];
					
					if(valor == VACIO) {
						
						this.tablero[i][j] = JUGADOR;
						
						int score = this.minMax(true);
						
						this.tablero[i][j] = VACIO;
						
						if( score < beta ) {
							
							beta = score;
							
						}
					}
				}
			}
			
			return beta;
		}	
	}
	
	public String getJugador() {
		return this.JUGADOR;
	}
	
	public String getComputadora() {
		return this.COMPUTADORA;
	}
	
	public String getEmpate() {
		return this.EMPATE;
	}

	public String getVacio() {
		return this.VACIO;
	}
}
