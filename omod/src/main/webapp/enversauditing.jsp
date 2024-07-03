<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<h2><spring:message code="eversauditing.title" /></h2>

<br/>
<h2>Select a Class for Audit</h2>

<form action="enversauditing.form" method="post">
    <select name="selectedClass">
        <c:forEach var="cls" items="${classes}">
            <option value="${cls}" <c:if test="${cls == currentClass}">selected</c:if>>${cls}</option>
        </c:forEach>
    </select>
    <button type="submit">View Audits</button>
</form>

<c:if test="${not empty audits}">
    <h2>Audit Table for ${currentClass}</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Changed By</th>
            <th>Changed On</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="audit" items="${audits}">
            <tr>
                <td>${audit.entity.id}</td>
                <td>${audit.revisionEntity.changedBy}</td>
                <td>${audit.revisionEntity.changedOn}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<%@ include file="/WEB-INF/template/footer.jsp"%>
