package com.han.constants;

public class EndPoint {
  public static final String COMPANY_ID = "companyId";
  public static final String COMPANY = "/company";

  public static final String COMPANY_DETAIL = COMPANY + "/{" + COMPANY_ID + "}";

}
