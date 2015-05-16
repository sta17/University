<?php
$page_title = "NCF About";
$page_header = "North Ceredigion Fitness(NCF)About Page";
$page_description = "North Ceredigion Fitness about webpage";
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
                <h3>Originality and Sources</h3>
                <p>
                    Most of the content are based on the material of Dr. A.D.Shaw our tutor, found <a href="http://users.aber.ac.uk/ais/examples/">here</a>, who provides it as part of the cources teachings. Anything else is by me unless stated below.<br />
                    <br />
                    <a href="http://www.css3.info/preview/rounded-border/">CSS3.info</a> provided the information in regards to the borders that is around the header and menu.<br /><br />
                    <a href="http://www.webdesignhot.com">webdesignhot.com</a> provided the flower that can be found in the header image, it is free web resources.<br />
                    <a href="http://stackoverflow.com/questions/712761/how-to-find-day-of-week-in-php-in-a-specific-timezone">stackoverflow.com</a> Provides the day manipulation for the day of the week code for dayschedule page.<br />
                    <a href="http://www.instructables.com/id/Make-your-HTML-Website-suitable-for-Mobile-Devices/">instructables.com</a> Provides the scaling website bits.<br />

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