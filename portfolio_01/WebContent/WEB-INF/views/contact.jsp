<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="keywords" content="corporate html template - agency theme - business html template - creative theme - portfolio html template - gallery theme - restaurant theme - ecommerce template - app template - landing page - real estate theme">
        <meta name="description" content="Bootstrap HTML5 template for agency, corporate, business, app and creative portfolio, it is suitable for any kind of websites, like ecommerce, restaurant, photography, gallery and retail website.">
        <meta name="author" content="EncodesLife">
        <link rel="icon" href="img/favicon.png">

        <title>Portfolio - Kim YoungMo</title>

        <!-- Bootstrap core CSS -->
        <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet">

        <!-- Theme skins -->
        <link id="skin" href="css/theme-colors/blue.css" rel="stylesheet">
        <link href="js/style-switcher/css/style-switcher.css" rel="stylesheet">
        <link href="css/line-icons/line-icons.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="js/html5shiv.min.js"></script>
          <script src="js/respond.min.js"></script>
        <![endif]-->

        <!--[if IE 9]>
          <link href="css/ie.css" rel="stylesheet">
        <![endif]-->
    </head>

    <body>
        <!-- START - Top area -->
        <div class="top-container">
            <div class="container">
                <div class="top-column-left">
                    <ul class="contact-line">
                        <li><i class="fa fa-envelope"></i> support@ecada.com</li>
                        <li><i class="fa fa-phone"></i> (0123)-123-456-789</li>
                    </ul>
                </div>
                <div class="top-column-right">
                    <div class="top-social-network">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-google-plus"></i></a>
                        <a href="#"><i class="fa fa-linkedin"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                    </div>
                    <ul class="register">
                        <li class="dropdown language" style="display: block;">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-globe"></i> Languages</a>
                            <ul class="dropdown-menu">
                                <li><a href="#">English <i class="fa fa-check"></i></a></li>
                                <li><a href="#">Spanish</a></li>
                                <li><a href="#">Russian</a></li>
                                <li><a href="#">German</a></li>
                            </ul>
                        </li>
                        <li><a href="page_faq.html">Help</a></li>
                        <li><a href="page_login.html">Login</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- END - Top area -->

        <div class="clearfix"></div>

        <!-- START - Navbar -->
		<nav class="navbar navbar-default navbar-dark megamenu">
		    <div class="container">
		
		        <!-- Brand and toggle get grouped for better mobile display -->
		        <div class="navbar-header">
		            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-menu">
		                <i class="fa fa-bars"></i>
		            </button>
		            <a class="navbar-brand logo" href="<c:url value='/index.do'/>">
		                <img src="<c:url value="/resources/img/themes/mogun_logo_kor5.png" />" alt="Logo" />
		            </a>
		        </div>
		
		        <!-- Collect the nav links, forms, and other content for toggling -->
		        <div class="collapse navbar-collapse" id="navbar-menu">
		            <ul class="nav navbar-nav navbar-right">
		                <li id="home" class="dropdown">
		                    <a href="<c:url value='/index.do'/>" class="dropdown-toggle" data-toggle="dropdown">Home</a>
		                </li>
		                <li class="dropdown">
		                    <a href="javascript:movePage(null, '/info/myInfo.do?typeSeq=2&page=1')">포트폴리오 소개</a>
		                    <ul class="dropdown-menu">
		                        <li>
		                            <a href="javascript:movePage(null, '/info/myInfo.do?typeSeq=2&page=1')">포트폴리오 매뉴얼</a>
		                        </li>
		                    	<li>
		                            <a href="javascript:movePage(null, '/info/myInfo.do?typeSeq=2&page=1')">개발자 프로필</a>
		                        </li>
		                        <li>
		                            <a href="javascript:movePage(null, '/info/note.do?typeSeq=2&page=1')">개발노트</a>
		                        </li>
		                    </ul>
		                </li>
		                
		                <li class="dropdown">
		                    <a href="javascript:movePage(null, '/notice/list.do?typeSeq=1&page=1')">공지사항</a>
		                    
		                </li>
		                <li class="dropdown">
		                    <a href="javascript:movePage(null, '/board/list.do?typeSeq=2&page=1')">자유게시판</a>
		                    
		                </li>
		                <li><a href="javascript:movePage(null, '/info/note.do?')">Contact</a></li>
		                
		                <c:if test='${sessionScope.memberId != null }'>
							<c:if test='${sessionScope.memberSeq == 9 }'>	
				                <li class="dropdown">
				                    <a href="javascript:movePage(null, '/board/rList.do?typeSeq=2&page=1')">관리자페이지</a>
				                    <ul class="dropdown-menu">
				                    	<li>
				                            <a href="javascript:movePage(null, '/info/myInfo.do?typeSeq=2&page=1')">회원관리</a>
				                        </li>
				                        <li>
				                            <a href="javascript:movePage(null, '/info/note.do?typeSeq=2&page=1')">서버현황</a>
				                        </li>
				                    </ul>
				                </li>
				             </c:if>
						</c:if>				             
		                
		            </ul>
		        </div>
		    </div>
		</nav>
		<!-- END - Navbar -->
        
        <div id="contentDiv"></div>

        <div class="clearfix"></div>

        <!-- START - Footer -->
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <div class="footer-description">
                            <img class="footer-logo" src="<c:url value="/resources/img/themes/mogun_logo_kor5.png" />" alt="Footer logo" />
                            <span>Portfolio - Kim YoungMo</span>
                            <div class="widget" id="text-footer">
                                <p>※ 본 포트폴리오는 아래의 항목을 이용하여 제작하였습니다.</p>
                            </div>
                        </div>
                        <div class="footer-details">
                            <ul class="list-unstyled footer-list">
                                <li><i class="fa fa-angle-double-right"></i><a href="#">Eclipse 4.7.3a</a></li>
                                <li><i class="fa fa-angle-double-right"></i><a href="#">myBatis 3.4.1</a></li>
                            </ul>
                            <ul class="list-unstyled footer-list">
                                <li><i class="fa fa-angle-double-right"></i><a href="#">Spring FW 4.3.13</a></li>
                                <li><i class="fa fa-angle-double-right"></i><a href="#">jUnit 4.12</a></li>
                            </ul>
                            <ul class="list-unstyled footer-list">
                                <li><i class="fa fa-angle-double-right"></i><a href="#">MySql 8.0.11</a></li>
                                <li><i class="fa fa-angle-double-right"></i><a href="#">Tomcat 8.5</a></li>
                            </ul>
                            <ul class="list-unstyled footer-list">
                                <li><i class="fa fa-angle-double-right"></i><a href="#">AWS EC2 Instance</a></li>
                                <li><i class="fa fa-angle-double-right"></i><a href="#">Ubuntu 16.04.5</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <h5>모군이네 소식듣기</h5>
                        <p>모군이네 포트폴리오의 지속적인 개발현황 및 업데이트 사항을 전해드립니다.</p>
                        <!-- START - Subscribe -->
                        <div class="input-group margin-bottom-20">
                            <input type="text" class="form-control" placeholder="해당 기능은 준비중입니다...">
                            <span class="input-group-btn">
                                <button class="btn-e btn-e-primary" type="button"><i class="fa fa-send-o"></i></button>
                            </span>
                        </div>
                        <!-- END - Subscribe -->
                    </div>
                </div>
            </div>
            <div class="subfooter">
                <div class="container">
                    <div class="row" style="text-align:center">
                    	<p><a href="#">Kim YoungMo</a> &copy; 2018 - All rights reserved.</p>
                    </div>
                </div>
            </div>
        </footer>
        <!-- END - Footer -->

        <!-- START - to top -->
        <a href="#" class="toTop">
            <i class="fa fa-chevron-up"></i>
        </a>
        <!-- END - to top -->

        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>

        <!-- BOOTSTRAP -->
        <script src="js/bootstrap/js/bootstrap.min.js"></script>

        <!-- jQuery Easing -->
        <script src="js/jquery.easing-1.3.min.js"></script>

        <!-- Parallax -->
        <script src="js/parallax/jquery.parallax-1.1.3.js"></script>
        <script src="js/parallax/setting.js"></script>

        <!-- Maps -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-aTe0C-r6fJ8baO7ze1h33C9D_RWiHls&callback"></script>
        <script src="js/maps/prettymaps.js"></script>
        <script src="js/maps/setting.js"></script>

        <!-- Start Form Plugin -->
        <script src="js/validation/js/jquery.validate.min.js"></script>
        <script src="js/validation/js/setting.js"></script>

        <!-- Custom javaScript for this theme -->
        <script src="js/custom.js"></script>

        <!-- Nicescroll -->
        <script src="js/nicescroll/jquery.nicescroll.min.js"></script>
        <script src="js/nicescroll/settings.js"></script>

        <!-- Theme option-->
        <script src="js/style-switcher/js/style-switcher.js"></script>
    </body>
</html>