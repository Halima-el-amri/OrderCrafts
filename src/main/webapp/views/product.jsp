<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>

    <!-- Include Tailwind CSS -->
    <link href="https://cdn.tailwindcss.com" rel="stylesheet">
</head>
<body>

<h2>Product List</h2>

<div class="px-4 sm:px-6 lg:px-8">
    <div class="sm:flex sm:items-center">
        <div class="sm:flex-auto">
            <h1 class="text-xl font-semibold text-gray-900">Products</h1>
            <p class="mt-2 text-sm text-gray-700">A list of all the Products in your account including their Id, name, Price, and Quantity Stock.</p>
        </div>
        <div class="mt-4 sm:mt-0 sm:ml-16 sm:flex-none">
            <button type="button" class="inline-flex items-center justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 sm:w-auto">Add Product</button>
        </div>
    </div>
    <div class="mt-8 flex flex-col">
        <div class="-my-2 -mx-4 overflow-x-auto sm:-mx-6 lg:-mx-8">
            <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                <div class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
                    <table class="min-w-full divide-y divide-gray-300">
                        <thead class="bg-gray-50">
                        <tr>
                            <th scope="col" class="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-6">Id</th>
                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Product Name</th>
                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Price</th>
                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Stock Quantity</th>
                            <th scope="col" class="relative py-3.5 pl-3 pr-4 sm:pr-6">
                                <span class="sr-only">Edit</span>
                                <span class="sr-only">Delete</span>
                            </th>
                        </tr>
                        </thead>

                        <tbody class="divide-y divide-gray-200 bg-white">
                        <%-- Use consistent variable names based on your Product class properties --%>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td class="whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6">${product.itemId}</td>
                                <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">${product.name}</td>
                                <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">${product.price}</td>
                                <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">${product.stockQuantity}</td>
                                <td class="relative whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-6">
                                    Actions
                                    <form action="${pageContext.request.contextPath}/product" method="post">
                                        <input type="hidden" name="_method" value="DELETE">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="productId" value="${product.id}">
                                        <button type="submit">Update</button>
                                    </form>                                    |
                                    <form action="${pageContext.request.contextPath}/product" method="post">
                                        <input type="hidden" name="_method" value="DELETE">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="productId" value="${product.id}">
                                        <button type="submit">Delete</button>
                                    </form>                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function confirmDelete(productId) {
        if (confirm("Are you sure you want to delete this product?")) {
            window.location.href = "${pageContext.request.contextPath}/product?action=delete&productId=" + productId;
        }
    }
</script>

</body>
</html>
