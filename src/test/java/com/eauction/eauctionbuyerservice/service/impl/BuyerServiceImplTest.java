package com.eauction.eauctionbuyerservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.eauction.eauctionbuyerservice.customException.BidDateException;
import com.eauction.eauctionbuyerservice.dto.BidInformationDTO;
import com.eauction.eauctionbuyerservice.model.ProductInfo;
import com.eauction.eauctionbuyerservice.repository.IBuyerRepository;
import com.eauction.eauctionbuyerservice.utils.Constants;

class BuyerServiceImplTest {

	BuyerServiceImpl buyerServiceImpl;
	IBuyerRepository buyerRepository;

	@BeforeEach
	public void setUp() {
		System.out.println("hi");
		buyerServiceImpl = new BuyerServiceImpl();
		buyerRepository = mock(IBuyerRepository.class);
		ReflectionTestUtils.setField(buyerServiceImpl, "buyerRepository", buyerRepository);
	}

	@Test
	void updateBidTest() throws BidDateException {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setId("32-42-");
		BidInformationDTO bidInformation = new BidInformationDTO();
		Mockito.when(buyerRepository.getProductInformation("32-42")).thenReturn(productInfo);
		Mockito.when(buyerRepository.getBidInformation("32-42", "annamalai@gmail.com")).thenReturn(bidInformation);
		Mockito.when(buyerRepository.updateBidInformation("32-42", "annamalai@gmail.com", 0))
				.thenReturn(Constants.SUCCESS);
		buyerServiceImpl.updateBid("32-42", "annamalai@gmail.com", 0);
		String actualResponse = buyerServiceImpl.updateBid("32-42", "annamalai@gmail.com", 0);
		assertEquals(Constants.SUCCESS, actualResponse);

	}

	@Test
	void updateBidTest_NullBidInformation() throws BidDateException {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setId("32-42");
		Mockito.when(buyerRepository.getProductInformation("32-42")).thenReturn(productInfo);
		Mockito.when(buyerRepository.getBidInformation("32-42", "annamalai@gmail.com")).thenReturn(null);
		Mockito.when(buyerRepository.updateBidInformation("32-42", "annamalai@gmail.com", 0))
				.thenReturn(Constants.SUCCESS);
		String actualResponse = buyerServiceImpl.updateBid("32-42", "annamalai@gmail.com", 0);
		assertEquals("Bid Information Not Available", actualResponse);

	}

	/*
	 * @Test(expected= BidDateException.class) public void myTest() {
	 * buyerServiceImpl.doSomethingThatMightThrowCustomException(); }
	 */

	/*
	 * @Test void updateBidTest_BidEndDate() throws BidDateException { ProductInfo
	 * productInfo = new ProductInfo(); productInfo.setId("32-42");
	 * productInfo.setBidEndDate("01/01/2022"); BidInformationDTO bidInformation =
	 * new BidInformationDTO();
	 * Mockito.when(buyerRepository.getProductInformation("32-42")).thenReturn(
	 * productInfo); Mockito.when(buyerRepository.getBidInformation("32-42",
	 * "annamalai@gmail.com")).thenReturn(bidInformation);
	 * Mockito.when(buyerRepository.updateBidInformation("32-42",
	 * "annamalai@gmail.com", 0)) .thenReturn(Constants.SUCCESS); String
	 * actualResponse = buyerServiceImpl.updateBid("32-42", "annamalai@gmail.com",
	 * 0); //assertEquals("Bid Date is Closed", actualResponse);
	 * Assertions.assertThrows(BidDateException.class, () ->
	 * buyerServiceImpl.updateBid("32-42", "annamalai@gmail.com", 0));
	 * 
	 * }
	 */

}
