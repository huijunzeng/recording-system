package com.zjh.recording.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.recording.system.user.dto.DictSaveDTO;
import com.zjh.recording.system.user.dto.DictPageDTO;
import com.zjh.recording.system.user.entity.DictEntity;

import java.util.List;

/**
 * Description: 字典表 Mapper 接口
 * @Author: zjh
 * @Date: 2022-05-11
*/
public interface DictMapper extends BaseMapper<DictEntity> {

    //void insert(DictSaveDTO dto);

    List list(DictPageDTO dto);
}
