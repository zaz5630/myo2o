package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.ProductCategory;
import cn.azzhu.myo2o.mapper.shop.ProductCategoryMapper;
import cn.azzhu.myo2o.service.ProductCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-02 18:56:47
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper categoryMapper;
    @Override
    public PageInfo<ProductCategory> getPage(ProductCategory productCategory, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ProductCategory> list = categoryMapper.selectList(new QueryWrapper<>());
        return new PageInfo<>(list);
    }
}
