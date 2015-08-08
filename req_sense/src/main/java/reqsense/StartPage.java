package reqsense;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.LoadableDetachableModel;
import org.springframework.beans.factory.annotation.Autowired;

import docmodel.meta.ModuleInfoService;

public class StartPage extends WebPage {

    @Autowired(required = true)
    private ModuleInfoService moduleInfoService;

    public StartPage() {

        Label message = new Label("message", new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                return moduleInfoService.message();
            }
        });
        add(message);
    }
}
