package itmo.soa.baseservice.exception;

public class ImpossibleRaffleException extends CustomException {
    public ImpossibleRaffleException() {
        super("невозможно провести розыгрыш");
    }
}
