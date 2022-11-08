package specs;

import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AuthSpec extends BaseSpec{
    public static String accept = "application/vnd.github+json";
    public static Header header = new Header("Authorization", "Bearer "
        + credentialsConfig.apiToken());

    public static RequestSpecification reqSpec = BaseSpec.baseRequest
        .accept(accept)
        .header(header);

    public static ResponseSpecification resSpec = BaseSpec.baseResponse;
}
