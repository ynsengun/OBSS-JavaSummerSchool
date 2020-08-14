package BusReservation;

public class Bus {
	private Destination destination;
	private Passanger passangers[];
	private int currentPassangerCount;
	
	public Bus( Destination destination, int capacity) {
		this.setDestination(destination);
		this.currentPassangerCount = 0;
		this.passangers = new Passanger[capacity];
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	public void insertPassanger( Passanger passanger) {
		if( this.currentPassangerCount >= this.passangers.length) {
			System.out.println("Bus is full");
		} else if( passanger != null && passanger.getDestination() == this.destination) {
			System.out.println("Passanger inserted");
			this.currentPassangerCount++;
		} else {
			System.out.println("Destinations do not match");
		}
	}
}
