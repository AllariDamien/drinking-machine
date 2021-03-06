package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;

import fr.univcotedazur.polytech.si4.fsm.project.defaultsm.*;
public class DrinkFactoryMachine extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPane;
	private DefaultSMStatemachine theFSM;
	private InformationsNFC infoNFC;
	private Order order; 
	private long temporaryId;
	private String cardInformation = "";
	
	private int sizeSliderValue;
	private int temperatureSliderValue;
	private int sugarSliderValue;
	
	private boolean isCupAdded = false;
	
	Stock stock;
	
	JLabel messagesToUser;
	
	Hashtable<Integer, JLabel> temperatureTable;
	
	JSlider sizeSlider;
	JSlider temperatureSlider;
	JSlider sugarSlider;
	
	
	JLabel lblOption1;
	JLabel lblOption2;
	JLabel lblOption3;
	JLabel lblOption4;
	JButton refuseAllOptions;
	
	JCheckBox cbOption1Yes;
	JCheckBox cbOption1No;
	JCheckBox cbOption2Yes;
	JCheckBox cbOption2No;
	JCheckBox cbOption3Yes;
	JCheckBox cbOption3No;
	JCheckBox cbOption4Yes;
	JCheckBox cbOption4No;

	
	/**
	 * @wbp.nonvisual location=311,475
	 */
	@SuppressWarnings("unused")
	private final ImageIcon imageIcon = new ImageIcon();
	private Thread t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrinkFactoryMachine frame = new DrinkFactoryMachine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Do methods
	 */
	protected boolean hasEnoughIngredients() {
		boolean enoughIngredients = true;
		if(sugarSlider.getValue() > stock.getStock().get("Sucre")) {
			enoughIngredients = false;
			messagesToUser.setText("<html>Le stock de sucre est insuffisant");
		}
		
		return enoughIngredients;
		
	}
	
	private boolean isOptionsSelected(String value) {
		switch(value) {
			case "Coffee":				
			case "Expresso":
				if((cbOption1Yes.isSelected()||cbOption1No.isSelected() || stock.getStock().get("Sirop")<1) && (cbOption2Yes.isSelected() || cbOption2No.isSelected() || stock.getStock().get("Lait")<1) && (cbOption3Yes.isSelected() || cbOption3No.isSelected() || stock.getStock().get("Glace")<1))
					return true;
				break;
			case "Tea":
				if((cbOption1Yes.isSelected()||cbOption1No.isSelected() || stock.getStock().get("Sirop")<1) && (cbOption2Yes.isSelected() || cbOption2No.isSelected()|| stock.getStock().get("Lait")<1))
					return true;
				break;
			case "Soup":
				if(cbOption4Yes.isSelected() || cbOption4No.isSelected() || stock.getStock().get("Croutons")<1)
					return true;
				break;
			case "Iced Tea": 
				if(cbOption1Yes.isSelected() || cbOption1No.isSelected() || stock.getStock().get("Sirop")<1)
					return true;
				break;
		}
		
		return false;
	}
	
	
	
	protected boolean isDrinkAvailable(String value) {
		
		if(!isOptionsSelected(value)) {
			return false;
		}
		
		if(!hasEnoughIngredients())
			return false;
		
		doUpdatePrice();
			
		return true;
	}
	
	protected void doUpdateAmountMoneyRaised(long value) {
		theFSM.setBalance(theFSM.getBalance() + value);
	}

	protected void doTypeSelectionRaised(String value) {
		theFSM.setType(value);
		doSetDrinkPrice(value);
		temperatureTable.replace(0, new JLabel("20°C"));
		temperatureTable.replace(1, new JLabel("35°C"));
		temperatureTable.replace(2, new JLabel("60°C"));
		temperatureTable.replace(3, new JLabel("85°C"));	
		for (JLabel l : temperatureTable.values()) {
			l.setForeground(Color.WHITE);
		}
		temperatureSlider.setLabelTable(temperatureTable);
		switch(value) {
			case "Coffee":
				messagesToUser.setText("<html>Boisson sélectionnée : Café<br>");
				break;
			case "Expresso":
		
				messagesToUser.setText("<html>Boisson sélectionnée : Expresso<br>");
				break;
			case "Tea":
				messagesToUser.setText("<html>Boisson sélectionnée : Thé<br>");
				break;
			case "Soup":
				messagesToUser.setText("<html>Boisson sélectionnée : Soupe<br>");
				break;
			case "Iced Tea": 
				messagesToUser.setText("<html>Boisson sélectionnée : Thé glacé<br>");
				temperatureTable.replace(0, new JLabel("2°C"));
				temperatureTable.replace(1, new JLabel("6°C"));
				temperatureTable.replace(2, new JLabel("10°C"));
				temperatureTable.replace(3, new JLabel("14°C"));	
				for (JLabel l : temperatureTable.values()) {
					l.setForeground(Color.WHITE);
				}
				temperatureSlider.setLabelTable(temperatureTable);
				break;
			default:
				
				messagesToUser.setText("<html>Veuillez choisir une boisson<br>");
				return;
		}
		messagesToUser.setText(messagesToUser.getText() + "Veuillez choisir les options<br>");
	}
	
	public void doSetDrinkPrice(String value) {
		switch(value) {
		case "Coffee":
			theFSM.setPrice(35);
			return;
		case "Expresso":
			theFSM.setPrice(50);
			return;
		case "Tea":
			theFSM.setPrice(40);
			return;
		case "Soup":
			theFSM.setPrice(75);
			return;
		case "Iced Tea": 
			theFSM.setPrice((sizeSlider.getValue()==2) ? 75 : 50); // à changer une fois qu'on aura un MVP qui marche --> enlever l'option short donc sizeSlider = 0
			return;
		default:
			theFSM.setPrice(-1);
			return;
		}
	}
	
	
	public void doUpdatePrice() {
		doSetDrinkPrice(theFSM.getType());
		
		if(theFSM.getOptionMapleSyrup())
			theFSM.setPrice(theFSM.getPrice() + 10);
		if(theFSM.getOptionSplashOfMilk())
			theFSM.setPrice(theFSM.getPrice() + 10);
		if(theFSM.getOptionMixedIceCream())
			theFSM.setPrice(theFSM.getPrice() + 60);
		if(theFSM.getOptionCroutons())
			theFSM.setPrice(theFSM.getPrice() + 30);
		
		if(isCupAdded)	
			theFSM.setPrice(theFSM.getPrice() - 10);
		
	}
	
	
	public void setVisibleOption1(boolean value) {
		if(stock.getStock().get("Sirop") > 0) {
			lblOption1.setVisible(value);
			cbOption1Yes.setVisible(value);
			cbOption1No.setVisible(value);
			cbOption1Yes.setSelected(false);
			cbOption1No.setSelected(false);
		}
		
	}
	
	public void setVisibleOption2(boolean value) {
		if(stock.getStock().get("Lait") > 0) {
			lblOption2.setVisible(value);
			cbOption2Yes.setVisible(value);
			cbOption2No.setVisible(value);
			cbOption2Yes.setSelected(false);
			cbOption2No.setSelected(false);
		}
	}
	
	public void setVisibleOption3(boolean value) {
		if(stock.getStock().get("Glace") > 0) {
			lblOption3.setVisible(value);
			cbOption3Yes.setVisible(value);
			cbOption3No.setVisible(value);
			cbOption3Yes.setSelected(false);
			cbOption3No.setSelected(false);
		}
	
	}
	
	public void setVisibleOption4(boolean value) {
		if(stock.getStock().get("Croutons") > 0) {
			lblOption4.setVisible(value);
			cbOption4Yes.setVisible(value);
			cbOption4No.setVisible(value);
			cbOption4Yes.setSelected(false);
			cbOption4No.setSelected(false);
		}
		
	}
	
	protected void doShowOptionsRaised(String value) {
		switch(value) {
			case "Coffee":
			case "Expresso":
				setVisibleOption1(true);
				setVisibleOption2(true);
				setVisibleOption3(true);
				setVisibleOption4(false);
				refuseAllOptions.setVisible(true);
				break;
			case "Tea":
				setVisibleOption1(true);
				setVisibleOption2(true);
				setVisibleOption3(false);
				setVisibleOption4(false);
				refuseAllOptions.setVisible(true);
				break;
			case "Soup":
				setVisibleOption1(false);
				setVisibleOption2(false);
				setVisibleOption3(false);
				setVisibleOption4(true);
				refuseAllOptions.setVisible(true);
				break;
			case "Iced Tea": 
				setVisibleOption1(true);
				setVisibleOption2(false);
				setVisibleOption3(false);
				setVisibleOption4(false);
				refuseAllOptions.setVisible(true);
				break;
			default:
				setVisibleOption1(false);
				setVisibleOption2(false);
				setVisibleOption3(false);
				setVisibleOption4(false);
				refuseAllOptions.setVisible(false);
		}
			
	}
	
	protected void doRefuseAllOptionsRaised() {
		cbOption1No.setSelected(true);
		cbOption2No.setSelected(true);
		cbOption3No.setSelected(true);
		cbOption4No.setSelected(true);
	}

	protected void doSaveInformationsRaised(long id) {
		temporaryId = id;
		Random random = new Random();
		cardInformation  = random.ints(48,  57)
				.limit(10)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		
		
	}

	protected void doResetRaised() {
		messagesToUser.setText("<html>Choix annulé");
		
		doResetSliders();
		doShowOptionsRaised("");
		
		if(theFSM.getBalance()!= 0) {
			doRefoundMoneyRaised();
		}
		
		if(temporaryId != 0) {
			messagesToUser.setText(messagesToUser.getText() + "<br>Transaction annulée");
			temporaryId = 0;
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theFSM.setOptionsSelected(false);
		isCupAdded = false;
		
		doTypeSelectionRaised(""); // reset aussi le prix à 0
		
		
	}
	
	protected void doResetSliders() {
		sugarSlider.setValue(1);
		sizeSlider.setValue(1);
		temperatureSlider.setValue(2);
		
	}

	protected void doRefoundMoneyRaised() {
		messagesToUser.setText(messagesToUser.getText() + "Le prix de votre boisson est  : " + (float)theFSM.getPrice()/100 + "€<br>");
		if(theFSM.getBalance() > 0) {
			long changeToBeReturned = (theFSM.getBalance() - theFSM.getPrice());
			messagesToUser.setText(messagesToUser.getText() + "Monnaie rendue : " + (float)changeToBeReturned/100 + "€<br>");
			theFSM.setBalance(0);
		}
		
		else {
			if(theFSM.getPrice() == 0)
				messagesToUser.setText(messagesToUser.getText() + "11ème achat, votre boisson est gratuite <br> ");
			else
				messagesToUser.setText(messagesToUser.getText() + "Vous avez été débité de  : " + (float)theFSM.getPrice()/100 + "€<br>");
		}
		
		
	}

	protected void doStartingPreparationRaised() {
		sizeSliderValue = sizeSlider.getValue();
		temperatureSliderValue = temperatureSlider.getValue();
		sugarSliderValue = sugarSlider.getValue();
		
		
		if(temporaryId != 0) {
			if(infoNFC.getNfc().containsKey(temporaryId)) {
				if(infoNFC.getNfc().get(temporaryId).getNbCommande() == 10 && theFSM.getPrice() <= infoNFC.getNfc().get(temporaryId).getPrixMoyen()) {
					// boisson gratuite
					theFSM.setPrice(0);
					infoNFC.remove(temporaryId);
					
				}
				else	
					infoNFC.incrementeNFC(temporaryId, theFSM.getPrice());
				
			}
			else {
				infoNFC.addNFC(temporaryId, theFSM.getPrice());
				
			}
			
		}
		cardInformation = "";
		
	}
	
	protected void doHeatingWaterRaised() {
		messagesToUser.setText(messagesToUser.getText() + "Démarage du chauffage de l'eau<br>");
	}
	
	protected void doPutProductRaised(String value) {
		switch(value) {
		case "Coffee":
			messagesToUser.setText(messagesToUser.getText() + "Ajout de la dosette<br>");
			stock.decrementStock("Cafe", 1);
			break;
		case "Expresso":
			messagesToUser.setText(messagesToUser.getText() + "Broyage des grains<br>");
			stock.decrementStock("Grains", 1);
			break;
		case "Tea":
			messagesToUser.setText(messagesToUser.getText() + "Ajout du sachet<br>");
			stock.decrementStock("Sachet", 1);
			break;
		case "Soup":
			messagesToUser.setText(messagesToUser.getText() + "Ajout de la dose de soupe<br>");
			stock.decrementStock("Soupe", 1);
			break;
		case "Iced Tea": 
			messagesToUser.setText(messagesToUser.getText() + "Ajout de la dosette<br>");
			stock.decrementStock("The", 1);
			break;
		}
	}
	
	protected void doPressRaised() {
		// En fonction de la taille
		messagesToUser.setText(messagesToUser.getText() + "Tassage des grains pour taille ");
		switch(sizeSliderValue) {
		case 0:
			messagesToUser.setText(messagesToUser.getText() + "short<br>");
			break;
		case 1:
			messagesToUser.setText(messagesToUser.getText() + "normal<br>");
			break;
		case 2:
			messagesToUser.setText(messagesToUser.getText() + "long<br>");
			break;			
		}
	}
	
	protected void doSetTemperatureRaised() {
		if(theFSM.getType().equals("Iced Tea")) {
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText(messagesToUser.getText() + "Température atteinte : hot !<br>");
				return;
		}
			
		
		
		switch(temperatureSliderValue) {
			case 0:
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText(messagesToUser.getText() + "Température atteinte : 20°C !<br>");
				break;
			case 1:
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText(messagesToUser.getText() + "Température atteinte :35°C !<br>");
				break;
			case 2:
			try {
				Thread.sleep(7500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText(messagesToUser.getText() + "Température atteinte 60°C !<br>");
				break;
			case 3:
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText(messagesToUser.getText() + "Température atteinte : 85°C !<br>");
				break;
		}
	}
	
	protected void doSetCupRaised() {
		if(isCupAdded)
			return;
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messagesToUser.setText(messagesToUser.getText() + "Gobelet positionné !<br>");
	}
	
	protected void doPutSugarRaised() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		switch(sugarSliderValue) {
			case 0:
				messagesToUser.setText(messagesToUser.getText() + "Sans sucre !<br>");
				break;
			case 1:
				messagesToUser.setText(messagesToUser.getText() + "Une dose de sucre ajoutée !<br>");
				//décrémenter compteur sucre
				break;
			case 2:
				messagesToUser.setText(messagesToUser.getText() + "2 doses de sucre ajoutées !<br>");
				//décrémenter compteur sucre
				break;
			case 3:
				messagesToUser.setText(messagesToUser.getText() + "3 doses de sucre ajoutées !<br>");
				//décrémenter compteur sucre
				break;
			case 4:
				messagesToUser.setText(messagesToUser.getText() + "4 doses de sucre ajoutées !<br>");
				//décrémenter compteur sucre
				break;
		}
		stock.decrementStock("Sucre", sugarSliderValue);
	}
	
	protected void doPourWaterRaised() {
		
		switch(sizeSliderValue) {
			case 0:
				if(theFSM.getType().equals("Expresso"))
					messagesToUser.setText(messagesToUser.getText() + "Ecoulement de l'eau pendant 4 secondes !<br>");
				else
					messagesToUser.setText(messagesToUser.getText() + "Ajout de l'eau de volume : court !<br>");
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1:
				if(theFSM.getType().equals("Expresso"))
					messagesToUser.setText(messagesToUser.getText() + "Ecoulement de l'eau pendant 6 secondes !<br>");
				else
					messagesToUser.setText(messagesToUser.getText() + "Ajout de l'eau de volume : normal !<br>");
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				if(theFSM.getType().equals("Expresso"))
					messagesToUser.setText(messagesToUser.getText() + "Ecoulement de l'eau pendant 8 secondes !<br>");
				else
					messagesToUser.setText(messagesToUser.getText() + "Ajout de l'eau de volume : long !<br>");
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		}
	}
	
	protected void doBrewingRaised() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messagesToUser.setText(messagesToUser.getText() + "Veuillez attendre la fin de l'infusion<br>");
		
	}
	
	protected void doRemoveTeaBagRaised() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messagesToUser.setText(messagesToUser.getText() + "Sachet de thé retiré<br>");
	}
	

	public void doDrinkRetrievableRaised() {
		messagesToUser.setText(messagesToUser.getText() + "Vous pouvez récupérer votre boisson ! <br>");
	}
	
	
	protected void doCleanSystemRaised() {
		messagesToUser.setText(messagesToUser.getText() + "Boisson récupérée !<br> Machine en cours de nettoyage ! <br>");
		doShowOptionsRaised("");
		doResetSliders();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messagesToUser.setText("Machine nettoyée");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theFSM.setOptionsSelected(false);
		isCupAdded = false;
		doTypeSelectionRaised("");
	}
	
	
	protected void doAddMapleSyrupRaised() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		stock.decrementStock("Sirop", 1);
		messagesToUser.setText(messagesToUser.getText() + "Sirop d'érable ajouté !<br>");

	}
	
	protected void doAddSplashOfMilk() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stock.decrementStock("Lait", 1);		
		messagesToUser.setText(messagesToUser.getText() + "Nuage de lait ajouté !<br>");
	}
	
	protected void doAddMixedIceCreamRaised() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stock.decrementStock("Glace", 1);
		messagesToUser.setText(messagesToUser.getText() + "Glace vanille ajoutée et mixée !<br>");
		
	}
	
	protected void doAddCroutonsRaised() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stock.decrementStock("Croutons", 1);
		messagesToUser.setText(messagesToUser.getText() + "Croutons ajoutés !<br>");
	}
	
	protected void doCoolingRaised() {
		switch(temperatureSliderValue) {
		case 0 :
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 1 :
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2 :
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3 :
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
						
		messagesToUser.setText(messagesToUser.getText() + "Boisson refroidie !<br>");
	}
	
	protected void doLockDoorRaised(boolean value) {
		if(value)
			messagesToUser.setText(messagesToUser.getText() + "Porte verrouillé !<br>");
		else
			messagesToUser.setText(messagesToUser.getText() + "Porte déverrouillé !<br>");
		
	}
	
	
	
	protected void doAddCupRaised() {
		isCupAdded = true;
		messagesToUser.setText(messagesToUser.getText() + "Tasse détectée !<br>");
		doUpdatePrice();
	}



	/**
	 * Create the frame.
	 */
	public DrinkFactoryMachine() {
		
		theFSM = new DefaultSMStatemachine();
		TimerService timer = new TimerService(); theFSM.setTimer(timer);
		theFSM.init();
		theFSM.enter();
		theFSM.getSCInterface().getListeners().add(new DrinkFactoryControlerInterfaceImplementation(this));
		stock = new Stock();
		infoNFC = new InformationsNFC();
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					theFSM.runCycle();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		t = new Thread(r);
		t.start();
		
		setForeground(Color.WHITE);
		setFont(new Font("Cantarell", Font.BOLD, 22));
		setBackground(Color.DARK_GRAY);
		setTitle("Drinking Factory Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 750); //x, y, largeur, hauteur
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		messagesToUser = new JLabel("<html>Veuillez choisir une boisson<br>");
		messagesToUser.setForeground(Color.WHITE);
		messagesToUser.setHorizontalAlignment(SwingConstants.LEFT);
		messagesToUser.setVerticalAlignment(SwingConstants.TOP);
		messagesToUser.setToolTipText("message to the user");
		messagesToUser.setBackground(Color.WHITE);
		messagesToUser.setBounds(480, 320, 225, 350);
		messagesToUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		contentPane.add(messagesToUser);
		

		JLabel lblCoins = new JLabel("Coins");
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoins.setBounds(538, 12, 44, 15);
		contentPane.add(lblCoins);
	

		JButton coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.WHITE);
		coffeeButton.setBackground(Color.DARK_GRAY);
		coffeeButton.setBounds(12, 34, 96, 25);
		contentPane.add(coffeeButton);
		coffeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stock.getStock().get("Cafe") > 0) {
					coffeeButton.setBackground(Color.DARK_GRAY);
					theFSM.raiseSelectType("Coffee");
				}
				else {
					coffeeButton.setBackground(Color.LIGHT_GRAY);
					messagesToUser.setText("Il n'y a plus de dosette en stock");
				}
				
			}
		});

		JButton expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.WHITE);
		expressoButton.setBackground(Color.DARK_GRAY);
		expressoButton.setBounds(12, 71, 96, 25);
		contentPane.add(expressoButton);
		expressoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stock.getStock().get("Grains") > 0) {
					expressoButton.setBackground(Color.DARK_GRAY);
					theFSM.raiseSelectType("Expresso");
				}
				else {
					expressoButton.setBackground(Color.LIGHT_GRAY);
					messagesToUser.setText("Il n'y a plus de grains en stock");
				}
					
				
			}
		});

		JButton teaButton = new JButton("Tea");
		teaButton.setForeground(Color.WHITE);
		teaButton.setBackground(Color.DARK_GRAY);
		teaButton.setBounds(12, 108, 96, 25);
		contentPane.add(teaButton);
		teaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stock.getStock().get("Sachet") > 0) {
					teaButton.setBackground(Color.DARK_GRAY);
					theFSM.raiseSelectType("Tea");
				}
				else {
					teaButton.setBackground(Color.LIGHT_GRAY);
					messagesToUser.setText("Il n'y a plus de sachet en stock");
				}
					
			}
		});

		
		JButton soupButton = new JButton("Soup");
		soupButton.setForeground(Color.WHITE);
		soupButton.setBackground(Color.DARK_GRAY);
		soupButton.setBounds(12, 145, 96, 25);
		contentPane.add(soupButton);
		soupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stock.getStock().get("Soupe") > 0) {
					soupButton.setBackground(Color.DARK_GRAY);
					theFSM.raiseSelectType("Soup");
				}
					
				else {
					soupButton.setBackground(Color.LIGHT_GRAY);
					messagesToUser.setText("Il n'y a plus de soupe en stock");
				}
					
			}
		});
		
		JButton icedTeaButton = new JButton("Iced Tea");
		icedTeaButton.setForeground(Color.WHITE);
		icedTeaButton.setBackground(Color.DARK_GRAY);
		icedTeaButton.setBounds(12, 182, 96, 25);
		contentPane.add(icedTeaButton);		
		icedTeaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stock.getStock().get("The") > 0) 	{
					icedTeaButton.setBackground(Color.DARK_GRAY);
					theFSM.raiseSelectType("Iced Tea");
				}
					
				else {
					icedTeaButton.setBackground(Color.LIGHT_GRAY);
					messagesToUser.setText("Il n'y a plus de dosette de the en stock");
				}
					
			}
		});
		
			
		JLabel lblOptions = new JLabel("OPTIONS");
		lblOptions.setForeground(Color.WHITE);
		lblOptions.setBackground(Color.DARK_GRAY);
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setBounds(115, 15, 120, 25);
		contentPane.add(lblOptions);
		
		lblOption1 = new JLabel("Maple Syrup");
		lblOption1.setForeground(Color.WHITE);
		lblOption1.setBackground(Color.DARK_GRAY);
		lblOption1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption1.setBounds(115, 40, 120, 15);
		contentPane.add(lblOption1);
		lblOption1.setVisible(false);
		
		
		lblOption2 = new JLabel("Splash of Milk");
		lblOption2.setForeground(Color.WHITE);
		lblOption2.setBackground(Color.DARK_GRAY);
		lblOption2.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption2.setBounds(115, 75, 120, 25);
		contentPane.add(lblOption2);
		lblOption2.setVisible(false);
		
		
		lblOption3 = new JLabel("Mixed Ice Cream");
		lblOption3.setForeground(Color.WHITE);
		lblOption3.setBackground(Color.DARK_GRAY);
		lblOption3.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption3.setBounds(115, 115, 120, 25);
		contentPane.add(lblOption3);
		lblOption3.setVisible(false);
		
		lblOption4 = new JLabel("Croutons");
		lblOption4.setForeground(Color.WHITE);
		lblOption4.setBackground(Color.DARK_GRAY);
		lblOption4.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption4.setBounds(115, 145, 120, 25);
		contentPane.add(lblOption4);
		lblOption4.setVisible(false);
		
		cbOption1Yes = new JCheckBox("Yes");
		cbOption1Yes.setForeground(Color.WHITE);
		cbOption1Yes.setBackground(Color.DARK_GRAY);
		cbOption1Yes.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption1Yes.setBounds(130, 54, 60, 20);
		contentPane.add(cbOption1Yes);
		cbOption1Yes.setVisible(false);
				
		cbOption1No = new JCheckBox("No");
		cbOption1No.setForeground(Color.WHITE);
		cbOption1No.setBackground(Color.DARK_GRAY);
		cbOption1No.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption1No.setBounds(180, 54, 60, 20);
		contentPane.add(cbOption1No);
		cbOption1No.setVisible(false);
		
		
		cbOption2Yes = new JCheckBox("Yes");
		cbOption2Yes.setForeground(Color.WHITE);
		cbOption2Yes.setBackground(Color.DARK_GRAY);
		cbOption2Yes.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption2Yes.setBounds(130, 94, 60, 20);
		contentPane.add(cbOption2Yes);
		cbOption2Yes.setVisible(false);
				
		cbOption2No = new JCheckBox("No");
		cbOption2No.setForeground(Color.WHITE);
		cbOption2No.setBackground(Color.DARK_GRAY);
		cbOption2No.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption2No.setBounds(180, 94, 60, 20);
		contentPane.add(cbOption2No);
		cbOption2No.setVisible(false);
		
		
		cbOption3Yes = new JCheckBox("Yes");
		cbOption3Yes.setForeground(Color.WHITE);
		cbOption3Yes.setBackground(Color.DARK_GRAY);
		cbOption3Yes.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption3Yes.setBounds(130, 134, 60, 20);
		contentPane.add(cbOption3Yes);
		cbOption3Yes.setVisible(false);		
		
		cbOption3No = new JCheckBox("No");
		cbOption3No.setForeground(Color.WHITE);
		cbOption3No.setBackground(Color.DARK_GRAY);
		cbOption3No.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption3No.setBounds(180, 134, 60, 20);
		contentPane.add(cbOption3No);
		cbOption3No.setVisible(false);
		
		
		cbOption4Yes = new JCheckBox("Yes");
		cbOption4Yes.setForeground(Color.WHITE);
		cbOption4Yes.setBackground(Color.DARK_GRAY);
		cbOption4Yes.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption4Yes.setBounds(130, 164, 60, 20);
		contentPane.add(cbOption4Yes);
		cbOption4Yes.setVisible(false);
		
		cbOption4No = new JCheckBox("No");
		cbOption4No.setForeground(Color.WHITE);
		cbOption4No.setBackground(Color.DARK_GRAY);
		cbOption4No.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption4No.setBounds(180, 164, 60, 20);
		contentPane.add(cbOption4No);
		cbOption4No.setVisible(false);
		
		
		refuseAllOptions = new JButton("No Options");
		refuseAllOptions.setForeground(Color.WHITE);
		refuseAllOptions.setBackground(Color.DARK_GRAY);
		refuseAllOptions.setBounds(125, 190, 110, 25);
		refuseAllOptions.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(refuseAllOptions);
		refuseAllOptions.setVisible(false);		
		

		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(10);
		progressBar.setForeground(Color.LIGHT_GRAY);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setBounds(12, 254, 622, 26);
		contentPane.add(progressBar);

		sugarSlider = new JSlider();
		sugarSlider.setValue(1);
		sugarSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sugarSlider.setBackground(Color.DARK_GRAY);
		sugarSlider.setForeground(Color.WHITE);
		sugarSlider.setPaintTicks(true);
		sugarSlider.setMinorTickSpacing(1);
		sugarSlider.setMajorTickSpacing(1);
		sugarSlider.setMaximum(4);
		sugarSlider.setBounds(301, 51, 200, 36);
		contentPane.add(sugarSlider);
		sugarSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				theFSM.raiseSugarSlider(sugarSlider.getValue());
				if(isDrinkAvailable(theFSM.getType()))
            		theFSM.setOptionsSelected(true);
				else
					theFSM.setOptionsSelected(false);
				
			}
		});

		sizeSlider = new JSlider();
		sizeSlider.setPaintTicks(true);
		sizeSlider.setValue(1);
		sizeSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sizeSlider.setBackground(Color.DARK_GRAY);
		sizeSlider.setForeground(Color.WHITE);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMaximum(2);
		sizeSlider.setMajorTickSpacing(1);
		sizeSlider.setBounds(301, 125, 200, 36);
		contentPane.add(sizeSlider);
		
		sizeSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				theFSM.raiseSizeSlider(sizeSlider.getValue());
			}
		});
		

		temperatureSlider = new JSlider();
		temperatureSlider.setPaintLabels(true);
		temperatureSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		temperatureSlider.setValue(2);
		temperatureSlider.setBackground(Color.DARK_GRAY);
		temperatureSlider.setForeground(Color.WHITE);
		temperatureSlider.setPaintTicks(true);
		temperatureSlider.setMajorTickSpacing(1);
		temperatureSlider.setMaximum(3);
		temperatureSlider.setBounds(301, 188, 200, 54);
		temperatureSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				theFSM.raiseTemperatureSlider(temperatureSlider.getValue());;
			}
		});

		temperatureTable = new Hashtable<Integer, JLabel>();
		temperatureTable.put(0, new JLabel("20°C"));
		temperatureTable.put(1, new JLabel("35°C"));
		temperatureTable.put(2, new JLabel("60°C"));
		temperatureTable.put(3, new JLabel("85°C"));
		for (JLabel l : temperatureTable.values()) {
			l.setForeground(Color.WHITE);
		}
		temperatureSlider.setLabelTable(temperatureTable);

		contentPane.add(temperatureSlider);

		JLabel lblSugar = new JLabel("Sugar");
		lblSugar.setForeground(Color.WHITE);
		lblSugar.setBackground(Color.DARK_GRAY);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setBounds(380, 34, 44, 15);
		contentPane.add(lblSugar);

		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(Color.WHITE);
		lblSize.setBackground(Color.DARK_GRAY);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(380, 113, 44, 15);
		contentPane.add(lblSize);

		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setForeground(Color.WHITE);
		lblTemperature.setBackground(Color.DARK_GRAY);
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setBounds(363, 173, 96, 15);
		contentPane.add(lblTemperature);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		lblCoins.setLabelFor(panel);
		panel.setBounds(538, 25, 96, 97);
		contentPane.add(panel);

		JButton money50centsButton = new JButton("0.50 €");
		money50centsButton.setForeground(Color.WHITE);
		money50centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money50centsButton);
		money50centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// doStop();
				theFSM.raiseCoinSlot(50);
				
			}
		});
		

		JButton money25centsButton = new JButton("0.25 €");
		money25centsButton.setForeground(Color.WHITE);
		money25centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money25centsButton);
		money25centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				theFSM.raiseCoinSlot(25);
				
			}
		});

		JButton money10centsButton = new JButton("0.10 €");
		money10centsButton.setForeground(Color.WHITE);
		money10centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money10centsButton);
		money10centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				theFSM.raiseCoinSlot(10);
				
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(538, 154, 96, 40);
		contentPane.add(panel_1);
			
		
		JButton nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.WHITE);
		nfcBiiiipButton.setBackground(Color.DARK_GRAY);
		panel_1.add(nfcBiiiipButton);
		
		JTextField idCard = new JTextField();
		idCard.setBounds(550, 190, 80, 20);		
		contentPane.add(idCard);
		
		
		nfcBiiiipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(!idCard.getText().equals(""))
					theFSM.raiseNFC(Long.parseLong(idCard.getText()));
			}
		});

		
		JLabel lblNfc = new JLabel("NFC");
		lblNfc.setForeground(Color.WHITE);
		lblNfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfc.setBounds(541, 139, 41, 15);
		contentPane.add(lblNfc);
		

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 292, 622, 15);
		contentPane.add(separator);

		JButton addCupButton = new JButton("Add cup");
		addCupButton.setForeground(Color.WHITE);
		addCupButton.setBackground(Color.DARK_GRAY);
		addCupButton.setBounds(45, 336, 96, 25);
		contentPane.add(addCupButton);
		

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel labelForPictures = new JLabel(new ImageIcon(myPicture));
		labelForPictures.setBounds(175, 319, 286, 260);
		labelForPictures.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				theFSM.raiseDrinkCollected();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		contentPane.add(labelForPictures);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(538, 217, 96, 33);
		contentPane.add(panel_2);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.DARK_GRAY);
		panel_2.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseCancelButton();
			}
		});

		// listeners
		addCupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BufferedImage myPicture = null;
				try {
					myPicture = ImageIO.read(new File("./picts/ownCup.jpg"));
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				labelForPictures.setIcon(new ImageIcon(myPicture));
				
				theFSM.raiseCupAdded();
			}
		});
		
		cbOption1Yes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption1Yes.isSelected())
            		cbOption1No.setSelected(false);            		
            	
            	theFSM.setOptionMapleSyrup((e.getStateChange()==ItemEvent.SELECTED?true:false));
            		
            	if(isDrinkAvailable(theFSM.getType())) // on le met à un seul endroit parce que dans tous les cas les 2 méthodes sont appelés
            		theFSM.setOptionsSelected(true);
            }
         });
		
		cbOption1No.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
            	
            	if(cbOption1No.isSelected())
            		cbOption1Yes.setSelected(false);

            	//theFSM.setOptionMapleSyrup(!(e.getStateChange()==ItemEvent.SELECTED));
            	
            	if(isDrinkAvailable(theFSM.getType()))
            		theFSM.setOptionsSelected(true);
            }    
         });
		
		
		cbOption2Yes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption2Yes.isSelected())
            		cbOption2No.setSelected(false);
            	
            	theFSM.setOptionSplashOfMilk((e.getStateChange()==ItemEvent.SELECTED?true:false));
            	
            	if(isDrinkAvailable(theFSM.getType()))
            		theFSM.setOptionsSelected(true);
            }
         });
		
		cbOption2No.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption2No.isSelected())
            		cbOption2Yes.setSelected(false);
            	
            //	theFSM.setOptionSplashOfMilk(!(e.getStateChange()==ItemEvent.SELECTED));
            	
            	if(isDrinkAvailable(theFSM.getType()))
            		theFSM.setOptionsSelected(true);
            }    
         }); 
		
		
		cbOption3Yes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	theFSM.raiseSelectOption();
            	if(cbOption3Yes.isSelected())
            		cbOption3No.setSelected(false);
            	
            	theFSM.setOptionMixedIceCream((e.getStateChange()==ItemEvent.SELECTED?true:false));
            	
            	if(isDrinkAvailable(theFSM.getType()))
            		theFSM.setOptionsSelected(true);
            }
         });
		
		cbOption3No.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
            	theFSM.raiseSelectOption();
            	if(cbOption3No.isSelected())
            		cbOption3Yes.setSelected(false);
            	
            	//theFSM.setOptionMixedIceCream(!(e.getStateChange()==ItemEvent.SELECTED));
            	
            	if(isDrinkAvailable(theFSM.getType()))
            		theFSM.setOptionsSelected(true);
            }    
         }); 	
		
		
		cbOption4Yes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	theFSM.raiseSelectOption();
            	if(cbOption4Yes.isSelected())
            		cbOption4No.setSelected(false);
            	
            	theFSM.setOptionCroutons((e.getStateChange()==ItemEvent.SELECTED?true:false));
            	
            	if(isDrinkAvailable(theFSM.getType()))
            		theFSM.setOptionsSelected(true);
            }
         });
		
		cbOption4No.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
            	theFSM.raiseSelectOption();
            	if(cbOption4No.isSelected())
            		cbOption4Yes.setSelected(false);
            	
            	//theFSM.setOptionCroutons(!(e.getStateChange()==ItemEvent.SELECTED));
            	
            	if(isDrinkAvailable(theFSM.getType()))
            		theFSM.setOptionsSelected(true);
            }    
         }); 
		
		
		refuseAllOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseSelectOption();
				doRefuseAllOptionsRaised();
				if(isDrinkAvailable(theFSM.getType()))
					theFSM.setOptionsSelected(true);
			}
		});
		
		
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		t.stop();
	}
}
