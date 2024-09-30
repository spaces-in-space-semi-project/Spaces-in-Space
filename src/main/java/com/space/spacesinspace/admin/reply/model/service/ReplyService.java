package com.space.spacesinspace.admin.reply.model.service;

import com.space.spacesinspace.admin.reply.model.dao.ReplyMapper;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {

    private final ReplyMapper replyMapper;

    public ReplyService(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    @Transactional
    public void deleteReply(int replyCode) {
        replyMapper.deleteReply(replyCode);
    }

    @Transactional
    public Integer updateReply(ReplyDTO reply) {
        return replyMapper.updateReply(reply);
    }

    @Transactional
    public Integer registNewReply(ReplyDTO newReply) {
        return replyMapper.registNewReply(newReply);
    }
}
