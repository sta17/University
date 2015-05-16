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
if (isset($_POST['logout'])) {
    session_destroy();
    header('Location: index.php'); // Force page reload after logout
}

if ($_POST['enteredby']) {
    $_SESSION['enteredby'] = $_POST['enteredby'];
}

if ($_POST['check_list']) {
    $_SESSION["buy_order"] = $_POST['check_list'];
}

$allowed = array(
    "admin@email.com" => "admin",
    "a" => "admin",
    "instructor@email.com" => "instructor",
    "i" => "instructor",
    "member@email.com" => "member",
    "m" => "member"
);

$admin = array(
    "createClass.php" => "**",
    "createInstructor.php" => "**",
    "deleteClass.php" => "**",
    "editClass.php" => "**",
    "promoteInstructor.php" => "**",
    "index.php" => "**",
    "about.php" => "**",
    "login.php" => "**",
    "classdescription.php" => "**"
);
$instructor = array(
    "cancelIndividualClass.php" => "**",
    "editIndividualClass.php" => "**",
    "index.php" => "**",
    "about.php" => "**",
    "login.php" => "**",
    "classdescription.php" => "**"
);
$member = array(
    "cancelenrollment.php" => "**",
    "enroll.php" => "**",
    "index.php" => "**",
    "about.php" => "**",
    "login.php" => "**",
    "classdescription.php" => "**"
);

// Have we got a valid username...
$gotusername = ((isset($_SESSION['enteredby'])) && (array_key_exists($_SESSION['enteredby'], $allowed)));

function curPageName() {
    return substr($_SERVER["SCRIPT_NAME"], strrpos($_SERVER["SCRIPT_NAME"], "/") + 1);
}

if ($gotusername == true && $allowed[$_SESSION['enteredby']] == "admin") {
    if (curPageName() == (array_key_exists($_SESSION['enteredby'], $admin))) {
        $gotpermission = 1;
    }
}

if ($gotusername == true && $allowed[$_SESSION['enteredby']] == "instructor") {
    if (curPageName() == (array_key_exists($_SESSION['enteredby'], $instructor))) {
        $gotpermission = 1;
    }
}

if ($gotusername == true && $allowed[$_SESSION['enteredby']] == "member") {
    if (curPageName() == (array_key_exists($_SESSION['enteredby'], $member))) {
        $gotpermission = 1;
    }
}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title><?php echo $page_title; ?></title>
        <meta charset="UTF-8" />
        <meta name="description" content="<?php echo "" . $page_description . ''; ?>">
        <meta name="author" content="Aber User Sta17">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="CSS/print.css" rel="stylesheet" type="text/css" media="print" />
        <link href="CSS/screen.css" rel="stylesheet" type="text/css" media="screen, projection" />

        <script><!--

            function validate(orderForm) {
                orderForm.onsubmit = function () {
<?php
if (curPageName() == "login.php") {
    ?>
                        // VERY useful regexp! ...
                        var emailRegexp = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                        if (!emailRegexp.test(orderForm.elements['login'].value)) {
                            alert("Invalid email address!");
                            return false;
                        }
<?php } else if (curPageName() == "register.php") {
    ?>

                        if (!emailRegexp.test(orderForm.elements['email'].value)) {
                            alert("Invalid email address!");
                            return false;
                        }
                        
                        var name = /^[A-Z][a-z ,.'-]+$/;
                        if (!name.test(orderForm.elements['lastname'].value)) {
                            alert("Invalid name format!");
                            return false;
                        }
                        
                        if (!name.test(orderForm.elements['firstname'].value)) {
                            alert("Invalid name format!");
                            return false;
                        }
                        
<?php } ?>
                    return true;
                };
            }
--> </script>
    </head>
    <body>
        <header>
            <h1><?php echo $page_header; ?></h1>
        </header>