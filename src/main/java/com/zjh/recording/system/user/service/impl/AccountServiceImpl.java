package com.zjh.recording.system.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.recording.system.common.enums.LogicEnum;
import com.zjh.recording.system.common.util.BeanConverter;
import com.zjh.recording.system.user.dto.AccountPageDTO;
import com.zjh.recording.system.user.dto.AccountSaveDTO;
import com.zjh.recording.system.user.dto.AccountUpdateDTO;
import com.zjh.recording.system.user.entity.AccountEntity;
import com.zjh.recording.system.user.mapper.AccountMapper;
import com.zjh.recording.system.user.service.AccountService;
import com.zjh.recording.system.user.vo.AccountVO;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 账号信息表 服务实现类
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements AccountService {

    @Autowired
    private AccountMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(AccountSaveDTO dto) {
        AccountEntity entity = BeanConverter.copyForBean(dto, AccountEntity::new);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(AccountUpdateDTO dto) {
        AccountEntity entity = BeanConverter.copyForBean(dto, AccountEntity::new);
        return super.updateById(entity);
    }

    @Override
    public AccountVO get(Long id) {
        AccountEntity entity = super.getById(id);
        AccountVO vo = BeanConverter.copyForBean(entity, AccountVO::new);
        return vo;
    }

    @Override
    public IPage page(AccountPageDTO dto) {
        Page page = dto.getPage();
        LambdaQueryWrapper<AccountEntity> queryWrapper = dto.build();
        queryWrapper.eq(StringUtils.isNotBlank(dto.getXhsId()), AccountEntity::getXhsId, dto.getXhsId());
        queryWrapper.eq(AccountEntity::getDeleted, LogicEnum.NO.getCode());
        queryWrapper.orderByDesc(AccountEntity::getCreatedTime);
        IPage<AccountEntity> iPage = super.page(page, queryWrapper);
        IPage<AccountVO> convertVo = iPage.convert(entity -> BeanConverter.copyForBean(entity, AccountVO::new));
        return convertVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(List<Long> ids) {
        return super.removeByIds(ids);
    }
}
