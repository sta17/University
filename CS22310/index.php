<?php
$page_title = "NCF Home";
$page_header = "North Ceredigion Fitness(NCF) Online";
$page_description = "North Ceredigion Fitness webpage";
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
                <h2>Disclaimer</h2>
                <p>
                    The information provided on this and other pages by me, Steven Aanetsen (<a href="mailto:sta17@aber.ac.uk">sta17@aber.ac.uk</a>), is
                    under my own personal responsibility and not that of Aberystwyth University. Similarly,
                    any opinions expressed are my own and are in no way to be taken as those of A.U.<br />
                </p>
            </section>
            <section>
                <h2>Notes</h2>
                <p>
                    This is a Univercity Assignment and as of such a work of fiction. Any Details, about web material can sources can be found in the about page.
                </p>
            </section>

            <?php
            $class1cancelstatus = "false";
            $class2cancelstatus = "true";
            $class3cancelstatus = "true";
            $class4cancelstatus = "false";
            ?> 
            <h2>Circuits</h2>
            <p>
                Location: Circuit Place <br>
                Time: 17:50 2015-04-20
                Go to <a href="classdescription.php?class=Circuit">Class Description</a>
            </p>
            <h2>Zumba</h2>
            <p>
                Location: Zumba hall.<br>
                Time: 17:50 2015-04-20
                CANCELLED
                Go to <a href="classdescription.php?class=Zumba">Class Description</a>
            </p>
            <h2>Chair Aerobics</h2>
            <p>
                Location: Chair Aerobics Center.<br>
                Time: 17:50 2015-04-20
                Go to <a href="classdescription.php?class=ChairAerobics">Class Description</a>
            </p>
            <h2>Joga</h2>
            <p>
                Location: Fitness South Center.<br>
                Time: 17:50 2015-04-20
                CANCELLED
                Go to <a href="classdescription.php?class=Joga">Class Description</a>
            </p>
                  </section>
            </div>     
        </div>
</main>

<footer>
    <?php include 'phps/footer.html'; ?>
</footer>

</body>
</html>