package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

//   抽取至BaseServlet类中
//   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
////        if ("login".equals(action)){
////            login(req,resp);
////        }
////        if ("regist".equals(action)){
////            regist(req,resp);
////        }
//        //通过反射调用方法
//        try {
//            //获取action业务鉴别字符串，获取相应的业务方法反射对象
//            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
//            //调用目标业务 方法
//            method.invoke(this,req,resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            //   跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //保存用户登录的信息到Session域中
            req.getSession().setAttribute("user",loginUser);
            // 登录 成功
            //跳到成功页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//        抽取至工具类中
//        使用BeanUtils工具类注入参数
//        try {
//            User user = new User();
//            System.out.println("注入之前"+user);
//            BeanUtils.populate(user,req.getParameterMap());
//            System.out.println("注入之后"+user);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

//            User user = new User();
//            System.out.println("注入之前"+user);
//            //工具类注入
//            WebUtils.copyParamToBean(req.getParameterMap(),user);

        //优化
//        User user = (User) WebUtils.copyParamToBean(req.getParameterMap(),new User());

        //使用泛型优化
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());


//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde

        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().invalidate();
        if (token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                System.out.println("用户名[" + username + "]已存在!");
//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(new User(null, username, password, email));
//
//        跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {

            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //session销毁
        req.getSession().invalidate();
        //重定向至首页
        resp.sendRedirect(req.getContextPath());
    }
}
