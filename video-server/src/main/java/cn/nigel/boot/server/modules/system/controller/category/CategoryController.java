package cn.nigel.boot.server.modules.system.controller.category;

import cn.nigel.boot.framework.common.pojo.CommonResult;
import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryCreateReqVO;
import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryListRespVO;
import cn.nigel.boot.server.modules.system.controller.category.vo.CategoryUpdateReqVO;
import cn.nigel.boot.server.modules.system.convert.category.CategoryConvert;
import cn.nigel.boot.server.modules.system.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static cn.nigel.boot.framework.common.pojo.CommonResult.success;

@Tag(name = "Category")
@RestController
@RequestMapping("/system/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    @Operation(summary = "Get Category List")
    public CommonResult<List<CategoryListRespVO>> getList() {
        return success(CategoryConvert.INSTANCE.convert(categoryService.getList()));
    }

    @GetMapping("/list-parent")
    @Operation(summary = "Get Category Parent List")
    public CommonResult<List<CategoryListRespVO>> getListParent(@RequestParam("parentId") Long parentId) {
        return success(CategoryConvert.INSTANCE.convert(categoryService.getListParent(parentId)));
    }

    @PostMapping("/create")
    @Operation(summary = "Create Category")
    public CommonResult<Long> create(@Valid @RequestBody CategoryCreateReqVO reqVO) {
        Long id = categoryService.create(reqVO);
        return success(id, "Create Successful");
    }

    @PutMapping("update")
    @Operation(summary = "Update Category")
    public CommonResult<Boolean> update(@Valid @RequestBody CategoryUpdateReqVO reqVO) {
        categoryService.update(reqVO);
        return success(true, "Update Successful");
    }

    @DeleteMapping("delete")
    @Operation(summary = "Delete Category")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        categoryService.delete(id);
        return success(true, "Delete Successful");
    }

}
