package pobj.motx.tme2;
import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.*;

public class GrillePotentiel {
	
	private GrillePlaces grille;
	private Dictionnaire dico;
	private List<Dictionnaire> motsPot;
	private List<IContrainte> contraintes;

	public GrillePotentiel (GrillePlaces grille, Dictionnaire dicoComplet) {
		this.grille = grille;
		dico = dicoComplet;
		motsPot = new ArrayList<Dictionnaire>();
		contraintes = new ArrayList<IContrainte>();
		
		for(Emplacement e : grille.getPlaces()) {			
			int taille = e.size();			
			Dictionnaire dicoTemp = dicoComplet.copy();			
			dicoTemp.filtreLongueur(taille);			
			for (int i = 0; i < taille; i ++) {
				if (!e.getCase(i).isVide()) {
					dicoTemp.filtreParLettre(e.getCase(i).getChar(), i);
				}
			}
			motsPot.add(dicoTemp.copy());
		}		
		int nbHoriz = grille.getNbHorizontal();	
		for (int mh = 0; mh < nbHoriz; mh++) {
			for (int mv = nbHoriz; mv < grille.getPlaces().size(); mv++) {
				Emplacement l1 = grille.getPlaces().get(mh);
				Emplacement l2 = grille.getPlaces().get(mv);
				for (int ch = 0; ch < l1.size(); ch++) {
					for (int cv = 0; cv < l2.size(); cv++) {
						if ( l1.getCase(ch) == l2.getCase(cv) && l1.getCase(ch).isVide()) {
							contraintes.add(new CroixContrainte(mh, ch, mv, cv));
						}
					}
				}
			}
		}
		
		for (IContrainte ce : contraintes) {
			ce.reduce(this);
		}
		
		propage();
		
	}
	
	public GrillePotentiel (GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> listDico) {
		this.grille = grille;
		dico = dicoComplet;
		motsPot = new ArrayList<Dictionnaire>();
		contraintes = new ArrayList<IContrainte>();
		
		for(Emplacement e : grille.getPlaces()) {
			int taille = e.size();
			Dictionnaire dicoTemp = listDico.get(grille.getPlaces().indexOf(e));			
			for (int i = 0; i < taille; i ++) {			
				if (!e.getCase(i).isVide()) {
					dicoTemp.filtreParLettre(e.getCase(i).getChar(), i);
				}
			}	
			motsPot.add(dicoTemp.copy());
		}
		
		int nbHoriz = grille.getNbHorizontal();
		
		for (int mh = 0; mh < nbHoriz; mh++) {
			for (int mv = nbHoriz; mv < grille.getPlaces().size(); mv++) {
				Emplacement l1 = grille.getPlaces().get(mh);
				Emplacement l2 = grille.getPlaces().get(mv);

				for (int ch = 0; ch < l1.size(); ch++) {
					for (int cv = 0; cv < l2.size(); cv++) {
						if ( l1.getCase(ch) == l2.getCase(cv) && l1.getCase(ch).isVide()) {
							contraintes.add(new CroixContrainte(mh, ch, mv, cv));
						}
					}
				}
			}
		}
		
		for (IContrainte ce : contraintes) {
			ce.reduce(this);
		}
		
		propage();	
	}
	
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	
	public boolean isDead() {
		for(Dictionnaire d : motsPot)
			if (d.size() == 0) return true;
		return false;
	}
	
	public GrillePotentiel fixer(int m, String soluce) {
		return new GrillePotentiel(grille.fixer(m, soluce), dico);
	}
	
	public List<IContrainte> getContraintes() {
		return contraintes;
	}
	
	private boolean propage() {	
		while(true) {
			int cpt = 0;
			for (IContrainte ce : contraintes) {
				cpt += ce.reduce(this);
			}
			if (this.isDead())
				return false;
			if (cpt == 0)
				return true;
		}
	}
	
	public GrillePlaces getGrillePlaces() {
		return grille;
	}
}
