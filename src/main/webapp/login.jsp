<!DOCTYPE html>
<html>
<body>
 
<form action="Login" method="post">
 
<table>
 
<tr>
 
<td>Name:</td>
 
 
<td><input type="text" name="userName"></td>
 
</tr>
 
 
<tr>
 
<td>Password:</td>
 
 
<td><input type="password" name="userPassword"></td>
 
</tr>

</table>
 <span style="color: red;">${error}<br></span>
<input type="submit" value="Login">
</form>
 
</body>
</html>