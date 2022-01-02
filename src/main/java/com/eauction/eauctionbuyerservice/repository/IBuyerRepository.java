package com.eauction.eauctionbuyerservice.repository;

import java.util.List;

import com.eauction.eauctionbuyerservice.dto.BidInformationDTO;
import com.eauction.eauctionbuyerservice.model.BidInformation;
import com.eauction.eauctionbuyerservice.model.ProductInfo;

public interface IBuyerRepository {

	ProductInfo getProductInformation(String productId);

	BidInformationDTO getBidInformation(BidInformationDTO bidInformationDTO);

	String updateBidInformation(BidInformationDTO bidInformationDTO);
}
