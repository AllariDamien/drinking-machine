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
	private Map<Long, List<Long>> infosNFC = new HashMap<>();
	List<Long> listeId; 
	private long temporaryId;
	
	private boolean optionMilk = false;
	private boolean optionMapleSyrup = false;
	private boolean optionMixedIceCream = false;
	private boolean optionCroutons = false;
	
	JLabel messagesToUser;
	
	JButton refuseAllOptions;
	JSlider sizeSlider;
	JSlider temperatureSlider;
	JSlider sugarSlider;
	
	JLabel lblOption1;
	JLabel lblOption2;
	JLabel lblOption3;
	JLabel lblOption4;
	
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
	
	protected boolean isOptionsSelected() {
		if((cbOption1Yes.isSelected()||cbOption1No.isSelected()) && (cbOption2Yes.isSelected() || cbOption2No.isSelected()) && (cbOption3Yes.isSelected() || cbOption3No.isSelected()))
			return true;
		return false;
	}
	
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
			
	}
	
	
	public void setVisibleOption1(boolean value) {
		lblOption1.setVisible(value);
		cbOption1Yes.setVisible(value);
		cbOption1No.setVisible(value);
	}
	
	public void setVisibleOption2(boolean value) {
		lblOption2.setVisible(value);
		cbOption2Yes.setVisible(value);
		cbOption2No.setVisible(value);
	}
	
	public void setVisibleOption3(boolean value) {
		lblOption3.setVisible(value);
		cbOption3Yes.setVisible(value);
		cbOption3No.setVisible(value);
	}
	
	public void setVisibleOption4(boolean value) {
		lblOption4.setVisible(value);
		cbOption4Yes.setVisible(value);
		cbOption4No.setVisible(value);
	}
	
	protected void doShowOptionsRaised(String value) {
		System.out.println("hellooooo");
		switch(value) {
		case "Coffee":
		case "Expresso":
			setVisibleOption1(true);
			setVisibleOption2(true);
			setVisibleOption3(true);
			setVisibleOption4(false);
			break;
		case "Tea":
			setVisibleOption1(true);
			setVisibleOption2(true);
			setVisibleOption3(false);
			setVisibleOption4(false);
			break;
		case "Soup":
			setVisibleOption1(false);
			setVisibleOption2(false);
			setVisibleOption3(false);
			setVisibleOption4(true);
			break;
		case "Iced Tea": 
			setVisibleOption1(true);
			setVisibleOption2(false);
			setVisibleOption3(false);
			setVisibleOption4(false);
			break;
		default:
			setVisibleOption1(false);
			setVisibleOption2(false);
			setVisibleOption3(false);
			setVisibleOption4(false);
		}
			
	}
	
	protected void doRefuseAllOptionsRaised() {
		cbOption1No.setSelected(true);
		cbOption2No.setSelected(true);
		cbOption3No.setSelected(true);
	}

	protected void doSaveInformationsRaised(long id) {
		temporaryId = id;
		listeId = new ArrayList<Long>();
		
		
	}

	protected void doResetRaised() {
		messagesToUser.setText("<html>Choix annulé");
		
		doResetSliders();
		doTypeSelectionRaised(""); // reset aussi le prix à 0
		
		if(theFSM.getBalance()!= 0) {
			doRefoundMoneyRaised();
		}
		
		if(temporaryId != 0) {
			messagesToUser.setText(messagesToUser.getText() + "<br>Transaction annulée");
			temporaryId = 0;
		}
		
		doShowOptionsRaised("");
		
		
	}
	
	protected void doResetSliders() {
		sugarSlider.setValue(1);
		sizeSlider.setValue(1);
		temperatureSlider.setValue(2);
	}

	protected void doRefoundMoneyRaised() {
		long changeToBeReturned = (theFSM.getBalance() - theFSM.getPrice());
		messagesToUser.setText(messagesToUser.getText() + "<br>Monnaie rendue : " + (float)changeToBeReturned/100 + "€");
		theFSM.setBalance(0);
		
		// rajouter pour NFC
		
	}

	protected void doStartingPreparationRaised() {
		long value = 1;
		long moyenne;
		long nbCommande;
		
		
		if(infosNFC.containsKey(temporaryId)) {
			if(infosNFC.get(temporaryId).get(0) == 10 && theFSM.getPrice() <= infosNFC.get(temporaryId).get(1)) {
				// boisson gratuite
				
			}
				
			
			nbCommande = infosNFC.get(temporaryId).get(0) + 1;
			
			listeId.add(nbCommande);
			moyenne = (infosNFC.get(temporaryId).get(1) * infosNFC.get(temporaryId).get(0) + theFSM.getPrice()) 
			/ nbCommande;
			listeId.add(moyenne);

			infosNFC.put(temporaryId, listeId );
			
		}
		else {
			listeId.add(value);
			moyenne = theFSM.getPrice();
			listeId.add(moyenne);
			infosNFC.put(temporaryId, listeId);
			
		}
		for (Long i : infosNFC.keySet()) {
			  System.out.println(i + " " + "nb de commandes = " + infosNFC.get(i).get(0) + " moyenne = " + infosNFC.get(i).get(1));
			}
	}
	
	protected void doHeatingWaterRaised() {
		messagesToUser.setText(messagesToUser.getText() + "<br>Démarage du chauffage de l'eau");
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
	
	protected void doPressRaised() {
		// faire en fonction de la taille
		System.out.println("ça broie en balle");
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
	
	protected void doCleanSystemRaised() {
		messagesToUser.setText("Boisson récupérée ! Machine en cours de nettoyage ! ");
		doTypeSelectionRaised("");
		doResetSliders();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messagesToUser.setText("Machine nettoyée");
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
		setBounds(100, 100, 750, 750); //x, y, largeur, hauteur
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
		messagesToUser.setBounds(480, 336, 165, 70);
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
		
			
		JLabel lblOptions = new JLabel("OPTIONS");
		lblOptions.setForeground(Color.WHITE);
		lblOptions.setBackground(Color.DARK_GRAY);
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setBounds(115, 20, 120, 25);
		contentPane.add(lblOptions);
		
		lblOption1 = new JLabel("Maple Syrup");
		lblOption1.setForeground(Color.WHITE);
		lblOption1.setBackground(Color.DARK_GRAY);
		lblOption1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption1.setBounds(115, 40, 120, 25);
		contentPane.add(lblOption1);
		lblOption1.setVisible(false);
		
		
		lblOption2 = new JLabel("Milk Splash");
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
		lblOption3.setBounds(115, 110, 120, 25);
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
		cbOption1Yes.setBounds(130, 52, 60, 30);
		contentPane.add(cbOption1Yes);
		cbOption1Yes.setVisible(false);
		
		
		cbOption1No = new JCheckBox("No");
		cbOption1No.setForeground(Color.WHITE);
		cbOption1No.setBackground(Color.DARK_GRAY);
		cbOption1No.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption1No.setBounds(180, 52, 60, 30);
		contentPane.add(cbOption1No);
		cbOption1No.setVisible(false);
		
		cbOption1Yes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption1Yes.isSelected())
            		cbOption1No.setSelected(false);
            	if(isOptionsSelected())
            		theFSM.setOptionsSelected(true);
            }
         });
		
		cbOption1No.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
            	//cbOption1Yes.setSelected((e.getStateChange()==0?true:false));
            	if(cbOption1No.isSelected())
            		cbOption1Yes.setSelected(false);
            	if(isOptionsSelected())
            		theFSM.setOptionsSelected(true);
            }    
         });
		
		cbOption2Yes = new JCheckBox("Yes");
		cbOption2Yes.setForeground(Color.WHITE);
		cbOption2Yes.setBackground(Color.DARK_GRAY);
		cbOption2Yes.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption2Yes.setBounds(130, 87, 60, 30);
		contentPane.add(cbOption2Yes);
		cbOption2Yes.setVisible(false);
		
		
		cbOption2No = new JCheckBox("No");
		cbOption2No.setForeground(Color.WHITE);
		cbOption2No.setBackground(Color.DARK_GRAY);
		cbOption2No.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption2No.setBounds(180, 87, 60, 30);
		contentPane.add(cbOption2No);
		cbOption2No.setVisible(false);
		
		cbOption2Yes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption2Yes.isSelected())
            		cbOption2No.setSelected(false);
            	if(isOptionsSelected())
            		theFSM.setOptionsSelected(true);
            }
         });
		
		cbOption2No.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption2No.isSelected())
            		cbOption2Yes.setSelected(false);
            	if(isOptionsSelected())
            		theFSM.setOptionsSelected(true);
            }    
         }); 
		
		cbOption3Yes = new JCheckBox("Yes");
		cbOption3Yes.setForeground(Color.WHITE);
		cbOption3Yes.setBackground(Color.DARK_GRAY);
		cbOption3Yes.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption3Yes.setBounds(130, 122, 60, 30);
		contentPane.add(cbOption3Yes);
		cbOption3Yes.setVisible(false);
		
		
		cbOption3No = new JCheckBox("No");
		cbOption3No.setForeground(Color.WHITE);
		cbOption3No.setBackground(Color.DARK_GRAY);
		cbOption3No.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption3No.setBounds(180, 122, 60, 30);
		contentPane.add(cbOption3No);
		cbOption3No.setVisible(false);
		
		cbOption3Yes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption3Yes.isSelected())
            		cbOption3No.setSelected(false);
            	if(isOptionsSelected())
            		theFSM.setOptionsSelected(true);
            }
         });
		
		cbOption3No.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption3No.isSelected())
            		cbOption3Yes.setSelected(false);
            	if(isOptionsSelected())
            		theFSM.setOptionsSelected(true);
            }    
         }); 	
		
		cbOption4Yes = new JCheckBox("Yes");
		cbOption4Yes.setForeground(Color.WHITE);
		cbOption4Yes.setBackground(Color.DARK_GRAY);
		cbOption4Yes.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption4Yes.setBounds(130, 157, 60, 30);
		contentPane.add(cbOption4Yes);
		cbOption4Yes.setVisible(false);
		
		cbOption4No = new JCheckBox("No");
		cbOption4No.setForeground(Color.WHITE);
		cbOption4No.setBackground(Color.DARK_GRAY);
		cbOption4No.setHorizontalAlignment(SwingConstants.CENTER);
		cbOption4No.setBounds(180, 157, 60, 30);
		contentPane.add(cbOption4No);
		cbOption4No.setVisible(false);
		
		cbOption4Yes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption4Yes.isSelected())
            		cbOption4No.setSelected(false);
            	if(isOptionsSelected())
            		theFSM.setOptionsSelected(true);
            }
         });
		
		cbOption4No.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
            	if(cbOption4No.isSelected())
            		cbOption4Yes.setSelected(false);
            	if(isOptionsSelected())
            		theFSM.setOptionsSelected(true);
            }    
         }); 
		
		refuseAllOptions = new JButton("No Options");
		refuseAllOptions.setForeground(Color.WHITE);
		refuseAllOptions.setBackground(Color.DARK_GRAY);
		refuseAllOptions.setBounds(115, 190, 120, 25);
		contentPane.add(refuseAllOptions);
		refuseAllOptions.setVisible(true);
		refuseAllOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doRefuseAllOptionsRaised();
            	theFSM.setOptionsSelected(true);
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
		
		JPanel panelId = new JPanel();
		panelId.setBackground(Color.DARK_GRAY);
		panelId.setBounds(538, 180, 96, 40);
		contentPane.add(panelId);
		
		

		JTextField idCard = new JTextField();
		
		JButton nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.WHITE);
		nfcBiiiipButton.setBackground(Color.DARK_GRAY);
		panel_1.add(nfcBiiiipButton);
		panelId.add(idCard);
		nfcBiiiipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
