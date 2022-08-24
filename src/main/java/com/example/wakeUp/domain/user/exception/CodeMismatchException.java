package com.example.wakeUp.domain.user.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class CodeMismatchException extends BusinessException {

    public static  final  CodeMismatchException EXCEPTION = new CodeMismatchException();

    private CodeMismatchException() {
        super(ErrorCode.CODE_MISMATCH);
    }
}
