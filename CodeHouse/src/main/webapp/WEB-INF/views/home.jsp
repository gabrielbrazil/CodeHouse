<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h1>OI</h1>
		<c:url value="/produtos/novo" var="novo"/>
		<a href="${novo}">Cadastre um Produto</a>
	</body>
</html>