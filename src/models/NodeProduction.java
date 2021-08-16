package models;

import java.util.ArrayList;

public class NodeProduction {

	private String production;
	private ArrayList<NodeProduction> childs;

	public NodeProduction(String production) {
		this.production = production;
		this.childs = new ArrayList<NodeProduction>();
	}
	
	public void addChild(String production) {
		childs.add(new NodeProduction(production));
	}
	
	public ArrayList<NodeProduction> getChilds() {
		return childs;
	}
	
	public String getProduction() {
		return production;
	}

	public boolean searchChild(NodeProduction id) {
		for (NodeProduction nodeProduction : childs) {
			if (nodeProduction.equals(id)) {
				return true;
			}
		}
		return false;
	}
	

}
