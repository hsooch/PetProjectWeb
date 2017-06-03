<%@page import="com.pet.member.model.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	Member member = (Member) session.getAttribute("log");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>다녀오개 홈페이지입니다</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lobster">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Lato", sans-serif;
}

body, html {
	height: 100%;
	color: #777;
	line-height: 1.8;
}

/* Create a Parallax Effect */
.bgimg-1, .bgimg-2, .bgimg-3 {
	opacity: 0.7;
	background-attachment: fixed;
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}

/* First image (Logo. Full height) */
.bgimg-1 {
	background-image: url('/data/Original_pet.jpg');
	min-height: 100%;
}

/* Second image */
.bgimg-2 {
	background-image: url("/data/pet_2.jpg");
	min-height: 400px;
}

/* Third image (Contact) */
.bgimg-3 {
	background-image: url("/data/pet_1.jpg");
	min-height: 400px;
}

/* Adjust the position of the parallax image text */
.w3-display-middle {
	bottom: 45%;
}

.w3-wide {
	letter-spacing: 10px;
}

.w3-hover-opacity {
	cursor: pointer;
}

#googleMap {
	width: 100%;
	height: 400px;
	-webkit-filter: grayscale(90%);
	filter: grayscale(90%);
}

.w3-myfont {
	font-family: "Comic Sans MS", cursive, sans-serif;
	font-size: 15pt
}

.w3-lobster {
	font-family: "Lobster", serif;
}

.modal-header, h4, .close {
	color: black; ! important;
	text-align: center;
	font-size: 30px;
}

.modal-footer {
	background-color: #f9f9f9;
}

/* 회사 피플 사진*/
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 70%;
	margin: auto;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
}
</style>
<script>
	function login() {
		form1.action = "/web/member";
		form1.method = "post";

	}
</script>
<script>
	$(document)
			.ready(
					function() {
						/*카카오톡 로그인 Key*/
						Kakao.init("e06a5a61a9835e996831d55ea974ac34");

						function getKakaotalkUserProfile() {
							Kakao.API
									.request({
										url : '/v1/user/me',
										success : function(res) {
											$("#kakao-profile").append(
													res.properties.nickname);
											$("#kakao-profile")
													.append(
															$(
																	"<img/>",
																	{
																		"src" : res.properties.profile_image,
																		"alt" : res.properties.nickname
																				+ "님의 프로필 사진"
																	}));
										},
										fail : function(error) {
											console.log(error);
										}
									});
						}

						/*카카오톡 로그인*/
						function createKakaotalkLogin() {
							$(
									"#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn")
									.remove();
							var loginBtn = $("<a/>", {
								"class" : "kakao-login-btn",
								"text" : "로그인"
							});
							loginBtn.click(function() {
								Kakao.Auth.login({
									persistAccessToken : true,
									persistRefreshToken : true,
									success : function(authObj) {
										getKakaotalkUserProfile();
										createKakaotalkLogout();
									},
									fail : function(err) {
										console.log(err);
									}
								});
							});
							$("#kakao-logged-group").prepend(loginBtn)

						}

						/*카카오톡 로그아웃*/
						function createKakaotalkLogout() {
							$(
									"#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn")
									.remove();
							var logoutBtn = $("<a/>", {
								"class" : "kakao-logout-btn",
								"text" : "로그아웃"
							});
							logoutBtn.click(function() {
								Kakao.Auth.logout();
								createKakaotalkLogin();
								$("#kakao-profile").text("");
							});
							$("#kakao-logged-group").prepend(logoutBtn);
						}
						if (Kakao.Auth.getRefreshToken() != undefined
								&& Kakao.Auth.getRefreshToken().replace(/ /gi,
										"") != "") {
							createKakaotalkLogout();
							getKakaotalkUserProfile();
						} else {
							createKakaotalkLogin();
						}
					});
</script>

