package com.test;

import com.smartgroup.backend.bean.Comentario;
import com.smartgroup.backend.bean.Enquete;
import com.smartgroup.backend.bean.Opcao;
import com.smartgroup.backend.bean.Resultado;
import com.smartgroup.backend.bean.Usuario;
import com.smartgroup.backend.bean.Voto;
import com.smartgroup.backend.bean.VotoOpcao;
import com.smartgroup.backend.business.ComentarioBusiness;
import com.smartgroup.backend.business.EnqueteBusiness;
import com.smartgroup.backend.business.ResultadoBusiness;
import com.smartgroup.backend.business.UsuarioBusiness;
import com.smartgroup.backend.business.VotoBusiness;
import com.smartgroup.backend.enumerate.enquete.Status;
import com.smartgroup.backend.enumerate.usuario.Tipo;
import com.smartgroup.backend.enumerate.votoopcao.Valor;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class JUnitTest {

    public JUnitTest() {
    }
    
    //@Test
    public void testComentarioBusiness() {
        EnqueteBusiness eb = new EnqueteBusiness();
        Enquete e = eb.listar().get(0);

        ComentarioBusiness cb = new ComentarioBusiness();
        List<Comentario> listarByEnquete = cb.listarByEnquete(e.getId());
        long listSizeByEnquete = cb.getListSizeByEnquete(e.getId());
        
        System.out.println(listarByEnquete);
        System.out.println(listSizeByEnquete);
    }
    
    //@Test
    public void testEnqueteBusiness() {
        UsuarioBusiness ub = new UsuarioBusiness();
        Usuario u = ub.listar().get(0);
        
        EnqueteBusiness eb = new EnqueteBusiness();
        List<Enquete> listarByAutor = eb.listarByAutor(u.getId());
        List<Enquete> listarByAutorVoto = eb.listarByAutorVoto(u.getId());
        List<Enquete> listarByUsuario = eb.listarByUsuario(u.getId());
        
        System.out.println(listarByAutor);
        System.out.println(listarByAutorVoto);
        System.out.println(listarByUsuario);
    }
    
    //@Test
    public void testUsuarioBusiness() {
        EnqueteBusiness eb = new EnqueteBusiness();
        Enquete e = eb.listar().get(0);

        UsuarioBusiness ub = new UsuarioBusiness();
        List<Usuario> usuariosByEnquete = ub.listarByEnquete(e.getId());
        
        System.out.println(usuariosByEnquete);
    }
    
    //@Test
    public void countTest() {
        VotoBusiness vb = new VotoBusiness();
        long countVotos = vb.getListSizeByEnquete(1);
        
        System.out.println(countVotos);
        
        ComentarioBusiness cb = new ComentarioBusiness();
        long countComentarios = cb.getListSizeByEnquete(1);
                
        System.out.println(countComentarios);
    }
       
    //@Test
    public void calculateResult() {
        EnqueteBusiness eb = new EnqueteBusiness();
        Enquete e = eb.listar().get(0);
        
        ResultadoBusiness rb = new ResultadoBusiness();
        Resultado result = rb.calculaResultado(e.getId());
        
        System.out.println(result);
    }

    @Test
    public void populateTables() {
        
        // Usuario
        Usuario u1 = new Usuario();
        u1.setLogin("login1");
        u1.setNome("nome 1");
        u1.setSenha("senha1");
        u1.setTipo(Tipo.CADASTRADO);
        
        Usuario u2 = new Usuario();
        u2.setLogin("login2");
        u2.setNome("nome 2");
        u2.setSenha("senha2");
        u2.setTipo(Tipo.VISITANTE);
        
        Usuario u3 = new Usuario();
        u3.setLogin("login3");
        u3.setNome("nome 3");
        u3.setSenha("senha3");
        u3.setTipo(Tipo.VISITANTE);

        UsuarioBusiness ub = new UsuarioBusiness();
        ub.inserir(u1);
        ub.inserir(u2);
        ub.inserir(u3);

        
        // Enquete
        Enquete e = new Enquete();
        e.setAutor(u1);
        e.setDescricao("descrição 1");
        e.setNome("Enquete teste 1");
        e.setStatus(Status.ATIVA);

        Opcao o1 = new Opcao();
        o1.setConteudo("opção 1");
        o1.setEnquete(e);
        o1.setOrdem(1);
        
        Opcao o2 = new Opcao();
        o2.setConteudo("opção 2");
        o2.setEnquete(e);
        o2.setOrdem(2);

        Opcao o3 = new Opcao();
        o3.setConteudo("opção 3");
        o3.setEnquete(e);
        o3.setOrdem(3);

        List<Opcao> opcoes = new ArrayList();
        opcoes.add(o1);
        opcoes.add(o2);
        opcoes.add(o3);
        e.setOpcoes(opcoes);

        EnqueteBusiness eb = new EnqueteBusiness();
        eb.inserir(e);

        
        // Comentario
        Comentario c1 = new Comentario();
        c1.setAutor(u1);
        c1.setConteudo("comentário 1");
        c1.setEnquete(e);

        Comentario c2 = new Comentario();
        c2.setAutor(u2);
        c2.setConteudo("comentário 2");
        c2.setEnquete(e);

        Comentario c3 = new Comentario();
        c3.setAutor(u3);
        c3.setConteudo("comentário 3");
        c3.setEnquete(e);

        ComentarioBusiness cb = new ComentarioBusiness();
        cb.inserir(c1);
        cb.inserir(c2);
        cb.inserir(c3);


        // Voto
        Voto v1 = new Voto();
        v1.setAutor(u1);
        v1.setEnquete(e);

        VotoOpcao vo1 = new VotoOpcao();
        vo1.setOpcao(e.getOpcoes().get(0));
        vo1.setValor(Valor.HATE);
        vo1.setVoto(v1);
        
        VotoOpcao vo2 = new VotoOpcao();
        vo2.setOpcao(e.getOpcoes().get(1));
        vo2.setValor(Valor.HATE);
        vo2.setVoto(v1);

        VotoOpcao vo3 = new VotoOpcao();
        vo3.setOpcao(e.getOpcoes().get(2));
        vo3.setValor(Valor.LIKE);
        vo3.setVoto(v1);

        List<VotoOpcao> votos1 = new ArrayList();
        votos1.add(vo1);
        votos1.add(vo2);
        votos1.add(vo3);
        
        v1.setVotosOpcoes(votos1);

        Voto v2 = new Voto();
        v2.setAutor(u2);
        v2.setEnquete(e);

        VotoOpcao vo21 = new VotoOpcao();
        vo21.setOpcao(e.getOpcoes().get(0));
        vo21.setValor(Valor.NULL);
        vo21.setVoto(v2);

        VotoOpcao vo22 = new VotoOpcao();
        vo22.setOpcao(e.getOpcoes().get(1));
        vo22.setValor(Valor.HATE);
        vo22.setVoto(v2);

        VotoOpcao vo23 = new VotoOpcao();
        vo23.setOpcao(e.getOpcoes().get(2));
        vo23.setValor(Valor.LIKE);
        vo23.setVoto(v2);

        List<VotoOpcao> votos2 = new ArrayList();
        votos2.add(vo21);
        votos2.add(vo22);
        votos2.add(vo23);

        v2.setVotosOpcoes(votos2);

        Voto v3 = new Voto();
        v3.setAutor(u3);
        v3.setEnquete(e);
        
        VotoOpcao vo31 = new VotoOpcao();
        vo31.setOpcao(e.getOpcoes().get(0));
        vo31.setValor(Valor.LIKE);
        vo31.setVoto(v3);
        
        VotoOpcao vo32 = new VotoOpcao();
        vo32.setOpcao(e.getOpcoes().get(1));
        vo32.setValor(Valor.LIKE);
        vo32.setVoto(v3);
        
        VotoOpcao vo33 = new VotoOpcao();
        vo33.setOpcao(e.getOpcoes().get(2));
        vo33.setValor(Valor.LIKE);
        vo33.setVoto(v3);
        
        List<VotoOpcao> votos3 = new ArrayList();
        votos3.add(vo31);
        votos3.add(vo32);
        votos3.add(vo33);
        
        v3.setVotosOpcoes(votos3);
        
        VotoBusiness vb = new VotoBusiness();
        vb.inserir(v1);
        vb.inserir(v2);
        vb.inserir(v3);
    }
}