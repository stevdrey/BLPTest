/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author srey
 */
@Entity
@Table(name = "tb_custumer", catalog = "db_blp_test", schema = "")
@NamedQueries({
    @NamedQuery(name = "TbCustumer.findAll", query = "SELECT t FROM TbCustumer t"),
    @NamedQuery(name = "TbCustumer.findByCusId", query = "SELECT t FROM TbCustumer t WHERE t.cusId = :cusId"),
    @NamedQuery(name = "TbCustumer.findByCusName", query = "SELECT t FROM TbCustumer t WHERE t.cusName = :cusName"),
    @NamedQuery(name = "TbCustumer.findByCusLastname", query = "SELECT t FROM TbCustumer t WHERE t.cusLastname = :cusLastname"),
    @NamedQuery(name = "TbCustumer.count", query = "SELECT COUNT(t.cusId) FROM TbCustumer t GROUP BY t.cusId")})
public class TbCustumer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cus_id")
    private Integer cusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cus_name")
    private String cusName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "cus_lastname")
    private String cusLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 190)
    @Column(name = "cus_email")
    private String cusEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cus_phone")
    private String cusPhone;
    @Column(name = "cus_birthday")
    @Temporal(TemporalType.DATE)
    private Date cusBirthday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cus_date_register")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cusDateRegister;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cus_comment")
    private String cusComment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 46)
    @Column(name = "cus_ip")
    private String cusIp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cus_agent")
    private String cusAgent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cus_os")
    private String cusOs;

    public TbCustumer() {
    }

    public TbCustumer(Integer cusId) {
        this.cusId = cusId;
    }

    public TbCustumer(Integer cusId, String cusName, String cusLastname, String cusEmail, 
            String cusPhone, Date cusDateRegister, String cusComment, String cusIp, 
            String cusAgent, String cusOs) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusLastname = cusLastname;
        this.cusEmail = cusEmail;
        this.cusPhone = cusPhone;
        this.cusDateRegister = cusDateRegister;
        this.cusComment = cusComment;
        this.cusIp = cusIp;
        this.cusAgent = cusAgent;
        this.cusOs = cusOs;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusLastname() {
        return cusLastname;
    }

    public void setCusLastname(String cusLastname) {
        this.cusLastname = cusLastname;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public Date getCusBirthday() {
        return cusBirthday;
    }

    public void setCusBirthday(Date cusBirthday) {
        this.cusBirthday = cusBirthday;
    }

    public Date getCusDateRegister() {
        return cusDateRegister;
    }

    public void setCusDateRegister(Date cusDateRegister) {
        this.cusDateRegister = cusDateRegister;
    }

    public String getCusComment() {
        return cusComment;
    }

    public void setCusComment(String cusComment) {
        this.cusComment = cusComment;
    }

    public String getCusIp() {
        return cusIp;
    }

    public void setCusIp(String cusIp) {
        this.cusIp = cusIp;
    }

    public String getCusAgent() {
        return cusAgent;
    }

    public void setCusAgent(String cusAgent) {
        this.cusAgent = cusAgent;
    }

    public String getCusOs() {
        return cusOs;
    }

    public void setCusOs(String cusOs) {
        this.cusOs = cusOs;
    }

    @Override
    public int hashCode() {
        int hash = 56;
        hash += Objects.hashCode(cusId);
        hash += Objects.hashCode(cusName);
        hash += Objects.hashCode(cusLastname);
        hash += Objects.hashCode(cusEmail);
        hash += Objects.hashCode(cusIp);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof TbCustumer) {
            TbCustumer other = (TbCustumer) object;
        
            return !((this.cusId == null && other.cusId != null) || 
                    (this.cusId != null && !this.cusId.equals(other.cusId)));
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "entity.TbCustumer[ cusId=" + cusId + " ]";
    }
    
}
