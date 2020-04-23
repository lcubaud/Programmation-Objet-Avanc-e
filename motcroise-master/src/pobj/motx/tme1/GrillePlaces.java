package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class GrillePlaces {
	private Grille grille;
	private List<Emplacement> places;
	private int nbMotHori;
	
	public GrillePlaces(Grille grille) {
		this.grille=grille;
		places = new ArrayList<Emplacement>();
		List<Case> cases;
		//mots horizontaux		
		for(int i=0; i<grille.nbLig();i++) {
			cases=getLig(i);
			cherchePlaces(cases);
		}
		nbMotHori=places.size();
		//mots verticaux
		for(int j=0; j<grille.nbCol();j++) {
			cases=getCol(j);
			cherchePlaces(cases);	
		}
		
		
	}
	
	private List<Case> getLig(int lig){
		List<Case> cases = new ArrayList<Case>();
		for(int j=0;j<grille.nbCol();j++)
			cases.add(grille.getCase(lig,j));
		return cases;	
	}
	
	private List<Case> getCol(int col){
		List<Case> cases = new ArrayList<Case>();
		for(int i=0;i<grille.nbLig();i++)
			cases.add(grille.getCase(i,col));
		return cases;
	}
	
	
	private void cherchePlaces(List<Case> cases) {
		Emplacement e = new Emplacement();
		for(int i=0;i<cases.size();i++){
			if(!cases.get(i).isPleine())
				e.add(cases.get(i));
			else{
				if(e.size() > 1)
					places.add(e);
				e = new Emplacement();
			}
		}
		if(e.size() > 1){
			places.add(e);
		}
	}
	
	
	public List<Emplacement> getPlaces(){
		return places; 
	}
	
	public int getNbHorizontal() {
		return nbMotHori;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Emplacement e : places){
			sb.append(e);
		}
		return sb.toString();
	}
	
	public GrillePlaces fixer(int m,String soluce) {
		GrillePlaces gp = new GrillePlaces(grille);
		Emplacement mot=gp.places.get(m);
		for(int i=0;i<mot.size();i++) {
			mot.getLettres().get(i).setChar(soluce.charAt(i));
		}
		return gp;
	}
	
	public Grille getGrille() {
		return grille;
	}
	
	
}
