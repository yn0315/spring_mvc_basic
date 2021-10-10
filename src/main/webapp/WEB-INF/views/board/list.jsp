<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 날짜포맷팅 태그 라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .pagination {
            width: 60%;
            margin-top: 10px;
            list-style: none;
            display: flex;
        }

        .pagination>li {
            justify-content: flex-end;
            margin-right: 5px;
        }

        .pagination>li>a {
            text-decoration: none;
            color: black;
        }

        .pagination>li>a:hover {
            color: yellowgreen;
        }

        .pagination>li.active>a {
            font-weight: bold;
            color: orangered;
            font-size: 1.1em;
        }

        #title, #delete {
            color: inherit;
            text-decoration: none;
        }

        #title:hover {
            color: rgb(221, 29, 29);
            text-decoration-line: underline;
        }

        #delete:hover {
            color: rgb(221, 29, 29);
            text-decoration-line: underline;
        }

        #gray {
            background: rgb(87, 82, 82);
            line-height: 20px;
        }

        #go {
            color: inherit;
        }

    </style>

    <%@ include file="../include/static-head.jsp" %>

</head>

<body>

    <%@ include file="../include/header.jsp" %>

    <div class="list-container">
        <h2>게시글 목록</h2>

        <div class="amount">
            <a id="gray" href="#">10</a>
            <a id="gray" href="#">20</a>
            <a id="gray" href="#">30</a>
        </div>

        <table class="table table-hover" border=" 1">
            <tr>
                <th class="table-dark">번호</th>
                <th class="table-dark">작성자</th>
                <th class="table-dark">제목</th>
                <th class="table-dark">작성시간</th>
                <th class="table-dark">조회수</th>
                <th class="table-dark">비고</th>
            </tr>


            <c:forEach var="article" items="${articles}">
                <tr>
                    <td>${article.boardNo}</td>
                    <td>${article.writer}</td>
                    <td>
                        <a id ="title" href="/board/content?boardNo=${article.boardNo}">${article.title}</a>

                        <!-- newFlag가 트루일때만 new 뜨게, test안쪽에 조건문 씀, jsp의 조건문작성은 c:if -->
                        <c:if test="${article.newFlag}">
                            <span class="badge rounded-pill bg-danger">new</span>
                        </c:if>

                        <!-- hitFlag가 트루일 때만 hit뜨게 -->
                        <c:if test="${article.hitFlag}">
                            <span class="badge rounded-pill bg-danger">hit</span>
                        </c:if>


                    </td>
                    <td>
                        <!-- a는 오전오후 마킹 요일나오게 하고싶으면 E넣으면 됨 -->
                        <fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd a hh:mm:ss" />
                    </td>
                    <td>${article.viewCnt}</td>
                    <td>

                        <a id ="delete" data-board-no="${article.boardNo}" class="del_btn" href="#">[삭제]</a>

                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>

    <!-- 페이지 영역 -->
    <ul class="pagination">


    </ul>


    <!-- 검색창 영역 -->
    <div class="search">
        <form action="/board/list" id="search-form">

            <select name="type">
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="writer">작성자</option>
                <option value="titleContent">제목+내용
                </option>
            </select>

            <input type="text" name="keyword" placeholder="검색어를 입력!" value="">

            <button type="submit">검색</button>

        </form>
    </div>

    <p>
        <a id ="go" href="/board/write">게시글 작성하기</a>
    </p>

    <script>
        //게시물 등록 처리알림
        const msg = '${msg}';
        if (msg === 'success') {
            alert('게시물 등록 성공!');
        } else if (msg === 'fail') {
            alert('게시물 등록 실패!');
        }

        //삭제버튼 클릭이벤트

        const table = document.querySelector('table');


        table.addEventListener('click', e => {

            if (!e.target.matches('table a.del_btn')) return;

            e.preventDefault(); //a태그 링크이동기능 중지
            //console.log('삭제버튼 클릭됨');
            const boardNo = e.target.dataset.boardNo;

            if (confirm('정말로 삭제하시겠습니까?')) {
                location.href = '/board/delete?boardNo=' + boardNo; //링크이동기능
            }

        });

    </script>




</body>

</html>