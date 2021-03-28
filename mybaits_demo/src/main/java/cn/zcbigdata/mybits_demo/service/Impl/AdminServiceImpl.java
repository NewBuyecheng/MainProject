package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Admin;
import cn.zcbigdata.mybits_demo.mapper.AdminMapper;
import cn.zcbigdata.mybits_demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin find(String account) {
        return this.adminMapper.find(account);
    }




}
