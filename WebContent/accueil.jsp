<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Uber</title>
<link rel="icon" type="image/svg+xml" sizes="298x600"
	href="assets/img/iphone.svg">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
<link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
<link rel="stylesheet" href="assets/fonts/ionicons.min.css">
<link rel="stylesheet" href="assets/css/Header-Blue.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Search.css">
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<link rel="stylesheet"
	href="assets/css/Registration-Form-with-Photo.css">
<link rel="stylesheet" href="assets/css/styles.css">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#map_canvas {
	height: 100%;
	width: 100%;
}
</style>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">
	var previousPosition = null;

	function initialize() {
		map = new google.maps.Map(document.getElementById("map_canvas"), {
			zoom : 19,
			center : new google.maps.LatLng(-4.441931, 15.266293),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		});
	}

	if (navigator.geolocation)
		var watchId = navigator.geolocation.watchPosition(successCallback,
				null, {
					enableHighAccuracy : true
				});
	else
		alert("Votre navigateur ne prend pas en compte la géolocalisation HTML5");

	function successCallback(position) {
		map.panTo(new google.maps.LatLng(position.coords.latitude,
				position.coords.longitude));
		var marker = new google.maps.Marker({
			position : new google.maps.LatLng(position.coords.latitude,
					position.coords.longitude),
			map : map
		});
		if (previousPosition) {
			var newLineCoordinates = [
					new google.maps.LatLng(previousPosition.coords.latitude,
							previousPosition.coords.longitude),
					new google.maps.LatLng(position.coords.latitude,
							position.coords.longitude) ];

			var newLine = new google.maps.Polyline({
				path : newLineCoordinates,
				strokeColor : "#FF0000",
				strokeOpacity : 1.0,
				strokeWeight : 2
			});
			newLine.setMap(map);
		}
		previousPosition = position;
	};
</script>
</head>

<body onload="initialize()">
<div id="map_canvas"></div>
	<nav
		class="navbar navbar-light navbar-expand-md navigation-clean-search">
		<div class="container">
			<a class="navbar-brand" href="#">Uber Kin</a>
			<button data-toggle="collapse" class="navbar-toggler"
				data-target="#navcol-1">
				<span class="sr-only">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="nav navbar-nav">
					<li class="nav-item" role="presentation"><a
						class="nav-link active" href="#">Location</a></li>
					<li class="nav-item" role="presentation"></li>
					<li class="nav-item" role="presentation"><a class="nav-link"
						href="#">Course</a></li>
					<li class="nav-item dropdown"><a
						class="dropdown-toggle nav-link" data-toggle="dropdown"
						aria-expanded="false" href="#">Mes Adresse</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" role="presentation" href="#">Mettre
								Ã  jour mon adresse</a><a class="dropdown-item" role="presentation"
								href="#">Mon dernier adresse</a><a class="dropdown-item"
								role="presentation" href="#">Mes Adresse</a>
						</div></li>
				</ul>
				<form class="form-inline mr-auto" target="_self">
					<div class="form-group">
						<label for="search-field"><i class="fa fa-search"></i></label><input
							class="form-control search-field" type="search" id="search-field"
							name="search">
					</div>
				</form>
				<a class="btn btn-light action-button" role="button" href="#">Mon
					Compte</a>
			</div>
		</div>
	</nav>
	<div class="card-group">
		
			<!-- Inclusion de l'API Google MAPS -->
			<!-- Le paramètre "sensor" indique si cette application utilise
détecteur pour déterminer la position de l'utilisateur -->
			
				
			
		
		<div class="card">
			<img class="card-img-top w-100 d-block">
			<div class="card-body">
				<h4 class="card-title">Entrez votre destination</h4>
				<form action="Commande" method="post">
					<div class="form-group">
						<input class="form-control" type="text" name="commune"
							placeholder="Commune" required="required">
					</div>
					<div class="form-group">
						<input class="form-control" type="text" name="quartier"
							placeholder="Quartier" required="required">
					</div>
					<div class="form-group">
						<input class="form-control" type="text" name="avenue"
							placeholder="Avenue" required="required">
					</div>
					<div class="form-group">
						<input class="form-control" type="number" name="numero" min="0"
							placeholder="Numero" required="required">
					</div>
					<button class="btn btn-primary" type="submit">Commendez un
						taxi</button>
			</div>
			</form>

		</div>
	</div>
	<div></div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>