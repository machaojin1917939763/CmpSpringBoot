package com.machaojin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.common.Result;
import com.machaojin.domain.*;
import com.machaojin.service.impl.BizPartsServiceImpl;
import com.machaojin.service.impl.BizPartsrepbillServiceImpl;
import com.machaojin.service.impl.BizPartsrepertoryServiceImpl;
import com.machaojin.service.impl.SysCodeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 马超金
 * @version 1.0
 * @date 2022/6/15 17:05
 */

@Slf4j
@RestController
@RequestMapping("/parts")
public class PartsController {
    @Autowired
    private BizPartsServiceImpl partsService;
    @Autowired
    private BizPartsrepertoryServiceImpl bizPartsrepertoryService;
    @Autowired
    private SysCodeServiceImpl codeService;
    @Autowired
    private BizPartsrepbillServiceImpl bizPartsrepbillService;
    /**
     * 分页查询订单状态
     * @param page 初始页面
     * @param pageSize 页面大小
     * @param name 订单编码
     * @return 返回订单列表
     */
    @GetMapping
    public Result<Page<BizParts>> getOrderList(Integer page, Integer pageSize, String name){
        Page<BizParts> orderPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<BizParts> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name),BizParts::getPartsname,name);
        partsService.page(orderPage,lambdaQueryWrapper);
        return Result.success(orderPage);
    }

    /**
     * 添加配件信息
     * @param parts 配件信息
     * @return 返回操作结果
     */
    @PostMapping
    public Result<String> addParts(@RequestBody BizParts parts){
        partsService.save(parts);
        return Result.success("success");
    }

    /**
     * 删除配件信息
     * @param id 配件的id
     * @return 返回执行结果
     */
    @DeleteMapping("{id}")
    public Result<String> deleteParts(@PathVariable String id){
        log.info("id{}",id);
        partsService.removeById(id);
        return Result.success("success");
    }

    /**
     *  分页查询配件库流水账
     * @param page 初始页面
     * @param pageSize 分页大小
     * @param partsname 配件名称
     * @param type 出入库
     * @param name 出入库类型
     * @param billuser 出入库时间
     * @return 返回分页查询数据
     */
    @GetMapping("/bill")
    public Result<Page<BizParts>> getPartsBillList(Integer page,Integer pageSize,String partsname,String type,String
            name,String billuser ){
        BizParts bizParts = new BizParts();
        bizParts.setPartsname(partsname);
        bizParts.setPartsmodel(type);
        bizParts.setPartsloc(name);
        bizParts.setPartsremark(billuser);
        Page<BizParts> patsPage = new Page<>(page,pageSize);
        partsService.getPartsInOutList(patsPage,bizParts);
        return  Result.success(patsPage);
    }

    /**
     *  配件出入库查询
     * @param page 初始页面
     * @param pageSize 分页大小
     * @param code 配件编码
     * @param name 配件名称
     * @return 返回配件
     */
    @GetMapping("/out")
    public Result<Page<BizParts>> getPartsOutIn(Integer page,Integer pageSize,String code,String name){
        Page<BizParts> partsPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<BizParts> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(code),BizParts::getPartsid,code);
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name),BizParts::getPartsname,name);
        partsService.page(partsPage,lambdaQueryWrapper);
        List<BizParts> records = partsPage.getRecords();
        Page<BizPartsrepertory> bizPartsrepertoryPage = new Page<>(page,pageSize);
        bizPartsrepertoryService.page(bizPartsrepertoryPage);
        for (BizParts record : records) {
            LambdaQueryWrapper<BizPartsrepertory> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(BizPartsrepertory::getPartsid,record.getPartsid());
            BizPartsrepertory bizPartsrepertory = bizPartsrepertoryService.getOne(lambdaQueryWrapper1);
            if(bizPartsrepertory == null){
                bizPartsrepertoryPage.setTotal(bizPartsrepertoryPage.getTotal() - 1);
                continue;
            }
            record.setPartsmodel(bizPartsrepertory.getPartsreqcount().toString());
        }
        //对象拷贝
        BeanUtils.copyProperties(bizPartsrepertoryPage,partsPage,"records");
        return Result.success(partsPage);
    }

    /**
     * 拉去出入库类型
     * @return 返回出入库类型
     */
    @GetMapping("/code")
    public Result<List<SysCode>> getCode(){
        LambdaQueryWrapper<SysCode> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysCode::getType,"in").or();
        lambdaQueryWrapper.eq(SysCode::getType,"out");
        List<SysCode> list = codeService.list(lambdaQueryWrapper);
        return Result.success(list);
    }
    //biz_parts a,biz_partsrepbill

    /**
     * 配件出入库
     * @param bizParts 配件基本信息
     * @return 执行结果
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add/{id}")
    public Result<String> addPartsOut(@RequestBody BizParts bizParts, @PathVariable String id){
        log.info("parts=={}",bizParts);
        LambdaQueryWrapper<BizParts> partsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        partsLambdaQueryWrapper.eq(BizParts::getPartsname,bizParts.getPartsprodate());
        BizParts parts = partsService.getOne(partsLambdaQueryWrapper);

        LambdaQueryWrapper<BizPartsrepertory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BizPartsrepertory::getPartsid,parts.getPartsid());
        BizPartsrepertory serviceOne = bizPartsrepertoryService.getOne(lambdaQueryWrapper);
        //出库/入库
        if ("入库".equals(bizParts.getPartsname())){
            serviceOne.setPartsreqcount(serviceOne.getPartsreqcount() + bizParts.getPartsid());
        }else{
            //判断
            if (serviceOne.getPartsreqcount() - bizParts.getPartsid() < 0){
                return Result.error("配件数量不能大于库存数量");
            }
            serviceOne.setPartsreqcount(serviceOne.getPartsreqcount() - bizParts.getPartsid());
        }
        bizPartsrepertoryService.updateById(serviceOne);
        //添加数据
        LambdaQueryWrapper<SysCode> codeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //获取出入库类型
        codeLambdaQueryWrapper.eq(SysCode::getName,bizParts.getPartsmodel());
        SysCode sysCode = codeService.getOne(codeLambdaQueryWrapper);

        BizPartsrepbill bizPartsrepbill = new BizPartsrepbill();
        bizPartsrepbill.setPartsid(parts.getPartsid());
        bizPartsrepbill.setBillflag(sysCode.getType());
        bizPartsrepbill.setBilltype(sysCode.getCode());
        bizPartsrepbill.setBillcount(bizParts.getPartsid());
        bizPartsrepbill.setBilltime(LocalDateTime.now().toString());
        bizPartsrepbill.setBilluser(Integer.valueOf(id));
        bizPartsrepbillService.save(bizPartsrepbill);
        return Result.success("success");
    }
}
