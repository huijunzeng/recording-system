package com.zjh.recording.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjh.recording.system.user.dto.TransactionRecordSaveDTO;
import com.zjh.recording.system.user.dto.TransactionRecordUpdateDTO;
import com.zjh.recording.system.user.dto.TransactionRecordPageDTO;
import com.zjh.recording.system.user.entity.TransactionRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.recording.system.user.vo.TransactionRecordVO;

import java.util.List;

/**
 * Description: 交易记录表 服务类
 * @Author: zjh
 * @Date: 2022-05-10
 */
public interface TransactionRecordService extends IService<TransactionRecordEntity> {

        /**
         * 新增
         * @param dto
         * @throws Exception
         */
        boolean save(TransactionRecordSaveDTO dto);

        /**
         * 更新指定信息
         * @param dto
         */
        boolean update(TransactionRecordUpdateDTO dto);

        /**
         * 根据id获取指定信息
         * @param id
         * @return
         */
        TransactionRecordVO get(Long id);

        /**
         * 根据条件分页获取信息列表
         * @param dto
         * @return
         */
        IPage page(TransactionRecordPageDTO dto);

        /**
         * 根据ids删除
         * @return
         */
        boolean removeByIds(List<Long> ids);
}
