package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Kombinacije <T> {

	private final int[] indexArray;
	private int k;

	public Kombinacije(int totalNumberOfItems) {
		checkTotalNumberOfItems(totalNumberOfItems);
		this.indexArray = new int[totalNumberOfItems];
	}

	public boolean generateNextCombination() {
		if (k == 0) {
			k = 1;
			return true;
		}

		if (indexArray[k - 1] < indexArray.length - 1) {
			indexArray[k - 1]++;
			return true;
		}

		for (int i = k - 2; i >= 0; --i) {
			if (indexArray[i] < indexArray[i + 1] - 1) {
				indexArray[i]++;

				for (int j = i + 1; j < k; ++j) {
					indexArray[j] = indexArray[j - 1] + 1;
				}

				return true;
			}
		}

		++k;

		if (k > indexArray.length) {
			return false;
		}

		for (int i = 0; i < k; ++i) {
			indexArray[i] = i;
		}

		return true;
	}

	public void loadCombination(Map<String, String> listaAtributa, Map<String, String> target) {
		Objects.requireNonNull(listaAtributa, "The list being sampled is null.");
		Objects.requireNonNull(target, "The list to hold the combination is null.");
		
		target.clear();
	
		List<String> list = new ArrayList<>(listaAtributa.keySet());
		List<String> list2 = new ArrayList<>(listaAtributa.values());
		
		 for (int i = 0; i < k; ++i) { 
			target.put(list.get(indexArray[i]),list2.get(indexArray[i]));
	        }
	}

	private void checkTotalNumberOfItems(int totalNumberOfItems) {
		if (totalNumberOfItems < 1) {
			throw new IllegalArgumentException(
					"Total number of items is illegal: " + 
							totalNumberOfItems + ".");
		}
	}


	static public List<Map<String, String>> sloziKombinacije(Map<String, String> listaAtributa){
		Map<String, String> combinationHolder = new LinkedHashMap<String, String>();
		Kombinacije<String> generator = new Kombinacije<>(listaAtributa.size());
		List<Map<String, String>> rez=new LinkedList<Map<String, String>>();
		
		while (generator.generateNextCombination()) {
			generator.loadCombination(listaAtributa, combinationHolder);
			LinkedHashMap<String, String> novalista= new LinkedHashMap<String, String>(combinationHolder);
			rez.add(novalista);
		}
		return rez;
	}
}


