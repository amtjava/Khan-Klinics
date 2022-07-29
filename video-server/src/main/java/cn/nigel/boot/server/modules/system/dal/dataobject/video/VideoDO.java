package cn.nigel.boot.server.modules.system.dal.dataobject.video;

import cn.nigel.boot.framework.common.enums.CommonStatusEnum;
import cn.nigel.boot.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("sys_video")
@Data
@EqualsAndHashCode(callSuper = true)
public class VideoDO extends BaseDO {

    @TableId
    private Long id;

    private String author;

    private String title;

    private String summary;

    private String posterUrl;

    private String videoUrl;

    private Long categoryId;

    private Integer sort;

    private Integer amountPlay;

    /**
     * {@link CommonStatusEnum}
     */
    private Integer display;

    /**
     * {@link CommonStatusEnum}
     */
    private Integer status;

}
