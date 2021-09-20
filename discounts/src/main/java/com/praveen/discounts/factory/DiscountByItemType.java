package com.praveen.discounts.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.DiscountResponse;
import com.praveen.discounts.controller.domain.Item;
import com.praveen.discounts.utils.DiscountsHelper;

public class DiscountByItemType implements DiscountCalculator {

	@Override
	public DiscountResponse calculateDiscount(List<Item> items, Discount discount) {
		DiscountResponse response = new DiscountResponse();
		response.setDiscount(discount);
		int remainingItemsPrice = 0;
		int sameTypeItemsPrice = 0;
		int totalPrice = 0;
		
		List<Item> sameTypeItems = items.stream().filter(item -> discount.getItemType().equals(item.getType())).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(sameTypeItems)) {
			sameTypeItemsPrice = DiscountsHelper.calculateDiscountPriceForTheItems(sameTypeItems, discount.getDiscountPercent());
		}
		
		
		List<Item> remainingItems = items.stream().filter(item -> !discount.getItemType().equals(item.getType())).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(remainingItems)) {
			remainingItemsPrice = DiscountsHelper.calculatePriceForTheItems(remainingItems);
		}
		totalPrice = sameTypeItemsPrice+remainingItemsPrice;
		response.setDiscountedPrice(totalPrice);
		return response;
	}

}
