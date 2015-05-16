<?php
$page_title = "NCF Edit Individual Class Enrollment";
$page_header = "North Ceredigion Fitness(NCF) Edit Individual Class Page";
$page_description = "North Ceredigion Fitness Edit Individual Class webpage";
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
                <h2>To Edit an Individual Class</h2>
                <p>
                    To edit an individual class, please pick a class from the list below, once one is picked,

                    the idea is that there will be some loading as the information for the spesific class is 
                    gotten and then, they are displayed below. 

                    The Time field will accept a date of format 
                    YYYY-MM-DD, where YYYY are a year of four numbers, MM the months, and DD the days.
                    The Instructers only Notes is the field where Instructors can add notes to themselves.
                    The Public information is the information that the members and public see.
                    Once done, click submit, and that will be it.
                </p>
                <form><br>
                    Please enter the name of the class.<br>
                    Classmames: <select name="classnames">
                        <option value="circuits">Circuits</option>
                        <option value="zumba">Zumba</option>
                        <option value="chairaerobics">Chair Aerobics</option>
                        <option value="Joga">joga</option>
                    </select>
                    <input type="submit" value="Submit">
                </form>
                <form><br><br>
                    Please enter the time of the day.<br>
                            Time:
                            <select name="dayofweek">
                                <option value="monday">Monday</option>
                                <option value="tuesday">Tuesday</option>
                                <option value="wednesday">Wednesday</option>
                                <option value="thursday">Thursday</option>
                                <option value="friday">Friday</option>
                                <option value="saturday">Saturday</option>
                                <option value="sunday">Sunday</option>
                            </select>
                            <input type="time" name="Time" placeholder="HH:MM">
                            <br><br>
                    Instructers only Notes:<br>
                    <textarea name="private" placeholder="This information here will only be visible to Instructors and Administrators" rows="10" cols="30">
                                
                    </textarea><br>
                    Public information:<br>
                    <textarea name="public" placeholder="This will be visible to all." rows="10" cols="30">
                                
                    </textarea>

                    <br>
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