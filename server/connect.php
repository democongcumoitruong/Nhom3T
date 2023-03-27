<?php
	$host="localhost";
	$username ="root"
	.$pass="";
	$database="sanpham";
	
	$conn= mysqli_connect($host, $username, $pass, $database);
	mysqli_set_charset($conn, 'UTF8');
	
?>