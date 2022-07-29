package cn.nigel.boot.server.modules.system.controller.category.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryUpdateReqVO {

    @NotNull(message = "Id cannot be empty")
    private Long id;

    private String name;

    private Integer display;
}
