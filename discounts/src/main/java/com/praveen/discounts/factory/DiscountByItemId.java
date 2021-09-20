package com.praveen.discounts.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.DiscountResponse;
import com.praveen.discounts.controller.domain.Item;
import com.praveen.discounts.utils.DiscountsHelper;

public class DiscountByItemId implements DiscountCalculator {

	@Override
	public DiscountResponse calculateDiscount(List<Item> items, Discount discount) {
		DiscountResponse response = new DiscountResponse();
		response.setDiscount(discount);
		int remainingItemsPrice = 0;
		int sameItemsPrice = 0;
		int totalPrice = 0;
		
		Optional<Item> sameItem = items.stream().filter(item -> discount.getItemId().equals(item.getId())).findFirst();
		
		if(sameItem.isPresent()) {
			if(sameItem.get().getQuantity() >= discount.getItemQuantity()) {
				sameItemsPrice = DiscountsHelper.calculateDiscountPriceForTheItems(Arrays.asList(sameItem.get()), discount.getDiscountPercent());
			} else {
				sameItemsPrice = DiscountsHelper.calculatePriceForTheItems(Arrays.asList(sameItem.get()));
			}
		}
		
		
		List<Item> remainingItems = items.stream().filter(item -> !discount.getItemId().equals(item.getId())).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(remainingItems)) {
			remainingItemsPrice = DiscountsHelper.calculatePriceForTheItems(remainingItems);
		}
		totalPrice = sameItemsPrice+remainingItemsPrice;
		response.setDiscountedPrice(totalPrice);
		return response;
	}

}
