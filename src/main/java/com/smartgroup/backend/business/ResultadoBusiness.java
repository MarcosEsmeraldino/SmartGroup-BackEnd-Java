package com.smartgroup.backend.business;

import com.smartgroup.backend.bean.Resultado;
import com.smartgroup.backend.bean.Voto;
import com.smartgroup.backend.bean.VotoOpcao;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultadoBusiness {
    
    public Resultado calculaResultado(long idEnquete) {
        
        VotoBusiness vb = new VotoBusiness();
        List<Voto> votos = vb.listarByEnquete(idEnquete);

        // contabiliza as avaliações de cada opção
        // LIKE=2pts; NULL=1pt; HATE=0pt
        HashMap<Long, Integer> respostas = new HashMap();
        for(Voto v : votos) {
            for(VotoOpcao vo : v.getVotosOpcoes()) {
                
                Long key = vo.getOpcao().getId();
                Integer value = respostas.get(key);
                
                if(value == null) {
                    value = 0;
                }
                
                switch(vo.getValor()) {
                    case LIKE: value+=2; break;
                    case NULL: value+=1; break;
                    case HATE: value+=0; break;
                }

                respostas.put(vo.getOpcao().getId(), value);
            }
        }
        
        // ordena respostas pela melhor avaliação
        //https://dzone.com/articles/how-to-sort-a-map-by-value-in-java-8
        LinkedHashMap<Long, Integer> respostasSorted = respostas.entrySet()
            .stream()
            .sorted((Map.Entry.<Long, Integer>comparingByValue().reversed()))
            .collect(Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, 
                    LinkedHashMap::new));
        
        // return
        Resultado r = new Resultado();
        r.setDataRegistro(new Date());
        r.setEnquete(idEnquete);
        r.setRespostas(respostasSorted);
        
        return r;
    }
}
