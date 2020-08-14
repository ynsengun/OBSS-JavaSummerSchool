package BusReservation;

public class Passanger {
	private String name;
	private Destination destination;
	
	public Passanger(String name, Destination destination) {
		this.setName(name);
		this.setDestination(destination);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		if(destination == null)
			destination = Destination.DEFAULT;
		this.destination = destination;
	}
	
	
}
