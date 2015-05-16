<?php
session_save_path("/tmp/"); // Avoids session cookie permissions problem
session_start();

if (!isset($_SESSION['CREATED'])) {
    $_SESSION['CREATED'] = time();
} else if (time() - $_SESSION['CREATED'] > 1800) {
    // session started more than 30 minutes ago
    session_regenerate_id(true);    // change session ID for the current session and invalidate old session ID
    $_SESSION['CREATED'] = time();  // update creation time
}

if (isset($_SESSION['LAST_ACTIVITY']) && (time() - $_SESSION['LAST_ACTIVITY'] > 1800)) {
    // last request was more than 30 minutes ago
    session_unset();     // unset $_SESSION variable for the run-time 
    session_destroy();   // destroy session data in storage
}
$_SESSION['LAST_ACTIVITY'] = time(); // update last activity time stamp

// The code which follows is for checking if the user is authorised to enter data into the database via this page
if (isset($_POST['logout'])){
	session_destroy();
	header('Location: index.php'); // Force page reload after logout
}

if ($_POST['enteredby']){
	$_SESSION['enteredby']= $_POST['enteredby'];
}

if ($_POST['check_list']){
	$_SESSION["buy_order"] = $_POST['check_list'];
}

$allowed=array(
	"A"=>"*******",
        "sta17"=>"*******",
	"your-name-here"=>"*******"
);

// Have we got a valid username...
$gotusername=((isset($_SESSION['enteredby']))&&(array_key_exists($_SESSION['enteredby'], $allowed)));
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><?php echo $page_title; ?></title>
    <meta charset="UTF-8" />
    <meta name="description" content="<?php  echo "" . $page_description . ''; ?>">
    <meta name="author" content="Aber User Sta17">
    <link rel="stylesheet" type="text/css" href="standardlook.css" />
    
    <script> <!--
        
    function val(){
    var chks = document.getElementsByName('check_list[]');
    var hasChecked = false;
    for (var i = 0; i < chks.length; i++){
        var t = i + 1;
            if (chks[i] == t){
            hasChecked = true;
            break;
        }
    }
    if (hasChecked == false){
	alert("Please select atleast 1 track to buy!");
	return false;
    }
    return true;
    }   
    function validate(orderForm) {
        //pricefield - numbers only, search field.
        //creditcard - numbers only.
        //login - basic sanitasion, because why not?
        //email - check for real email.
	orderForm.onsubmit=function(){
            <?php 
            function curPageName() {
                return substr($_SERVER["SCRIPT_NAME"],strrpos($_SERVER["SCRIPT_NAME"],"/")+1);
            }
                if(curPageName() === "store.php"){ ?>
                    //var check_list = orderForm.elements["check_list[]"].value;
                    var check_list = var chks = document.getElementsByName('check_list[]');
                    var hasChecked = false;
                    for (var i = 0; i < check_list.length; i++){
                        var t = i + 1;
                            if (check_list[i] == t){
                            hasChecked = true;
                            break;
                        }
                    }
                    if (hasChecked == false){
                        alert("Please select atleast 1 track to buy!");
                        return false;
                    }
     	     	if(orderForm.elements["pricefield"].value.length==0) {
        		alert("You have not entered a price to search!");
        		return false;
                } else {
	     		var numberRegexp=/^([0-9])+$/; // 1 or more numbers
			if(!numberRegexp.test(orderForm.elements['pricefield'].value)){
				alert("Can only search in Price! Numbers only!");
				return false;
			}
		} <?php
                } else if(curPageName() == "index.php"){ ?>
                if(orderForm.elements['enteredby'].value.length==0) {
        		alert("You have not entered your Login Details!");
        		return false;
	     	} <?php
                } else if(curPageName() == "basket.php"){ ?>
		// VERY useful regexp! ...
		var emailRegexp=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!emailRegexp.test(orderForm.elements['email2'].value)){
			alert("Invalid email address!");
			return false;
		}
                
                if(orderForm.elements['creditcard'].value.length==0) {
        		alert("You have not entered a card number!");
        		return false;
                } else if(orderForm.elements['creditcard'].value.length!=16){
                                alert("Credit Card Number must be 16 numbers long!");
				return false;    
                } else {
	     		var numberRegexp=/^([0-9])+$/; // 1 or more numbers
			if(!numberRegexp.test(orderForm.elements['creditcard'].value)){
				alert("Credit Card Number must be an numbers!");
				return false;
			}
		} 
                 <?php } ?>
	      	return true;
	} }
--> </script>
<script src='jquery-2.1.1.min.js'></script>
<script src='main.js'></script>
</head>
<body>
<header>
	<h1><?php echo $page_header; ?></h1>
</header>
<?php
$viewmonth=date("n");
if (($viewmonth==12)||($viewmonth<7))
{
if (isset($_POST["viewsource"])) {echo"<hr />";highlight_file(__FILE__);}
else { echo" <form action=" . $_SERVER["PHP_SELF"] . " method=\"post\"> 
<p><input type=\"submit\" name=\"viewsource\" value=\"View source\"/></p></form> ";
} }
?>