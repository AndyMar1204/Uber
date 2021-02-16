<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Uber</title>
    <link rel="icon" type="image/svg+xml" sizes="298x600" href="assets/img/iphone.svg">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/Header-Blue.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="assets/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <nav class="navbar navbar-light navbar-expand-md navigation-clean">
                        <div class="container"><a class="navbar-brand" href="#">Uber Admin</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                            <div class="collapse navbar-collapse"
                                id="navcol-1">
                                <ul class="nav navbar-nav ml-auto">
                                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Accueil</a></li>
                                    <li class="nav-item" role="presentation"><a class="nav-link" href="#">Commander un taxi</a></li>
                                    <li class="nav-item" role="presentation"><a class="nav-link" href="#">Mon Compte</a></li>
                                    <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">Option</a>
                                        <div class="dropdown-menu" role="menu"><a class="dropdown-item" role="presentation" href="#">Sauvegarder les donnÃ©es</a><a class="dropdown-item" role="presentation" href="#">Restaurer les donnÃ©es</a><a class="dropdown-item" role="presentation" href="#">Me deconnecter</a></div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 offset-md-0">
                    <div>
                        <ul class="nav nav-tabs">
                            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-1">Utilisateurs</a></li>
                            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-2">Adresses</a></li>
                            <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#tab-3">Courses</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane" role="tabpanel" id="tab-1">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Id utilisateur</th>
                                                <th>Nom d'utilisateur</th>
                                                <th>Telephone</th>
                                                <th>Mot de passe</th>
                                                <th>Email</th>
                                                <th>Adresse</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="users" value="${list_user}" scope="request" />
                                        <c:set var="u_manage" value="${u_manager}" scope="request"></c:set>
                                        <c:forEach items ="${users }" var="u">
                                        	<c:set var="x" value="${ x + 1 }" />
                                        	<tr>
                                                <td><c:out value="${x }" /></td>
                                                <td><c:out value="${u.username }" /></td>
                                                <td><c:out value="${u.telephone }" /></td>
                                                <td><c:out value="${u.password }" /></td>
                                                <td><c:out value="${u.email }" /></td>
                                                <td><c:out value="${u.adresse.details() }" /></td>
                                                <td><select><optgroup label="Action"><option value="12" selected=""> <button onclick=""> Supprimer</button> </option><option value="13">Mettre Ã  jour</option><option value="14">Contacter</option></optgroup></select></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <p>Tous les utilisateur</p>
                            </div>
                            <div class="tab-pane" role="tabpanel" id="tab-2">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Id Adresse</th>
                                                <th>Commune</th>
                                                <th>Quartier</th>
                                                <th>Avenue</th>
                                                <th>Numero</th>
                                                <th>Position GEO</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="adresse" value="${list_adresse}" scope="request" />
                                        <c:forEach items ="${adresse }" var="ad">
                                        	<c:set var="y" value="${ y + 1 }" />
                                        	 <tr>
                                                <td><c:out value="${ y }" /></td>
                                                <td><c:out value="${ ad.getCommune() }" /></td>
                                                <td><c:out value="${ ad.getQuartier()}" /></td>
                                                <td><c:out value="${ ad.getAvenue() }" /></td>
                                                <td><c:out value="${ ad.numero }" /></td>
                                                <td><c:out value="${ad.position }" /></td>
                                                <td><select><optgroup label="Action"><option value="12" selected="">Supprimer</option><option value="13">Mettre Ã  jour</option></optgroup></select></td>
                                            </tr>
                                        </c:forEach>
                                           
                                            <tr></tr>
                                        </tbody>
                                    </table>
                                </div>
                                <p>Les adresses</p>
                            </div>
                            <div class="tab-pane active" role="tabpanel" id="tab-3">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Id course</th>
                                                <th>Nom d'utilisateur</th>
                                                <th>Destination</th>
                                                <th>prix</th>
                                                <th>Etat</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:set var="course" value="${list_course }" scope="request"></c:set>
                                        	<c:forEach items ="${course }" var="cou">
                                        	<c:set var="z" value="${ z + 1 }" />
                                        	<tr>
                                                <td><c:out value="${ z }"/></td>
                                                <td><c:out value="${ cou.user.username }" /></td>
                                                <td><c:out value="${ cou.adresse_destination.details()}" /></td>
                                                <td><c:out value="${ cou.prix}" /></td>
                                                <td><c:out value="${ cou.etat}" /></td>
                                                <td><select><optgroup label="Action"><option value="12" selected="">Supprimer</option><option value="13">Mettre Ã  jour</option><option value="14">Contacter</option></optgroup></select></td>
                                            </tr>
                                        	</c:forEach>
                                           
                                        </tbody>
                                    </table>
                                </div>
                                <p>Les courses</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>