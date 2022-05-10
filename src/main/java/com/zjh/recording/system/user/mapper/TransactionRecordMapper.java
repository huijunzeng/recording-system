package com.zjh.recording.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.recording.system.user.dto.TransactionRecordSaveDTO;
import com.zjh.recording.system.user.dto.TransactionRecordPageDTO;
import com.zjh.recording.system.user.entity.TransactionRecordEntity;

import java.util.List;

/**
 * Description: 交易记录表 Mapper 接口
 * @Author: zjh
 * @Date: 2022-05-10
*/
public interface TransactionRecordMapper extends BaseMapper<TransactionRecordEntity> {

    //void insert(TransactionRecordSaveDTO dto);

    List list(TransactionRecordPageDTO dto);
}
