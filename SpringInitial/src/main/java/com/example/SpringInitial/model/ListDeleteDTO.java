package com.example.SpringInitial.model;

import javax.validation.constraints.Min;

public class ListDeleteDTO {

	@Min(1)
	private long userID;
	
	@Min(1)
	private long rowID;

	public long getRowID() {
		return rowID;
	}

	public void setRowID(long rowID) {
		this.rowID = rowID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
}
