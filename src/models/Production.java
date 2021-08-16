package models;


public class Production {

	private String producer, production;

	public Production(String producer, String production) {
		this.producer = producer;
		this.production = production;
	}

	public String getProducer() {
		return producer;
	}

	public String getProduction() {
		return production;
	}
	
	@Override
	public String toString() {
		return producer + " => "+ production;
	}

}
