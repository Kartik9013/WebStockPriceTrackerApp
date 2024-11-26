<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<link rel="stylesheet" href="index.css">	
<title>Login page</title>
</head>
<body>
  <div class="wrapper">
    <form action="loginservlet" method="post">
        <h2>User Login</h2>
        <div class="input-field">
            <input type="text" name="username" required>
        <label>Username</label>
      </div>
      <div class="input-field">
        <input type="password" name="password" required>
        <label>Password</label>
      </div>
      
      <button type="submit">Log In</button>
      <div class="register">
        <p>Don't have an account? <a href="register.jsp">Register</a></p>
      </div>
    </form>
  </div>
</body>
</html>