</head>
<body>
	<!-- 첫 Main Title -->
	<div class="bgimg-1 w3-opacity w3-display-container"
		style="padding: 300px;"></div>

	<!-- Company 영역 -->
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="my_company" role="dialog"
			style="background-image: url('/images/company_background_2.jpg');">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header"
						style="padding: 35px 50px; background-image: url('/data/photo_8.jpg');">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>
							<span class="glyphicon glyphicon-lock"></span>Company People
						</h4>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">


						<!-- Company People 다음으로 넘기는 화면-->
						<div class="container" style="width: 500px;">
							<br>
							<div id="myCarousel" class="carousel slide" data-ride="carousel">
								<!-- Indicators -->
								<ol class="carousel-indicators">
									<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
									<li data-target="#myCarousel" data-slide-to="1"></li>
									<li data-target="#myCarousel" data-slide-to="2"></li>
									<li data-target="#myCarousel" data-slide-to="3"></li>
								</ol>

								<!-- Wrapper for slides -->
								<div class="carousel-inner" role="listbox"
									style="width: 500px; height: 400px;">

									<div class="item active"
										style="width: 500px; height: 450px; overflow: hidden; margin-right: 85px;">
										<img src="/data/people_1.png" alt="Chania" width="500px"
											height="300px" style="margin-right: 90px; margin-top: 15px;">
										<div class="carousel-caption">
											<h3>회장 권형섭</h3>
										</div>
									</div>

									<div class="item"
										style="width: 500px; height: 450px; overflow: hidden; margin-right: 85px;">
										<img src="/data/people_2.png" alt="Chania" width="500px"
											height="300px" style="margin-right: 90px; margin-top: 15px;">
										<div class="carousel-caption">
											<h3>이사 황수찬</h3>
										</div>
									</div>

									<div class="item"
										style="width: 500px; height: 450px; overflow: hidden; margin-right: 85px;">
										<img src="/data/people_3.png" alt="Chania" width="500px"
											height="300px" style="margin-right: 90px; margin-top: 15px;">
										<div class="carousel-caption">
											<h3>부장 성웅식</h3>
										</div>
									</div>

									<div class="item"
										style="width: 500px; height: 450px; overflow: hidden; margin-right: 85px;">
										<img src="/data/people_4.png" alt="Chania" width="500px"
											height="300px" style="margin-right: 90px; margin-top: 15px;">
										<div class="carousel-caption">
											<h3>과장 지병훈</h3>
										</div>
									</div>

								</div>

								<!-- Left and right controls -->
								<a class="left carousel-control" href="#myCarousel"
									role="button" data-slide="prev"> <span
									class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
									<span class="sr-only">Previous</span>
								</a> <a class="right carousel-control" href="#myCarousel"
									role="button" data-slide="next"> <span
									class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>
						</div>


						<!-- Company People end-->

					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-danger btn-default pull-left"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span> Cancel
						</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- Company 영역 끝 -->

	<!-- 회원가입 폼 Modal 영역 -->
	<div class="container">
		<ul class="nav nav-tabs" role="tablist">

			<!-- 회원가입 폼 Modal 영역 -->
			<!-- <div class="container">
  Modal
  <div class="modal fade" id="my_join" role="dialog" style="background-image: url('/data/photo_4.jpg');">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content" style="background-size : cover;">
        <div class="modal-header" style="background-image: url('/data/photo_8.jpg'); height: 70px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <p></p>
          <h4 class="modal-title fa fa-user-plus" style="font-weight: bold; font-size: 20pt; text-align: center;">회원가입</h4>
        </div>
        <div class="modal-body">
		회원가입 양식 폼
		  <form name="form1">
		    <div class="form-group">
		      <label for="id">ID :</label>
		      <input type="id" name ="id" class="form-control" placeholder="아이디를 입력하세요" maxlength="16">
		    	<input type="button" onClick="idCheck()" value="유효성">
		    </div>
		    <div class="form-group">
		      <label for="pwd">Password :</label>
		      <input type="password" name="pwd" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요" maxlength="16">
		    </div>
		    <div class="form-group">
		      <label for="name">Name :</label>
		      <input type="name" naem="name" class="form-control" id="name" placeholder="이름을 입력하세요">
		    </div>
            <div class="form-group">
                <label for="inputPhoneNumber" class="col-lg-2 control-label">성별</label>
                <div class="col-lg-10">
                    <select class="form-control" id="gender" name="sex">
                        <option value="남자">남</option>
                        <option value="여자">여</option>
                    </select>
                </div>
            </div>		    
		    <div class="form-group">
				<label for="location">Location :</label>
				<input type="text" id="sample6_postcode" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" name="loc" id="sample6_address" placeholder="주소">
				<input type="text" name="loc2" id="sample6_address2" placeholder="상세주소">
		    </div>
		    <div class="form-group">
		      <label for="phone">Phone :</label>
		      <input type="phone" class="form-control" name="phone" id="phone" placeholder="핸드폰번호를 입력하세요">
		    </div>
			  <div class="form-group">
			    <label for="exampleInputEmail2">Email</label>
			    <input type="email" class="form-control" name="email" placeholder="jane.doe@example.com">
			  </div>
		  <fieldset class="form-group">
		    <label for="exampleInputFile">People Photo</label>
		    <input type="file" class="form-control-file" name="myfile" accept="image/*" id="imgfile" onchange="readURL(this)">
		    <img  src="/data/photo_1.png" width="100px" id="preview" alt="your image">
		    <small class="text-muted">고객님의 프로필 사진을 지정해주세요!</small>
		  </fieldset>
		  </form>		        			    		    		    		    		     		     
        양식 폼 끝  
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default btn btn-info btn-lg" data-dismiss="modal" onClick="regist()">OK</button>
          <button type="button" class="btn btn-default btn btn-info btn-lg" data-dismiss="modal">Close</button>
        </div>
        
      </div>      
    </div>
  </div>
