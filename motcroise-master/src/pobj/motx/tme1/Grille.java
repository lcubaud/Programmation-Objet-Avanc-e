package pobj.motx.tme1;


public class Grille {
	private Case[][] matrice;
	private int hauteur,largeur;
	
	public Grille(int hauteur, int largeur) {
		matrice = new Case[hauteur][largeur];
		for(int i=0;i<hauteur;i++) {
			for(int j=0;j<largeur;j++) {
				matrice[i][j]=new Case(i,j,' ');
			}
		}
		this.hauteur=hauteur;
		this.largeur=largeur;
	}
	
	public Case getCase(int lig, int col) {
		if(matrice[lig][col].getLig() == lig && matrice[lig][col].getCol() == col)
			return matrice[lig][col];
		return null;
	}
	
	public String toString(){
		return GrilleLoader.serialize(this, false);
	}
	
	public int nbLig() {
		return hauteur;
	}
	
	public int nbCol() {
		return largeur;
	}
	
	public Grille copy() {
		Grille copy = new Grille(hauteur,largeur);
		for(int i=0;i<hauteur;i++) {
			for(int j=0;j<largeur;j++) {
				char car = matrice[i][j].getChar();
				copy.matrice[i][j].setChar(car);
			}
		}
		return copy;
	}
}
