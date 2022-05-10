package com.zjh.recording.system.user.controller;


import com.zjh.recording.system.common.vo.Result;
import com.zjh.recording.system.user.dto.*;
import com.zjh.recording.system.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/user")
@Api(value = "user", tags = {"用户操作接口"})
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody UserSaveDTO dto) {
        return Result.status(service.save(dto));
    }

    @ApiOperation(value = "修改用户", notes = "更新指定用户信息")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody UserUpdateDTO dto) {
        return Result.status(service.update(dto));
    }

    @ApiOperation(value = "修改用户状态", notes = "更新指定用户状态")
    @PostMapping("/status/update")
    public Result statusUpdate(@Validated @RequestBody UserUpdateStatusDTO dto) {
        return Result.status(service.statusUpdate(dto));
    }

    @ApiOperation(value = "获取用户", notes = "根据用户id获取指定用户信息")
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        return Result.success(service.get(id));
    }

    @ApiOperation(value = "获取用户", notes = "根据用户账号/用户手机号码（唯一标识）获取指定用户信息")
    @GetMapping("/getByUniqueId")
    public Result getByUniqueId(@NotBlank(message = "唯一标识不能为空") String uniqueId) {
        return Result.success(service.getByUniqueId(uniqueId));
    }

    @ApiOperation(value = "搜索用户", notes = "根据条件获取用户信息列表")
    @PostMapping("/list")
    public Result list(@RequestBody UserListDTO dto) {
        return Result.success(service.list(dto));
    }

    @ApiOperation(value = "分页搜索用户", notes = "根据条件分页获取用户信息列表")
    @PostMapping("/page")
    public Result page(@RequestBody UserPageDTO dto) {
        return Result.success(service.page(dto));
    }

    @ApiOperation(value = "删除", notes = "根据ids集合删除")
    @PostMapping("/remove")
    public Result<Boolean> remove(@RequestBody UserRemoveDTO dto) {
        return Result.status(service.removeByIds(dto.getIds()));
    }

    @ApiOperation(value = "用户重置密码(成功后推出重新登录获取token)", notes = "用户重置密码")
    @PostMapping("/resetPassword")
    public Result<Boolean> resetPassword(@RequestBody UserResetPasswordDTO dto, HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return Result.status(service.resetPassword(dto, token));
    }
}
