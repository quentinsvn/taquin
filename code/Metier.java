/** Metier.java
 * 	date 	: 17/02/2021
 * 	@author	: Raphaël Lizot, Théo Lavie, Quentin Savéan
 */

public class Metier
{
	private char[][] plateauJeu;

	public Metier(int mode)
	{
		this.plateauJeu = new char[6][6];

		this.initialisationPlateau( mode );

	}


	// Initialisation du plateau
	private void initialisationPlateau(int mode)
	{
		char[] sacImg = null;
		if ( mode == 0 )
		{
			sacImg = new char[] { 'M','M','M','M','M','M',
								  'O','O','O','O','O','O',
								  'R','R','R','R','R','R',
								  'V','V','V','V','V','V',
								  'B','B','B','B','B','B',
								  'J','J','J','J','J','J' } ;
		}
		if ( mode == 1 )
		{
		 	sacImg = new char[] { '0','1','2','3','4','5',
								  '6','7','8','9','A','B',
								  'C','D','E','F','G','H',
								  'I','J','K','L','M','N',
								  'O','P','Q','R','S','T',
								  'U','V','W','X','Y','Z', };
		}


		int index = 35;
		int pioche = (int) ( Math.random() * (index+1) );
		
		for( int cptLig = 0; cptLig < 6; cptLig++ )
			for ( int cptCol =0; cptCol < 6; cptCol++ )
			{
				this.plateauJeu[cptLig][cptCol] = sacImg[pioche] ;

				this.permuter(sacImg, pioche, index);

				index--;

				pioche = (int) ( Math.random() * (index+1) );

			}

	}

	// Méthode permuter
	private void permuter (char[] tab, int ind1, int ind2)
	{
		char temp;
		
		temp = tab[ind1];
		tab[ind1] = tab[ind2];
		tab[ind2 ] = temp;
	}

	// Affichage du plateu
	public char[][] getPlateau()
	{
		return this.plateauJeu;
	}

	// Déplacement des éléments ( Ligne ou colonne )
	public void deplacerElement ( boolean axe, int num, char direction )
	{		
		int indChang;
		char temp;
		if (direction == 'G') // Gauche / Haut
		{	
			indChang = 1;
			if (axe)          // Ligne
			{
				for(int cpt=0;cpt<5;cpt++) 
				{
					temp = this.plateauJeu[num][cpt];
					this.plateauJeu[num][cpt] = this.plateauJeu[num][indChang];
					this.plateauJeu[num][indChang]= temp;

					indChang++;
				}
			}
			else             // Colonne
			{
				for(int cpt=0;cpt<5;cpt++) 
				{
					temp = this.plateauJeu[cpt][num];
					this.plateauJeu[cpt][num] = this.plateauJeu[indChang][num];
					this.plateauJeu[indChang][num]= temp;

					indChang++;
				}
			}
		}
		else                // Droite / bas
		{
			indChang = 4;
			if (axe)        // Ligne
			{
				for(int cpt=5;cpt>0;cpt--) 
				{
					temp = this.plateauJeu[num][cpt];
					this.plateauJeu[num][cpt] = this.plateauJeu[num][indChang];
					this.plateauJeu[num][indChang]= temp;

					indChang--;
				}
				
			}
			else           // Colonne
			{
				for(int cpt=5;cpt>0;cpt--) 
				{
					temp = this.plateauJeu[cpt][num];
					this.plateauJeu[cpt][num] = this.plateauJeu[indChang][num];
					this.plateauJeu[indChang][num]= temp;

					indChang--;
				}
			}
		}


	}

	
	
}

