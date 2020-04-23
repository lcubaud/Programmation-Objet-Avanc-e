package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {
		private List<String> mots = new ArrayList<>();
		private EnsembleLettre[] cache=null;

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}
	
	public static Dictionnaire loadDictionnaire(String path) {
		Dictionnaire dict = new Dictionnaire();
		try(BufferedReader br =new BufferedReader(new FileReader(path))) {
			for(String line = br.readLine() ; line !=null; line = br.readLine() ) {
				dict.add(line);	
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return dict;
	}

	
	public int filtreParLettre(char c,int i) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.charAt(i)==c)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}
	

	
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
	
	public EnsembleLettre calculEns(int m, int c) {
		EnsembleLettre res = new EnsembleLettre();
		
		for (String mot: mots) {
			res.add(mot.charAt(c));
		}
		return res;
	}
	
	public int filtreParEnsemble (int i, EnsembleLettre e) {
		
		int supp = 0;		
		List<String> cible = new ArrayList<>();
		for (String mot : mots) {
			if (e.contains(mot.charAt(i))) {
					cible.add(mot);
			} else {
				supp++;
			}
		}			
		mots = cible;
		return supp;
	
	}
}
