package com.zjh.recording.system.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjh.recording.system.common.vo.Result;
import com.zjh.recording.system.user.dto.UserShopRelPageDTO;
import com.zjh.recording.system.user.dto.UserShopRelRemoveDTO;
import com.zjh.recording.system.user.dto.UserShopRelSaveDTO;
import com.zjh.recording.system.user.dto.UserShopRelUpdateDTO;
import com.zjh.recording.system.user.service.UserShopRelService;
import com.zjh.recording.system.user.vo.UserShopRelVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

/**
 * Description: 用户店铺关联表 api
 * @Author: zjh
 * @Date: 2022-05-10
 */
@RestController
@RequestMapping("/userShopRel")
@Api(value = "UserShopRelEntity", tags = {"用户店铺关联表 操作restful接口"})
@Slf4j
@Validated
public class UserShopRelController {

         @Autowired
         private UserShopRelService service;

         @ApiOperation(value = "新增", notes = "新增")
         @PostMapping("/save") public Result<Boolean> save(@Validated @RequestBody UserShopRelSaveDTO dto) {
         return Result.status(service.save(dto));
         }

         @ApiOperation(value = "修改", notes = "更新指定信息")
         @PostMapping(("/update")) public Result<Boolean> update(@Validated @RequestBody UserShopRelUpdateDTO dto) {
         return Result.status(service.update(dto));
         }

         @ApiOperation(value = "获取", notes = "根据id获取指定信息")
         @GetMapping("/{id}") public Result<UserShopRelVO> get(@PathVariable Long id) {
         return Result.success(service.get(id));
         }

         @ApiOperation(value = "分页搜索", notes = "根据条件分页获取信息列表")
         @PostMapping("/page") public Result<IPage> page(@RequestBody UserShopRelPageDTO dto) {
         return Result.success(service.page(dto));
         }

         @ApiOperation(value = "删除", notes = "根据ids集合删除")
         @PostMapping("/remove") public Result<Boolean> remove(@RequestBody UserShopRelRemoveDTO dto) {
         return Result.status(service.removeByIds(dto.getIds()));
         }
}
