

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 태그라이브러리, for문같은거 쓸 때 필요, jstl이라고 함 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>

<!-- 프라이빗이어도 EL은 쓸 수 있음 -->

    <h1>회원가입에 성공했습니다!</h1>
    <h2>${user.getUserName()}님 환영합니다!</h2>

    <div>
        #아이디:${user.userId}     <br>
        #비밀번호:${user.userPw}   <br>
        #이름: ${user.userName}   <br>
        #나이:${user.userAge}     <br>
        #취미:${user.hobbies}     <br>
        <ul>
            <!-- jsp에서만 되는 방법, for문.. -->
            <c:forEach var = "hobby" items = "${user.hobbies}">
                <li>${hobby}</li>
            </c:forEach>

        </ul>
        
    </div>

</body>
</html>