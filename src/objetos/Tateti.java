package objetos;

import interfaces.ITateti;

public class Tateti implements ITateti{

	// creamos una matriz de 3x3 para representar el tablero de la partida
	private String[][] tablero = new String[3][3];
	
	// creamos las opciones de forma constante para que no haya inconsistencia en los datos
	final private String JUGADOR = "X";
	final private String COMPUTADORA = "O";
	final private String EMPATE = "E";
	final private String VACIO = " ";
	
	// booleano que nos dice si arranca jugando el jugador o no
	private boolean turnoDeJugador = false;
	
	// PUNTO 1
	// funcion que setea todos los valores de la matriz tablero en "vacio"
	@Override
	public void inicializar() {
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				this.tablero[i][j] = VACIO;
				
			}
			
		}
		
	}

	/* 
	 * PUNTO 2
	 * el parametro de entrada recive un booleano
	 * que si es verdadero comienza el jugador
	 * que si es falso comienza comienza la maquina
	 * que si no es llamado, por defecto comienza la maquina
	 */
	@Override
	public void turno(boolean esPrimeroJugador) {
		
		this.turnoDeJugador = esPrimeroJugador;
		
	}
	
	/*
	 * PUNTO 3
	 * se recibe por parametro la fila y la columna para poner una ficha por parte del jugador
	 * se valida de que la posición no haya sido utilizada
	 * si puede ingresar la ficha, la ingresa y retorna true
	 * y si no puede, no la ingersa, y retorna false
	 */
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
	
	/*
	 * funcion que al ser llamada, se ejecuta el algoritmo para que 
	 * la computadora elija la mejor jugada y la ejecute
	 */
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
	
	/*
	 * funcion que usamos para verificar el estado de la partida
	 * si retorna X gano el jugador
	 * si retorna O gano la computadora
	 * si retorna E hubo empate
	 * si retorna " " se puede seguir jugando
	 */
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
	
	/*
	 * funcion que muestra por consola el tablero
	 */
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
	
	/*
	 * funcion que retorna un booleano
	 * si es true arranca jugando el jugador
	 * si es false arranca jugando la computadora
	 */
	public boolean esTurnoPersona() {
		
		return this.turnoDeJugador;
		
	}

	/*
	 * funcion minMax, algortimo para la seleccion de la mejor jugada
	 */
	private int minMax(boolean isMaximizing) {
		
		if(this.finalizoJuego() == JUGADOR) return -1;
		
		else if(this.finalizoJuego() == COMPUTADORA) return 1;
		
		else if(this.finalizoJuego() == EMPATE) return 0;
			
		// emula las posibles jugadas con las fichas de la computadora
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
			
		// emula las posibles jugadas con las fichas del jugador
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
	
	public void finPartida() {
		
		if (this.finalizoJuego()== this.getJugador()) {
			
			System.out.println("~~~~~FELICIDADES GANASTE~~~~~");
			
		}else if(this.finalizoJuego()== this.getComputadora()){
			
			System.out.println("~~~~~PERDISTE~~~~~");
			
		}else if(this.finalizoJuego()== this.getEmpate()){
			
			System.out.println("~~~~~EMPATARON~~~~~");
			
		}
	}
	
	/*
	 *  las siguientes funciones sirven para retornar el valor de
	 *  JUGADOR, COMPUTADORA, EMPATE y VACIO
	 *  ya que son variables privadas
	 */
	
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
