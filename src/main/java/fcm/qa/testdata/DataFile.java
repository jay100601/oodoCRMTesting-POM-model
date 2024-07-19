package fcm.qa.testdata;

import fcm.qa.util.Xls_Reader;

public class DataFile {

	Xls_Reader d = new Xls_Reader("C:\\QA\\Selanium_Workspace\\FreeCRMAutomationTest\\Contact.xlsx");
	
	public  String name = d.getCellData("Sheet1", "Name", 2);
	public  String companyName = d.getCellData("Sheet1", "CompanyName", 2);
	public  String jobPosition = d.getCellData("Sheet1", "JobPosition", 2);
	public  String phone = d.getCellData("Sheet1", "Phone", 2);
	public  String mobile = d.getCellData("Sheet1", "Mobile", 2);
	public  String email = d.getCellData("Sheet1", "Email", 2);

}
