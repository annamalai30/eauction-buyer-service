package com.eauction.eauctionbuyerservice.respository.impl;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.eauction.eauctionbuyerservice.model.BidInformation;
import com.eauction.eauctionbuyerservice.repository.impl.BuyerRepository;

public class BuyerRepositoryTest {

	DynamoDBMapper dynamoDBMapper;
	BuyerRepository buyerRepository;

	@BeforeEach
	public void setUp() {
		buyerRepository = new BuyerRepository();
		dynamoDBMapper = mock(DynamoDBMapper.class);
		ReflectionTestUtils.setField(buyerRepository, "mapper", dynamoDBMapper);
	}

	@Test
	public void getBidInformationTest() {
		BidInformation bidInformation = new BidInformation();
		bidInformation.setEmail("annamalai@gmail.com");
		bidInformation.setProductId("23423");
		// Mock out specific DynamoDBOperations behavior expected by this method
		ArgumentCaptor<DynamoDBMapper> queryCaptor = ArgumentCaptor.forClass(DynamoDBMapper.class);
		ArgumentCaptor<Class> classCaptor = ArgumentCaptor.forClass(Class.class);
		Mockito.doReturn(bidInformation).when(dynamoDBMapper).load(classCaptor.capture(), queryCaptor.capture());
		buyerRepository.getBidInformation("annamalai@gmail.com", "23423");
	}
}
