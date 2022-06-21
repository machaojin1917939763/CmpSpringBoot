package com.machaojin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.common.Result;
import com.machaojin.domain.BizProduct;
import com.machaojin.service.impl.BizProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 马超金
 * @version 1.0
 * @date 2022/6/21 09:31
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private BizProductServiceImpl productService;

    /**
     * 产品的信息查询
     * @param page 初始页
     * @param pageSize 每页大小
     * @return 返回分页查询的结果
     */
    @GetMapping
    public Result<Page<BizProduct>> getProductList(Integer page,Integer pageSize){
            Page<BizProduct> productPage = new Page<>(page,pageSize);
            productService.page(productPage);
            return Result.success(productPage);
    }

    /**
     * 删除成功
     * @param id 产品的id
     * @return 返回删除结果
     */
    @DeleteMapping("{id}")
    public Result<String> delete(@PathVariable String id){
        productService.removeById(id);
        return Result.success("删除成功");
    }
}
