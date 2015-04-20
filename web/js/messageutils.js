/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function encryptMessage(){
    
    var message = document.getElementById("message").value;
    
    //recipients public key - retrieve from an element called publickey
    var publickey = document.getElementById("publicKey").innerHTML;
    var beginning = "-----BEGIN PUBLIC KEY-----";
    var end = "-----END PUBLIC KEY-----";
    publickey = beginning.concat(publickey);
    publickey = publickey.concat(end);

    //calls instance of JSEncrypt and encrypts the message
    var crypt = new JSEncrypt();
    crypt.setPublicKey(publickey);
    var encryptedmessage = crypt.encrypt(message);
    
    //changes the message to encrypted form
    document.getElementById("message").value = encryptedmessage;
}


function decryptMessage(){
    
    //your private key - retrieved from local storage
    var privatekey; 
    
    var encryptedkey;
    var decryptedkey;
    
    var passcode = document.getElementById("passcode").value;
    var message = document.getElementById("message").value;
    
    if (typeof(Storage) != "undefined") {
        
        encryptedkey = localStorage.getItem("encryptedkey");
        decryptedkey = decryptKey(encryptedkey, passcode);
        
    } else {
        document.getElementById("test").innerHTML = "Sorry, your browser does not support Web Storage...";
    }
    
    var beginning = "-----BEGIN RSA PRIVATE KEY-----";
    var end = "-----END RSA PRIVATE KEY-----";
    var private = beginning.concat(decryptedkey);
    private = private.concat(end);

    
    //calls instance of JSEncrypt and encrypts the message
    var crypt = new JSEncrypt();
    crypt.setPrivateKey(private);
    var decryptedmessage = crypt.decrypt(message);
    if(!decryptedmessage) {
        decryptedmessage = "Forgot your passcode? Ask a Librarian";
    }
    document.getElementById("message").value = decryptedmessage;
    document.getElementById("div_login_passcode").innerHTML = "";
}
