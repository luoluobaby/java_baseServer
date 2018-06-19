package com.service.model;
import java.io.Serializable;

public interface ServiceI<TP> {
	/**
	 * 更新用户数据
	 * @param user 传入的用户数据对象
	 */
	public void update(TP t);
	/**
	 * 删除用户，通过id
	 * @param ids 用户id列表,用","分隔
	 */
	public void delete(String ids);
	/**
	 * 查询用户数据，通过主键id
	 * @param id
	 * @return
	 */
	public TP queryByKey(String id);
	/**
	 * 插入数据
	 * @param userInfoV
	 */
	public Serializable insert(TP t);
	/**
	 * 验证数据合法性
	 * @param t
	 */
	public void Validate(TP t);
}
