package com.pet.registphoto.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.registphoto.model.domain.RegistPhoto;

@Repository
public class MyBatisRegistPhotoDAO implements RegistPhotoDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(RegistPhoto registPhoto) {
		sqlSessionTemplate.insert("RegistPhoto.insert", registPhoto);
	}

	@Override
	public void update(RegistPhoto registPhoto) {
		sqlSessionTemplate.update("RegistPhoto.update", registPhoto);
	}

	@Override
	public void delete(int registPhoto_id) {
		sqlSessionTemplate.delete("RegistPhoto.delete", registPhoto_id);
	}

	@Override
	public RegistPhoto selectOne(int registPhoto_id) {
		return sqlSessionTemplate.selectOne("RegistPhoto.selectOne", registPhoto_id);
	}

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("RegistPhoto.selectAll");
	}

}
