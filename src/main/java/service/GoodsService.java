package service;

import bean.vo.GoodsVo;

import java.util.List;


public interface GoodsService {
    public List<GoodsVo> getGoodsByPage(int pageNo);
    public GoodsVo getGoodsById(String id);
    public int getPageCount();
    public int addGoods(GoodsVo newGoods);
    public int modifyGoods(GoodsVo modifyGoods);
    public int deleteGoods(String id);
}
