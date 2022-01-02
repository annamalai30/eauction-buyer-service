package com.eauction.eauctionbuyerservice.repository.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.eauction.eauctionbuyerservice.dto.BidInformationDTO;
import com.eauction.eauctionbuyerservice.model.BidInformation;
import com.eauction.eauctionbuyerservice.model.ProductInfo;
import com.eauction.eauctionbuyerservice.repository.IBuyerRepository;
import com.eauction.eauctionbuyerservice.utils.Constants;

@Repository
public class BuyerRepository implements IBuyerRepository {

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

	DynamoDBMapper mapper = new DynamoDBMapper(client);

	DynamoDB dynamoDB = new DynamoDB(client);

	public ProductInfo getProductInformation(String productId) {
		return mapper.load(ProductInfo.class, productId);
	}

	public BidInformationDTO getBidInformation(BidInformationDTO bidInformationDTO) {
		BidInformation bidInformation = new BidInformation();
		bidInformation.setEmail(bidInformationDTO.getEmail());
		bidInformation.setProductId(bidInformationDTO.getProductId());

		BidInformation bidInformationDB = mapper.load(bidInformation,
				new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
		if (bidInformationDB != null) {
			DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
			BidInformationDTO bidInformationResponse = dozerBeanMapper.map(bidInformationDB, BidInformationDTO.class);
			return bidInformationResponse;
		}
		return null;
	}

	public String updateBidInformation(BidInformationDTO bidInformationDTO) {
		Table table = dynamoDB.getTable("BidInformation");

		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("Email", bidInformationDTO.getEmail(), "ProductId", bidInformationDTO.getProductId())
				.withUpdateExpression("set BidAmount = :r")
				.withValueMap(new ValueMap().withNumber(":r", bidInformationDTO.getBidAmount()))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		try {
			System.out.println("Updating the item...");
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
			return Constants.SUCCESS;
		} catch (Exception e) {
			System.err.println("Unable to update item: ");
			System.err.println(e.getMessage());
			return Constants.FAILED;
		}
	}

}
