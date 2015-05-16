<?php
$page_title = "NCF Class Decription";
$page_header = "North Ceredigion Fitness(NCF) Class Decription";
$page_description = "North Ceredigion Fitness Class Decription webpage";
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
                <?php
                if ($_GET["class"]) {
                    echo "<h2> " . $_GET['class'] . "</h2>";
                }
                ?> 
                <p>
                    Location: 
                    <?php
                    if ($_GET["class"]) {
                        echo $_GET['class'];
                    }
                    ?> 
                    hall.<br>
                    Time: 17:50 2015-04-20
                </p>
                <?php
                $private = "A private message here";

                if (($allowed[$_SESSION['enteredby']] == "instructor") || ($allowed[$_SESSION['enteredby']] == "admin")) {
                    echo "<h3>Private Details</h3>";
                    echo "<p>" + $private + "</p>";
                }

                $public = "A public message here";
                echo "<h3>Instructors Details</h3>";
                echo "<p>";
                echo $public;
                echo "</p>";

                $classcancelstatus = "true";
                if ($classcancelstatus == true) {
                    echo '<div id="cancelled">CANCELLED</div>';
                    $cancelmessage = "This is the Cancelled message, explaining why, disease, injury, problems at location, etc.";
                    echo "<h3>Cancellation Details</h3>";
                    echo "<p>";
                    echo $cancelmessage;
                    echo "</p>";
                }
                ?>
            </section>
        </div>     
    </div>
</main>

<footer>
    <?php include 'phps/footer.html'; ?>
</footer>

</body>
</html>