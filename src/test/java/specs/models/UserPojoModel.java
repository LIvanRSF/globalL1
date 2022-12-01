package specs.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class UserPojoModel {

    private String login;
    private String id;
    private String url;
    @SerializedName("repos_url")
    private String reposUrl;
    private String type;
    private String name;
    private String company;
    private String location;
    private String email;
    @SerializedName("twitter_username")
    private String twitterNickname;
}
