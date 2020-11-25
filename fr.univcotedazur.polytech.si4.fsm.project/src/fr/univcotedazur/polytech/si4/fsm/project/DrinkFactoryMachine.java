package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
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
	private HashMap<Long, Long> infosNFC = new HashMap<Long ,Long>();
	private long temporaryId;
	
	private boolean optionMilk = false;
	private boolean optionMapleSyrup = false;
	private boolean optionMixedIceCream = false;
	private boolean optionCroutons = false;
	
	JLabel messagesToUser;
	
	JButton option1;
	JButton option2;
	JButton option3;
	JSlider sizeSlider;
	JSlider temperatureSlider;
	JSlider sugarSlider;
	
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
	
	protected void doUpdateAmountMoneyRaised(long value) {
		theFSM.setBalance(theFSM.getBalance() + value);
	}

	protected void doTypeSelectionRaised(String value) {
		theFSM.setType(value);
		switch(value) {
		case "Coffee":
			theFSM.setPrice(35);
			break;
		case "Expresso":
			theFSM.setPrice(50);
			break;
		case "Tea":
			theFSM.setPrice(40);
			break;
		case "Soup":
			theFSM.setPrice(75);
			break;
		case "Iced Tea": 
			theFSM.setPrice((sizeSlider.getValue()==1) ? 50 : 75); // à changer une fois qu'on aura un MVP qui marche --> enlever l'option short donc sizeSlider = 0
			break;
		default:
			theFSM.setPrice(-1);
		}
		//System.out.println(theFSM.getType());
		//System.out.println(theFSM.getPrice());
			
	}
	
	protected void doShowOptionsRaised(String value) {
		switch(value) {
		case "Coffee":
		case "Expresso":
			option1.setText("Maple Syrup");
			option1.setVisible(true);
			option2.setText("Splash of Milk");
			option2.setVisible(true);
			option3.setText("Mixed Ice Cream");
			option3.setVisible(true);
			break;
		case "Tea":
			option1.setText("Maple Syrup");
			option1.setVisible(true);
			option2.setText("Splash of Milk");
			option2.setVisible(true);
			option3.setVisible(false);
			break;
		case "Soup":
			option1.setText("Croutons");
			option1.setVisible(true);
			option2.setVisible(false);
			option3.setVisible(false);
			break;
		case "Iced Tea": 
			option1.setText("Maple Syrup");
			option1.setVisible(true);
			option2.setVisible(false);
			option3.setVisible(false);
			break;
		default:
			option1.setVisible(false);
			option2.setVisible(false);
			option3.setVisible(false);
		}
			
	}

	protected void doSaveInformationsRaised(long id) {
		temporaryId = id;
	}

	protected void doResetRaised() {
		doTypeSelectionRaised("");
		doShowOptionsRaised("");
		theFSM.setBalance(0);
		temporaryId = 0;
		
		// doRefoundMoneyRaised();
		
		
	}

	protected void doRefoundMoneyRaised() {
		long changeToBeReturned = (theFSM.getBalance() - theFSM.getPrice());
		messagesToUser.setText("Monnaie rendue : " + (float)changeToBeReturned/100 + "€");		
	}

	protected void doStartingPreparationRaised() {
		long value = 1;
		if(infosNFC.containsKey(temporaryId)) {
			infosNFC.put(temporaryId, infosNFC.get(temporaryId)+1);
		}
		else {
			infosNFC.put(temporaryId, value);
		}
		for (Long i : infosNFC.keySet()) {
			  System.out.println(i + " " + infosNFC.get(i));
			}
	}
	
	protected void doHeatingWaterRaised() {
		messagesToUser.setText(messagesToUser.getText() + "\nDémarage du chauffage de l'eau");
	}
	
	protected void doPutProductRaised(String value) {
		switch(value) {
		case "Coffee":
			messagesToUser.setText("Ajout de la dosette");
			// décrémenter le compteur de dosette de café
			break;
		case "Expresso":
			messagesToUser.setText("Broyage des grains");
			// décrémenter le compteur de grains
			break;
		case "Tea":
			messagesToUser.setText("Ajout du sachet");
			// décrémenter le compteur de sachet
			break;
		case "Soup":
			messagesToUser.setText("Ajout de la dose de soupe");
			// décrémenter le compteur de dose
			break;
		case "Iced Tea": 
			messagesToUser.setText("Ajout de la dosette");
			// décrémenter le compteur de dosette de thé
			break;
		}
			
	}
	
	protected void doSetTemperatureRaised() {
		switch(temperatureSlider.getValue()) {
			case 0:
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText("Température atteinte : 20°C !");
				break;
			case 1:
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText("Température atteinte :35°C !");
				break;
			case 2:
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText("Température atteinte 60°C !");
				break;
			case 3:
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				messagesToUser.setText("Température atteinte : 85°C !");
				break;
		}
	}
	
	protected void doSetCupRaised() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messagesToUser.setText("Gobelet positionné !");
	}
	
	protected void doPutSugarRaised() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(sugarSlider.getValue()) {
			case 0:
				messagesToUser.setText("Sans sucre !");
				break;
			case 1:
				messagesToUser.setText("Une dose de sucre ajoutée !");
				//décrémenter compteur sucre
				break;
			case 2:
				messagesToUser.setText("2 doses de sucre ajoutées !");
				//décrémenter compteur sucre
				break;
			case 3:
				messagesToUser.setText("3 doses de sucre ajoutées !");
				//décrémenter compteur sucre
				break;
			case 4:
				messagesToUser.setText("4 doses de sucre ajoutées !");
				//décrémenter compteur sucre
				break;
		}
	}
	
	protected void doPourWaterRaised() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(sizeSlider.getValue()) {
			case 0:
				messagesToUser.setText("Eau ajoutée : court !");
				break;
			case 1:
				messagesToUser.setText("Eau ajoutée : normal !");
				break;
			case 2:
				messagesToUser.setText("Eau ajoutée : long !");
				break;
		}
	}
	
	protected void doBrewingRaised() {
		
	}
	
	protected void doRemoveTeaBagRaised() {
	
	}
	
	protected void doDrinkCollectableRaised() {
		
	}
	
	protected void doCleanSystemRaised() {
		
	}
	
	protected void doAddSplashOfMilk() {
		theFSM.setPrice(theFSM.getPrice()+10);
	}
	
	protected void doAddMapleSyrupRaised() {
		theFSM.setPrice(theFSM.getPrice()+10);
	}
	
	protected void doAddMixedIceCreamRaised() {
		theFSM.setPrice(theFSM.getPrice()+60);
	}
	
	protected void doAddCroutonsRaised() {
		theFSM.setPrice(theFSM.getPrice()+30);
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
		setBounds(100, 100, 650, 650); //x, y, largeur, hauteur
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		messagesToUser = new JLabel("<html>This is<br>place to communicate <br> with the user");
		messagesToUser.setForeground(Color.WHITE);
		messagesToUser.setHorizontalAlignment(SwingConstants.LEFT);
		messagesToUser.setVerticalAlignment(SwingConstants.TOP);
		messagesToUser.setToolTipText("message to the user");
		messagesToUser.setBackground(Color.WHITE);
		messagesToUser.setBounds(126, 34, 165, 70);
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
				theFSM.raiseSelectType("Coffee");;
				// doStop();
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
				theFSM.raiseSelectType("Expresso");;
				// doStop();
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
				theFSM.raiseSelectType("Tea");
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
				theFSM.raiseSelectType("Soup");
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
				theFSM.raiseSelectType("Iced Tea");
			}
		});
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setForeground(Color.WHITE);
		lblOptions.setBackground(Color.DARK_GRAY);
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setBounds(115, 85, 120, 25);
		contentPane.add(lblOptions);
		
		option1 = new JButton("option 1");
		option1.setForeground(Color.WHITE);
		option1.setBackground(Color.DARK_GRAY);
		option1.setBounds(115, 108, 120, 25);
		contentPane.add(option1);
		option1.setVisible(false);
		
		option2 = new JButton("option 2");
		option2.setForeground(Color.WHITE);
		option2.setBackground(Color.DARK_GRAY);
		option2.setBounds(115, 145, 120, 25);
		contentPane.add(option2);
		option2.setVisible(false);
		
		option3 = new JButton("option 3");
		option3.setForeground(Color.WHITE);
		option3.setBackground(Color.DARK_GRAY);
		option3.setBounds(115, 182, 120, 25);
		contentPane.add(option3);
		option3.setVisible(false);
		option3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		

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
				System.out.println(sizeSlider.getValue());
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

		Hashtable<Integer, JLabel> temperatureTable = new Hashtable<Integer, JLabel>();
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
		nfcBiiiipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseNFC(1234);
				// doStop();
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
				sugarSlider.setValue(1);
				sizeSlider.setValue(1);
				temperatureSlider.setValue(2);
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
