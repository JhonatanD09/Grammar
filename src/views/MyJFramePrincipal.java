package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Grammar;
import presenters.Events;

public class MyJFramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField terminals, noTerminals, axiomaticSymbol, productions;
	private ActionListener l;
	private JPanel grammarCreator, grammar;

	public MyJFramePrincipal(ActionListener l) {
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		this.l = l;
		grammar = new JPanel();
	}

	public void addGrammarCreator() {
		remove(grammar);

		terminals = new JTextField();
		setJTextField(terminals, "Ingrese los simbolos terminales separados por comas (,)  :");
		noTerminals = new JTextField();
		setJTextField(noTerminals, "Ingrese los simbolos no terminales separados por comas (,)  :");
		axiomaticSymbol = new JTextField();
		setJTextField(axiomaticSymbol, "Ingrese el simbolo inicial axiomatico  :");
		productions = new JTextField();
		setJTextField(productions, "Ingrese las producciones serparadas por comas (,) formato :(T>>a)");

		grammarCreator = new JPanel();
		grammarCreator.setLayout(new GridLayout(5, 1, 10, 10));
		grammarCreator.add(terminals);
		grammarCreator.add(noTerminals);
		grammarCreator.add(axiomaticSymbol);
		grammarCreator.add(productions);
		grammarCreator.add(editButton("Crear gramatica", Events.CREATE_GRAMMAR.name()));
		add(grammarCreator);

		revalidate();
		repaint();
	}

	public void addPanelGrammar() {
		remove(grammarCreator);
		grammar = new JPanel();
		grammar.setLayout(new GridLayout(4, 1));
		grammar.add(editButton("Mostrar gramatica", Events.SHOW_GRAMMAR.name()));
		grammar.add(editButton("Buscar palabra", Events.SEARCH_WORD.name()));
		grammar.add(editButton("Crear nueva gramatica", Events.CREATE_NEW_GRAMMAR.name()));
		grammar.add(editButton("Salir", Events.EXIT.name()));
		add(grammar);
		revalidate();
		repaint();
	}
	
	public void showGrammar(Grammar grammar) {
		remove(this.grammar);
		JPanel grammarShow = new JPanel();
		grammarShow.add(new JLabel(grammar.getTerminals().toString()));
		grammarShow.add(new JLabel(grammar.getNoTerminals().toString()));
		grammarShow.add(new JLabel(grammar.getAxiomticSymbol()));
		grammarShow.add(new JLabel(grammar.getProductions().toString()));
		add(grammarShow);
		revalidate();
		repaint();
	}
	
	private JButton editButton(String name, String commandName) {
		JButton btn = new JButton(name);
		btn.setActionCommand(commandName);
		btn.addActionListener(l);
		return btn;
	}

	private void setJTextField(JTextField jTextField, String title) {
		jTextField.setBorder(BorderFactory.createTitledBorder(title));
	}

	public String getTerminals() {
		return terminals.getText().replaceAll("\\s", "");
	}

	public String getNoTerminals() {
		return noTerminals.getText().replaceAll("\\s", "");
	}

	public String getAxiomatic() {
		return axiomaticSymbol.getText().replaceAll("\\s", "");
	}

	public String getProductions() {
		return productions.getText().replaceAll("\\s", "");
	}


}
