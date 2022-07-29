package cn.nigel.boot.server.modules.system.controller.sys;

import cn.nigel.boot.framework.common.pojo.CommonResult;
import cn.nigel.boot.server.modules.system.controller.sys.vo.ContactCreateReqVO;
import cn.nigel.boot.server.modules.system.service.sys.SysService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.nigel.boot.framework.common.pojo.CommonResult.success;

@Tag(name = "Sys")
@RestController
@RequestMapping("/system/sys")
public class SysController {

    @Resource
    private SysService sysService;

    @PostMapping("/contact")
    @Operation(summary = "Contact us")
    public CommonResult<Boolean> create(@Valid @RequestBody ContactCreateReqVO reqVO) {
        sysService.create(reqVO);
        return success(true, "Send Successful");
    }

}
