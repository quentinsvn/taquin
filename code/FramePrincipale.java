/** FramePrincipale.java
 * 	date 	: 17/02/2021
 * 	@author	: Raphaël Lizot, Théo Lavie, Quentin Savéan
 */

import javax.swing.JFrame;
import java.awt.Dimension;


public class FramePrincipale extends JFrame
{
	private PanelJeu panelJeu;


	public FramePrincipale ( Controleur ctrl )
	{
		int       largeurEcran, hauteurEcran;
		Dimension dimEcran;


		this.panelJeu = new PanelJeu(ctrl);


		dimEcran     = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		this.setTitle   ( "Permuter");
		this.setSize    ( 550,575  );
		this.setResizable(false);

		this.setLocation( (largeurEcran/2)-200, (hauteurEcran/2)-300 );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add ( this.panelJeu );

		this.setVisible(true);
	}

	public void majIHM()
	{
		this.panelJeu.repaint();
	}


}
