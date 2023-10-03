<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resources/common/css/fragments.css' />" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../../views/header.jsp" />
	<section>
		<div id="">
			<div class="">
				<h3>${productVo.p_name}</h3>
			</div>
			<div class="">
				<ul>
					<li>
						<img src="<c:url value="/productUploadImg/${productVo.p_img}"/>">
					</li>
					<li>
						<table>
						
						</table>
					</li>
				</ul>
			</div>
		</div>
	</section>
	<jsp:include page="../../../views/footer.jsp" />
</body>
</html>