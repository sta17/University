<?php
session_save_path("/tmp/"); // Avoids session cookie permissions problem
session_start(); // start up your PHP session! 
if(isset($_SESSION['views']))
    $_SESSION['views'] = $_SESSION['views']+ 1;
else
    $_SESSION['views'] = 1;
?>