<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie List</title>
</head>
    <body>
        <form:form action="register" modelAttribute="user">
            <table>
                <tr>
                    <td>Username</td>
                    <td><form:input path="username" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><form:password path="password" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <form:button>Register</form:button>
                    </td>
                </tr>
            </table>
        </form:form>
        <div style="color: red">${errorMessage}</div>
    </body>
</html>
