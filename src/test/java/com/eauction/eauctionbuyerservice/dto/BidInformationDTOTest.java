package com.eauction.eauctionbuyerservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.eauction.eauctionbuyerservice.model.BidInformation;

public class BidInformationDTOTest {

	private BidInformation bidInformation;
	@Test
	public void setUp() {
		bidInformation = new BidInformation();
		bidInformation.setAddress("sadsd");
	}
	
}
