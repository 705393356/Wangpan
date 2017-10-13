package com.hcw.vo;

public class FilePath {

	private String cosFilePath;
	private String localPathDown;
	private String foldername;
	
	
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	public String getCosFilePath() {
		return cosFilePath;
	}
	public void setCosFilePath(String cosFilePath) {
		this.cosFilePath = cosFilePath;
	}
	public String getLocalPathDown() {
		return localPathDown;
	}
	public void setLocalPathDown(String localPathDown) {
		this.localPathDown = localPathDown;
	}
	public FilePath(String cosFilePath, String localPathDown) {
		super();
		this.cosFilePath = cosFilePath;
		this.localPathDown = localPathDown;
	}
	public FilePath(String cosFilePath) {
		super();
		this.cosFilePath = cosFilePath;
	}
	
	public FilePath() {
		super();
	}
	public FilePath(String cosFilePath, String localPathDown, String foldername) {
		super();
		this.cosFilePath = cosFilePath;
		this.localPathDown = localPathDown;
		this.foldername = foldername;
	}
	
	
	
	
}
