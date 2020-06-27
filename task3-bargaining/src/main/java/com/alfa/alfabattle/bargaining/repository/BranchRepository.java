package com.alfa.alfabattle.bargaining.repository;

import com.alfa.alfabattle.bargaining.model.Branch;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository to perform CRUD operations with branches
 */
public interface BranchRepository extends CrudRepository<Branch, Long> {
}
