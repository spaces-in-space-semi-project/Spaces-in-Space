package com.space.spacesinspace.admin.reply.model.service;

import com.space.spacesinspace.admin.reply.model.dao.ReplyMapper;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    private final ReplyMapper replyMapper;

    public ReplyService(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public void registReply(ReplyDTO newReply) {
        replyMapper.registReply(newReply);
    }

    public void deleteReply(int replyCode) {
        replyMapper.deleteReply(replyCode);
    }

    public ReplyDTO findReplyByCode(int replyCode) {
        return replyMapper.findReplyByCode(replyCode);
    }

    public void updateReply(ReplyDTO reply) {
        replyMapper.updateReply(reply);
    }
}
