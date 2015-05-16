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
        <p>Logged in as <?php echo "" . $_SESSION['enteredby'] . ""; ?> </p>
        <form action="index.php" method="post">
        <p><input type="submit" name="logout" value="log out" /></p>
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