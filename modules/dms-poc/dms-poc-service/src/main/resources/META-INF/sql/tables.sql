create table DMSPOC_DmsPoc (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	groupId LONG,
	orgId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	modifiedBy LONG,
	modifiedByName VARCHAR(75) null,
	deleted BOOLEAN,
	repairOrderNo VARCHAR(75) null,
	dealerCode VARCHAR(75) null,
	authorisedDealerInfo VARCHAR(75) null,
	dealershipName VARCHAR(75) null,
	dealershipAddress VARCHAR(75) null,
	dealerShipPhoneNo LONG,
	faxNo LONG,
	dealershipEmail VARCHAR(75) null,
	GST VARCHAR(75) null,
	customerName VARCHAR(75) null,
	customerAddress VARCHAR(75) null,
	customerMobileNo LONG,
	customerEmailAddress VARCHAR(75) null,
	contactPerson VARCHAR(75) null,
	contactPersonPhone LONG,
	regNo VARCHAR(75) null,
	VIN VARCHAR(75) null,
	dateOfDelivery DATE null,
	kmsIn LONG,
	model VARCHAR(75) null,
	engineNo VARCHAR(75) null,
	insurancePolicyNo VARCHAR(75) null,
	insuranceExpiryDate DATE null,
	rsaRequired BOOLEAN,
	appointmentType VARCHAR(75) null,
	orderDateIn DATE null,
	orderTimeIn DATE null,
	serviceAdvisor VARCHAR(75) null,
	serviceAdvisorMobileNo LONG,
	soldByDealer VARCHAR(75) null,
	customerMobilityType VARCHAR(75) null,
	transactionNo VARCHAR(75) null,
	reasonForVisit VARCHAR(75) null,
	extendedWarranty VARCHAR(75) null,
	servicePlan VARCHAR(75) null,
	customerOrderDescription VARCHAR(75) null,
	complaint VARCHAR(75) null,
	workToBeDone VARCHAR(75) null,
	instruction VARCHAR(75) null,
	carPicture VARCHAR(75) null,
	xDent VARCHAR(75) null,
	scratch VARCHAR(75) null,
	bodyDamage VARCHAR(75) null,
	modificationOrNonOBMFittings VARCHAR(75) null,
	tyreCondition VARCHAR(75) null,
	treadDepth VARCHAR(75) null,
	frontRight VARCHAR(75) null,
	frontLeft VARCHAR(75) null,
	rearRight VARCHAR(75) null,
	rearLeft VARCHAR(75) null,
	windowsGlazzing VARCHAR(75) null,
	wiperBlades VARCHAR(75) null,
	lights VARCHAR(75) null,
	exhaustSystem VARCHAR(75) null,
	underbody VARCHAR(75) null,
	engineCompartment VARCHAR(75) null,
	washing VARCHAR(75) null,
	oilLevel VARCHAR(75) null,
	coolant VARCHAR(75) null,
	windshieldWasher VARCHAR(75) null,
	brakes VARCHAR(75) null,
	axle VARCHAR(75) null,
	monograms VARCHAR(75) null,
	polishing VARCHAR(75) null,
	fuel VARCHAR(75) null,
	ownersManual TEXT null,
	serScheMaintenanceManual VARCHAR(75) null,
	infotainmentMmiNavigManual VARCHAR(75) null,
	extWarrantyBook VARCHAR(75) null,
	quickReferenceGuide VARCHAR(75) null,
	spareWheel VARCHAR(75) null,
	jackToolKits VARCHAR(75) null,
	floorMat VARCHAR(75) null,
	mudFlaps VARCHAR(75) null,
	warningTriangle VARCHAR(75) null,
	firstAidKit VARCHAR(75) null,
	noOfKeys LONG,
	otherLooseItems VARCHAR(75) null,
	financialInformation VARCHAR(75) null,
	modeOfPayment VARCHAR(75) null,
	estimatedCost LONG,
	taxesAsApplicable VARCHAR(75) null,
	estimatedDateOfDelivery DATE null,
	estimatedDeliveryTime VARCHAR(75) null,
	laborEstimate LONG,
	parts LONG,
	termsAndConditions VARCHAR(75) null,
	grantMyConsent VARCHAR(75) null,
	serviceAdvisotSignature VARCHAR(75) null,
	customerSignature VARCHAR(75) null,
	remarksArea VARCHAR(75) null,
	termsStrictly VARCHAR(75) null,
	workshopCopy VARCHAR(75) null,
	version VARCHAR(75) null,
	customerWaiting BOOLEAN,
	campaign VARCHAR(75) null,
	dissNo VARCHAR(75) null,
	descriptiveInformation VARCHAR(75) null,
	timeStart VARCHAR(75) null,
	timeStop VARCHAR(75) null,
	nameOfTechnician VARCHAR(75) null,
	technicianSignature VARCHAR(75) null,
	inventoryPartsJSONStr VARCHAR(2048) null,
	partNumber VARCHAR(75) null,
	partDescription VARCHAR(75) null,
	partCatagory VARCHAR(75) null,
	internalPartsOrder VARCHAR(75) null,
	partQuantity VARCHAR(75) null,
	partRate VARCHAR(75) null,
	partCost VARCHAR(75) null,
	partTotal VARCHAR(75) null,
	partsJSONStr VARCHAR(2048) null,
	labourDescription VARCHAR(75) null,
	labourCatagory VARCHAR(75) null,
	labourCode VARCHAR(75) null,
	timeUnit VARCHAR(75) null,
	hours VARCHAR(75) null,
	labourRate VARCHAR(75) null,
	labourCost LONG,
	labourTotal LONG,
	labourJSONStr VARCHAR(2048) null,
	additionalRepairsReqDesc VARCHAR(75) null,
	additionalRepairsReqPart VARCHAR(75) null,
	additionalRepairsReqLabour VARCHAR(75) null,
	additionalRepairsReqAuth VARCHAR(75) null,
	additionalRepairsReqDT DATE null,
	roadTestKmOut LONG,
	roadTestKmIn LONG,
	allJobsAttended BOOLEAN,
	accessoriesSettingAsOriginal BOOLEAN,
	roadTestSignature VARCHAR(75) null,
	roadTestDate DATE null,
	interiors BOOLEAN,
	interiorsPolishing BOOLEAN,
	exteriors BOOLEAN,
	exteriorsPolishing BOOLEAN,
	vacuumCleaned BOOLEAN,
	washingCheckerSignature VARCHAR(75) null,
	status VARCHAR(75) null,
	toShow BOOLEAN
);