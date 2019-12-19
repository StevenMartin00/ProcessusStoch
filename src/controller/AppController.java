package controller;

import model.FileMM1;
import model.FileMM1K;
import model.FileMMS;
import model.TimeUnit;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

public class AppController 
{

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
		frmFilesDattente.setBounds(100, 100, 522, 654);
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
		spinnerNbServicesMoy.setBounds(49, 124, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerNbServicesMoy);
		
		JSpinner spinnerClientsMoy = new JSpinner();
		spinnerClientsMoy.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerClientsMoy.setBounds(49, 65, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerClientsMoy);
		
		JSpinner spinnerNbServeur = new JSpinner();
		spinnerNbServeur.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerNbServeur.setBounds(49, 208, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerNbServeur);
		
		JLabel lblUniteTemps = new JLabel("");
		lblUniteTemps.setBounds(257, 385, 138, 22);
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
		cbUniteTemps.setBounds(274, 89, 190, 22);
		/** Adds all the time units to the combobox **/
		TimeUnit[] timeUnits = TimeUnit.class.getEnumConstants();
		for(int i = 0; i < timeUnits.length; i++)
		{
			cbUniteTemps.addItem(timeUnits[i]);
		}
		cbUniteTemps.setSelectedIndex(-1);
		frmFilesDattente.getContentPane().add(cbUniteTemps);
		
		JLabel lblUniteDeTemps = new JLabel("par unité de temps *");
		lblUniteDeTemps.setBounds(274, 68, 123, 16);
		frmFilesDattente.getContentPane().add(lblUniteDeTemps);
		
		JLabel lblNbClientsMoyenne = new JLabel("Nombre de clients en moyenne *");
		lblNbClientsMoyenne.setBounds(49, 39, 236, 16);
		frmFilesDattente.getContentPane().add(lblNbClientsMoyenne);
		
		JLabel lblNombreDeServices = new JLabel("Nombre de services en moyenne *");
		lblNombreDeServices.setBounds(49, 95, 236, 16);
		frmFilesDattente.getContentPane().add(lblNombreDeServices);
		
		JSpinner spinnerClientsMax = new JSpinner();
		spinnerClientsMax.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerClientsMax.setBounds(49, 260, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerClientsMax);
		
		JLabel lblNbClientsMax = new JLabel("Nombre de clients max *");
		lblNbClientsMax.setBounds(49, 231, 190, 16);
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
		chckbxIndetermine.setBounds(257, 259, 100, 25);
		frmFilesDattente.getContentPane().add(chckbxIndetermine);
		
		JTextArea txtAreaResultats = new JTextArea();
		txtAreaResultats.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAreaResultats.setBounds(49, 469, 404, 125);
		txtAreaResultats.setEditable(false);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		txtAreaResultats.setBorder(BorderFactory.createCompoundBorder(border, 
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		frmFilesDattente.getContentPane().add(txtAreaResultats);
		
		JLabel lblResultats = new JLabel("Résultats");
		lblResultats.setBounds(49, 447, 56, 16);
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
									System.out.println(spinnerNbServicesMoy.getValue().toString());
									mm1 = new FileMM1(Double.parseDouble(spinnerClientsMoy.getValue().toString()), Double.parseDouble(spinnerNbServicesMoy.getValue().toString()));
									results = getResult(mm1.L(), mm1.Lq(), mm1.W(), mm1.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), false, 0, 0);
									txtAreaResultats.setText(results);
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
		btnCalculer.setBounds(365, 271, 99, 25);
		frmFilesDattente.getContentPane().add(btnCalculer);
		
		JLabel lblNombreDeServeur = new JLabel("Nombre de serveurs *");
		lblNombreDeServeur.setBounds(49, 178, 190, 16);
		frmFilesDattente.getContentPane().add(lblNombreDeServeur);
		
		JLabel lblChamps = new JLabel("* : Champs requis");
		lblChamps.setBounds(367, 300, 115, 14);
		frmFilesDattente.getContentPane().add(lblChamps);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Proc. Stocha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox_1.setBounds(23, 13, 469, 313);
		frmFilesDattente.getContentPane().add(horizontalBox_1);
		
		JSpinner spinnerTempsPourcent = new JSpinner();
		spinnerTempsPourcent.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.1)));
		spinnerTempsPourcent.setBounds(49, 385, 190, 22);
		frmFilesDattente.getContentPane().add(spinnerTempsPourcent);
		
		JLabel lblProbabilitQuunClient = new JLabel("Probabilité qu'un client attende plus que");
		lblProbabilitQuunClient.setBounds(49, 362, 348, 16);
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
		buttonCalculerProba.setBounds(341, 407, 123, 25);
		frmFilesDattente.getContentPane().add(buttonCalculerProba);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(null, "Probabilit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		horizontalBox.setBounds(23, 339, 469, 106);
		
		frmFilesDattente.getContentPane().add(horizontalBox);
		
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
