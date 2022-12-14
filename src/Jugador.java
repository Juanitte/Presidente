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
		for(int i = 0; i < ultimaJugada.length; i++) {
			this.ultimaJugada[i] = new Carta();
		}
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
	 * Método que muestra un menú y recoge la opción introducida por el jugador para actuar en consecuencia.
	 * @param jugadores , el array de jugadores.
	 * @param pos , la posicion del jugador actual.
	 * @param isOver , el array de flags.
	 * @param isFirstTurn , un boolean que indica si es el primer turno.
	 * @param cartaASuperar , la carta a superar en este turno.
	 * @return el array de booleanos modificado.
	 */
	
	public static boolean[] options(Jugador[] jugadores,int pos, boolean[] isOver, boolean isFirstTurn, Carta cartaASuperar) {
		
		// En isOver[0] se controlará el fin de turno.
		
		// En isOver[1] se controlará el fin de ronda.
		
		// En isOver[2] se controlará el fin de partida.
		
		// En isOver[3] se controlará el fin del juego.
		
		// En isOver[4] se controlará si el jugador ha pasado turno.
		do {	
			System.out.println("");
			System.out.print("TURNO DE "+jugadores[pos].nombre.toUpperCase()+".");
			System.out.println("");
			System.out.println("1 - Jugar.");
			System.out.println("2 - Pasar.");
			System.out.println("3 - Ver Mano.");
			
			int opt = Jugador.leeEntero("", 1, 3);
			
			switch(opt) {
				case 1:
					//Aquí además de realizar las acciones de la opción jugar, llena un array con las cartas que se han jugado.
					if(isFirstTurn) {
						verMano(jugadores, pos);
						System.out.println("");
						jugadores = numeroCartas(jugadores, pos, cartaASuperar);
					}else {
						jugadores = numCartasRonda(jugadores);
					}
					if(isFirstTurn || numCartasCorrecto(jugadores, pos, cartaASuperar)) {
						if(!isFirstTurn) {
							verMano(jugadores, pos);
							System.out.println("");
						}
						jugadores = Jugador.opcionJugar(jugadores, pos, isFirstTurn, cartaASuperar);
						isOver[0] = true;
						isOver[4] = false;
						if((isFirstTurn && !numCartasCorrecto(jugadores, pos, cartaASuperar)) || jugadores[pos].getNumeroCartasJugadas() == 0 || jugadores[pos].getUltimaJugada()[0].getNumero() == 0) {
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
						isOver[0] = false;
						isOver[4] = false;
					}
					break;
				case 3:
					Jugador.verMano(jugadores, pos);
					isOver[0] = false;
					isOver[4] = false;
			}
		}while(!isOver[0]);
		
		return isOver;
	}
	
	/**
	 * Método para settear el número de cartas jugadas por el jugador.
	 * @param jugadores , el array de jugadores.
	 * @param pos , la posición del jugador actual en el array.
	 * @return el array de jugadores modificado.
	 */
	
	public static Jugador[] numeroCartas(Jugador[] jugadores, int pos, Carta cartaASuperar) {

		System.out.println("");
		jugadores[pos].setNumeroCartasJugadas(leeEntero("Introduce el número de cartas a jugar o 0 para volver al menú: ", 0, 8));
		
		if(jugadores[pos].getNumeroCartasJugadas() == 0) {
			System.out.println("Volviendo...");
		}else if(!numCartasCorrecto(jugadores, pos, cartaASuperar)) {
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
	 * @param jugadores , el array de jugadores.
	 * @param pos , la posicion del jugador actual.
	 * @param isFirstTurn , boolean true si es el primer turno de la ronda.
	 * @param cartaASuperar , la ultima carta jugada, con el valor a igualar o superar.
	 * @return el array de jugadores modificado.
	 */
	
	public static Jugador[] opcionJugar(Jugador[] jugadores, int pos, boolean isFirstTurn, Carta cartaASuperar) {
		System.out.println("");
		String nombreCarta = "";
		boolean areThere = false;
		Carta carta = new Carta();
		int auxCartas = 0;
		Carta cartaJugada = new Carta();
		boolean isCero = false;
		
		
		
		if(!isFirstTurn || numCartasCorrecto(jugadores, pos, cartaASuperar)) {
		
			
			
			for(int i = 0; i < jugadores[pos].getNumeroCartasJugadas() && !isCero; i++) {
				boolean isCorrect = false;
				isCero = false;
				do {
					boolean isValid = false;
					auxCartas = 0;
					boolean isOK = false;
					do {
						isOK = false;
						int min = 1;
						System.out.println("");
						System.out.print("Introduce la "+(i + 1)+"ª carta a jugar (0 para volver al menú) : ");
						nombreCarta = validaString("", min, 50);
						if(nombreCarta.charAt(0) == '0') {
							isCero = true;
							isCorrect = true;
							min = 1;
							isOK = true;
						}else {
							min = setMin(nombreCarta);
						}
						if(!isCero) {
							if(nombreCarta.length() >= min) {
								isValid = true;
							}else {
								System.out.println("Debes introducir un nombre de carta válido.");
							}
							if(isValid) {
								isOK = compruebaValores(cartaASuperar, nombreCarta);
								if(!isOK) {
									System.out.println("La carta introducida no es del valor adecuado.");
								}
							}
						}
						
					}while(!isOK);
					if(!isCero) {
						cartaJugada = cartaFromString(nombreCarta);
						isCorrect = cartaEnMano(jugadores, pos, cartaJugada);
						if(isCorrect) {
							carta = cartaFromString(nombreCarta);
						}
						if(isCorrect && !areThere) {
							for(int j = 0; j < jugadores[pos].mano.length; j++) {
								if(jugadores[pos].mano[j].getNumero() == carta.getNumero() || jugadores[pos].getMano()[j].getNumero() == 2) {
									auxCartas++;
								}
							}
							if(auxCartas < jugadores[pos].getNumeroCartasJugadas()) {
								System.out.println("No tienes suficientes cartas del valor especificado en la mano.");
								isCorrect = false;
							}else {
								areThere = true;
							}
						}else if(!isCorrect && !areThere){
							System.out.println("El nombre introducido no coincide con ninguna carta de tu mano.");
						}
					}
				}while(!isCorrect);
				jugadores[pos].getUltimaJugada()[i] = new Carta();
				if(!isCero) {
					jugadores[pos].getUltimaJugada()[i].setNumero(carta.getNumero());
					jugadores[pos].getUltimaJugada()[i].setPalo(carta.getPalo());
					jugadores = sacaCarta(jugadores, pos, carta);
				}
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
		if(cartaASuperar.getNumero() == 2) {
			if(cartaASuperar.getPalo() != "oros") {
				if(carta.getNumero() == 2) {
					isOK = true;
				}
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
		
		Carta carta = new Carta(2, "");
		boolean isDone = false;
		
		for(int i = 0; i < jugadores[pos].getUltimaJugada().length; i++) {
			if(jugadores[pos].getUltimaJugada()[i].getNumero() !=2 && jugadores[pos].getUltimaJugada()[i].getNumero() !=0) {
				carta.setNumero(jugadores[pos].getUltimaJugada()[i].getNumero());
				carta.setPalo(jugadores[pos].getUltimaJugada()[i].getPalo());
			}
		}
		if(carta.getNumero() == 2) {
			for(int j = 0; j < jugadores[pos].getUltimaJugada().length && !isDone; j++) {
				if(jugadores[pos].getUltimaJugada()[j].getNumero() == 2) {
					carta.setPalo(jugadores[pos].getUltimaJugada()[j].getPalo());
					if(jugadores[pos].getUltimaJugada()[j].getPalo() == "oros") {
						carta.setPalo(jugadores[pos].getUltimaJugada()[j].getPalo());
						isDone = true;
					}
				}
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
	
	public static boolean jugadasIguales(Jugador[] jugadores, int pos, int contPasa, boolean[] isOver) {
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
					if(jugadores[(jugadores.length) - (contPasa - pos)].getUltimaJugada()[i].getNumero() != 2 && jugadores[(jugadores.length) - (contPasa - pos)].getUltimaJugada()[i].getNumero() != 0) {
						ant = jugadores[(jugadores.length) - (contPasa - pos)].getUltimaJugada()[i].getNumero();
					}
				}
			}
		if(!isOver[1]) {
			if(act == ant) {
				isEqual = true;
			}
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
				jugadores[i].ultimaJugada[j] = new Carta();
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
		}else if(nombreCarta.charAt(0) == 'S' || nombreCarta.charAt(0) == 's') {
			carta.setNumero(8);
			esFigura = true;
			if(nombreCarta.charAt(8) == 'o' || nombreCarta.charAt(8) == 'O') {
				carta.setPalo("oros");
			}else if(nombreCarta.charAt(8) == 'c' || nombreCarta.charAt(8) == 'C') {
				carta.setPalo("copas");			
			}else if(nombreCarta.charAt(8) == 'e' || nombreCarta.charAt(8) == 'E') {
				carta.setPalo("espadas");			
			}else{
				carta.setPalo("bastos");			
			}
		}else if(nombreCarta.charAt(0) == 'C' || nombreCarta.charAt(0) == 'c') {
			carta.setNumero(9);
			esFigura = true;
			if(nombreCarta.charAt(11) == 'o' || nombreCarta.charAt(11) == 'O') {
				carta.setPalo("oros");
			}else if(nombreCarta.charAt(11) == 'c' || nombreCarta.charAt(11) == 'C') {
				carta.setPalo("copas");			
			}else if(nombreCarta.charAt(11) == 'e' || nombreCarta.charAt(11) == 'E') {
				carta.setPalo("espadas");			
			}else{
				carta.setPalo("bastos");			
			}
		}else if(nombreCarta.charAt(0) == 'R' || nombreCarta.charAt(0) == 'r') {
			carta.setNumero(10);
			esFigura = true;
			if(nombreCarta.charAt(7) == 'o' || nombreCarta.charAt(7) == 'O') {
				carta.setPalo("oros");
			}else if(nombreCarta.charAt(7) == 'c' || nombreCarta.charAt(7) == 'C') {
				carta.setPalo("copas");			
			}else if(nombreCarta.charAt(7) == 'e' || nombreCarta.charAt(7) == 'E') {
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
			if(nombreCarta.charAt(5) == 'o' || nombreCarta.charAt(5) == 'O') {
				carta.setPalo("oros");
			}else if(nombreCarta.charAt(5) == 'c' || nombreCarta.charAt(5) == 'C') {
				carta.setPalo("copas");			
			}else if(nombreCarta.charAt(5) == 'e' || nombreCarta.charAt(5) == 'E') {
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
			for(int j = 0; j < jugadores[pos].mano.length && !isThere; j++) {
				if(cartaASuperar.getNumero() == 1) {
					if(((jugadores[pos].mano[i].getNumero() == jugadores[pos].mano[j].getNumero()) || (jugadores[pos].mano[j].getNumero() == 2)) && (jugadores[pos].getMano()[j].getNumero() == 1 || jugadores[pos].getMano()[j].getNumero() == 2)) {
						cont++;
					}else if(jugadores[pos].getMano()[i].getNumero() == 2) {
						if(jugadores[pos].getMano()[j].getNumero() == 2) {
							cont++;
						}else {
							if(jugadores[pos].getMano()[j].getNumero() == jugadores[pos].getMano()[i].getNumero()) {
								cont++;
							}	
						}
					}
				}else if(cartaASuperar.getNumero() == 2) {
					if(cartaASuperar.getPalo() != "oros") {
						if(jugadores[pos].getMano()[j].getNumero() == 2) {
							cont++;
						}
					}
				}else {
					if(jugadores[pos].getMano()[i].getNumero() == 2) {
						if(jugadores[pos].getMano()[j].getNumero() == 2) {
							cont++;
						}else {
							if(jugadores[pos].getMano()[j].getNumero()  == jugadores[pos].getMano()[i].getNumero()) {
								cont++;
							}
						}
					}else if((jugadores[pos].getMano()[i].getNumero() == jugadores[pos].getMano()[j].getNumero() || jugadores[pos].getMano()[j].getNumero() == 2) && (jugadores[pos].getMano()[j].getNumero() >= cartaASuperar.getNumero() || jugadores[pos].getMano()[j].getNumero() == 2 || jugadores[pos].getMano()[j].getNumero() == 1)) {
						cont++;
					}
				}

				if(cont >= numeroCartas) {
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
			jugadoresNuevo[i].setNumeroCartasJugadas(jugadores[i].getNumeroCartasJugadas());
			jugadoresNuevo[i].setUltimaJugada(jugadores[i].getUltimaJugada());
			
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
		boolean is2deOros = false;
		
		for(int i = 0; i < manoNueva.length;i++) {
			posMax = 0;
			for(int j = 0; j < mano.length; j++) {
				if(mano[j].getNumero() == 2) {
					if(mano[j].getPalo() == "oros") {
						posMax = j;
						is2deOros = true;
					}else if(!is2deOros){
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
				mano[posMax].setPalo("");
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

					Carta[] ultimaJugada = new Carta[8];
					if(i != jugadores.length - 1) {
						jugadoresOrdenados[j] = new Jugador(jugadores.length);
						jugadoresOrdenados[j].setNombre(jugadores[i].getNombre());
						jugadoresOrdenados[j].setRol(jugadores[i].getRol());
						jugadoresOrdenados[j].setMano(jugadores[i].getMano());
						jugadoresOrdenados[j].setPosicion(j + 1);
						jugadoresOrdenados[j].setNumeroCartasJugadas(jugadores[i].getNumeroCartasJugadas());
						jugadoresOrdenados[j].setUltimaJugada(ultimaJugada);
						for(int k = 0; k < jugadores[i].getUltimaJugada().length; k++) {
							jugadoresOrdenados[j].getUltimaJugada()[k] = new Carta(jugadores[i].getUltimaJugada()[k].getNumero(), jugadores[i].getUltimaJugada()[k].getPalo());
						}
						i++;
					}else {
						jugadoresOrdenados[j] = new Jugador(jugadores.length);
						jugadoresOrdenados[j].setNombre(jugadores[i].getNombre());
						jugadoresOrdenados[j].setRol(jugadores[i].getRol());
						jugadoresOrdenados[j].setMano(jugadores[i].getMano());
						jugadoresOrdenados[j].setPosicion(j + 1);
						jugadoresOrdenados[j].setNumeroCartasJugadas(jugadores[i].getNumeroCartasJugadas());
						jugadoresOrdenados[j].setUltimaJugada(ultimaJugada);
						for(int k = 0; k < jugadores[i].getUltimaJugada().length; k++) {
							jugadoresOrdenados[j].getUltimaJugada()[k] = new Carta(jugadores[i].getUltimaJugada()[k].getNumero(), jugadores[i].getUltimaJugada()[k].getPalo());
						}
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
		Carta[] ultimaJugada = new Carta[8];
		
		for(int i = 0; i < ultimaJugada.length; i++) {
			ultimaJugada[i] = new Carta();
		}
		
		for(int i = 0; i < jugadores.length; i++) {
			jugadoresOrdenados[i] = new Jugador(jugadores.length);
			for(int j = 0; j < jugadores.length; j++) {
				if(jugadores[j].getPosicion() == i+1) {
					jugadoresOrdenados[i].setNombre(jugadores[j].getNombre());
					jugadoresOrdenados[i].setRol(jugadores[j].getRol());
					jugadoresOrdenados[i].setMano(jugadores[j].getMano());
					jugadoresOrdenados[i].setPosicion(jugadores[j].getPosicion());
					for(int k = 0; k < jugadores[i].getUltimaJugada().length; k++) {
						jugadoresOrdenados[i].getUltimaJugada()[k].setNumero(jugadores[j].getUltimaJugada()[k].getNumero());
						jugadoresOrdenados[i].getUltimaJugada()[k].setPalo(jugadores[j].getUltimaJugada()[k].getPalo());
					}
					jugadoresOrdenados[i].setNumeroCartasJugadas(jugadores[j].getNumeroCartasJugadas());
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
	 * @return el array de cartas con la ultima jugada.
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
	public static Jugador[] cartasAsignadas (Jugador[] jugadores, boolean thereIsVice){
		Carta[] cartasIntercambiables = new Carta[2];
		Carta[] cartasIntercambiablesAux = new Carta[4];
	    int culoPos=0;
	    int presPos=0;
	    int viceCuloPos = 0;
	    int vicePresPos = 0;
	    Carta carta = new Carta();
	    boolean isDone = false;
	    for(int i = 0; i < cartasIntercambiables.length; i++) {
	    	cartasIntercambiables[i] = new Carta();
	    }
	    for(int i = 0; i < cartasIntercambiablesAux.length; i++) {
	    	cartasIntercambiablesAux[i] = new Carta();
	    }
	    if(!thereIsVice) {
		    for (int i=0; i<jugadores.length; i++){
		        if(jugadores[i].getRol() == "culo"){
		            cartasIntercambiables[0].setNumero(jugadores[i].getMano()[0].getNumero());
		            cartasIntercambiables[0].setPalo(jugadores[i].getMano()[0].getPalo());
		            jugadores = sacaCarta(jugadores, i, cartasIntercambiables[0]);
		            culoPos=i;
		            System.out.println("");
		            System.out.println("El culo le ha dado un "+cartasIntercambiables[0].toString()+" al presidente.");
		        }else if(jugadores[i].getRol() == "presidente"){
		        	verMano(jugadores, i);
		            do {
		            	System.out.println("");
			            String cartaPresidente = validaString("Introduce la carta que quieres dar al culo :", 1,30);
			            System.out.println("");
			            carta = cartaFromString(cartaPresidente);
			                
			                if(cartaEnMano(jugadores, i, carta)){
			                    cartasIntercambiables[1].setNumero(carta.getNumero());
			                    cartasIntercambiables[1].setPalo(carta.getPalo());
			                    jugadores = sacaCarta(jugadores, i, cartasIntercambiables[1]);
			                    presPos=i;
			                    isDone = true;
			                }else {
			                	System.out.println("Introduce una carta válida.");
			                	isDone = false;
			                }
		            }while(!isDone);
		            isDone = false;
		        }
		    }
	    }else {
	    	for (int i=0; i<jugadores.length; i++){
		        if(jugadores[i].getRol() == "culo"){
		        	cartasIntercambiablesAux[2].setNumero(jugadores[i].getMano()[0].getNumero());
		            cartasIntercambiablesAux[2].setPalo(jugadores[i].getMano()[0].getPalo());
		            cartasIntercambiablesAux[3].setNumero(jugadores[i].getMano()[1].getNumero());
		            cartasIntercambiablesAux[3].setPalo(jugadores[i].getMano()[1].getPalo());
		            jugadores = sacaCarta(jugadores, i, cartasIntercambiablesAux[2]);
		            jugadores = sacaCarta(jugadores, i, cartasIntercambiablesAux[3]);
		            culoPos=i;
		            System.out.println("");
		            System.out.println("El culo le ha dado un "+cartasIntercambiablesAux[2].toString()+" y un "+cartasIntercambiablesAux[3].toString()+" al presidente.");
		            
		        }else if(jugadores[i].getRol() == "viceculo"){
		            cartasIntercambiables[0].setNumero(jugadores[i].getMano()[0].getNumero());
		            cartasIntercambiables[0].setPalo(jugadores[i].getMano()[0].getPalo());
		            jugadores = sacaCarta(jugadores, i, cartasIntercambiables[0]);
		            viceCuloPos=i;
		            System.out.println("");
		            System.out.println("El viceculo le ha dado un "+cartasIntercambiables[0].toString()+" al presidente.");
		           
		        }else if(jugadores[i].getRol() == "presidente"){

		            for(int k = 2; k < 4; k++) {
		            	do {
				        	verMano(jugadores, i);
				        	System.out.println("");
				            String cartaPresidente = validaString("Introduce la "+(k - 1)+"º carta que quieres dar al culo", 1,30);
				            System.out.println("");
				            carta = cartaFromString(cartaPresidente);
				                
				                if(cartaEnMano(jugadores, i, carta)){
				                    cartasIntercambiablesAux[k].setNumero(carta.getNumero());
				                    cartasIntercambiablesAux[k].setPalo(carta.getPalo());
				                    jugadores = sacaCarta(jugadores, i, cartasIntercambiablesAux[k]);
				                    presPos=i;
				                    isDone = true;
				                }else {
				                	System.out.println("Introduce una carta válida.");
				                }
		            	}while(!isDone);
		            	isDone = false;
		            }
		        }else if(jugadores[i].getRol() == "vicepresidente"){
		        	do {
			        	verMano(jugadores, i);
			        	System.out.println("");
				        String cartaPresidente = validaString("Introduce la carta que quieres dar al viceculo", 1,30);
				        System.out.println("");
				        carta = cartaFromString(cartaPresidente);
				                
				            if(cartaEnMano(jugadores, i, carta)){
				                cartasIntercambiables[1].setNumero(carta.getNumero());
				                cartasIntercambiables[1].setPalo(carta.getPalo());
				                jugadores = sacaCarta(jugadores, i, cartasIntercambiables[1]);
				                presPos=i;
				                isDone = true;
				            }else {
				            	System.out.println("Introduce una carta válida.");
				            }
		        	}while(!isDone);
		        	isDone = false;
		        }
		    }
	    }
	    if(!thereIsVice) {
	    	for(int i = 0; i < jugadores[presPos].getMano().length && !isDone; i++) {
	    		if(jugadores[presPos].getMano()[i].getNumero() == 0) {
			    	jugadores[presPos].getMano()[i].setNumero(cartasIntercambiables[0].getNumero());
			    	jugadores[presPos].getMano()[i].setPalo(cartasIntercambiables[0].getPalo());
			    	isDone = true;
	    		}
	    	}
	    	isDone = false;
	    	for(int i = 0; i < jugadores[culoPos].getMano().length && !isDone; i++) {
	    		if(jugadores[culoPos].getMano()[i].getNumero() == 0) {
	    			jugadores[culoPos].getMano()[i].setNumero(cartasIntercambiables[1].getNumero());
                	jugadores[culoPos].getMano()[i].setPalo(cartasIntercambiables[1].getPalo());
                	isDone = true;
	    		}
	    	}
	    	isDone = false;
	    }else {
	    	for(int i = 0; i < jugadores[presPos].getMano().length && !isDone; i++) {
	    		if(jugadores[presPos].getMano()[i].getNumero() == 0) {
			    	jugadores[presPos].getMano()[i].setNumero(cartasIntercambiablesAux[0].getNumero());
			    	jugadores[presPos].getMano()[i].setPalo(cartasIntercambiablesAux[0].getPalo());
			    	isDone = true;
	    		}
	    	}
	    	isDone = false;
	    	for(int i = 0; i < jugadores[presPos].getMano().length && !isDone; i++) {
	    		if(jugadores[presPos].getMano()[i].getNumero() == 0) {
			    	jugadores[presPos].getMano()[i].setNumero(cartasIntercambiablesAux[1].getNumero());
			    	jugadores[presPos].getMano()[i].setPalo(cartasIntercambiablesAux[1].getPalo());
			    	isDone = true;
	    		}
	    	}
	    	isDone = false;
	    	for(int i = 0; i < jugadores[vicePresPos].getMano().length && !isDone; i++) {
	    		if(jugadores[vicePresPos].getMano()[i].getNumero() == 0) {
			    	jugadores[vicePresPos].getMano()[i].setNumero(cartasIntercambiables[0].getNumero());
			    	jugadores[vicePresPos].getMano()[i].setPalo(cartasIntercambiables[0].getPalo());
			    	isDone = true;
	    		}
	    	}	    	
	    	isDone = false;
	    	for(int i = 0; i < jugadores[viceCuloPos].getMano().length && !isDone; i++) {
	    		if(jugadores[viceCuloPos].getMano()[i].getNumero() == 0) {
			    	jugadores[viceCuloPos].getMano()[i].setNumero(cartasIntercambiables[1].getNumero());
			    	jugadores[viceCuloPos].getMano()[i].setPalo(cartasIntercambiables[1].getPalo());
			    	isDone = true;
	    		}
	    	}	    	
	    	isDone = false;
	    	for(int i = 0; i < jugadores[culoPos].getMano().length && !isDone; i++) {
	    		if(jugadores[culoPos].getMano()[i].getNumero() == 0) {
	    			jugadores[culoPos].getMano()[i].setNumero(cartasIntercambiablesAux[2].getNumero());
                	jugadores[culoPos].getMano()[i].setPalo(cartasIntercambiablesAux[2].getPalo());
                	isDone = true;
	    		}
	    	}
	    	isDone = false;
	    	for(int i = 0; i < jugadores[culoPos].getMano().length && !isDone; i++) {
	    		if(jugadores[culoPos].getMano()[i].getNumero() == 0) {
	    			jugadores[culoPos].getMano()[i].setNumero(cartasIntercambiablesAux[3].getNumero());
                	jugadores[culoPos].getMano()[i].setPalo(cartasIntercambiablesAux[3].getPalo());
                	isDone = true;
	    		}
	    	}
	    	isDone = false;
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
	
	/**
	 * Método para reordenar a los jugadores con el Culo en primera posición.
	 * @param jugadores , el array de jugadores.
	 * @return el array de jugadores reordenado.
	 */
	
	public static Jugador[] ordenaConRoles(Jugador[] jugadores) {
		Jugador[] jugadoresOrdenados = new Jugador[jugadores.length];
		int pos = 0;
		boolean isDone = false;
			for(int j = 0; j < jugadores.length && !isDone; j++) {
				if(jugadores[j].getRol() == "culo") {
					jugadoresOrdenados[0] = new Jugador(jugadores.length);
					jugadoresOrdenados[0].setMano(jugadores[j].getMano());
					jugadoresOrdenados[0].setNombre(jugadores[j].getNombre());
					jugadoresOrdenados[0].setNumeroCartasJugadas(jugadores[j].getNumeroCartasJugadas());
					jugadoresOrdenados[0].setPosicion(jugadores[j].getPosicion());
					jugadoresOrdenados[0].setRol(jugadores[j].getRol());
					jugadoresOrdenados[0].setUltimaJugada(jugadores[j].getUltimaJugada());
					if(j == jugadores.length - 1) {
						pos = 0;
						for(int i = 1; i < jugadores.length; i++) {
							jugadoresOrdenados[i] = new Jugador(jugadores.length);
							jugadoresOrdenados[i].setMano(jugadores[pos].getMano());
							jugadoresOrdenados[i].setNombre(jugadores[pos].getNombre());
							jugadoresOrdenados[i].setNumeroCartasJugadas(jugadores[pos].getNumeroCartasJugadas());
							jugadoresOrdenados[i].setPosicion(jugadores[pos].getPosicion());
							jugadoresOrdenados[i].setRol(jugadores[pos].getRol());
							jugadoresOrdenados[i].setUltimaJugada(jugadores[pos].getUltimaJugada());
							pos++;
						}
						isDone = true;
					}else {
						pos = j + 1;
						for(int i = 1; i < jugadores.length; i++) {
							jugadoresOrdenados[i] = new Jugador(jugadores.length);
							jugadoresOrdenados[i].setMano(jugadores[pos].getMano());
							jugadoresOrdenados[i].setNombre(jugadores[pos].getNombre());
							jugadoresOrdenados[i].setNumeroCartasJugadas(jugadores[pos].getNumeroCartasJugadas());
							jugadoresOrdenados[i].setPosicion(jugadores[pos].getPosicion());
							jugadoresOrdenados[i].setRol(jugadores[pos].getRol());
							jugadoresOrdenados[i].setUltimaJugada(jugadores[pos].getUltimaJugada());
							if(pos == jugadores.length - 1) {
								pos = 0;
							}else {
								pos++;
							}
						}
						isDone = true;
					}
				}
			}
		
		return jugadoresOrdenados;
	}
	
	/**
	 * Método que cuenta las cartas en la mano del jugador actual.
	 * @param jugadores , el array de jugadores.
	 * @param pos , la posicion del jugador actual.
	 * @return un int con la cantidad de cartas en la mano del jugador.
	 */
	
	public static int numeroCartasEnMano(Jugador[] jugadores, int pos) {
		int count = 0;
		for(int i = 0; i < jugadores[pos].getMano().length; i++) {
			if(jugadores[pos].getMano()[i].getNumero() != 0) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Método que limpia los roles de los jugadores.
	 * @param jugadores , el array de jugadores.
	 * @return el array de jugadores modificado.
	 */
	
	public static Jugador[] limpiaRoles(Jugador[] jugadores) {
		
		for(int i = 0; i < jugadores.length; i++) {
			jugadores[i].setRol("");
		}
		
		return jugadores;
	}
	
	public static Jugador[] limpiaMano(Jugador[] jugadores, int pos) {
		
		for(int i = 0; i < jugadores[pos].getMano().length; i++) {
			jugadores[pos].getMano()[i].setNumero(0);
			jugadores[pos].getMano()[i].setPalo("");
		}
		
		return jugadores;
	}
	
	public static boolean[] terminaPartida(boolean[] isOver, String msn) {
		
		boolean isDone = false;
		String result = "";
		
		while(!isDone) {
			result = validaString(msn, 2, 2);
			
			if(result.charAt(0) == 'N' || result.charAt(0) == 'n') {
				System.out.println("Espero que lo hayais pasado bien.");
				System.out.println("Cerrando programa...");
				isOver[3] = true;
				isDone = true;
			}else if(result.charAt(0) == 'S' || result.charAt(0) == 's') {
				isOver[3] = false;
				isDone = true;
			}else {
				System.out.println("Introduce una respuesta valida.");
				isDone = false;
			}
		}
		
		return isOver;
	}
}

	