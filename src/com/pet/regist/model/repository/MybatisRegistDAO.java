package com.pet.regist.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.regist.model.domain.Regist;

@Repository
public class MybatisRegistDAO implements RegistDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Regist regist) {
		sqlSessionTemplate.insert("Regist.insert", regist);
	}

	@Override
	public void update(Regist regist) {
		sqlSessionTemplate.update("Regist.update", regist);
	}

	@Override
	public void delete(int regist_id) {
		sqlSessionTemplate.update("Regist.delete", regist_id);
	}

	@Override
	public void deleteMember(int member_id) {
		sqlSessionTemplate.update("Regist.deleteMember", member_id);
	}

	@Override
	public Regist selectOne(int regist_id) {
		return sqlSessionTemplate.selectOne("Regist.selectOne", regist_id);
	}

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Regist.selectAll");
	}

}
