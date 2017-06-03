package com.pet.reply.model.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.reply.model.domain.Reply;
import com.pet.reply.model.repository.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public void insert(Reply reply) {
		replyDAO.insert(reply);
	}

	@Override
	public void update(Reply reply) {
		replyDAO.update(reply);
	}

	@Override
	public void delete(int reply_id) {
		replyDAO.delete(reply_id);
	}

	@Override
	public void deleteRegist(int regist_id) {
		replyDAO.delete(regist_id);
	}

	@Override
	public void deleteMember(int member_id) {
		replyDAO.delete(member_id);
	}

	@Override
	public List selectRegist(int regist_id) {
		return replyDAO.selectRegist(regist_id);
	}

	@Override
	public Reply selectOne(int reply_id) {
		return replyDAO.selectOne(reply_id);
	}

}
