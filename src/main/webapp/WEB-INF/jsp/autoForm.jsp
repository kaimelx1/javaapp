<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h2 class="text-center">${param.action == 'create' ? 'Create auto' : 'Edit auto'}</h2>
        <hr>
        <jsp:useBean id="auto" type="top.desq.javaapp.model.Auto" scope="request"/>
        <form method="post" action="auto">
            <input type="hidden" name="id" value="${auto.id}">

            <dl>
                <dt>Brand:</dt>
            <select name="brand" class="form-control d-inline w-auto">
                <option value="">-</option>
                <c:forEach items="${brands}" var="brand">
                    <jsp:useBean id="brand" type="top.desq.javaapp.model.Brand"/>
                    <option value="${brand}" ${auto.brand.equals(brand) ? "selected" : ""}>${brand}</option>
                </c:forEach>
            </select>
            </dl>

            <dl>
                <dt>Model:</dt>
                <dd><input type="text" value="${auto.model}" size=40 name="model" required></dd>
            </dl>

            <dl>
                <dt>BodyStyle:</dt>
                <select name="body" class="form-control d-inline w-auto">
                    <option value="">-</option>
                    <c:forEach items="${bodies}" var="body">
                        <jsp:useBean id="body" type="top.desq.javaapp.model.BodyStyle"/>
                        <option value="${body}" ${auto.body.equals(body) ? "selected" : ""}>${body}</option>
                    </c:forEach>
                </select>
            </dl>

            <dl>
                <dt>Color:</dt>
                <select name="color" class="form-control d-inline w-auto">
                    <option value="">-</option>
                    <c:forEach items="${colors}" var="color">
                        <jsp:useBean id="color" type="top.desq.javaapp.model.Color"/>
                        <option value="${color}" ${auto.color.equals(color) ? "selected" : ""}>${color}</option>
                    </c:forEach>
                </select>
            </dl>

            <dl>
                <dt>Power:</dt>
                <dd><input type="power" value="${auto.power}" name="power" required></dd>
            </dl>

            <dl>
                <dt>FuelType:</dt>
                <select name="fuel" class="form-control d-inline w-auto">
                    <option value="">-</option>
                    <c:forEach items="${fuels}" var="fuel">
                        <jsp:useBean id="fuel" type="top.desq.javaapp.model.FuelType"/>
                        <option value="${fuel}" ${auto.fuel.equals(fuel) ? "selected" : ""}>${fuel}</option>
                    </c:forEach>
                </select>
            </dl>

            <dl>
                <dt>DateTime:</dt>
                <dd><input type="datetime-local" value="${auto.dateTime}" name="dateTime"></dd>
            </dl>

            <button type="submit" class="btn btn-success">Save</button>
            <button onclick="window.history.back()" type="button" class="btn btn-danger">Cancel</button>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>