package logic;

public class Ship {
	private String shipName;
	private String url;
	private int size;
	
	public Ship(String shipName) {
			
			switch (shipName) {
			case "Speedboat":
				url = "Speedboat.png";
				size = 2;
				break;
			case "Yacht":
				url = "Yacht.png";
				size = 3;
				break;
			case "Cruise":
				url = "Cruise.png";
				size = 4;
				break;
			default:
				url = "Container Ship.png";
				size = 5;
							}
			this.shipName = shipName;
			
	}

	public String getShipName() {
		return shipName;
	}

	public String getUrl() {
		return url;
	}

	public int getSize() {
		return size;
	}
}