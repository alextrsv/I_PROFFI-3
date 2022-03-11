package itmo.soa.baseservice.exception;

public class NoSuchPrizeException  extends CustomException {
    public NoSuchPrizeException() {
        super("no such Prize");
    }
}
