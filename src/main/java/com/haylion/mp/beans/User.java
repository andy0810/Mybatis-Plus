package com.haylion.mp.beans;

import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;

    @TableLogic
    private Integer logicFlag;
}
