<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="s" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Livros</title>
</head>
<body>
	
	<s:hasBindErrors name="product">
	<ul>
	 <c:forEach  var="error" items="${errors.allErrors}">
	 	<li>${error.code}-${error.field}</li>
	 </c:forEach>
	</ul>
	</s:hasBindErrors>
	
	<s:url value="/produtos/salvar" var="save"/>
	<form:form method="post" action="${save}" modelAttribute="product" >
		<div>
			<label for="title">Title</label>
			<form:input path="title"/>
			<form:errors path="title"/>
		</div>
		
		<div>
			<label for="description">Description</label><br/>
			<form:textarea rows="10" cols="20" path="description"/>
			<form:errors path="description"/>
		</div>
		
		<div>
			<label for="pageNumber">Pages Number</label>
			<form:input path="pageNumber"/>
			<form:errors path="pageNumber"/>
		</div>
		
		<div>
			<label for="releaseDate">Release Date</label>
			<form:input  type="date" path="releaseDate"/>
			<form:errors path="releaseDate"/>
		</div>
		
		<c:forEach items="${bookTypes}" var="bookType" varStatus="status">
		<div>
			<label for="price_${bookType}">${bookType}</label>
			<form:input path="prices[${status.index}].value" id="price_${bookType}"/>
			<form:input type="hidden" path="prices[${status.index}].bookType" value="${bookType}"/> 
		</div>
		</c:forEach>
		
		<div>
			<input type="submit" value="save"/>
		</div>
	</form:form>
	
</body>
</html>