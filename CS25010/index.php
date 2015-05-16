<?php
$page_title="Music Store";
$page_header="The Music Store Online!";
$page_description="Music Store main page.";
include 'phps/header.php';?>
<nav>
<?php include "phps/nav.php"; echo "\r\n"; ?>    
</nav>
<main>
<section>
<h3>Disclaimer</h3>
<p>
The information provided on this and other pages by me, Steven Aanetsen (<a href="mailto:sta17@aber.ac.uk">sta17@aber.ac.uk</a>), is
under my own personal responsibility and not that of Aberystwyth University. Similarly,
any opinions expressed are my own and are in no way to be taken as those of A.U.<br />
</p>
</section>
<section>
<h3>Login</h3>
<?php if ($gotusername){ ?>
        <p>Logged in as <?php echo "" . $_SESSION['enteredby'] . ""; ?> </p>
        <form action="index.php" method="post">
        <p><input type="submit" name="logout" value="log out" /></p>
        </form>
   <?php } else {
        if (isset($_SESSION['enteredby'])){
        echo '<p>User name ' . $_SESSION['enteredby'] . ' not recognised.</p>';
        session_destroy();
        } ?>
	<p>Not logged in; you may not buy music tracks.</p>
        <form name="login" action="store.php" method="post">
        <p>ENTER YOUR NAME: <input type="text" name="enteredby" size="8" maxlength="20" placeholder="Username" required/></p>
        <p><input type="submit" name="send" value="Login" /> <input type="reset" /></p>
        </form>
        <script type="text/javascript">
        <!--
        new validate(document.forms["login"]);
        -->
        </script>
 <?php  } 
 //print_r($_SESSION);?>
        <?php
$viewmonth=date("n");
if (($viewmonth==12)||($viewmonth<7))
{
if (isset($_POST["viewsource"])) {echo"<hr />";highlight_file(__FILE__);}
else { echo" <form action=" . $_SERVER["PHP_SELF"] . " method=\"post\"> 
<p><input type=\"submit\" name=\"viewsource\" value=\"View source\"/></p></form> ";
} }
?>
</section>    
</main>
<footer>
<?php include 'phps/footer.php';?>
</footer>
</body>
</html>