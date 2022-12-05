
public class Carta {

	private int numero;
	private String palo;
	
	
	/**
	 * Constructor full equip para la clase Carta.
	 * @param numero , el numero de la carta.
	 * @param palo , el palo de la carta.
	 */
	
	public Carta(int numero, String palo) {
		this.numero = numero;
		this.palo = palo;
	}
	
	
	/**
	 * Constructor por defecto para la clase Carta.
	 */
	
	public Carta() {
		this.numero = 0;
		this.palo = "";
	}

	/**
	 * Getter para el atributo numero de la clase Carta.
	 * @return el número de la carta.
	 */
	
	public int getNumero() {
		return numero;
	}

	/**
	 * Setter para el atributo numero de la clase Carta.
	 * @param numero , el número de la carta.
	 */
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Getter para el atributo palo de la clase Carta.
	 * @return el palo de la carta.
	 */
	
	public String getPalo() {
		return palo;
	}

	/**
	 * Setter para el atributo palo de la clase Carta.
	 * @param palo , el palo de la carta.
	 */
	
	public void setPalo(String palo) {
		this.palo = palo;
	}
	
	/**
	 * Método que comprueba que una carta sea el 3 de bastos.
	 * @param carta , carta a comprobar.
	 * @return un boolean true si es el 3 de bastos y false si no lo es.
	 */
	
	public boolean is3DeBastos(Carta carta) {
		boolean isThis = false;
		
		if(carta.getNumero() == 3 && carta.getPalo() == "bastos") {
			isThis = true;
		}
		
		return isThis;
	}
	
	/**
	 * Sobreescritura del método toString para que muestre el nombre de la carta.
	 */
	
	public String toString() {
		String card = numero+" de "+palo;
		if(numero == 8) {
			card = "Sota de "+palo;
		}else if(numero == 9) {
			card = "Caballo de "+palo;
		}else if(numero == 10) {
			card = "Rey de "+palo;
		}
		
		return card;
	}

	
}
