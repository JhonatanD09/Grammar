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
		int x = 0;
		if(treeWord.getRoot().getDer()!=null){
			x = getWidth() / treeWord.getAltura();
		}
		if(treeWord.getRoot().getIzq()!=null){
			x = ((getWidth()/treeWord.getAltura())*(treeWord.getAltura()-1));
		}
		int y = getHeight() / 10;
		printTree(treeWord.getRoot(), x, 0, y, g);
	}

	private void printTree(NodeTreeWord node, int x, int xRoot, int y, Graphics g) {
		int heightvar = treeWord.getAltura();
		if (node != null) {
			g.setColor(Color.ORANGE);
			g.fillOval(x, y, DIAMETER_OVAL, DIAMETER_OVAL);
			g.setColor(Color.BLACK);
			g.drawString(node.getData() + "", x+(DIAMETER_OVAL/3), y+(2*(DIAMETER_OVAL/3)));
			g.setColor(Color.GREEN);
			if (node.getIzq()!=null) {
				g.drawLine(x+(DIAMETER_OVAL/2), y+DIAMETER_OVAL, x - (getWidth() / treeWord.getAltura())+(DIAMETER_OVAL/2), y + getHeight()/heightvar);
			}
			if (node.getDer()!=null) {
				g.drawLine(x+(DIAMETER_OVAL/2), y+DIAMETER_OVAL, x + (getWidth() / treeWord.getAltura()), y + getHeight()/heightvar);
			}
			// g.drawLine(x+(DIAMETER_OVAL/2), y+DIAMETER_OVAL, x - ((x - xRoot)/2)+(DIAMETER_OVAL/2), y + VALUE_PRINT+DIAMETER_OVAL);
			// g.drawLine(x+(DIAMETER_OVAL/2), y+DIAMETER_OVAL, x + ((x - xRoot)/2)+(DIAMETER_OVAL/2), y + VALUE_PRINT+DIAMETER_OVAL);
			printTree(node.getIzq(), x - (getWidth() / treeWord.getAltura()), x, y + getHeight()/heightvar, g);
			printTree(node.getDer(), x + (getWidth() / treeWord.getAltura()), x, y + getHeight()/heightvar, g);
		}
	}

}
