/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.defaultsm;

import fr.univcotedazur.polytech.si4.fsm.project.ITimer;
import java.util.LinkedList;
import java.util.List;

public class DefaultSMStatemachine implements IDefaultSMStatemachine {
	protected class SCInterfaceImpl implements SCInterface {
	
		private List<SCInterfaceListener> listeners = new LinkedList<SCInterfaceListener>();
		
		public List<SCInterfaceListener> getListeners() {
			return listeners;
		}
		private boolean coinSlot;
		
		private long coinSlotValue;
		
		
		public void raiseCoinSlot(long value) {
			synchronized(DefaultSMStatemachine.this) {
				coinSlotValue = value;
				coinSlot = true;
			}
		}
		protected long getCoinSlotValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! coinSlot ) 
					throw new IllegalStateException("Illegal event value access. Event CoinSlot is not raised!");
				return coinSlotValue;
			}
		}
		
		private boolean nFC;
		
		private long nFCValue;
		
		
		public void raiseNFC(long value) {
			synchronized(DefaultSMStatemachine.this) {
				nFCValue = value;
				nFC = true;
			}
		}
		protected long getNFCValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! nFC ) 
					throw new IllegalStateException("Illegal event value access. Event NFC is not raised!");
				return nFCValue;
			}
		}
		
		private boolean selectType;
		
		private String selectTypeValue;
		
		
		public void raiseSelectType(String value) {
			synchronized(DefaultSMStatemachine.this) {
				selectTypeValue = value;
				selectType = true;
			}
		}
		protected String getSelectTypeValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! selectType ) 
					throw new IllegalStateException("Illegal event value access. Event SelectType is not raised!");
				return selectTypeValue;
			}
		}
		
		private boolean sliderSugar;
		
		private long sliderSugarValue;
		
		
		public void raiseSliderSugar(long value) {
			synchronized(DefaultSMStatemachine.this) {
				sliderSugarValue = value;
				sliderSugar = true;
			}
		}
		protected long getSliderSugarValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! sliderSugar ) 
					throw new IllegalStateException("Illegal event value access. Event SliderSugar is not raised!");
				return sliderSugarValue;
			}
		}
		
		private boolean sliderSize;
		
		private long sliderSizeValue;
		
		
		public void raiseSliderSize(long value) {
			synchronized(DefaultSMStatemachine.this) {
				sliderSizeValue = value;
				sliderSize = true;
			}
		}
		protected long getSliderSizeValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! sliderSize ) 
					throw new IllegalStateException("Illegal event value access. Event SliderSize is not raised!");
				return sliderSizeValue;
			}
		}
		
		private boolean sliderTemperature;
		
		private long sliderTemperatureValue;
		
		
		public void raiseSliderTemperature(long value) {
			synchronized(DefaultSMStatemachine.this) {
				sliderTemperatureValue = value;
				sliderTemperature = true;
			}
		}
		protected long getSliderTemperatureValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! sliderTemperature ) 
					throw new IllegalStateException("Illegal event value access. Event SliderTemperature is not raised!");
				return sliderTemperatureValue;
			}
		}
		
		private boolean cancelButton;
		
		
		public void raiseCancelButton() {
			synchronized(DefaultSMStatemachine.this) {
				cancelButton = true;
			}
		}
		
		private boolean drinkCollected;
		
		
		public void raiseDrinkCollected() {
			synchronized(DefaultSMStatemachine.this) {
				drinkCollected = true;
			}
		}
		
		private boolean preparationFinished;
		
		
		public void raisePreparationFinished() {
			synchronized(DefaultSMStatemachine.this) {
				preparationFinished = true;
			}
		}
		
		private boolean doUpdateAmountMoney;
		
		private long doUpdateAmountMoneyValue;
		
		
		public boolean isRaisedDoUpdateAmountMoney() {
			synchronized(DefaultSMStatemachine.this) {
				return doUpdateAmountMoney;
			}
		}
		
		protected void raiseDoUpdateAmountMoney(long value) {
			synchronized(DefaultSMStatemachine.this) {
				doUpdateAmountMoneyValue = value;
				doUpdateAmountMoney = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoUpdateAmountMoneyRaised(value);
				}
			}
		}
		
		public long getDoUpdateAmountMoneyValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! doUpdateAmountMoney ) 
					throw new IllegalStateException("Illegal event value access. Event DoUpdateAmountMoney is not raised!");
				return doUpdateAmountMoneyValue;
			}
		}
		
		private boolean doTypeSelection;
		
		private String doTypeSelectionValue;
		
		
		public boolean isRaisedDoTypeSelection() {
			synchronized(DefaultSMStatemachine.this) {
				return doTypeSelection;
			}
		}
		
		protected void raiseDoTypeSelection(String value) {
			synchronized(DefaultSMStatemachine.this) {
				doTypeSelectionValue = value;
				doTypeSelection = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoTypeSelectionRaised(value);
				}
			}
		}
		
		public String getDoTypeSelectionValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! doTypeSelection ) 
					throw new IllegalStateException("Illegal event value access. Event DoTypeSelection is not raised!");
				return doTypeSelectionValue;
			}
		}
		
		private boolean doSaveInformations;
		
		private long doSaveInformationsValue;
		
		
		public boolean isRaisedDoSaveInformations() {
			synchronized(DefaultSMStatemachine.this) {
				return doSaveInformations;
			}
		}
		
		protected void raiseDoSaveInformations(long value) {
			synchronized(DefaultSMStatemachine.this) {
				doSaveInformationsValue = value;
				doSaveInformations = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoSaveInformationsRaised(value);
				}
			}
		}
		
		public long getDoSaveInformationsValue() {
			synchronized(DefaultSMStatemachine.this) {
				if (! doSaveInformations ) 
					throw new IllegalStateException("Illegal event value access. Event DoSaveInformations is not raised!");
				return doSaveInformationsValue;
			}
		}
		
		private boolean doReset;
		
		
		public boolean isRaisedDoReset() {
			synchronized(DefaultSMStatemachine.this) {
				return doReset;
			}
		}
		
		protected void raiseDoReset() {
			synchronized(DefaultSMStatemachine.this) {
				doReset = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoResetRaised();
				}
			}
		}
		
		private boolean doRefoundMoney;
		
		
		public boolean isRaisedDoRefoundMoney() {
			synchronized(DefaultSMStatemachine.this) {
				return doRefoundMoney;
			}
		}
		
		protected void raiseDoRefoundMoney() {
			synchronized(DefaultSMStatemachine.this) {
				doRefoundMoney = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoRefoundMoneyRaised();
				}
			}
		}
		
		private boolean doStartingPreparation;
		
		
		public boolean isRaisedDoStartingPreparation() {
			synchronized(DefaultSMStatemachine.this) {
				return doStartingPreparation;
			}
		}
		
		protected void raiseDoStartingPreparation() {
			synchronized(DefaultSMStatemachine.this) {
				doStartingPreparation = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoStartingPreparationRaised();
				}
			}
		}
		
		private boolean doCleanSystem;
		
		
		public boolean isRaisedDoCleanSystem() {
			synchronized(DefaultSMStatemachine.this) {
				return doCleanSystem;
			}
		}
		
		protected void raiseDoCleanSystem() {
			synchronized(DefaultSMStatemachine.this) {
				doCleanSystem = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoCleanSystemRaised();
				}
			}
		}
		
		private boolean doDrinkCollectable;
		
		
		public boolean isRaisedDoDrinkCollectable() {
			synchronized(DefaultSMStatemachine.this) {
				return doDrinkCollectable;
			}
		}
		
		protected void raiseDoDrinkCollectable() {
			synchronized(DefaultSMStatemachine.this) {
				doDrinkCollectable = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoDrinkCollectableRaised();
				}
			}
		}
		
		private long balance;
		
		public synchronized long getBalance() {
			synchronized(DefaultSMStatemachine.this) {
				return balance;
			}
		}
		
		public void setBalance(long value) {
			synchronized(DefaultSMStatemachine.this) {
				this.balance = value;
			}
		}
		
		private long price;
		
		public synchronized long getPrice() {
			synchronized(DefaultSMStatemachine.this) {
				return price;
			}
		}
		
		public void setPrice(long value) {
			synchronized(DefaultSMStatemachine.this) {
				this.price = value;
			}
		}
		
		protected void clearEvents() {
			coinSlot = false;
			nFC = false;
			selectType = false;
			sliderSugar = false;
			sliderSize = false;
			sliderTemperature = false;
			cancelButton = false;
			drinkCollected = false;
			preparationFinished = false;
		}
		protected void clearOutEvents() {
		
		doUpdateAmountMoney = false;
		doTypeSelection = false;
		doSaveInformations = false;
		doReset = false;
		doRefoundMoney = false;
		doStartingPreparation = false;
		doCleanSystem = false;
		doDrinkCollectable = false;
		}
		
	}
	
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_Ready,
		main_region_Cancellable,
		main_region_Cancellable_Drink_Drink_Selection,
		main_region_Cancellable_Payment_Payment_Selection_,
		main_region_Cancellable_Payment_Payed,
		main_region_Cancellable_Payment_Coins,
		main_region_Cancellable_Timer_Timer,
		main_region_Preparation,
		main_region_Drink_available,
		$NullState$
	};
	
	private final State[] stateVector = new State[3];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[1];
	
	public DefaultSMStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public synchronized void init() {
		this.initialized = true;
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 3; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setBalance(0);
		
		sCInterface.setPrice(-1);
	}
	
	public synchronized void enter() {
		if (!initialized) {
			throw new IllegalStateException(
				"The state machine needs to be initialized first by calling the init() function."
			);
		}
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		enterSequence_main_region_default();
	}
	
	public synchronized void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_Ready:
				main_region_Ready_react(true);
				break;
			case main_region_Cancellable_Drink_Drink_Selection:
				main_region_Cancellable_Drink_Drink_Selection_react(true);
				break;
			case main_region_Cancellable_Payment_Payment_Selection_:
				main_region_Cancellable_Payment_Payment_Selection__react(true);
				break;
			case main_region_Cancellable_Payment_Payed:
				main_region_Cancellable_Payment_Payed_react(true);
				break;
			case main_region_Cancellable_Payment_Coins:
				main_region_Cancellable_Payment_Coins_react(true);
				break;
			case main_region_Cancellable_Timer_Timer:
				main_region_Cancellable_Timer_Timer_react(true);
				break;
			case main_region_Preparation:
				main_region_Preparation_react(true);
				break;
			case main_region_Drink_available:
				main_region_Drink_available_react(true);
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
	public synchronized void exit() {
		exitSequence_main_region();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public synchronized boolean isActive() {
		return stateVector[0] != State.$NullState$||stateVector[1] != State.$NullState$||stateVector[2] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public synchronized boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCInterface.clearEvents();
		for (int i=0; i<timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
		sCInterface.clearOutEvents();
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public synchronized boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_Ready:
			return stateVector[0] == State.main_region_Ready;
		case main_region_Cancellable:
			return stateVector[0].ordinal() >= State.
					main_region_Cancellable.ordinal()&& stateVector[0].ordinal() <= State.main_region_Cancellable_Timer_Timer.ordinal();
		case main_region_Cancellable_Drink_Drink_Selection:
			return stateVector[0] == State.main_region_Cancellable_Drink_Drink_Selection;
		case main_region_Cancellable_Payment_Payment_Selection_:
			return stateVector[1] == State.main_region_Cancellable_Payment_Payment_Selection_;
		case main_region_Cancellable_Payment_Payed:
			return stateVector[1] == State.main_region_Cancellable_Payment_Payed;
		case main_region_Cancellable_Payment_Coins:
			return stateVector[1] == State.main_region_Cancellable_Payment_Coins;
		case main_region_Cancellable_Timer_Timer:
			return stateVector[2] == State.main_region_Cancellable_Timer_Timer;
		case main_region_Preparation:
			return stateVector[0] == State.main_region_Preparation;
		case main_region_Drink_available:
			return stateVector[0] == State.main_region_Drink_available;
		default:
			return false;
		}
	}
	
	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correctly
	* executed.
	* 
	* @param timer
	*/
	public synchronized void setTimer(ITimer timer) {
		this.timer = timer;
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}
	
	public synchronized void timeElapsed(int eventID) {
		timeEvents[eventID] = true;
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public synchronized void raiseCoinSlot(long value) {
		sCInterface.raiseCoinSlot(value);
	}
	
	public synchronized void raiseNFC(long value) {
		sCInterface.raiseNFC(value);
	}
	
	public synchronized void raiseSelectType(String value) {
		sCInterface.raiseSelectType(value);
	}
	
	public synchronized void raiseSliderSugar(long value) {
		sCInterface.raiseSliderSugar(value);
	}
	
	public synchronized void raiseSliderSize(long value) {
		sCInterface.raiseSliderSize(value);
	}
	
	public synchronized void raiseSliderTemperature(long value) {
		sCInterface.raiseSliderTemperature(value);
	}
	
	public synchronized void raiseCancelButton() {
		sCInterface.raiseCancelButton();
	}
	
	public synchronized void raiseDrinkCollected() {
		sCInterface.raiseDrinkCollected();
	}
	
	public synchronized void raisePreparationFinished() {
		sCInterface.raisePreparationFinished();
	}
	
	public synchronized boolean isRaisedDoUpdateAmountMoney() {
		return sCInterface.isRaisedDoUpdateAmountMoney();
	}
	
	public synchronized long getDoUpdateAmountMoneyValue() {
		return sCInterface.getDoUpdateAmountMoneyValue();
	}
	
	public synchronized boolean isRaisedDoTypeSelection() {
		return sCInterface.isRaisedDoTypeSelection();
	}
	
	public synchronized String getDoTypeSelectionValue() {
		return sCInterface.getDoTypeSelectionValue();
	}
	
	public synchronized boolean isRaisedDoSaveInformations() {
		return sCInterface.isRaisedDoSaveInformations();
	}
	
	public synchronized long getDoSaveInformationsValue() {
		return sCInterface.getDoSaveInformationsValue();
	}
	
	public synchronized boolean isRaisedDoReset() {
		return sCInterface.isRaisedDoReset();
	}
	
	public synchronized boolean isRaisedDoRefoundMoney() {
		return sCInterface.isRaisedDoRefoundMoney();
	}
	
	public synchronized boolean isRaisedDoStartingPreparation() {
		return sCInterface.isRaisedDoStartingPreparation();
	}
	
	public synchronized boolean isRaisedDoCleanSystem() {
		return sCInterface.isRaisedDoCleanSystem();
	}
	
	public synchronized boolean isRaisedDoDrinkCollectable() {
		return sCInterface.isRaisedDoDrinkCollectable();
	}
	
	public synchronized long getBalance() {
		return sCInterface.getBalance();
	}
	
	public synchronized void setBalance(long value) {
		sCInterface.setBalance(value);
	}
	
	public synchronized long getPrice() {
		return sCInterface.getPrice();
	}
	
	public synchronized void setPrice(long value) {
		sCInterface.setPrice(value);
	}
	
	/* Entry action for state 'Timer'. */
	private void entryAction_main_region_Cancellable_Timer_Timer() {
		timer.setTimer(this, 0, (45 * 1000), false);
	}
	
	/* Exit action for state 'Timer'. */
	private void exitAction_main_region_Cancellable_Timer_Timer() {
		timer.unsetTimer(this, 0);
	}
	
	/* 'default' enter sequence for state Ready */
	private void enterSequence_main_region_Ready_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Ready;
	}
	
	/* 'default' enter sequence for state Cancellable */
	private void enterSequence_main_region_Cancellable_default() {
		enterSequence_main_region_Cancellable_Drink_default();
		enterSequence_main_region_Cancellable_Payment_default();
		enterSequence_main_region_Cancellable_Timer_default();
	}
	
	/* 'default' enter sequence for state Drink Selection */
	private void enterSequence_main_region_Cancellable_Drink_Drink_Selection_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Cancellable_Drink_Drink_Selection;
	}
	
	/* 'default' enter sequence for state Payment Selection  */
	private void enterSequence_main_region_Cancellable_Payment_Payment_Selection__default() {
		nextStateIndex = 1;
		stateVector[1] = State.main_region_Cancellable_Payment_Payment_Selection_;
	}
	
	/* 'default' enter sequence for state Payed */
	private void enterSequence_main_region_Cancellable_Payment_Payed_default() {
		nextStateIndex = 1;
		stateVector[1] = State.main_region_Cancellable_Payment_Payed;
	}
	
	/* 'default' enter sequence for state Coins */
	private void enterSequence_main_region_Cancellable_Payment_Coins_default() {
		nextStateIndex = 1;
		stateVector[1] = State.main_region_Cancellable_Payment_Coins;
	}
	
	/* 'default' enter sequence for state Timer */
	private void enterSequence_main_region_Cancellable_Timer_Timer_default() {
		entryAction_main_region_Cancellable_Timer_Timer();
		nextStateIndex = 2;
		stateVector[2] = State.main_region_Cancellable_Timer_Timer;
	}
	
	/* 'default' enter sequence for state Preparation */
	private void enterSequence_main_region_Preparation_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Preparation;
	}
	
	/* 'default' enter sequence for state Drink available */
	private void enterSequence_main_region_Drink_available_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Drink_available;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* 'default' enter sequence for region Drink */
	private void enterSequence_main_region_Cancellable_Drink_default() {
		react_main_region_Cancellable_Drink__entry_Default();
	}
	
	/* 'default' enter sequence for region Payment */
	private void enterSequence_main_region_Cancellable_Payment_default() {
		react_main_region_Cancellable_Payment__entry_Default();
	}
	
	/* 'default' enter sequence for region Timer */
	private void enterSequence_main_region_Cancellable_Timer_default() {
		react_main_region_Cancellable_Timer__entry_Default();
	}
	
	/* Default exit sequence for state Ready */
	private void exitSequence_main_region_Ready() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Cancellable */
	private void exitSequence_main_region_Cancellable() {
		exitSequence_main_region_Cancellable_Drink();
		exitSequence_main_region_Cancellable_Payment();
		exitSequence_main_region_Cancellable_Timer();
	}
	
	/* Default exit sequence for state Drink Selection */
	private void exitSequence_main_region_Cancellable_Drink_Drink_Selection() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Payment Selection  */
	private void exitSequence_main_region_Cancellable_Payment_Payment_Selection_() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state Payed */
	private void exitSequence_main_region_Cancellable_Payment_Payed() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state Coins */
	private void exitSequence_main_region_Cancellable_Payment_Coins() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state Timer */
	private void exitSequence_main_region_Cancellable_Timer_Timer() {
		nextStateIndex = 2;
		stateVector[2] = State.$NullState$;
		
		exitAction_main_region_Cancellable_Timer_Timer();
	}
	
	/* Default exit sequence for state Preparation */
	private void exitSequence_main_region_Preparation() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Drink available */
	private void exitSequence_main_region_Drink_available() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_Ready:
			exitSequence_main_region_Ready();
			break;
		case main_region_Cancellable_Drink_Drink_Selection:
			exitSequence_main_region_Cancellable_Drink_Drink_Selection();
			break;
		case main_region_Preparation:
			exitSequence_main_region_Preparation();
			break;
		case main_region_Drink_available:
			exitSequence_main_region_Drink_available();
			break;
		default:
			break;
		}
		
		switch (stateVector[1]) {
		case main_region_Cancellable_Payment_Payment_Selection_:
			exitSequence_main_region_Cancellable_Payment_Payment_Selection_();
			break;
		case main_region_Cancellable_Payment_Payed:
			exitSequence_main_region_Cancellable_Payment_Payed();
			break;
		case main_region_Cancellable_Payment_Coins:
			exitSequence_main_region_Cancellable_Payment_Coins();
			break;
		default:
			break;
		}
		
		switch (stateVector[2]) {
		case main_region_Cancellable_Timer_Timer:
			exitSequence_main_region_Cancellable_Timer_Timer();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region Drink */
	private void exitSequence_main_region_Cancellable_Drink() {
		switch (stateVector[0]) {
		case main_region_Cancellable_Drink_Drink_Selection:
			exitSequence_main_region_Cancellable_Drink_Drink_Selection();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region Payment */
	private void exitSequence_main_region_Cancellable_Payment() {
		switch (stateVector[1]) {
		case main_region_Cancellable_Payment_Payment_Selection_:
			exitSequence_main_region_Cancellable_Payment_Payment_Selection_();
			break;
		case main_region_Cancellable_Payment_Payed:
			exitSequence_main_region_Cancellable_Payment_Payed();
			break;
		case main_region_Cancellable_Payment_Coins:
			exitSequence_main_region_Cancellable_Payment_Coins();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region Timer */
	private void exitSequence_main_region_Cancellable_Timer() {
		switch (stateVector[2]) {
		case main_region_Cancellable_Timer_Timer:
			exitSequence_main_region_Cancellable_Timer_Timer();
			break;
		default:
			break;
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Ready_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_Cancellable_Drink__entry_Default() {
		enterSequence_main_region_Cancellable_Drink_Drink_Selection_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_Cancellable_Payment__entry_Default() {
		enterSequence_main_region_Cancellable_Payment_Payment_Selection__default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_Cancellable_Timer__entry_Default() {
		enterSequence_main_region_Cancellable_Timer_Timer_default();
	}
	
	/* The reactions of state null. */
	private void react_main_region__sync0() {
		sCInterface.raiseDoRefoundMoney();
		
		sCInterface.raiseDoStartingPreparation();
		
		enterSequence_main_region_Preparation_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_Ready_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.selectType) {
				exitSequence_main_region_Ready();
				sCInterface.raiseDoTypeSelection(sCInterface.getSelectTypeValue());
				
				enterSequence_main_region_Cancellable_default();
				react();
			} else {
				if (sCInterface.coinSlot) {
					exitSequence_main_region_Ready();
					sCInterface.raiseDoUpdateAmountMoney(sCInterface.getCoinSlotValue());
					
					enterSequence_main_region_Cancellable_Drink_default();
					enterSequence_main_region_Cancellable_Payment_Coins_default();
					enterSequence_main_region_Cancellable_Timer_default();
					react();
				} else {
					if (sCInterface.nFC) {
						exitSequence_main_region_Ready();
						sCInterface.raiseDoSaveInformations(sCInterface.getNFCValue());
						
						enterSequence_main_region_Cancellable_Drink_default();
						enterSequence_main_region_Cancellable_Payment_Payed_default();
						enterSequence_main_region_Cancellable_Timer_default();
						react();
					} else {
						did_transition = false;
					}
				}
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_Cancellable_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			did_transition = false;
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_Cancellable_Drink_Drink_Selection_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.selectType) {
				exitSequence_main_region_Cancellable_Drink_Drink_Selection();
				sCInterface.raiseDoTypeSelection(sCInterface.getSelectTypeValue());
				
				enterSequence_main_region_Cancellable_Drink_Drink_Selection_default();
			} else {
				if ((sCInterface.getPrice()>=0 && isStateActive(State.main_region_Cancellable_Payment_Payed))) {
					exitSequence_main_region_Cancellable();
					react_main_region__sync0();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_Cancellable_Payment_Payment_Selection__react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.nFC) {
				exitSequence_main_region_Cancellable_Payment_Payment_Selection_();
				sCInterface.raiseDoSaveInformations(sCInterface.getNFCValue());
				
				enterSequence_main_region_Cancellable_Payment_Payed_default();
			} else {
				if (sCInterface.coinSlot) {
					exitSequence_main_region_Cancellable_Payment_Payment_Selection_();
					sCInterface.raiseDoUpdateAmountMoney(sCInterface.getCoinSlotValue());
					
					enterSequence_main_region_Cancellable_Payment_Coins_default();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_Cancellable_Payment_Payed_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if ((isStateActive(State.main_region_Cancellable_Drink_Drink_Selection) && sCInterface.getPrice()>=0)) {
				exitSequence_main_region_Cancellable();
				react_main_region__sync0();
			} else {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_Cancellable_Payment_Coins_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.coinSlot) {
				exitSequence_main_region_Cancellable_Payment_Coins();
				sCInterface.raiseDoUpdateAmountMoney(sCInterface.getCoinSlotValue());
				
				enterSequence_main_region_Cancellable_Payment_Coins_default();
			} else {
				if (((sCInterface.getBalance() - sCInterface.getPrice())>=0 && sCInterface.getPrice()>=0)) {
					exitSequence_main_region_Cancellable_Payment_Coins();
					enterSequence_main_region_Cancellable_Payment_Payed_default();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_Cancellable_Timer_Timer_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if ((sCInterface.selectType || (sCInterface.sliderSugar || (sCInterface.sliderSize || (sCInterface.sliderTemperature || (sCInterface.coinSlot || sCInterface.nFC)))))) {
				exitSequence_main_region_Cancellable_Timer_Timer();
				enterSequence_main_region_Cancellable_Timer_Timer_default();
				main_region_Cancellable_react(false);
			} else {
				if (timeEvents[0]) {
					exitSequence_main_region_Cancellable();
					sCInterface.raiseDoReset();
					
					enterSequence_main_region_Ready_default();
					react();
				} else {
					if (sCInterface.cancelButton) {
						exitSequence_main_region_Cancellable();
						sCInterface.raiseDoReset();
						
						enterSequence_main_region_Ready_default();
						react();
					} else {
						did_transition = false;
					}
				}
			}
		}
		if (did_transition==false) {
			did_transition = main_region_Cancellable_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_Preparation_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.preparationFinished) {
				exitSequence_main_region_Preparation();
				sCInterface.raiseDoDrinkCollectable();
				
				enterSequence_main_region_Drink_available_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_Drink_available_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.drinkCollected) {
				exitSequence_main_region_Drink_available();
				sCInterface.raiseDoCleanSystem();
				
				enterSequence_main_region_Ready_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
}
