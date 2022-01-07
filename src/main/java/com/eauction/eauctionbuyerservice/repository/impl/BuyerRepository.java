package com.eauction.eauctionbuyerservice.repository.impl;

import java.util.logging.Logger;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
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

	static Logger logger = Logger.getLogger(BuyerRepository.class.getName());

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(
					new BasicAWSCredentials("AKIARBJUXX6FQBVMR44P", "5J/IKaXzR/J1yQmm9Qgg5UBwScb5gCfbC+hUG1la")))
			.build();

	DynamoDBMapper mapper = new DynamoDBMapper(client);

	DynamoDB dynamoDB = new DynamoDB(client);

	public ProductInfo getProductInformation(String productId) {
		return mapper.load(ProductInfo.class, productId);
	}

	public BidInformationDTO getBidInformation(String productId, String buyerEmailId) {
		logger.info("getBidInformation repository layer Starts Here");
		BidInformation bidInformation = new BidInformation();
		bidInformation.setEmail(buyerEmailId);
		bidInformation.setProductId(productId);

		BidInformation bidInformationDB = mapper.load(bidInformation,
				new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
		if (bidInformationDB != null) {
			DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
			BidInformationDTO bidInformationResponse = dozerBeanMapper.map(bidInformationDB, BidInformationDTO.class);
			logger.info("getBidInformation repository layer Ends Here " + bidInformationResponse);
			return bidInformationResponse;
		}
		logger.info("getBidInformation repository layer Ends Here ");
		return null;
	}

	public String updateBidInformation(String productId, String buyerEmailId, int newBidAmount) {

		logger.info("updateBidInformation repository layer Ends Here ");
		Table table = dynamoDB.getTable("BidInformation");
		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("Email", buyerEmailId, "ProductId", productId)
				.withUpdateExpression("set BidAmount = :r").withValueMap(new ValueMap().withNumber(":r", newBidAmount))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		try {
			logger.info("updateBidInformation repository, Updating the item...");
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			logger.info("updateBidInformation repository, UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
			return Constants.SUCCESS;
		} catch (Exception e) {
			logger.info("updateBidInformation repository Exception " + e.getMessage());
			return Constants.FAILED;
		}
	}

}
