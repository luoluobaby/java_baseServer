package com.model.po;
// Generated 2018-6-19 8:57:24 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * DriveTrainCompanyInfo generated by hbm2java
 */
@Entity
@Table(name = "DriveTrainCompanyInfo", catalog = "SimulatorManagement", uniqueConstraints = @UniqueConstraint(columnNames = "numId"))
public class DriveTrainCompanyInfo implements java.io.Serializable {

	private String trainUnitCode;
	private String numId;
	private String name;
	private Set<SimulatorInfo> simulatorInfos = new HashSet<SimulatorInfo>(0);

	public DriveTrainCompanyInfo() {
	}

	public DriveTrainCompanyInfo(String trainUnitCode, String numId) {
		this.trainUnitCode = trainUnitCode;
		this.numId = numId;
	}

	public DriveTrainCompanyInfo(String trainUnitCode, String numId, String name, Set<SimulatorInfo> simulatorInfos) {
		this.trainUnitCode = trainUnitCode;
		this.numId = numId;
		this.name = name;
		this.simulatorInfos = simulatorInfos;
	}

	@Id

	@Column(name = "trainUnitCode", unique = true, nullable = false)
	public String getTrainUnitCode() {
		return this.trainUnitCode;
	}

	public void setTrainUnitCode(String trainUnitCode) {
		this.trainUnitCode = trainUnitCode;
	}

	@Column(name = "numId", unique = true, nullable = false, length = 20)
	public String getNumId() {
		return this.numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "driveTrainCompanyInfo")
	public Set<SimulatorInfo> getSimulatorInfos() {
		return this.simulatorInfos;
	}

	public void setSimulatorInfos(Set<SimulatorInfo> simulatorInfos) {
		this.simulatorInfos = simulatorInfos;
	}

}
