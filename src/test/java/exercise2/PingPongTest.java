package exercise2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class PingPongTest {

    @Test
    @DisplayName("Should alternate between pings and pongs")
    void testPingPong() throws InterruptedException {
        PingPong pingPong = new PingPong();
        pingPong.start();
        List<String> results = new ArrayList<>(pingPong.getResults());

        boolean pingPongCondition = IntStream.range(0, results.size() - 1)
            .allMatch(i -> {
                String currentElement = results.get(i);
                return (currentElement.equals("ping") || currentElement.equals("pong"))
                        && !currentElement.equals(results.get(i + 1));
            });

        assertTrue(pingPongCondition, "Did not match ping-pong output");
    }
}
