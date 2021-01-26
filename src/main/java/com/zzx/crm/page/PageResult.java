package com.zzx.crm.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-26 17:25:12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private Integer total;
    private List rows;
}
