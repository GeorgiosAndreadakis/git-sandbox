package service;

import org.springframework.stereotype.Service;

@Service("textService")
public class TextService {
    public String hello() {
        return "Hello from TextService";
    }
}
