package org.innocv.restiud.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.innocv.restiud.exceptions.UserAlreadyExistsException;
import org.innocv.restiud.exceptions.UserNotFoundException;
import org.innocv.restiud.model.User;
import org.innocv.restiud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	

	@Async("asyncExecutor")
	@Override
	public CompletableFuture<List<User>> findAllAsyncronous() {
		return CompletableFuture.completedFuture(userRepository.findAll());
	}
	

	@Async("asyncExecutor")
	@Override
	public CompletableFuture<User> findByIdAsyncronous(Integer id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return CompletableFuture.completedFuture(optionalUser.get());
		} else {
			throw new UserNotFoundException(id,HttpStatus.NOT_FOUND);
		}
	}

	@Async("asyncExecutor")
	@Override
	public CompletableFuture<User> save(User user) {
		Optional<User> optionalUser = userRepository.findByName(user.getName());
		if (optionalUser.isPresent()) {
			throw new UserAlreadyExistsException(user.getName());
		} else {
			return CompletableFuture.completedFuture(userRepository.save(user));
		}
	}

	@Override
	public User findById(Integer id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			throw new UserNotFoundException(id,HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void create(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

}
