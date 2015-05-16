<ul id="menu">
<li><a href="index.php">Main</a></li>
<li><a href="about.php">About</a></li>
<li><a href="classdescription.php">Class Description</a></li>

<?php
if($gotusername != true){
echo '<li><a href="login.php">Login</a></li>';
echo '<li><a href="register.php">Register an Account</a></li>';
}
if($gotusername == true && $allowed[$_SESSION['enteredby']] == "admin" ) {
    echo '<li><a href="login.php">Logout</a></li>';
    echo '<li><a href="admin/createClass.php">Create a Class</a></li>';
    echo '<li><a href="admin/createInstructor.php">Create a Instructor Account</a></li>';
    echo '<li><a href="admin/deleteClass.php">Delete a Class</a></li>';
    echo '<li><a href="admin/editClass.php">Edit a Class</a></li>';
    echo '<li><a href="admin/promoteInstructor.php">Promote an Instructor</a></li>';
}
if($gotusername == true && $allowed[$_SESSION['enteredby']] == "instructor" ) {
    echo '<li><a href="instructor/login.php">Logout</a></li>';
    echo '<li><a href="instructor/cancelIndividualClass.php">Cancel a Class</a></li>';
    echo '<li><a href="instructor/editIndividualClass.php">Edit a Class</a></li>';
}
if($gotusername == true && $allowed[$_SESSION['enteredby']] == "member" ) {
    echo '<li><a href="member/login.php">Logout</a></li>';
    echo '<li><a href="member/cancelenrollment.php">Cancel an Enrollment</a></li>';
    echo '<li><a href="member/enroll.php">Enroll into a Class</a></li>';
}
?>
</ul>
