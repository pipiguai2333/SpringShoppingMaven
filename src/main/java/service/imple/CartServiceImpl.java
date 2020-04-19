package service.imple;

import bean.vo.GoodsVo;
import bean.vo.Item;
import org.springframework.stereotype.Service;
import service.CartService;
import service.GoodsService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;

@Service("cartService")
public class CartServiceImpl implements CartService {
    ArrayList<Item> cart = new ArrayList<Item>();

    @Resource(name="goodsService")
    GoodsService goodsService;

    @Override
    public void addToCart(String id, int quantity) {
        GoodsVo g = goodsService.getGoodsById(id);

        Iterator<Item> it = cart.iterator();
        boolean find = false;
        while(it.hasNext()){
            Item oneItem = it.next();
            if(oneItem.getGoods().getGoodsId().equalsIgnoreCase(id)){
                oneItem.setQuantity(oneItem.getQuantity() + quantity);
                find = true;
                break;
            }
        }
        if(!find){
            Item newItem = new Item(g, quantity);
            cart.add(newItem);
        }
    }

    @Override
    public void update(String id, int quantity) {
        Iterator<Item> it = cart.iterator();
        while(it.hasNext()){
            Item oneItem = it.next();
            if(oneItem.getGoods().getGoodsId().equalsIgnoreCase(id)){
                oneItem.setQuantity(quantity);
                break;
            }
        }
    }

    @Override
    public void delete(String id) {
        if(cart != null){
            Iterator<Item> it = cart.iterator();
            while (it.hasNext()){
                Item temp = it.next();
                String goodId = temp.getGoods().getGoodsId();
                if(goodId.equals(id)){
                    cart.remove(temp);
                    break;
                }
            }
        }
    }

    @Override
    public ArrayList<Item> getCart() {
        return cart;
    }

    @Override
    public void setCart(ArrayList<Item> cart) {
        this.cart = cart;
    }
}
