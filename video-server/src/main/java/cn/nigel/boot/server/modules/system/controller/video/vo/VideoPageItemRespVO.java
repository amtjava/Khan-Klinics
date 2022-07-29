package cn.nigel.boot.server.modules.system.controller.video.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class VideoPageItemRespVO extends VideoBaseVO{

    private Long id;

    private Long categoryId;

    private String categoryName;

    private Integer amountPlay;

    private Integer display;

    private Date createTime;

    private String posterUrl;

    private String videoUrl;

}
