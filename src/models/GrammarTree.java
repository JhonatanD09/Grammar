package models;

import java.util.ArrayList;

public class GrammarTree {

	private NodeProduction root, rootTreeWord;
	private ArrayList<Production> productions;
	private ArrayList<String> noTerminals;
	private ArrayList<String> pathWord;
	private String temp;
	private int count;

	public GrammarTree(String initialSymbol, ArrayList<String> noTerminals, ArrayList<Production> productions) {
		this.root = new NodeProduction(initialSymbol);
		this.rootTreeWord = new NodeProduction(initialSymbol, initialSymbol);
		this.productions = productions;
		this.noTerminals = noTerminals;
		temp = "";
		addProductions();
	}

	public NodeProduction getRoot() {
		return root;
	}

	public void searchWord(String word) {
		this.pathWord = new ArrayList<String>();
		int limit = (word.length() + noTerminals.size());
		treeWord(rootTreeWord, limit, word);
		System.out.println(pathWord.toString());
	}

	private void addProductions() {
		addProductions(root, 1, 5);
	}

	private void addProductions(NodeProduction nodeProduction, int count, int limit) {
		for (Production production : productions) {
			for (int i = 0; i < nodeProduction.getProduction().length(); i++) {
				if (production.getProducer().equals(String.valueOf(nodeProduction.getProduction().charAt(i)))) {
					String temp = nodeProduction.getProduction().replace(production.getProducer(),
							production.getProduction());
					nodeProduction.addChild(temp);
				}
			}
		}
		if (count < limit) {
			count++;
			for (NodeProduction production : nodeProduction.getChilds()) {
				addProductions(production, count, limit);
			}
		}
	}

	private void treeWord(NodeProduction nodeProduction, int limit, String word) {
		for (Production production : productions) {
			for (int i = 0; i < nodeProduction.getProduction().length(); i++) {
				if (production.getProducer().equals(String.valueOf(nodeProduction.getProduction().charAt(i)))) {
					temp = nodeProduction.getProduction().replace(production.getProducer(), production.getProduction());
					nodeProduction.addChild(temp, production.getProduction());
				}
				if (temp.equals(word)) {
					pathWord.add(production.getProduction());
					pathWord = createPath(nodeProduction);
					count = limit;
					return;
				}
			}
			if (count == limit) {
				return;
			}
		}
		if (count < limit) {
			count++;
			for (NodeProduction production : nodeProduction.getChilds()) {
				treeWord(production, limit, word);
			}
		}
	}

	private ArrayList<String> createPath(NodeProduction nodeProduction) {
		NodeProduction aux = nodeProduction;
		while (aux != rootTreeWord) {
			pathWord.add(aux.getProducer());
			aux = searchDad(aux);
		}
		pathWord.add(rootTreeWord.getProducer());
		return pathWord;
	}

	public NodeProduction searchDad(NodeProduction id) {
		for (int i = 0; i < rootTreeWord.getChilds().size(); i++) {
			if (rootTreeWord.searchChild(id)) {
				return rootTreeWord;
			}
		}
		return searchDad(rootTreeWord, id);
	}

	private NodeProduction searchDad(NodeProduction node, NodeProduction nodeChild) {
		if (!node.getChilds().isEmpty()) {
			for (NodeProduction actual : node.getChilds()) {
				if (actual.searchChild(nodeChild)) {
					return actual;
				} else {
					NodeProduction search = searchDad(actual, nodeChild);
					if (search != null) {
						return search;
					}
				}
			}
		}
		return null;

	}
	
	public ArrayList<String> getPathWord() {
		return pathWord;
	}

}
