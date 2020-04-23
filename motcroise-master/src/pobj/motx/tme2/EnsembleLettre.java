package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

public class EnsembleLettre {
	private List<Character> ens_lettres;
	
	public EnsembleLettre() {
		ens_lettres = new ArrayList<Character>();
	}
	public void add (char c) {
		if (!ens_lettres.contains(c)) 
			ens_lettres.add(c);	
	}
	
	public int size() {
		return ens_lettres.size();
	}
	
	public boolean contains(char c) {		
		return ens_lettres.contains(c);
	}
	
	public EnsembleLettre intersection(EnsembleLettre l2) {
		EnsembleLettre l = new EnsembleLettre();	
		for (int i = 0; i<size(); i++) {
			if (l2.ens_lettres.contains(ens_lettres.get(i)))
				l.add(ens_lettres.get(i));
		}
		return l;
	}
	
	
}
