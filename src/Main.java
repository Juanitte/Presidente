
public class Main {

	public static void main(String[] args) {
		
		//Mensaje de inicio.
		
		System.out.println("ALÓ, PRESIDENTE");
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
		
		
		//Repartir las cartas entre los jugadores.
		
		Jugador.llenaMano(baraja.getCartas(), jugadores);
		jugadores = Jugador.ordenaManos(jugadores);
		
		//Reordenar los jugadores para que empiece el poseedor del 3 de bastos.
		
		jugadores = Jugador.ordenaInicio(jugadores);		
		
		//Empieza la partida.

		boolean[] isOver = new boolean[5];
		do {
			int contPasa = 0;
			
			do {
				do {
					
					for(int i = 0; i < jugadores.length; i++) {
						do {
							Jugador.muestraMenu(jugadores, i);
							isOver = Jugador.options(jugadores, i);
							if(isOver[4]) {
								contPasa++;
							}
							if(contPasa == jugadores.length - 1) {
								jugadores = Jugador.asignaPosicion(jugadores, i);
								isOver[1] = true;
							}else {
								contPasa = 0;
							}
						}while(!isOver[0]);
					}
				}while(!isOver[1]);
				jugadores = Jugador.ordenaJugadores(jugadores);
				
			}while(!isOver[2]);
			
			//Al terminar una partida se seleccionan las normas en función del número de jugadores.
			
			if(thereIsVice) {
				
			}
		}while(!isOver[3]);
	}
}
