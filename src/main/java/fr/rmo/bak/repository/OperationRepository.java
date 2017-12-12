package fr.rmo.bak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.rmo.bak.domain.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}