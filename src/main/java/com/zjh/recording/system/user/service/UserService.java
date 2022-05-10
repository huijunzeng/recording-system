package com.zjh.recording.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.recording.system.user.dto.*;
import com.zjh.recording.system.user.entity.UserEntity;
import com.zjh.recording.system.user.vo.*;

import java.util.List;

public interface UserService extends IService<UserEntity> {

    /**
     * 新增用户信息
     * @param userSaveDTO
     * @return
     */
    boolean save(UserSaveDTO userSaveDTO);

    /**
     * 更新指定用户信息
     * @param userUpdateDTO
     */
    boolean update(UserUpdateDTO userUpdateDTO);

    /**
     * 更新指定用户状态
     * @param dto
     * @return
     */
    boolean statusUpdate(UserUpdateStatusDTO dto);

    /**
     * 根据用户id获取指定用户信息
     * @param id
     * @return
     */
    UserVO get(Long id);

    /**
     * 根据用户名/用户手机号码（唯一标识）获取指定用户信息
     * @param uniqueId
     * @return
     */
    UserDetailsVO getByUniqueId(String uniqueId);

    /**
     * 根据条件获取用户信息列表
     * @param dto
     * @return
     */
    List<UserVO> list(UserListDTO dto);

    /**
     * 根据条件分页获取用户信息列表
     * @param userPageDTO
     * @return
     */
    IPage page(UserPageDTO userPageDTO);

    /**
     * 根据ids删除
     * @param ids
     * @return
     */
    boolean removeByIds(List<Long> ids);

    /**
     * 用户重置密码
     * @param dto
     * @param token
     * @return
     */
    boolean resetPassword(UserResetPasswordDTO dto, String token);

}
