package com.onlineshopping.backend.repository;

import com.onlineshopping.backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "address", path = "address")
public interface AddressRepository extends JpaRepository<Address,Long> {
}
