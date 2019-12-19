package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.text.NumberFormatter;

import controller.AppController;
import model.FileMM1;
import model.FileMM1K;
import model.FileMMS;
import model.TimeUnit;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App 
{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {/*
					AppController appController = new AppController();
					appController.initialize();
					appController.getFrame().setResizable(false);
					appController.getFrame().setVisible(true);*/
					App app = new App();
					app.initialize();
					app.frmFilesDattente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JFrame frmFilesDattente;
	private JFormattedTextField txtFieldClientsMax;
	private String results;
	private JFormattedTextField textFieldTempsPourcent;
	private FileMM1 mm1;
	private FileMM1K mm1k;
	private FileMMS mms;
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() 
	{
		frmFilesDattente = new JFrame();
		frmFilesDattente.getContentPane().setBackground(SystemColor.menu);
		frmFilesDattente.setTitle("Files d'attente");
		frmFilesDattente.setBounds(100, 100, 522, 723);
		frmFilesDattente.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmFilesDattente.addWindowListener(new java.awt.event.WindowAdapter() 
		{
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	int confirmDialog = JOptionPane.showConfirmDialog(frmFilesDattente, 
		    			"Êtes-vous sûr de vouloir fermer l'application ?", "Confirmation de fermeture", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE);
		        if (confirmDialog == JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }
		        else if(confirmDialog == JOptionPane.NO_OPTION)
				{
		        	//do nothing
				}
		    }
		});
		frmFilesDattente.getContentPane().setLayout(null);
		
		NumberFormat format = NumberFormat.getNumberInstance();
		NumberFormatter numberFormatter = new NumberFormatter(format);
		numberFormatter.setValueClass(Integer.class); 
		numberFormatter.setAllowsInvalid(false);
		
		JSpinner spinnerNbServicesMoy = new JSpinner();
		spinnerNbServicesMoy.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerNbServicesMoy.setBounds(53, 178, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerNbServicesMoy);
		
		JSpinner spinnerClientsMoy = new JSpinner();
		spinnerClientsMoy.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerClientsMoy.setBounds(53, 119, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerClientsMoy);
		
		JSpinner spinnerNbServeur = new JSpinner();
		spinnerNbServeur.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerNbServeur.setBounds(53, 262, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerNbServeur);
		
		JLabel lblUniteTemps = new JLabel("");
		lblUniteTemps.setBounds(261, 439, 138, 22);
		frmFilesDattente.getContentPane().add(lblUniteTemps);
		
		JComboBox<TimeUnit> cbUniteTemps = new JComboBox<TimeUnit>();
		cbUniteTemps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(cbUniteTemps.getSelectedIndex() != -1)
					lblUniteTemps.setText(cbUniteTemps.getSelectedItem().toString());
			}
		});
		cbUniteTemps.setBackground(Color.WHITE);
		cbUniteTemps.setBounds(278, 143, 190, 22);
		/** Adds all the time units to the combobox **/
		TimeUnit[] timeUnits = TimeUnit.class.getEnumConstants();
		for(int i = 0; i < timeUnits.length; i++)
		{
			cbUniteTemps.addItem(timeUnits[i]);
		}
		cbUniteTemps.setSelectedIndex(-1);
		frmFilesDattente.getContentPane().add(cbUniteTemps);
		
		JLabel lblUniteDeTemps = new JLabel("par unité de temps *");
		lblUniteDeTemps.setBounds(278, 122, 123, 16);
		frmFilesDattente.getContentPane().add(lblUniteDeTemps);
		
		JLabel lblNbClientsMoyenne = new JLabel("Nombre de clients en moyenne *");
		lblNbClientsMoyenne.setBounds(53, 93, 236, 16);
		frmFilesDattente.getContentPane().add(lblNbClientsMoyenne);
		
		JLabel lblNombreDeServices = new JLabel("Nombre de services en moyenne *");
		lblNombreDeServices.setBounds(53, 149, 236, 16);
		frmFilesDattente.getContentPane().add(lblNombreDeServices);
		
		JSpinner spinnerClientsMax = new JSpinner();
		spinnerClientsMax.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerClientsMax.setBounds(53, 314, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerClientsMax);
		
		JLabel lblNbClientsMax = new JLabel("Nombre de clients max *");
		lblNbClientsMax.setBounds(53, 285, 190, 16);
		frmFilesDattente.getContentPane().add(lblNbClientsMax);
		
		JCheckBox chckbxIndetermine = new JCheckBox("Indéterminé");
		chckbxIndetermine.setBackground(SystemColor.menu);
		chckbxIndetermine.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(spinnerClientsMax.isEnabled())
				{
					spinnerClientsMax.setEnabled(false);
				}
				else
				{
					spinnerClientsMax.setEnabled(true);
				}
			}
		});
		chckbxIndetermine.setBounds(261, 313, 100, 25);
		frmFilesDattente.getContentPane().add(chckbxIndetermine);
		
		JTextArea txtAreaResultats = new JTextArea();
		txtAreaResultats.setForeground(new Color(128, 128, 128));
		txtAreaResultats.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAreaResultats.setBounds(27, 523, 469, 125);
		txtAreaResultats.setEditable(false);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		txtAreaResultats.setBorder(new CompoundBorder(new LineBorder(new Color(192, 192, 192)), new EmptyBorder(10, 10, 10, 10)));
		frmFilesDattente.getContentPane().add(txtAreaResultats);
		
		JLabel lblResultats = new JLabel("Résultats");
		lblResultats.setForeground(new Color(0, 0, 128));
		lblResultats.setBounds(37, 507, 56, 16);
		frmFilesDattente.getContentPane().add(lblResultats);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!spinnerClientsMoy.getValue().toString().equals("")  && !spinnerNbServicesMoy.getValue().toString().equals(""))
				{
					if(Integer.valueOf(spinnerClientsMoy.getValue().toString()) != 0 && Integer.valueOf(spinnerNbServicesMoy.getValue().toString()) != 0 && Integer.valueOf(spinnerNbServeur.getValue().toString()) != 0)
					{
						if(Integer.valueOf(spinnerClientsMoy.getValue().toString()) > 0 && Integer.valueOf(spinnerNbServicesMoy.getValue().toString()) > 0 && Integer.valueOf(spinnerNbServeur.getValue().toString()) > 0)
						{
							if(Integer.valueOf(spinnerNbServeur.getValue().toString()) == 1)
							{
								if(chckbxIndetermine.isSelected())
								{
									mm1 = new FileMM1(Double.parseDouble(spinnerClientsMoy.getValue().toString()), Double.parseDouble(spinnerNbServicesMoy.getValue().toString()));
									if(mm1.getRho() < 1)
									{
										results = getResult(mm1.L(), mm1.Lq(), mm1.W(), mm1.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), false, 0, 0);
										txtAreaResultats.setText(results);
									}
									else
									{
										JOptionPane.showMessageDialog(frmFilesDattente, "Le taux rho est supérieur ou égal à 1, le calcul est donc impossible");
									}
								}
								else
								{
									if(!spinnerClientsMax.getValue().toString().equals(""))
									{
										mm1k = new FileMM1K(Double.parseDouble(spinnerClientsMoy.getValue().toString()), Double.parseDouble(spinnerNbServicesMoy.getValue().toString()), Integer.parseInt(spinnerNbServeur.getValue().toString()));
										results = getResult(mm1k.L(), mm1k.Lq(), 0, 0, (TimeUnit) cbUniteTemps.getSelectedItem(), false, 0, 0);
										txtAreaResultats.setText(results);
									}
									else
									{
										//Print alert clients max doit etre rempli
										JOptionPane.showMessageDialog(frmFilesDattente, "Le champ correspondant au nombre de clients maximum doit être rempli");
									}
								}
							}
							else
							{
								mms = new FileMMS(Double.parseDouble(spinnerClientsMoy.getValue().toString()), Double.parseDouble(spinnerNbServicesMoy.getValue().toString()), Integer.parseInt(spinnerNbServeur.getValue().toString()));
								results = getResult(mms.L(), mms.Lq(), mms.W(), mms.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), false, 0, 0);
								txtAreaResultats.setText(results);
							}	
						}
						else
						{
							//Print alert < 0
							JOptionPane.showMessageDialog(frmFilesDattente, "Le nombre de clients, de services ou de serveur ne peut être négatif ou nul");
						}
					}
					else
					{
						//Print alert 0 impossible
						JOptionPane.showMessageDialog(frmFilesDattente, "Le nombre de clients, de services ou de serveur ne peut être nul");
					}
				}
				else
				{
					//Print alert remplir champs
					JOptionPane.showMessageDialog(frmFilesDattente, "Tous les champs doivent être remplis");
				}
			}
		});
		btnCalculer.setBounds(369, 325, 99, 25);
		frmFilesDattente.getContentPane().add(btnCalculer);
		
		JLabel lblNombreDeServeur = new JLabel("Nombre de serveurs *");
		lblNombreDeServeur.setBounds(53, 232, 190, 16);
		frmFilesDattente.getContentPane().add(lblNombreDeServeur);
		
		JLabel lblChamps = new JLabel("* : Champs requis");
		lblChamps.setBounds(371, 354, 115, 14);
		frmFilesDattente.getContentPane().add(lblChamps);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		horizontalBox_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Proc. Stocha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		horizontalBox_1.setBounds(27, 67, 469, 313);
		frmFilesDattente.getContentPane().add(horizontalBox_1);
		
		JSpinner spinnerTempsPourcent = new JSpinner();
		spinnerTempsPourcent.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.1)));
		spinnerTempsPourcent.setBounds(53, 439, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerTempsPourcent);
		
		JLabel lblProbabilitQuunClient = new JLabel("Probabilité qu'un client attende plus que");
		lblProbabilitQuunClient.setBounds(53, 416, 348, 16);
		frmFilesDattente.getContentPane().add(lblProbabilitQuunClient);

		JButton buttonCalculerProba = new JButton("Calculer Proba");
		buttonCalculerProba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(!spinnerTempsPourcent.getValue().toString().equals(""))
				{
					String tpsPourcentTxt = spinnerTempsPourcent.getValue().toString().replace(",", ".");
					double tpsPourcent = Double.valueOf(tpsPourcentTxt);
					
					if(tpsPourcent > 0.0)
					{
						if(Integer.valueOf(spinnerNbServeur.getValue().toString()) == 1)
						{
							results = getResult(mm1.L(), mm1.Lq(), mm1.W(), mm1.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), true, tpsPourcent, mm1.probaTempsSejour(tpsPourcent));
							txtAreaResultats.setText(results);
						}
						else
						{
							results = getResult(mms.L(), mms.Lq(), mms.W(), mms.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), true, tpsPourcent, mms.probaTempsSejour(tpsPourcent));
							txtAreaResultats.setText(results);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmFilesDattente, "Le temps d'attente d'un client ne peut être négatif");
					}
				}
					
			}
		});
		buttonCalculerProba.setBounds(345, 461, 123, 25);
		frmFilesDattente.getContentPane().add(buttonCalculerProba);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Probabilit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		horizontalBox.setBounds(27, 393, 469, 106);
		
		frmFilesDattente.getContentPane().add(horizontalBox);
		
		JLabel lblFilesDattente = new JLabel("Files d'attente");
		lblFilesDattente.setForeground(new Color(0, 0, 128));
		lblFilesDattente.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblFilesDattente.setBounds(27, 11, 262, 45);
		frmFilesDattente.getContentPane().add(lblFilesDattente);
		
		JMenuBar menuBar = new JMenuBar();
		frmFilesDattente.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("?");
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFrame aPropos = new JFrame();
				aPropos.getContentPane().setBackground(SystemColor.menu);
				aPropos.setTitle("A propos");
				aPropos.setBounds(400, 400, 400, 400);
				
				JLabel lblAPropos = new JLabel("<html>Application créée par Steven MARTIN et Damien LE GOANVIC <br>Encadrant: Mohamed SLIMANE</html>");
				JLabel img = new JLabel(new ImageIcon(new ImageIcon("image.jpg").getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH)));
				
				lblAPropos.setBounds(30, 25, 400, 30);
				
			
				aPropos.getContentPane().add(lblAPropos);
				aPropos.getContentPane().add(img);
				
				aPropos.setVisible(true);
			}
		});
		menuBar.add(menu);
		
	}
	
	/**
	 * Allows to get the frame of the app
	 * @return the frame of the app
	 */
	public JFrame getFrame()
	{
		return this.frmFilesDattente;
	}
	
	/**
	 * Creates a result string
	 */
	public String getResult(double l, double lq, double w, double wq, TimeUnit tu, boolean isProba, double time, double proba)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		if(isProba)
		{
			return "Nombre moyen de clients dans le système (L) : " + df.format(l) + "\n" + "Nombre moyen de clients dans la file (Lq) : " + df.format(lq) + "\n" + "Durée moyenne d'attente dans le système (W) : " + df.format(w) + " " + tu.name().toLowerCase() + "\n" + "Durée moyenne d'attente dans la file (Wq) : " + df.format(wq) + " " + tu.name().toLowerCase() + "\n" + "Probabilité qu'un client attende plus de " + df.format(time) + " " + tu.name().toLowerCase() + " est de : " + df.format(proba);
		}
		else
		{
			return "Nombre moyen de clients dans le système (L) : " + df.format(l) + "\n" + "Nombre moyen de clients dans la file (Lq) : " + df.format(lq) + "\n" + "Durée moyenne d'attente dans le système (W) : " + df.format(w) + " " + tu.name().toLowerCase() + "\n" + "Durée moyenne d'attente dans la file (Wq) : " + df.format(wq) + " " + tu.name().toLowerCase();
		}
	}
}
