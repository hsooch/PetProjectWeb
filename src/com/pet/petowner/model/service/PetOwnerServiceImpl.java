package com.pet.petowner.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.petowner.domain.PetOwner;
import com.pet.petowner.model.repositary.PetOwnerDAO;

@Service
public class PetOwnerServiceImpl implements PetOwnerService {
	@Autowired
	private PetOwnerDAO petOwnerDAO;

	@Override
	public List selectAll() {
		return petOwnerDAO.selectAll();
	}

	@Override
	public List selectMember(String member_id) {
		return petOwnerDAO.selectMember(member_id);
	}

	@Override
	public PetOwner selectOne(int petowner_id) {
		return petOwnerDAO.selectOne(petowner_id);
	}

	@Override
	public void insert(PetOwner petowner) {
		petOwnerDAO.insert(petowner);
	}

	@Override
	public void delete(int petowner_id) {
		petOwnerDAO.delete(petowner_id);
	}

	@Override
	public void deleteMember(String member_id) {
		petOwnerDAO.deleteMember(member_id);
	}

	@Override
	public void update(PetOwner petowner) {
		petOwnerDAO.update(petowner);
	}
}
