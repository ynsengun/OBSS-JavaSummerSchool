package com.example.SpringInitial.model;

import javax.validation.constraints.Min;

public class UserBookDTO {
	
	@Min(1)
	private long userID;
	
	@Min(1)
	private long bookID;

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getBookID() {
		return bookID;
	}

	public void setBookID(long bookID) {
		this.bookID = bookID;
	}
}
