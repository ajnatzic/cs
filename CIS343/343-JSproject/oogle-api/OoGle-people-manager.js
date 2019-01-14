/**
 * This Javascript program runs the back end to manage the database of Oogle workers.
 * 
 * @author AJ Natzic
 * */
// The API toolkit for making REST systems easily
const express = require('express');
// A good solution for handling JSON data in routes
const bodyParser = require('body-parser');
// Node JS modules for filesystem access
const fs = require('fs');
// Our database connection
// This will be a JSON object of our programmers
// and can be accessed as if it was any other javascript
// object
const database = require('./programmers.json');

// This creates an array of all the objects in "database"
const dbArray = [database];

// This holds the values of all the keys in the database
const dbKey = Object.keys(database);

// Make an instance of our express application
const app = express();
// Specify our > 1024 port to run on
const port = 3000;

// Apply our middleware so our code can natively handle JSON easily
app.use(bodyParser.json());

// We must have our list of programmers to use
if (!fs.existsSync('./programmers.json')) {
    throw new Error('Could not find database of programmers!');
}
// Build our routes

// Gets all of the objects in dbArray
app.get('/', (req, res) => {
    res.send(dbArray);
});

// Gets users with SID
app.get('/:id', (req, res) => {
    const id = req.params.id;
    // Create an array for IDs
    var idList = [];
    dbArray.forEach(s => {
        if (s.SID == id) {
            idList.push(s);
        }
    });

    // Throws a "404 not found" error if ID cannot be found
    if (idList.length == 0) {
        res.sendStatus(404);
    }
    else {
        res.send(idList);
    }
});

// Updates person in database with the ID found
app.put('/:id', (req, res) => {
    const id = req.params.id;
    const body = req.body;
    const bodyKey = Object.keys(body);

    // Removes object in dbArray
    // used https://www.tutorialspoint.com/javascript/array_splice.htm to figure this out
    var index = dbArray.length - 1;
    while (index >= 0) {
        if (dbArray[index].SID == id) {
            dbArray.splice(index, 1);  // removes this portion of the array
        }
        index--;
    }

    // Places new object
    let data = {};
    dbKey.forEach(k => {
        if (body[k]) {
            data[k] = body[k];
        } else {
            data[k] = "";
        }
    });
    dbArray.push(data);
    res.send(`Update values with ID: ${id}`);
});

// Creates new person in database
app.post('/', (req, res) => {
    const body = req.body; // Hold your JSON in here!
    const bodyKey = Object.keys(body);

    // Places new object
    let data = {};
    dbKey.forEach(k => {  //k for key
        if (body[k]) {
            data[k] = body[k];
        }
        else {
            data[k] = "";
        }
    });
    dbArray.push(data);
    res.send(`You sent: ${JSON.stringify(body)}`);
});

// IMPLEMENT A ROUTE TO HANDLE ALL OTHER ROUTES AND RETURN AN ERROR MESSAGE
app.all('*', (req, res) => {
    res.sendStatus(403); // HTTP 403 Forbids the request
});
app.listen(port, () => {
    console.log(`She's alive on port ${port}`);
});
