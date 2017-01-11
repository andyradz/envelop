package com.codigo.aplios.envelop.system.core.test.proc;

@Factory(id = "Tiramisu", type = Meal.class) class Tiramisu implements Meal {

	@Override
	public float getPrice() {
		return 4.5f;
	}
}
