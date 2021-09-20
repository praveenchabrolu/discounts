package com.praveen.discounts.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.discounts.controller.dao.DiscountRepository;
import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.DiscountResponse;
import com.praveen.discounts.controller.domain.Item;
import com.praveen.discounts.factory.DiscountCalculatorFactory;
import com.praveen.discounts.utils.DiscountsHelper;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountRepository discountRepo;

	@Autowired
	private DiscountCalculatorFactory discountCalculatorFactory;

	@Override
	public List<Discount> getAllDiscounts() {
		return discountRepo.findAll();
	}

	@Override
	public Discount insertDiscount(Discount discount) {
		return discountRepo.save(discount);
	}

	@Override
	public void deleteDiscount(String discountName) {
		Discount disc = discountRepo.findByName(discountName);
		discountRepo.deleteById(disc.getId());
	}

	@Override
	public String getDiscountPrice(List<Item> itemsRequest) {
		List<Discount> discounts = getAllDiscounts();
		if (CollectionUtils.isEmpty(itemsRequest)) {
			return "No Items in the request";
		}
		
		
		DiscountResponse bestOffer = null;
		if (CollectionUtils.isNotEmpty(discounts)) {
			for (Discount discount : discounts) {
				DiscountResponse currentDiscount = discountCalculatorFactory.calculateDiscount(itemsRequest, discount);
				if (bestOffer == null || bestOffer.getDiscountedPrice() > currentDiscount.getDiscountedPrice()) {
					bestOffer = currentDiscount;
				}
			}
		}

		if (bestOffer == null || bestOffer.getDiscount() == null) {
			return "No discounts available and total cost is " + DiscountsHelper.calculatePriceForTheItems(itemsRequest);
		} else {
			return "The best discount available is " + bestOffer.getDiscount().getName() + " and the total cost is: $"
					+ bestOffer.getDiscountedPrice();
		}
	}

}
