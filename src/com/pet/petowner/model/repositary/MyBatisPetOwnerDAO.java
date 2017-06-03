package com.pet.petowner.model.repositary;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.petowner.domain.PetOwner;

@Repository
public class MyBatisPetOwnerDAO implements PetOwnerDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("PetOwner.selectAll");
	}

	@Override
	public List selectMember(String member_id) {

		return sqlSessionTemplate.selectList("PetOwner.selectMember", member_id);
	}

	@Override
	public PetOwner selectOne(int petowner_id) {

		return sqlSessionTemplate.selectOne("PetOwner.selectOne", petowner_id);
	}

	@Override
	public void insert(PetOwner petowner) {
		sqlSessionTemplate.insert("PetOwner.insert", petowner);
	}

	@Override
	public void delete(int petowner_id) {
		sqlSessionTemplate.delete("PetOwner.delete", petowner_id);
	}

	@Override
	public void deleteMember(String member_id) {
		sqlSessionTemplate.delete("PetOwner.deleteMember", member_id);
	}

	@Override
	public void update(PetOwner petowner) {
		sqlSessionTemplate.update("PetOwner.update", petowner);
	}

}
