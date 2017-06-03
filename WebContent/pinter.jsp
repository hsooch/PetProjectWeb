<%@page import="com.pet.petowner.domain.PetOwner"%>
<%@page import="java.util.List"%>
<%@page import="com.pet.member.model.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	Member member = (Member) session.getAttribute("log");
	List list = (List) request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script> 
//프로필 사진 관한곳
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('preview').src=e.target.result;
        }
        reader.readAsDataURL(input.files[0]);
    }
}
var a;
var b;
	window.addEventListener("load", function() {
		document.getElementById('sample6_postcode').readOnly = true;
		a=document.getElementById('sample6_address');
		a.readOnly = true;
		// 커서를 상세주소 필드로 이동한다.
		b=document.getElementById('sample6_address2');
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
	function findXY() {
		var xhttp = getHttpXMLRequest();
		xhttp.open("Post", "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=ko&address=" + form1.loc.value, true);//전송준비상태, 비동기시 트루
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhttp.send();//서버전송일어남
		xhttp.onreadystatechange = function() {//콜백 메서드
			if (xhttp.readyState == 4 && xhttp.status == 200) {
					var json = eval('(' + xhttp.responseText + ')');
			        if(json.results.length > 0) {
			            var lat = json.results[0].geometry.location.lat;
			            var lng = json.results[0].geometry.location.lng;
			            alert('위도 : ' + lat + ' , 경도 : ' + lng);
			            form1.locx.value=lat;
			            form1.locy.value=lng;
			        }else {
			            alert('검색된 위치 정보가 없습니다.');
			            form1.locx.value="";
			            form1.locy.value="";
			        }
		        }
			}
	}
	$(document).ready(function(){ 
		Kakao.init("34cbd98b5fb3c53fc45cd9acddcb43e2"); 		

		function getKakaotalkUserProfile(){ 
			Kakao.API.request({ 
				url: '/v1/user/me', 
				success: function(res) { 
					$("#kakao-profile").append(res.properties.nickname); 
					$("#kakao-profile").append($("<img/>",{"src":res.properties.profile_image,"alt":res.properties.nickname+"님의 프로필 사진"})); 
				}, 
				fail: function(error) { 
					console.log(error); 
				} 
			}); 
		} 
		function createKakaotalkLogin(){ 
			$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove(); 
			var loginBtn = $("<a/>",{"class":"kakao-login-btn","text":"로그인"}); 
			loginBtn.click(function(){ 
				Kakao.Auth.login({ 
					persistAccessToken: true, 
					persistRefreshToken: true, 
					success: function(authObj) { 
						getKakaotalkUserProfile(); 
						createKakaotalkLogout(); 
					}, 
					fail: function(err) { 
						console.log(err); 
					} 
				}); 
			}); 
			$("#kakao-logged-group").prepend(loginBtn) 
		} 
	function createKakaotalkLogout(){ 
			$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove(); 
			var logoutBtn = $("<a/>",{"class":"kakao-logout-btn","text":"로그아웃"}); 
			logoutBtn.click(function(){ 
				Kakao.Auth.logout(); 
				createKakaotalkLogin(); 
				$("#kakao-profile").text(""); 
			}); 
			$("#kakao-logged-group").prepend(logoutBtn); 
		} 
		if(Kakao.Auth.getRefreshToken()!=undefined&&Kakao.Auth.getRefreshToken().replace(/ /gi,"")!=""){ 
			createKakaotalkLogout(); 
			getKakaotalkUserProfile(); 
		}else{ 
			createKakaotalkLogin(); 
		} 
	}); 
</script>
<!-- Pet Regist 모달 영역 끝!! -->
<script>
// Script to open and close sidenav
function w3_open() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
    document.getElementsByClassName("w3-overlay")[0].style.display = "block";
}
 
function w3_close() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
    document.getElementsByClassName("w3-overlay")[0].style.display = "none";
}
function regist(){
	form1.address.value=form1.loc.value+b.value;
	form1.action = "/web/petowner";
	form1.encoding = "multipart/form-data";
	form1.method = "post";
	form1.submit();
}
function cut(petowner_id){
	form1.action = "/web/petowner/"+petowner_id;
	form1.method = "get";
	form1.submit();
}
function out(nick){
	form1.action = "/web/member/"+nick;
	form1.method = "post";
	form1.submit();
}
</script>
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}

