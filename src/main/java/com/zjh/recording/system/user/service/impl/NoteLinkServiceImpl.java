package com.zjh.recording.system.user.service.impl;

import com.zjh.recording.system.user.dto.NoteLinkSaveDTO;
import com.zjh.recording.system.user.dto.NoteLinkUpdateDTO;
import com.zjh.recording.system.user.dto.NoteLinkPageDTO;
import com.zjh.recording.system.user.entity.NoteLinkEntity;
import com.zjh.recording.system.user.mapper.NoteLinkMapper;
import com.zjh.recording.system.user.service.NoteLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.recording.system.user.vo.NoteLinkVO;
import org.springframework.stereotype.Service;
import com.zjh.recording.system.common.enums.LogicEnum;
import com.zjh.recording.system.common.util.BeanConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 笔记链接表 服务实现类
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Service
@Slf4j
public class NoteLinkServiceImpl extends ServiceImpl<NoteLinkMapper, NoteLinkEntity> implements NoteLinkService {

    @Autowired
    private NoteLinkMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(NoteLinkSaveDTO dto) {
        NoteLinkEntity entity = BeanConverter.copyForBean(dto, NoteLinkEntity::new);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(NoteLinkUpdateDTO dto) {
        NoteLinkEntity entity = BeanConverter.copyForBean(dto, NoteLinkEntity::new);
        return super.updateById(entity);
    }

    @Override
    public NoteLinkVO get(Long id) {
        NoteLinkEntity entity = super.getById(id);
        NoteLinkVO vo = BeanConverter.copyForBean(entity, NoteLinkVO::new);
        return vo;
    }

    @Override
    public IPage page(NoteLinkPageDTO dto) {
        Page page = dto.getPage();
        LambdaQueryWrapper<NoteLinkEntity> queryWrapper = dto.build();
        queryWrapper.eq(NoteLinkEntity::getDeleted, LogicEnum.NO.getCode());
        queryWrapper.orderByDesc(NoteLinkEntity::getCreatedTime);
        IPage<NoteLinkEntity> iPage = super.page(page, queryWrapper);
        IPage<NoteLinkVO> convertVo = iPage.convert(entity -> BeanConverter.copyForBean(entity, NoteLinkVO::new));
        return convertVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(List<Long> ids) {
        return super.removeByIds(ids);
    }

}
