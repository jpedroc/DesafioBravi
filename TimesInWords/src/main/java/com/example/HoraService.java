package com.example;

import java.util.Arrays;
import java.util.List;

public class HoraService {

    public String converterParaExtenso(String horaCompleta) {
        List<String> horaMinuto = validarFormatoHora(horaCompleta);
        validarHoraCompleta(horaMinuto.get(0), horaMinuto.get(1));

        return horaPorExtenso(Integer.parseInt(horaMinuto.get(0)), Integer.parseInt(horaMinuto.get(1)));
    }

    private List<String> validarFormatoHora(String horaCompleta) {
        if(!horaCompleta.contains(":")) {
            throw new CustomException("Formato inválido");
        }
        List<String> horaMinuto = Arrays.asList(horaCompleta.split(":"));
        if(horaMinuto.size() != 2) {
            throw new CustomException("Formato inválido");
        }
        return horaMinuto;
    }

    private void validarHoraCompleta(String hora, String minutos) {
        try {
            Integer horaN = Integer.parseInt(hora);
            Integer minutosN = Integer.parseInt(minutos);
            if(minutosN > 59 || horaN > 12){
                throw new CustomException("Valor inválido");
            }
        }
        catch (NumberFormatException e) {
            throw new CustomException("Hora deve conter apenas números e o separador ':'");
        }
    }

    private String horaPorExtenso(Integer hora, Integer minuto) {
        if(minuto == 0) {
            return getNumeroExtenso(hora).concat(" ").concat(ConstantesUtil.OCLOCK);
        }
        else if (minuto <= 30) {
            if(minuto == 15) {
                return ConstantesUtil.QUARTER.concat(" past ").concat(getNumeroExtenso(hora));
            }
            return getNumeroExtenso(minuto).concat(" minutes past ").concat(getNumeroExtenso(hora));
        }
        hora = hora == 12 ? 0 : (hora + 1);
        minuto = 60 - minuto;
        if(minuto == 45) {
            return ConstantesUtil.QUARTER.concat(" to ").concat(getNumeroExtenso(hora));
        }
        return getNumeroExtenso(minuto).concat(" minutes to ").concat(getNumeroExtenso(hora));
    }

    private String getNumeroExtenso(Integer numero) {
        return ConstantesUtil.ARRAY_NUMEROS.get(numero);
    }
}
