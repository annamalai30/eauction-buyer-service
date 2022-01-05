package com.eauction.eauctionbuyerservice.repository;

import com.eauction.eauctionbuyerservice.dto.BidInformationDTO;
import com.eauction.eauctionbuyerservice.model.ProductInfo;

public interface IBuyerRepository {

	ProductInfo getProductInformation(String productId);

	BidInformationDTO getBidInformation(String productId, String buyerEmailId);

	String updateBidInformation(String productId, String buyerEmailId, int newBidAmount);
}
