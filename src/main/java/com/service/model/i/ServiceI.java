package com.service.model.i;

import java.io.Serializable;
/**
 * 数据库操作接口
 * @author 阳
 *
 * @param <TP> 数据库读写类
 * @param <TK> 主键类型
 */
public interface ServiceI<TP,TK extends  Serializable> {

    /**
     * 更新用户数据
     * @param t 传入的用户数据对象
     */
    public void update(TP t);
    /**
     * 删除用户，通过id
     * @param ids 用户id列表,用","分隔
     * @return
     */
    public boolean delete(TK ids);
    /**
     * 查询用户数据，通过主键id
     * @param id
     * @return
     */
    public TP queryByKey(TK id);
    /**
     * 插入数据
     * @param t
     * @return
     */
    public Serializable insert(TP t);

    /**
     * 验证数据合法性
     * @param t
     */
    public void Validate(TP t);
}
