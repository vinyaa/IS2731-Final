/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//These functions will grab the div returned by the server on the generated page.

// Check browser support - function adapted from w3schools


//This function is only to be used the first time the user gets their key from the server and saves it
//The passcode they enter encrypts the private key before it is stored

function getKey(){
    
    var privatekey;
    var passcode;
    var encryptedkey;
    
    passcode = document.getElementById("passcode").value;
    privatekey = document.getElementById("myprivate").innerHTML;
    encryptedkey = encryptKey(privatekey, passcode);
    
    if (typeof(Storage) != "undefined") {
        // Store
        localStorage.setItem("encryptedkey", encryptedkey);
        
    } else {
        document.getElementById("test").innerHTML = "Sorry, your browser does not support Web Storage...";
    }
}


// This function will get the key from local storage and decrypt it with AES
function unlockKey(){
    
    var privatekey;
    var passcode;
    var encryptedkey;
    var decryptedkey;
    
    passcode = document.getElementById("passcode").innerHTML;
    
    if (typeof(Storage) != "undefined") {
        
        encryptedkey = localStorage.getItem("encryptedkey");
        decryptedkey = decryptKey(encryptedkey, passcode);
        
    } else {
        document.getElementById("test").innerHTML = "Sorry, your browser does not support Web Storage...";
    }
    
    //this is for the test to show key on decryptexample (can be removed)
    document.getElementById("test").innerHTML = decryptedkey.toString();
    return null;
}


//the following two functions assume that the required crypto.js files have been included in the html document
function encryptKey(privatekey, passcode){
    
    var encrypted = CryptoJS.AES.encrypt(privatekey, passcode);
    return encrypted;
    
}

function decryptKey(encryptedkey, passcode){
    
    var decrypted = CryptoJS.AES.decrypt(encryptedkey, passcode);
    
    //we need to convert hex to ascii to get the original private key back
    decrypted = hexToAscii(decrypted);
    return decrypted;
    
}

// http://stackoverflow.com/questions/3745666/how-to-convert-from-hex-to-ascii-in-javascript
function hexToAscii(hextext) {
    var hex = hextext.toString();
    var str = '';
    for (var i = 0; i < hex.length; i += 2)
        str += String.fromCharCode(parseInt(hex.substr(i,2),16));
    return str;
}

