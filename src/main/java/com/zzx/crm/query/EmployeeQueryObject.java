package com.zzx.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zzx
 * @date 2021-01-26 17:27:45
 */
@Setter
@Getter
public class EmployeeQueryObject {
    private Integer page;
    private Integer rows;

    public Integer getStart() {
        return (this.page - 1) * (this.rows);
    }
}
