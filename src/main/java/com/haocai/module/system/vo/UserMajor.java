package com.haocai.module.system.vo;

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
public class UserMajor implements Serializable {
    //用户ID
    private String userId;
    //姓名
    private String name;
    //专业名称
    private String majorName;
}
