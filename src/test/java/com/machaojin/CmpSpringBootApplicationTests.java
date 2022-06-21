package com.machaojin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machaojin.domain.BizParts;
import com.machaojin.domain.SysCustomer;
import com.machaojin.service.impl.BizPartsServiceImpl;
import com.machaojin.service.impl.SysCustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CmpSpringBootApplicationTests {
    @Autowired
    private SysCustomerServiceImpl customerService;
    @Autowired
    private BizPartsServiceImpl partsService;
    @Autowired
    private BizPartsServiceImpl service;

    @Test
    void contextLoads() {
        Page<SysCustomer> list = customerService.getList(1, 10, null);
        System.out.println(list.getTotal() + "total");
    }
    @Test
    void test1(){
      Page<BizParts> page = new Page<>(1,10);
      BizParts bizParts = new BizParts();
      bizParts.setPartsname("入");
      service.getPartsInOutList(page,bizParts);
        List<BizParts> records = page.getRecords();
        for (BizParts record : records) {
            System.out.println(record);
        }
    }

}
