package org.innocv.restiud.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.innocv.restiud.model.User;
import org.innocv.restiud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CompletableFuture<ResponseEntity<List<User>>> findAllAsyncronous()
			throws InterruptedException {
		return userService.findAllAsyncronous().<ResponseEntity<List<User>>>thenApply(ResponseEntity::ok);
	}

	@GetMapping(value = "{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CompletableFuture<ResponseEntity<User>> findById(@PathVariable("id") Integer id)
			throws InterruptedException {
		return userService.findByIdAsyncronous(id).<ResponseEntity<User>>thenApply(ResponseEntity::ok);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CompletableFuture<ResponseEntity<User>> save(
			@Valid @RequestBody @NotEmpty(message = "User not null.") User user) throws InterruptedException {
		return userService.save(user).<ResponseEntity<User>>thenApply(ResponseEntity::ok);
	}

	@PutMapping(headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	public User updateUser(@Valid @RequestBody @NotEmpty User userUpdate) throws InterruptedException {
		User user = userService.findById(userUpdate.getId());
		user.setId(userUpdate.getId());
		user.setName(userUpdate.getName());
		user.setBirthdate(userUpdate.getBirthdate());
		userService.create(user);
		return user;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		userService.delete(id);
	}

}
