package cn.nigel.boot.server.modules.system.controller.sys.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ContactCreateReqVO {

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Phone cannot be empty")
    private String phone;

    @NotNull(message = "Address cannot be empty")
    private String address;

    private String query;
}
