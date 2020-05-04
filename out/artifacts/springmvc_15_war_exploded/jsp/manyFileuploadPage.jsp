<%--
  Created by IntelliJ IDEA.
  User: q1591
  Date: 2020-05-04
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>多文件上传实例页</title>
</head>
<body>
    <h3>多文件上传实例</h3>
    <form action="../manyFileupload" method="post" enctype="multipart/form-data">
        文件描述: <input type="text" name="description"/><br>
        选择文件: <input type="file" name="manyFile" multiple="multiple"/><br>
        <input type="submit" value="点击上传"/>
    </form>
</body>
</html>
