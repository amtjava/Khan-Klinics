package cn.nigel.boot.server.modules.system.service.category.impl;

import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryCreateReqVO;
import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryUpdateReqVO;
import cn.nigel.boot.server.modules.system.convert.category.CategoryConvert;
import cn.nigel.boot.server.modules.system.dal.dataobject.category.CategoryDO;
import cn.nigel.boot.server.modules.system.dal.mysql.category.CategoryMapper;
import cn.nigel.boot.server.modules.system.service.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDO> getList() {
        return categoryMapper.selectList();
    }

    @Override
    public List<CategoryDO> getListParent(Long parentId) {
        return categoryMapper.selectListParent(parentId);
    }

    @Override
    public CategoryDO getById(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<CategoryDO> getListByIds(Collection<Long> ids) {
        return categoryMapper.selectBatchIds(ids);
    }

    @Override
    public Long create(CategoryCreateReqVO reqVO) {
        CategoryDO categoryDO = CategoryConvert.INSTANCE.convert(reqVO);
        categoryMapper.insert(categoryDO);
        return categoryDO.getId();
    }

    @Override
    public void update(CategoryUpdateReqVO reqVO) {
        CategoryDO categoryDO = CategoryConvert.INSTANCE.convert(reqVO);
        categoryMapper.updateById(categoryDO);
    }

    @Override
    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }
}
