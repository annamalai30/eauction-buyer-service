package com.eauction.eauctionbuyerservice.service;

import com.eauction.eauctionbuyerservice.customException.BidDateException;
import com.eauction.eauctionbuyerservice.dto.BidInformationDTO;

public interface IBuyerService {
	String updateBid(BidInformationDTO bidInformationDTO) throws BidDateException;
}
