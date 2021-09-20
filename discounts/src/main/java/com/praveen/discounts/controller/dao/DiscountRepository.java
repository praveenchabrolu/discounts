package com.praveen.discounts.controller.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praveen.discounts.controller.domain.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
	
	Discount findByName(String name); 

}
