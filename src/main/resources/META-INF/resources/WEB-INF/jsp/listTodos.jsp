<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>To Do List Page</title>
    </head>

    <body>
        <h1>Welcome, ${name}!</h1>
        <hr>
        <h2>
            Your ToDos are:
        </h2>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.isDone}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>

</html>