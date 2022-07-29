package cn.nigel.boot.server.modules.system.controller.category.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryListRespVO {

    private Long id;

    private Long parentId;

    private String name;

    private Date createTime;

    private Integer display;

}
