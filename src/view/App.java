package view;

import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.AppController;
import model.FileMM1;
import model.FileMM1K;
import model.FileMMS;
import model.TimeUnit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class App 
{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*AppController appController = new AppController();
					appController.initialize();
					appController.getFrame().setVisible(true);*/
					App app = new App();
					app.initialize();
					app.frmFilesDattente.setResizable(false);
					app.frmFilesDattente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFrame frmFilesDattente;
	private JTextField txtFieldClientsMoy;
	private JTextField txtFieldNbServicesMoy;
	private JTextField txtFieldClientsMax;
	private String results;
	private JTextField txtFieldNbServeur;
	private JTextField textFieldTempsPourcent;
	private FileMM1 mm1;
	private FileMM1K mm1k;
	private FileMMS mms;
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() 
	{
		frmFilesDattente = new JFrame();
		frmFilesDattente.setTitle("Files d'attente");
		frmFilesDattente.setBounds(100, 100, 522, 630);
		frmFilesDattente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFilesDattente.addWindowListener(new java.awt.event.WindowAdapter() 
		{
			/**
			 * On close operation
			 * If yes is pressed, closes the window and saves the company to "data.ser" which is a file used for serialization
			 * If no is pressed, does nothing
			 */
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
		
		txtFieldClientsMoy = new JTextField();
		txtFieldClientsMoy.setBounds(49, 40, 190, 22);
		frmFilesDattente.getContentPane().add(txtFieldClientsMoy);
		txtFieldClientsMoy.setColumns(10);
		
		txtFieldNbServicesMoy = new JTextField();
		txtFieldNbServicesMoy.setColumns(10);
		txtFieldNbServicesMoy.setBounds(49, 99, 190, 22);
		frmFilesDattente.getContentPane().add(txtFieldNbServicesMoy);
		
		JLabel lblUniteTemps = new JLabel("");
		lblUniteTemps.setBounds(259, 361, 138, 14);
		frmFilesDattente.getContentPane().add(lblUniteTemps);
		
		JComboBox cbUniteTemps = new JComboBox();
		cbUniteTemps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(cbUniteTemps.getSelectedIndex() != -1)
					lblUniteTemps.setText(cbUniteTemps.getSelectedItem().toString());
			}
		});
		cbUniteTemps.setBackground(Color.WHITE);
		cbUniteTemps.setBounds(274, 69, 190, 22);
		/** Adds all the time units to the combobox **/
		TimeUnit[] timeUnits = TimeUnit.class.getEnumConstants();
		for(int i = 0; i < timeUnits.length; i++)
		{
			cbUniteTemps.addItem(timeUnits[i]);
		}
		cbUniteTemps.setSelectedIndex(-1);
		frmFilesDattente.getContentPane().add(cbUniteTemps);
		
		JLabel lblUniteDeTemps = new JLabel("par unité de temps *");
		lblUniteDeTemps.setBounds(274, 48, 123, 16);
		frmFilesDattente.getContentPane().add(lblUniteDeTemps);
		
		JLabel lblNbClientsMoyenne = new JLabel("Nombre de clients en moyenne *");
		lblNbClientsMoyenne.setBounds(49, 19, 236, 16);
		frmFilesDattente.getContentPane().add(lblNbClientsMoyenne);
		
		JLabel lblNombreDeServices = new JLabel("Nombre de services en moyenne *");
		lblNombreDeServices.setBounds(49, 75, 236, 16);
		frmFilesDattente.getContentPane().add(lblNombreDeServices);
		
		txtFieldClientsMax = new JTextField();
		txtFieldClientsMax.setColumns(10);
		txtFieldClientsMax.setBounds(49, 235, 190, 22);
		frmFilesDattente.getContentPane().add(txtFieldClientsMax);
		
		JLabel lblNbClientsMax = new JLabel("Nombre de clients max *");
		lblNbClientsMax.setBounds(49, 211, 190, 16);
		frmFilesDattente.getContentPane().add(lblNbClientsMax);
		
		JCheckBox chckbxIndetermine = new JCheckBox("Indéterminé");
		chckbxIndetermine.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtFieldClientsMax.isEnabled())
				{
					txtFieldClientsMax.setEnabled(false);
				}
				else
				{
					txtFieldClientsMax.setEnabled(true);
				}
			}
		});
		chckbxIndetermine.setBounds(259, 232, 100, 25);
		frmFilesDattente.getContentPane().add(chckbxIndetermine);
		
		JTextArea txtAreaResultats = new JTextArea();
		txtAreaResultats.setBounds(49, 440, 404, 125);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		txtAreaResultats.setBorder(BorderFactory.createCompoundBorder(border, 
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		frmFilesDattente.getContentPane().add(txtAreaResultats);
		
		JLabel lblResultats = new JLabel("Résultats");
		lblResultats.setBounds(49, 418, 56, 16);
		frmFilesDattente.getContentPane().add(lblResultats);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!txtFieldClientsMoy.getText().equals("")  && !txtFieldNbServicesMoy.getText().equals(""))
				{
					if(Integer.valueOf(txtFieldClientsMoy.getText()) != 0 && Integer.valueOf(txtFieldNbServicesMoy.getText()) != 0)
					{
						if(Integer.valueOf(txtFieldClientsMoy.getText()) > 0 && Integer.valueOf(txtFieldNbServicesMoy.getText()) > 0 && Integer.valueOf(txtFieldNbServeur.getText()) > 0)
						{
							if(Integer.valueOf(txtFieldNbServeur.getText()) == 1)
							{
								if(chckbxIndetermine.isSelected())
								{
									mm1 = new FileMM1(Double.parseDouble(txtFieldClientsMoy.getText()), Double.parseDouble(txtFieldNbServicesMoy.getText()));
									results = getResult(mm1.L(), mm1.Lq(), mm1.W(), mm1.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), false, 0, 0);
									txtAreaResultats.setText(results);
								}
								else
								{
									if(!txtFieldClientsMax.getText().equals(""))
									{
										mm1k = new FileMM1K(Double.parseDouble(txtFieldClientsMoy.getText()), Double.parseDouble(txtFieldNbServicesMoy.getText()), Integer.parseInt(txtFieldClientsMax.getText()));
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
								mms = new FileMMS(Double.parseDouble(txtFieldClientsMoy.getText()), Double.parseDouble(txtFieldNbServicesMoy.getText()), Integer.parseInt(txtFieldNbServeur.getText()));
								results = getResult(mms.L(), mms.Lq(), mms.W(), mms.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), false, 0, 0);
								txtAreaResultats.setText(results);
							}	
						}
						else
						{
							//Print alert < 0
							JOptionPane.showMessageDialog(frmFilesDattente, "Le nombre de clients, de services ou de serveur ne peut être négatif");
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
		btnCalculer.setBounds(365, 251, 99, 25);
		frmFilesDattente.getContentPane().add(btnCalculer);
		
		txtFieldNbServeur = new JTextField();
		txtFieldNbServeur.setColumns(10);
		txtFieldNbServeur.setBounds(49, 182, 190, 22);
		frmFilesDattente.getContentPane().add(txtFieldNbServeur);
		
		JLabel lblNombreDeServeur = new JLabel("Nombre de serveurs *");
		lblNombreDeServeur.setBounds(49, 158, 190, 16);
		frmFilesDattente.getContentPane().add(lblNombreDeServeur);
		
		JLabel lblChamps = new JLabel("* : Champs requis");
		lblChamps.setBounds(367, 280, 115, 14);
		frmFilesDattente.getContentPane().add(lblChamps);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		horizontalBox_1.setBounds(25, 11, 469, 293);
		frmFilesDattente.getContentPane().add(horizontalBox_1);
		
		textFieldTempsPourcent = new JTextField();
		textFieldTempsPourcent.setColumns(10);
		textFieldTempsPourcent.setBounds(49, 357, 190, 22);
		frmFilesDattente.getContentPane().add(textFieldTempsPourcent);
		
		JLabel lblProbabilitQuunClient = new JLabel("Probabilité qu'un client attende plus que");
		lblProbabilitQuunClient.setBounds(49, 330, 348, 16);
		frmFilesDattente.getContentPane().add(lblProbabilitQuunClient);

		JButton buttonCalculerProba = new JButton("Calculer Proba");
		buttonCalculerProba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(!textFieldTempsPourcent.getText().equals(""))
				{
					if(Integer.valueOf(textFieldTempsPourcent.getText()) > 0)
					{
						if(Integer.valueOf(txtFieldNbServeur.getText()) == 1)
						{
							results = getResult(mm1.L(), mm1.Lq(), mm1.W(), mm1.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), true, textFieldTempsPourcent.getText().toLowerCase(), mm1.probaTempsSejour(Double.valueOf(textFieldTempsPourcent.getText())));
							txtAreaResultats.setText(results);
						}
						else
						{
							results = getResult(mms.L(), mms.Lq(), mms.W(), mms.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem(), true, textFieldTempsPourcent.getText().toLowerCase(), mms.probaTempsSejour(Double.valueOf(textFieldTempsPourcent.getText())));
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
		buttonCalculerProba.setBounds(341, 375, 123, 25);
		frmFilesDattente.getContentPane().add(buttonCalculerProba);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		horizontalBox.setBounds(25, 315, 469, 92);
		
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
	public String getResult(double l, double lq, double w, double wq, TimeUnit tu, boolean isProba, String time, double proba)
	{
		if(isProba)
		{
			return "Nombre moyen de clients dans le système (L) : " + String.valueOf(l) + "\n" + "Nombre moyen de clients dans la file (Lq) : " + String.valueOf(lq) + "\n" + "Durée moyenne d'attente dans le système (W) : " + String.valueOf(w) + " " + tu.name().toLowerCase() + "\n" + "Durée moyenne d'attente dans la file (Wq) : " + String.valueOf(wq) + " " + tu.name().toLowerCase() + "\n" + "Probabilité qu'un client attende plus de " + time + " est de : " + String.valueOf(proba);
		}
		else
		{
			return "Nombre moyen de clients dans le système (L) : " + String.valueOf(l) + "\n" + "Nombre moyen de clients dans la file (Lq) : " + String.valueOf(lq) + "\n" + "Durée moyenne d'attente dans le système (W) : " + String.valueOf(w) + " " + tu.name().toLowerCase() + "\n" + "Durée moyenne d'attente dans la file (Wq) : " + String.valueOf(wq) + " " + tu.name().toLowerCase();
		}
	}
}
