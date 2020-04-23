package pobj.motx.tme2;

public class CroixContrainte implements IContrainte {

	private int m1,m2,c1,c2;
	
	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.m1=m1;
		this.m2=m2;
		this.c1=c1;
		this.c2=c2;
	}
	
	
	@Override
	public int reduce(GrillePotentiel grille) {
		Dictionnaire d1 = grille.getMotsPot().get(m1);
		Dictionnaire d2 = grille.getMotsPot().get(m2);
		EnsembleLettre l1 = d1.calculEns(m1, c1);
		EnsembleLettre l2 = d2.calculEns(m2, c2);	
		EnsembleLettre s = l1.intersection(l2);
		int motsfiltres=0;
		if(l1.size()>s.size()) {
			motsfiltres += d1.filtreParEnsemble(c1, s);
		}
		if(l2.size()>s.size()) {
			motsfiltres += d2.filtreParEnsemble(c2, s);
		}
		
		return motsfiltres;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof CroixContrainte)) {
			return false;
		}
		CroixContrainte other = (CroixContrainte) o;
		if (c1 != other.c1) {
			return false;
		}
		if (c2 != other.c2) {
			return false;
		}
		if (m1 != other.m1) {
			return false;
		}
		if (m2 != other.m2) {
			return false;
		}
		return true;
	}

}
