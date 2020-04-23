package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	
	private List<T> liste;
	
	public NaiveMultiSet(){
		liste=new ArrayList<T>();
	}
	
	@Override
	public boolean add(T e, int count) {
		for(int i=0;i<count;i++) {
			liste.add(e);
		}
		return true;
	}
	
	public boolean add(T e) {
		liste.add(e);
		return true;
	}

	@Override
	public boolean remove(Object e, int count) {
		for(int i=0;i<count;i++) {
			liste.remove(e);
		}
		return true;
	}

	@Override
	public int count(T o) {
		int cpt=0;
		for(T elem : liste) {
			if(elem.equals(o))
				cpt++;
		}
		return cpt;
	}

	@Override
	public List<T> elements() {
		Set<T> keySet = new HashSet<>(liste);
		List<T> l = new ArrayList<T>(keySet);		
		return l;	}

	@Override
	public Iterator<T> iterator() {
		return new NaiveMultiSetIterator();
	}

	@Override
	public int size() {
		return liste.size();
	}
	
	class NaiveMultiSetIterator implements Iterator<T>{
		
		private int compteur;
		
		public NaiveMultiSetIterator() {
			compteur=0;
		}
		
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			compteur++;
			return liste.get(compteur);

		}
		
		public boolean hasNext() {
			return compteur<liste.size();
		}
	}
	
	

}
