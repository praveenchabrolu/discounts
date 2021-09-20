package com.praveen.discounts.factory;

import java.util.List;

import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.DiscountResponse;
import com.praveen.discounts.controller.domain.Item;
import com.praveen.discounts.utils.DiscountsHelper;

public class DiscountByPrice implements DiscountCalculator {

	@Override
	public DiscountResponse calculateDiscount(List<Item> items, Discount discount) {
		DiscountResponse response = new DiscountResponse();
		response.setDiscount(discount);
		int price = DiscountsHelper.calculatePriceForTheItems(items);
		
		if(price >= discount.getItemQuantity()) {
			price = DiscountsHelper.calculateDiscountPriceForTheItems(items, discount.getDiscountPercent());
		}
		response.setDiscountedPrice(price);
		
		return response;
	}

}
