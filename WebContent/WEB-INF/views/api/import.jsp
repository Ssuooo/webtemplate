<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${result }<br />
	<form action="/api/importTrade" method="POST">
		서비스키 : <input type="text" name="serviceKey" id="serviceKey" size="50" />
		지역코드 : <input type="text" name="lawdCd" id="lawdCd" />
		거래월 : <input type="text" name="dealYmd" id="dealYmd" />
		<input type="submit" value="import trade" />
	</form>
	<br />
	<form action="/api/importRent" method="POST">
		서비스키 : <input type="text" name="serviceKey" id="serviceKey" />
		지역코드 : <input type="text" name="lawdCd" id="lawdCd" />
		거래월 : <input type="text" name="dealYmd" id="dealYmd" />
		<input type="submit" value="import rent" />
	</form>
<!-- 	<a href="<c:url value='/api/importTrade' />">Import Trade API</a>
	<a href="<c:url value='/api/importRent' />">Import Rent API</a>  -->
</body>
</html>