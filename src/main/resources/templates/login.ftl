<html>
<#include "common/header.ftl">

<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column" />
        <div class="col-md-4 column">
            <form role="form" method="post" action="/sell/seller/login">
                <div class="form-group">
                    <label>用户名</label><input type="text" class="form-control" name="username" />
                </div>
                <div class="form-group">
                    <label >密码</label><input type="password" class="form-control" name="password" />
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>
        <div class="col-md-4 column" />
    </div>
</div>

</body>
</html>
