package models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase encargada de el manejo de todas las clases del modelo
 * aqui se instancian cada una de ellas y esta clase es la que va al controlador
 */

public class Grammar {

	private ArrayList<String> terminals, noTerminals;
	private String axiomticSymbol;
	private ArrayList<Production> productions;
	private GrammarTree grammarTree;
	private boolean isWordInGrammar;

	public Grammar(ArrayList<String> terminals, ArrayList<String> noTerminals, String axiomticSymbol,
			ArrayList<Production> productions) {
		super();
		this.terminals = terminals;
		this.noTerminals = noTerminals;
		this.axiomticSymbol = axiomticSymbol;
		this.productions = productions;
		isWordInGrammar = true;
		grammarTree = new GrammarTree(axiomticSymbol, noTerminals, productions);
	}

	public String getAxiomticSymbol() {
		return axiomticSymbol;
	}

	public ArrayList<String> getNoTerminals() {
		return noTerminals;
	}

	public ArrayList<Production> getProductions() {
		return productions;
	}

	public ArrayList<String> getTerminals() {
		return terminals;
	}

	public void searchWord(String word) {
		grammarTree.searchWord(word);
	}

	public TreeWord getTree() {
		ArrayList<String> pathWord = grammarTree.getPathWord();
		Collections.reverse(pathWord);
		if (pathWord.size() == 0) {
			isWordInGrammar = false;
		}else {
			isWordInGrammar = true;
		}
		TreeWord treeWord = new TreeWord();
		for (String pw : pathWord) {
			if (pw.length() == 1) {
				treeWord.add("" + pw.charAt(0), "", pathWord.get(0));
			} else {
				if (noTerminal(pw).equals("")) {
					treeWord.add( "" + pw, "", noTerminal(pw));
				} else {
					treeWord.add( "" + pw.charAt(0), "" + pw.charAt(1), noTerminal(pw));
				}
			}
		}
		return treeWord;
	}

	private String noTerminal(String word) {
		for (String nt : noTerminals) {
			for (int i = 0; i < word.length(); i++) {
				if (nt.equals("" + word.charAt(i))) {
					return nt;
				}
			}
		}
		return "";
	}


	public GrammarTree getGrammarTree() {
		return grammarTree;
	}

	public boolean isWordInGramar() {
		return isWordInGrammar;
	}
}
