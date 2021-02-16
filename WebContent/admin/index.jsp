<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>
<c:url value='Admin/admin.jsp' var='admin'></c:url>
	<a href='${admin }'> S'identifier</a>
</body>
</html>