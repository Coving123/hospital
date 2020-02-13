package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "requirement")
public class RequireMent implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid")
	private Integer oid;

	@ManyToOne
	@JoinColumn(name = "nid")
	private Nurse nurse;

	@ManyToOne
	@JoinColumn(name = "pid")
	private Patient patient;

	@OneToOne
	@JoinColumn(name = "mid")
	private Medicine medicine;

	@Column(name = "total")
	private Integer total;
	
	@Column(name = "createdate")
	private String createDate;
	
	@Column(name = "flag")
	private Integer flag;
	
	@Column(name = "money", columnDefinition="double(16,2) default '0.00'")
	private double money;
	
	@Transient
	private String state;
	
	@Transient
	private String expireDate;
	
	public RequireMent() {
		super();
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getState() {
		if(flag == 1){
			setState("未处理");
		}else if(flag == 2){
			setState("已接受");
		}else if(flag == 3){
			setState("已失效");
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	
	
}