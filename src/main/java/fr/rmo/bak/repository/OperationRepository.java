package fr.rmo.bak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.rmo.bak.domain.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query("select o from Operation o where o.account.number = :accountNumber order by o.date desc")
	List<Operation> findOperationsByAccountNumber(@Param("accountNumber") String accountNumber);
}