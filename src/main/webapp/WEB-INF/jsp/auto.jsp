<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="auto.title"/></h3>

        <form method="get" action="auto/filter">
            <input type="hidden" name="action" value="filter">
            <dl>
                <dt><spring:message code="auto.startDate"/>:</dt>
                <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
            </dl>
            <dl>
                <dt><spring:message code="auto.endDate"/>:</dt>
                <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
            </dl>
            <dl>
                <dt><spring:message code="auto.startTime"/>:</dt>
                <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
            </dl>
            <dl>
                <dt><spring:message code="auto.endTime"/>:</dt>
                <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
            </dl>
            <button type="submit" class="btn btn-secondary"><spring:message code="auto.filter"/></button>
        </form>
        <hr/>
        <a href="auto/create?action=create" class="btn btn-primary"><span class="fa fa-plus"></span> <spring:message code="auto.add"/></a>
        <br><br>
        <table class="table table-striped mt-3">
            <thead>
            <tr>
                <th>Brand</th>
                <th>Model</th>
                <th>Body</th>
                <th>Color</th>
                <th>Power</th>
                <th>Fuel</th>
                <th>Date</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${autoList}" var="auto">
                <jsp:useBean id="auto" type="top.desq.javaapp.model.Auto"/>
                <tr>
                    <td>${auto.brand}</td>
                    <td>${auto.model}</td>
                    <td>${auto.body}</td>
                    <td>${auto.color}</td>
                    <td>${auto.power}</td>
                    <td>${auto.fuel}</td>
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${auto.dateTime.toLocalDate()}
                    </td>
                    <td><a href="auto/update?id=${auto.id}" class="btn btn-success"><spring:message code="common.update"/></a></td>
                    <td><a href="auto/delete?id=${auto.id}" class="btn btn-danger"><spring:message code="common.delete"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>