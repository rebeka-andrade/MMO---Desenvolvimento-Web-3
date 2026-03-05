package com.devotics.MMORebekaEClarice.services;

import java.util.Optional;

public interface UserService {
	Optional<Photo> findByUserId(Long userId);
}