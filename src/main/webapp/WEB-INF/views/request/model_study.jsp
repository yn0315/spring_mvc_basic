<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>
//${} 안에 전송할 데이터의 이름작성, EL이라고 함

    <h1>model객체를 학습합니다.</h1>
    <p>
        서버가 전송한 데이터: ${greet}
    </p>

    <p>당신의 나이는 ${myAge}살이고, ${birth}년생입니다.</p>

</body>
</html>