package cn.nigel.boot.server.modules.system.service.video.impl;

import cn.nigel.boot.framework.common.pojo.PageParam;
import cn.nigel.boot.framework.common.pojo.PageResult;
import cn.nigel.boot.framework.qiniu.config.QiniuProperties;
import cn.nigel.boot.server.modules.system.controller.video.vo.VideoCreateReqVO;
import cn.nigel.boot.server.modules.system.controller.video.vo.VideoUpdateReqVO;
import cn.nigel.boot.server.modules.system.convert.video.VideoConvert;
import cn.nigel.boot.server.modules.system.dal.dataobject.video.VideoDO;
import cn.nigel.boot.server.modules.system.dal.mysql.video.VideoMapper;
import cn.nigel.boot.server.modules.system.service.video.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoMapper videoMapper;

    @Resource
    private QiniuProperties qiniuProperties;

    @Override
    public Long create(VideoCreateReqVO reqVO) {
        VideoDO videoDO = VideoConvert.INSTANCE.convert(reqVO);
        videoMapper.insert(videoDO);
        return videoDO.getId();
    }

    @Override
    public PageResult<VideoDO> getPage(PageParam reqVO) {
        return videoMapper.selectPage(reqVO);
    }

    @Override
    public VideoDO getDetail(Long id) {
        return videoMapper.selectById(id);
    }

    @Override
    public List<VideoDO> getListSameCate(Long id, Long categoryId) {
        return videoMapper.getListSameCate(id, categoryId);
    }

    @Override
    public List<VideoDO> getListByCate(Long categoryId) {
        return videoMapper.getListByCate(categoryId);
    }

    @Override
    public void update(VideoUpdateReqVO reqVO) {
        if (reqVO.getPosterUrl() != null) {
            reqVO.setPosterUrl(reqVO.getPosterUrl().replaceAll(qiniuProperties.getDomain(), ""));
        }
        if (reqVO.getVideoUrl() != null) {
            reqVO.setVideoUrl(reqVO.getVideoUrl().replaceAll(qiniuProperties.getDomain(), ""));
        }
        videoMapper.updateById(VideoConvert.INSTANCE.convert(reqVO));
    }

    @Override
    public void delete(Long id) {
        videoMapper.deleteById(id);
    }

    @Override
    public void increasePlay(Long id) {
        VideoDO videoDO = this.getDetail(id);
        videoDO.setAmountPlay(videoDO.getAmountPlay() + 1);
        videoMapper.updateById(videoDO);
    }
}
