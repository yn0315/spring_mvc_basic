<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>check.jsp파일입니다.</h1>

<!-- 보낼 경로 작성 , 상대경로로 작성한 것/ 절대경로는 /jsp/validate.jsp-->
<form action="validate.jsp">
    <label>
        <input type="text" name="age" placeholder="나이를 숫자로 입력!">
    </label>
    <button type="submit">확인</button>
</form>

</body>
</html>