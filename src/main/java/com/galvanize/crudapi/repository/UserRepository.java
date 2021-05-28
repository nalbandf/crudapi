package com.galvanize.crudapi.repository;

import com.galvanize.crudapi.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long>{


}
