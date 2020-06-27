package com.alfa.alfabattle.bargaining.repository;

import com.alfa.alfabattle.bargaining.model.QueueWait;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository to perform CRUD operations with wait time in queue.
 */
public interface QueueWaitRepository extends CrudRepository<QueueWait, Long> {
    List<QueueWait> findByBranchId(Long branchId);
}
