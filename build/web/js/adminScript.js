/* 
    Created on : Mar 28, 2015, 4:42:53 PM
    Author     : hab81
*/

/*
 * This function clears text fields of user login area.
 */
function clearLoginText(){
    document.getElementById("text_user_name").value = "";
    document.getElementById("password_password").value = ""; 
}

/*
 * This function clears text fields of user register area.
 */
function clearRegisterText(){
    document.getElementById("text_user_name").value = "";
    document.getElementById("password_password").value = ""; 
    document.getElementById("password_password_confirm").value = ""; 
    document.getElementById("text_email_address").value = "";
    document.getElementById("text_answer").value = "";
}

/*
 * This function clears text fields of new user area.
 */
function clearUserText(){
    document.getElementById("text_user_name").value = "";
    document.getElementById("text_user_email").value = "";
    document.getElementById("password_password").value = "";
    document.getElementById("password_password_confirm").value = ""; 
    document.getElementById("text_user_answer").value = "";
    document.getElementById("select_user_rank").select = "client";
}


