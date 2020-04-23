package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pobj.util.Chrono;

public class WordCount {
		
		
		public static void wordcount(MultiSet<String> ms) {		
			try {
				//String file = "data/WarAndPeace.txt";
				String file = "data/text.txt";

				BufferedReader br;
				br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine())!=null) {
					for (String word : line.split("\\P{L}+")) {
						if (word.equals("")) continue;
						ms.add(word);
					}
				}
				br.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println(ms.toString());
			
			List<String> liste = ms.elements();
					
			class MultiSetComparable<T> implements Comparator<T>{
				
				MultiSet<T> ms;
				
				public MultiSetComparable(MultiSet<T> ms) {
					this.ms=ms;
				}

				@Override
				public int compare(T o1, T o2) {
					if(ms.count(o1) > ms.count(o2))
						return -1;
					if(ms.count(o1)==ms.count(o2))
						return 0;
					else
						return 1;
				}
				
			}
			Comparator<String> compare = new MultiSetComparable<String>(ms);
			liste.sort(compare);
			if(liste.size()>=10) {
				for(int i=0;i<10;i++) {
					System.out.println(liste.get(i));
				}
			}else {
				for(String s : liste) {
					System.out.println(s);
				}
			}
					
		}
	
		public static void main(String[] args) {
			/*System.out.println("avec HashMap");
			MultiSet<String> ms = new HashMultiSet<String>();
			MultiSet<String> naif = new NaiveMultiSet<String>();
			Chrono chronoHash = new Chrono();
			wordcount(ms);
			chronoHash.stop();
			System.out.println("avec ArrayList");
			Chrono chronoNaif = new Chrono();
			wordcount(naif);
			chronoNaif.stop();*/
			
			MultiSet<String> ms = new MultiSetDecorator<>(new HashMultiSet<>());
			Chrono chronoHash = new Chrono();
			wordcount(ms);
			chronoHash.stop();
			System.out.println("avec ArrayList");
			MultiSet<String> naif = new MultiSetDecorator<>(new NaiveMultiSet<String>());
			Chrono chronoNaif = new Chrono();
			wordcount(naif);
			chronoNaif.stop();
			
		}
	
}
