package com.zjh.recording.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjh.recording.system.user.dto.NoteLinkSaveDTO;
import com.zjh.recording.system.user.dto.NoteLinkUpdateDTO;
import com.zjh.recording.system.user.dto.NoteLinkPageDTO;
import com.zjh.recording.system.user.entity.NoteLinkEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.recording.system.user.vo.NoteLinkVO;

import java.util.List;

/**
 * Description: 笔记链接表 服务类
 * @Author: zjh
 * @Date: 2022-05-10
 */
public interface NoteLinkService extends IService<NoteLinkEntity> {

        /**
         * 新增
         * @param dto
         * @throws Exception
         */
        boolean save(NoteLinkSaveDTO dto);

        /**
         * 更新指定信息
         * @param dto
         */
        boolean update(NoteLinkUpdateDTO dto);

        /**
         * 根据id获取指定信息
         * @param id
         * @return
         */
        NoteLinkVO get(Long id);

        /**
         * 根据条件分页获取信息列表
         * @param dto
         * @return
         */
        IPage page(NoteLinkPageDTO dto);

        /**
         * 根据ids删除
         * @return
         */
        boolean removeByIds(List<Long> ids);
}
