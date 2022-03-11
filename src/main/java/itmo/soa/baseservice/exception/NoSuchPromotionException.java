package itmo.soa.baseservice.exception;

public class NoSuchPromotionException extends CustomException{
    public NoSuchPromotionException() {
        super("no such promotion");
    }
}
