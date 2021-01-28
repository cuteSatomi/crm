package com.zzx.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zzx
 * @date 2021-01-28 11:59:09
 */
@Setter
@Getter
public class QueryObject {
    private Integer page;
    private Integer rows;

    public Integer getStart() {
        return this.page == 0 ? 0 : (this.page - 1) * (this.rows);
    }
}
