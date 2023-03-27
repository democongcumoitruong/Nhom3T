<?php
	include "connect.php";	
	$SDT = $_POST['SDT'];
	$matkhau = $_POST['matkhau'];
	$tenuser= $_POST['tenuser'];
	$gmail= $_POST['gmail'];
	

	$query='SELECT * FROM `user` WHERE `SDT` = "'.$SDT.'"';
	$data = mysqli_query($conn,$query);
	$numrow = mysqli_num_rows($data);
	if($numrow > 0){
		$arr=[
			'success'=>false,
			'message'=> "Số điện thoại đã tồn tại"
		];

	}else{
		$query='INSERT INTO `user`( `SDT`, `matkhau`, `tenuser`,`gmail`) VALUES ("'.$SDT.'","'.$matkhau.'","'.$tenuser.'","'.$gmail.'")';
			$data = mysqli_query($conn,$query);
			if($data == true){
				$arr = [
					'success'=>true,
					'message'=> "ok"
				];
			}else{
				$arr = [
				'success'=>false,
				'message'=> "khong thanh cong"
				];
			}
}
	print_r(json_encode($arr));
?>