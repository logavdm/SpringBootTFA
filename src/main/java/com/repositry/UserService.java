package com.repositry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Users;

@Service
@Transactional
public class UserService implements UserRepository{
	
	
	UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		
		this.repository=repository;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Users arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Users> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Users> findAll(Iterable<Long> arg0) {
				
		return repository.findAll();
	}

	@Override
	public Users findOne(Long arg0) {
					
		return repository.findOne(arg0);
	}

	@Override
	public <S extends Users> S save(S arg0) {
		
		return repository.save(arg0);
	}

	@Override
	public <S extends Users> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findByEmail(String email) {
		
		return repository.findByEmail(email);
	}

	
	
	
}
