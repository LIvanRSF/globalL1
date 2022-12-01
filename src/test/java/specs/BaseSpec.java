package specs;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

import configs.CredentialsConfig;
import helpers.CustomApiListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

public class BaseSpec {

    public static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);

    public static RequestSpecification baseRequest = with()
        .log().uri()
        .log().body()
        .contentType(JSON)
        .filter(CustomApiListener.withCustomTemplates());

    public static ResponseSpecification baseResponse = new ResponseSpecBuilder()
        .log(LogDetail.BODY)
        .log(LogDetail.STATUS)
        .build();
}
