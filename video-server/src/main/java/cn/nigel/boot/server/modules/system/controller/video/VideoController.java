package cn.nigel.boot.server.modules.system.controller.video;

import cn.hutool.core.collection.CollUtil;
import cn.nigel.boot.framework.common.pojo.CommonResult;
import cn.nigel.boot.framework.common.pojo.PageParam;
import cn.nigel.boot.framework.common.pojo.PageResult;
import cn.nigel.boot.framework.common.util.collection.CollectionUtils;
import cn.nigel.boot.framework.qiniu.config.QiniuProperties;
import cn.nigel.boot.server.modules.system.controller.video.vo.*;
import cn.nigel.boot.server.modules.system.convert.category.CategoryConvert;
import cn.nigel.boot.server.modules.system.convert.video.VideoConvert;
import cn.nigel.boot.server.modules.system.dal.dataobject.category.CategoryDO;
import cn.nigel.boot.server.modules.system.dal.dataobject.video.VideoDO;
import cn.nigel.boot.server.modules.system.service.category.CategoryService;
import cn.nigel.boot.server.modules.system.service.video.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.nigel.boot.framework.common.pojo.CommonResult.success;

@Tag(name = "Video")
@RestController
@RequestMapping("/system/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private QiniuProperties qiniuProperties;

    @PostMapping("/create")
    @Operation(summary = "Insert Video")
    public CommonResult<Long> create(@Valid @RequestBody VideoCreateReqVO reqVO) {
        Long id = videoService.create(reqVO);
        return success(id, "Create Successful");
    }

    @GetMapping("/page")
    @Operation(summary = "Get Page Record")
    public CommonResult<PageResult<VideoPageItemRespVO>> getPage(@Valid PageParam reqVO) {
        PageResult<VideoDO> pageResult = videoService.getPage(reqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }
        Collection<Long> categoryIds = CollectionUtils.convertList(pageResult.getList(), VideoDO::getCategoryId);
        Map<Long, CategoryDO> categoryMap = categoryService.getMap(categoryIds);

        List<VideoPageItemRespVO> respVOList = new ArrayList<>();
        for (VideoDO videoDO : pageResult.getList()) {
            VideoPageItemRespVO itemRespVO = VideoConvert.INSTANCE.convert(videoDO);
            itemRespVO.setPosterUrl(qiniuProperties.getDomain() + itemRespVO.getPosterUrl());
            itemRespVO.setVideoUrl(qiniuProperties.getDomain() + itemRespVO.getVideoUrl());
            CategoryDO categoryDO = categoryMap.get(videoDO.getCategoryId());
            itemRespVO.setCategoryId(categoryDO != null ? categoryDO.getId() : -1L);
            itemRespVO.setCategoryName(categoryDO != null ? categoryDO.getName() : "");
            respVOList.add(itemRespVO);
        }
        return success(new PageResult<>(respVOList, pageResult.getTotal()));
    }

    @GetMapping("/get")
    @Operation(summary = "Get Video Detail")
    public CommonResult<VideoRespVO> getInfo(@RequestParam("id") Long id) {
        final VideoDO videoDO = videoService.getDetail(id);
        final VideoRespVO respVO = VideoConvert.INSTANCE.convert2(videoDO);
        respVO.setPosterUrl(qiniuProperties.getDomain() + respVO.getPosterUrl());
        respVO.setVideoUrl(qiniuProperties.getDomain() + respVO.getVideoUrl());
        respVO.setCategory(CategoryConvert.INSTANCE.convert(categoryService.getById(videoDO.getCategoryId())));
        return success(respVO);
    }

    @GetMapping("/listSameCate")
    @Operation(summary = "Gets a list of the same category")
    public CommonResult<List<VideoRespVO>> getListSameCate(@RequestParam("id") Long id, @RequestParam("categoryId") Long categoryId) {
        final List<VideoDO> videoDOList = videoService.getListSameCate(id, categoryId);
        return success(videoDOList.stream().map(VideoConvert.INSTANCE::convert2).peek(item -> {
            item.setPosterUrl(qiniuProperties.getDomain() + item.getPosterUrl());
            item.setVideoUrl(qiniuProperties.getDomain() + item.getVideoUrl());
        }).collect(Collectors.toList()));
    }

    @GetMapping("/listByCate")
    @Operation(summary = "Gets a list by category")
    public CommonResult<List<VideoRespVO>> getListByCate(@RequestParam("categoryId") Long categoryId) {
        final List<VideoDO> videoDOList = videoService.getListByCate(categoryId);
        return success(videoDOList.stream().map(VideoConvert.INSTANCE::convert2).peek(item -> {
            item.setPosterUrl(qiniuProperties.getDomain() + item.getPosterUrl());
            item.setVideoUrl(qiniuProperties.getDomain() + item.getVideoUrl());
        }).collect(Collectors.toList()));
    }

    @PutMapping("/update")
    @Operation(summary = "Update video")
    public CommonResult<Boolean> update(@Valid @RequestBody VideoUpdateReqVO reqVO) {
        videoService.update(reqVO);
        return success(true);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Delete Video")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        videoService.delete(id);
        return success(true, "Delete Successful");
    }

    @PutMapping("/increasePlay")
    @Operation(summary = "Increase play")
    public CommonResult<Boolean> increasePlay(@Valid @RequestBody VideoUpdateReqVO reqVO) {
        videoService.increasePlay(reqVO.getId());
        return success(true);
    }

}
