package com.pet.test;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAOMybatis implements TestDAO {
	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;

	@Override
	public List selectAll() {
		List list = sqlsessionTemplate.selectList("Test.selectAll");
		return list;
	}
}
