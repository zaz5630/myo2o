package cn.azzhu.myo2o.service.impl;

import cn.azzhu.myo2o.entity.LogInfo;
import cn.azzhu.myo2o.mapper.sys.LogInfoMapper;
import cn.azzhu.myo2o.service.LogInfoService;
import cn.azzhu.myo2o.utils.DataGridView;
import cn.azzhu.myo2o.vo.LogInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-03 22:21:30
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        QueryWrapper<LogInfo> queryWrapper = new QueryWrapper<>();
        //封装查询条件
        if(!StringUtils.isEmpty(logInfoVo.getLoginname())) {
            queryWrapper.like("loginname",logInfoVo.getLoginname());
        }
        if(!StringUtils.isEmpty(logInfoVo.getLoginip())) {
            queryWrapper.like("loginip",logInfoVo.getLoginip());
        }

        if(!StringUtils.isEmpty(logInfoVo.getStartTime())) {
            queryWrapper.ge("logintime",logInfoVo.getStartTime());
        }

        if(!StringUtils.isEmpty(logInfoVo.getEndTime())) {
            queryWrapper.le("logintime",logInfoVo.getEndTime());
        }
        queryWrapper.orderByDesc("logintime");
        List<LogInfo> logInfos = logInfoMapper.selectList(queryWrapper);
        PageInfo<LogInfo> pageInfo = new PageInfo<>();
        DataGridView<LogInfo> dataGridView = new DataGridView<>();
        dataGridView.setCount(pageInfo.getTotal());
        dataGridView.setData(logInfos);
        return dataGridView;
    }

    @Override
    public void addLogInfo(LogInfo logInfo) {
        logInfoMapper.insert(logInfo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoid) {
        logInfoMapper.deleteById(logInfoid);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        logInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
