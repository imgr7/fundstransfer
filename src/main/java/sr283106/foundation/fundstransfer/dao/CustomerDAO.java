package sr283106.foundation.fundstransfer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sr283106.foundation.fundstransfer.domain.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
   
}
