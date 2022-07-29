package cn.nigel.boot.server.modules.qiniu.controller.token;

import cn.nigel.boot.framework.common.pojo.CommonResult;
import cn.nigel.boot.framework.qiniu.config.QiniuProperties;
import cn.nigel.boot.server.modules.qiniu.controller.token.vo.TokenRespVO;
import com.qiniu.util.Auth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.nigel.boot.framework.common.pojo.CommonResult.success;

@Tag(name = "Qiniu")
@RestController
@RequestMapping("/qiniu/token")
public class QiniuTokenController {

    @Resource
    private QiniuProperties qiniuProperties;

    @GetMapping("/get")
    @Operation(summary = "Get Token")
    public CommonResult<TokenRespVO> getToken() {
        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        String upToken = auth.uploadToken(qiniuProperties.getBucket());
        return success(TokenRespVO.builder().token(upToken).build());
    }

}
