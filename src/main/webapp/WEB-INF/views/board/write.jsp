<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <title>Insert title here</title>


   <style>
      .fileDrop {
         width: 800px;
         height: 400px;
         border: 1px dashed gray;
         display: flex;
         justify-content: center;
         align-items: center;
         font-size: 1.5em;
      }

      .uploaded-list {
         display: flex;
      }

      .uploaded-list a {
         display: flex;
         flex-direction: column;
      }

      .uploaded-list a img {
         width: 100px;
         height: 100px;
         display: block;
      }

      .uploaded-list .thumbnail-box {
         display: flex;
      }

      #go {
            color: inherit;
      }

      #go:hover {
         color: rgb(221, 29, 29);
      }

   </style>

   <%@ include file="../include/static-head.jsp" %>

</head>

<body>
   <%@ include file="../include/header.jsp" %>

   <div class="write-container">
      <h2>게시글 등록</h2>

      <form class="write-form" action="/board/write" method="post">
         <div class="input-box">

            # 작성자: <input class="form-control" type="text" name="writer" readonly value="${loginUser.account}"><br>
            # 제목: <input class="form-control" type="text" name="title"><br>
            # 내용: <br>
            <textarea class="form-control" rows="5" cols="30" name="content"></textarea>
            <br>

         </div>
         <button type="submit">등록</button>
      </form>
   </div>

   <a id="go" href="/board/list">글 목록보기</a>

</body>

</html>