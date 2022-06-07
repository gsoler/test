package com.king.constants;

public enum KingDataStatus {

	COMPLETED("Completed"), CANCELED("Canceled"), ERROR("Error");

	private String label;

	KingDataStatus(String label) {
		this.label = label;
	}

	public String label() {
		return label;
	}

}
