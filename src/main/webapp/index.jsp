<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register User</title>
<link rel="stylesheet" href="index.css">	
</head>
<body>
<div class="wrapper">
    <form action="registerservlet" method="post">
        <h2>User Registration</h2>
        <div class="input-field">
            <input type="text" name="username" required>
        <label>Username</label>
      </div>
      <div class="input-field">
        <input type="password" name="password" required>
        <label>Password</label>
      </div>
      
      <button type="submit">Register</button>
      <div class="register">
        <p>Already registered ? <a href="login.jsp">Login</a></p>
      </div>
    </form>
  </div>
</body>
</html>