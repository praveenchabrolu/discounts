package com.praveen.discounts.utils;

import java.util.List;

import com.praveen.discounts.controller.domain.Item;

public class DiscountsHelper {

	public static int calculatePriceForTheItems(List<Item> items) {
		int price = 0;
		for(Item item: items) {
			price = price + (item.getCost() * item.getQuantity());
		}
		return price;
	}
	
	public static int calculateDiscountPriceForTheItems(List<Item> items, int discountPercentage) {
		int price = calculatePriceForTheItems(items);
		double dividend = 100;
		double percentage = 100-discountPercentage;
		price = (int) (price * (percentage/dividend));
		return price;
	}
}
