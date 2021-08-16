package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Grammar;
import models.Production;
import views.MyJFramePrincipal;

public class Presenter implements ActionListener {

	private MyJFramePrincipal framePrincipal;
	private Grammar grammar;

	public Presenter() {

		framePrincipal = new MyJFramePrincipal(this);
		framePrincipal.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case CREATE_GRAMMAR:
			ArrayList<String> terminals = convert(framePrincipal.getTerminals());
			ArrayList<String> noTerminals = convert(framePrincipal.getNoTerminals());
			String axiomatic = framePrincipal.getAxiomatic();
			ArrayList<Production> productions = convertProductions(framePrincipal.getProductions());
			if (valideSymbolsInProductions(terminals, noTerminals, productions) && valideAxiomaticSymbol(noTerminals,axiomatic)) {
				grammar = new Grammar(terminals, noTerminals, axiomatic, productions);
				grammar.showTree();
				framePrincipal.resetJTextFile();
			} else {
				JOptionPane.showMessageDialog(null,
						"Los simbolos de las producciones no coinciden con los simbolos terminales o no terminales o no hay una produccion que "
						+ "inicie con el simbolo inicial axomatico","Error en la gramatica",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	}

	private boolean valideAxiomaticSymbol(ArrayList<String> noTerminals, String axiomatic) {
		for (String noTerminalsTemp : noTerminals) {
			if (axiomatic.equals(noTerminalsTemp)) {
				return true;
			}
		}
		return false;
	}

	private boolean valideSymbolsInProductions(ArrayList<String> terminals, ArrayList<String> noTerminals,
			ArrayList<Production> productions) {
		boolean temp = false;
		for (Production production : productions) {
			for (int i = 0; i < production.getProduction().length(); i++) {
				if ((valideTerminalsAndNoTerminals(terminals, "" + production.getProduction().charAt(i))
						|| valideTerminalsAndNoTerminals(noTerminals, "" + production.getProduction().charAt(i)))
						&& valideAxiomaticSymbol(noTerminals, production.getProducer())) {
					temp =  true;
				}else {
					temp = false;
				}
			}
		}
		return temp;
	}

	private boolean valideTerminalsAndNoTerminals(ArrayList<String> terminals, String symbol) {
		for (String t : terminals) {
			if (t.equals(symbol)) {
				return true;
			}
		}
		return false;
	}

	private ArrayList<String> convert(String text) {
		ArrayList<String> list = new ArrayList<String>();
		String[] listText = text.split(",");
		for (String string : listText) {
			list.add(string);
		}
		return list;
	}

	private ArrayList<Production> convertProductions(String text) {
		ArrayList<Production> productions = new ArrayList<Production>();
		ArrayList<String> temp = convert(text);
		for (String p : temp) {
			if (p.split(">>").length<2) {
			JOptionPane.showMessageDialog(null,"Hay una produccion incompleta", "Error en las producciones",JOptionPane.ERROR_MESSAGE);	
			}else {
			productions.add(new Production(p.split(">>")[0], p.split(">>")[1]));
			}
		}
		return productions;
	}
}
