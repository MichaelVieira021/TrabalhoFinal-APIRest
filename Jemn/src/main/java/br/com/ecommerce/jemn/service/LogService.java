package br.com.ecommerce.jemn.service;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.ecommerce.jemn.dto.pedido.PedidoResponseDTO;
import br.com.ecommerce.jemn.model.Log;
import br.com.ecommerce.jemn.repository.LogRepository;

@Service
public class LogService {
    
    @Autowired
    private LogRepository logRepository;

    public void registrarLog(Log log){
        this.logRepository.save(log);
    }

    public PedidoResponseDTO getRegistro(Date data) {
    Optional<Log> logOptional = logRepository.findByData(data);

    if (logOptional.isPresent()) {
        Log log = logOptional.get();
        String json = log.getVlAtual();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            var objeto = objectMapper.readValue(json, PedidoResponseDTO.class);
            return objeto;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    return null;
}
}
