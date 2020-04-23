package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.*;

public class DicoVariable implements IVariable {
	
	private int index;
	private GrillePotentiel gp;
	
	public DicoVariable (int index, GrillePotentiel gp) {
		this.index=index;
		this.gp=gp;
	}
	
	@Override
	public List<String> getDomain() {
		List<String> list = new ArrayList<String>();
		Dictionnaire d = gp.getMotsPot().get(index);
		for(int i=0; i<d.size();i++) {
			list.add(d.get(i));
		}
		return list;
	}
	
	public String toString() {
		String s="";
		List<String> list = getDomain();
		for(String m : list){
			s+=m+"\n";
		}
		return s;
	}
	
	public int getIndex() {
		return index;
	}

}
