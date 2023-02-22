package net.mfjassociates.jasper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Value;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TestReport {
	@Value
	static public class Orders {
		private int ORDERID;
		private BigDecimal FREIGHT;
		private String SHIPNAME;
		private String SHIPADDRESS;
		private String SHIPCITY;
		private String SHIPCOUNTRY;
	}
	static private class OrdersFactory {
		public static List<Orders> getOrders() {
			return Arrays.asList(new Orders[] {
				new Orders(10248, BigDecimal.valueOf(32.432), "Vins et alcools Chevalier", "59 rue de l'Abbaye", "Reims", "France"),
				new Orders(10249, BigDecimal.valueOf(11.97332), "Tom Spezialitaten", "Luisenstr. 48", "Munster", "Germany")
			});
		}
	}
	
	public static void main(String[] args) throws JRException {
		JasperReport jr=JasperCompileManager.compileReport("my_simple_report.jrxml");
		JasperPrint jp=JasperFillManager.fillReport(jr, new HashMap(), new JRBeanCollectionDataSource(OrdersFactory.getOrders()));
		JasperExportManager.exportReportToPdfFile(jp, "target/my_simple_report.pdf");
	}

}
