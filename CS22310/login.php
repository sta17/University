<?php
$page_title = "NCF Login";
$page_header = "North Ceredigion Fitness(NCF) Login Page";
$page_description = "North Ceredigion Fitness login webpage";
include 'phps/header.php';
?>

<main>
    <div id="container">
        <div id="menu">
            <nav>
                <?php include "phps/nav.php";
                echo "\r\n";
                ?> 
            </nav>   
        </div>  
        <div id="main">
            <section>
	<p>You can log in below, if you are not registered and do not have an account, you can go to the 
            <a href="register.php">Register page</a>.<br>.<br>
        The email is used as a user name for login purposes, please enter it below.
        </p>
        <form name="loginform" action="index.php" method="post">
        <p>Enter your Email: </p><input type="text" name="login" size="20" maxlength="20" placeholder="your@email.com" required/>
        <input type="submit" name="send" value="Login" /> <input type="reset" />
        </form>
            </section>
        </div>     
    </div>    
</main>

<footer>
    <?php include 'phps/footer.html'; ?>
</footer>

</body>
</html>