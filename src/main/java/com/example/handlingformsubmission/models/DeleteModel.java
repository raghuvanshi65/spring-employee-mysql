package com.example.handlingformsubmission.models;

public class DeleteModel {
	private Integer option;
	private String id;
	
	public DeleteModel() {
		super();
	}

	public DeleteModel(Integer option) {
		super();
		this.option = option;
	}
	
	public DeleteModel(Integer option, String id) {
		this.id = id;
		this.option = option;
	}

	public Integer getOption() {
		return option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
