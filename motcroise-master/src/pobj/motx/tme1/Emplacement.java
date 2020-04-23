package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class Emplacement {
	private List<Case> lettres;
	
	public Emplacement() {
		lettres = new ArrayList<Case>();
	}
	public String toString() {
		String s="";
		for(int i=0; i<lettres.size(); i++){
			s+=lettres.get(i).getChar();
		}
		return s;
	}
	
	public int size() {
		return lettres.size();
	}
	
	public void add(Case c) {
		lettres.add(c);
	}
	
	public List<Case> getLettres() {
		return lettres;
	}
	
	public Case getCase(int i) {
		return lettres.get(i);
	}
	
	public boolean hasCaseVide() {
		for(Case c : lettres) {
			if(c.isVide()) 
				return true;	
		}
		return false;
	}
	
}