.w3-sidenav a, .w3-sidenav h4 {
	font-weight: bold
}
</style>
</head>
<body class="w3-light-grey w3-content" style="max-width: 1600px;">

	<!-- 프로필영역/menu -->
	<nav class="w3-sidenav w3-collapse w3-white w3-animate-left"
		style="z-index:3;width:300px;">
	<br>
	<div class="w3-container">
		<a href="#" onclick="w3_close()"
			class="w3-hide-large w3-right w3-jumbo w3-padding" title="close menu">
			<i class="fa fa-remove"></i>
		</a>
		<!-- 프로필 사진 이미지 크기 조정! -->
		<img src="/data/me.jpg"
			style="width: 200px; margin-top: 30px; margin-left: 30px;"
			class="w3-round"><br>
		<br>
		<h4 class="w3-padding-0"
			style="font-size: x-large; margin-left: 90px;">
			<b><p></p>
				<%
					if (member != null) {
						out.print(member.getId());
					} else {
						out.print("아이디");
					}
				%></b>
		</h4>

	</div>


	<!-- Pet Regist 모달 영역 끝 -->
	<button class="w3-btn w3-white btn btn-info btn-lg" data-toggle="modal"
		data-target="#my_pet" style="margin-top: 15px; margin-left: 60px;">
		<i class="fa fa-diamond w3-margin-right"></i>Pet Regist
	</button>


	<!-- 프로필밑에 각종 페이스북, 애플, 안드로이드 등등 아이폰들 -->
	<div class="w3-section w3-padding-top w3-large">
		<a href="#"
			class="w3-hover-white w3-hover-text-indigo w3-show-inline-block"><i
			class="fa fa-facebook-official"></i></a> <a href="#"
			class="w3-hover-white w3-hover-text-indigo w3-show-inline-block"><i
			class="fa fa fa-apple"></i></a> <a href="#"
			class="w3-hover-white w3-hover-text-indigo w3-show-inline-block"><i
			class="fa fa fa-android"></i></a> <a href="#"
			class="w3-hover-white w3-hover-text-indigo w3-show-inline-block"><i
			class="fa fa fa fa-angellist "></i></a>
	</div>
	</nav>

	<!-- Overlay effect when opening sidenav on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 290px; margin-top: -25px;">

		<!-- Header -->
		<header class="w3-container"> <a href="#"></a> <span
			class="w3-opennav w3-hide-large w3-xxlarge w3-hover-text-grey"
			onclick="w3_open()"><i class="fa fa-bars"></i></span>
		<h1>
			<b><img src="/data/petphoto_1.png"
				style="width: 900px; height: 370px;"></b>
		</h1>
		<div class="w3-section">
			<!--  
      <span class="w3-margin-right">Filter:</span> 
      <button class="w3-btn">ALL</button>
      <button class="w3-btn w3-white"><i class="fa fa-diamond w3-margin-right"></i>Design</button>
      <button class="w3-btn w3-white w3-hide-small"><i class="fa fa-photo w3-margin-right"></i>Photos</button>
      <button class="w3-btn w3-white w3-hide-small"><i class="fa fa-map-pin w3-margin-right"></i>Art</button> -->
		</div>
		</header>

		<!-- First Photo Grid-->
		<%
			int num = 0;
		%>
		<%
			for (int i = 0; i < ((list.size()) / 3) + 1; i++) {
		%>
		<div class="w3-row-padding">
			<%
				for (int a = 0; a < 3; a++) {
			%>
			<%
				if (num >= list.size()) {
							break;
						}
			%>
			<%
				PetOwner dto = (PetOwner) list.get(num);
			%>
			<%
				num++;
			%>
			<div class="w3-third w3-container w3-margin-bottom">
				<img
					src="http://localhost:9090/save_photo/petowner/<%=dto.getPetOwner_id() + "."
							+ dto.getPhoto().substring(dto.getPhoto().lastIndexOf(".") + 1, dto.getPhoto().length())%>"
					alt="Norway" style="width: 100%" class="w3-hover-opacity">
				<div class="w3-container w3-white">
					<p></p>
					<p style="border: 1px;">
						<b><%=dto.getName()%></b>
					</p>
					<p>
						품종 :
						<%=dto.getWhatKind()%></p>
					<p>
						성별 :
						<%=dto.getSex()%></p>
					<p>
						중성화 :<%=dto.getIsOperation()%></p>
					<p>
						예방접종 :<%=dto.getIsRegularCheck()%></p>
					<p>
						등록번호 :<%=dto.getRegistNumber()%></p>
					<p>
						위도 :<%=dto.getLocx()%></p>
					<p>
						경도 :<%=dto.getLocy()%></p>
					<p>
						주소 :<%=dto.getAddress()%></p>
					<%
						if ((member != null) && (member.getNick().equals(dto.getMember_id()))) {
					%>
					<button onClick="update(<%=dto.getMember_id()%>)">고치기</button>
					<button onClick="cut(<%=dto.getPetOwner_id()%>)">지우기</button>
					<%
						}
					%>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<%
			}
		%>

		<!-- End page content -->
	</div>
	<!-- Pet Regist 모달 영역 -->
	<div class="container">
		<!-- Modal -->

		<div class="modal fade" id="my_pet" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header"
						style="padding: 35px 50px; background-image: url('/data/photo_8.jpg');">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 style="text-align: center; font-size: 30pt;">
							<span class="fa fa-github-square"></span> Pet Regist
						</h4>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<form name="form1">
							<div class="form-group">
								<label for="usrname"><span class="fa fa-linux"></span>
									PetName</label> <input type="text" class="form-control" name="name"
									id="usrname" placeholder="이름을 입력하세요">
							</div>
							<div class="form-group">
								<label for="usrname"><span class="fa fa-gittip"></span>
									Kind</label> <input type="text" class="form-control" name="whatKind"
									id="usrname" placeholder="품종을 입력하세요">
							</div>
							<div class="form-group">
								<label for="usrname"><span
									class="glyphicon glyphicon-user"></span> petnumber</label> <input
									type="text" class="form-control" id="usrname"
									name="registNumber" placeholder="동물번호입력하세요">
							</div>
							<div class="form-group">
								<label for="usrname"><span class="fa fa-ambulance"></span>
									중성화</label> <select name="isOperation">
									<option value="yes" selected="selected">유</option>
									<option value="no">무</option>
								</select name="isRegularCheck"> <label for="usrname"><span class="fa fa-ambulance"></span>
									예방접종</label> <select name="isRegularCheck">
									<option value="yes" selected="selected">유</option>
									<option value="no">무</option>
								</select> <label for="usrname"><span class="fa fa-ambulance"></span>
									성별</label> <select name="sex">
									<option value="boy" selected="selected">수</option>
									<option value="girl">암</option>
								</select>
							</div>
							<div class="form-group">
								<label for="usrname"><span class="fa fa-wechat"></span>
									주소</label> <input type="text" id="sample6_postcode" placeholder="우편번호">
								<input type="button" onclick="sample6_execDaumPostcode()"
									value="우편번호 찾기"><br> <input type="text" name="loc"
									id="sample6_address" placeholder="주소"> <input
									type="text" id="sample6_address2" placeholder="상세주소"> <input
									type="text" id="sample6_address3" name="address"
									placeholder="진짜주소"> <input type="text"
									id="sample6_address4" name="locx" placeholder="위도"> <input
									type="text" id="sample6_address5" name="locy" placeholder="경도">
								<%
									if (member != null) {
								%>
								<input type="hidden" name="email" value="<%=member.getId()%>">
								<input type="hidden" name="member_id"
									value="<%=member.getNick()%>">
								<%
									}
								%>
							</div>
							<fieldset class="form-group">
								<label for="exampleInputFile">Pet Photo</label> <input
									type="file" class="form-control-file" name="myFile"
									accept="image/*" id="imgfile" onchange="readURL(this)">
								<img src="/data/photo_1.png" width="100px" id="preview"
									alt="your image"> <small class="text-muted">반려동물의
									사진을 지정해주세요!</small>
							</fieldset>
							<button onClick="regist()" class="btn btn-success btn-block">
								<span class="glyphicon glyphicon-off"></span>등록
							</button>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>


</body>
</html>

