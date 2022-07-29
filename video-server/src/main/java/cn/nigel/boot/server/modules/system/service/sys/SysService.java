package cn.nigel.boot.server.modules.system.service.sys;

import cn.nigel.boot.server.modules.system.controller.sys.vo.ContactCreateReqVO;

public interface SysService {
    void create(ContactCreateReqVO reqVO);
}
