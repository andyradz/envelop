var Page_ValidationActive = false;
if (typeof (ValidatorOnLoad) == "function") {
	ValidatorOnLoad();
}

function ValidatorOnSubmit() {
	if (Page_ValidationActive) {
		return ValidatorCommonOnSubmit();
	} else {
		return true;
	}
}

var Page_Validators = new Array(document.getElementById("rfvFirstName"),
		document.getElementById("rfvLastName"), document
				.getElementById("rfvEmail"), document
				.getElementById("revValidEmail"), document
				.getElementById("rfvUsername"), document
				.getElementById("revUsernameStrength"), document
				.getElementById("cvUsernameUnavailable"), document
				.getElementById("rfvPassword"), document
				.getElementById("revPasswordStrong"), document
				.getElementById("revPasswordNoSpaces"), document
				.getElementById("rfvConfirmPassword"), document
				.getElementById("cvConfirmPassword"), document
				.getElementById("rfvCompany"), document
				.getElementById("rfvPhone"), document
				.getElementById("rfvEventSize"), document
				.getElementById("rfvRevenueSize"), document
				.getElementById("cvTermOfService"));

var rfvFirstName = document.all ? document.all["rfvFirstName"] : document
		.getElementById("rfvFirstName");
rfvFirstName.controltovalidate = "txtFirstName";
rfvFirstName.display = "Dynamic";
rfvFirstName.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvFirstName.initialvalue = "";
var rfvLastName = document.all ? document.all["rfvLastName"] : document
		.getElementById("rfvLastName");
rfvLastName.controltovalidate = "txtLastName";
rfvLastName.display = "Dynamic";
rfvLastName.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvLastName.initialvalue = "";
var rfvEmail = document.all ? document.all["rfvEmail"] : document
		.getElementById("rfvEmail");
rfvEmail.controltovalidate = "txtEmail";
rfvEmail.display = "Dynamic";
rfvEmail.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvEmail.initialvalue = "";
var revValidEmail = document.all ? document.all["revValidEmail"] : document
		.getElementById("revValidEmail");
revValidEmail.controltovalidate = "txtEmail";
revValidEmail.display = "Dynamic";
revValidEmail.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
revValidEmail.validationexpression = "\\w+([-+.\']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
var rfvUsername = document.all ? document.all["rfvUsername"] : document
		.getElementById("rfvUsername");
rfvUsername.controltovalidate = "txtUserName";
rfvUsername.display = "Dynamic";
rfvUsername.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvUsername.initialvalue = "";
var revUsernameStrength = document.all ? document.all["revUsernameStrength"]
		: document.getElementById("revUsernameStrength");
revUsernameStrength.controltovalidate = "txtUserName";
revUsernameStrength.display = "Dynamic";
revUsernameStrength.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
revUsernameStrength.validationexpression = "^[\\w!#$%&*@+/=?^_{}~.-]{6,100}$";
var cvUsernameUnavailable = document.all ? document.all["cvUsernameUnavailable"]
		: document.getElementById("cvUsernameUnavailable");
cvUsernameUnavailable.controltovalidate = "txtUserName";
cvUsernameUnavailable.display = "Dynamic";
cvUsernameUnavailable.evaluationfunction = "CustomValidatorEvaluateIsValid";
cvUsernameUnavailable.clientvalidationfunction = "ValidateUserName";
var rfvPassword = document.all ? document.all["rfvPassword"] : document
		.getElementById("rfvPassword");
rfvPassword.controltovalidate = "txtPassword";
rfvPassword.display = "Dynamic";
rfvPassword.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvPassword.initialvalue = "";
var revPasswordStrong = document.all ? document.all["revPasswordStrong"]
		: document.getElementById("revPasswordStrong");
revPasswordStrong.controltovalidate = "txtPassword";
revPasswordStrong.display = "Dynamic";
revPasswordStrong.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
revPasswordStrong.validationexpression = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z]).*$";
var revPasswordNoSpaces = document.all ? document.all["revPasswordNoSpaces"]
		: document.getElementById("revPasswordNoSpaces");
revPasswordNoSpaces.controltovalidate = "txtPassword";
revPasswordNoSpaces.display = "Dynamic";
revPasswordNoSpaces.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
revPasswordNoSpaces.validationexpression = "^[^\\s+]*$";
var rfvConfirmPassword = document.all ? document.all["rfvConfirmPassword"]
		: document.getElementById("rfvConfirmPassword");
rfvConfirmPassword.controltovalidate = "txtConfirmPassword";
rfvConfirmPassword.display = "Dynamic";
rfvConfirmPassword.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvConfirmPassword.initialvalue = "";
var cvConfirmPassword = document.all ? document.all["cvConfirmPassword"]
		: document.getElementById("cvConfirmPassword");
cvConfirmPassword.controltovalidate = "txtConfirmPassword";
cvConfirmPassword.display = "Dynamic";
cvConfirmPassword.evaluationfunction = "CompareValidatorEvaluateIsValid";
cvConfirmPassword.controltocompare = "txtPassword";
cvConfirmPassword.controlhookup = "txtPassword";
var rfvCompany = document.all ? document.all["rfvCompany"] : document
		.getElementById("rfvCompany");
rfvCompany.controltovalidate = "txtCompany";
rfvCompany.display = "Dynamic";
rfvCompany.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvCompany.initialvalue = "";
var rfvPhone = document.all ? document.all["rfvPhone"] : document
		.getElementById("rfvPhone");
rfvPhone.controltovalidate = "txtPhone";
rfvPhone.display = "Dynamic";
rfvPhone.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvPhone.initialvalue = "";
var rfvEventSize = document.all ? document.all["rfvEventSize"] : document
		.getElementById("rfvEventSize");
rfvEventSize.controltovalidate = "ddlEventSize";
rfvEventSize.display = "Dynamic";
rfvEventSize.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvEventSize.initialvalue = "";
var rfvRevenueSize = document.all ? document.all["rfvRevenueSize"] : document
		.getElementById("rfvRevenueSize");
rfvRevenueSize.controltovalidate = "ddlRevenueSize";
rfvRevenueSize.display = "Dynamic";
rfvRevenueSize.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
rfvRevenueSize.initialvalue = "";
var cvTermOfService = document.all ? document.all["cvTermOfService"] : document
		.getElementById("cvTermOfService");
cvTermOfService.display = "Dynamic";
cvTermOfService.evaluationfunction = "CustomValidatorEvaluateIsValid";
cvTermOfService.clientvalidationfunction = "ValidateTermOfService";

if ("1" == "1")
	$('#txtPhone').mask('(999) 999-9999? x99999');

var _ValidatorUpdateDisplay = ValidatorUpdateDisplay;
ValidatorUpdateDisplay = function(val) {
	for (var i = 0, v; i < Page_Validators.length
			&& (v = Page_Validators[i]) != val; i++) {
		if (v.controltovalidate == val.controltovalidate && !v.isvalid)
			val.isvalid = true;
	}
	_ValidatorUpdateDisplay(val);
};