import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.execution.Parser;

public class ParserTest {

    @Test
    void isDate() {
        assertEquals(true,  Parser.isDate("02/12/2019 1800"));
        assertEquals(false, Parser.isDate("2 Jan 2019"));
    }
}
