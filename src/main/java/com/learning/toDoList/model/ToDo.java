package com.learning.toDoList.model;

public class ToDo {

	private static Integer counter = 0;
	private Integer code;
	private String text;
	
	public ToDo() {
	}
	
	public ToDo(String text) {
		this.code = counter++;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
