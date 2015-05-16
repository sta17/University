<?php 
$page_title="Music Store";
$page_header="The Music Store Online!";
$page_description="Music Store main page.";
include 'phps/header.php';?>
<script>
        function show(x) {
            document.getElementById(x).style.display = "block";
        }
        function hide(x) {
            document.getElementById(x).style.display = "none";
        }
</script>
<nav>
<?php include "phps/nav.php"; echo "\r\n"; ?>    
</nav>
<main>
<section>
    <?php
    function writeRow($id, $artist, $genre, $title, $price, $description, $value) {
    echo "<tr>";
    if ($value == $id){
        echo "<td><input type='checkbox' name=\"check_list[]\" value='" . $id . "' checked/></td>";
    } else{
        echo "<td><input type='checkbox' name=\"check_list[]\" value='" . $id . "' /></td>";
    }
    echo "<td>" . htmlspecialchars($title, ENT_QUOTES) . "</td>";
    echo "<td>" . htmlspecialchars($price, ENT_QUOTES) . "</td>";
    echo "<td>" . htmlspecialchars($artist, ENT_QUOTES) . "</td>";
    echo "<td>" . htmlspecialchars($genre, ENT_QUOTES) . "</td>";
    echo "<td><p><a class=\"tooltip\" title=\"" . htmlspecialchars($description, ENT_QUOTES) . "\">Hold mouse over for description.</a></p></td>";
    echo "</tr>\n";
    }
    if ($gotusername){
      $con = pg_connect("host=db.dcs.aber.ac.uk port=5432
      dbname=teaching user=csguest password=r3p41r3d");
    if (!con){
        die('Could not connect to database. Store not loaded.');
    } else {
        echo "<p>Logged in as " . $_SESSION['enteredby'] . "</p>\r\n";
        echo "<form action='index.php' method='post'>\r\n";
        echo "<p><input type='submit' name='logout' value='log out' /></p>\r\n";
        echo "</form>\r\n";
        if(count($_POST) > 0){
            $id = $_POST['id'];
            $description = $_POST['description'];
            $price = $_POST['price'];
        }?>
            <form name='search' action='store.php' method='get'>
            <select name='pricesearch'>
                <option>Show all</option>
		<option>Greater than</option>
		<option>Less than</option>
		<option>Equal to</option>
            </select>
            <input type="number" name="pricefield" min="0" placeholder="Search the tracks" step="0.01" />
            <input type='submit' name='search' value='Search' />
            </form>
            <script type="text/javascript">
            <!--
                new validate(document.forms['search']);
            -->
            </script>
            <?php
            $pricesearch = $_GET["pricesearch"];
            $pricefield = $_GET["pricefield"];
            $count = pg_fetch_row(pg_query ($con, "select count(ref) from music"));
            $count = $count[0];
            echo "<p>Total of " . $count . " music tracks in the store.</p>";
            ?>
            <form name='orders' action='basket.php' method='post'>
            <input type='submit' name='Send' value='Shopping basket' onclick="return val();" />
            <div id='table'>
            <table><tr>
            <th>To Buy</th><th>Title</th><th>Price</th><th>Artist</th><th>Genre</th><th>Description</th>
            <?php
            $orders = $_SESSION["buy_order"];
            $i = 0;
            $res=pg_query($con, "SELECT ref, artist, genre, title, price, description FROM music ORDER BY ref");
            while (list($id, $artist, $genre, $title, $price, $description)=pg_fetch_row($res)){
                $value = $orders[$i];
                if($pricesearch == "Greater than" && is_numeric($pricefield)){
                    if($pricefield < $price){
                        writeRow($id, $artist, $genre, $title, $price, $description,$value);
                    }
                }else if($pricesearch == "Less than" && is_numeric($pricefield)){
                    if($pricefield > $price){
                        writeRow($id, $artist, $genre, $title, $price, $description,$value);
                    }
                }else if($pricesearch == "Equal to" && is_numeric($pricefield)){
                    if($pricefield == $price){
                        writeRow($id, $artist, $genre, $title, $price, $description,$value);
                    }
                }else if($pricesearch == "Show all"){
                    writeRow($id, $artist, $genre, $title, $price, $description,$value);
                } else {
                    writeRow($id, $artist, $genre, $title, $price, $description,$value);
                }
            $i++; } ?>
        <th>To Buy</th><th>Title</th><th>Price</th><th>Artist</th><th>Genre</th><th>Description</th>
        </table>
        </div>
        <input type='submit' name='Send' value='Shopping basket' onclick="return val();" />
        </form>
            <script type="text/javascript">
            <!--
                new validate(document.forms['orders']);
            -->
            </script>
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
<?php }  pg_close($con);
    } else {
        if (isset($_SESSION['enteredby'])){
        session_destroy();
        } ?>
        <script type="text/javascript">
        window.location.href = "http://users.aber.ac.uk/sta17/cs25010/index.php"
        </script>
        <p>If you are not redirected automatically, follow the <a href="http://users.aber.ac.uk/sta17/cs25010/index.php">link to Login page</a></p>
<?php } 
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