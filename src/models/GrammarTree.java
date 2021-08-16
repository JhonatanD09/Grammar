package models;

import java.util.ArrayList;

public class GrammarTree {

	private NodeProduction root, rootTreeWord;
	private ArrayList<Production> productions;
	private ArrayList<String> noTerminals;
	private ArrayList<String> pathWord;

	public GrammarTree(String initialSymbol, ArrayList<String>noTerminals, ArrayList<Production> productions) {
		this.root = new NodeProduction(initialSymbol);
		this.rootTreeWord = new NodeProduction(initialSymbol);
		this.productions = productions;
		this.noTerminals = noTerminals;
		this.pathWord = new ArrayList<String>();
		addProductions();
	}

	public NodeProduction getRoot() {
		return root;
	}
	
	public void searchWord(String word) {
		int limit = ( word.length()+ noTerminals.size());
		treeWord(rootTreeWord,1,limit,word);	
		System.out.println(pathWord.toString());
	}

	private void addProductions() {
		addProductions(root, 1, 5);
	}

	private void addProductions(NodeProduction nodeProduction, int count, int limit) {
		for (Production production : productions) {
			for (int i = 0; i < nodeProduction.getProduction().length(); i++) {
				if (production.getProducer().equals(String.valueOf(nodeProduction.getProduction().charAt(i)))) {
					nodeProduction.addChild(nodeProduction.getProduction().replace(production.getProducer(),
							production.getProduction()));
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
	
	private void treeWord(NodeProduction nodeProduction, int count, int limit, String word) {
		for (Production production : productions) {
			for (int i = 0; i < nodeProduction.getProduction().length(); i++) {
				if (production.getProducer().equals(String.valueOf(nodeProduction.getProduction().charAt(i)))) {
					String temp = nodeProduction.getProduction().replace(production.getProducer(),
							production.getProduction());
					nodeProduction.addChild(temp);
					if (temp.equals(word)) {
						pathWord.add(temp);
						pathWord = createPath(nodeProduction);
						return;
					}
				}
			}
		}
		if (count < limit) {
			count++;
			for (NodeProduction production : nodeProduction.getChilds()) {
				 treeWord(production, count, limit,word);
			}
		}
	}

	private ArrayList<String> createPath(NodeProduction nodeProduction) {
		ArrayList<String> path= new ArrayList<String>();
		NodeProduction aux = nodeProduction;
		System.out.println(aux.getProduction());
		while (aux != rootTreeWord) {
			path.add(aux.getProduction());
			aux = searchDad(aux);
		}
		path.add(rootTreeWord.getProduction());
		return path;
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


}
