package com.pet.pet.model.repository;

import java.util.List;

import com.pet.pet.model.domain.Pet;

public interface PetDAO {
	public void insert(Pet pet);

	public void update(Pet pet);

	public void delete(int pet_id);

	public void deleteMember(int member_id);

	public Pet selectOne(int pet_id);

	public List selectMember(int member_id);
}
