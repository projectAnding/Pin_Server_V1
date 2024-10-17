package com.server.pin.domain.auth.exception.impl;

import com.server.pin.domain.auth.exception.AuthExceptionDetails;
import com.server.pin.global.exception.CustomException;

public class PasswordWrong extends CustomException {
  private PasswordWrong() {super(AuthExceptionDetails.PASSWORD_WRONG);}

  public static final PasswordWrong INSTANCE = new PasswordWrong();
}
