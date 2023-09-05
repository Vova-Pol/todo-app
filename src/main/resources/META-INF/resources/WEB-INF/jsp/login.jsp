<html>

    <head>
        <title>JSP Login Page</title>
    </head>

    <body>
        <h1>Welcome! Time to Login!</h1>
        <pre>${errorMessage}</pre>
        <form method="post">
            Name: <input type="text" name="name">
            Password: <input type="password" name="password">
            <input type="submit">
        </form>
    </body>

</html>