
public class Main {

	public static void main(String[] args) {
		
		//Mensaje de inicio.
		
		System.out.println("ALÓ, PRESIDENTES");
		System.out.println("");
		
		
		//Introducir nº jugadores.
		
		int numeroJugadores = Jugador.leeEntero("Introduce el número de jugadores: ", 2, 8);
		
		
		//Crear los jugadores de la partida.
		
		Jugador[] jugadores = new Jugador[numeroJugadores];
		
		jugadores = Jugador.creaJugadores(numeroJugadores);
		
		
		//Comprobar si hay más de 4 jugadores para cambiar las reglas.
		
		boolean thereIsVice = Jugador.thereIsVice(numeroJugadores);
		
		
		//Generar baraja.
		
		Baraja baraja = new Baraja();
		
		
		//Barajar las cartas.
		
		baraja.setCartas(Baraja.shuffle(baraja.getCartas()));
		
		
		//Repartir las cartas entre los jugadores y ordenarlas en la mano.
		
		Jugador.llenaMano(baraja.getCartas(), jugadores);
		jugadores = Jugador.ordenaManos(jugadores);
		
		//Reordenar los jugadores para que empiece el poseedor del 3 de bastos.
		
		jugadores = Jugador.ordenaInicio(jugadores);		
		
		//Empieza la partida.
		
		//Creación de array de booleanos para controlar: [0] Fin de turno - [1] Fin de ronda - [2] Fin de partida - [3] Fin de programa - [4] Si el jugador actual ha pasado turno.
		boolean[] isOver = new boolean[5];
		
		//Inicializado de array de cartas para generar una jugada anterior vacía al pasar turno.
		Carta[] ultimaJugadaPasa = new Carta[8];
		
			//Bucle para empezar una nueva partida en caso de que lo elijan los jugadores.
		do {
			
			//Contador de personas seguidas que han pasado turno.
			int contPasa = 0;
			
			//Inicializador a false de todo el array de booleanos.
			isOver = Jugador.creaFlagArray(isOver);
		
			//Bucle para no terminar la partida actual hasta que la flag esté en true.
			do {

				//Boolean que controlará si es el 1º turno de la partida.
				boolean isFirstTurn = true;
				
				//Bucle para no terminar la ronda actual hasta que la flag esté en true.
				do {
					
					//Bucle for para elegir el jugador al que le toca el turno en base a su posición en el array de jugadores.
					for(int i = 0; i < jugadores.length; i++) {
						
						//Bucle para no terminar el turno del jugador actual hasta que la flag esté en true.
						do {
							
							//Muestra el menú para el jugador[i], lee la opción introducida y actúa en consecuencia. Aquí se rellena el array de booleanos que está explicado arriba (línea 50).
							Jugador.muestraMenu(jugadores, i);
							isOver = Jugador.options(jugadores, i, isOver, isFirstTurn);
							
							//Comprobación: Si han pasado todos los jugadores menos 1, reasigna la posición de los jugadores para que el ganador empiece la siguiente ronda y activa el boolean que da por terminada la ronda.
							if(contPasa == jugadores.length - 1) {
								jugadores = Jugador.asignaPosicion(jugadores, i);
								isOver[1] = true;
							}
							
							//Comprobación: Si el jugador actual ha pasado turno, suma 1 al contador de personas que han pasado turno.
							if(isOver[4]) {
								contPasa++;
								jugadores[i].setUltimaJugada(ultimaJugadaPasa);
								jugadores[i].setUltimaJugada(Jugador.creaCartaArray(jugadores, i));
								
								//Si el jugador actual NO ha pasado turno, el contador de personas seguidas que pasan turno se resetea a 0.
							}else {
								contPasa = 0;
							}
							
							//Aquí se comprueba si hay que terminar el turno del jugador actual.
						}while(!isOver[0]);
						
						isOver[0] = false;;
						
						//Comparación de las cartas jugadas en este turno y el anterior para saltar el turno del siguiente jugador si se han jugado las mismas cartas en 2 turnos seguidos.
						if(!isFirstTurn && contPasa == 0) {
							if(Jugador.jugadasIguales(jugadores, i)) {
								if(i == jugadores.length - 1) {
									i = -1;
								}else {
									i++;
								}
							}
						}else {
							isFirstTurn = false;
						}
					}
					
					//Aquí se comprueba si hay que terminar la ronda actual.
				}while(!isOver[1]);
				
				jugadores = Jugador.limpiaNumeroCartas(jugadores);
				
				isOver[1] = false;
				//Aquí se limpian las cartas de ultimaJugada.
				jugadores = Jugador.limpiaUltimaJugada(jugadores);
				
				//En caso de terminar la ronda anterior, se ordenan los jugadores para que el ganador de la ronda, empiece en la siguiente.
				jugadores = Jugador.ordenaJugadores(jugadores);
				
				
				//Aquí se comprueba si hay que terminar la partida actual.
			}while(!isOver[2]);
			
			isOver[2] = false;
			
			//Al terminar una partida se seleccionan las normas en función del número de jugadores.
			if(thereIsVice) {
				
			}
			
			//Aquí se comprueba si hay que terminar el programa.
		}while(!isOver[3]);
	}
}
