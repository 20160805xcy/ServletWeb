package com.jerry.web.controller;

import com.jerry.domain.Customer;
import com.jerry.service.BusinessService;
import com.jerry.service.impl.BusinessServiceImpl;
import com.jerry.util.FormBeanUtil;
import com.jerry.web.bean.CustomerFormBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ControllerServlet extends HttpServlet {
    private String encoding = "UTF-8";
    private BusinessService s = new BusinessServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);

        String op = request.getParameter("op");
        if ("showAllCustomers".equals(op)) {
            showAllCustomers(request, response);
        } else if ("addCustomer".equals(op)) {
            addCustomer(request, response);
        } else if ("editCustomerUI".equals(op)) {
            editCustomerUI(request, response);
        } else if ("editCustomer".equals(op)) {
            editCustomer(request, response);
        } else if ("delOne".equals(op)) {
            delOne(request, response);
        } else if ("delMulti".equals(op)) {
            delMulti(request, response);
        }

    }

    //删除多条记录
    private void delMulti(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ids[] = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            for (String id : ids)
                s.deleteCustomerById(id);
        }
        response.sendRedirect(request.getContextPath());
    }

    //删除一条记录
    private void delOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String customerId = request.getParameter("customerId");
        s.deleteCustomerById(customerId);
        response.sendRedirect(request.getContextPath());
    }

    //更新用户信息
    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //封装数据到formbean
            CustomerFormBean formBean = FormBeanUtil.fillBean(request, CustomerFormBean.class);
            if (!formBean.validate()) {
                request.setAttribute("formBean", formBean);
                request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
                return;
            }
            //填充模型
            Customer c = new Customer();
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.copyProperties(c, formBean);

            //单独处理爱好
            String hobbies[] = request.getParameterValues("hobbies");
            if (hobbies != null && hobbies.length > 0) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hobbies.length; i++) {
                    if (i > 0)
                        sb.append(",");
                    sb.append(hobbies[i]);
                }
                c.setHobby(sb.toString());
            }

            s.updateCustomer(c);
            response.setHeader("Refresh", "1;URL=" + request.getContextPath());
            response.getWriter().write("保存成功。1秒后自动转向主页");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("服务器忙");
        }
    }

    //根据id查询客户信息，交给界面显示
    private void editCustomerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customerId");
        Customer c = s.findCustomerById(customerId);
        request.setAttribute("c", c);
        request.getRequestDispatcher("/editCustomer.jsp").forward(request, response);
    }

    //保存客户信息
    private void addCustomer(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        try {
            //封装数据到formbean
            CustomerFormBean formBean = FormBeanUtil.fillBean(request, CustomerFormBean.class);
            if (!formBean.validate()) {
                request.setAttribute("formBean", formBean);
                request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
                return;
            }
            //填充模型
            Customer c = new Customer();
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.copyProperties(c, formBean);

            //单独处理爱好
            String hobbies[] = request.getParameterValues("hobbies");
            if (hobbies != null && hobbies.length > 0) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hobbies.length; i++) {
                    if (i > 0)
                        sb.append(",");
                    sb.append(hobbies[i]);
                }
                c.setHobby(sb.toString());
            }

            s.saveCustomer(c);
            response.getWriter().write("保存成功。2秒后自动转向主页");
            response.setHeader("Refresh", "2;URL=" + request.getContextPath());

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("服务器忙");
        }
    }

    //查询所有客户信息
    private void showAllCustomers(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        List<Customer> cs = s.findAllCustomers();
        request.setAttribute("cs", cs);
        request.getRequestDispatcher("/listCustomers.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
