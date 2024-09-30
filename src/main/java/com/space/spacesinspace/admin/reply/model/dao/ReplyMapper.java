package com.space.spacesinspace.admin.reply.model.dao;

import com.space.spacesinspace.common.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

    void deleteReply(int replyCode);

    int updateReply(ReplyDTO reply);

    int registNewReply(ReplyDTO newReply);
}
