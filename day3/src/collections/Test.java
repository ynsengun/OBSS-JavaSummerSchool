package collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {

		Set<String> hashSet = new HashSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/yusuf/OBSS/day3/src/collections/tmp.txt"))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				for (int i = 0; i < words.length; i++) {
					hashSet.add(words[i]);
				}
			}

			System.out.println(hashSet.size());

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Set<String> treeSet = new TreeSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/yusuf/OBSS/day3/src/collections/tmp.txt"))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				for (int i = 0; i < words.length; i++) {
					treeSet.add(words[i]);
				}
			}

			System.out.println(hashSet.size());
			for (String item : treeSet) {
				System.out.print(item + " ");
			}
			System.out.println();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Map<Word, Integer> hashMap = new HashMap<Word, Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/yusuf/OBSS/day3/src/collections/tmp.txt"))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				for (int i = 0; i < words.length; i++) {
					treeSet.add(words[i]);
				}
			}

			System.out.println(hashSet.size());
			for (String item : treeSet) {
				System.out.print(item + " ");
			}
			System.out.println();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
