package reqsense;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import service.TextService;

@Component
@EnableAutoConfiguration
@ComponentScan({"docmodel", "reqsense", "service"})
public class ReqSenseApplication extends WebApplication {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private TextService textService;

    /**
     * Spring Boot main method to build context.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ReqSenseApplication.class, args);

    }

    @Override
    public Class<? extends Page> getHomePage() {
        return StartPage.class;
    }


    @Override
    protected void init() {
        super.init();
        /*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //Scan package for annotated beans
        ctx.scan("reqsense", "docmodel");
        ctx.refresh();*/
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
        System.err.println("\n\n****************\nSay hello: " + textService.hello() + "\n****************\n\n");
    }

}
