<?php
include 'DatabaseConfig.php';
$con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);

// Check connection
if (mysqli_connect_error($con)) {
    die("Failed to connect to MySQL: " . mysqli_connect_error());
}

$nim = $_GET['NIM'];
// query the application data
$sql = "SELECT * FROM user WHERE NIM = '$nim'";
$result = mysqli_query($con, $sql);

// an array to save the application data
$rows = array();

// iterate to query result and add every rows into array
while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
    $rows[] = $row;
}
// echo the application data in json format
echo json_encode($rows);

// close the database connection
mysqli_close($con);
