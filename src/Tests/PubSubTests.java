package Tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class PubSubTests {
    @Test
    public void whenSubscribeToIt_thenShouldConsumeAll() {

        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        var lgr = Logger.getInstance();
        publisher.subscribe(lgr);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");

        // when
        Assert.assertEquals(publisher.getNumberOfSubscribers(), 1);

        items.forEach(publisher::submit);
        publisher.close();
    }
}
