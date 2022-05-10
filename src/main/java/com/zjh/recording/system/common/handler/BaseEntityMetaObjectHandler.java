package com.zjh.recording.system.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zjh.recording.system.common.enums.LogicEnum;
import com.zjh.recording.system.common.util.DateUtil;
import com.zjh.recording.system.common.entity.BaseEntity;
import com.zjh.recording.system.common.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 实体类公共字段自动填充处理类  mybatis-plus
 * @Author: ZJH
 * @Date: 2020/3/11 13:18
 */

@Component
@Slf4j
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {

    private Long getCurrentUserId() {
        String userId = StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUserId(), "0");
        return Long.parseLong(userId);
    }

    private String getCurrentUsername() {
        String username = StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUsername(), "system");
        return username;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, BaseEntity.CREATED_BY, Long.class, getCurrentUserId());
            this.strictInsertFill(metaObject, BaseEntity.CREATED_USERNAME, String.class, getCurrentUsername());
            this.strictInsertFill(metaObject, BaseEntity.UPDATED_BY, Long.class, getCurrentUserId());
            this.strictInsertFill(metaObject, BaseEntity.UPDATED_USERNAME, String.class, getCurrentUsername());
            this.strictInsertFill(metaObject, BaseEntity.CREATED_TIME, Date.class, DateUtil.instantToDateConverter(ZonedDateTime.now().toInstant()));
            this.strictInsertFill(metaObject, BaseEntity.UPDATED_TIME, Date.class, DateUtil.instantToDateConverter(ZonedDateTime.now().toInstant()));
            this.strictInsertFill(metaObject, BaseEntity.DELETED, Integer.class, LogicEnum.NO.getCode());
            this.strictInsertFill(metaObject, BaseEntity.VERSION, Long.class, 1L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, BaseEntity.UPDATED_BY, Long.class, getCurrentUserId());
            this.strictInsertFill(metaObject, BaseEntity.UPDATED_USERNAME, String.class, getCurrentUsername());
            this.strictInsertFill(metaObject, BaseEntity.UPDATED_TIME, Date.class, DateUtil.instantToDateConverter(ZonedDateTime.now().toInstant()));
            this.strictInsertFill(metaObject, BaseEntity.DELETED, Integer.class, LogicEnum.NO.getCode());
    }
}
