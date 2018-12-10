<!DOCTYPE html>
<html lang="en">
<script type = "text/javascript">
function sendToMule(){
	userName = document.getElementById("userName").value;
    compassCode = document.getElementById("compassKey").value;
    if (!compassCode) { alert("You must enter a Compass Key to proceed with SCE authorization.");}
    document.forms[0].submit();
};
</script>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">

<meta charset="utf-8">
<title>Green Button Authentication - The Energy Coaltion</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="assets/favicon.jpg" type="image/x-icon">

<!--[if IE 8]>
	<script src="http://energycoalition.org/assets/js/vendor/respond.js"></script>
	<script src="http://energycoalition.org/assets/js/vendor/selectivizr-min.js"></script>
<![endif]-->

<link media="all" href="assets/index.css" type="text/css" rel="stylesheet">
</head>
<body class="page sub green-button-authentication">
  <header class="banner navbar navbar-default navbar-static-top" role="banner">

  <div class="container">
    <div class="navbar-header">

      <a class="navbar-brand" href="http://energycoalition.org/">
        <img src="assets/logo.png">
      </a>
    </div><!-- navbar-header -->

    <nav class="collapse navbar-collapse" role="navigation">
    </nav><!-- nav -->


  </div><!-- container -->

</header><!-- header -->

<div class="seperator full">
  <div class="container">
    <div class="row">
      <div class="col-xs-12">
        <p id="breadcrumbs"><span xmlns:v="http://rdf.data-vocabulary.org/#"><span typeof="v:Breadcrumb"><a href="http://energycoalition.org/" rel="v:url" property="v:title">Home</a> &gt; <span class="breadcrumb_last">Green Button Authentication</span></span></span></p>      </div>
    </div>
  </div>
</div>


<div class="wrap container" role="document">
	<div class="content row">
		<div class="main col-sm-9" role="main">

			<div class="page-header">
  <h1>Green Button Authentication</h1>
  <p style="font-family:Arial">Green Button enables TEN to access your utility data without the back and forth communications and requests between the customer and utility representatives</p>
</div>
<div class="gform_wrapper">

  <form method="POST" action="https://tec.psdconsulting.net/authorize/client" id="login">
    <div class="content">
        <h2>Login</h2>
        <p><label for="userName">User email:</label> <input name="userName" id="userName" class="class=" medium""="" value="" type="text"></p>
        <p><label for="compassKey">Agency Code:</label> <input name="compassKey" id="compassKey" value="" type="text"></p>
        <p class="submit"><input onclick="sendToMule()" value="Sync Data with SCE" class="gform_button button" type="button"></p>
    </div>
  </form>

</div><!-- Form wrapper -->

</div><!-- /.main -->

<aside class="sidebar col-sm-3" role="complementary">
		<div class="testimonials">
				<img src="assets/quoteicon.jpg" alt="Quote" title="Quote">
				<div class="testimonial">
					<p>Connecting people and ideas is essential to generating energy innovation</p>
				</div>
	  </div>
</aside><!-- /.sidebar -->

	</div><!-- /.content -->
</div><!-- /.wrap -->


<footer class="content-info" role="contentinfo">
		<div class="blue">
		<ul id="menu-footer" class="menu"><li class="menu-home"><a href="http://energycoalition.org/">Home</a></li>
    <li class="menu-our-story"><a href="http://energycoalition.org/our-story/">Our Story</a></li>
    <li class="menu-portfolio"><a href="http://energycoalition.org/portfolio/">Portfolio</a></li>
    <li class="menu-energy-architects-in-action"><a href="http://energycoalition.org/energy-architects-in-action/">Energy Architects In Action</a></li>
    <li class="active dropdown menu-contact-us"><a class="dropdown-toggle" href="http://energycoalition.org/contact-us/">Contact Us <b class="caret"></b></a>
    <ul class="dropdown-menu">
    	<li class="menu-rfprfqs"><a href="http://energycoalition.org/work-us/">RFP/RFQs</a></li>
    </ul>
    </li>
    </ul>		<div class="social">
    </div><!-- col -->
	</div><!-- blue -->
</footer><!-- footer -->


</body>
</html>
