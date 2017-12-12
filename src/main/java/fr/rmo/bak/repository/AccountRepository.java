package fr.rmo.bak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.rmo.bak.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}