package com.smartgroup.backend.business;

import com.smartgroup.backend.bean.Usuario;
import com.smartgroup.backend.infra.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioBusiness {
    
    public long inserir(Usuario u) {
        u.setDataRegistro(new Date());
        u.setAtivo(true);
        u.setSenha(DigestUtils.sha256Hex(u.getSenha()));
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(u);
        t.commit();
        
        return u.getId();
    }
    
    public void alterar(Usuario u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(u);
        t.commit();
    }
    
    public void excluir(long id) {
        Usuario u = selecionar(id);
        u.setAtivo(false);
        alterar(u);
    }
    
    public Usuario selecionar(long id) {
        return (Usuario) HibernateUtil.getSessionFactory().openSession()
                .get(Usuario.class, id);
    }
    
    public List<Usuario> listar() {
        return (List<Usuario>) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Usuario").list();
    }
    
    public List<Usuario> listarByEnquete(long id) {
        /*Session s = HibernateUtil.getSessionFactory().openSession();
        
        return (List<Usuario>) s.createQuery("from Enquete e join Voto v where 
                v.autor=:id")
                .setParameter("id", id)
                .list();*/
        return null;
    }
}
