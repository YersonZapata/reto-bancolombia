package co.com.reto.consumer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String CONTENT_TYPE="content-type";
    public static final String CONTENT_TYPE_TEXTPLAIN="text/plain";
    public static final String USER_NO_FOUND="Usuario no encontrado";
    public static final String USER_NO_AUTH="Usuario no autorizado";
    public static final String CREDENTIAL_PROBLEMS="Problema en secrets";
    public static final String HTTP_CODE_200="200";
    public static final String HTTP_CODE_201="201";
    public static final String HTTP_CODE_401="401";
    public static final String HTTP_CODE_404="404";
    public static final String HTTP_CODE_500="500";
    public static final String HTTP_CODE_503="503";



}
