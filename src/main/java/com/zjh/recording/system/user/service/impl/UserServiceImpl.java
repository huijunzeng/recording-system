package com.zjh.recording.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.recording.system.common.enums.LogicEnum;
import com.zjh.recording.system.common.exception.BusinessException;
import com.zjh.recording.system.common.util.BeanConverter;
import com.zjh.recording.system.user.dto.*;
import com.zjh.recording.system.user.entity.UserEntity;
import com.zjh.recording.system.user.exception.UserExceptionEnums;
import com.zjh.recording.system.user.mapper.UserMapper;
import com.zjh.recording.system.user.service.UserService;
import com.zjh.recording.system.user.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserSaveDTO dto) {
        checkValidParam(null, dto.getUsername());
        UserEntity userEntity = BeanConverter.copyForBean(dto, UserEntity::new);
        if (StringUtils.isNotBlank(userEntity.getPassword())){
            // 密码加密
            userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        }
        return super.save(userEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UserUpdateDTO dto) {
        checkValidParam(dto.getId(), dto.getUsername());
        UserEntity userEntity = BeanConverter.copyForBean(dto, UserEntity::new);
        return super.updateById(userEntity);
    }

    @Override
    public boolean statusUpdate(UserUpdateStatusDTO dto) {
        LambdaUpdateWrapper<UserEntity> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.set(UserEntity::getStatus, dto.getStatus());
        queryWrapper.in(UserEntity::getId, dto.getIds());
        return super.update(queryWrapper);
    }

    @Override
    public UserVO get(Long id) {
        UserEntity entity = super.getById(id);
        return BeanConverter.copyForBean(entity, UserVO::new);
    }

    @Override
    public UserDetailsVO getByUniqueId(String uniqueId) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, uniqueId);
        UserEntity entity = super.getOne(queryWrapper);
        if (Objects.isNull(entity)) {
            return null;
        }
        UserDetailsVO vo = BeanConverter.copyForBean(entity, UserDetailsVO::new);
        return vo;
    }

    @Override
    public List<UserVO> list(UserListDTO dto) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.orderByDesc(UserEntity::getCreatedTime);
        List<UserEntity> entities = super.list(queryWrapper);
        List<UserVO> vos = BeanConverter.copyForList(entities, UserVO::new);
        return vos;
    }

    @Override
    public IPage page(UserPageDTO dto) {
        Page page = dto.getPage();
        LambdaQueryWrapper<UserEntity> queryWrapper = dto.build();
        if (StringUtils.isNotBlank(dto.getCreatedUsername())) {
            LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.like(UserEntity::getUsername, dto.getCreatedUsername());
            List<UserEntity> userEntities = this.list(lambdaQueryWrapper);
            if (!CollectionUtils.isEmpty(userEntities)) {
                Set<Long> userIds = userEntities.stream().collect(Collectors.mapping(UserEntity::getId, Collectors.toSet()));
                queryWrapper.in(UserEntity::getCreatedBy, userIds);
            }
        }
        queryWrapper.like(StringUtils.isNotBlank(dto.getUsername()), UserEntity::getUsername, dto.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(dto.getSex()), UserEntity::getSex, dto.getSex());
        queryWrapper.eq(dto.getStatus()!=null && dto.getStatus()>=0, UserEntity::getStatus, dto.getStatus());
        queryWrapper.eq(UserEntity::getDeleted, LogicEnum.NO.getCode());
        queryWrapper.orderByDesc(UserEntity::getCreatedTime);
        IPage<UserEntity> iPage = super.page(page, queryWrapper);
        IPage<UserPageVO> userVOIPage = iPage.convert(e -> BeanConverter.copyForBean(e, UserPageVO::new));
        return userVOIPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(List<Long> ids) {
        ids.forEach(id -> {
            super.removeById(id);
        });
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resetPassword(UserResetPasswordDTO dto, String token) {
        UserEntity entity = super.getById(dto.getUserId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(dto.getOldPassword(), entity.getPassword())) {
            throw new BusinessException(UserExceptionEnums.OLDPASSWORD_WRONG);
        }
        entity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        super.updateById(entity);
        //authorizationServerApi.logout();
        return true;
    }

    private void checkValidParam(Long id, String username) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, username);
        List<UserEntity> entities = super.list(queryWrapper);
        if (id!=null && id>0) {
            if (!CollectionUtils.isEmpty(entities) && !entities.get(0).getId().equals(id)) {
                throw new BusinessException(UserExceptionEnums.DUPLICATE_USERNAME);
            }
        } else {
            if (!CollectionUtils.isEmpty(entities)) {
                throw new BusinessException(UserExceptionEnums.DUPLICATE_USERNAME);
            }
        }
    }
}
