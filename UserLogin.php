<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include 'DatabaseConfig.php';

    $con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);

    $nim = $_POST['NIM'];
    $password = $_POST['PASSWORD_MHS'];

    $sql_query = "SELECT * from user WHERE NIM = '$nim' AND PASSWORD_MHS = '$password' ";

    $check = mysqli_fetch_array(mysqli_query($con, $sql_query));
    if (isset($check)) {
        echo 'Data Matched!';
    } else {
        echo 'Invalid Username or Password. Please Try Again!';
    }
} else {
    echo 'Check Again!';
}
mysqli_close($con);
