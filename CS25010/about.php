<?php 
$page_title="Music Store's about";
$page_header="About Page";
$page_description="Music Store about page";
include 'phps/header.php';?>
<nav>
<?php include "phps/nav.php"; echo "\r\n"; ?>    
</nav>
<main>
<section>
<?php
    if ($gotusername){	
        echo '<p>Logged in as ' . $_SESSION['enteredby'] . '</p>';
        
        echo '<form action="index.php" method="post">';
        echo '<p><input type="submit" name="logout" value="log out" /></p>';
        echo "    </form>";
    } ?>    
<h3>Originality</h3>
<p>
Most of the content are based on the material of Dr. A.D.Shaw our tutor, found <a href="http://users.aber.ac.uk/ais/examples/">here</a>, who provides it as part of the cources teachings. Anything else is by me unless stated below.<br />   
</p>
<h3>Working on the Assignment</h3>
<p>
When i started this assignment i thought about the basic structure and number of pages required. I concluded that having a basic, start page(aka index), a store page, a page that handeled the basket and one about page was propobly the best way, as this seperated it up good enough rather than try to squise everything into one page. I also started making the pages in this order simply because i figured it would be the best way to handle the difficult part. I started out designing the index page and the "login" feature, borrowed this from my first year web assignment and from the managemusic page by AD Shaw, our lecturer and fitted it onto the page, had some initial problems, because i did not understand how quite it worked, but once i found the array with user names, i got it to work, the index page code is pretty much adapted directly from their. The header.php has some extra features in regards to login that i found on the internet regarding expiration of the session that i felt should be their, to fix these issues i implemented some extra lines regarding session expiration and renewal. 
<br /><br />
Splitting up the pages with the includes for parts header, nav(navigation) and footer was something that i did rather early in the year, because i figured that learning these bits would be wise for later when i started on the assignment, thus the basic page setup inregards to php was done without much difficulty. Thus the basic there was already done and could be copy pasted from my adaptation of last years assignment code. The code in regards to login and session in header.php file, where mostly new bit, as handeling sessions was something i was initially worried about, but as adapting the code from managemusic took me nothing more than a Saturday with straight adaption once i understood what part did what and i was done with that. The real difficulty came in printing the checkboxes and limiting what colums to print. The colums and database request was something i was slow to understand, but got it working within a day if i dont remember wrong. In regards to the search feature, so did i start working on that after getting the right colums to appear, i theorised on basically how to implement the search and as it happened, my first idea turned out to work rather perfectly thus that was done, with some considerations later, created a php function to help with tidiness and code reuse at this point also.
<br /><br />
The Checkboxes for the colums was a real nasty bit for me, as i had forgotten about array and was scrathing my head how to implement it, it took some many hours of wrangling with it, but got that working to, the "got that working to" might be subject to change pending good test, but it appears to be superficially to work anyways. The highlighting of the description was another headache, i did not get it to work initially, tried several examples, found one in the end i could download, i watched my downloaded example work, but not my implemenation of it which drove me nuts for several hours, got it to work in the end with the fault being on some tiny error in markup somewhere. During the course of my search for my previously mentioned problem i found the free implemenation version of row highlighting which i implemented without too much trouble. More trouble came when i implemented the basket page, initially everything with the table and such worked fine, but then came checkbox page change and form problems. The issue of once a person came to the basket page, going back was a big issue as the most central code snippet from the login bit that was used to update the username from the page had passed me by. It took some many more hours of wrangling with code, testing and reworking stuff before i was able to have a page that recorded the checkboxes upon change to basket page and then repopulated the checkboxes when changing to the store page again. Another thing worth mentioning in regards to the table on the basket page is the total price, the basic thing was simply implemented after an idea of turning the row printer into a price summeriser in addition, which worked rather neatly, adding another thing to my page.
<br /><br />
In regards to the forms, i desided that as many of the form input fields should be HTML5 type input fields, simply because it would check it in browser and thus insure of correction even if javascript had for example been turned off. The other form issues and Javascript authentication and testing was a big issue as i did not initially get it to work, had been implemented corretly, but did not correct the form inputs at all, i eventually traced the problem down to some syntacs errors that i believe disappeard after some reworking and thus why it at some point started working perfectly as intended. To test the Javascript for the forms i changed the input forms to normal text type so that the browers would not check it and the javascript would be able to work totally alone. While i am on the subject of testing, i also did tests for the table generation on the basket page, the sum of price, reprint of email and credit card(what else am i supposed to do with it?). After i print the email, credit card and what you "buy" i erase the session variable, since, now you have "paid for it", thus keeping it around is no longer nessessary. While i got most to work, there where some one thing i did not get to work, i tried to make a javascript that checked the checkbox array, to see if it contained any values at all, thus, is it empty? This have for some reason failed, despite several modifications, i believe i am close, but it fails for some reason, its either syntax problems or comparison failure thats the issue, i have tried the most obvious comparison but no luck, i have put the code in soo that you can see i tried.
</p>
<h3>Evaluation</h3>
<p>
Overall it was a fun assignment, always liked the coding, learned good php out of it, it was also not overly long which i liked, as it took me exacly a week as i am writing this on a friday. If i could improve it i would get some different wallpapers and banners, something music theme, as per assignment, but i acidentally overwrote my musicy wallpaper so it got scrapped. I have noticed that there is some small fiddle with the array and basket page around 1 and 0 tracks, i would like to fix these, maybe add some more forms. Some basic sanitsation for the login input on index page might also be due, not that it would be usefull since i dont access the database, but just as extra feature. Some more images, maybe add some images for the tracks, as the description highlight allowed for images to be added to, improve the css function, but it worked at the basic, which was why did not do much with it.
</p>
<h3>Sources</h3>
<p>
<a href="http://www.css3.info/preview/rounded-border/">CSS3.info</a> provided the information in regards to the borders that is around the header and menu.<br /><br />
<a href="http://www.webdesignhot.com">webdesignhot.com</a> provided the flower that can be found in the header image, it is free web resources.<br />
<a href="http://www.webdesignhot.com/free-vector-graphics/floral-design-element-vector/">webdesignhot.com</a> provided the floral part of the background image, it is free web resources.<br />
<a href="http://www.free-vectors.com/free-musical-notes-vector-free-download/">Free-vectors.com</a> provided the music part of the background image, it is free web resources.<br />
<a href="http://www.pageresource.com/html5/input-placeholder-tutorial/">Pageresource.com/html5tutorials.html</a> Code and help with HTML5 form inputs, this is about placeholder values, form attributes.<br />
<a href="http://www.pageresource.com/html5/input-validation-tutorial/">Pageresource.com/html5tutorials.html</a> Code and help with HTML5 form inputs, this is about form validation values, number, text, etc.<br />
<a href="http://stackoverflow.com/questions/4997252/get-post-from-multiple-checkboxes">Stackoverflow.com</a> First answer, Sean Walsh. Helped with the code to get the checkboxes to work for the table.<br />
<a href="http://phpprogramming.wordpress.com/2007/02/25/php-arrays-tutorial-and-examples/">Phpprogramming.wordpress.com</a> Also provided code i looked at to help spawn the table on basket page, to make the table there.<br />
<a href="http://stackoverflow.com/questions/520237/how-do-i-expire-a-php-session-after-30-minutes">Stackoverflow.com</a> First answer, Gumbo. I took the code about session expiration from there.<br />
<a href="http://www.mkyong.com/jquery/how-to-highlight-table-row-record-on-hover-with-jquery/">mkyong.com/jquery/</a> Got code for Jquery with yellow table higlight from here, modified since the base, free available.<br />
<a href="http://cssglobe.com/easiest-tooltip-and-image-preview-using-jquery/">cssglobe.com/</a> This is where i got the Javascript for the description highlight, i do not take credit for it and view it as fairly well hinted that other can take it and use it.<br />
<a href="http://www.thesitewizard.com/php/sessions.shtml">thesitewizard.com/php/</a> Its about session, storing and saving data, used it to store, what the user bought.<br />
<a href="http://www.tutscorner.com/javascript-checkbox-array-or-checkbox-group-validation/">tutscorner.com</a> I got the bit where i checked the empty array from here. <br />
The code about figuring out what site i am currently on is also taken from somewhere, i have forgotten to save that link.<br />

</p>

<?php //print_r($_SESSION);?>
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