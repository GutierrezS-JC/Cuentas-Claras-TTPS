package com.ttps.cuentasclaras.dto;

public class GroupCategoryCreateDTO {
	private String name;
	private String base64Image;

	public GroupCategoryCreateDTO(String name, String base64Image) {
		super();
		this.name = name;
		this.base64Image = base64Image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

}
