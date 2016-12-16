package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Kombinacije <T> {

	/**
	 * The array holding indices to elements considered to be in a combination.
	 * Only the first {@code k} indices are considered actually to encode an 
	 * item in a combination.
	 */
	private final int[] indexArray;

	/**
	 * The current number of items in a combination.
	 */
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

	public void loadCombination(LinkedHashMap<String, String> all, LinkedHashMap<String, String> target) {
		Objects.requireNonNull(all, "The list being sampled is null.");
		Objects.requireNonNull(target, 
				"The list to hold the combination is null.");

		target.clear();
	
		/*for (Entry<String, String> set: all.entrySet()) {
			if(i>=k) break; 
			String kljuc=set.getKey();
			String vrijednost=set.getValue();
			System.out.println("kljuc: "+kljuc+"ve: "+vrijednost);
			target.put(kljuc,vrijednost);
			i++;
			
		}*/
		List<String> list = new ArrayList<>(all.keySet());
		List<String> list2 = new ArrayList<>(all.values());
		
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

	public static void main(final String... args) {
		/*List<Map<String, String>> svi= new ArrayList<>();

		Map<String, String> oMap= new HashMap<>();
		oMap.put("ds", "jdksjds");


		Map<String, String> el1 = new HashMap<>();
		el1.put("krvnagrupa", "a");
		Map<String, String> el2 = new HashMap<>();
		el2.put("dna", "ccc");
		Map<String, String> el3 = new HashMap<>();
		el3.put("oruzje", "maè");

		svi.add(el1);
		svi.add(el2);
		svi.add(el3);

		int row = 1;
		Map<String, String> combinationHolder = new HashMap<>();
		Kombinacije<String> generator = new Kombinacije<>(svi.size());

		while (generator.generateNextCombination()) {
			generator.loadCombination(svi, combinationHolder);
			System.out.printf("%2d: %s\n", row++, combinationHolder);
		}*/
	}
	
	
	static public List<LinkedHashMap<String, String>> sloziKombinacije(LinkedHashMap<String,String > svi){
		LinkedHashMap<String, String> combinationHolder = new LinkedHashMap<String, String>();
		Kombinacije<String> generator = new Kombinacije<>(svi.size());
		List<LinkedHashMap<String, String>> rez=new LinkedList<LinkedHashMap<String, String>>();
		
		int i=-1;
		
		while (generator.generateNextCombination()) {
			generator.loadCombination(svi, combinationHolder);
			System.out.println(i+". "+combinationHolder);
			i++;
			LinkedHashMap<String, String> novalista= new LinkedHashMap<String, String>(combinationHolder);
			rez.add(novalista);
		}
		return rez;
	}
}


