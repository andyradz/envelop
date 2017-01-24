package com.codigo.aplios.envelop.system.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import com.codigo.aplios.envelop.system.core.NumberHelper.DecimalPrecision;
import com.codigo.aplios.envelop.system.core.metrics.JUnitStopWatch;
import com.codigo.aplios.envelop.system.core.test.inf.Repeat;
import com.codigo.aplios.envelop.system.core.test.inf.RepeatRule;
import java.util.Random;
import org.junit.Before;

public class NumberHelperTest {

    @Rule
    public final JUnitStopWatch stopWatch = new JUnitStopWatch();

    @Rule
    public final RepeatRule repeatRule = new RepeatRule();

    private double value;

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Repeat(times = 1000)
    public void shouldReturnTrue() {
        final double myFraction = NumberHelper.fraction(1.7976931348623157, DecimalPrecision.PRECTO6);
        assertThat(0.797693, equalTo(myFraction));
    }

    @Before
    public void myRandom() {
        Random r = new Random();
        this.value = (r.nextInt((int) ((1000 - 1) * 100 + 1)) + 1 * 10) / 100.0;
        System.out.println(this.value);
    }

    // -----------------------------------------------------------------------------------------------------------------
}
