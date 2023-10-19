package br.com.ecommerce.jemn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.jemn.model.Log;
import br.com.ecommerce.jemn.repository.LogRepository;

@Service
public class LogService {
    
    @Autowired
    private LogRepository logRepository;

    public void registrarLog(Log log){
        this.logRepository.save(log);
    }
}
