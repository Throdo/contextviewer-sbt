import controllers.routes;
import org.junit.Test;
import play.mvc.Content;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 19/05/13
 * Time: 09:07
 */
public class ContextViewerTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("ContextViewer application is ready to be developed !!!");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("ContextViewer application is ready to be developed !!!");
    }

    @Test
    public void callIndex() {
        Result result = callAction(routes.ref.ContextViewer.index());
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("ContextViewer application is under development with 2 OrangeCluster(s) !!!");
    }

}
