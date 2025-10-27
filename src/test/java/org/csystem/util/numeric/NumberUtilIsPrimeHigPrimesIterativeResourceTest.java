package org.csystem.util.numeric;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NumberUtilIsPrimeHigPrimesIterativeResourceTest {

    @Disabled("It can take too much time in some computers")
    @ParameterizedTest
    @ValueSource(strings = "src/test/resources/primes.txt")
    void textPrimes(String filePath) throws IOException
    {
        Files.lines(Path.of(filePath))
                .map(String::strip)
                .filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong)
                .forEach(n -> assertTrue(NumberUtil.isPrime(n)));

    }
}
