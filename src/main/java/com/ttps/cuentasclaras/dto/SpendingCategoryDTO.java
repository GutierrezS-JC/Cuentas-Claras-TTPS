package com.ttps.cuentasclaras.dto;

public class SpendingCategoryDTO {
	private Integer id;
	private String name;
	private String base64Image;

	public SpendingCategoryDTO(Integer id, String name, String base64Image) {
		super();
		this.id = id;
		this.name = name;
		this.base64Image = base64Image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
