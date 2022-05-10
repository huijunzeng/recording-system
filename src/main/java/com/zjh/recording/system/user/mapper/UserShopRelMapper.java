package com.zjh.recording.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.recording.system.user.dto.UserShopRelSaveDTO;
import com.zjh.recording.system.user.dto.UserShopRelPageDTO;
import com.zjh.recording.system.user.entity.UserShopRelEntity;

import java.util.List;

/**
 * Description: 用户店铺关联表 Mapper 接口
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
public interface UserShopRelMapper extends BaseMapper<UserShopRelEntity> {

    //void insert(UserShopRelSaveDTO dto);

    List list(UserShopRelPageDTO dto);
}
