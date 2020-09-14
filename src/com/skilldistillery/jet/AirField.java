package com.skilldistillery.jet;

import java.util.ArrayList;

public class AirField {
	
	private ArrayList<Jet> listOfJets;

	public AirField() {
		listOfJets = new ArrayList<>();
	}

	public ArrayList<Jet> getListOfJets() {
		return listOfJets;
	}
	
	public void addJets(Jet newJet) {
		this.listOfJets.add(newJet);
		}
	public void removeJet(int remove) {
		this.listOfJets.remove(remove);
	}
}