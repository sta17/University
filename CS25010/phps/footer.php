<hr />

<?php
$viewmonth=date("n");
if (($viewmonth==12)||($viewmonth<7))
{
if (isset($_POST["viewsource"])) {echo"<hr />";highlight_file(__FILE__);}
else { echo" <form action=" . $_SERVER["PHP_SELF"] . " method=\"post\"> 
<p><input type=\"submit\" name=\"viewsource\" value=\"View source\"/></p></form> ";
} }
?>
	<p>
		<a href="mailto:sta17@aber.ac.uk">sta17@aber.ac.uk</a>
	</p>
	<p>
		<a href="http://validator.w3.org/check?uri=referer"><img
		src="http://www.w3.org/html/logo/badge/html5-badge-h-solo.png" 
		width="63" height="64"
		alt="Valid HTML 5!" /></a>
	</p>
	<p>
		<a href="http://jigsaw.w3.org/css-validator/check/referer">
		<img style="border:0;width:88px;height:31px"
		src="http://jigsaw.w3.org/css-validator/images/vcss-blue"
		alt="Valid CSS!" />
	</a>
</p>

<script>
var myDate = new Date(document.lastModified);
document.write("Last edited: " + myDate.toLocaleString());
</script>