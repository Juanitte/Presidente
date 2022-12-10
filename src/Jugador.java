import java.util.Scanner;
public class Jugador {

	private String nombre;
	private String rol;
	private int posicion;
	private Carta[] mano;
	private Carta[] ultimaJugada;
	private int numeroCartasJugadas;

	/**
	 * Constructor de la clase Jugador.
	 * @param numeroJugadores , número de jugadores, para calcular el tamaño del array.
	 */
	
	
	public Jugador(int numeroJugadores) {
		this.nombre = "";
		this.rol = "";
		this.posicion = 0;
		this.ultimaJugada = new Carta[8];
		this.numeroCartasJugadas = 0;
		this.mano = new Carta[(40 / numeroJugadores) + 1];
		for(int i = 0; i < mano.length; i++) {
			this.mano[i] = new Carta();
		}
	}
	
	/**
	 * Getter para el atributo nombre de la clase Jugador.
	 * @return un String con el nombre del jugador.
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter para el atributo nombre de la clase Jugador.
	 * @param nombre , el nombre del jugador.
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter para el atributo rol de la clase Jugador.
	 * @return , un String con el rol del jugador.
	 */
	
	public String getRol() {
		return rol;
	}
	
	/**
	 * Setter para el atributo rol de la clase Jugador.
	 * @param rol , el rol del jugador.
	 */
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	/**
	 * Getter para el atributo posicion de la clase Jugador.
	 * @return , un int con la posicion del jugador.
	 */
	
	public int getPosicion() {
		return posicion;
	}
	
	/**
	 * Setter para el atributo posicion de la clase Jugador.
	 * @param posicion , la posicion del jugador.
	 */
	
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	/**
	 * Getter para el atributo mano de la clase Jugador.
	 * @return Un array de objetos de clase Carta con la mano del jugador.
	 */
	
	public Carta[] getMano() {
		return mano;
	}
	
	/**
	 * Setter para el atributo mano de la clase Jugador.
	 * @param mano , la mano del jugador.
	 */
	
	public void setMano(Carta[] mano) {
		this.mano = mano;
	}
	
	/**
	 * Getter para el atributo ultimaJugada de la clase Jugador.
	 * @return un array de cartas con las ultimas cartas jugadas del jugador.
	 */
	
	public Carta[] getUltimaJugada() {
		return ultimaJugada;
	}
	
	/**
	 * Setter para el atributo ultimaJugada de la clase Jugador.
	 * @param ultimaJugada , las cartas del jugador.
	 */
	
	public void setUltimaJugada(Carta[] ultimaJugada) {
		this.ultimaJugada = ultimaJugada;
	}
		
	/**
	 * Getter para el atributo numeroCartasJugadas de la clase Jugador.
	 * @return un entero con el número de cartas jugadas por el jugador.
	 */
	
	public int getNumeroCartasJugadas() {
		return numeroCartasJugadas;
	}
	
	/**
	 * Setter para el atributo numeroCartasJugadas de la clase Jugador.
	 * @param numeroCartasJugadas , el número de cartas jugadas por el jugador.
	 */
	
	public void setNumeroCartasJugadas(int numeroCartasJugadas) {
		this.numeroCartasJugadas = numeroCartasJugadas;
	}
	
	
	/**
	 * Método para determinar si hay más de 4 jugadores o no.
	 * @param jugadores , el número de jugadores.
	 * @return Un boolean true si hay más de 4 jugadores, o false si hay 4 o menos.
	 */
	
	
	public static boolean thereIsVice(int jugadores) {
		boolean thereIsVice = false;
		
		if(jugadores > 4) {
			thereIsVice = true;
		}
		
		return thereIsVice;
	}
	
	
	/**
	 * Método para crear un array de jugadores e introducir sus nombres.
	 * @param numeroJugadores , el número de jugadores.
	 * @return Un array de objetos de clase Jugador con el nombre ya puesto en cada uno.
	 */
	
	
	public static Jugador[] creaJugadores(int numeroJugadores) {
		Jugador[] jugadores = new Jugador[numeroJugadores];
		
		for(int i = 0; i < jugadores.length; i++) {
			jugadores[i] = new Jugador(numeroJugadores);
			System.out.println("JUGADOR "+(i + 1));
			jugadores[i].nombre = validaString("Introduce tu nombre: ", 3, 20);
			System.out.println("¡Jugador creado con éxito!");
		}
		
		return jugadores;
	}
	
	/**
	 * Método para validar que el nombre de jugador introducido no tiene más de 20 caracteres ni menos de 3.
	 * @param msn , el mensaje para pedir que introduzca nombre.
	 * @return Un String con el nombre del jugador ya validado.
	 */
	
	
	
