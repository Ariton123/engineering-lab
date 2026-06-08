const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const session = require('express-session');
const database = require('./server/database');

const app = express();
const port = 3000;

// Middleware
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, 'public')));
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));
app.use(session({ secret: 'secret', resave: false, saveUninitialized: true }));

// Authentication middleware
function authMiddleware(req, res, next) {
    if (req.session.username) {
        console.log('User authenticated:', req.session.username);
        next();
    } else {
        console.log('User not authenticated, redirecting to login');
        res.redirect('/login');
    }
}

function adminMiddleware(req, res, next) {
    if (req.session.username === 'admin') {
        console.log('Admin authenticated');
        next();
    } else {
        console.log('Admin not authenticated, redirecting to login');
        res.status(403).send('Forbidden');
    }
}

app.get('/', (req, res) => {
    res.redirect('/register');
});

app.get('/register', (req, res) => {
    res.render('register');
});

app.post('/register', (req, res) => {
    const { username, password } = req.body;
    if (database.findUser(username)) {
        res.status(400).send('User already exists');
    } else {
        database.addUser(username, password);
        res.redirect('/login');
    }
});

app.get('/login', (req, res) => {
    res.render('login');
});

app.post('/login', (req, res) => {
    const { username, password } = req.body;
    if (database.authenticateUser(username, password)) {
        req.session.username = username;
        res.redirect('/home');
    } else {
        res.status(401).send('Invalid credentials!');
    }
});

app.get('/home', authMiddleware, (req, res) => {
    const isAdmin = req.session.username === 'admin';
    const username = req.session.username;
    res.render('home', { isAdmin, username });
});

app.get('/create', authMiddleware, (req, res) => {
    res.render('create');
});

app.post('/createAuction', authMiddleware, (req, res) => {
    const { itemName, startingPrice } = req.body;
    const startingPriceInWei = startingPrice; // Assume the client sends price in Wei
    const auction = database.addAuction(itemName, startingPriceInWei, req.session.username);
    res.redirect('/home');
});

app.get('/approve', adminMiddleware, (req, res) => {
    const pendingAuctions = database.getPendingAuctions();
    res.render('approve', { auctions: pendingAuctions });
});

app.post('/approveAuction', adminMiddleware, async (req, res) => {
    const { auctionId } = req.body;
    console.log(`Attempting to approve auction with ID: ${auctionId}`);

    try {
        const auction = await database.approveAuction(parseInt(auctionId));
        if (auction) {
            console.log(`Auction approved: ${JSON.stringify(auction)}`);
            res.redirect('/active');
        } else {
            console.error('Auction not found or already approved/discarded.');
            res.status(404).send('Auction not found or already approved/discarded.');
        }
    } catch (error) {
        console.error('Error approving auction:', error);
        res.status(500).send('Error approving auction');
    }
});

app.post('/discardAuction', adminMiddleware, (req, res) => {
    const { auctionId } = req.body;
    try {
        const result = database.discardAuction(parseInt(auctionId));
        res.redirect('/approve');
    } catch (error) {
        console.error('Error discarding auction:', error);
        res.status(500).send('Error discarding auction');
    }
});

app.get('/active', authMiddleware, (req, res) => {
    const activeAuctions = database.getApprovedAuctions();
    const isAdmin = req.session.username === 'admin';
    res.render('active', { auctions: activeAuctions, isAdmin, username: req.session.username });
});

app.post('/placeBid', authMiddleware, (req, res) => {
    const { auctionId, bidAmount } = req.body;
    const auction = database.placeBid(auctionId, bidAmount, req.session.username);
    if (auction) {
        res.redirect('/active');
    } else {
        res.status(400).send('Invalid bid');
    }
});

app.listen(port, () => {
    console.log(`Server running on http://localhost:${port}`);
});