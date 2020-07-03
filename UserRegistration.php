<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include 'DatabaseConfig.php';

    $con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);
    $nim = $_POST['NIM'];
    $nama = $_POST['NAMA_MHS'];
    $prodi = $_POST['PRODI'];
    $password = $_POST['PASSWORD_MHS'];

    $checkSQL = "SELECT * FROM user WHERE NIM = '$nim'";
    $check = mysqli_fetch_array(mysqli_query($con, $checkSQL));

    if (isset($check)) {
        echo 'Email Already Exist, Please Enter Another Email!';
    } else {
        $sql_query = "INSERT INTO user (NIM, NAMA_MHS, PRODI PASSWORD_MHS) values ('$nim', '$nama', '$prodi', '$password')";
        if (mysqli_query($con, $sql_query)) {
            echo 'User Registration Successfully';
        } else {
            echo 'Something Went Wrong!';
        }
    }
    mysqli_close($con);
}
