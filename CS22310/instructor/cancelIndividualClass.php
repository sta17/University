<?php
$page_title = "NCF Cancel Individual Class";
$page_header = "North Ceredigion Fitness(NCF) Cancel Individual Class Page";
$page_description = "North Ceredigion Fitness Cancel Individual Class webpage";
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title><?php echo $page_title; ?></title>
        <meta charset="UTF-8" />
        <meta name="description" content="<?php echo "" . $page_description . ''; ?>">
        <meta name="author" content="Aber User Sta17">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="../CSS/print.css" rel="stylesheet" type="text/css" media="print" />
        <link href="../CSS/screen.css" rel="stylesheet" type="text/css" media="screen, projection" />

    </head>
    <body>
        <header>
            <h1><?php echo $page_header; ?></h1>
        </header>
?>

<main>
        <div id="container">
            <div id="menu">
                <nav>
                    <ul id="menu">
                    <li><a href="../index.php">Main</a></li>
                    <li><a href="../about.php">About</a></li>
                    <li><a href="../classdescription.php">Class Description</a></li>
                    <?php
                    echo '<li><a href="../logout.php">Logout</a></li>';
                    echo '<li><a href="cancelIndividualClass.php">Cancel a Class</a></li>';
                    echo '<li><a href="editIndividualClass.php">Edit a Class</a></li>';
                    ?>
                </ul>
                </nav>   
            </div>  
            <div id="main">
                <section>
                    <h2>To cancel an Individual Class</h2>
                    <p>
                        To cancel an individual class, please pick a class from the list below, once one is picked,
                        Once done, click submit, and that will be it. If you want to add a message explaing why it is 
                        cancelled, then there is a textfield below, where a reason can be stated.
                    </p>
                    <form><br>
                        Classname: <select name="classnames">
                            <option value="circuits">Circuits</option>
                            <option value="zumba">Zumba</option>
                            <option value="chairaerobics">Chair Aerobics</option>
                            <option value="Joga">joga</option>
                        </select><br><br>
                        Cancel Message:<br>
                        <textarea name="CancelMessage" placeholder="You can give a reason for canceling the class here." rows="10" cols="30">
                                
                        </textarea>

                        <br><br>
                        <input type="submit" value="Submit">
                    </form>
                </section>
            </div>     
        </div>
</main>

<footer>
    <?php include '../phps/footer.html'; ?>
</footer>

</body>
</html>