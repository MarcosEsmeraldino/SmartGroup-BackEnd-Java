package com.smartgroup.business;

import com.smartgroup.bean.Voto;
import com.smartgroup.infra.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VotoBusiness {
    
    public long inserir(Voto v) {
        
        v.setDataRegistro(new Date());
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(v);
        t.commit();
        
        return v.getId();
    }
    
    public void alterar(Voto v) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(v);
        t.commit();
    }
    
    public void excluir(long id) {
        Session v = HibernateUtil.getSessionFactory().openSession();

        Voto c = selecionar(id);

        Transaction t = v.beginTransaction();
        v.delete(c);
        t.commit();
    }
    
    public Voto selecionar(long id) {
        return (Voto) HibernateUtil.getSessionFactory().openSession()
                .get(Voto.class, id);
    }
    
    public List<Voto> listar() {
        return (List<Voto>) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Voto").list();
    }
    
    public List<Voto> listarByEnquete(long idEnquete) {
        return (List<Voto>) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Voto where enquete_id = :enquete_id")
                .setLong("enquete_id", idEnquete)
                .list();
    }
    
    public long getListSizeByEnquete(long idEnquete) {
        return (long) HibernateUtil.getSessionFactory().openSession()
                .createQuery(
                "select count(*) from Voto where enquete_id = :enquete_id")
                .setLong("enquete_id", idEnquete)
                .uniqueResult();
    }
}
