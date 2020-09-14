package com.skilldistillery.jet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

public class JetApp {
	private static Scanner input = new Scanner(System.in);
	private AirField field = new AirField();

	public static void main(String[] args) {
		JetApp ja = new JetApp();
		ja.launch();
		ja.displayUserMenu(input);
	}

	public void displayUserMenu(Scanner input) {
		System.out.println();
		System.out.println("--------------------MENU--------------------");
		System.out.println();
		System.out.println("Please choose one of the following options:");
		System.out.println("1 . List fleet");
		System.out.println("2 . Fly all jets.");
		System.out.println("3 . View fastest jet.");
		System.out.println("4 . View jet with longest range.");
		System.out.println("5 . Load all Cargo Jets.");
		System.out.println("6 . Dogfight!");
		System.out.println("7 . Add a jet to Fleet.");
		System.out.println("8 . Remove a jet from Fleet.");
		System.out.println("9 . Quit the program.");
		int userInput = input.nextInt();
		switch (userInput) {
		case 1:
			this.printJets();
			System.out.println();
			displayUserMenu(input);
			break;
		case 2:
			this.flyAllJets();
		   System.out.println();
		   displayUserMenu(input);
			break;
		case 3:
			this.fastestJet();
			System.out.println();
			displayUserMenu(input);
			break;
		case 4:
			this.longestRangeJet();
			displayUserMenu(input);
			System.out.println();
			break;
		case 5:
			this.flyCargo();
			System.out.println();
			displayUserMenu(input);
			break;
		case 6:
			this.dogFight();
			System.out.println();
			displayUserMenu(input);
			break;
		case 7:
			this.addJet(input);
			System.out.println();
			displayUserMenu(input);
			break;
		case 8:
			this.deleteJet(input);
			System.out.println();
			displayUserMenu(input);
			break;
		case 9:
			System.out.println("Goodbye");
			break;
		}
	}

	private void launch() {

		try (BufferedReader bufIn = new BufferedReader(new FileReader("ListOfJets"))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				String[] fields = line.split(",");
				if(fields[0].equals("fight")) {
				this.field.addJets(new FighterJet(fields[1], Double.parseDouble(fields[2]), Integer.parseInt(fields[3]), Long.parseLong(fields[4])));
				} else {
					this.field.addJets(new CargoPlane(fields[1], Double.parseDouble(fields[2]), Integer.parseInt(fields[3]), Long.parseLong(fields[4])));
				}

			}
		}
		 catch (IOException e) {
			System.err.println(e);
		}

	}
	private void printJets() {
		for (Jet arrJet : this.field.getListOfJets()) {
			System.out.println(arrJet);
			
		}
	}
	
	private void flyAllJets() {
		for (Jet arrJet : this.field.getListOfJets()) {
			arrJet.fly();
			
		}
	}


	
	private void fastestJet() {
		int fastestJet = 0;
		for (int i = 1; i < this.field.getListOfJets().size(); i++) {
			if(this.field.getListOfJets().get(i).getSpeed() >
			this.field.getListOfJets().get(fastestJet).getSpeed()) {
				fastestJet = i;
			}
		}
		System.out.println(this.field.getListOfJets().get(fastestJet));
	}
	
	private void longestRangeJet() {
		int longestRangeJet = 0;
		for (int i = 1; i < this.field.getListOfJets().size(); i++) {
			if(this.field.getListOfJets().get(i).getRange() >
			this.field.getListOfJets().get(longestRangeJet).getRange()) {
				longestRangeJet = i;
			}
		}
		System.out.println(this.field.getListOfJets().get(longestRangeJet));
	}
	
	private void flyCargo() {
		for (Jet arrJet : this.field.getListOfJets()) {
			try {
			((CargoPlane) arrJet).loadCargo();
	}
			catch(ClassCastException e) {}
		}
	}
	private void dogFight() {
		for (Jet arrJet : this.field.getListOfJets()) {
			try {
			((FighterJet) arrJet).fight();
	}
			catch(ClassCastException e) {}
		}
	}

	private void addJet(Scanner input) {
		String model;
		double speed;
		int range;
		long price;
		
		System.out.println("Please enter the model of a Jet.");
		model = input.nextLine();
		System.out.println("Please enter the speed of a Jet.");
		speed = input.nextDouble();
		input.nextLine();
		System.out.println("Please enter the range of a Jet.");
		range = input.nextInt();
		input.nextLine();
		System.out.println("Please enter the price of a Jet.");
		price = input.nextLong();
		input.nextLine();

		this.field.addJets(new FighterJet(model, speed, range, price));
		
	}
	
	private void deleteJet(Scanner input) {
		int remove;
		this.printJets();
		System.out.println("Which jet would you like to remove?");
		remove = input.nextInt();
		input.nextLine();
		this.field.removeJet(remove);
		
	}




}

