package br.com.ecommerce.jemn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.jemn.model.Log;

public interface LogRepository extends JpaRepository <Log, Long> {
    

}
