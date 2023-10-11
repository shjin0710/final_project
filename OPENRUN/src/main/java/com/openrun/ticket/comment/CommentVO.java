package com.openrun.ticket.comment;

public class CommentVO {
	private String c_content;
	private int c_ID;
	private String userID;
	private int c_available;
	private String c_date;
	private int bbsID;
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public int getC_ID() {
		return c_ID;
	}
	public void setC_ID(int c_ID) {
		this.c_ID = c_ID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getC_available() {
		return c_available;
	}
	public void setC_available(int c_available) {
		this.c_available = c_available;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public int getBbsID() {
		return bbsID;
	}
	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}
	
	
}
