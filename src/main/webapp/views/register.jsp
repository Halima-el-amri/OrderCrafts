<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        clifford: '#da373d',

                    }
                }
            },
            plugins: [
                // ...
                require('@tailwindcss/forms'),
            ]
        }
    </script>
    <title>Registration</title>
</head>
<body>
<div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
        <img class="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company">
        <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Register new User</h2>
    </div>
<%--<h2>User Registration</h2>--%>

<%--<form action="${pageContext.request.contextPath}/register" method="post">--%>
<%--    <label for="username">Username:</label>--%>
<%--    <input type="text" id="username" name="username" required><br>--%>

<%--    <label for="password">Password:</label>--%>
<%--    <input type="password" id="password" name="password" required><br>--%>

<%--    <input type="submit" value="Register">--%>
<%--</form>--%>

<form action="${pageContext.request.contextPath}/register" method="post">
<div>
    <label for="username" class="ml-px pl-4 block text-sm font-medium text-gray-700">User Name</label>
    <div class="mt-1">
        <input type="text" name="username" id="username" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 px-4 rounded-full" placeholder="H">
    </div>
</div>

    <div>
        <label for="password" class="ml-px pl-4 block text-sm font-medium text-gray-700">Password</label>
        <div class="mt-1">
            <input type="password" name="password" id="password" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 px-4 rounded-full" placeholder="H">
        </div>
    </div>
</form>
</body>
</html>
