package com.praveen.discounts;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praveen.discounts.controller.dao.DiscountRepository;
import com.praveen.discounts.controller.domain.Discount;
import com.praveen.discounts.controller.domain.DiscountType;
import com.praveen.discounts.controller.domain.Item;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscountsTests {
	
	@Autowired
	private DiscountRepository discountRepo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void clearAllDiscounts() {
		discountRepo.deleteAll();
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
	
	@Test
	public void scenario1() throws Exception {
		List<Discount> discounts = Arrays.<Discount>asList(
				new Discount(null, "ABC", DiscountType.ITEM_TYPE, 10, 0, null, "CLOTHES"),
				new Discount(null, "CDE", DiscountType.COST, 15, 100, null, null)
			);
		discountRepo.saveAll(discounts);
		
		List<Item> items = Arrays.<Item>asList(
				new Item(123L, "Shirt", "CLOTHES", 50, 1)
			);
		
		this.mockMvc.perform(post("/discounts/getBestPrice")
		.content(asJsonString(items))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("The best discount available is ABC and the total cost is: $45")));
		
	}
	
	@Test
	public void scenario2() throws Exception {
		List<Discount> discounts = Arrays.<Discount>asList(
				new Discount(null, "ABC", DiscountType.ITEM_TYPE, 10, 0, null, "CLOTHES"),
				new Discount(null, "CDE", DiscountType.COST, 15, 100, null, null),
				new Discount(null, "FGH", DiscountType.ITEM_ID, 20, 2, 123L, null)
			);
		discountRepo.saveAll(discounts);
		
		List<Item> items = Arrays.<Item>asList(
				new Item(123L, "Shirt", "CLOTHES", 50, 5)
			);
		
		this.mockMvc.perform(post("/discounts/getBestPrice")
				.content(asJsonString(items))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("The best discount available is FGH and the total cost is: $200")));
		
	}
	
	@Test
	public void scenario3() throws Exception {
		List<Discount> discounts = Arrays.<Discount>asList(
				new Discount(null, "ABC", DiscountType.ITEM_TYPE, 10, 0, null, "CLOTHES"),
				new Discount(null, "CDE", DiscountType.COST, 15, 100, null, null)
			);
		discountRepo.saveAll(discounts);
		
		List<Item> items = Arrays.<Item>asList(
				new Item(123L, "Shirt", "CLOTHES", 50, 1),
				new Item(456L, "TV", "ELECTRONICS", 300, 1)
			);
		
		this.mockMvc.perform(post("/discounts/getBestPrice")
				.content(asJsonString(items))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("The best discount available is CDE and the total cost is: $297")));
		
	}

}
