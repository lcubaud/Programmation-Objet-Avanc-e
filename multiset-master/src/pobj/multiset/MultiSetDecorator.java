package pobj.multiset;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T>{
	
	private MultiSet<T> decorated;
	
	public MultiSetDecorator(MultiSet<T> d) {
		decorated = d;
	}
	

	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}

	@Override
	public boolean add(T e, int count) {
		boolean b = decorated.add(e,count);
		assert(isConsistent());
		return b;
	}

	@Override
	public boolean add(T e) {
		boolean b = decorated.add(e);
		assert(isConsistent());
		return b;
	}

	@Override
	public boolean remove(Object e) {
		boolean b = decorated.remove(e);
		assert(isConsistent());
		return b;
	}

	@Override
	public boolean remove(Object e, int count) {
		boolean b = decorated.remove(e,count);
		assert(isConsistent());
		return b;
	}

	@Override
	public int count(T o) {
		return decorated.count(o);
	}

	@Override
	public void clear() {
		decorated.clear();
	}

	@Override
	public int size() {
		return decorated.size();
	}

	@Override
	public List<T> elements() {
		return decorated.elements();
	}
	
	public boolean isConsistent() {
		int taille=0;
		List<T> elem = elements();
		for(T e : elem) {
			taille+=count(e);
		}
		if(taille==size()) {
			return true;
		}else {
			throw new InternalError();
		}
	}
	
}
