package itmo.soa.baseservice.exception;

public class NoSuchParticipantException extends CustomException{
    public NoSuchParticipantException() {
        super("no such promotion");
    }
}
