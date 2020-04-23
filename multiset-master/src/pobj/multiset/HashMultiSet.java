package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	
	private Map<T,Integer> map;
	private int size;
	
	public HashMultiSet() {
		map = new HashMap<T,Integer>();
		size=0;
	}
	
	public HashMultiSet(Collection<T> coll) {
		size=0;
		map = new HashMap<T,Integer>(coll.size());
		for( T t : coll) {
			if(map.containsKey(t)) {
				int v = map.get(t)+1;
				map.put(t, v);
				size++;
			}else {
				map.put(t,1);
				size++;
			}
		}
	}
	
	public Iterator<T> iterator() {
		return new HashMultiSetIterator();
	}
	
	private class HashMultiSetIterator implements Iterator<T>{
		private Iterator<Map.Entry<T, Integer>> it;
		private int compteur;
		private T value;
		private int t;
		
		public HashMultiSetIterator(){
			it =map.entrySet().iterator();
			compteur=0;
			t=0;
		}
		
		public T next() {	
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if(compteur==0) {
				Map.Entry<T, Integer> next = it.next();
				value= next.getKey();
				compteur=next.getValue();	
			}
			compteur--;
			t++;
			return value;		
		}

		@Override
		public boolean hasNext() {
			if(t<size) {
				return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean add(T e, int count) {
		if (count<=0) {
			throw new IllegalArgumentException();
		}
		if(map.containsKey(e)){
			int v = (int)map.get(e);
			v+=count;
			map.put(e,v);
		}
		else {
			map.put(e,count);
		}
		size+=count;
		assert(isConsistent());
		return true;
	}

	@Override
	public boolean add(T e) {
		if(map.containsKey(e)){
			int v = (int)map.get(e);
			v+=1;
			map.put(e,v);
		}
		else {
			map.put(e,1);
		}
		size++;
		assert(isConsistent());
		return true;
	}

	@Override
	public boolean remove(Object e) {
		if(map.containsKey(e)){
			int v = map.get(e)-1;
			map.put((T)e, v);
			size--;
			assert(isConsistent());
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object e, int count) {
		if (count<=0) {
			throw new IllegalArgumentException();
		}
		if(map.containsKey(e)){
			int v = (int)map.get(e);
			v-=count;
			if(v>=0) {			
				map.put((T)e,v);
				size-=count;
				assert(isConsistent());
				return true;
			}
		}
		return false;
	}

	@Override
	public int count(T o) {
		if(map.get(o) == null) {
			return 0;
		}
		return (int)map.get(o);
		
	}

	@Override
	public void clear() {
		map.clear();
		size=0;
		assert(isConsistent());
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<T> elements() {
		Set<T> keySet = map.keySet();
		List<T> liste = new ArrayList<T>(keySet);		
		return liste;
	}
	
	public String toString() {
		Iterator<Map.Entry<T, Integer>> it = map.entrySet().iterator();
		StringBuilder b = new StringBuilder();
		b.append("[");
		while(it.hasNext()) {
			Map.Entry<T, Integer> next = it.next();
			String s = " "+next.getKey()+":"+ next.getValue()+";";
			b.append(s);
		}
		b.replace(b.length()-1,b.length(),"]");
		b.delete(1,2);

		return b.toString();
	}
	
	public boolean isConsistent() {
		Iterator<Map.Entry<T, Integer>> it = map.entrySet().iterator();
		int taille=0;
		while(it.hasNext()) {
			Map.Entry<T, Integer> next = it.next();
			taille+=next.getValue();
		}
		return taille==size;
	}
}
