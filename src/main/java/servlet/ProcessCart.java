package servlet;

import bean.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.CartService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ProcessCart extends HttpServlet {

    @Autowired
    CartService cartService;

    public ProcessCart() {
        super();
    }
    public void destroy() {
        super.destroy();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String type = request.getParameter("action");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String goodsId = request.getParameter("goodsId");

        HttpSession session = request.getSession();
        ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");

        if(type.equalsIgnoreCase("修改")){
            cartService.setCart(cart);
            cartService.update(goodsId, quantity);
        }

        if(type.equalsIgnoreCase("删除")){
            cartService.setCart(cart);
            cartService.delete(goodsId);
        }

        session.setAttribute("cart", cartService.getCart());

        response.sendRedirect("cart.jsp");
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }
}
