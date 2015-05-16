<?php
$page_title = "NCF Instructor Account Promotion";
$page_header = "North Ceredigion Fitness(NCF) Instructor Account Promotion Page";
$page_description = "North Ceredigion Fitness Instructor Account Promotion webpage";
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
                    echo '<li><a href="createClass.php">Create a Class</a></li>';
                    echo '<li><a href="editClass.php">Edit a Class</a></li>';
                    echo '<li><a href="deleteClass.php">Delete a Class</a></li>';
                    echo '<li><a href="createInstructor.php">Create a Instructor Account</a></li>';
                    echo '<li><a href="deleteInstructor.php">Delete a Instructor Account</a></li>';
                    echo '<li><a href="promoteInstructor.php">Promote an Instructor</a></li>';
                    ?>
                </ul>
            </nav>   
        </div>  
        <div id="main">
            <section>
                <h2>To promote an Instructor</h2>
                    <p>
                        To promote an instructor, please enter firstname and lastname.
                        Once done, click submit, and that will be it.
                    </p>
                <form>
                    Firstname: <input type="text" name="firstname" placeholder="First name" pattern="/^[a-z ,.'-]+$/i" required><br>
                    Lastname: <input type="text" name="lastname" placeholder="Last name" pattern="/^[a-z ,.'-]+$/i" required><br>
                    <input type="submit" value="Submit"><br>
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