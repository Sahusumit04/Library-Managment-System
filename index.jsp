<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>## LBMS ##</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <style>
        .nav_img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col">
                <%@ include file="header.jsp" %>
            </div>
        </div>

        <div class="row justify-content-around mt-5">
            <div class="col-md-3 border border-secondary rounded" style="padding: 10px">
                <a href="login.do?user_type_id=1"><img src="static/media/images/institute.png" class="nav_img"></a>
            </div>
            <div class="col-md-3 border border-secondary rounded">
                <a href="login.do?user_type_id=2"><img src="static/media/images/student.webp"  class="nav_img"></a>
            </div>
            <div class="col-md-3 border border-secondary rounded">
                <a href="login.do?user_type_id=3"><img src="static/media/images/teacher.png"  class="nav_img"></a>
            </div>
        </div>
    </div>
</body>

</html>