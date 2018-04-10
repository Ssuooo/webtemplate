<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>파일 또는 경로를 찾을 수 없습니다</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	margin: 0px;
}

td,body {
	color: #464646;
	font-family: NSimsun;
	font-size: 12px;
	line-height: 1.5;
}

img {
	border: none;
}

.b {
	font-weight: bold;
}

.gray01,a.gray01:link,a.gray01:visited,a.gray01:hover,a.gray01:active {
	color: #636363;
}

.o01,a.o01:link,a.o01:visited,a.o01:hover,a.o01:active {
	color: #EB5D07;
}

.br01,a.br01:link,a.br01:visited,a.br01:hover,a.br01:active {
	color: #855133
}

a:link,a:visited,a:active {
	color: #464646;
	text-decoration: none;
}

a:hover {
	color: #464646;
	text-decoration: underline;
}
</style>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table cellspacing="0" cellpadding="0" border="0" width="810">
	<tr>
		<td align="center" style="padding: 50 0 35 0">
		<table cellspacing="0" cellpadding="0" border="0" width="600">
			<tr>
				<td>
				<table cellspacing="0" cellpadding="0" border="0" width="100%">
					<tr valign="top">
						<td style="padding: 105 0 0 45"><span class="b"><font
							color="#B45F38">요청하신 페이지를 찾을 수 없습니다.</font></span><br>
						<img width="1" height="7"><br>
						찾으시는 페이지의 url 이 변경되었거나 더 이상 제공되지 않는 경우입니다.</td>
					</tr>
					<tr>
						<td style="padding: 5 0 0 45">요청하신 페이지 : <a href="${url}">${url}</a></td>
					</tr>
					<tr>
						<td colspan="2" height="10"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td align="center">
				<table cellspacing="0" cellpadding="0" border="0" width="535">
					<tr>
						<td style="padding: 10 0 7 14; line-height: 2.0"><b>${statusCode}
						ERROR</b><br>
						${message}</td>
					</tr>
					<tr>
						<td height="1" bgcolor="#E5E5E5"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="17"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
