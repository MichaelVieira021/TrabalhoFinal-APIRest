package br.com.ecommerce.jemn.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorData {
    
    public static String converterDateParaDataHora(Date data){
        return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(data);
    }
}