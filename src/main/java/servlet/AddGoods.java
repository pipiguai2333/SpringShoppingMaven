package servlet;

import bean.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.GoodsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddGoods extends HttpServlet {

    @Autowired
    GoodsService goodsService;

    public AddGoods() {
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

        String newGoodsId = request.getParameter("goodsId");
        String newGoodsName = request.getParameter("goodsName");
        Float newGoodsPrice = Float.valueOf(request.getParameter("goodsPrice"));
        String message;

        GoodsVo newGoods = new GoodsVo();
        newGoods.setGoodsId(newGoodsId);
        newGoods.setGoodsName(newGoodsName);
        newGoods.setPrice(newGoodsPrice);

        if(goodsService.addGoods(newGoods)>0){
             message = "添加商品成功";
        }
        else {
             message = "添加商品失败";
        }

        request.setAttribute("message", message);

        String forward = "getAllGoods";
        RequestDispatcher rd = request.getRequestDispatcher(forward);
        rd.forward(request, response);

    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }
}
