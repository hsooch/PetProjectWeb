package com.pet.pet.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.pet.model.domain.Pet;

@Repository
public class MybatisPetDAO implements PetDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Pet pet) {
		sqlSessionTemplate.insert("Pet.insert", pet);
	}

	@Override
	public void update(Pet pet) {
		sqlSessionTemplate.update("Pet.update", pet);

	}

	@Override
	public void delete(int pet_id) {
		sqlSessionTemplate.delete("Pet.delete", pet_id);

	}

	@Override
	public Pet selectOne(int pet_id) {
		return sqlSessionTemplate.selectOne("Pet.selectOne", pet_id);
	}

	@Override
	public void deleteMember(int member_id) {
		sqlSessionTemplate.delete("Pet.deleteMember", member_id);
	}

	@Override
	public List selectMember(int member_id) {
		return sqlSessionTemplate.selectList("Pet,selectMember", member_id);
	}

}
