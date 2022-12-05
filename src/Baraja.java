
public class Baraja {

	private Carta[] cartas = new Carta[40];
	
	
	
	/**
	 * Constructor por defecto de la clase Baraja.
	 * Está programado para generar una baraja española por si mismo.
	 */
	
	public Baraja() {
		
		for(int i = 0; i < 4; i++) {
			
			String paloCarta = "";
			int k = 0;
			
			if(i == 0) {
				paloCarta = "oros";
			}else if(i == 1) {
				paloCarta = "copas";
				k = 10;
			}else if(i == 2) {
				paloCarta = "espadas";
				k = 20;
			}else {
				paloCarta = "bastos";
				k = 30;
			}
			
			for(int j = 1; j <= 10; j++) {
				
				cartas[k] = new Carta(j, paloCarta);
				k++;
			}
		}
	}
	
	/**
	 * Getter para el atributo cartas de la clase Baraja.
	 * @return Un array de objetos de clase Carta, que sería la baraja.
	 */
	
	public Carta[] getCartas() {
		return cartas;
	}
	
	/**
	 * Setter para el atributo cartas de la clase Baraja.
	 * @param cartas , las cartas en la baraja.
	 */
	
	public void setCartas(Carta[] cartas) {
		this.cartas = cartas;
	}
	
	/**
	 * Método para barajar un mazo de cartas.
	 * @param sinBarajar , el array de objetos de clase Carta que representa el mazo a barajar.
	 * @return Un array de objetos de clase Carta, que representa el mazo ya barajado.
	 */
	
	public static Carta[] shuffle(Carta[] sinBarajar) {
		System.out.println("Barajando...");
		
		Carta[] barajada = new Carta[sinBarajar.length];
		
		int pos = 0;										//pos , variable auxiliar que recogerá el numero aleatorio que marcará la posición a modificar del array sinBarajar.
		
		for(int i = 0; i < barajada.length; i++) {
			barajada[i] = new Carta();
			pos = (int) (Math.random() * (39 - 0)) + 0;
			if(sinBarajar[pos].getNumero() != 0) {
				barajada[i].setNumero(sinBarajar[pos].getNumero());
				barajada[i].setPalo(sinBarajar[pos].getPalo());
				sinBarajar[pos].setNumero(0);
			}else if(pos != 39){
				boolean isDone = false;
				for(int j = pos; j < 39 && !isDone; j++) {
					pos++;
					if(sinBarajar[pos].getNumero() != 0) {
						isDone = true;
						barajada[i].setNumero(sinBarajar[pos].getNumero());
						barajada[i].setPalo(sinBarajar[pos].getPalo());
						sinBarajar[pos].setNumero(0);
					}
				}
				if(!isDone) {
					for(int j = pos; j >= 0 && !isDone; j--) {
						pos--;
						if(sinBarajar[pos].getNumero() != 0) {
							isDone = true;
							barajada[i].setNumero(sinBarajar[pos].getNumero());
							barajada[i].setPalo(sinBarajar[pos].getPalo());
							sinBarajar[pos].setNumero(0);
						}
					}
				}
			}else {
				boolean isDone = false;
				for(int j = pos; j >= 0 && !isDone; j--) {
					pos--;
					if(sinBarajar[pos].getNumero() != 0) {
						isDone = true;
						barajada[i].setNumero(sinBarajar[pos].getNumero());
						barajada[i].setPalo(sinBarajar[pos].getPalo());
						sinBarajar[pos].setNumero(0);
					}
				}
			}
		}
		
		return barajada;
	}
	
	/**
	 * Método que reorganiza el array de cartas cuando sacas la de la 1º posición.
	 * @param anterior , Array de objetos de clase Cartadel que se acaba de sustraer la 1º y se desea reorganizar.
	 * @return El nuevo array de objetos de clase Carta, ya reorganizado.
	 */
	
	public static Carta[] reordenaBaraja(Carta[] baraja) {
		
		for(int i = 1; i < baraja.length; i++) {
			if(baraja[i].getNumero() != 0) {
				baraja[(i - 1)].setNumero(baraja[i].getNumero());
				baraja[(i - 1)].setPalo(baraja[i].getPalo());
			}else {
				baraja[(i - 1)].setNumero(0);
			}
		}
		baraja[39].setNumero(0);
		
		return baraja;
	}
	
	public static Carta sacaCarta(Carta[] baraja) {
		Carta carta = new Carta(baraja[0].getNumero(), baraja[0].getPalo());
		
		return carta;
	}
}
