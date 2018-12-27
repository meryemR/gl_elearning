package org.mql.collections;

public enum Categories {

	Network("Réseaux"), WebDev("Dévloppement Web"), BigData("Big Data"), BI("Informatique Décisionnelle"),
	DevMobile("DévloppementMobile");

	private Categories(String category) {
		this.category = category;
	}
	
	private String category;

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return this.category;
	}
	
	


}
