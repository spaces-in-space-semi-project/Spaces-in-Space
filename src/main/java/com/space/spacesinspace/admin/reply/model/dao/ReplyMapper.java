package com.space.spacesinspace.admin.reply.model.dao;

import com.space.spacesinspace.common.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface ReplyMapper {

    @Transactional
    void registReply(ReplyDTO newReply);

    @Transactional
    void deleteReply(int replyCode);

    ReplyDTO findReplyByCode(int replyCode);

    @Transactional
    void updateReply(ReplyDTO reply);
}
