package com.objectcomputing.greatdivider;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.objectcomputing.greatdivider.DivisionWarningEnum.*;
import static org.apache.commons.lang3.math.NumberUtils.createBigDecimal;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

public class DivisionWarningGenerator implements BiFunction<String, String, DivisionWarningEnum> {


    @Override
    public DivisionWarningEnum apply(String dividend, String divisor) {
        boolean noBlanks = isCreatable(dividend) && isCreatable(divisor);
        boolean isZero = noBlanks && createBigDecimal(divisor).compareTo(BigDecimal.ZERO) == 0;
        if (!noBlanks) {
            return BLANKS;
        } else if (isZero) {
            return DIV_BY_ZERO;
        } else {
            return OK;
        }
    }

    @Override
    public <V> BiFunction<String, String, V> andThen(Function<? super DivisionWarningEnum, ? extends V> after) {
        return BiFunction.super.andThen(after);
    }
}

