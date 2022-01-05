package com.eauction.eauctionbuyerservice.service;

import com.eauction.eauctionbuyerservice.customException.BidDateException;

public interface IBuyerService {
	String updateBid(String productId, String buyerEmailId, int newBidAmount) throws BidDateException;
}
