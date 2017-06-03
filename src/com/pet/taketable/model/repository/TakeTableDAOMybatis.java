package com.pet.taketable.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.taketable.domain.TakeTable;

@Repository
public class TakeTableDAOMybatis implements TakeTableDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("TakeTable.selectAll");
	}

	@Override
	public List selectMember(String member_id) {
		return sqlSessionTemplate.selectList("TakeTable.selectMember", member_id);
	}

	@Override
	public TakeTable selectOne(int taketable_id) {
		return sqlSessionTemplate.selectOne("TakeTable.selectOne", taketable_id);
	}

	@Override
	public void delete(int taketable_id) {
		sqlSessionTemplate.delete("TakeTable.delete", taketable_id);
	}

	@Override
	public void deleteMember(String member_id) {
		sqlSessionTemplate.delete("TakeTable.deleteMember", member_id);
	}

	@Override
	public void update(TakeTable takeTable) {
		sqlSessionTemplate.update("TakeTable.update", takeTable);
	}

	@Override
	public void insert(TakeTable takeTable) {
		sqlSessionTemplate.insert("TakeTable.insert", takeTable);
	}

}
