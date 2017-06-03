package com.pet.petowner.model.service;

import java.util.List;

import com.pet.petowner.domain.PetOwner;

public interface PetOwnerService {
	public List selectAll();

	public List selectMember(String member_id);

	public PetOwner selectOne(int petowner_id);

	public void insert(PetOwner petowner);

	public void delete(int petowner_id);

	public void deleteMember(String member_id);

	public void update(PetOwner petowner);
}
