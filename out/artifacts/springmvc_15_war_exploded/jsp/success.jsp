<%--
  Created by IntelliJ IDEA.
  User: q1591
  Date: 2020-05-04
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传成功</title>
</head>
<body>
    <h3>文件上传成功</h3>
    <h4>上传路径: ${requestScope.fileUrl}</h4>
    <h4>下载地址: <a href="fileDownload?fileName=${requestScope.fileName}">${requestScope.fileName}</a></h4>
</body>
</html>
