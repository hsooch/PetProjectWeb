package com.pet.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestDAO testDAO;

	@Override
	public List selectAll() {
		List list = testDAO.selectAll();
		return list;
	}
}
