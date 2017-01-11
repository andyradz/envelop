package com.codigo.aplios.envelop.system.core.ui.gridview;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class BeanPropertyTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	private Object _bean;

	private String _nameColumnName = "Namecolumn";
	private String _valueColumnName = "Valuecolumn";

	public BeanPropertyTableModel() {
		super();
	}

	public void setBean(Object bean) throws RuntimeException {

		_bean = bean;
		refresh();
	}

	public void refresh() throws RuntimeException {

		final Vector<Object> columnNames = new Vector<Object>();
		columnNames.add(_nameColumnName);
		columnNames.add(_valueColumnName);
		final Vector<Object> columnData = new Vector<Object>();
		if (_bean != null) {
			try {
				BeanInfo info = Introspector.getBeanInfo(_bean.getClass(), Introspector.USE_ALL_BEANINFO);
				processBeanInfo(info, columnData);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}

		// Sort the rows by the property name.
		Collections.sort(columnData, new DataSorter());

		setDataVector(columnData, columnNames);
	}

	private void processBeanInfo(BeanInfo info, Vector<Object> columnData) throws InvocationTargetException,
			IllegalAccessException {

		BeanInfo[] extra = info.getAdditionalBeanInfo();
		if (extra != null) {
			for (int i = 0; i < extra.length; ++i) {
				processBeanInfo(extra[i], columnData);
			}
		}

		PropertyDescriptor[] propDesc = info.getPropertyDescriptors();
		for (int i = 0; i < propDesc.length; ++i) {
			final String propName = propDesc[i].getName();
			final Method getter = propDesc[i].getReadMethod();
			if (propName != null && getter != null) {
				Vector<Object> line = generateLine(propName, _bean, getter);
				if (line != null) {
					columnData.add(line);
				}
			}
		}
	}

	/**
	 * Generate a line for the passed property.
	 *
	 * @param propName
	 *        Name of the property.
	 * @param bean
	 *        Bean containg the property.
	 * @param getter
	 *        The "getter" function to retrieve the
	 *        properties value.
	 *
	 * @return A <CODE>Vector</CODE> containing the cells for the line in
	 *         the table. Element zero the first cell etc. Return
	 *         <CODE>null</CODE> if this property is <B>not</B> to be added
	 *         to the table.
	 */
	protected Vector<Object> generateLine(String propName, Object bean, Method getter) throws InvocationTargetException,
			IllegalAccessException {

		final Vector<Object> line = new Vector<Object>();
		line.add(propName);
		line.add(executeGetter(bean, getter));
		return line;
	}

	protected Object executeGetter(Object bean, Method getter) throws InvocationTargetException,
			IllegalAccessException {

		return getter.invoke(bean, (Object[]) null);
	}

	public void setNameColumnName(String value) {

		_nameColumnName = value;
	}

	public void setValueColumnName(String value) {

		_valueColumnName = value;
	}

	/**
	 * This comparator is compatible with the strange use of lists in this
	 * class. This classes lists are Vectors with Strings as the first element
	 * and any object as the other objects.
	 */
	private static final class DataSorter implements Comparator<Object> {
		@Override
		public int compare(Object o1, Object o2) {

			Vector<?> v1 = Vector.class.cast(o1);
			Vector<?> v2 = Vector.class.cast(o2);
			String lhs = (String) v1.get(0);
			String rhs = (String) v2.get(0);
			return lhs.compareToIgnoreCase(rhs);
		}
	}
}
