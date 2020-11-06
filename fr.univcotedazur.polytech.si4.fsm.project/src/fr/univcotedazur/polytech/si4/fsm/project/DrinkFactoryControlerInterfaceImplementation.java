package fr.univcotedazur.polytech.si4.fsm.project;

import fr.univcotedazur.polytech.si4.fsm.project.defaultsm.IDefaultSMStatemachine.SCInterfaceListener;

public class DrinkFactoryControlerInterfaceImplementation implements SCInterfaceListener {

	DrinkFactoryMachine machine;
	
	
	
	public DrinkFactoryControlerInterfaceImplementation(DrinkFactoryMachine machine) {
		super();
		this.machine = machine;
	}
	
	@Override
	public void onDoUpdateAmountMoneyRaised(long value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDoTypeSelectionRaised(long value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoSaveInformationsRaised(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoResetRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoRefoundMoneyRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoStartingPreparationRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoCleanSystemRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoDrinkCollectableRaised() {
		// TODO Auto-generated method stub
		
	}

}
