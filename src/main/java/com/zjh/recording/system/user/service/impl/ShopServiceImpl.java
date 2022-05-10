package com.zjh.recording.system.user.service.impl;

import com.zjh.recording.system.user.dto.ShopSaveDTO;
import com.zjh.recording.system.user.dto.ShopUpdateDTO;
import com.zjh.recording.system.user.dto.ShopPageDTO;
import com.zjh.recording.system.user.entity.ShopEntity;
import com.zjh.recording.system.user.mapper.ShopMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.recording.system.user.service.ShopService;
import com.zjh.recording.system.user.vo.ShopVO;
import org.springframework.stereotype.Service;
import com.zjh.recording.system.common.enums.LogicEnum;
import com.zjh.recording.system.common.util.BeanConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 店铺表 服务实现类
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Service
@Slf4j
public class ShopServiceImpl extends ServiceImpl<ShopMapper, ShopEntity> implements ShopService {

    @Autowired
    private ShopMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ShopSaveDTO dto) {
        ShopEntity entity = BeanConverter.copyForBean(dto, ShopEntity::new);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ShopUpdateDTO dto) {
        ShopEntity entity = BeanConverter.copyForBean(dto, ShopEntity::new);
        return super.updateById(entity);
    }

    @Override
    public ShopVO get(Long id) {
        ShopEntity entity = super.getById(id);
        ShopVO vo = BeanConverter.copyForBean(entity, ShopVO::new);
        return vo;
    }

    @Override
    public IPage page(ShopPageDTO dto) {
        Page page = dto.getPage();
        LambdaQueryWrapper<ShopEntity> queryWrapper = dto.build();
        queryWrapper.eq(StringUtils.isNotBlank(dto.getName()), ShopEntity::getName, dto.getName());
        queryWrapper.eq(ShopEntity::getDeleted, LogicEnum.NO.getCode());
        queryWrapper.orderByDesc(ShopEntity::getCreatedTime);
        IPage<ShopEntity> iPage = super.page(page, queryWrapper);
        IPage<ShopVO> convertVo = iPage.convert(entity -> BeanConverter.copyForBean(entity, ShopVO::new));
        return convertVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(List<Long> ids) {
        return super.removeByIds(ids);
    }
}
