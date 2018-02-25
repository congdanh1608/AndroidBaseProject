package vn.danhtran.customglide.customsize;

/**
 * Created by danhtran on 17/02/2017.
 */
public class CustomImageSizeModelFutureStudio implements CustomImageSizeModel {

    String baseImageUrl;

    public CustomImageSizeModelFutureStudio(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    @Override
    public String requestCustomSizeUrl(int width, int height) {
        return baseImageUrl + "?w=" + width + "&h=" + height;
    }
}