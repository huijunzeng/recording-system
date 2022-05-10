package com.zjh.recording.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.recording.system.user.dto.NoteLinkSaveDTO;
import com.zjh.recording.system.user.dto.NoteLinkPageDTO;
import com.zjh.recording.system.user.entity.NoteLinkEntity;

import java.util.List;

/**
 * Description: 笔记链接表 Mapper 接口
 * @Author: zjh
 * @Date: 2022-05-10
*/
public interface NoteLinkMapper extends BaseMapper<NoteLinkEntity> {

    //void insert(NoteLinkSaveDTO dto);

    List list(NoteLinkPageDTO dto);
}
