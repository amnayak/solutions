import java.util.*;

public class solutions{
	public static void main (String [] args){

		String input = "exa-mple";
		String rule_char_set = "ae";

		int[] a = {10, 20, 30, 40, 51, 61, 71};
		int[] b = {15, 25, 31, 86, 600, 700, 900};

		solutions s = new solutions();

		ArrayList<String> sols = s.enumerate(input, rule_char_set); //These are the solutions

		//Print out solutions
		for (String str : sols){
			System.out.println(str);
		}

		//Print out solutions
		System.out.println(Arrays.toString(s.findMedian(a,b)));
	}

	public ArrayList<String> enumerate (String input, String rule_char_set){
		ArrayList<String> list = new ArrayList<String>();

		//Parallel Array indicating if character is permutable as per ruleset.
		int[] perm_array = new int[input.length()];

		for (int i = 0; i<input.length(); i++){
			char in = input.charAt(i);
			//If this char is found in the rule_char_set, it is permutable
			for (int j = 0; j<rule_char_set.length(); j++){
				if (in == rule_char_set.charAt(j)){
					perm_array[i] = 1; //Set corresponding index to 1.
					j = rule_char_set.length();
				}
			}
		}

		findPermute(input,0,perm_array,"",list);

		return list;
	}

	void findPermute(String input, int index, int[] toPerm, String current, ArrayList list){
			if (index == input.length()){ //no more chars left, add to list
				list.add(current);
				return;
			} else if (toPerm[index] != 1){ //You can't permute the current letter
				//Move onto next letter...
				findPermute(input, index + 1, toPerm, current.concat(input.substring(index,index+1)), list);
			} else { //current letter is permutable, branch out into two possibilities
				String c = input.substring(index,index+1);
				findPermute(input, index + 1, toPerm, current.concat(c.toUpperCase()), list);
				findPermute(input, index + 1, toPerm, current.concat(c.toLowerCase()), list);
			}
	}

	int[] findMedian(int[] a, int[] b){
		return findMedianSub(a,b, 0, a.length, 0, b.length);
	}

	int[] findMedianSub(int[] a, int[] b, int alo, int ahi, int blo, int bhi){
		int medA = a[((ahi-alo)/2)+alo];
		int medB = b[((bhi-blo)/2)+blo];

		if (medA == medB){
			int[] res = {medA,medB};
			return res;
		} else if ((ahi-alo) == 1 && (bhi-blo) == 1){
			int min = a[alo];
			int max = b[blo];
			if (b[blo] > a[alo]){
				min = b[blo];
			}
			if (b[bhi] > a[ahi]){
				max = a[ahi];
			}
			int[] res = {min,max};
			return res;

		} else if (medA > medB){
			return findMedianSub(a,b, alo, (((ahi-alo)/2)+alo), (((bhi-blo)/2)+blo), bhi);
		} else { //(medB > medA)
			return findMedianSub(a,b, (((ahi-alo)/2)+alo), ahi, blo, (((bhi-blo)/2)+blo));
		}

	}

}