</div>   -->
			<!-- 여기까지 회원가입폼 완성! -->

			<!-- 로그인 폼 시작 -->
			<div class="container">
				<!-- Modal -->
				<div class="modal fade" id="my_login" role="dialog"
					style="background-image: url('/images/company_background_2.jpg');">
					<div class="modal-dialog">

						<!-- Modal content-->
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header"
								style="padding: 35px 50px; background-image: url('/data/photo_8.jpg');">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>
									<span class="glyphicon glyphicon-lock"></span> Login
								</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
								<form name="form1">
									<div class="form-group">
										<label for="usrname"><span
											class="glyphicon glyphicon-user"></span> Username</label> <input
											type="text" class="form-control" id="usrname"
											placeholder="Enter email" name="id">
									</div>
									<div class="form-group">
										<label for="psw"><span
											class="glyphicon glyphicon-eye-open"></span> Password</label> <input
											type="password" class="form-control" id="pwd"
											placeholder="비밀번호를 입력하세요" maxlength="16" name="nick">
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value="" checked>Remember
											me</label>
									</div>
									<button id="loggg" onClick="login()">로그인</button>
								</form>

								<!-- 카카오톡 버튼 -->
								<img
									src="http://mud-kage.kakao.co.kr/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg"
									width="300" id="kakao-logged-group" /> </a>

							</div>
							<div class="modal-footer">
								<button type="submit"
									class="btn btn-danger btn-default pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> Cancel
								</button>
								<p>
									Not a member? <a href="#" data-toggle="modal"
										data-target="#my_join">Sign Up</a>
								</p>
								<p>
									Forgot <a href="#">Password?</a>
								</p>
							</div>
						</div>

					</div>
				</div>
			</div>
			<!-- 로그인 폼 완료 -->




			<!-- Pet 게시판 이동영역 끝 -->

			<li class="active"><a href="#" class="btn btn-info btn-lg">Home</a></li>
			<li><a href="#" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#my_company">Company People</a></li>
			<%
				if (member == null) {
			%>
			<li><a href="#" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#my_login">Login</a></li>
			<%
				}
			%>
			<li><a href="http://localhost:9090/web/petowner"
				class="btn btn-info btn-lg">Pet</a></li>

			<!--  <li><a href="list.jsp" class="btn btn-info btn-lg">Community</a></li>-->

		</ul>
	</div>


	<!-- Container (About Section) -->
	<div class="w3-content w3-container w3-padding-64" id="about">
		<h3 class="w3-center" style="font-weight: bold; font-size: 30pt">
			<font color="gray">Company Story
		</h3>
		<div class="w3-col m6 w3-center w3-section">
			<p>
				<b> </b>
			</p>
			<br> <img src="/data/king.jpg" class="w3-circle"
				alt="Photo of Me" style="width: 450px; height: 400px;">
		</div>

		<!-- Hide this text on small devices -->
		<div class="w3-col m6 w3-hide-small w3-section">
			<br> <br>
			<p
				style="font-size: 20pt; font-style: oblique; font-family: sans-serif; font-weight: bold; font-style: normal;">Welcome
				to 다녀오개</p>
			<p>사랑하는 반려동물에게 믿을 수 있는 임시보호처를 제공하는</p>
			<p>가장 믿을 수 있는 임시보호 전문회사입니다.</p>
			<p>반려동물의 편의와 행복을 위한다는 사명으로</p>
			<p>끊임없이 믿을 수 있는 회사로 발전하고 있습니다.</p>
			<p>가족같은 반려동물의 건강하게 보호할 수 있도록</p>
			<p>믿을 수 있는 회사가 되겠습니다.</p>
			<p>감사합니다.</p>
		</div>
	</div>
	</div>

	<!-- Second Parallax Image with Portfolio Text -->
	<div class="bgimg-2 w3-display-container">
		<div class="w3-display-middle">
			<span class="w3-xxlarge w3-text-light-grey w3-wide"
				style="font-weight: bold; font-size: 30pt">CAT</span>
		</div>
	</div>

	<!-- Container (Portfolio Section) -->
	<div class="w3-content w3-container w3-padding-64">
		<h3 class="w3-center" style="font-weight: bold; font-size: 30pt">Best
			Photo</h3>
		<p class="w3-center">
			<em><br></em>
		</p>
		<br>

		<!-- Responsive Grid. Four columns on tablets, laptops and desktops. Will stack on mobile devices/small screens (100% width) -->
		<div class="w3-row-padding w3-center">
			<div class="w3-col m3">
				<img src="/data/photo_1.png" style="width: 150px" height="100px"
					onclick="onClick(this)" class="w3-hover-opacity">
			</div>

			<div class="w3-col m3">
				<img src="/data/photo_2.png" style="width: 150px" height="100px"
					onclick="onClick(this)" class="w3-hover-opacity">
			</div>

			<div class="w3-col m3">
				<img src="/data/photo_3.jpg" style="width: 150px" height="100px"
					onclick="onClick(this)" class="w3-hover-opacity">
			</div>

			<div class="w3-col m3">
				<img src="/data/photo_4.jpg" style="width: 150px" height="100px"
					onclick="onClick(this)" class="w3-hover-opacity">
			</div>
		</div>

		<div class="w3-row-padding w3-center w3-section">
			<div class="w3-col m3">
				<img src="/data/photo_5.jpg" style="width: 150px" height="100px"
					onclick="onClick(this)" class="w3-hover-opacity">
			</div>

			<div class="w3-col m3">
				<img src="/data/photo_6.jpg" style="width: 150px" height="100px"
					onclick="onClick(this)" class="w3-hover-opacity">
			</div>

			<div class="w3-col m3">
				<img src="/data/photo_7.jpg" style="width: 150px" height="100px"
					onclick="onClick(this)" class="w3-hover-opacity">
			</div>

			<div class="w3-col m3">
				<img src="/data/photo_8.jpg" style="width: 150px" height="100px"
					onclick="onClick(this)" class="w3-hover-opacity">
			</div>
		</div>
	</div>

	<!-- Modal for full size images on click-->
	<div id="modal01" class="w3-modal w3-black"
		onclick="this.style.display='none'">
		<span
			class="w3-closebtn w3-hover-red w3-text-white w3-xxxlarge w3-container w3-display-topright">×</span>
		<div
			class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
			<img id="img01" style="max-width: 100%">
		</div>
	</div>

	<!-- Third Parallax Image with Portfolio Text -->
	<div class="bgimg-3 w3-display-container">
		<div class="w3-display-middle">
			<span class="w3-xxlarge w3-text-light-grey w3-wide"
				style="font-weight: bold; font-size: 30pt">FRIEND</span>
		</div>
	</div>

	<!-- Container (Contact Section) -->
	<div class="w3-content w3-container w3-padding-64">
		<h3 class="w3-center">찾아오시는 길</h3>
		<p class="w3-center">
			<em></em>
		</p>

		<div class="w3-row w3-padding-32 w3-section">
			<div class="w3-col m4 w3-container">
				<!-- Add Google Maps -->
				<div id="googleMap" class="w3-round-large"></div>
			</div>
			<div class="w3-col m8 w3-container w3-section">
				<div class="w3-large w3-margin-bottom">
					<i class="fa fa-map-marker w3-hover-text-black" style="width: 30px"></i>경기도
					성남시 분당구 판교동 32-4<br> <i
						class="fa fa-phone w3-hover-text-black" style="width: 30px"></i>
					Phone: 010-2716-7460<br> <i
						class="fa fa-envelope w3-hover-text-black" style="width: 30px">
					</i> Email : byounghoonji@naver.com<br> <img src="/data/talk.png"
						style="width: 30px" height="30px"> </i> Kakao ID : jibyounghoon<br>
				</div>
				<p></p>
			</div>
		</div>

		<!-- Add Google Maps -->
		<script src="http://maps.googleapis.com/maps/api/js"></script>
		<!-- 다음 우편번호 API -->
		<script
			src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
		<script>
			var myCenter = new google.maps.LatLng(37.394791, 127.113370);

			//구글맵 설정 영역!
			//시카고 41.878114, -87.629798
			//분당구 판교동 37.394791, 127.113370 

			//구글맵 영역!
			function initialize() {
				var mapProp = {
					center : myCenter,
					zoom : 17,
					scrollwheel : false,
					draggable : false,
					mapTypeId : google.maps.MapTypeId.ROADMAP
				};

				var map = new google.maps.Map(document
						.getElementById("googleMap"), mapProp);

				var marker = new google.maps.Marker({
					position : myCenter,
				});

				marker.setMap(map);
			}

			google.maps.event.addDomListener(window, 'load', initialize);

			// Modal Image Gallery
			function onClick(element) {
				document.getElementById("img01").src = element.src;
				document.getElementById("modal01").style.display = "block";
			}

			// Change style of navbar on scroll
			window.onscroll = function() {
				myFunction()
			};
			function myFunction() {
				var navbar = document.getElementById("myNavbar");
				if (document.body.scrollTop > 100
						|| document.documentElement.scrollTop > 100) {
					navbar.className = "w3-navbar" + " w3-card-2"
							+ " w3-animate-top" + " w3-white";
				} else {
					navbar.className = navbar.className.replace(
							" w3-card-2 w3-animate-top w3-white", "");
				}
			}

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
			var add1;
			var add2;
			//우편번호 관한곳
			window
					.addEventListener(
							"load",
							function() {
								document.getElementById('sample6_postcode').readOnly = true;
								add1 = document
										.getElementById('sample6_address').readOnly = true;
								add2 = document
										.getElementById('sample6_address2').readOnly = true;
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
												+ data.buildingName
												: data.buildingName);
									}
									// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
									fullAddr += (extraAddr !== '' ? ' ('
											+ extraAddr + ')' : '');
								}

								// 우편번호와 주소 정보를 해당 필드에 넣는다.
								document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
								document.getElementById('sample6_address').value = fullAddr;
								// 커서를 상세주소 필드로 이동한다.
								document.getElementById('sample6_address2').readOnly = false;
								document.getElementById('sample6_address2')
										.focus();
							}
						}).open();
			}
			//로그인 버튼 누를시 회원가입 할 내용들
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
			//서버에 아이디 요청
			function goIdCheck() {
				var xhttp = getHttpXMLRequest();
				xhttp.open("Post", "/app/member/" + form1.id.value, true);//전송준비상태, 비동기시 트루
				xhttp.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded");
				xhttp.send();//서버전송일어남

				xhttp.onreadystatechange = function() {//콜백 메서드
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						var msg = xhttp.responseText;
						/* var obj = JSON.parse(data);
						var msg = JSON.parse(data).msg; */
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
					form1.action = "/web/regist";
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
		</script>
</body>
</html>

