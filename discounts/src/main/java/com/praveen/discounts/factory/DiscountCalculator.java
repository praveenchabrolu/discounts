package com.praveen.discounts.factory;

import java.util.List;

import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.DiscountResponse;
import com.praveen.discounts.controller.domain.Item;

public interface DiscountCalculator {
	
	public DiscountResponse calculateDiscount(List<Item> items, Discount discount);

}
