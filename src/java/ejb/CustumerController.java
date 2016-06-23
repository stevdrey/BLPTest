/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.TbCustumer;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author srey
 */
@Named(value = "custumerController")
@SessionScoped
public class CustumerController implements Serializable {
    @EJB
    private CustomerService service;
    private TbCustumer current;
    private ResourceBundle bundle= null;

    public CustumerController() {
        super();
        
        this.bundle= ResourceBundle.getBundle("resources.i18n.messages");
        this.current= new TbCustumer();
    }
      
    public void addCustumer() {
        HttpServletRequest request= ((HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest());
        
        if (request != null) {
            String userAgent= request.getHeader("user-agent");
            
            this.current.setCusOs(this.getClientOsName(userAgent));
            this.current.setCusIp(request.getRemoteAddr());
            this.current.setCusAgent(this.getClientAgent(userAgent));
        }
        
        this.current.setCusDateRegister(Date.from(LocalDateTime.now().
                atZone(ZoneId.systemDefault()).toInstant()));
        
        if (this.service.add(this.current)) {
            PrimeFacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                            bundle.getString("cus.success.add.summary"), 
                            String.format(bundle.getString("cus.success.add.detail"), 
                                    this.current.getCusName(), 
                                    this.current.getCusLastname())));
            
            this.cleanCurrentCustumer();
        }
    }
    
    public void onCleanAction() {
        this.cleanCurrentCustumer();
    }
    
    public long getCountCustomers() {
        return this.service.getCount(false);
    }
    
    public boolean showButton() {
        return getCountCustomers() > 0;
    }

    public TbCustumer getCurrent() {
        return current;
    }

    public void setCurrent(TbCustumer current) {
        this.current = current;
    }
    
    public String getPhoneMask() {
        String mask= "";
        HttpServletRequest request= ((HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest());
        
        Locale locale= request != null ? request.getLocale() : 
                FacesContext.getCurrentInstance().getViewRoot().getLocale();
        
        if (locale.getCountry().toLowerCase().equals("cr"))
            mask= "9999-9999";
        
        else
            mask= "(999) 9999-9999";
        
        return mask;
    }
    
    public String getPatternDate() {
        String pattern= "";
        HttpServletRequest request= ((HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest());
        Locale locale= request != null ? request.getLocale() : 
                FacesContext.getCurrentInstance().getViewRoot().getLocale();
        DateFormat format= DateFormat.getDateInstance(DateFormat.SHORT, locale);
        
        if (format instanceof SimpleDateFormat)
            pattern= ((SimpleDateFormat) format).toPattern();
        
        else
            pattern= "MM/dd/yyyy";
        
        return pattern;
    }
    
    public void onShowCustumerDetails(ActionListener evt) {
        Map<String,Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("width", 800);
        options.put("height", 400);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        
        RequestContext.getCurrentInstance().openDialog("viewCustumers", options, null);
    }
    
    // section of private methods
    
    private void cleanCurrentCustumer() {
        this.current= new TbCustumer();
    }
    
    private String getClientOsName(String userAgent) {
        if (userAgent.toLowerCase().contains("windows") )
             return "Windows";
         
        else if(userAgent.toLowerCase().contains("mac"))
            return "Mac";
        
        else if(userAgent.toLowerCase().contains("x11"))
            return "Unix";
        
        else if(userAgent.toLowerCase().contains("android"))
            return "Android";
        
        else if(userAgent.toLowerCase().contains("iphone"))
            return "IPhone";
        
        return "UnKnown OS ";
    }
    
    private String getClientAgent(String userAgent) {
        String user= userAgent.toLowerCase();
        
        if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            return substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
            
        } else if (user.contains("safari") && user.contains("version"))
        
            return (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).
                    split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).
                            split(" ")[0]).split("/")[1];
        
        else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera"))
                return (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).
                        split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).
                                split(" ")[0]).split("/")[1];
            
            else if(user.contains("opr"))
                return ((userAgent.substring(userAgent.indexOf("OPR")).
                        split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
            
        } else if (user.contains("chrome"))
            return (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        
        else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6"))  || (user.contains("mozilla/4.7")) || 
                (user.contains("mozilla/4.78")) || (user.contains("mozilla/4.08")) || (user.contains("mozilla/3")) )
            return "Netscape-?";

        else if (user.contains("firefox"))
            return (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        
        else if(user.contains("rv"))
            return "IE";
        
        return "UnKnown Agent";
    }
}