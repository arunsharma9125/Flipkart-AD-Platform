<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
</head>
<body>
<#if message?has_content>
	${message}
</#if>
<#if loggedInUser?has_content>
	Welcome ${loggedInUser.name}
<#else>
	Please signin
	<a href="/signup>Signup</a>
</#if>
</body>
</html>