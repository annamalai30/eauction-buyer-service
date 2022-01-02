package com.eauction.eauctionbuyerservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.Data;

@Data
@DynamoDBDocument
public class SellerInfo {

	@DynamoDBAttribute(attributeName = "FirstName")
	private String firstName;
	@DynamoDBAttribute(attributeName = "LastName")
	private String lastName;
	@DynamoDBAttribute(attributeName = "Address")
	private String address;
	@DynamoDBAttribute(attributeName = "City")
	private String city;
	@DynamoDBAttribute(attributeName = "State")
	private String state;
	@DynamoDBAttribute(attributeName = "Pin")
	private String pin;
	@DynamoDBAttribute(attributeName = "Phone")
	private String phone;
	@DynamoDBAttribute(attributeName = "Email")
	private String email;
}
