package com.praveen.discounts.service;

import java.util.List;

import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.Item;

public interface DiscountService {

	List<Discount> getAllDiscounts();

	Discount insertDiscount(Discount discount);

	void deleteDiscount(String discountId);

	String getDiscountPrice(List<Item> itemsRequest);

}
