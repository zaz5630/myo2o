package cn.azzhu.myo2o.service;

import cn.azzhu.myo2o.entity.ProductCategory;
import com.github.pagehelper.PageInfo;

/**
 * @author azzhu
 * @create 2019-09-02 18:52:56
 */
public interface ProductCategoryService {

    /**
     * 带条件的分页查询
     * @param productCategory
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<ProductCategory> getPage(ProductCategory productCategory, Integer pageNum, Integer pageSize);
}
