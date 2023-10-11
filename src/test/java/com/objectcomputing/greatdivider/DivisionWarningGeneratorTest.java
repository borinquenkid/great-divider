package com.objectcomputing.greatdivider;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionWarningGeneratorTest {

    @ParameterizedTest
    @CsvSource({
            "3,2,OK",
            "3,0,DIV_BY_ZERO",
            ",0,BLANKS",
            "3,,BLANKS",

    })
    void testApply(String dividend, String divisor,String expectedResponse ) {
        DivisionWarningGenerator divisionWarningGenerator = new DivisionWarningGenerator();
        DivisionWarningEnum actualResponse = divisionWarningGenerator.apply(dividend, divisor);
        assertEquals(DivisionWarningEnum.getIfPresent(expectedResponse), actualResponse);

    }

}