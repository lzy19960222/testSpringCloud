package com.kuang.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//实体类
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Dept implements Serializable {
    private  Long deptno;//主键
    private  String dname;
    private  String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }
    /*
    链式写法：
    Dept dept = new Dept();
    dept.setDeptNo(11).setDeptname("sssss")
     */

}

