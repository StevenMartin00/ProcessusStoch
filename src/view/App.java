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
import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
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
		
		JComboBox cbUniteTemps = new JComboBox();
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
		frmFilesDattente.getContentPane().add(txtAreaResultats);
		
		JLabel lblResultats = new JLabel("Résultats");
		lblResultats.setBounds(49, 418, 56, 16);
		frmFilesDattente.getContentPane().add(lblResultats);
		
		//TODO: prendre en compte unité de temps
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
									FileMM1 fileMM1 = new FileMM1(Double.parseDouble(txtFieldClientsMoy.getText()), Double.parseDouble(txtFieldNbServicesMoy.getText()));
									results = getResult(fileMM1.L(), fileMM1.Lq(), fileMM1.W(), fileMM1.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem());
									txtAreaResultats.setText(results);
								}
								else
								{
									if(!txtFieldClientsMax.getText().equals(""))
									{
										FileMM1K fileMM1K = new FileMM1K(Double.parseDouble(txtFieldClientsMoy.getText()), Double.parseDouble(txtFieldNbServicesMoy.getText()), Integer.parseInt(txtFieldClientsMax.getText()));
										results = getResult(fileMM1K.L(), fileMM1K.Lq(), 0, 0, (TimeUnit) cbUniteTemps.getSelectedItem());
										txtAreaResultats.setText(results);
									}
									else
									{
										//Print alert clients max doit etre rempli
									}
								}
							}
							else
							{
								FileMMS fileMMS = new FileMMS(Double.parseDouble(txtFieldClientsMoy.getText()), Double.parseDouble(txtFieldNbServicesMoy.getText()), Integer.parseInt(txtFieldNbServeur.getText()));
								results = getResult(fileMMS.L(), fileMMS.Lq(), fileMMS.W(), fileMMS.Wq(), (TimeUnit) cbUniteTemps.getSelectedItem());
								txtAreaResultats.setText(results);
							}
						}
						else
						{
							//Print alert < 0
						}
					}
					else
					{
						//Print alert 0 impossible
					}
				}
				else
				{
					//Print alert remplir champs
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
		
		JLabel lblUniteTemps = new JLabel("");
		lblUniteTemps.setBounds(259, 361, 46, 14);
		frmFilesDattente.getContentPane().add(lblUniteTemps);
		
		JButton buttonCalculerPropa = new JButton("Calculer Proba");
		buttonCalculerPropa.setBounds(341, 375, 123, 25);
		frmFilesDattente.getContentPane().add(buttonCalculerPropa);
		
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
	public String getResult(double l, double lq, double w, double wq, TimeUnit tu)
	{
		return "L : " + String.valueOf(l) + "\n" + "Lq : " + String.valueOf(lq) + "\n" + "W : " + String.valueOf(w) + "\n" + "Wq : " + String.valueOf(wq) + "\n" + "Unité de temps : " + tu.name().toLowerCase();
	}
}
