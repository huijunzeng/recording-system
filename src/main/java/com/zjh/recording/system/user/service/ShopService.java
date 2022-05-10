package com.zjh.recording.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjh.recording.system.user.dto.ShopSaveDTO;
import com.zjh.recording.system.user.dto.ShopUpdateDTO;
import com.zjh.recording.system.user.dto.ShopPageDTO;
import com.zjh.recording.system.user.entity.ShopEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.recording.system.user.vo.ShopVO;

import java.util.List;

/**
 * Description: 店铺表 服务类
 * @Author: zjh
 * @Date: 2022-05-10
 */
public interface ShopService extends IService<ShopEntity> {

        /**
         * 新增
         * @param dto
         * @throws Exception
         */
        boolean save(ShopSaveDTO dto);

        /**
         * 更新指定信息
         * @param dto
         */
        boolean update(ShopUpdateDTO dto);

        /**
         * 根据id获取指定信息
         * @param id
         * @return
         */
        ShopVO get(Long id);

        /**
         * 根据条件分页获取信息列表
         * @param dto
         * @return
         */
        IPage page(ShopPageDTO dto);

        /**
         * 根据ids删除
         * @return
         */
        boolean removeByIds(List<Long> ids);
}
