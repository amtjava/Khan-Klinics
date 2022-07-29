package cn.nigel.boot.server.modules.system.dal.mysql.category;

import cn.nigel.boot.framework.mybatis.core.mapper.BaseMapperX;
import cn.nigel.boot.framework.mybatis.core.query.QueryWrapperX;
import cn.nigel.boot.server.modules.system.dal.dataobject.category.CategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {

    default List<CategoryDO> selectListParent(Long parentId) {
        return selectList(new QueryWrapperX<CategoryDO>()
                .eqIfPresent("parent_id", parentId)
                .eqIfPresent("display", 0));
    }
}
