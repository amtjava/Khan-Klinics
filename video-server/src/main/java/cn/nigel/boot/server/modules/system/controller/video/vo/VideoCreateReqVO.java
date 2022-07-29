package cn.nigel.boot.server.modules.system.controller.video.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class VideoCreateReqVO extends VideoBaseVO {

    @NotNull(message = "Category cannot be empty")
    private Long categoryId;

    @NotNull(message = "PosterUrl cannot be empty")
    private String posterUrl;

    @NotNull(message = "VideoUrl cannot be empty")
    private String videoUrl;
}
