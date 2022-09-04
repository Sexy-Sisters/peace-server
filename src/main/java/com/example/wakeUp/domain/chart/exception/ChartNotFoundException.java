package com.example.wakeUp.domain.chart.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class ChartNotFoundException extends BusinessException {

    public static final ChartNotFoundException EXCEPTION = new ChartNotFoundException();

    private ChartNotFoundException() {
        super(ErrorCode.CHART_NOT_FOUND);
    }
}
