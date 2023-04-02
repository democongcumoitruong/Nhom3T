<?php
	include "connect.php";	
	$iduser= $_POST['iduser'];
	$query= 'SELECT * FROM `donhang` WHERE `iduser` = '.$iduser.' ORDER BY madonhang DESC';
	$data = mysqli_query($conn,$query);
	$result = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$sqlldl= 'SELECT * FROM `chitietdonhang` INNER JOIN mathang ON chitietdonhang.idsap = mathang.mamathang WHERE chitietdonhang.iddonhang = '.$row['madonhang'];
		$dataldl=mysqli_query($conn,$sqlldl);
		$item = array();
		while ($row1 = mysqli_fetch_assoc($dataldl)){
			$item[] =$row1;
		} 
			$row['item']=$item;
			$result[]=$row;
	}
	if(!empty($result)){
		$arr=[
			"success" => true,
			"message" => "thanh cong",
			"result" => $result,
		];
	}
	else{
			$arr=[
			"success" => false,
			"message" => "khong thanh cong",
			"result" => $result,
			];
		}

	print_r(json_encode($arr));

?>