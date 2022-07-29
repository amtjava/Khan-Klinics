package cn.nigel.boot.server.modules.system.controller.video.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VideoBaseVO {

    @NotNull(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Author cannot be empty")
    private String author;

}
