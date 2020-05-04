<%--
  Created by IntelliJ IDEA.
  User: q1591
  Date: 2020-05-04
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>单文件上传实例页</title>
</head>
<body>
    <h3>单文件上传实例</h3>
    <!-- 文件上传表单, 必须加enctype="multipart/form-data"属性, 否则controller会空指针 -->
    <form action="../oneFileupload" method="post" enctype="multipart/form-data">
        选择文件: <input type="file" name="oneFile"/><br>
        <input type="submit" value="点击上传"/>
    </form>
</body>
</html>
