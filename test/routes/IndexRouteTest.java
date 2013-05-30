package routes;

import org.junit.Test;
import play.Logger;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 28/05/13
 * Time: 21:34
 */
public class IndexRouteTest {
    @Test
    public void contextViewerRoute() {
        Logger.info("Une requête GET pour récupérer les informations concernant les clusters Couchbase /contextViewer");
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/contextViewer"));
                Logger.info("status(result) : " + status(result));
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }

    @Test
    public void badRoute() {
        Logger.info("Une requête GET pour récupérer les informations concernant les clusters Couchbase /contextViewer");

        //TODO Change the deprecated function routeAndCall
        Result result = routeAndCall(fakeRequest(GET, "/bad"));
        assertThat(result).isNull();
/*
        // TODO This code doesn't work because of a null pointer exception (NPE) at runtime.
        // In fact, because the route doesn't exist, you get a NPE at Runtime with fakeApplication().
        // fakeApplication is necessary to run the route() method.
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/bad"));
                Logger.info("status(result) : " + status(result));
                assertThat(status(result)).isEqualTo(BAD_REQUEST);
            }
        });*/

    }
}
