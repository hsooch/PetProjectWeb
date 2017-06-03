package com.pet.gettable.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.gettable.domain.GetTable;

@Repository
public class GetTableDAOMybatis implements GetTableDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("GetTable.selectAll");
	}

	@Override
	public List selectMember(String member_id) {
		return sqlSessionTemplate.selectList("GetTable.selectMember", member_id);
	}

	@Override
	public GetTable selectOne(int gettable_id) {
		return sqlSessionTemplate.selectOne("GetTable.selectOne", gettable_id);
	}

	@Override
	public void insert(GetTable getTable) {
		sqlSessionTemplate.insert("GetTable.insert", getTable);
	}

	@Override
	public void delete(int gettable_id) {
		sqlSessionTemplate.delete("GetTable.delete", gettable_id);
	}

	@Override
	public void deleteMember(String member_id) {
		sqlSessionTemplate.delete("GetTable.deleteMember", member_id);
	}

	@Override
	public void update(GetTable getTable) {
		sqlSessionTemplate.update("GetTable.update", getTable);
	}

}
