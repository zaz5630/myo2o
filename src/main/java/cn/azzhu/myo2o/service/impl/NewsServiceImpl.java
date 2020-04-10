package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.LogInfo;
import cn.azzhu.myo2o.entity.News;
import cn.azzhu.myo2o.mapper.sys.NewsMapper;
import cn.azzhu.myo2o.service.NewsService;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.vo.NewsVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-03 22:34:56
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        PageHelper.startPage(newsVo.getPage(),newsVo.getLimit());
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        //封装查询条件
        if(!StringUtils.isEmpty(newsVo.getTitle())) {
            queryWrapper.like("title",newsVo.getTitle());
        }
        if(!StringUtils.isEmpty(newsVo.getContent())) {
            queryWrapper.like("content",newsVo.getContent());
        }
        if(!StringUtils.isEmpty(newsVo.getStartTime())) {
            queryWrapper.ge("createtime",newsVo.getStartTime());
        }

        if(!StringUtils.isEmpty(newsVo.getEndTime())) {
            queryWrapper.le("createtime",newsVo.getEndTime());
        }
        queryWrapper.orderByDesc("createtime");
        List<News> news = newsMapper.selectList(queryWrapper);
        PageInfo<LogInfo> pageInfo = new PageInfo<>();
        DataGridView<News> dataGridView = new DataGridView<>();
        dataGridView.setCount(pageInfo.getTotal());
        dataGridView.setData(news);
        return dataGridView;
    }

    @Override
    public void addNews(NewsVo newsVo) {
        newsMapper.insert(newsVo);
    }

    @Override
    public void updateNews(NewsVo newsVo) {
        newsMapper.updateById(newsVo);
    }

    @Override
    public void deleteNews(Integer newsid) {
        newsMapper.deleteById(newsid);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        newsMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public News queryNewsById(Integer id) {
        return newsMapper.selectById(id);
    }
}
