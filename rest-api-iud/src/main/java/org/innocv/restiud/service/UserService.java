package org.innocv.restiud.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.innocv.restiud.model.User;

public interface UserService {
	

	CompletableFuture<List<User>> findAllAsyncronous();

	CompletableFuture<User> findByIdAsyncronous(Integer id);

	CompletableFuture<User> save(User user);

	User findById(Integer id);

	void create(User user);

	void delete(Integer id);

}
