/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.TbCustumer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.primefaces.context.PrimeFacesContext;

/**
 *
 * @author srey
 */
@Stateless
public class CustomerService {
    @PersistenceContext(name = "BLPTestPU")
    private EntityManager em;
    
    private ResourceBundle bundle= null;

    public CustomerService() {
        super();
        
        this.bundle= ResourceBundle.getBundle("resources.i18n.messages");
    }
    
    
    public boolean add(TbCustumer custumer) {
        boolean result= false;
        
        try {
            em.persist(custumer);
            
            result= true;
        } catch (EntityExistsException ex) {
            Logger.getLogger(CustomerService.class.getName()).
                    log(Level.SEVERE, ex.getMessage(), ex);
            
            PrimeFacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            String.format(bundle.getString("add.err"), 
                                    custumer.getCusName() + custumer.getCusLastname()), 
                            ex.getMessage()));
        }
        
        return result;
    }
    
    public List<TbCustumer> getAll() {
        Optional<List<TbCustumer>> listCustumers= Optional.<List<TbCustumer>>empty();
        
        try {
            listCustumers= Optional.
                    of(em.createNamedQuery("TbCustumer.findAll", TbCustumer.class).
                            getResultList());
        } catch (PersistenceException ex) {
            Logger.getLogger(CustomerService.class.getName()).
                    log(Level.SEVERE, ex.getMessage(), ex);
            
            PrimeFacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            bundle.getString("get.all.error"), ex.getMessage()));
        }
        
        return listCustumers.orElse(Collections.<TbCustumer>emptyList());
    }
    
    public Optional<TbCustumer> getById(int id) {
        Optional<TbCustumer> custumer= Optional.empty();
        
        try {
            custumer= Optional.
                    of(em.createNamedQuery("TbCustumer.findByCusId", TbCustumer.class).
                            setParameter("cusId", id).getSingleResult());
        } catch (PersistenceException ex) {
            Logger.getLogger(CustomerService.class.getName()).
                    log(Level.SEVERE, ex.getMessage(), ex);
            
            PrimeFacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            String.format(bundle.getString("get.by.id.error"), id), 
                            ex.getMessage()));
        }
        
        return custumer;
    }
    
    public long getCount(boolean showMessage) {
        long result= 0;
        
        try {
            result= em.createNamedQuery("TbCustumer.count", Long.class).
                    getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(CustomerService.class.getName()).
                    log(Level.SEVERE, ex.getMessage(), ex);
            
            if (showMessage) 
                PrimeFacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            bundle.getString("get.count.error"), 
                            ex.getMessage()));
        }
        
        return result;
    }
}
