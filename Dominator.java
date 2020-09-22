/**
* The Dominator program produces dominator sets for all nodes in a connected digraph
* The dominator of the start node is the start node itself.
* The set of dominators for any other node n is the intersection of 
* the set of dominators for all predecessors p of n. 
* The node n is also in the set of dominators for n.
* private Map<Character, Set<Character>> preds contains immidiate predecessors of
* all the nodes. Just in the case of root node, it is also an immidiate predecessor of itself.
*
* @author  Hetul Bhatt
* @version 1.0
* @since   2020-09-22 
*/

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Dominator {
	private char root;
	private Map<Character, Set<Character>> adjList;
	private Map<Character, Set<Character>> dominator;
	private Map<Character, Set<Character>> preds;

	public Dominator() {
		this.initializeAdjecencyList();
		this.initializeDominator();
		this.immidiatePredMaker();
		this.finalizeDominator();
	}

	public final Scanner scannerFactory() throws Exception {
		File file;
		System.out.println("Select input file");
		System.out.println("For input1.txt enter: 1");
		System.out.println("For input2.txt enter: 2");
		System.out.println("For System.in (Manual input) enter: 3");
		System.out.print("Enter choice: ");
		Scanner sc = new Scanner(System.in);
		int option = Integer.parseInt(sc.nextLine());
		switch(option) {
			case 1:
				file = new File("input1.txt");
				sc = new Scanner(file);
				break;
			case 2:
				file = new File("input2.txt");
				sc = new Scanner(file);
				break;
			case 3:
				break;
			default:
				throw new IndexOutOfBoundsException();
		}
		return sc;
	}

	public final void initializeAdjecencyList() {
		this.adjList = new TreeMap<>();
		Scanner sc = null;
		try {
			sc = this.scannerFactory();
			System.out.print("\nNumber of nodes: ");
			int n = Integer.parseInt(sc.nextLine());

			for (int i = 0; i < n; ++i) {
				System.out.print("\nNode " + (i + 1) + ": ");
				char key = sc.nextLine().charAt(0);
				System.out.println("\nEnter all the nodes adjecent to " + key + ". Enter '#' to terminate entering.");
				char c;
				Set<Character> value = new TreeSet<>();
				while ((c = sc.nextLine().charAt(0)) != '#') {
					value.add(c);
				}
				this.adjList.put(key, value);
				if (i == 0) {
					this.root = key;
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("FileNotFoundException occured. Check if input file is present in the current directory.");
			System.exit(1);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Exception occured. Enter valid input.");
			System.exit(2);
		} catch(NumberFormatException e) {
			System.out.println("Exception occured. Enter valid input.");
			System.exit(2);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(3);
		} finally {
			sc.close();
		}
	}

	public final void flush(String message, Map<Character, Set<Character>> map) {
		System.out.println(message);
		for (Map.Entry<Character, Set<Character>> e : map.entrySet()) {
			System.out.println(e.getKey() + " => " + e.getValue());
		}
	}

	public final void initializeDominator() {
		this.dominator = new TreeMap<>();
		Set<Character> set = adjList.keySet();

		for (Map.Entry<Character, Set<Character>> e : this.adjList.entrySet()) {
			this.dominator.put(e.getKey(), set);
		}

		Set<Character> onechar = new TreeSet<>();
		onechar.add(this.root);
		this.dominator.put(this.root, onechar);
	}

	public final void immidiatePredMaker() {
		this.preds = new TreeMap<>();
		Set<Character> rootSet = new TreeSet<>();
		rootSet.add(this.root);
		this.preds.put(this.root, rootSet);

		for (Map.Entry<Character, Set<Character>> e : this.adjList.entrySet()) {
			Character parent = e.getKey();
			for (Character c : e.getValue()) {
				if (this.preds.containsKey(c)) {
					Set<Character> temp = this.preds.get(c);
					temp.add(parent);
					this.preds.put(c, temp);
				} else {
					Set<Character> temp = new TreeSet<>();
					temp.add(parent);
					this.preds.put(c, temp);
				}
			}
		}
	}

	public final void finalizeDominator() {
		for (int i = 0; i < this.adjList.size(); i++) {
			for (Character c : adjList.keySet()) {
				Set<Character> set = new TreeSet<>(adjList.keySet());
				for (Character x : this.preds.get(c)) {
					set.retainAll(this.dominator.get(x));
				}
				set.add(c);
				this.dominator.put(c, set);
			}
		}
	}

	public static void main(String[] args) {
		Dominator d = new Dominator();
		d.flush("\nAdjacency List:",d.adjList);
		d.flush("\nImmidiate Predecessors:",d.preds);
		d.flush("\nDominator sets:",d.dominator);
	}
}