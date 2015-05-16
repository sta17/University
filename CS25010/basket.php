<?php 
$page_title="Music Store's basket";
$page_header="Shopping basket";
$page_description="Music Store's basket";
include 'phps/header.php';?>
<nav>
<?php include "phps/nav.php"; echo "\r\n"; ?>    
</nav>
<main>
<section>
    <h3>Disclaimer</h3>
<p>This is not a real web shop. It is created as part of my university coursework. Please do not attempt to buy anything from this site, or enter any real card details."</p>
<?php
    if ($gotusername){
      $con = pg_connect("host=db.dcs.aber.ac.uk port=5432
      dbname=teaching user=csguest password=r3p41r3d");
    if (!con){
	//die('Could not connect: ' . pg_error());
        die('Could not connect to database. Store not loaded.');
    } else {
            echo "<p>Logged in as " . $_SESSION['enteredby'] . "</p>\r\n";
            echo "<form action='index.php' method='post' autocomplete='off'>\r\n";
            echo "<p><input type='submit' name='logout' value='log out' /></p>\r\n";
            echo "</form>\r\n";
            if(count($_POST) > 0){
                $id = $_POST['id'];
                $description = $_POST['description'];
                $price = $_POST['price'];
            }
            $creditcard = $_POST["creditcard"];
            if (!filter_input(INPUT_POST, 'email', FILTER_VALIDATE_EMAIL) && !is_numeric($creditcard)){ ?>
                <p>Tracks are sent automatically throught email, your 16 digit Credit Card number is needed for payment.</p>
                <form name='basket' action='basket.php' method='post'>
                    <p>ENTER EMAIL: <input type="email" name="email2" placeholder= "your@email.address" required="" /></p>
                    <p>ENTER CREDIT CARD NUMBER: <input type="number" name="creditcard" min='0' max='9999999999999999' placeholder="0000111100001111" required=""/></p>
  <?php    } else {
                // echo "E-Mail is valid";
                $email = $_POST["email2"];
                $creditcard = $_POST["creditcard"];
                echo "<p>Music Tracks sent to " . $email . "</p>";
                echo "<p>Credit Card number is: " . $creditcard ."</p>";
                echo "<p>As you can see, this is not a real shop, entering your real details, is a stupid descision.</p>";
            } ?>
            <script type='text/javascript' src='jquery-2.1.1.min.js'></script>
            <div id="table">
            <table><tr>
            <th>Title</th><th>Price</th><th>Artist</th><th>Genre</th><th>Description</th>
   <?php    $pricesum = 0;
            $trackCount = 0;
            $orders = $_SESSION["buy_order"];
            
//            $res=pg_query($con, "SELECT ref FROM music");
//            $i = 0;
//            while (list($id)=pg_fetch_row($res)){           
//            if(empty($orders[$i])){
//                $orders[$i] = "NO";
//            }
//            $i++;
//            }
            
            $_SESSION["buy_order"] = $orders;
            
            $i = 0;
            $res=pg_query($con, "SELECT ref, artist, genre, title, price, description FROM music ORDER BY ref");
            while (list($id, $artist, $genre, $title, $price, $description)=pg_fetch_row($res)){
                //echo " " .$orders[$i] . "";
                if(!empty($orders)) {
                    foreach ($orders as $key => $value) {
                        if($value == $id){
                            $oldsum = $pricesum;
                            $pricesum = $oldsum + $price;
                            $trackCount++;
                            echo "<tr>";
                            echo "<td>" . htmlspecialchars($title, ENT_QUOTES) . "</td>";
                            echo "<td>" . htmlspecialchars($price, ENT_QUOTES) . "</td>";
                            echo "<td>" . htmlspecialchars($artist, ENT_QUOTES) . "</td>";
                            echo "<td>" . htmlspecialchars($genre, ENT_QUOTES) . "</td>";
                            //echo "<td>" . htmlspecialchars($description, ENT_QUOTES) . "</td>";
                            echo "<td><p><a class=\"tooltip\" title=\"" . htmlspecialchars($description, ENT_QUOTES) . "\">Hold mouse over for description.</a></p></td>";
                            echo "</tr>\n";
                        }}}
            $i++; } ?>
        <th>Title</th><th>Price</th><th>Artist</th><th>Genre</th><th>Description</th>
        </table>
        </div>
        <script type="text/javascript">
        $('tr:gt(0)').hover(function() {
            if ($(this).next().length != 0) {
                $(this).find('td').css("background","#FFFFCC");  
            }
            }, function() {
                $(this).find('td').css("background", "");
            }
        );
        </script>
<?php   if (!filter_input(INPUT_POST, 'email', FILTER_VALIDATE_EMAIL) && !is_numeric($creditcard)){
            // echo "E-Mail is not valid";
            echo "<input type='submit' name='Send' value='Buy' />\r\n";
            echo "</form>\r\n";
            echo "<script>
            <!--
                new validate(document.forms['basket']);
            -->
            </script>";
        } else {
            // echo "E-Mail is valid";
            unset($_SESSION['check_list']);
        }
        echo "<p>The total price for the " . $trackCount . " is £" . $pricesum . "</p>\r\n";
        }  pg_close($con);
    } else {
        if (isset($_SESSION['enteredby'])){
        session_destroy();
        }
        echo '<script>';
        echo 'window.location.href = "http://users.aber.ac.uk/sta17/cs25010/index.php"';
        echo '</script>';
        echo '<p>If you are not redirected automatically, follow the <a href="http://users.aber.ac.uk/sta17/cs25010/index.php">link to Login page</a></p>';
    } 
    //print_r($_SESSION);
    ?>
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