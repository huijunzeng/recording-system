package com.zjh.recording.system.user.service.impl;

import com.zjh.recording.system.user.dto.TransactionRecordSaveDTO;
import com.zjh.recording.system.user.dto.TransactionRecordUpdateDTO;
import com.zjh.recording.system.user.dto.TransactionRecordPageDTO;
import com.zjh.recording.system.user.entity.TransactionRecordEntity;
import com.zjh.recording.system.user.mapper.TransactionRecordMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.recording.system.user.service.TransactionRecordService;
import com.zjh.recording.system.user.vo.TransactionRecordVO;
import org.springframework.stereotype.Service;
import com.zjh.recording.system.common.enums.LogicEnum;
import com.zjh.recording.system.common.util.BeanConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 交易记录表 服务实现类
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Service
@Slf4j
public class TransactionRecordServiceImpl extends ServiceImpl<TransactionRecordMapper, TransactionRecordEntity> implements TransactionRecordService {

    @Autowired
    private TransactionRecordMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(TransactionRecordSaveDTO dto) {
        TransactionRecordEntity entity = BeanConverter.copyForBean(dto, TransactionRecordEntity::new);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(TransactionRecordUpdateDTO dto) {
        TransactionRecordEntity entity = BeanConverter.copyForBean(dto, TransactionRecordEntity::new);
        return super.updateById(entity);
    }

    @Override
    public TransactionRecordVO get(Long id) {
        TransactionRecordEntity entity = super.getById(id);
        TransactionRecordVO vo = BeanConverter.copyForBean(entity, TransactionRecordVO::new);
        return vo;
    }

    @Override
    public IPage page(TransactionRecordPageDTO dto) {
        Page page = dto.getPage();
        LambdaQueryWrapper<TransactionRecordEntity> queryWrapper = dto.build();
        queryWrapper.eq(TransactionRecordEntity::getDeleted, LogicEnum.NO.getCode());
        queryWrapper.orderByDesc(TransactionRecordEntity::getCreatedTime);
        IPage<TransactionRecordEntity> iPage = super.page(page, queryWrapper);
        IPage<TransactionRecordVO> convertVo = iPage.convert(entity -> BeanConverter.copyForBean(entity, TransactionRecordVO::new));
        return convertVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(List<Long> ids) {
        return super.removeByIds(ids);
    }
}
