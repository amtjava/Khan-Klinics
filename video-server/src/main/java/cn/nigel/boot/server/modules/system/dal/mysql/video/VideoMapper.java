package cn.nigel.boot.server.modules.system.dal.mysql.video;

import cn.nigel.boot.framework.common.enums.CommonStatusEnum;
import cn.nigel.boot.framework.common.pojo.PageParam;
import cn.nigel.boot.framework.common.pojo.PageResult;
import cn.nigel.boot.framework.mybatis.core.mapper.BaseMapperX;
import cn.nigel.boot.framework.mybatis.core.query.QueryWrapperX;
import cn.nigel.boot.server.modules.system.dal.dataobject.video.VideoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper extends BaseMapperX<VideoDO> {
    default PageResult<VideoDO> selectPage(PageParam reqVO) {
        return selectPage(reqVO, new QueryWrapperX<>());
    }

    default List<VideoDO> getListSameCate(Long id, Long categoryId) {
        return selectList(new QueryWrapperX<VideoDO>()
                .eqIfPresent("category_id", categoryId)
                .neIfPresent("id", id)
                .orderByDesc("amount_play"));
    }

    default List<VideoDO> getListByCate(Long categoryId) {
        return selectList(new QueryWrapperX<VideoDO>()
                .eqIfPresent("category_id", categoryId)
                .eqIfPresent("display", CommonStatusEnum.ENABLE.getStatus())
                .orderByDesc("amount_play"));
    }
}
