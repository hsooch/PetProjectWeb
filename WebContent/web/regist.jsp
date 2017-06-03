<%@page import="com.pet.member.model.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	response.setContentType("text/html; charset=utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
ul {
	list-style-type: none;
}

label {
	displayed: inline-block;
	width: 120px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script>
	//프로필 사진 관한곳
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	var a;
	var b;
	window.addEventListener("load", function() {
		document.getElementById('sample6_postcode').readOnly = true;
		a = document.getElementById('sample6_address');
		a.readOnly = true;
		// 커서를 상세주소 필드로 이동한다.
		b = document.getElementById('sample6_address2');
		b.readOnly = true;
		document.getElementById('sample6_address3').readOnly = true;
		document.getElementById('sample6_address4').readOnly = true;
		document.getElementById('sample6_address5').readOnly = true;
	});

	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							//법정동명이 있을 경우 추가한다.
							if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('sample6_address').value = fullAddr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById('sample6_address2').readOnly = false;
						document.getElementById('sample6_address2').focus();
						findXY();
					}
				}).open();
	}
	var setId;
	function getHttpXMLRequest() {
		var xhttp;
		if (window.XMLHttpRequest) {//다른모든버전
			xhttp = new XMLHttpRequest();
		} else {//IE5,6이하 버전일 경우
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xhttp;
	}
	//아이디 폼 체크
	function idCheck() {
		if (form1.id.value == "") {
			alert('아이디를 입력하세요');
			form1.id.focus();
		} else {
			if (form1.id.value.length < 2) {
				alert('아이디는2자이상입니다');
				form1.id.value = null;
				form1.id.focus();
			} else if (form1.id.value.includes(" ")) {
				alert('아이디에 띄어쓰기 넣지마세요');
				form1.id.value = null;
				form1.id.focus();
			} else {
				goIdCheck();
			}
		}
	}
	function findXY() {
		var xhttp = getHttpXMLRequest();
		xhttp
				.open(
						"Post",
						"http://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=ko&address="
								+ form1.loc.value, true);//전송준비상태, 비동기시 트루
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhttp.send();//서버전송일어남
		xhttp.onreadystatechange = function() {//콜백 메서드
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				var json = eval('(' + xhttp.responseText + ')');
				if (json.results.length > 0) {
					var lat = json.results[0].geometry.location.lat;
					var lng = json.results[0].geometry.location.lng;
					alert('위도 : ' + lat + ' , 경도 : ' + lng);
					form1.locX.value = lat;
					form1.locY.value = lng;
				} else {
					alert('검색된 위치 정보가 없습니다.');
					form1.locX.value = "";
					form1.locY.value = "";
				}
			}
		}
	}
	//서버에 아이디 요청
	function goIdCheck() {
		var xhttp = getHttpXMLRequest();
		xhttp.open("Post", "/app/login/" + form1.id.value, true);//전송준비상태, 비동기시 트루
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		xhttp.send();//서버전송일어남

		xhttp.onreadystatechange = function() {//콜백 메서드
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				var obj = xhttp.responseText;
				var msg = JSON.parse(obj).msg;
				if (msg == "아이디가없습니다") {
					setId = true;
					alert('가입가능한 아이디입니다');
				} else {
					alert(msg);
					form1.id.value = null;
					setId = false;
					form1.id.focus();
				}
			}
		};

	}
	//다채워질시 보내질 메서드
	function goregist() {
		if (setId) {
			alert('가입');
			form1.action = "/app/memberw";
			form1.encoding = "multipart/form-data";
			form1.method = "post";
			form1.submit();
		} else {
			alert('아이디 유효성 검사를 하세요');
			form1.id.focus();
		}
	}
	//채울거 확인후 메서드 실행
	function regist() {
		if (!setId) {
			alert('아이디 유효성 검사를 하세요');
			form1.id.focus();
			return;
		}
		form1.location.value = a.value + " " + b.value;
		if (form1.locX.value == "") {
			alert('지번주소로다시입력하세요');
			return;
		}
		checkPwd();

	}
	//비번 확인 메서드
	function checkPwd() {
		if (form1.pwd.value == "") {
			alert('비밀번호를 입력하세요');
			return;
		}
		if (form1.pwd.value.includes(" ")) {
			alert('비밀번호에띄어쓰기 넣지마세요');
			return;
		}
		form1.pwd.value = form1.pwd.value.toLocaleLowerCase();
		goregist();
	}
	function idChange() {
		setId = false;
	}
</Script>
</head>
<body>
	<form name="form1">
		<fieldset>
			<legend>회원등록</legend>
			<ul>
				<li><label>아이디:</label> <input type="text" name="id"
					onChange="idChange()"> <input type="button"
					onClick="idCheck()"></li>
				<li><label>비밀번호:</label> <input type="text" name="pwd">
				</li>
				<li><label>핸펀:</label> <input type="text" name="phone">
				</li>
				<li><label>Name :</label> <input type="name" name="name"
					id="name" placeholder="이름을 입력하세요"></li>
				<li><label>집:</label> <input type="text" id="sample6_postcode"
					placeholder="우편번호"> <input type="button"
					onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" name="loc" id="sample6_address" placeholder="주소">
					<input type="text" id="sample6_address2" placeholder="상세주소">
					<input type="text" id="sample6_address3" name="location"
					placeholder="진짜주소"> <input type="text"
					id="sample6_address4" name="locX" placeholder="위도"> <input
					type="text" id="sample6_address5" name="locY" placeholder="경도">

				</li>
				<label>성별</label>
				<select name="gender">
					<option value="남자" selected="selected">남</option>
					<option value="여자">여</option>
				</select>
				<li><label>이메일:</label> <input type="text" name="email"></li>
				<li><label>사진:</label> <input type="file" name="myfile"
					accept="image/*" id="imgfile" onchange="readURL(this)"></li>
				<img src="/data/photo_1.png" width="100px" id="preview"
					alt="your image">
			</ul>
			<input type="button" onClick="regist()">
		</fieldset>
	</form>

</body>
</html>