
package com.zjh.recording.system.user.controller;

import com.zjh.recording.system.common.vo.Result;
import com.zjh.recording.system.user.dto.AccountPageDTO;
import com.zjh.recording.system.user.dto.AccountRemoveDTO;
import com.zjh.recording.system.user.dto.AccountSaveDTO;
import com.zjh.recording.system.user.dto.AccountUpdateDTO;
import com.zjh.recording.system.user.service.AccountService;
import com.zjh.recording.system.user.vo.AccountVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 账号信息表 api
 * @Author: zjh
 * @Date: 2022-05-10
 */
@RestController
@RequestMapping("/account")
@Api(value = "AccountEntity", tags = {"账号信息表 操作restful接口"})
@Slf4j
@Validated
public class AccountController {

         @Autowired
         private AccountService service;

         @ApiOperation(value = "新增", notes = "新增")
         @PostMapping("/save") public Result<Boolean> save(@Validated @RequestBody AccountSaveDTO dto) {
         return Result.status(service.save(dto));
         }

         @ApiOperation(value = "修改", notes = "更新指定信息")
         @PostMapping(("/update")) public Result<Boolean> update(@Validated @RequestBody AccountUpdateDTO dto) {
         return Result.status(service.update(dto));
         }

         @ApiOperation(value = "获取", notes = "根据id获取指定信息")
         @GetMapping("/{id}") public Result<AccountVO> get(@PathVariable Long id) {
         return Result.success(service.get(id));
         }

         @ApiOperation(value = "分页搜索", notes = "根据条件分页获取信息列表")
         @PostMapping("/page") public Result<IPage> page(@RequestBody AccountPageDTO dto) {
         return Result.success(service.page(dto));
         }

         @ApiOperation(value = "删除", notes = "根据ids集合删除")
         @PostMapping("/remove") public Result<Boolean> remove(@RequestBody AccountRemoveDTO dto) {
         return Result.status(service.removeByIds(dto.getIds()));
         }
}
