package models;

import java.util.ArrayList;

public class GrammarTree {

	private NodeProduction root, rootTreeWord;
	private ArrayList<Production> productions;
	private ArrayList<String> noTerminals;

	public GrammarTree(String initialSymbol, ArrayList<String>noTerminals, ArrayList<Production> productions) {
		this.root = new NodeProduction(initialSymbol);
		this.rootTreeWord = new NodeProduction(initialSymbol);
		this.productions = productions;
		this.noTerminals = noTerminals;
		addProductions();
	}

	public NodeProduction getRoot() {
		return root;
	}
	
	public void searchWord(String word) {
		if (treeWord(rootTreeWord,1,(word.length()+noTerminals.size()),word)!=null) {			
			System.out.println(treeWord(rootTreeWord,1,( word.length()+ noTerminals.size()),word).toString());
		}else {
			System.out.println("F");
		}
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
		valideLimitTree(nodeProduction, count, limit);
	}
	
	private ArrayList<String> treeWord(NodeProduction nodeProduction, int count, int limit, String word) {
		System.out.println(limit);
		ArrayList<String> treeWord= new ArrayList<String>();
		for (Production production : productions) {
			for (int i = 0; i < nodeProduction.getProduction().length(); i++) {
				if (production.getProducer().equals(String.valueOf(nodeProduction.getProduction().charAt(i)))) {
					String temp = nodeProduction.getProduction().replace(production.getProducer(),
							production.getProduction());
					nodeProduction.addChild(temp);
					System.out.println("antes de holisss");
					System.out.println(temp+ " " + word);
//					if (temp.equals(word)) {
//						System.out.println("Holisss");
//						treeWord.add(temp);
//						createPath(nodeProduction,treeWord);
//						return treeWord;
//					}
				}
			}
		}
		if (count < limit) {
			count++;
			for (NodeProduction production : nodeProduction.getChilds()) {
				return treeWord(production, count, limit,word);
			}
		}
		
		return null;
	}

	private void createPath(NodeProduction nodeProduction, ArrayList<String> treeWord) {
		NodeProduction aux = nodeProduction;
		System.out.println(aux.getProduction());
		while (aux != rootTreeWord) {
			treeWord.add(aux.getProduction());
			aux = searchDad(aux);
		}
		treeWord.add(rootTreeWord.getProduction());
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

	private void valideLimitTree(NodeProduction nodeProduction, int count, int limit) {
		if (count < limit) {
			count++;
			for (NodeProduction production : nodeProduction.getChilds()) {
				addProductions(production, count, limit);
			}
		}
	}

}
