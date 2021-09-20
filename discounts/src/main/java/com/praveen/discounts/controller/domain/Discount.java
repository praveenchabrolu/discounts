package com.praveen.discounts.controller.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discounts")
public class Discount {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "discountType")
	private DiscountType discountType;

	@Column(name = "discountPercent")
	private int discountPercent;

	@Column(name = "itemQuantity")
	private int itemQuantity;

	@Column(name = "itemId")
	private Long itemId;
	
	@Column(name = "itemType")
	private String itemType;
	
	public Discount() {
		super();
	}

	public Discount(Long id, String name, DiscountType discountType, int discountPercent, int itemQuantity, Long itemId,
			String itemType) {
		super();
		this.id = id;
		this.name = name;
		this.discountType = discountType;
		this.discountPercent = discountPercent;
		this.itemQuantity = itemQuantity;
		this.itemId = itemId;
		this.itemType = itemType;
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

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}
