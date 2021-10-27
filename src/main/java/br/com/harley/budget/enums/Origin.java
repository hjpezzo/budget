package br.com.harley.budget.enums;

public enum Origin {

	FEDERAL("FEDERAL"), STATE("STATE"), COUNTY("COUNTY");

	private final String value;

	Origin(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
