package com.haocai.module.system.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMajorParam implements Serializable {
    private int page;
    private int limit;
    private String name;
    private String deptName;
}
