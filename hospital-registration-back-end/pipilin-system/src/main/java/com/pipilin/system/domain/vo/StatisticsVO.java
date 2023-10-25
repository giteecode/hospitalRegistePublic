package com.pipilin.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int monthNum;

    private int totalNumber;
}
