<?php
$page_title = "NCF Register";
$page_header = "North Ceredigion Fitness(NCF) Register Page";
$page_description = "North Ceredigion Fitness Register webpage";
include 'phps/header.php';
?>

<main>
    <div id="container">
        <div id="menu">
            <nav>
                <?php
                include "phps/nav.php";
                echo "\r\n";
                ?> 
            </nav>   
        </div>  
        <div id="main">
            <section>
                <form name="register" action="index.php" method="post">
                    Firstname: <input type="text" name="firstname" placeholder="First name" required><br>
                    Lastname: <input type="text" name="lastname" placeholder="Last name" required><br>
                    Email: <input type="email" name="email" placeholder="email@address.com"  required><br>
                    <input type="submit" value="Submit">
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