package com.zjh.recording.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.recording.system.user.dto.ShopSaveDTO;
import com.zjh.recording.system.user.dto.ShopPageDTO;
import com.zjh.recording.system.user.entity.ShopEntity;

import java.util.List;

/**
 * Description: 店铺表 Mapper 接口
 * @Author: zjh
 * @Date: 2022-05-10
*/
public interface ShopMapper extends BaseMapper<ShopEntity> {

    //void insert(ShopSaveDTO dto);

    List list(ShopPageDTO dto);
}
