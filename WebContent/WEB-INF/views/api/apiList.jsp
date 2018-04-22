<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>API RESULT</title>
</head>
<body>
	<c:forEach var="item" items="${resultList }">
		<ul>
			<li>[${item.id }] ${item.building_name} ${item.floor} , ${item.dong} ${item.bunji} , ${item.cost} , ${item.private_area} , ${item.contract_year}-${item.contract_month} ${item.contract_date} , ${item.region_code} , ${item.building_year} 
		</ul>
	</c:forEach>
</body>
</html>