package com.space.spacesinspace.admin.reply.model.dao;

import com.space.spacesinspace.common.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

    void registReply(ReplyDTO newReply);

    void deleteReply(int replyCode);

    ReplyDTO findReplyByCode(int replyCode);

    void updateReply(ReplyDTO reply);
}
