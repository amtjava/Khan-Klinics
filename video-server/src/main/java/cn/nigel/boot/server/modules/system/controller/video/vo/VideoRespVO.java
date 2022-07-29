package cn.nigel.boot.server.modules.system.controller.video.vo;

import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryListRespVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VideoRespVO extends VideoPageItemRespVO {

    private CategoryListRespVO category;

    private String posterUrl;

    private String videoUrl;

}
