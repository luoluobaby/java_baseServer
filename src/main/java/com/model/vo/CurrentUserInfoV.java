package com.model.vo;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.model.po.CurrentUserInfo;
import com.model.po.SimulatorInfo;

public class CurrentUserInfoV {
	/**
	 * 训练流水号
	 */
	@SerializedName(value="simulatorId")
	private String simulatorId;
	/**
	 * 模拟器序列号
	 */
	@SerializedName(value="EQUIPMENT_ID")
	private String equipmentId;
	
	@SerializedName(value="TRAIN_UNIT_CODE")
	private String trainUnitCode;
	@SerializedName(value="pupilNo")
	private String pupilNo;
	@SerializedName(value="pupilName")
	private String pupilName;
	@SerializedName(value="cardNo")
	private String cardNo;
	@SerializedName(value="EQUIPMENT_TYPE")
	private String equipmentType;
	
	@SerializedName(value="STAT")
	private byte stat;
	@SerializedName(value="DateTime")
	private Date dateTime;
	public String getEquipmentId() {
		return equipmentId;
	}
	
	/**
	 * 构造函数
	 * @param simulatorId
	 * @param equipmentId
	 * @param trainUnitCode
	 * @param pupilNo
	 * @param pupilName
	 * @param cardNo
	 * @param equipmentType
	 * @param stat
	 * @param dateTime
	 */
	public CurrentUserInfoV(String simulatorId, String trainUnitCode, String pupilNo,
			String pupilName, String cardNo, String equipmentType) {
		super();
		this.simulatorId = simulatorId;
		this.trainUnitCode = trainUnitCode;
		this.pupilNo = pupilNo;
		this.pupilName = pupilName;
		this.cardNo = cardNo;
		this.equipmentType = equipmentType;
	}

	public CurrentUserInfoV() {
		super();
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getTrainUnitCode() {
		return trainUnitCode;
	}
	public void setTrainUnitCode(String trainUnitCode) {
		this.trainUnitCode = trainUnitCode;
	}
	public String getPupilNo() {
		return pupilNo;
	}
	public void setPupilNo(String pupilNo) {
		this.pupilNo = pupilNo;
	}
	public String getPupilName() {
		return pupilName;
	}
	public void setPupilName(String pupilName) {
		this.pupilName = pupilName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getSimulatorId() {
		return simulatorId;
	}
	public void setSimulatorId(String simulatorId) {
		this.simulatorId = simulatorId;
	}
	
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public byte getStat() {
		return stat;
	}
	public void setStat(byte stat) {
		this.stat = stat;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "CurrentUserInfoAdditionV [simulatorId=" + simulatorId + ", equipmentId=" + equipmentId
				+ ", trainUnitCode=" + trainUnitCode + ", pupilNo=" + pupilNo + ", pupilName=" + pupilName + ", cardNo="
				+ cardNo + ", equipmentType=" + equipmentType + ", stat=" + stat + ", dateTime=" + dateTime + "]";
	}
}
