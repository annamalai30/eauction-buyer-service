package com.eauction.eauctionbuyerservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.eauction.eauctionbuyerservice.customException.BidDateException;
import com.eauction.eauctionbuyerservice.dto.Response;
import com.eauction.eauctionbuyerservice.service.IBuyerService;
import com.eauction.eauctionbuyerservice.utils.Constants;

class BuyerControllerTest {

	private BuyerController buyerControllerTest;
	private IBuyerService buyerService;

	@BeforeEach
	public void setUp() {
		System.out.println("hi");
		buyerControllerTest = new BuyerController();
		buyerService = mock(IBuyerService.class);
		ReflectionTestUtils.setField(buyerControllerTest, "buyerService", buyerService);
	}

	@Test
	void updateBidControllerTest() throws BidDateException {
		Response exceptedResponse = new Response();
		exceptedResponse.setResponse(Constants.SUCCESS);
		Mockito.when(buyerService.updateBid("32-42-", "annamalai@gmail.com", 0)).thenReturn(Constants.SUCCESS);
		Response actualResponse = buyerControllerTest.updateBidController("32-42-", "annamalai@gmail.com", 0);
		assertEquals(exceptedResponse.toString(), actualResponse.toString());
	}

	@Test
	void updateBidController_NullValueTest() throws BidDateException {
		Response exceptedResponse = new Response();
		exceptedResponse.setResponse(Constants.FAILED);
		Response actualResponse = buyerControllerTest.updateBidController(null, null, 0);
		System.out.println(actualResponse.toString());
		assertEquals(exceptedResponse.getResponse(), actualResponse.getResponse());
	}

}
