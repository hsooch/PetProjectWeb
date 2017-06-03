package com.pet.reply.model.repository;

import java.util.List;

import com.pet.reply.model.domain.Reply;

public interface ReplyDAO {
	public void insert(Reply reply);

	public void update(Reply reply);

	public void delete(int reply_id);

	public void deleteRegist(int regist_id);

	public void deleteMember(int member_id);

	public List selectRegist(int regist_id);

	public Reply selectOne(int reply_id);
}
