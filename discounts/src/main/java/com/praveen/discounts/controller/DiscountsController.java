package com.praveen.discounts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.Item;
import com.praveen.discounts.service.DiscountService;

@RestController
public class DiscountsController {
	
	@Autowired
	private DiscountService discountService;

	@GetMapping("/discounts")
	public List<Discount> getDiscounts() {
		return discountService.getAllDiscounts();
	}
	
	@PostMapping("/discounts")
	public Discount addDiscount(@RequestBody Discount discount) {
		return discountService.insertDiscount(discount);
	}
	
	@DeleteMapping("/discounts/{id}")
	public String removeDiscount(@PathVariable(name = "id") String id) {
		discountService.deleteDiscount(id);
		return "Removed the discount by id:: "+id;
	}
	
	@PostMapping("/discounts/getBestPrice")
	public String getDiscountPrice(@RequestBody List<Item> items) {
		return discountService.getDiscountPrice(items);
	}
	
}
