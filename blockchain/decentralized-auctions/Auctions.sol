// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract Auction {
    enum AuctionState { Pending, Approved, Discarded }

    struct AuctionItem {
        string itemName;
        uint256 startingPrice;
        address payable owner;
        address payable highestBidder;
        uint256 highestBid;
        AuctionState state;
    }

    AuctionItem[] public auctions;

    event AuctionCreated(uint256 auctionId);
    event AuctionApproved(uint256 auctionId, uint256 state);
    event AuctionDiscarded(uint256 auctionId);
    event BidPlaced(uint256 auctionId, address bidder, uint256 amount);

    function createAuction(string memory _itemName, uint256 _startingPrice) public {
        auctions.push(AuctionItem({
            itemName: _itemName,
            startingPrice: _startingPrice,
            owner: payable(msg.sender),
            highestBidder: payable(address(0)),
            highestBid: 0,
            state: AuctionState.Pending
        }));
        emit AuctionCreated(auctions.length - 1);
    }

    function approveAuction(uint256 _auctionId) external {
        require(_auctionId < auctions.length, "Auction does not exist");
        auctions[_auctionId].state = AuctionState.Approved;
        emit AuctionApproved(_auctionId, uint256(auctions[_auctionId].state));
    }

    function discardAuction(uint256 _auctionId) external {
        require(_auctionId < auctions.length, "Auction does not exist");
        auctions[_auctionId].state = AuctionState.Discarded;
        emit AuctionDiscarded(_auctionId);
    }

    function placeBid(uint256 _auctionId) external payable {
        require(_auctionId < auctions.length, "Auction does not exist");
        AuctionItem storage auction = auctions[_auctionId];
        require(auction.state == AuctionState.Approved, "Auction is not approved");
        require(msg.value > auction.highestBid, "Bid must be higher than current highest bid");

        if (auction.highestBidder != address(0)) {
            // Refund the previous highest bidder
            auction.highestBidder.transfer(auction.highestBid);
        }

        auction.highestBidder = payable(msg.sender);
        auction.highestBid = msg.value;
        emit BidPlaced(_auctionId, msg.sender, msg.value);
    }

    function getAuctionCount() external view returns (uint256) {
        return auctions.length;
    }

    function getAuctionByIndex(uint256 _index) external view returns (string memory, uint256, address, address, uint256, AuctionState) {
        require(_index < auctions.length, "Auction does not exist");
        AuctionItem memory auction = auctions[_index];
        return (
            auction.itemName,
            auction.startingPrice,
            auction.owner,
            auction.highestBidder,
            auction.highestBid,
            auction.state
        );
    }
}