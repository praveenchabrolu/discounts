package com.praveen.discounts.controller.domain;

public class Item {

	private Long id;
	private String name;
	private String type;
	private int cost;
	private int quantity;

	public Item(Long id, String name, String type, int cost, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
