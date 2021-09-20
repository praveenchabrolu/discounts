package com.praveen.discounts.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.DiscountResponse;
import com.praveen.discounts.controller.domain.Item;

@Component
public class DiscountCalculatorFactory {
	
	public DiscountResponse calculateDiscount(List<Item> itemsRequest, Discount discount) {
		
		if(itemsRequest != null) {
			DiscountCalculator discountCalculator;
			switch(discount.getDiscountType()) {
				case ITEM_TYPE:
					discountCalculator = new DiscountByItemType();
					return discountCalculator.calculateDiscount(itemsRequest, discount);
				case ITEM_ID:
					discountCalculator = new DiscountByItemId();
					return discountCalculator.calculateDiscount(itemsRequest, discount);
				case COST:
					discountCalculator = new DiscountByPrice();
					return discountCalculator.calculateDiscount(itemsRequest, discount);
				default:
					return null;
			}
			
		}
		
		return null;
	}

}
