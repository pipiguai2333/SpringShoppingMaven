package dao.impl;

import bean.vo.GoodsVo;
import dao.GoodsDAO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("goodsDao")
public class GoodsDAOImpl implements GoodsDAO {

    @Resource(name="jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @Override
    public List<GoodsVo> getGoodsByPage(int pageNo) {
        int numPerPage = 2;
        int beginIndex = (pageNo-1)*numPerPage;
        String sql = "select * from good limit "+beginIndex+","+numPerPage;
        RowMapper<GoodsVo> rowMapper = new BeanPropertyRowMapper<GoodsVo>(GoodsVo.class);
        return jdbcTemplate.query(sql,rowMapper,null);
    }

    @Override
    public GoodsVo getGoodsById(String id) {
        String sql = "select * from good where goodsid=?";
        RowMapper<GoodsVo> rowMapper = new BeanPropertyRowMapper<GoodsVo>(GoodsVo.class);
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }

    @Override
    public int getPageCount() {
        String sql = "select count(*) from good";
        Integer count = jdbcTemplate.queryForObject(sql, null, Integer.class);
        int i = count.intValue();
        return (i-1)/2+1;
    }

    @Override
    public int addGoods(GoodsVo newGoods) {
        String sql = "insert into good values(?,?,?)";
        String newGoodsId = newGoods.getGoodsId();
        String newGoodsName = newGoods.getGoodsName();
        Float newGoodsPrice = newGoods.getPrice();
        Object param[] = {newGoodsId, newGoodsName, newGoodsPrice};
        int count = jdbcTemplate.update(sql, param);
        return count;

    }

    @Override
    public int modifyGoods(GoodsVo modifyGoods) {
        String modifyGoodsId = modifyGoods.getGoodsId();
        String modifyGoodsName = modifyGoods.getGoodsName();
        Float modifyGoodsPrice = modifyGoods.getPrice();
        Object param[] = {modifyGoodsName, modifyGoodsPrice, modifyGoodsId};
        String sql = "update good set goodsname=?,price=? where goodsid=?";
        int count = jdbcTemplate.update(sql, param);
        return count;
    }

    @Override
    public int deleteGoods(String id) {
        String sql = "delete from good where goodsid=?";
        int count = jdbcTemplate.update(sql, new Object[]{id});
        return count;
    }
}
