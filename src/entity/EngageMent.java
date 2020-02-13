package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "engagement")
public class EngageMent implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "eid")
	private Integer eid;

	@ManyToOne
	@JoinColumn(name = "nid")
	private Nurse nurse;

	@ManyToOne
	@JoinColumn(name = "pid")
	private Patient patient;

	@Column(name = "description")
	private String description;

	@Column(name = "createdate")
	private String createDate;
	
	@Column(name = "effectdate")
	private String effectDate;
	
	@Column(name = "expiredate")
	private String expireDate;
	
	@Column(name = "flag")
	private Integer flag;
	
	@Transient
	private String state;
	
	public EngageMent() {
		super();
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		if(flag == 1){
			setState("未执行");
		}else if(flag == 2){
			setState("已执行");
		}else if(flag == 3){
			setState("已失效");
		}
		return state;
	}
}
