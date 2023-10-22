package br.com.ecommerce.jemn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.jemn.model.Log;
import java.util.Optional;
import java.util.Date;



public interface LogRepository extends JpaRepository <Log, Long> {
    
   //Log findByVlAtual(String vlAtual);
   Optional<Log> findByData(Date data);

}
