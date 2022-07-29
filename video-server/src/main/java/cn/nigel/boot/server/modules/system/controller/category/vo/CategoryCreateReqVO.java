package cn.nigel.boot.server.modules.system.controller.category.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryCreateReqVO {

    @NotNull(message = "ParentId cannot be empty")
    private Long parentId;

    @NotNull(message = "name cannot be empty")
    private String name;
}
