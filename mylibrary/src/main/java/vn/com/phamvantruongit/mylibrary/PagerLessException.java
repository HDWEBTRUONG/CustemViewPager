package vn.com.phamvantruongit.mylibrary;

public class PagerLessException extends Exception {
    @Override
    public String getMessage() {
        return "Pages must equal or larger than 2";
    }
}
