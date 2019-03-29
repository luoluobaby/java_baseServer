package com.model.po;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Jimmy
 * @date 2018/9/10 10:24
 */
@Entity
@Table(name = "tb_trades_one", catalog = "Test")
public class TbTradesOne {
    private Integer id;
    private String name;
    private String state;
    private String type;

    public TbTradesOne() {
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
    @Column(name = "state", nullable = false,length = 11)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "type", nullable = false,length = 7)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbTradesOne that = (TbTradesOne) o;
        return  Objects.equals(id, that.id)&&
                Objects.equals(name, that.name) &&
                Objects.equals(state, that.state) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, state, type);
    }

    public TbTradesOne(String name) {
        this.name = name;
        this.id = -1 ;
        this.state ="NORMAL";
        this.type ="GOODS";
    }
}
