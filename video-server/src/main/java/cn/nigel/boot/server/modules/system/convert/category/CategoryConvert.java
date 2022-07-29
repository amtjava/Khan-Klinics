package cn.nigel.boot.server.modules.system.convert.category;

import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryCreateReqVO;
import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryListRespVO;
import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryUpdateReqVO;
import cn.nigel.boot.server.modules.system.dal.dataobject.category.CategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryConvert {

    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    List<CategoryListRespVO> convert(List<CategoryDO> list);

    CategoryListRespVO convert(CategoryDO categoryDO);

    CategoryDO convert(CategoryCreateReqVO reqVO);

    CategoryDO convert(CategoryUpdateReqVO reqVO);
}