	public static String validaString(String msn, int min, int max) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean isIn = false;
		String nombre = "";
		
		do {
			System.out.println("");
			System.out.print(msn);
			nombre = sc.nextLine();
			if(nombre.length() <= max && nombre.length() >= min) {
				isIn = true;
			}else if(nombre.length() > max){
				System.out.println("El texto no puede superar los "+max+" caracteres.");
			}else {
				System.out.println("El texto debe tener al menos "+min+" caracteres.");
			}
		}while(!isIn);
		
		
		return nombre;
	}
	
	/**
	 * Método que reparte todas las cartas de la baraja entre los jugadores.
	 * @param baraja , baraja a repartir.
	 * @param jugadores , array de jugadores de la partida.
	 * @return el array de jugadores con el atributo mano modificado.
	 */
	
	public static Jugador[] llenaMano(Carta[] baraja, Jugador[] jugadores) {
		System.out.println("Repartiendo cartas...");
		for(int i = 0; i < jugadores.length; i++) {
			for(int j = 0; j < (baraja.length / jugadores.length); j++) {
				jugadores[i].mano[j] = Baraja.sacaCarta(baraja);
				Baraja.reordenaBaraja(baraja);
			}
		}
		
		return jugadores;
	}
	
	/**
	 * Método para mostrar el menú de acción.
	 * @param jugadores , array de jugadores.
	 * @param pos , posición del jugador que debe jugar este turno.
	 */
	
	public static void muestraMenu(Jugador[] jugadores, int pos) {
		System.out.println("");
		System.out.print("TURNO DE "+jugadores[pos].nombre.toUpperCase()+".");
		System.out.println("");
		System.out.println("1 - Jugar.");
		System.out.println("2 - Pasar.");
		System.out.println("3 - Ver Mano.");
	}
	
	public static boolean[] options(Jugador[] jugadores,int pos, boolean[] isOver, boolean isFirstTurn, Carta cartaASuperar) {
		
		// En isOver[0] se controlará el fin de turno.
		
		// En isOver[1] se controlará el fin de ronda.
		
		// En isOver[2] se controlará el fin de partida.
		
		// En isOver[3] se controlará el fin del juego.
		
		// En isOver[4] se controlará si el jugador ha pasado turno.
		
		int opt = Jugador.leeEntero("", 1, 3);
		
		switch(opt) {
			case 1:
				//Aquí además de realizar las acciones de la opción jugar, llena un array con las cartas que se han jugado.
				if(isFirstTurn) {
					jugadores = numeroCartas(jugadores, pos, cartaASuperar);
				}else {
					jugadores = numCartasRonda(jugadores);
				}
				if(isFirstTurn || numCartasCorrecto(jugadores, pos, cartaASuperar)) {
					jugadores = Jugador.opcionJugar(jugadores, pos, isFirstTurn, cartaASuperar);
					isOver[0] = true;
					isOver[4] = false;
					if(isFirstTurn && !numCartasCorrecto(jugadores, pos, cartaASuperar)) {
						isOver[0] = false;
						isOver[4] = false;
					}
				}else {
					System.out.println("No tienes cartas suficientes para jugar este turno.");
				}
				break;
			case 2:
				if(!isFirstTurn) {
					System.out.println("Has pasado tu turno.");
					isOver[0] = true;
					isOver[4] = true;
				}else {
					System.out.println("No puedes pasar el 1º turno de la ronda.");
				}
				break;
			case 3:
				Jugador.verMano(jugadores, pos);
				isOver[0] = false;
				isOver[4] = false;
		}
		
		return isOver;
	}
	
	/**
	 * Método para settear el número de cartas jugadas por el jugador.
	 * @param jugadores , el array de jugadores.
	 * @param pos , la posición del jugador actual en el array.
	 * @return el array de jugadores modificado.
	 */
	
	public static Jugador[] numeroCartas(Jugador[] jugadores, int pos, Carta cartaASuperar) {

		jugadores[pos].setNumeroCartasJugadas(leeEntero("Introduce el número de cartas a jugar: ", 1, 8));
		
		if(!numCartasCorrecto(jugadores, pos, cartaASuperar)) {
			System.out.println("No tienes suficientes cartas para realizar esta jugada.");
			jugadores[pos].setNumeroCartasJugadas(0);
		}	
		
		return jugadores;
	}
	
	/**
	 * Método para comprobar que el número de cartas introducido es correcto.
	 * @param jugadores , el array de jugadores.
	 * @param pos , la posición del jugador actual en el array.
	 * @return un boolean true si la jugada es correcta y false si no lo es.
	 */
	
	public static boolean numCartasCorrecto(Jugador[] jugadores, int pos, Carta cartaASuperar) {
		boolean isCorrect = false;
		
		if(cartasEnMano(jugadores, jugadores[pos].getNumeroCartasJugadas(), pos, cartaASuperar)) {
			isCorrect = true;
		}
		
		return isCorrect;
	}
	
	/**
	 * Método para llenar el atributo numeroCartasJugadas para que sea en todos los jugadores como en el 1º.
	 * @param jugadores , el array de jugadores.
	 * @return el array de jugadores modificado.
	 */
	
	public static Jugador[] numCartasRonda(Jugador[] jugadores) {
		
		for(int i = 0; i < jugadores.length; i++) {
			jugadores[i].setNumeroCartasJugadas(jugadores[0].getNumeroCartasJugadas());
		}
		
		return jugadores;
	}
	
	/**
	 * Método para realizar las acciones de la opción Jugar.
	 * @param pos , posición del jugador actual.
	 */
	
	public static Jugador[] opcionJugar(Jugador[] jugadores, int pos, boolean isFirstTurn, Carta cartaASuperar) {
		System.out.println("");
		String nombreCarta = "";
		boolean areThere = false;
		Carta carta = new Carta();
		int auxCartas = 0;
		Carta cartaJugada = new Carta();
		
		
		
		if(!isFirstTurn || numCartasCorrecto(jugadores, pos, cartaASuperar)) {
		
			Carta[] lastPlay = new Carta[jugadores[pos].getNumeroCartasJugadas()];
			
			lastPlay = creaCartaArray(jugadores, pos);
			
			jugadores[pos].setUltimaJugada(lastPlay);
			
			for(int i = 0; i < jugadores[pos].getNumeroCartasJugadas(); i++) {
				boolean isCorrect = false;
				do {
					boolean isValid = false;
					areThere = false;
					boolean isOK = false;
					do {
						isOK = false;
						int min = 1;
						System.out.println("");
						System.out.print("Introduce la "+(i + 1)+"ª carta a jugar:");
						nombreCarta = validaString("", min, 50);
						min = setMin(nombreCarta);
						if(nombreCarta.length() >= min) {
							isValid = true;
						}else {
							System.out.println("Debes introducir un nombre de carta válido.");
						}
						if(isValid) {
							isOK = compruebaValores(cartaASuperar, nombreCarta);
						}
						if(!isOK) {
							System.out.println("La carta introducida no es del valor adecuado.");
						}
					}while(!isOK);
					
					cartaJugada = cartaFromString(nombreCarta);
					isCorrect = cartaEnMano(jugadores, pos, cartaJugada);
					if(isCorrect) {
						carta = cartaFromString(nombreCarta);
					}
					if(isCorrect && !areThere) {
						for(int j = 0; j < jugadores[pos].mano.length; j++) {
							if(jugadores[pos].mano[j].getNumero() == carta.getNumero()) {
								auxCartas++;
							}
						}
						if(auxCartas < jugadores[pos].getNumeroCartasJugadas()) {
							System.out.println("No tienes suficientes cartas del valor especificado en la mano.");
							isCorrect = false;
						}else {
							areThere = true;
						}
					}else {
						System.out.println("El nombre introducido no coincide con ninguna carta de tu mano.");
					}
				}while(!isCorrect);
				jugadores[pos].ultimaJugada[i].setNumero(carta.getNumero());
				jugadores[pos].ultimaJugada[i].setPalo(carta.getPalo());
				jugadores = sacaCarta(jugadores, pos, carta);
			}
		}
		return jugadores;
	}
	
	/**
	 * Método para comparar si la carta introducida es igual o superior a la anterior.
	 * @param cartaASuperar , la carta a superar.
	 * @param nombreCarta , el string introducido por el jugador, con el nombre de la carta.
	 * @return un boolean true si la carta es jugable y false si no lo es.
	 */
	
	public static boolean compruebaValores(Carta cartaASuperar, String nombreCarta) {
		
		boolean isOK = false;
		Carta carta = new Carta();
		
		carta = cartaFromString(nombreCarta);
		if(cartaASuperar.getNumero() == 2 && cartaASuperar.getPalo() != "oros") {
			if(carta.getNumero() == 2) {
				isOK = true;
			}
		}else if(cartaASuperar.getNumero() == 1) {
			if(carta.getNumero() == 2 || carta.getNumero() == 1) {
				isOK = true;
			}
		}else {
			if(carta.getNumero() >= cartaASuperar.getNumero() || carta.getNumero() == 1 || carta.getNumero() == 2) {
				isOK = true;
			}
		}
		
		return isOK;
	}
	
	/**
	 * Método que devuelve la última carta jugada.
	 * @param jugadores , el array de jugadores.
	 * @param pos , la posición del jugador actual.
	 * @return un objeto de clase Carta con el valor de la última carta jugada.
	 */
	
	public static Carta compruebaJugada(Jugador[] jugadores, int pos) {
		
		Carta carta = new Carta();
		
		for(int i = 0; i < jugadores[pos].getUltimaJugada().length; i++) {
			if(jugadores[pos].getUltimaJugada()[i].getNumero() !=2 && jugadores[pos].getUltimaJugada()[i].getNumero() !=0) {
				carta.setNumero(jugadores[pos].getUltimaJugada()[i].getNumero());
				carta.setPalo(jugadores[pos].getUltimaJugada()[i].getPalo());
			}else if(jugadores[pos].getUltimaJugada()[i].getNumero() !=0) {
				carta.setNumero(2);
				carta.setPalo(jugadores[pos].getUltimaJugada()[i].getPalo());
			}
		}
		
		return carta;
	}
	
	/**
	 * Método que marca un mínimo de tamaño de string en función de la carta que se introduzca.
	 * @param nombreCarta , el string con el nombre de la carta que han introducido.
	 * @return un int con el tamaño mínimo que debe tener el string.
	 */
	
	public static int setMin(String nombreCarta) {
		int min = 5;
		
		if(nombreCarta.charAt(0) == 'R') {
			min = 7;
		}else if(nombreCarta.charAt(0) == 'C') {
			min = 11;
		}else if(nombreCarta.charAt(0) == 'S') {
			min = 8;
		}
		
		return min;
	}
	
	public static Jugador[] limpiaNumeroCartas(Jugador[] jugadores) {
		
		for(int i = 0; i < jugadores.length; i++) {
			jugadores[i].setNumeroCartasJugadas(0);
		}
		
		return jugadores;
	}
	
	/**
	 * Método que compara 2 arrays de cartas y devuelve un boolean true si los números de las cartas (que no sean 2) coinciden. En caso de ser todo 2 también da true.
	 * @param jugadaActual , uno de los arrays de cartas.
	 * @param jugadaAnterior , el otro array de cartas.
	 * @return un boolean true si las 2 últimas jugadas son iguales.
	 */
	
	public static boolean jugadasIguales(Jugador[] jugadores, int pos, int contPasa) {
		boolean isEqual = false;
		int act = 2;
		int ant = 2;
			
		
			for(int i = 0; i < jugadores[pos].getUltimaJugada().length ; i++) {
				if(jugadores[pos].getUltimaJugada()[i].getNumero() != 2 && jugadores[pos].getUltimaJugada()[i].getNumero() != 0) {
					act = jugadores[pos].getUltimaJugada()[i].getNumero();
				}
				if(pos - contPasa >= 0) {
					if(jugadores[pos - contPasa].getUltimaJugada()[i].getNumero() != 2 && jugadores[pos - contPasa].getUltimaJugada()[i].getNumero() != 0) {
						ant = jugadores[pos - contPasa].getUltimaJugada()[i].getNumero();
					}
				}else {
					if(jugadores[jugadores.length - contPasa].getUltimaJugada()[i].getNumero() != 2 && jugadores[jugadores.length - contPasa].getUltimaJugada()[i].getNumero() != 0) {
						ant = jugadores[jugadores.length - contPasa].getUltimaJugada()[i].getNumero();
					}
				}
			}
		
		if(act == ant) {
			isEqual = true;
		}
		
		return isEqual;
	}
	
	/**
	 * Método para vaciar el array ultimaJugada de todos los jugadores para la siguiente ronda.
	 * @param jugadores , el array de jugadores.
	 * @return el array de jugadores con todas las cartas de ultimaJugada en default.
	 */
	
	public static Jugador[] limpiaUltimaJugada(Jugador[] jugadores) {
		
		for(int i = 0; i < jugadores.length; i++) {
			for(int j = 0; j < jugadores[i].getUltimaJugada().length; j++) {
				jugadores[i].ultimaJugada[j].setNumero(0);
				jugadores[i].ultimaJugada[j].setPalo("");;
			}
		}
		
		return jugadores;
	}
	
	/**
	 * Método que recibe un string y devuelve la carta a la que hace referencia.
	 * @param nombreCarta , el string que designa a la carta(en el formato de Carta.toString()).
	 * @return El objeto de clase Carta a la que hace referencia el String dado.
	 */
	
	public static Carta cartaFromString(String nombreCarta) {
		Carta carta = new Carta();
		boolean esFigura = false;
		if(nombreCarta.charAt(0) == '3') {
			carta.setNumero(3);
		}else if(nombreCarta.charAt(0) == '4') {
			carta.setNumero(4);
		}else if(nombreCarta.charAt(0) == '5') {
			carta.setNumero(5);
		}else if(nombreCarta.charAt(0) == '6') {
			carta.setNumero(6);
		}else if(nombreCarta.charAt(0) == '7') {
			carta.setNumero(7);
		}else if(nombreCarta.charAt(0) == 'S') {
			carta.setNumero(8);
			esFigura = true;
			if(nombreCarta.charAt(8) == 'o') {
				carta.setPalo("oros");
			}else if(nombreCarta.charAt(8) == 'c') {
				carta.setPalo("copas");			
			}else if(nombreCarta.charAt(8) == 'e') {
				carta.setPalo("espadas");			
			}else{
				carta.setPalo("bastos");			
			}
		}else if(nombreCarta.charAt(0) == 'C') {
			carta.setNumero(9);
			esFigura = true;
			if(nombreCarta.charAt(11) == 'o') {
				carta.setPalo("oros");
			}else if(nombreCarta.charAt(11) == 'c') {
				carta.setPalo("copas");			
			}else if(nombreCarta.charAt(11) == 'e') {
				carta.setPalo("espadas");			
			}else{
				carta.setPalo("bastos");			
			}
		}else if(nombreCarta.charAt(0) == 'R') {
			carta.setNumero(10);
			esFigura = true;
			if(nombreCarta.charAt(7) == 'o') {
				carta.setPalo("oros");
			}else if(nombreCarta.charAt(7) == 'c') {
				carta.setPalo("copas");			
			}else if(nombreCarta.charAt(7) == 'e') {
				carta.setPalo("espadas");			
			}else{
				carta.setPalo("bastos");			
			}
		}else if(nombreCarta.charAt(0) == '1') {
			carta.setNumero(1);
		}else if(nombreCarta.charAt(0) == '2') {
			carta.setNumero(2);
		}
		if(!esFigura) {
			if(nombreCarta.charAt(5) == 'o') {
				carta.setPalo("oros");
			}else if(nombreCarta.charAt(5) == 'c') {
				carta.setPalo("copas");			
			}else if(nombreCarta.charAt(5) == 'e') {
				carta.setPalo("espadas");			
			}else{
				carta.setPalo("bastos");			
			}
		}
		
		return carta;
	}
	
	/**
	 * Método para comprobar si se tiene en mano el número de cartas jugables especificado.
	 * @param jugadores , el array de los jugadores que participan en la partida.
	 * @param numeroCartas , el número de cartas que ha decidido jugar el jugador.
	 * @param pos , la posición del jugador que tiene el turno.
	 * @return un boolean true si tiene las cartas especificadas en la mano, false si no las tiene.
	 */
	
	public static boolean cartasEnMano(Jugador[] jugadores, int numeroCartas, int pos, Carta cartaASuperar) {
		boolean isThere = false;
		
		for(int i = 0; i < jugadores[pos].mano.length && !isThere; i++) {
			int cont = 0;
			for(int j = i; j < jugadores[pos].mano.length && !isThere; j++) {
				if(cartaASuperar.getNumero() == 1) {
					if(((jugadores[pos].mano[i].getNumero() == jugadores[pos].mano[j].getNumero()) || (jugadores[pos].mano[j].getNumero() == 2)) && (jugadores[pos].getMano()[i].getNumero() == 1 || jugadores[pos].getMano()[i].getNumero() == 2)) {
						cont++;
					}
				}else if(cartaASuperar.getNumero() == 2 && cartaASuperar.getPalo() != "oros") {
					if(jugadores[pos].getMano()[i].getNumero() == 2) {
						cont++;
					}
				}else {
					if((jugadores[pos].getMano()[i].getNumero() == jugadores[pos].getMano()[j].getNumero() || jugadores[pos].getMano()[j].getNumero() == 2) && (jugadores[pos].getMano()[i].getNumero() >= cartaASuperar.getNumero() || jugadores[pos].getMano()[i].getNumero() == 2 || jugadores[pos].getMano()[i].getNumero() == 1)) {
						cont++;
					}
				}

				if(cont == numeroCartas) {
					isThere = true;
				}
			}
		}
		
		return isThere;
	}
	
	/**
	 * Método para mostrar por pantalla las cartas en la mano de un jugador dado.
	 * @param jugadores , array de jugadores.
	 * @param pos , posición del jugador actual.
	 */
	
	public static void verMano(Jugador[] jugadores, int pos) {
		System.out.println("");
		System.out.print("Cartas en la Mano: ");
		for(int i = 0; i < jugadores[pos].mano.length; i++) {
			if(jugadores[pos].mano[i] !=null && jugadores[pos].mano[i].getNumero() != 0) {
				System.out.print(jugadores[pos].mano[i].toString());
				if(i < (jugadores[pos].mano.length - 2)) {
					System.out.print(" - ");
				}
			}
		}
	}
	
	/**
	 * Método para ordenar la mano de todos los jugadores.
	 * @param jugadores , El array con los jugadores de la partida.
	 * @return Un nuevo array de jugadores ya con la mano ordenada.
	 */
	
	public static Jugador[] ordenaManos(Jugador[] jugadores) {
		Jugador[] jugadoresNuevo = new Jugador[jugadores.length];
		for(int i = 0; i < jugadores.length; i++) {
			jugadoresNuevo[i] = new Jugador(jugadores.length);
			jugadoresNuevo[i].setNombre(jugadores[i].getNombre());
			jugadoresNuevo[i].setRol(jugadores[i].getRol());
			jugadoresNuevo[i].setMano(ordenaMano(jugadores[i].getMano()));
			jugadoresNuevo[i].setPosicion(jugadores[i].getPosicion());
			
		}
		
		return jugadoresNuevo;
	}
	
	/**
	 * Método para ordenar las cartas de la mano de un jugador, de mayor a menor valor.
	 * @param mano , el array de cartas en la mano de un jugador.
	 * @return el array de cartas ordenado.
	 */
	
	public static Carta[] ordenaMano(Carta[] mano) {
		Carta[] manoNueva = new Carta[mano.length];
		int posMax = 0;
		
		for(int i = 0; i < manoNueva.length;i++) {
			posMax = 0;
			for(int j = 0; j < mano.length; j++) {
				if(mano[j].getNumero() == 2) {
					if(mano[j].getPalo() == "oros") {
						posMax = j;
					}else if(mano[posMax].getPalo() != "oros") {
						posMax = j;
					}
				}else if(mano[j].getNumero() == 1 && mano[posMax].getNumero() != 2) {
					posMax = j;
				}else if(mano[j].getNumero() > mano[posMax].getNumero() && mano[posMax].getNumero() != 2 && mano[posMax].getNumero() != 1) {			
						posMax = j;
				}
			}
			if(mano[posMax].getNumero() != 0) {
				manoNueva[i] = new Carta(mano[posMax].getNumero(), mano[posMax].getPalo());
				mano[posMax].setNumero(0);
			}else {
				manoNueva[i] = new Carta();
			}
		}
		
		return manoNueva;
	}
	
	/**
	 * Método para ordenar los jugadores al inicio de la primera partida, de forma que empiece el poseedor del 3 de bastos.
	 * @param jugadores , el array de jugadores.
	 * @return el array de jugadores ordenado.
	 */
	
	public static Jugador[] ordenaInicio(Jugador[] jugadores) {
		Jugador[] jugadoresOrdenados = new Jugador[jugadores.length];
		boolean isIn = false;
		boolean isDone = false;
		for(int i = 0; i < jugadores.length && !isDone; i++) {
			for(int j = 0; j < jugadores[i].getMano().length && !isIn; j++) {
				if(jugadores[i].getMano()[j].is3DeBastos(jugadores[i].getMano()[j])) {
					isIn = true;
				}
			}
			if(isIn) {
				for(int j = 0; j < jugadores.length; j++) {
					if(i != jugadores.length - 1) {
						jugadoresOrdenados[j] = new Jugador(jugadores.length);
						jugadoresOrdenados[j].setNombre(jugadores[i].getNombre());
						jugadoresOrdenados[j].setRol(jugadores[i].getRol());
						jugadoresOrdenados[j].setMano(jugadores[i].getMano());
						jugadoresOrdenados[j].setPosicion(j + 1);
						i++;
					}else {
						jugadoresOrdenados[j] = new Jugador(jugadores.length);
						jugadoresOrdenados[j].setNombre(jugadores[i].getNombre());
						jugadoresOrdenados[j].setRol(jugadores[i].getRol());
						jugadoresOrdenados[j].setMano(jugadores[i].getMano());
						jugadoresOrdenados[j].setPosicion(j + 1);
						i = 0;
					}
				}
				isDone = true;
				if(i == jugadores.length - 1) {
					i = -1;
				}
			}
		}
		
		return jugadoresOrdenados;
	}
	
	/**
	 * Método para ordenar a los jugadores después de cada ronda.
	 * @param jugadores , el array de los jugadores.
	 * @return el array de jugadores ordenados.
	 */
	
	public static Jugador[] ordenaJugadores(Jugador[] jugadores) {
		Jugador[] jugadoresOrdenados = new Jugador[jugadores.length];
		
		for(int i = 0; i < jugadores.length; i++) {
			jugadoresOrdenados[i] = new Jugador(jugadores.length);
			for(int j = 0; j < jugadores.length; j++) {
				if(jugadores[j].getPosicion() == i+1) {
					jugadoresOrdenados[i].setNombre(jugadores[j].getNombre());
					jugadoresOrdenados[i].setRol(jugadores[j].getRol());
					jugadoresOrdenados[i].setMano(jugadores[j].getMano());
					jugadoresOrdenados[i].setPosicion(jugadores[j].getPosicion());
					jugadoresOrdenados[i].setUltimaJugada(jugadores[j].getUltimaJugada());
				}
			}
		}
		
		return jugadoresOrdenados;
	}
	
	/**
	 * Método para asignar la nueva posición a los jugadores tras una ronda.	
	 * @param jugadores , el array con los jugadores.
	 * @param pos , la posición en la que se encuentra el ganador de la ronda anterior en el array de jugadores.
	 * @return El array de jugadores con las posiciones modificadas.
	 */
	
	public static Jugador[] asignaPosicion(Jugador[] jugadores, int pos) {
		
		for(int i = 0; i < jugadores.length; i++) {
			if(pos + 1 < jugadores.length) {
				jugadores[pos + 1].setPosicion(i + 1);
				if(pos == jugadores.length - 1) {
					pos = 0;
				}else {
					pos++;
				}
			}else {
				pos = -1;
				jugadores[pos + 1].setPosicion(i + 1);
				pos++;
			}
		}
		
		
		return jugadores;
	}
	
	/**
	 * Método para llenar un array de booleanos por defecto en false.
	 * @param arrayDeFlags , el array a llenar.
	 * @return El array de booleanos lleno todo en false.
	 */
	
	public static boolean[] creaFlagArray(boolean[] arrayDeFlags) {
		
		for(int i = 0; i < arrayDeFlags.length; i++) {
			arrayDeFlags[i] = false;
		}
		
		return arrayDeFlags;
	}
	
	/**
	 * Método para inicializar el array de cartas ultimaJugada.
	 * @param jugadores , el array de jugadores.
	 * @return el array de jugadores modificado.
	 */
	
	public static Carta[] creaCartaArray(Jugador[] jugadores, int pos) {
		for(int i = 0; i < jugadores[pos].getUltimaJugada().length; i++) {
			jugadores[pos].getUltimaJugada()[i] = new Carta();
		}
		
		return jugadores[pos].getUltimaJugada();
	}

	
	
	/**
	 * Método que valida que un número sea entero y esté entre dos número dados.
	 * @param msn , Mensaje a mostrar para pedir el número.
	 * @param min , El menor número en el rango a validar.
	 * @param max , El mayor número en el rango a validar.
	 * @return El número entero, entre dos parámetros, validado.
	 */
	
	
	public static int leeEntero(String msn, int min, int max) {
		int numero = 0;
		boolean isCorrect = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println(msn);
			try {
				numero = sc.nextInt();
				if(numero >= min && numero <= max) {
					isCorrect = true;
				}else {
					System.out.println("El número debe estar entre "+min+" y "+max+".");
				}
			}catch(Exception InputMismatchException) {
				System.out.println("Debes introducir un número entero.");
				sc.nextLine();
			}
		}while(!isCorrect);
		
		return numero;
	}

	
	/**
	 * Método para validar que un número sea entero.
	 * @param msn , Mensaje a mostrar para pedir el número.
	 * @return el número validado.
	 */
	
	public static int leeEntero(String msn) {
		int numero = 0;
		boolean isCorrect = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("");
			System.out.print(msn);
			try {
				numero = sc.nextInt();
				isCorrect = true;
			}catch(Exception InputMismatchException) {
				System.out.println("Debes introducir un número entero.");
				sc.nextLine();
			}
		}while(!isCorrect);
		
		return numero;
	}
	
	
	/**
	 * Método para borrar la carta jugada de la mano.
	 * @param jugadores , Recibe el Array de jugadores.
	 * @param pos , La posición del jugador actual.
	 * @param carta , La carta a borrar
	 * @return , Devuelve el array ya modificado.
	 */
	
	
	
	public static Jugador[] sacaCarta(Jugador[] jugadores,int pos, Carta carta) {
		for(int i = 0; i < jugadores[pos].getMano().length; i++) {
		
			if(jugadores[pos].getMano()[i].getNumero() == carta.getNumero()){
				if(jugadores[pos].getMano()[i].getPalo() == carta.getPalo()){
					
					jugadores[pos].getMano()[i].setNumero(0);
					jugadores[pos].getMano()[i].setPalo("");
				}
				
			}
			
		}
		return jugadores;
	}
	
	/**
	 * Método que comprueba si la carta que me han introducido está en la mano del jugador
	 * @param jugadores array con los jugadores
	 * @param pos  posición del jugador actual
	 * @param nombreCarta     String que ha introducido el usuario
	 * @return    el boolean que dice si la carta está en la mano
	 */
	public static boolean cartaEnMano (Jugador[] jugadores, int pos, Carta carta) {
	    boolean sonValidas=false;
	    for (int i=0; i<jugadores[pos].getMano().length; i++){
	        if(jugadores[pos].getMano()[i].getNumero() == carta.getNumero() && jugadores[pos].getMano()[i].getPalo() == carta.getPalo()){
	            sonValidas=true;
	        }
	    }
	    return sonValidas;
	    }

	
	
	/**
	 * método para comprobar cuántos jugadores tienen carta
	 * @param jugadores array de jugadores
	 * @return un entero que es el número de jugadores que aún tienen cartas
	 */
	public static int jugadoresConCarta (Jugador[] jugadores){
	    int n=0;
	    boolean hayCarta=false;
	    for (int i=0; i<jugadores.length; i++){
	        for (int j=0; j<jugadores[i].getMano().length; j++){
	            if(jugadores[i].getMano()[j].getNumero() !=0 ){
	                hayCarta=true;
	            }
	        }
	        if(hayCarta){
	            n++;
	        }
	    }
	    return n;
	}
	/**
	 * Método que cambia las cartas de presidente a culo
	 * @param jugadores  El array de jugadores
	 * @return  El array de jugadores modificado
	 */
	public static Jugador[] cartasAsignadas (Jugador[] jugadores ){
	    Carta[] cartasIntercambiables = new Carta[2];
	    boolean culo = false;
	    boolean presidente = false;
	    int culoPos=0;
	    int presPos=0;
	    Carta carta = new Carta();
	    
	    for (int i=0; i<jugadores.length; i++){
	        if(jugadores[i].getRol() == "culo"){
	            cartasIntercambiables[1].setNumero(jugadores[i].getMano()[0].getNumero());
	            cartasIntercambiables[1].setPalo(jugadores[i].getMano()[0].getPalo());
	            jugadores = sacaCarta(jugadores, i, cartasIntercambiables[1]);
	            culo= true;
	            culoPos=i;
	            if (presidente){
	            	jugadores[presPos].getMano()[0].setNumero(cartasIntercambiables[0].getNumero());
	            	jugadores[presPos].getMano()[0].setPalo(cartasIntercambiables[0].getPalo());
	            }
	        }else if(jugadores[i].getRol() == "presidente"){
	            
	            String cartaPresidente = validaString("Introduce la carta que quieres dar al culo", 1,30);
	            carta = cartaFromString(cartaPresidente);
	            
	            
	            for (int j=0; j<jugadores[i].getMano().length; j++){
	                
	                if(cartaEnMano(jugadores, i, carta)){
	                    cartasIntercambiables[0].setNumero(jugadores[i].getMano()[j].getNumero());
	                    cartasIntercambiables[0].setPalo(jugadores[i].getMano()[j].getPalo());
	                    jugadores = sacaCarta(jugadores, i, cartasIntercambiables[0]);
	                    presidente=true;
	                    presPos=i;
	                } if(culo) {
	                	jugadores[culoPos].getMano()[j].setNumero(cartasIntercambiables[1].getNumero());
	                	jugadores[culoPos].getMano()[j].setPalo(cartasIntercambiables[1].getPalo());
	                }
	            }
	        }
	    }

	    return jugadores;
	}
	
	/**
	 * Método que comprueba si hay cartas en la mano de un jugador.
	 * @param jugadores , el array de jugadores.
	 * @param pos , la posicion del jugador actual.
	 * @return Un boolean en true si hay cartas en la mano del jugador y false si no las hay.
	 */
	
	public static boolean hayCartasEnMano(Jugador[]jugadores, int pos) {
		boolean hayCarta=false;
		for (int i = 0; i < jugadores[pos].getMano().length; i++) {
			if (jugadores[pos].getMano()[i].getNumero()!=0) {
				hayCarta=true;
			}
		}
		return hayCarta;
	}
}
	