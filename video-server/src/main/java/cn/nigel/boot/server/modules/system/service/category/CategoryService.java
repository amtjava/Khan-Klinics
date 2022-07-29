package cn.nigel.boot.server.modules.system.service.category;

import cn.hutool.core.collection.CollUtil;
import cn.nigel.boot.framework.common.util.collection.CollectionUtils;
import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryCreateReqVO;
import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryUpdateReqVO;
import cn.nigel.boot.server.modules.system.dal.dataobject.category.CategoryDO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<CategoryDO> getList();

    List<CategoryDO> getListParent(Long parentId);

    CategoryDO getById(Long id);

    List<CategoryDO> getListByIds(Collection<Long> ids);

    default Map<Long, CategoryDO> getMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<CategoryDO> list = getListByIds(ids);
        return CollectionUtils.convertMap(list, CategoryDO::getId);
    }

    Long create(CategoryCreateReqVO reqVO);

    void update(CategoryUpdateReqVO reqVO);

    void delete(Long id);
}
