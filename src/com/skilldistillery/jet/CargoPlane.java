package com.skilldistillery.jet;

public class CargoPlane extends Jet implements Delivery {
	
	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}
	public void loadCargo() {
		System.out.println("Plane is loaded.");
	}

}
