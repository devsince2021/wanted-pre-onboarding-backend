package com.han.constants;

public class EndPoint {
  public static final String API = "/api";
  public static final String COMPANY_ID = "companyId";
  public static final String COMPANY = "/company";
  public static final String COMPANY_DETAIL = COMPANY + "/{" + COMPANY_ID + "}";

  public static final String POST = "/post";

  public static final String POST_ID = "postId";

  public static final String POST_DETAIL = POST + "/{" + POST_ID + "}";
}
