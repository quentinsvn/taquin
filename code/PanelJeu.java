/** PanelJeu.java
 * 	date 	: 17/02/2021
 * 	@author	: Raphaël Lizot, Théo Lavie, Quentin Savéan
 */


import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;



public class PanelJeu extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton[]   tabButtonNord;
	private JButton[]   tabButtonSud;
	private JButton[]   tabButtonEst;
	private JButton[]   tabButtonOuest;

	private ImageIcon[]    imgFleche;
	private ImageIcon[]  imgCouleur;
	
	private JPanel panelNord;
	private JPanel panelSud;
	private JPanel panelEst;
	private JPanel panelOuest;
	private JPanel panelPlateau;

	private JLabel[] tabPlateauImg;
	


	public PanelJeu ( Controleur ctrl )
	{
		this.ctrl = ctrl;

		/*------------------------*/
		/* Création des Controles */
		/*------------------------*/

		this.tabPlateauImg = new JLabel[36];

		for ( int cpt = 0 ; cpt < 36; cpt++ )
		{
			this.tabPlateauImg[cpt] = new JLabel();
			this.tabPlateauImg[cpt].setPreferredSize(new Dimension( 65, 65 ));
		}

		// Initialisation des panels
		this.panelNord  = new JPanel( new GridLayout (1,8) );
		this.panelSud   = new JPanel( new GridLayout (1,8) );
		this.panelEst   = new JPanel( new GridLayout (6,1) );
		this.panelOuest = new JPanel( new GridLayout (6,1) );

		// PanelPlateau
		this.panelPlateau= new JPanel( new GridLayout (6,6) );


		// Chargement des images de imgFleche
		this.imgFleche      = new ImageIcon[4];

		this.imgFleche[0]   = new ImageIcon( "../images/fl_bas.gif"   );
		this.imgFleche[1]   = new ImageIcon( "../images/fl_droite.gif");
		this.imgFleche[2]   = new ImageIcon( "../images/fl_gauche.gif");
		this.imgFleche[3]   = new ImageIcon( "../images/fl_haut.gif"  );


		// Chargement des images du jeu pour le mode Couleur
		ImageIcon   imgVide      = new ImageIcon( "../images/vide.gif" );
		String[] tabCouleur      = new String[] {"mauve", "orange", "rouge", "vert", "bleu", "jaune"};

		this.imgCouleur  = new ImageIcon[ tabCouleur.length ];
		for(int cpt = 0; cpt < tabCouleur.length; cpt++)
			this.imgCouleur[cpt] = new ImageIcon("../images/" + tabCouleur[cpt] + ".gif") ;

		// Création des boutons de control
		this.tabButtonNord  = new JButton[6];
		this.tabButtonOuest = new JButton[6];
		this.tabButtonSud   = new JButton[6];
		this.tabButtonEst   = new JButton[6];
		
		for(int cpt = 0; cpt < 6 ; cpt++)
		{
			this.tabButtonSud  [cpt]     = new JButton( this.imgFleche[0] );
			this.tabButtonSud  [cpt].setPreferredSize( new Dimension( 65, 65 ) );
			

			this.tabButtonOuest[cpt]     = new JButton( this.imgFleche[1] );
			this.tabButtonOuest[cpt].setPreferredSize (new Dimension( 65, 65 ) );

			this.tabButtonEst  [cpt]     = new JButton( this.imgFleche[2]);
			this.tabButtonEst  [cpt].setPreferredSize( new Dimension( 65, 65 ) );

			this.tabButtonNord [cpt]     = new JButton( this.imgFleche[3]);
			this.tabButtonNord [cpt].setPreferredSize( new Dimension( 65, 65 ) );
		}

		/*----------------------------------*/
		/* Lier les Controles aux événements*/
		/*----------------------------------*/

		for(int cpt = 0; cpt < 6 ; cpt++)
		{
			this.tabButtonSud  [cpt].addActionListener   ( this );
			this.tabButtonNord [cpt].addActionListener   ( this );
			this.tabButtonOuest[cpt].addActionListener   ( this );
			this.tabButtonEst  [cpt].addActionListener   ( this );
		}
		

		/*---------------------------------*/
		/* Ajout des Controles sur le Panel*/
		/*---------------------------------*/

		// Placement des panels
		this.add( this.panelNord  ,   BorderLayout.NORTH );
		this.add( this.panelEst   ,   BorderLayout.EAST  );

		this.add( this.panelPlateau,  BorderLayout.CENTER);
		
		this.add( this.panelOuest ,   BorderLayout.WEST  );
		this.add( this.panelSud   ,   BorderLayout.SOUTH );
		

		// Ajout des boutons sur les panels
		this.panelNord.add  ( new JLabel( imgVide) );
		this.panelSud .add   ( new JLabel( imgVide) );

		for ( int cpt =0; cpt < 6 ; cpt++)
		{
			this.panelNord .add  ( this.tabButtonNord [cpt] );
			this.panelSud  .add  ( this.tabButtonSud  [cpt] );
			this.panelEst  .add  ( this.tabButtonEst  [cpt] );
			this.panelOuest.add  ( this.tabButtonOuest[cpt] );
		}
		
		this.panelNord.add  ( new JLabel( imgVide) );
		this.panelSud .add  ( new JLabel( imgVide) );


		// Affichage du plateau
		this.initialisationtabPlateauImg();

		for( int cpt = 0; cpt < this.tabPlateauImg.length ; cpt++)
			this.panelPlateau.add( this.tabPlateauImg[cpt]  );


	}

	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		this.initialisationtabPlateauImg();
	}

	public void initialisationtabPlateauImg()
	{
		
		char[][] plateau = ctrl.getPlateau();
		int cpt = 0;
		String nomImg = "";

		if ( ctrl.getMode() == 0)
		{
			for (int cptLig = 0; cptLig < 6; cptLig++)
			{
				for (int cptCol = 0; cptCol < 6; cptCol++)
				{
					switch(  plateau[cptLig][cptCol] )
					{
						case 'M' -> this.tabPlateauImg[cpt].setIcon( imgCouleur[0] );
						case 'O' -> this.tabPlateauImg[cpt].setIcon( imgCouleur[1] );
						case 'R' -> this.tabPlateauImg[cpt].setIcon( imgCouleur[2] );
						case 'V' -> this.tabPlateauImg[cpt].setIcon( imgCouleur[3] );
						case 'B' -> this.tabPlateauImg[cpt].setIcon( imgCouleur[4] );
						case 'J' -> this.tabPlateauImg[cpt].setIcon( imgCouleur[5] );
						default  -> this.tabPlateauImg[cpt].setText( "Erreur" );
					}

					cpt++;
				}
			}
		}
		if ( ctrl.getMode() == 1 )
		{
			for (int cptLig = 0; cptLig < 6; cptLig++)
				for (int cptCol = 0; cptCol < 6; cptCol++)
				{
					nomImg = "../images_asterix/" + plateau[cptLig][cptCol] + ".png";
					this.tabPlateauImg[cpt].setIcon( new ImageIcon( nomImg )   );
					cpt++;
				}
		}

	}


	public void actionPerformed(ActionEvent e)
	{
		// Action des boutons verticaux
		for ( int cpt = 0; cpt < 6; cpt++)
		{
			if ( e.getSource() == this.tabButtonSud  [cpt])
				ctrl.deplacerElement( false, cpt, 'D');
			
			if ( e.getSource() == this.tabButtonNord [cpt])
				ctrl.deplacerElement( false, cpt, 'G');
			
		}
		// Action des boutons horizontaux
		for ( int cpt = 0; cpt < 6; cpt++)
		{
			if ( e.getSource() == this.tabButtonEst  [cpt])
				ctrl.deplacerElement( true, cpt, 'G');

			if ( e.getSource() == this.tabButtonOuest  [cpt])
				ctrl.deplacerElement( true, cpt, 'D');
		}

	}
}
