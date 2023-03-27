<?php
	include "connect.php";	
	$SDT = $_POST['SDT'];
	$matkhau = $_POST['matkhau'];
	
	

	$query='SELECT * FROM `user` WHERE `SDT`= "'.$SDT.'" AND `matkhau`="'.$matkhau.'"';
	$data = mysqli_query($conn,$query);
	$result = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$result[] =($row);
		/*array_push($sanpham,new sanpham(
		$row['mamathang'],
		$row['tenmathang'],
		$row['gia'],
		$row['hinhanhmathang'],
		$row['IDSP'],
		$row['manhacungcap'],
		$row['mota'],
	));	*/
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