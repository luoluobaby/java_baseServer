package com.model.vo;

import com.google.gson.annotations.SerializedName;
import com.model.po.CurrentUserInfo;

public class CurrentUserInfoV {
	/**
	 * 训练流水号
	 */
	@SerializedName(value="simulatorId")
	private String simulatorId;
	/**
	 * 模拟器序列号
	 */
	@SerializedName(value="equipmentId")
	private String equipmentId;
	/**
	 * 驾校编号
	 */
	@SerializedName(value="trainUnitCode")
	private String trainUnitCode;
	/**
	 * 学员身份证号
	 */
	@SerializedName(value="pupilNo")
	private String pupilNo;
	/**
	 * 学员姓名
	 */
	@SerializedName(value="pupilName")
	private String pupilName;
	/**
	 * 学员卡号
	 */
	@SerializedName(value="cardNo")
	private String cardNo;
	/**
	 * 训练类型
	 */
	@SerializedName(value="equipmentType")
	private String equipmentType;
	
	@SerializedName(value="stat")
	private byte stat;
	
	@SerializedName(value="time")
	private float time;
	
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
			String pupilName, String cardNo, String equipmentType , String time) {
		super();
		this.trainUnitCode = trainUnitCode;
		this.pupilNo = pupilNo;
		this.pupilName = pupilName;
		this.cardNo = cardNo;
		this.equipmentType = equipmentType;
		//构造函数的时候就把驾校编号加到流水号里面去，让流水号变成唯一。
		this.simulatorId =trainUnitCode+"_"+simulatorId;
		this.time=Float.parseFloat(time);
	}

	public CurrentUserInfoV(CurrentUserInfo info) {
		super();
		this.trainUnitCode = info.getTrainUnitCode() ;
		this.pupilNo = info.getPupilNo();
		this.pupilName = info.getPupilName();
		this.cardNo = info.getCardNo();
		this.equipmentType = info.getEquipmentType();
		//构造函数的时候就把驾校编号加到流水号里面去，让流水号变成唯一。
		this.simulatorId =info.getSimulatorId();
		this.time=info.getTime();
	}
	
	public CurrentUserInfoV() {
		super();
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	/**
	 * 获得驾校编号
	 */
	public String getTrainUnitCode() {
		return trainUnitCode;
	}
	/**
	 * 设置驾校编号
	 */
	public void setTrainUnitCode(String trainUnitCode) {
		this.trainUnitCode = trainUnitCode;
	}
	/**
	 * 获得学员身份证号
	 */
	public String getPupilNo() {
		return pupilNo;
	}
	/**
	 * 设置学员身份证号
	 */
	public void setPupilNo(String pupilNo) {
		this.pupilNo = pupilNo;
	}
	/**
	 * 获得学员姓名
	 */
	public String getPupilName() {
		return pupilName;
	}
	/**
	 * 设置学员姓名
	 */
	public void setPupilName(String pupilName) {
		this.pupilName = pupilName;
	}
	/**
	 * 获得学员卡号
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * 设置学员卡号
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 获得训练流水号
	 */
	public String getSimulatorId() {
		return simulatorId;
	}
	/**
	 * 设置训练流水号
	 */
	public void setSimulatorId(String simulatorId) {
		this.simulatorId = simulatorId;
	}
	/**
	 * 获得训练类型
	 */
	public String getEquipmentType() {
		return equipmentType;
	}
	/**
	 * 设置训练类型
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	public byte getStat() {
		return stat;
	}
	public void setStat(byte stat) {
		this.stat = stat;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "CurrentUserInfoAdditionV [simulatorId=" + simulatorId + ", equipmentId=" + equipmentId
				+ ", trainUnitCode=" + trainUnitCode + ", pupilNo=" + pupilNo + ", pupilName=" + pupilName + ", cardNo="
				+ cardNo + ", equipmentType=" + equipmentType + ", stat=" + stat + ", time=" + time + "]";
	}
}
