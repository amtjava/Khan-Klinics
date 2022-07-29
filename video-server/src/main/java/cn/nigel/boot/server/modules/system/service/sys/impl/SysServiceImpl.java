package cn.nigel.boot.server.modules.system.service.sys.impl;

import cn.nigel.boot.framework.email.config.SendEmailHandler;
import cn.nigel.boot.server.modules.system.controller.sys.vo.ContactCreateReqVO;
import cn.nigel.boot.server.modules.system.service.sys.SysService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysServiceImpl implements SysService {

    @Resource
    private SendEmailHandler sendEmailHandler;

    @Override
    public void create(ContactCreateReqVO reqVO) {
        String content = "Name:" + reqVO.getName() +
                "<br>Phone:" + reqVO.getPhone() +
                "<br>Email:" + reqVO.getEmail() +
                "<br>Address:" + reqVO.getAddress() +
                "<br>Your Query:" + reqVO.getQuery();
        try {
            sendEmailHandler.sendMail("Khanklinics@gmail.com", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
