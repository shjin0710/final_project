<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<table id="calendar" align="center">
			<tr>
				<td align="center"><label onclick="prevCalendar()"> ◀ </label></td>
				<td colspan="5" align="center" id="calendarTitle">yyyy년 m월</td>
				<td align="center"><label onclick="nextCalendar()"> ▶ </label></td>
			</tr>
			<tr>
				<td align="center"><font color ="#F79DC2">일</td>
				<td align="center">월</td>
				<td align="center">화</td>
				<td align="center">수</td>
				<td align="center">목</td>
				<td align="center">금</td>
				<td align="center"><font color ="skyblue">토</td>
			</tr>
			<script type="text/javascript">buildCalendar();</script>
	</table>
</div>
