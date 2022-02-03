package sr283106.foundation.fundstransfer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sr283106.foundation.fundstransfer.domain.Account;


public interface AccountDAO extends JpaRepository<Account, Long> {


}
