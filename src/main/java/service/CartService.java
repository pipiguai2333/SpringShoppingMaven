package service;

import bean.vo.Item;

import java.util.ArrayList;

public interface CartService  {
    public void addToCart(String id, int quantity);
    public void update(String id, int quantity);
    public void delete(String id);
    public ArrayList<Item> getCart();
    public void setCart(ArrayList<Item> cart);
}
