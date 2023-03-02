package com.devsuperior.uri2621.dtos;

import com.devsuperior.uri2621.projections.ProductMinProjection;

public class ProductMinDTO {

	private String name;
	
	public ProductMinDTO() {
	}

	public ProductMinDTO(String name) {
		super();
		this.setName(name);
	}
	
	public ProductMinDTO(ProductMinProjection projection) {
		super();
		this.setName(projection.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductMinDTO [name=" + name + "]";
	}
	
}
