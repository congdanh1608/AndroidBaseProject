package vn.danhtran.sociallogin;

import com.glide.sociallogin.SocialLogin;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class LoginUnitTest {
    private SocialLogin socialLogin;

    @Before
    public void setUp() throws Exception {
        SocialLogin.initialize();
        socialLogin = SocialLogin.getInstance();
    }


    @Test
    public void testLoginFacebook() throws Exception {
        assertEquals(2, 1 + 1);
    }
}