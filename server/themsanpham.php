<?php
	include "connect.php";
	$mangsanpham = array();
	$query = "SELECT * FROM `mathang` ORDER BY `mamathang` DESC ";
	$data = mysqli_query($conn, $query);
	$result=array();

	while ($row = mysqli_fetch_assoc($data)) {
		$result[] =($row);
	
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
		
	

	/*class sanpham{
		function sanpham($mamathang,$tenmathang,$gia,$hinhanhmathang,$IDSP,$manhacungcap,$mota){
			$this->mamathang=$mamathang;
			$this->tenmathang=$tenmathang;
			$this->gia=$gia;
			$this->hinhanhmathang=$hinhanhmathang;
			$this->IDSP=$IDSP;
			$this->manhacungcap=$manhacungcap;
			$this->mota=$mota;

		}*/
	
	print_r(json_encode($arr))
?>