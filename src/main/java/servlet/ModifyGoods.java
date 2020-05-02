package servlet;

import bean.vo.GoodsVo;
import bean.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.CartService;
import service.GoodsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ModifyGoods extends HttpServlet {
    @Autowired
    GoodsService goodsService;
    @Autowired
    CartService cartService;

    public ModifyGoods() {
        super();
    }
    public void destroy() {
        super.destroy();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String modifyGoodsId = (String) session.getAttribute("id");
        String modifyGoodsName = request.getParameter("goodsName");
        Float modifyGoodsPrice = Float.valueOf(request.getParameter("goodsPrice"));
        String message;

        GoodsVo modifyGoods = new GoodsVo();
        modifyGoods.setGoodsId(modifyGoodsId);
        modifyGoods.setGoodsName(modifyGoodsName);
        modifyGoods.setPrice(modifyGoodsPrice);

        ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
        Iterator<Item> it = cart.iterator();
        while(it.hasNext()){
            Item oneItem = it.next();
            if(oneItem.getGoods().getGoodsId().equalsIgnoreCase(modifyGoodsId)){
                oneItem.getGoods().setGoodsName(modifyGoodsName);
                oneItem.getGoods().setPrice(modifyGoodsPrice);
                break;
            }
        }
        cartService.setCart(cart);
        session.setAttribute("cart", cartService.getCart());


        if(goodsService.modifyGoods(modifyGoods)>0){
            message = "修改商品成功";
        }
        else {
            message = "修改商品失败";
        }

        request.setAttribute("message", message);

        String forward = "getAllGoods";
        RequestDispatcher rd = request.getRequestDispatcher(forward);
        rd.forward(request, response);
    }
}
