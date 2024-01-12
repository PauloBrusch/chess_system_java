package boardgame;

import javax.management.RuntimeMBeanException;

public class BoardException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BoardException(String msg) {
        super(msg);
    }
}
