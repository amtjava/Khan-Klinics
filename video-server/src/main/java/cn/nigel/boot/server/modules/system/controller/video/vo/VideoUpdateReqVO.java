package cn.nigel.boot.server.modules.system.controller.video.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VideoUpdateReqVO {

    @NotNull(message = "Id cannot be empty")
    private Long id;

    private Integer display;

    private String title;

    private String author;

    private Long categoryId;

    private String posterUrl;

    private String videoUrl;

}
