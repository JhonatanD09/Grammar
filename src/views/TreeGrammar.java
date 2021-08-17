package views;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import models.NodeTreeWord;
import models.TreeWord;

public class TreeGrammar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PINK = "#ffb8c1";
	private static final int DIAMETER_OVAL = 30;
	private static final int VALUE_PRINT = 100;
	private TreeWord treeWord;

	public TreeGrammar(TreeWord treeWord) {
		this.treeWord = treeWord;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int x = getSize().width / 2;
		int y = getSize().height / 10;
		printTree(treeWord.getRoot(), x, 0, y, g);
	}

	private void printTree(NodeTreeWord node, int x, int xRoot, int y, Graphics g) {
		if (node != null) {
			g.setColor(Color.BLACK);
			g.fillOval(x, y, DIAMETER_OVAL, DIAMETER_OVAL);
			g.setColor(Color.WHITE);
			g.drawString(node.getData() + "", x+10, y+10);
			g.setColor(Color.decode(PINK));
			g.drawLine(x, y, x - (x - xRoot)/2, y + VALUE_PRINT);
			g.drawLine(x, y, x + (x - xRoot)/2, y + VALUE_PRINT);
			printTree(node.getIzq(), x - Math.abs((x - xRoot))/2, x, y + VALUE_PRINT, g);
			printTree(node.getDer(), x + Math.abs((x - xRoot))/2, x, y + VALUE_PRINT, g);
		}
	}

}
