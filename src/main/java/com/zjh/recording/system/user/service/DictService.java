package com.zjh.recording.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjh.recording.system.user.dto.DictSaveDTO;
import com.zjh.recording.system.user.dto.DictUpdateDTO;
import com.zjh.recording.system.user.dto.DictPageDTO;
import com.zjh.recording.system.user.vo.DictVO;
import com.zjh.recording.system.user.entity.DictEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Description: 字典表 服务类
 * @Author: zjh
 * @Date: 2022-05-11
 */
public interface DictService extends IService<DictEntity> {

        /**
         * 新增
         * @param dto
         * @throws Exception
         */
        boolean save(DictSaveDTO dto);

        /**
         * 更新指定信息
         * @param dto
         */
        boolean update(DictUpdateDTO dto);

        /**
         * 根据id获取指定信息
         * @param id
         * @return
         */
        DictVO get(Long id);

        /**
         * 根据条件分页获取信息列表
         * @param dto
         * @return
         */
        IPage page(DictPageDTO dto);

        /**
         * 根据ids删除
         * @return
         */
        boolean removeByIds(List<Long> ids);
}



