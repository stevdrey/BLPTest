/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.TbCustumer;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author srey
 */
@Named(value = "customerDetail")
@RequestScoped
public class CustumerDetailController implements Serializable {
    @EJB
    private CustomerService service;
    private TbCustumer selectd;
    private List<TbCustumer> filterCustumers;
    
    public List<TbCustumer> getFilterCustumers() {
        return filterCustumers;
    }

    public void setFilterCustumers(List<TbCustumer> filterCustumers) {
        this.filterCustumers = filterCustumers;
    }
    
    public List<TbCustumer> getListOfCustumers() {
        return this.service.getAll();
    }

    public TbCustumer getSelectd() {
        return selectd;
    }

    public void setSelectd(TbCustumer selectd) {
        this.selectd = selectd;
    }
}