package com.tn.sonede.jasper;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class MyJasperReportsDataSource implements JRDataSource {
	
	public Object[][]  data;
	public Integer size;
	
	private int index = -1;
	
	public MyJasperReportsDataSource() {
		super();
		 data = new Object[1000][6];
		 size=1;
	}
	
	public boolean next() throws JRException {
		index++;
		return (index < size);
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;

		String fieldName = field.getName();

		if (fieldName.equals("col0")) {
			value = data[index][0];
		} else if (fieldName.equals("col1")) {
			value = data[index][1];
		} else if (fieldName.equals("col2")) {
			value = data[index][2];
		} else if (fieldName.equals("col3")) {
			value = data[index][3];
		}  else if (fieldName.equals("col4")) {
			value = data[index][4];
		} else if (fieldName.equals("col5")) {
			value = data[index][5];
		}

		return value;
	}
}
