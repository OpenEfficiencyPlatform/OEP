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
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" >

<meta charset="utf-8">
<title>Green Button Authentication - The Energy Coaltion</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="icon" href="assets/favicon.jpg" type="image/x-icon">

<link rel="stylesheet" type="text/css" href="assets/index.css">
</head>
<body class="page sub green-button-authentication">
  <header class="banner navbar navbar-default navbar-static-top" role="banner">

  <div class="container">
    <div class="navbar-header">

      <a class="navbar-brand" href="http://mybrand.com/">
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
        <p id="breadcrumbs"><span xmlns:v="http://rdf.data-vocabulary.org/#"><span typeof="v:Breadcrumb"><a href="http://mybrand.com/" rel="v:url" property="v:title">Home</a> &gt; <span class="breadcrumb_last">Green Button Authentication</span></span></span></p>      </div>
    </div>
  </div>
</div>


<div class="wrap container" role="document">
	<div class="content row">
		<div class="main col-sm-9" role="main">

			<div class="page-header">
  <h1>Green Button Authentication</h1>
</div>
<div>
<h2 style="color: red">The Customer Code entered does not match any existing portfolio manager. Please enter another Customer Code.</h2>
</div>
<div class="gform_wrapper">

  <form method="POST" action="#[flowVars['CodeNotFound']]" id="login">
    <div class="content">
        <h2>Login</h2>
        <p><label for="userName">User email:</label> <input name="userName" id="userName" class="class=" medium""="" value="" type="text"></p>
        <p><label for="compassKey">Compass Key:</label> <input name="compassKey" id="compassKey" value="" type="text"></p>
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
</div><!-- blue -->
</footer><!-- footer -->


</body>
</html>
