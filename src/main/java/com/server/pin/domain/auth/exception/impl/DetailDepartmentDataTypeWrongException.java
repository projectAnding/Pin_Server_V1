package com.server.pin.domain.auth.exception.impl;

import com.server.pin.domain.auth.exception.AuthExceptionDetails;
import com.server.pin.global.exception.CustomException;

public class DetailDepartmentDataTypeWrongException extends CustomException {
  private DetailDepartmentDataTypeWrongException() {super(AuthExceptionDetails.DETAILDEPARTMENT_DATA_WRONG);}

  public static final DetailDepartmentDataTypeWrongException INSTANCE = new DetailDepartmentDataTypeWrongException();
}
