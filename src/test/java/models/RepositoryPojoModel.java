package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class RepositoryPojoModel {

    private String id;
    private String name;
    @SerializedName("private")
    private boolean isPrivate;
    private String description;
    private RepoOwner owner;

    @Data
    @Accessors(fluent = true)
    public static class RepoOwner {

        private String login;
    }
}
