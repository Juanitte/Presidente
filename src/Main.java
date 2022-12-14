
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
		
		
			//Bucle para empezar una nueva partida en caso de que lo elijan los jugadores.
		do {
			
			//Inicializador a false de todo el array de booleanos.
			isOver = Jugador.creaFlagArray(isOver);

			boolean isPresi = false;
			boolean isVicePresi = false;
			boolean isCulo = false;
		
			//Bucle para no terminar la partida actual hasta que la flag esté en true.
			do {

				//Boolean que controlará si es el 1º turno de la partida.
				boolean isFirstTurn = true;

				//Indicador del ganador de la ronda.
				int posGanaRonda = -1;
				
				//Contador de personas que han pasado turno seguidas.
				int contPasa = 1;
				
				//Indicador de la carta a superar.
				Carta cartaASuperar = new Carta();
				
				//Bucle para no terminar la ronda actual hasta que la flag esté en true.
				do {
					
					//Bucle for para elegir el jugador al que le toca el turno en base a su posición en el array de jugadores.
					for(int i = 0; i < jugadores.length && !isOver[1]; i++) {
						
						isOver[4] = false;
						
						if(!isCulo) {
							if(jugadores[i].getRol() != "") {
								while(jugadores[i].getRol() != ""){
									if(i == jugadores.length - 1) {
										i = 0;
										contPasa++;
									}else {
										i++;
										contPasa++;
									}
								}
							}
						}
						
							//Bucle para no terminar el turno del jugador actual hasta que la flag esté en true.
							do {
								
								//Comprobación: Si han pasado todos los jugadores menos 1, reasigna la posición de los jugadores para que el ganador empiece la siguiente ronda y activa el boolean que da por terminada la ronda.
								
								if(!isCulo) {
									if(posGanaRonda == i) {
										jugadores = Jugador.asignaPosicion(jugadores, i - 1);
										isOver[1] = true;
										isOver[0] = true;
									}
									if(posGanaRonda != -1) {
										while(jugadores[posGanaRonda].getRol() != "") {
											if(posGanaRonda == jugadores.length - 1) {
												posGanaRonda = 0;
											}else {
												posGanaRonda++;
											}
										}
									}
								}
									
								if(!isCulo) {
									if(!isOver[0]){
									
										//Muestra el menú para el jugador[i], lee la opción introducida y actúa en consecuencia. Aquí se rellena el array de booleanos que está explicado arriba (línea 50).
										isOver = Jugador.options(jugadores, i, isOver, isFirstTurn, cartaASuperar);
																	
										//Comprobación: Si el jugador actual ha pasado turno, suma 1 al contador de personas que han pasado turno.
										if(isOver[4]) {
											Carta[] ultimaJugadaPasa = new Carta[8];
											jugadores[i].setUltimaJugada(ultimaJugadaPasa);
											jugadores[i].setUltimaJugada(Jugador.creaCartaArray(jugadores, i));
											
											//Si el jugador actual NO ha pasado turno, el contador de personas seguidas que pasan turno se resetea a 0.
										}else if(isOver[0]){
											posGanaRonda = i;
											cartaASuperar = Jugador.compruebaJugada(jugadores, i);
										}
									}
								}
															
								//Aquí se comprueba si hay que terminar el turno del jugador actual.
							}while(!isOver[0]);
							
							//Reparto de rol conforme se quede sin cartas un jugador.
							if(jugadores[i].getRol() == "" || isCulo) {
								int jugadoresFuera = 0;
								if(Jugador.numeroCartasEnMano(jugadores, i) == 0 && !thereIsVice) {
									for(int j = 0; j < jugadores.length; j++) {
										if(jugadores[j].getRol() != "") {
											jugadoresFuera++;
										}
										if(jugadores[j].getRol() == "presidente") {
											isPresi = true;
										}
									}
									if(!isPresi) {
										jugadores[i].setRol("presidente");
										System.out.println(jugadores[i].getNombre()+" es el "+jugadores[i].getRol()+".");
										System.out.println("");
									}else if(jugadoresFuera == jugadores.length - 2){
										jugadores[i].setRol("neutro");
										System.out.println(jugadores[i].getNombre()+" es "+jugadores[i].getRol()+".");
										System.out.println("");
										boolean isDone = false;
										for(int j = 0; j < jugadores.length && !isDone; j++) {
											if(jugadores[j].getRol() == "") {
												jugadores = Jugador.limpiaMano(jugadores, j);
												jugadores[j].setRol("culo");
												System.out.println(jugadores[j].getNombre()+" es el "+jugadores[j].getRol()+".");
												System.out.println("");
												isCulo = true;
												isOver[2] = true;
												isOver[1] = true;
												isOver[0] = true;
												isDone = true;
											}
										}
										isDone = false;
									}else {
										jugadores[i].setRol("neutro");
										posGanaRonda = i;
										System.out.println(jugadores[i].getNombre()+" es "+jugadores[i].getRol()+".");
										System.out.println("");
									}
								}else if(Jugador.numeroCartasEnMano(jugadores, i) == 0) {
									for(int j = 0; j < jugadores.length; j++) {
										if(jugadores[j].getRol() != "") {
											jugadoresFuera++;
										}
										if(jugadores[j].getRol() == "presidente") {
											isPresi = true;
										}
										if(jugadores[j].getRol() == "vicepresidente") {
											isVicePresi = true;
										}
									}
									if(!isPresi) {
										jugadores[i].setRol("presidente");
										System.out.println(jugadores[i].getNombre()+" es el "+jugadores[i].getRol()+".");
										System.out.println("");
									}else if(!isVicePresi) {
										jugadores[i].setRol("vicepresidente");
										System.out.println(jugadores[i].getNombre()+" es el "+jugadores[i].getRol()+".");
										System.out.println("");
									}else if(jugadoresFuera == jugadores.length - 2){
										jugadores[i].setRol("viceculo");
										System.out.println(jugadores[i].getNombre()+" es el "+jugadores[i].getRol()+".");
										System.out.println("");
										boolean isDone = false;
										for(int j = 0; j < jugadores.length && !isDone; j++) {
											if(jugadores[j].getRol() == "") {
												jugadores[j].setRol("culo");
												System.out.println(jugadores[j].getNombre()+" es el "+jugadores[j].getRol()+".");
												System.out.println("");
												isCulo = true;
												isOver[2] = true;
												isOver[1] = true;
												isOver[0] = true;
												isDone = true;
											}
										}
										isDone = false;
									}else {
										jugadores[i].setRol("neutro");
										posGanaRonda = i;
										System.out.println(jugadores[i].getNombre()+" es "+jugadores[i].getRol()+".");
									}
								}
							}
							if(!isCulo) {
								//Comparación de las cartas jugadas en este turno y el anterior para saltar el turno del siguiente jugador si se han jugado las mismas cartas en 2 turnos seguidos.
								if(isOver[4]) {
									contPasa++;
								}else if(!isFirstTurn && !isOver[4]) {
									if(Jugador.jugadasIguales(jugadores, i, contPasa, isOver)) {
										if(i == jugadores.length - 1) {
											i = 0;
										}else {
											i++;
										}
										Carta[] ultimaJugadaPasa = new Carta[8];
										jugadores[i].setUltimaJugada(ultimaJugadaPasa);
										jugadores[i].setUltimaJugada(Jugador.creaCartaArray(jugadores, i));
										contPasa = 2;									
											if(Jugador.numeroCartasEnMano(jugadores, i) == 0) {
												while(Jugador.numeroCartasEnMano(jugadores, i) == 0){
													if(i == jugadores.length - 1) {
														i = 0;
														contPasa++;
													}else {
														i++;
														contPasa++;
													}
												}
											}
									}else {
										contPasa = 1;
									}
								}else if(isFirstTurn){
									isFirstTurn = false;
								}
							}
							isOver[0] = false;
						}
					
					
					//Aquí se comprueba si hay que terminar la ronda actual.
				}while(!isOver[1]);
				
				contPasa = 1;
				
				jugadores = Jugador.limpiaNumeroCartas(jugadores);
				
				isOver[1] = false;
				//Aquí se limpian las cartas de ultimaJugada.
				jugadores = Jugador.limpiaUltimaJugada(jugadores);
				if(!isOver[2]) {
					//En caso de terminar la ronda anterior, se ordenan los jugadores para que el ganador de la ronda, empiece en la siguiente.
					jugadores = Jugador.ordenaJugadores(jugadores);
				}
				
				
				
				//Aquí se comprueba si hay que terminar la partida actual.
			}while(!isOver[2]);
			
			isOver[2] = false;
			
			isOver = Jugador.terminaPartida(isOver, "¿Empezar otra partida? Si / No : ");
			
			if(!isOver[3]) {
				//Reinicializar jugadores.
				
				
				//Generar baraja.
				
				baraja = new Baraja();
				
				
				//Barajar las cartas.
				
				baraja.setCartas(Baraja.shuffle(baraja.getCartas()));
				
				
				//Repartir las cartas entre los jugadores y ordenarlas en la mano.
				
				Jugador.llenaMano(baraja.getCartas(), jugadores);
				jugadores = Jugador.ordenaManos(jugadores);
				
				//Ordenar los jugadores por rol.
				jugadores = Jugador.ordenaConRoles(jugadores);
				
				//Aquí se cambian las cartas según los roles.
				jugadores = Jugador.cartasAsignadas(jugadores, thereIsVice);
				jugadores = Jugador.ordenaManos(jugadores);
				jugadores = Jugador.limpiaRoles(jugadores);
			}
			
			//Aquí se comprueba si hay que terminar el programa.
		}while(!isOver[3]);
	}
}
