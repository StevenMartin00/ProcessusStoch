package view;

import java.awt.EventQueue;

import controller.AppController;

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
					AppController appController = new AppController();
					appController.initialize();
					appController.getFrame().setResizable(false);
					appController.getFrame().setVisible(true);
					/*App app = new App();
					app.initialize();
					app.frmFilesDattente.setVisible(true);*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
