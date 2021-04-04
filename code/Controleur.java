/** Controleur.java
 * 	date 	: 17/02/2021
 * 	@author	: Raphaël Lizot, Théo Lavie, Quentin Savéan
 */

public class Controleur
{
	private Metier          metier;
	private FramePrincipale frmPrc;
	private int mode;

	public Controleur( int mode)    // mode : couleur -> 0  || Asterix -> 1
	{
		this.mode   = mode;
		this.metier = new Metier( mode );
		this.frmPrc = new FramePrincipale ( this );
	}
	
	public int getMode()
	{
		return this.mode;
	}

	public char[][] getPlateau()
	{
		return metier.getPlateau();
	}

	public void deplacerElement ( boolean axe, int num, char direction )
	{
		this.metier.deplacerElement( axe, num, direction);

		this.frmPrc.majIHM();
	}

	// Classe main du programme
	public static void main(String[] a)
	{
		if ( a.length == 0)
			System.out.println("Paramètre manquant, ( voir readme.txt).");

		else if( a[0].equals( "Couleur") )
			new Controleur( 0 );
		else if( a[0].equals( "Asterix") )
			new Controleur( 1 );
			
	}
}
