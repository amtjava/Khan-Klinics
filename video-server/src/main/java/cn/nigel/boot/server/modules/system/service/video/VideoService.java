package cn.nigel.boot.server.modules.system.service.video;

import cn.nigel.boot.framework.common.pojo.PageParam;
import cn.nigel.boot.framework.common.pojo.PageResult;
import cn.nigel.boot.server.modules.system.controller.video.vo.VideoCreateReqVO;
import cn.nigel.boot.server.modules.system.controller.video.vo.VideoUpdateReqVO;
import cn.nigel.boot.server.modules.system.dal.dataobject.video.VideoDO;

import java.util.List;

public interface VideoService {
    Long create(VideoCreateReqVO reqVO);

    PageResult<VideoDO> getPage(PageParam reqVO);

    VideoDO getDetail(Long id);

    List<VideoDO> getListSameCate(Long id, Long categoryId);

    List<VideoDO> getListByCate(Long categoryId);

    void update(VideoUpdateReqVO reqVO);

    void delete(Long id);

    void increasePlay(Long id);
}
