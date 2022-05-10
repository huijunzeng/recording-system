package com.zjh.recording.system.user.service.impl;

import com.zjh.recording.system.common.util.BeanConverter;
import com.zjh.recording.system.user.dto.DictSaveDTO;
import com.zjh.recording.system.user.dto.DictUpdateDTO;
import com.zjh.recording.system.user.dto.DictPageDTO;
import com.zjh.recording.system.user.entity.DictEntity;
import com.zjh.recording.system.user.mapper.DictMapper;
import com.zjh.recording.system.user.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.recording.system.user.vo.DictVO;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 字典表 服务实现类
 *
 * @Author: zjh
 * @Date: 2022-05-11
 */
@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService {

    @Autowired
    private DictMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DictSaveDTO dto) {
        DictEntity entity = BeanConverter.copyForBean(dto, DictEntity::new);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(DictUpdateDTO dto) {
        DictEntity entity = BeanConverter.copyForBean(dto, DictEntity::new);
        return super.updateById(entity);
    }

    @Override
    public DictVO get(Long id) {
        DictEntity entity = super.getById(id);
        DictVO vo = BeanConverter.copyForBean(entity, DictVO::new);
        return vo;
    }

    @Override
    public IPage page(DictPageDTO dto) {
        Page page = dto.getPage();
        LambdaQueryWrapper<DictEntity> queryWrapper = dto.build();
        queryWrapper.orderByDesc(DictEntity::getCreatedTime);
        IPage<DictEntity> iPage = super.page(page, queryWrapper);
        IPage<DictVO> convertVo = iPage.convert(entity -> BeanConverter.copyForBean(entity, DictVO::new));
        return convertVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(List<Long> ids) {
        return super.removeByIds(ids);
    }
}
