package cn.nigel.boot.server.modules.system.convert.video;

import cn.nigel.boot.server.modules.system.controller.video.vo.VideoCreateReqVO;
import cn.nigel.boot.server.modules.system.controller.video.vo.VideoPageItemRespVO;
import cn.nigel.boot.server.modules.system.controller.video.vo.VideoRespVO;
import cn.nigel.boot.server.modules.system.controller.video.vo.VideoUpdateReqVO;
import cn.nigel.boot.server.modules.system.dal.dataobject.video.VideoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoConvert {

    VideoConvert INSTANCE = Mappers.getMapper(VideoConvert.class);

    VideoDO convert(VideoCreateReqVO reqVO);

    VideoPageItemRespVO convert(VideoDO videoDO);

    VideoRespVO convert2(VideoDO videoDO);

    VideoDO convert(VideoUpdateReqVO reqVO);
}
