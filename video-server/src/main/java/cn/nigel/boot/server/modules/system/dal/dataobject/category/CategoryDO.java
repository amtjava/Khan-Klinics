package cn.nigel.boot.server.modules.system.dal.dataobject.category;

import cn.nigel.boot.framework.common.enums.CommonStatusEnum;
import cn.nigel.boot.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_category")
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryDO extends BaseDO {

    @TableId
    private Long id;

    private Long parentId;

    private String name;

    private Integer sort;

    /**
     * {@link CommonStatusEnum}
     */
    private Integer display;

    /**
     * {@link CommonStatusEnum}
     */
    private Integer status;

}
