package uiObjInfo;

public class UILocators {
	// gLogin
	public String gsiginlink_xp = "//a[text()='Sign in']";
	public String gUserName_id = "id=identifierId";
	public String gnext_xp = "//span[text()='Next']";
	public String gPassword_xp = "//input[@name='password']";
	public String gLoginbtn_xp = "//span[text()='Next']";
	// gLogout
	public String guser_xp = "//a[contains(text(),'Google Account')]";
	// this can also used instead of text "//a[contains(.,'Google Account')]"
	public String glogout_xp = "//a[.='Sign out']"; 
	// Login page
	public String username_id = "id=userid";
	public String password_id = "id=password";
	public String signin_id = "id=btnActive";	
	// Home page
	public String home_id =   "id=pt1:_UIShome::icon";
	public String returnToHome_xp = "//div[1]/table/tbody/tr/td[@id='pt1:_UISgc3']" ;  
	//Work Definition Navigation
	public String SCE_xp= "//a[contains(@id,'supply_chain_execution')]";
	public String workdef_xp= "//a[contains(@id,'manufacturing_work_definition')]";
	// logout
	public String Logout_xp = "//td[@id='pt1:_UISgc5']";
	public String Signout_xp = "//a[@id='pt1:_UISlg1']";
	public String Confirm_xp = "//button[@id='Confirm']";
	//Taskbar
	public String Task_xp="//img[@title='Tasks']";  
	//Manage Work Definitions
	public String MWDLink_xp="//a[text()='Manage Work Definitions']";	
	// input Item
	public String ItemInput_xp = "//input[contains(@id,'AP1:qryId1:value00::content')]";
	// input WrokDef
	public String WorkDefLOV_xp = "//a[contains(@id,'AP1:qryId1:value10::lovIconId')]";			
	public String WDSearchLink_xp =  "//a[contains(@id,'AP1:qryId1:value10::dropdownPopup::popupsearch')]";
	public String WDNameInput_xp = "//input[contains(@id,'AP1:qryId1:value10::_afrLovInternalQueryId:value00::content')]";
	public String WDNameSerch_xp = "//button[contains(@id,':AP1:qryId1:value10::_afrLovInternalQueryId::search')]";	
	public String WDNameresult_xp = "//div[contains(@id,':AP1:qryId1:value10_afrtablegridcell')]//td//td[1]//span[1]";
	public String WDNameOK_xp =  "//button[contains(@id,'AP1:qryId1:value10::lovDialogId::ok')]";	
	// Search button
	public String searchButton_xp = "//button[contains(@id,'AP1:qryId1::search')]";
	// searchRestults
	public String AddWDIcon_xp = "//img[contains(@id,':AP1:AT2:_ATp:create::icon')]";	
	// results table records
	public String ResultTable_xp = "//div[2]/table/tbody/tr/td[2]/div/table/tbody/tr/td[1]";
	// clcik version symbol
	public String VersionIcon_xp = "//div[2]/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td[6]/span//img[contains(@id,':cil0::icon')]";
	//  //img[@id='pt1:_FOr1:1:_FOSritemNode_manufacturing_work_definition:0:MAt2:0:AP1:AT2:_ATp:wdTbl:1:cil0::icon']  -- for 2nd record
	public String VersionIconpart1_xp = "//div[2]/table/tbody/tr[";			
	public String VersionIconpart2_xp = "]/td[2]/div/table/tbody/tr/td[6]/span//img[contains(@id,':cil0::icon')]";	 	
	// Add version
	public String AddVersion_xp = "//img[contains(@id,'AT1:_ATp:create::icon')]";
	// Save and Edit
	public String SaveAndEdit_xp = "//span[contains(text(),'Save and Edit')]";
	// click on Add Operations Icon
	public String AddOperationsIcon_xp = "//img[contains(@id,'AP1:sdi1::icon')]";
	// New Supplier icon
	public String AddNewSupplierOperataion_xp ="//span[contains(@id,'AP1:r17:0:titleText2j_id_1')]";
	// clcik Actions down arrow
	public String ActionsArrow_xp = "//a[contains(@id,'AP1:r17:0:r3:0:ctb1::popEl')]";
	// select to click Assign
	public String ClickAssign_xp =  "//table[contains(@id,'m12::ScrollContent')]";
			
			
		// old one : 	"//tr[contains(@id,':r3:0:evn_cap_0')]//td[@class='xnw'][contains(text(),'Assign')]";
	// Sequence input
	public String SeqNum_xp = "//input[contains(@id,'AP1:r17:0:r8:0:it9::content')]";
	// Operation  Name Input
	public String OpName_xp ="//input[contains(@id,'AP1:r17:0:r8:0:it2::content')]";
	// Operation Description
	 public String OpDesc_xp = "//input[contains(@id,'AP1:r17:0:r8:0:it1::content')]";
	// Work Center 
	public String OpWorkCenter_xp ="//input[contains(@id,'AP1:r17:0:r8:0:soc1::content')]";
	//Lead
	public String OpLeadTime_xp ="//input[contains(@id,'AP1:r17:0:r8:0:it::content')]";	
	//OSP item
	public String OSPItem_xp = "//input[contains(@id,'AP1:r17:0:r8:0:OspItemNumber:lovTxtId::content')]";
	//OSP supplier
	public String OSPSupplier_xp = "//input[contains(@id,'AP1:r17:0:r8:0:supplierNameId::content')]";
	// lead time
	public String UOM_xp ="//select[contains(@id,'AP1:r17:0:r8:0:soc3::content')]";
	// fixed hours
	public String Fixed_xp = "//input[contains(@id,'AP1:r17:0:r8:0:it14::content')]";
	//generate shipment
	public String GenShipment_xp = "//label[contains(@id,'::Label0')]";
	// click OK
	public String OKButton_xp = "//button[contains(@id,'0:cb1')]";
		// 	"//span[contains(text(),'K')]";
	// Save and Close1
	public String SaveAndClose_xp = "//button[contains(@id,'AP1:cb2')]";
	public String  	SaveandCloseBtn_xp = "//button[contains(text(),'ave and Close')]";
	// done  D or ne	
	public String DoneButton_xp = "//span[text()='D']";
	///***************** Workf Def ********************************************//


}