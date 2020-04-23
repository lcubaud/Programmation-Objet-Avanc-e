package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.*;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP {
	
	private List<DicoVariable> dico;
	private GrillePotentiel gp;
	
	public MotX(GrillePotentiel gp) {
		this.gp=gp;
		dico = new ArrayList<DicoVariable>();
		List<Emplacement> emp = gp.getGrillePlaces().getPlaces();
		for(int i=0; i<emp.size();i++) {
			if(emp.get(i).hasCaseVide()) {
				dico.add(new DicoVariable(i,gp));
			}
		}
	}
	
	@Override
	public List<IVariable> getVars(){
		List<IVariable> temp = new ArrayList<IVariable>();
		for (DicoVariable d : dico) {
			temp.add(d);
		}
		return temp;
	}

	@Override
	public boolean isConsistent() {
		return !(gp.isDead());
	}

	@Override
	public ICSP assign(IVariable vi, String val) {
		if (vi instanceof DicoVariable) {
			DicoVariable ve = (DicoVariable) vi;
			gp = gp.fixer(ve.getIndex(), val);
		}
		
		return new MotX(gp);
	}
	
	public String toString() {
		return GrilleLoader.serialize(gp.getGrillePlaces().getGrille(), false);
	}

}
