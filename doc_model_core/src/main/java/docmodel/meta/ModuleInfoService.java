package docmodel.meta;

import org.springframework.stereotype.Service;

@Service("moduleInfoService")
public class ModuleInfoService {

    public String message() {
        return "Hello from the Spring service";
    }
}
