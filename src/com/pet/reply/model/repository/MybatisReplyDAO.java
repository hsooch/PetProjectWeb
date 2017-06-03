package com.pet.reply.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.reply.model.domain.Reply;

@Repository
public class MybatisReplyDAO implements ReplyDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Reply reply) {
		sqlSessionTemplate.insert("Reply.insert", reply);
	}

	@Override
	public void update(Reply reply) {
		sqlSessionTemplate.update("Reply.update", reply);
	}

	@Override
	public void delete(int reply_id) {
		sqlSessionTemplate.delete("Reply.delete", reply_id);
	}

	@Override
	public void deleteRegist(int regist_id) {
		sqlSessionTemplate.delete("Reply.deleteRegist", regist_id);
	}

	@Override
	public void deleteMember(int member_id) {
		sqlSessionTemplate.delete("Reply.deleteMember", member_id);
	}

	@Override
	public List selectRegist(int regist_id) {
		return sqlSessionTemplate.selectList("Reply.selectRegist", regist_id);
	}

	@Override
	public Reply selectOne(int reply_id) {
		return sqlSessionTemplate.selectOne("Reply.selectOne", reply_id);
	}

}
