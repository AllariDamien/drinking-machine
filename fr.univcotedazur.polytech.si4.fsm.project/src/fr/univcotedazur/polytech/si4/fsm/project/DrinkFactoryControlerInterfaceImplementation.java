package fr.univcotedazur.polytech.si4.fsm.project;

import fr.univcotedazur.polytech.si4.fsm.project.defaultsm.IDefaultSMStatemachine.SCInterfaceListener;

public class DrinkFactoryControlerInterfaceImplementation implements SCInterfaceListener {

	DrinkFactoryMachine machine;
	
	
	
	public DrinkFactoryControlerInterfaceImplementation(DrinkFactoryMachine machine) {
		
		this.machine = machine;
	}



	@Override
	public void onDoUpdateAmountMoneyRaised(long value) {
		// TODO Auto-generated method stub
		machine.doUpdateAmountMoneyRaised(value);
		
	}



	@Override
	public void onDoTypeSelectionRaised(String value) {
		// TODO Auto-generated method stub
		machine.doTypeSelectionRaised(value);
	}



	@Override
	public void onDoSaveInformationsRaised(long value) {
		// TODO Auto-generated method stub
		machine.doSaveInformationsRaised(value);
	}



	@Override
	public void onDoResetRaised() {
		// TODO Auto-generated method stub
		machine.doResetRaised();
	}



	@Override
	public void onDoRefoundMoneyRaised() {
		// TODO Auto-generated method stub
		machine.doRefoundMoneyRaised();
	}



	@Override
	public void onDoStartingPreparationRaised() {
		// TODO Auto-generated method stub
		machine.doStartingPreparationRaised();
	}



	@Override
	public void onDoCleanSystemRaised() {
		// TODO Auto-generated method stub
		machine.doCleanSystemRaised();
	}	
	

	
	@Override
	public void onDoHeatingWaterRaised() {
		// TODO Auto-generated method stub
		machine.doHeatingWaterRaised();
	}
	
	
	
	@Override
	public void onDoPutProductRaised(String value) {
		// TODO Auto-generated method stub
		machine.doPutProductRaised(value);
	}

	
	
	@Override
	public void onDoSetTemperatureRaised() {
		// TODO Auto-generated method stub
		machine.doSetTemperatureRaised();
	}
	
	
	
	@Override
	public void onDoSetCupRaised() {
		// TODO Auto-generated method stub
		machine.doSetCupRaised();		
	}



	@Override
	public void onDoPourWaterRaised() {
		// TODO Auto-generated method stub
		machine.doPourWaterRaised();
	}
	
	

	@Override
	public void onDoPutSugarRaised() {
		// TODO Auto-generated method stub
		machine.doPutSugarRaised();
	}



	@Override
	public void onDoBrewingRaised() {
		// TODO Auto-generated method stub
		machine.doBrewingRaised();
	}



	@Override
	public void onDoRemoveTeaBagRaised() {
		// TODO Auto-generated method stub
		machine.doRemoveTeaBagRaised();
	}



	@Override
	public void onDoAddSplashOfMilkRaised() {
		// TODO Auto-generated method stub
		machine.doAddSplashOfMilk();
	}



	@Override
	public void onDoAddMapleSyrupRaised() {
		// TODO Auto-generated method stub
		machine.doAddMapleSyrupRaised();
	}



	@Override
	public void onDoAddMixedIceCreamRaised() {
		// TODO Auto-generated method stub
		machine.doAddMixedIceCreamRaised();
	}



	@Override
	public void onDoAddCroutonsRaised() {
		// TODO Auto-generated method stub
		machine.doAddCroutonsRaised();
	}


	
	@Override
	public void onDoShowOptionsRaised(String value) {
		// TODO Auto-generated method stub
		//machine.doShowOptionsRaised(value);
	}



	@Override
	public void onDoPressRaised() {
		// TODO Auto-generated method stub
		machine.doPressRaised();		
	}
	
	
}
