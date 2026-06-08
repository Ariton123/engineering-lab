const Web3 = require('web3');
const web3 = new Web3();

let users = [];
let auctions = [];
let nextAuctionId = 0;

function addUser(username, password, role = 'user') {
    users.push({ username, password, role });
    console.log('User added:', { username, password, role });
}

function findUser(username) {
    return users.find(user => user.username === username);
}

function authenticateUser(username, password) {
    return users.find(user => user.username === username && user.password === password);
}

function addAuction(itemName, startingPrice, owner) {
    const auction = {
        id: nextAuctionId++,
        itemName,
        startingPrice,
        highestBid: '0',
        highestBidder: null,
        state: 'Pending',
        owner
    };
    console.log('Adding auction to database:', auction);
    auctions.push(auction);
    console.log('Current Auctions:', auctions);
    return auction;
}

function findAuctionById(id) {
    return auctions.find(auction => auction.id === id);
}

function approveAuction(id) {
    const auction = findAuctionById(id);
    if (auction && auction.state === 'Pending') {
        auction.state = 'Approved';
        console.log(`Auction state set to Approved: ${JSON.stringify(auction)}`);
        return auction;
    } else {
        console.error('Auction not found or already approved/discarded');
        return null;
    }
}

function discardAuction(id) {
    const index = auctions.findIndex(auction => auction.id === id);
    if (index > -1) {
        const removedAuction = auctions.splice(index, 1);
        console.log('Auction removed:', removedAuction);
        return true;
    }
    console.log('Auction not found with ID:', id);
    return false;
}

function getPendingAuctions() {
    const pendingAuctions = auctions.filter(auction => auction.state === 'Pending');
    console.log('Pending Auctions:', pendingAuctions);
    return pendingAuctions;
}

function getApprovedAuctions() {
    const approvedAuctions = auctions.filter(auction => auction.state === 'Approved');
    console.log('Approved Auctions:', approvedAuctions);
    return approvedAuctions;
}

async function placeBid(id, bidAmount, bidder) {
    const auction = findAuctionById(id);
    if (auction && auction.state === 'Approved' && web3.utils.toBN(bidAmount).gt(web3.utils.toBN(auction.highestBid))) {
        auction.highestBid = bidAmount;
        auction.highestBidder = bidder;
        console.log('Bid placed:', { id, bidAmount, bidder });
        return auction;
    }
    return null;
}

module.exports = {
    addUser,
    findUser,
    authenticateUser,
    addAuction,
    findAuctionById,
    getPendingAuctions,
    getApprovedAuctions,
    approveAuction,
    discardAuction,
    placeBid
};