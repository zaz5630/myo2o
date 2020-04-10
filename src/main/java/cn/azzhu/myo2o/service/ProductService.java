package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.entity.Product;
import cn.azzhu.myo2o.vo.ProductVo;
import com.github.pagehelper.PageInfo;

/**
 * @author azzhu
 * @create 2019-08-26 17:15:44
 */
public interface ProductService {

    /**
     * 带查询条件的分页数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Product> getPage(Integer pageNum,Integer pageSize,ProductVo productVo);

    /**
     * 根据商品id删除
     * @param product
     */
    void deleteProductById(Product product);

    /**
     * 下架商品
     * @param product
     */
    void down(Product product);

    /**
     * 上架商品
     * @param product
     */
    void up(Product product);

    /**
     * 批量删除
     * @param ids
     */
    void deleteProductByIds(String[] ids);

    /**
     * 新增商品
     * @param product
     */
    void addProduct(Product product);

    /**
     * 修改商品
     * @param product
     */
    void updateProduct(Product product);
}
