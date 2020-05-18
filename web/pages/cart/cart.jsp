<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteItem").click(function () {
				return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？");
            });
			$("a.clear").click(function () {
				return confirm("你确定要清空购物车吗?")
            });
            // //给输入框绑定onchange内容发生改变事件
            // $(" input.updateCount").change(function () {
				// if (confirm("你确定将【"+$(this).parent().parent().find("td:first").text()+"】商品数量修改为："+this.value()+"吗?")){
				// 	location.href="http://localhost:8080/Book/carServlet?action=updateCount&id="+$(this).attr("bookId")+"&count="+this.value();
				// } else {
				//     //defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值
				//     this.defaultValue;
				// }
            // });
            // 给输入框绑定 onchange内容发生改变事件
            $(".updateCount").change(function () {
                // 获取商品名称
                var name = $(this).parent().parent().find("td:first").text();
                var id = $(this).attr('bookId');
                // 获取商品数量
                var count = this.value;
                if ( confirm("你确定要将【" + name + "】商品修改数量为：" + count + " 吗?") ) {
                    //发起请求。给服务器保存修改
                    location.href = "http://localhost:8080/Book/carServlet?action=updateCount&count="+count+"&id="+id;
                } else {
                    // defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值。
                    this.value = this.defaultValue;
                }
            });
        });
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:if test="${empty sessionScope.car.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">购物车为空，请去商品页浏览商品！！！</a></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.car.items}">
				<c:forEach items="${sessionScope.car.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" bookId="${entry.value.id}" style="width: 80px" type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="carServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


			
		</table>
		<c:if test="${not empty sessionScope.car.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.car.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.car.totalPrice}</span>元</span>
				<span class="cart_span"><a class="clear" href="carServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>