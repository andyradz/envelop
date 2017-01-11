package com.codigo.aplios.envelop.system.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import com.codigo.aplios.envelop.system.core.NumberHelper.DecimalPrecision;
import com.codigo.aplios.envelop.system.core.metrics.JUnitStopWatch;

public class NumberHelperTest {

	@Rule
	public final JUnitStopWatch stopWatch = new JUnitStopWatch();
	
	// -----------------------------------------------------------------------------------------------------------------

	@Test
	public void shouldReturnTrue() {
		final double myFraction = NumberHelper.fraction(1.7976931348623157, DecimalPrecision.PRECTO6);
		assertThat(Double.valueOf(0.797693).doubleValue(), equalTo(myFraction));
	}

	// -----------------------------------------------------------------------------------------------------------------
}

class Zero extends Stopwatch {

}