package com.zjh.recording.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjh.recording.system.user.dto.UserShopRelSaveDTO;
import com.zjh.recording.system.user.dto.UserShopRelUpdateDTO;
import com.zjh.recording.system.user.dto.UserShopRelPageDTO;
import com.zjh.recording.system.user.entity.UserShopRelEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.recording.system.user.vo.UserShopRelVO;

import java.util.List;

/**
 * Description: 用户店铺关联表 服务类
 * @Author: zjh
 * @Date: 2022-05-10
 */
public interface UserShopRelService extends IService<UserShopRelEntity> {

        /**
         * 新增
         * @param dto
         * @throws Exception
         */
        boolean save(UserShopRelSaveDTO dto);

        /**
         * 更新指定信息
         * @param dto
         */
        boolean update(UserShopRelUpdateDTO dto);

        /**
         * 根据id获取指定信息
         * @param id
         * @return
         */
        UserShopRelVO get(Long id);

        /**
         * 根据条件分页获取信息列表
         * @param dto
         * @return
         */
        IPage page(UserShopRelPageDTO dto);

        /**
         * 根据ids删除
         * @return
         */
        boolean removeByIds(List<Long> ids);
}
