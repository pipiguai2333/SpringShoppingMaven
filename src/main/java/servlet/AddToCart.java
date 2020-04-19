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
public class AddToCart extends HttpServlet {

    @Autowired
    CartService cartService;

    public AddToCart() {
        super();
    }
    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String goodsId = request.getParameter("goodsId");
        HttpSession session = request.getSession();
        ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
        if(cart != null){
            cartService.setCart(cart);
            cartService.addToCart(goodsId, 1);
        }
        else{
            cart = new ArrayList<Item>();
            cartService.setCart(cart);
            cartService.addToCart(goodsId, 1);
        }
        session.setAttribute("cart", cartService.getCart());

        response.sendRedirect("cart.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request,response);
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }
}
