<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="outcomeTypeDto" type="com.fm.internal.dtos.OutcomeTypeDto"--%>

<t:master-page title="${outcomeTypeDto.name}">
    <div class="container">
        <h1>${outcomeTypeDto.name}</h1>
        <script src="<c:url value="/resources/js/defaultDateForRange.js"/>"></script>
        <form:form method="get" action="/outcometype/page" modelAttribute="rangeDto" id="rangeForm">
            <div class="input-daterange input-group col-xs-2" id="datepicker-range">
                <form:input path="start" type="text" cssClass="input-sm form-control" name="start" id="start" readonly="true"/>
                <span class="input-group-addon">to</span>
                <form:input path="end" type="text" cssClass="input-sm form-control" name="end" id="end" readonly="true"/>
            </div>
            <br/>
            <button type="submit" class="btn btn-default">Submit</button>
            <input type="hidden" name="itemId" value="${outcomeTypeDto.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <script>
                $('#datepicker-range').datepicker({
                    format: "yyyy-mm-dd",
                    todayBtn: "linked",
                    clearBtn: true
                });
            </script>
        </form:form>
        <c:choose>
            <c:when test="${not empty outcomeTypeDto.outcomes}">
                <jsp:include page="../fragments/pagination.jsp"/>
                <table class="table">
                    <tr>
                        <th>Дата</th>
                        <th></th>
                        <th></th>
                        <th>Счет</th>
                        <th>Заметка</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach var="outcome" items="${outcomeTypeDto.outcomes}">
                        <tr>
                            <td>
                                    ${outcome.date} ${outcome.time}
                            </td>
                            <td>
                                <fmt:formatNumber type="currency"
                                                  currencySymbol="${outcome.account.currency.characterCode}"
                                                  value="${outcome.amount}"/>
                            </td>
                            <td>
                                <fmt:formatNumber type="currency"
                                                  currencySymbol="${outcome.account.user.info.currency.characterCode}"
                                                  value="${outcome.defaultAmount}"/>
                            </td>
                            <td>
                                    ${outcome.account.name}
                            </td>
                            <td>
                                    ${outcome.note}
                            </td>
                            <td>
                                <form action="<c:url value="/outcome/delete"/>" method="POST"
                                      onsubmit="return confirm('Операция будет удалена! Вы хотите продолжить?')">
                                    <input type="hidden" name="outcomeId" value="${outcome.id}">
                                    <button class="btn btn-danger" type="submit"><span class="glyphicon glyphicon-trash"></span></button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">
                    <span class="glyphicon glyphicon-info-sign"></span> Пока нет расходов по данной категории.
                </div>
            </c:otherwise>
        </c:choose>
        <div>
            <button class="btn btn-danger" type="button" data-toggle="modal" data-target="#outcometype-delete">
                Удалить категорию
            </button>
            <jsp:include page="outcometype-delete.jsp"/>
        </div>
    </div>
</t:master-page>
