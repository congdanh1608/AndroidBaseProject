package vn.danhtran.sociallogin;

/**
 * Created by congdanh on 04/04/2017.
 */
public class MyAccessToken {

    private final String token;
    private final String secret;
    private final String email;
    private final String username;
    private final String userId;

    private MyAccessToken(Builder builder) {
        token = builder.token;
        secret = builder.secret;
        email = builder.email;
        username = builder.username;
        userId = builder.userId;
    }

    public String getToken() {
        return token;
    }

    public String getSecret() {
        return secret;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public static class Builder {
        private final String token;
        private String secret;
        private String username;
        private String email;
        private String userId;

        public Builder(String token) {
            this.token = token;
        }

        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public MyAccessToken build() {
            return new MyAccessToken(this);
        }
    }
}
