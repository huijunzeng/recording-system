package com.zjh.recording.system.user.service.impl;

import com.zjh.recording.system.user.dto.UserShopRelSaveDTO;
import com.zjh.recording.system.user.dto.UserShopRelUpdateDTO;
import com.zjh.recording.system.user.dto.UserShopRelPageDTO;
import com.zjh.recording.system.user.entity.UserShopRelEntity;
import com.zjh.recording.system.user.mapper.UserShopRelMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.recording.system.user.service.UserShopRelService;
import com.zjh.recording.system.user.vo.UserShopRelVO;
import org.springframework.stereotype.Service;
import com.zjh.recording.system.common.enums.LogicEnum;
import com.zjh.recording.system.common.util.BeanConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 用户店铺关联表 服务实现类
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Service
@Slf4j
public class UserShopRelServiceImpl extends ServiceImpl<UserShopRelMapper, UserShopRelEntity> implements UserShopRelService {

    @Autowired
    private UserShopRelMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserShopRelSaveDTO dto) {
        UserShopRelEntity entity = BeanConverter.copyForBean(dto, UserShopRelEntity::new);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UserShopRelUpdateDTO dto) {
        UserShopRelEntity entity = BeanConverter.copyForBean(dto, UserShopRelEntity::new);
        return super.updateById(entity);
    }

    @Override
    public UserShopRelVO get(Long id) {
        UserShopRelEntity entity = super.getById(id);
        UserShopRelVO vo = BeanConverter.copyForBean(entity, UserShopRelVO::new);
        return vo;
    }

    @Override
    public IPage page(UserShopRelPageDTO dto) {
        Page page = dto.getPage();
        LambdaQueryWrapper<UserShopRelEntity> queryWrapper = dto.build();
        queryWrapper.eq(UserShopRelEntity::getDeleted, LogicEnum.NO.getCode());
        queryWrapper.orderByDesc(UserShopRelEntity::getCreatedTime);
        IPage<UserShopRelEntity> iPage = super.page(page, queryWrapper);
        IPage<UserShopRelVO> convertVo = iPage.convert(entity -> BeanConverter.copyForBean(entity, UserShopRelVO::new));
        return convertVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(List<Long> ids) {
        return super.removeByIds(ids);
    }
}
