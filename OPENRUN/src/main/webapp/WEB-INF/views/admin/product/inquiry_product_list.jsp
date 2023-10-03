<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resources/common/css/sellerModifyProduct.css' />" rel="stylesheet" type="text/css">
<script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../../../views/header.jsp" />

	<section>
		<div id="section_wrap">
			<div>
				<jsp:include page="../../seller/seller_nav_side.jsp"/>
			</div>
			<div>
				<div class="word">
					<h3>문의관리</h3>
				</div>
				<div class="top_nav">
					<table>
						<tr>
							<th>문의 목록 조회</th>
						</tr>
					</table>
				</div>
				<div class="inquiry_table">
					<form action="<c:url value='/product/admin/inquiryProductList' />" name="inquiry_list" method="post" enctype="multipart/form-data">
						<div>
							<img src="/resource/image/filter.png"/>
							<p>옵션</p>
						</div>
						<table>
							<thead>
								<tr>
									<th>카테고리</th>
									<th>제목</th>
									<th>작성자</th>
									<th>문의등록일</th>
									<th>문의답변일</th>
									<th>처리상태</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="inquiry" items="${inquiryVos}">
									<tr>
										<td>
										<c:url value='/product/admin/inquiryDetail' var='detail_url'>
											<c:param name='i_no' value='${inquiry.i_no}'/>
										</c:url>
										<a href="${detail_url}">${inquiry.i_titile}</a>
										</td>
										<td>${inquiry.u_name}</td>
										<td>${inquiry.i_date}</td>
										<td>
											<c:choose>
												<c:when test="${item.i_answer_date eq 0}"> <c:out value="-" /> </c:when>
												<c:when test="${item.i_answer_date eq 1}"> <c:out value="${inquiry.i_answer_date}" /> </c:when>
												<c:otherwise> <c:out value="-" /> </c:otherwise>
											</c:choose>
										${inquiry.i_isbn}</td>
										<td>
											<input type="button" value="답변작성" onClick="location.href='inquiry_product_answer.jsp'">
											<c:choose>
												<c:when test="${item.i_answer_state eq 0}"> <c:out value="답변작성" /> </c:when>
												<c:when test="${item.i_answer_state eq 1}"> <c:out value="답변완료" /> </c:when>
												<c:otherwise> <c:out value="답변작성" /> </c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../../../views/footer.jsp" />
</body>
</html>