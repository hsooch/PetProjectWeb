package com.pet.pet.model.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.pet.model.domain.Pet;
import com.pet.pet.model.repository.PetDAO;

@Service
public class PetServiceImpl implements PetService {
	@Autowired
	private PetDAO petDAO;

	@Override
	public void insert(Pet pet) {
		petDAO.insert(pet);
	}

	@Override
	public void update(Pet pet) {
		petDAO.update(pet);
	}

	@Override
	public void delete(int pet_id) {
		petDAO.delete(pet_id);
	}

	@Override
	public void deleteMember(int member_id) {
		petDAO.delete(member_id);
	}

	@Override
	public Pet selectOne(int pet_id) {
		return petDAO.selectOne(pet_id);
	}

	@Override
	public List selectMember(int member_id) {
		return petDAO.selectMember(member_id);
	}

}
