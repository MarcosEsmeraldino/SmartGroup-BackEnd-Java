package com.smartgroup.backend.business;

import com.smartgroup.backend.bean.Comentario;
import com.smartgroup.backend.infra.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ComentarioBusiness {
    
    public long inserir(Comentario c) {
        
        c.setDataRegistro(new Date());
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(c);
        t.commit();
        
        return c.getId();
    }
    
    public void alterar(Comentario c) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(c);
        t.commit();
    }
    
    public void excluir(long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Comentario c = selecionar(id);

        Transaction t = s.beginTransaction();
        s.delete(c);
        t.commit();
    }
    
    public Comentario selecionar(long id) {
        return (Comentario) HibernateUtil.getSessionFactory().openSession()
                .get(Comentario.class, id);
    }
    
    public List<Comentario> listar() {
        return (List<Comentario>) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Comentario").list();
    }
    
    public List<Comentario> listarByEnquete(long idEnquete) {
        return (List<Comentario>) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Comentario where enquete_id=:enquete_id")
                .setLong("enquete_id", idEnquete)
                .list();
    }
    
    public long getListSizeByEnquete(long idEnquete) {
        return (long) HibernateUtil.getSessionFactory().openSession()
            .createQuery(
                "select count(*) from Comentario where enquete_id=:enquete_id")
                .setLong("enquete_id", idEnquete)
                .uniqueResult();
    }
}
