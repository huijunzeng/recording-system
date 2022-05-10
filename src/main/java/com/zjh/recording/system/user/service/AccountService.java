package com.zjh.recording.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.recording.system.user.dto.AccountPageDTO;
import com.zjh.recording.system.user.dto.AccountSaveDTO;
import com.zjh.recording.system.user.dto.AccountUpdateDTO;
import com.zjh.recording.system.user.entity.AccountEntity;
import com.zjh.recording.system.user.vo.AccountVO;

import java.util.List;

/**
 * Description: 账号信息表 服务类
 * @Author: zjh
 * @Date: 2022-05-10
 */
public interface AccountService extends IService<AccountEntity> {

        /**
         * 新增
         * @param dto
         * @throws Exception
         */
        boolean save(AccountSaveDTO dto);

        /**
         * 更新指定信息
         * @param dto
         */
        boolean update(AccountUpdateDTO dto);

        /**
         * 根据id获取指定信息
         * @param id
         * @return
         */
        AccountVO get(Long id);

        /**
         * 根据条件分页获取信息列表
         * @param dto
         * @return
         */
        IPage page(AccountPageDTO dto);

        /**
         * 根据ids删除
         * @return
         */
        boolean removeByIds(List<Long> ids);
}
