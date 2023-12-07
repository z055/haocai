package com.haocai.module.outCon.vo.param;

import com.haocai.module.outCon.vo.ConTypeNameVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConTypeNameParam extends ConTypeNameVo {
    private int page;
    private int limit;
    private String conType;
}
