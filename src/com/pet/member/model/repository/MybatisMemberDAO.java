package com.pet.member.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.member.model.domain.Member;

@Repository
public class MybatisMemberDAO implements MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;

	@Override
	public void insert(Member member) {
		sqlsessionTemplate.insert("Member.insert", member);
	}

	@Override
	public void update(Member member) {
		sqlsessionTemplate.update("Member.update", member);
	}

	@Override
	public void delete(int member_id) {
		sqlsessionTemplate.delete("Member.delete", member_id);
	}

	@Override
	public Member selectNick(String nick) {
		return sqlsessionTemplate.selectOne("Member.selectNick", nick);
	}

	@Override
	public Member selectId(String id) {
		Member member = sqlsessionTemplate.selectOne("Member.selectId", id);
		return member;
	}

	@Override
	public List selectAll() {
		return sqlsessionTemplate.selectList("Member.selectAll");
	}

}
