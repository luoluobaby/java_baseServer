package com.model.po;

import javax.naming.Name;
import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Jimmy
 * @date 2018/9/10 10:24
 */
@Entity
@Table(name = "tb_trades_two", catalog = "Test")
public class TbTradesTwo {
    private Integer id;
    private String name;
    private String state;
    private TbTradesOne tbTradesOneByParentId;

    public TbTradesTwo() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public TbTradesTwo(String name ,TbTradesOne one){
        this.name = name;
        this.state = "NORMAL";
        this.id =-1 ;
        this.tbTradesOneByParentId = one ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbTradesTwo that = (TbTradesTwo) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, state);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id" , nullable = false)
    public TbTradesOne getTbTradesOneByParentId() {
        return tbTradesOneByParentId;
    }

    public void setTbTradesOneByParentId(TbTradesOne tbTradesOneByParentId) {
        this.tbTradesOneByParentId = tbTradesOneByParentId;
    }
}
