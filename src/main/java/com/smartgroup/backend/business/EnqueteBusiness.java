package com.smartgroup.backend.business;

import com.smartgroup.backend.bean.Enquete;
import com.smartgroup.backend.enumerate.enquete.Status;
import com.smartgroup.backend.infra.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EnqueteBusiness {
    
    public long inserir(Enquete e) {
        
        e.setDataRegistro(new Date());
        e.setStatus(Status.ATIVA);
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(e);
        t.commit();
        
        return e.getId();
    }
    
    public void alterar(Enquete e) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(e);
        t.commit();
    }
    
    public void excluir(long id) {
        Enquete e = selecionar(id);
        e.setStatus(Status.DESATIVADA);
        alterar(e);
    }
    
    public Enquete selecionar(long id) {
        return (Enquete) HibernateUtil.getSessionFactory().openSession()
                .get(Enquete.class, id);
    }
    
    public List<Enquete> listar() {
        return (List<Enquete>) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Enquete").list();
    }
    
    public List<Enquete> listarByAutor(long id) {
        return (List<Enquete>) HibernateUtil.getSessionFactory().openSession()
                .createQuery("from Enquete where autor_id=:autor_id")
                .setLong("autor_id", id)
                .list();
    }
    
    public List<Enquete> listarByAutorVoto(long id) {
        /*Session s = HibernateUtil.getSessionFactory().openSession();
        return (List<Enquete>) s.createQuery(
            "from Enquete e inner join Voto v where e.id=v.enquete and v.autor=:id")
            .setLong("id", id)
            .list();*/
        return null;
    }
    
    public List<Enquete> listarByUsuario(long id) {
        /*Session s = HibernateUtil.getSessionFactory().openSession();
        return (List<Enquete>) s.createQuery(
                "from Enquete inner join Voto v where "
                        + "autor_id=:autor_id"
                        + "|| v.autor_id = :v.autor_id")
                .setLong("autor_id", id)
                .setLong("v.autor_id", id)
                .list();*/
        return null;
    }
}
