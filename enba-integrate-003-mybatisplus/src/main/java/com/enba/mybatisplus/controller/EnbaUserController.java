package com.enba.mybatisplus.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enba.mybatisplus.entity.EnbaUser;
import com.enba.mybatisplus.service.EnbaUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (EnbaUser)表控制层
 *
 * @author dingjunhua
 * @since 2024-08-02 01:03:58
 */
@RestController
@RequestMapping("/enbaUser")
public class EnbaUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private EnbaUserService enbaUserService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param enbaUser 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<EnbaUser> page, EnbaUser enbaUser) {
        return success(this.enbaUserService.page(page, new QueryWrapper<>(enbaUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.enbaUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param enbaUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody EnbaUser enbaUser) {
        return success(this.enbaUserService.save(enbaUser));
    }

    /**
     * 修改数据
     *
     * @param enbaUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody EnbaUser enbaUser) {
        return success(this.enbaUserService.updateById(enbaUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.enbaUserService.removeByIds(idList));
    }
}