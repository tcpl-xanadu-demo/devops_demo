package com.tc.demo.dms.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.tc.demo.dms.constants.CommonPortletKeys;
import com.tc.poc.dms.exception.NoSuchDmsPocException;
import com.tc.poc.dms.model.DmsPoc;
import com.tc.poc.dms.service.DmsPocLocalServiceUtil;

import java.io.IOException;
import java.util.Date;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author admin
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.dms",
		"com.liferay.portlet.instanceable=true", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jobcard-view.jsp", "javax.portlet.name=" + CommonPortletKeys.JobCard,
		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.add-default-resource=true" }, service = Portlet.class)
public class JobCardPortlet extends MVCPortlet {
	private static final Log log = LogFactoryUtil.getLog(JobCardPortlet.class);

	JSONObject jsonObj = null;

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		jsonObj = JSONFactoryUtil.createJSONObject();
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String cmd = ParamUtil.getString(resourceRequest, "cmd");
		log.debug("cmd:" + cmd);
		System.out.println("cmd:" + cmd);
		DmsPoc dmsPoc = null;
		if (cmd.equals("getRegVINDetails")) {
			String regNo = ParamUtil.getString(resourceRequest, "regNo");
			String vin = ParamUtil.getString(resourceRequest, "vin");
			try {
				if (vin != null && !vin.isEmpty()) {
					System.out.println("in side vin::" + vin);
					dmsPoc = DmsPocLocalServiceUtil.findByVIN(vin.trim());
					System.out.println("in side vin found");
					// dmsPoc = DmsPocServiceUtil.findByRegNoOrVIN("", vin);
					resourceRequest.setAttribute("dmsPoc", dmsPoc);
					getPortletContext().getRequestDispatcher("/jsp/createJobCard.jsp").include(resourceRequest,
							resourceResponse);
					System.out.println("done");
				} else if (regNo != null && !regNo.isEmpty()) {
					System.out.println("in side regNo");
					dmsPoc = DmsPocLocalServiceUtil.findByRegNo(regNo);
					System.out.println("in side found");
					resourceRequest.setAttribute("dmsPoc", dmsPoc);
					getPortletContext().getRequestDispatcher("/jsp/createJobCard.jsp").include(resourceRequest,
							resourceResponse);
				}
			} catch (NoSuchDmsPocException e) {
				System.out.println("NoSuchDmsPocException");
				// SessionErrors.a
				// SessionErrors.add(resourceRequest,"fetch-record-form-error");
				// e.printStackTrace();
				// resourceRequest.setAttribute("error", "No results found");
			} catch (Exception e) {
				System.out.println("Exception");
				// SessionErrors.a
				// SessionErrors.add(resourceRequest,"fetch-record-form-error");
				e.printStackTrace();
				// resourceRequest.setAttribute("error", "No results found");
			}
		} else if (cmd.equals("scanImage")) {
			String scannedFilterType = ParamUtil.getString(resourceRequest, "filterType");
			String regNo = ParamUtil.getString(resourceRequest, "regNo");
			String vin = ParamUtil.getString(resourceRequest, "vin");
			try {
				if (vin != null && !vin.isEmpty()) {
					System.out.println("in side vin::" + vin);
					dmsPoc = DmsPocLocalServiceUtil.findByVIN(vin.trim());
					System.out.println("in side vin found");
					// dmsPoc = DmsPocServiceUtil.findByRegNoOrVIN("", vin);
					resourceRequest.setAttribute("dmsPoc", dmsPoc);
					getPortletContext().getRequestDispatcher("/jsp/createJobCard.jsp").include(resourceRequest,
							resourceResponse);
					System.out.println("done");
				} else if (regNo != null && !regNo.isEmpty()) {
					System.out.println("in side regNo");
					dmsPoc = DmsPocLocalServiceUtil.findByRegNo(regNo);
					System.out.println("in side found");
					resourceRequest.setAttribute("dmsPoc", dmsPoc);
					getPortletContext().getRequestDispatcher("/jsp/createJobCard.jsp").include(resourceRequest,
							resourceResponse);
				}
			} catch (NoSuchDmsPocException e) {
				System.out.println("NoSuchDmsPocException");
				// SessionErrors.a
				// SessionErrors.add(resourceRequest,"fetch-record-form-error");
				// e.printStackTrace();
				// resourceRequest.setAttribute("error", "No results found");
			} catch (Exception e) {
				System.out.println("Exception");
				// SessionErrors.a
				// SessionErrors.add(resourceRequest,"fetch-record-form-error");
				e.printStackTrace();
				// resourceRequest.setAttribute("error", "No results found");
			}
			/*UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			File paramFile = uploadRequest.getFile("image");
			if (paramFile != null && paramFile.exists()) {
				System.out.println("paramFile found:");
			}
			log.debug("scannedFilterType:" + scannedFilterType);
			log.debug("scannedFilterType:" + scannedFilterType);*/
			System.out.println("scannedFilterType:" + scannedFilterType);
		} else if (cmd.equals("createJobCard")) {
			/* Code Changes by Manisha Gera */
			long id = ParamUtil.getLong(resourceRequest, "id");
			DmsPoc dmsPocTmp = null;
			try {
				dmsPocTmp = DmsPocLocalServiceUtil.find(id);
				String repairOrderNo = ParamUtil.getString(resourceRequest, "repairOrderNo");
				if (repairOrderNo != null && repairOrderNo.isEmpty()) {
					repairOrderNo = dmsPocTmp.getRepairOrderNo();
				}
				String customerName = ParamUtil.getString(resourceRequest, "customerName");
				System.out.println("customerName:"+customerName+"--"+customerName.isEmpty());
				if (customerName != null && customerName.isEmpty()) {
					System.out.println("customerName:"+customerName);
					customerName = dmsPocTmp.getCustomerName();
					System.out.println("customerName:"+customerName);
				}
				String inventoryPartsJSONArray = ParamUtil.getString(resourceRequest, "inventoryPartsJSONStr");
				if (inventoryPartsJSONArray != null && inventoryPartsJSONArray.isEmpty()) {
					inventoryPartsJSONArray = dmsPocTmp.getInventoryPartsJSONStr();
				}
				System.out.println("inventoryPartsJSONArray:"+inventoryPartsJSONArray);
				String partsJSONArray = ParamUtil.getString(resourceRequest, "partsJSONArray");
				if (partsJSONArray != null && partsJSONArray.isEmpty()) {
					partsJSONArray = dmsPocTmp.getPartsJSONStr();
				}
				String labourJSONArray = ParamUtil.getString(resourceRequest, "labourJSONArray");
				if (labourJSONArray != null && labourJSONArray.isEmpty()) {
					labourJSONArray = dmsPocTmp.getLabourJSONStr();
				}
				long customerMobileNo = 0L;
				String mobileNo = ParamUtil.getString(resourceRequest, "customerMobileNo");
				if (mobileNo != null && !mobileNo.isEmpty()) {
					customerMobileNo = Long.parseLong(mobileNo);
				}
				long contactPersonPhone = 0L;
				String contactPersonPhoneStr = ParamUtil.getString(resourceRequest, "contactPersonPhone");
				if (contactPersonPhoneStr != null && !contactPersonPhoneStr.isEmpty()) {
					contactPersonPhone = Long.parseLong(contactPersonPhoneStr);
				}
				String customerAddress = ParamUtil.getString(resourceRequest, "address");
				if (customerAddress != null && customerAddress.isEmpty()) {
					customerAddress = dmsPocTmp.getCustomerAddress();
				}
				String sa = ParamUtil.getString(resourceRequest, "sa");
				if (sa != null && sa.isEmpty()) {
					sa = dmsPocTmp.getServiceAdvisor();
				}
				long kmsIn = 0L;
				String kmsInStr = ParamUtil.getString(resourceRequest, "kms");
				if (kmsInStr != null && !kmsInStr.isEmpty()) {
					kmsIn = Long.parseLong(kmsInStr);
				}
				System.out.println("successfull customer name..2." + customerName);
				long serviceAdvisorMobileNo = 0L;
				String saPhone = ParamUtil.getString(resourceRequest, "saPhone");
				if (saPhone != null && !saPhone.isEmpty()) {
					serviceAdvisorMobileNo = Long.parseLong(saPhone);
				}
				String regNo = ParamUtil.getString(resourceRequest, "regNo");
				if (regNo != null && regNo.isEmpty()) {
					regNo = dmsPocTmp.getRegNo();
				}
				String vin = ParamUtil.getString(resourceRequest, "vin");
				if (vin != null && vin.isEmpty()) {
					vin = dmsPocTmp.getVIN();
				}
				String customerEmailAddress = ParamUtil.getString(resourceRequest, "emailaddress");
				if (customerEmailAddress != null && customerEmailAddress.isEmpty()) {
					customerEmailAddress = dmsPocTmp.getCustomerEmailAddress();
				}
				System.out.println("successfull customer name..3." + customerName);
				String appointmentType = ParamUtil.getString(resourceRequest, "appointmentType");
				if (appointmentType != null && appointmentType.isEmpty()) {
					appointmentType = dmsPocTmp.getAppointmentType();
				}
				String servicePlan = ParamUtil.getString(resourceRequest, "servicePlan");
				if (servicePlan != null && servicePlan.isEmpty()) {
					servicePlan = dmsPocTmp.getServicePlan();
				}
				String contactPerson = ParamUtil.getString(resourceRequest, "contactPerson");
				if (contactPerson != null && contactPerson.isEmpty()) {
					contactPerson = dmsPocTmp.getContactPerson();
				}
				String otherLooseItemsDes = ParamUtil.getString(resourceRequest, "otherLooseItemsDes");
				if (otherLooseItemsDes != null && otherLooseItemsDes.isEmpty()) {
					otherLooseItemsDes = dmsPocTmp.getContactPerson();
				}
				
				String dealerCode = ParamUtil.getString(resourceRequest, "dealerCode");
				String soldByDealer = ParamUtil.getString(resourceRequest, "soldByDealer");
				String model = ParamUtil.getString(resourceRequest, "model");
				String mobilityService = ParamUtil.getString(resourceRequest, "mobilityService");
				String xDent = ParamUtil.getString(resourceRequest, "xDent");
				String tyreCondition = ParamUtil.getString(resourceRequest, "tyreCondition");
				String scratch = ParamUtil.getString(resourceRequest, "scratch");
				String treadDepth = ParamUtil.getString(resourceRequest, "treadDepth");
				String bodyDamage = ParamUtil.getString(resourceRequest, "bodyDamage");
				String frontRight = ParamUtil.getString(resourceRequest, "frontRight");
				String frontLeft = ParamUtil.getString(resourceRequest, "frontLeft");
				String rearRight = ParamUtil.getString(resourceRequest, "rearRight");
				String rearLeft = ParamUtil.getString(resourceRequest, "rearLeft");
				System.out.println("successfull customer name..4." + customerName);
				String windowsGlazzing = ParamUtil.getString(resourceRequest, "windowsGlazing");
				String wiperBlades = ParamUtil.getString(resourceRequest, "wiperBlades");
				String lights = ParamUtil.getString(resourceRequest, "lights");
				String exhaustSystem = ParamUtil.getString(resourceRequest, "exhoustSystem");
				String underbody = ParamUtil.getString(resourceRequest, "underbody");
				String enginCompartment = ParamUtil.getString(resourceRequest, "enginCompartment");
				String washing = ParamUtil.getString(resourceRequest, "washing");
				String oilLevel = ParamUtil.getString(resourceRequest, "oilLevel");
				String coolant = ParamUtil.getString(resourceRequest, "coolant");
				String windshieldWasher = ParamUtil.getString(resourceRequest, "windshildWasher");
				String breaks = ParamUtil.getString(resourceRequest, "breaks");
				String axle = ParamUtil.getString(resourceRequest, "axle");
				String monograms = ParamUtil.getString(resourceRequest, "monograms");
				String polishing = ParamUtil.getString(resourceRequest, "polishing");
				String fule = ParamUtil.getString(resourceRequest, "fule");
				String ownerManual = ParamUtil.getString(resourceRequest, "ownerManual");
				String serScheMaintenanceManual = ParamUtil.getString(resourceRequest, "maintenanceManuals");
				String infotainmentMmiNavigManual = ParamUtil.getString(resourceRequest, "infotainmentMMI");
				String extWarrantyBook = ParamUtil.getString(resourceRequest, "extWarrantyBook");
				String quickReferenceGuide = ParamUtil.getString(resourceRequest, "quickReferenceGuide");
				String spareWheel = ParamUtil.getString(resourceRequest, "spareWheel");
				String jackToolKits = ParamUtil.getString(resourceRequest, "toolKit");
				String floorMat = ParamUtil.getString(resourceRequest, "floorMats");
				String mudFlaps = ParamUtil.getString(resourceRequest, "mudFlaps");
				String warningTriangle = ParamUtil.getString(resourceRequest, "warningTriangle");
				String firstAidKit = ParamUtil.getString(resourceRequest, "firstAidKit");
				String otherLooseItems = ParamUtil.getString(resourceRequest, "otherLooseItems");
				String customerVoice = ParamUtil.getString(resourceRequest, "customerVoice");
				String workToBeDone = ParamUtil.getString(resourceRequest, "workToBeDone");
				System.out.println("successfull customer name..1." + customerName);
				/* Need to store date in below variables */
				long noOfKeys = 0L;
				String authorisedDealerInfo = dmsPocTmp.getAuthorisedDealerInfo();
				String dealershipName = dmsPocTmp.getDealershipName();
				String dealershipAddress = dmsPocTmp.getDealershipAddress();
				long dealerShipPhoneNo = dmsPocTmp.getDealerShipPhoneNo();
				String dealershipEmail = dmsPocTmp.getDealershipAddress();
				long faxNo = dmsPocTmp.getFaxNo();
				String GST = dmsPocTmp.getGST();
				String engineNo = dmsPocTmp.getEngineNo();
				String insurancePolicyNo = dmsPocTmp.getInsurancePolicyNo();
				Date insuranceExpiryDate = dmsPocTmp.getInsuranceExpiryDate();
				boolean rsaRequired = dmsPocTmp.getRsaRequired();
				String transactionNo = dmsPocTmp.getTransactionNo();
				String reasonForVisit = dmsPocTmp.getReasonForVisit();
				String extendedWarranty = dmsPocTmp.getExtendedWarranty();
				String customerOrderDescription = dmsPocTmp.getCustomerOrderDescription();
				String instruction = dmsPocTmp.getInstruction();
				String carPicture = dmsPocTmp.getCarPicture();
				String modificationOrNonOBMFittings = dmsPocTmp.getModificationOrNonOBMFittings();
				String financialInformation = dmsPocTmp.getFinancialInformation();
				String modeOfPayment = dmsPocTmp.getModeOfPayment();
				long estimatedCost = dmsPocTmp.getEstimatedCost();
				String taxesAsApplicable = dmsPocTmp.getTaxesAsApplicable();
				Date estimatedDateOfDelivery = dmsPocTmp.getEstimatedDateOfDelivery();
				String estimatedDeliveryTime = dmsPocTmp.getEstimatedDeliveryTime();
				long laborEstimate = dmsPocTmp.getLaborEstimate();
				long parts = dmsPocTmp.getParts();
				String termsAndConditions = dmsPocTmp.getTermsAndConditions();
				String grantMyConsent = dmsPocTmp.getGrantMyConsent();
				String serviceAdvisotSignature = dmsPocTmp.getServiceAdvisotSignature();
				String customerSignature = dmsPocTmp.getCustomerSignature();
				String remarksArea = dmsPocTmp.getRemarksArea();
				String termsStrictly = dmsPocTmp.getTermsStrictly();
				String workshopCopy = dmsPocTmp.getWorkshopCopy();
				String version = dmsPocTmp.getVersion();
				boolean customerWaiting = dmsPocTmp.getCustomerWaiting();
				String campaign = dmsPocTmp.getCampaign();
				String dissNo = dmsPocTmp.getDissNo();
				String descriptiveInformation = dmsPocTmp.getDescriptiveInformation();
				String timeStart = dmsPocTmp.getTimeStart();
				String timeStop = dmsPocTmp.getTimeStop();
				String nameOfTechnician = dmsPocTmp.getNameOfTechnician();
				String technicianSignature = dmsPocTmp.getTechnicianSignature();
				String partNumber = dmsPocTmp.getPartNumber();
				String partDescription = dmsPocTmp.getPartDescription();
				String partCatagory = dmsPocTmp.getPartCatagory();
				String internalPartsOrder = dmsPocTmp.getInternalPartsOrder();
				String partQuantity = dmsPocTmp.getPartQuantity();
				String partRate = dmsPocTmp.getPartRate();
				String partCost = dmsPocTmp.getPartCost();
				String partTotal = dmsPocTmp.getPartTotal();
				String labourDescription = dmsPocTmp.getLabourDescription();
				String labourCatagory = dmsPocTmp.getLabourCatagory();
				String labourCode = dmsPocTmp.getLabourCode();
				String timeUnit = dmsPocTmp.getTimeUnit();
				String hours = dmsPocTmp.getHours();
				String labourRate = dmsPocTmp.getLabourRate();
				long labourCost = dmsPocTmp.getLabourCost();
				long labourTotal = dmsPocTmp.getLabourTotal();
				String additionalRepairsReqDesc = dmsPocTmp.getAdditionalRepairsReqDesc();
				String additionalRepairsReqPart = dmsPocTmp.getAdditionalRepairsReqPart();
				String additionalRepairsReqLabour = dmsPocTmp.getAdditionalRepairsReqLabour();
				String additionalRepairsReqAuth = dmsPocTmp.getAdditionalRepairsReqAuth();
				Date additionalRepairsReqDT = dmsPocTmp.getAdditionalRepairsReqDT();
				long roadTestKmOut = dmsPocTmp.getRoadTestKmOut();
				long roadTestKmIn = dmsPocTmp.getRoadTestKmIn();
				boolean allJobsAttended = dmsPocTmp.getAllJobsAttended();
				boolean accessoriesSettingAsOriginal = dmsPocTmp.getAccessoriesSettingAsOriginal();
				String roadTestSignature = dmsPocTmp.getRoadTestSignature();
				Date roadTestDate = dmsPocTmp.getRoadTestDate();
				boolean interiors = dmsPocTmp.getInteriors();
				boolean interiorsPolishing = dmsPocTmp.getInteriorsPolishing();
				boolean exteriors = dmsPocTmp.getExteriors();
				boolean exteriorsPolishing = dmsPocTmp.getExteriorsPolishing();
				boolean vacuumCleaned = dmsPocTmp.getVacuumCleaned();
				String washingCheckerSignature = dmsPocTmp.getWashingCheckerSignature();
				String status = dmsPocTmp.getStatus();

				Date orderDateIn = new Date();
				Date orderTimeIn = new Date();
				Date dateOfDel = new Date();
				System.out.println("successfull customer name..." + customerName);

				dmsPoc = DmsPocLocalServiceUtil.update(id, repairOrderNo, dealerCode,
						authorisedDealerInfo, dealershipName,
						dealershipAddress,dealerShipPhoneNo, faxNo,
						dealershipEmail, GST, customerName, customerAddress,
						customerMobileNo, customerEmailAddress, contactPerson, contactPersonPhone, regNo, vin,
						dateOfDel, kmsIn, model, engineNo,insurancePolicyNo,
						insuranceExpiryDate, rsaRequired, appointmentType, orderDateIn, orderTimeIn, sa,
						serviceAdvisorMobileNo, soldByDealer, mobilityService, transactionNo, reasonForVisit,
						extendedWarranty, servicePlan, customerOrderDescription, customerVoice, workToBeDone,
						instruction, carPicture, xDent, scratch, bodyDamage, modificationOrNonOBMFittings,
						tyreCondition, treadDepth, frontRight, frontLeft, rearRight, rearLeft, windowsGlazzing,
						wiperBlades, lights, exhaustSystem, underbody, enginCompartment, washing, oilLevel, coolant,
						windshieldWasher, breaks, axle, monograms, polishing, fule, ownerManual,
						serScheMaintenanceManual, infotainmentMmiNavigManual, extWarrantyBook, quickReferenceGuide,
						spareWheel, jackToolKits, floorMat, mudFlaps, warningTriangle, firstAidKit, noOfKeys,
						otherLooseItems, financialInformation, modeOfPayment, estimatedCost, taxesAsApplicable,
						estimatedDateOfDelivery, estimatedDeliveryTime, laborEstimate, parts, termsAndConditions,
						grantMyConsent, serviceAdvisotSignature, customerSignature, remarksArea, termsStrictly,
						workshopCopy, version, customerWaiting, campaign, dissNo, descriptiveInformation, timeStart,
						timeStop, nameOfTechnician, technicianSignature, partNumber, partDescription, partCatagory,
						internalPartsOrder, partQuantity, partRate, partCost, partTotal, labourDescription,
						labourCatagory, labourCode, timeUnit, hours, labourRate, labourCost, labourTotal,
						additionalRepairsReqDesc, additionalRepairsReqPart, additionalRepairsReqLabour,
						additionalRepairsReqAuth, additionalRepairsReqDT, roadTestKmOut, roadTestKmIn, allJobsAttended,
						accessoriesSettingAsOriginal, roadTestSignature, roadTestDate, interiors, interiorsPolishing,
						exteriors, exteriorsPolishing, vacuumCleaned, washingCheckerSignature, status);
//				dmsPoc = DmsPocLocalServiceUtil.updateToShow(dmsPoc.getId());
				System.out.println("successfull saved name..." + dmsPoc.getCustomerName());

				dmsPoc = DmsPocLocalServiceUtil.updatePartsLabourInventory(dmsPoc.getId(),
						 inventoryPartsJSONArray,
						partsJSONArray, labourJSONArray, true);

				System.out.println("successfull saved data..." + dmsPoc.getToShow());
				resourceRequest.setAttribute("dmsPoc", dmsPoc);
				getPortletContext().getRequestDispatcher("/jsp/orderSummary.jsp").include(resourceRequest,
						resourceResponse);
			} catch (NoSuchDmsPocException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			} catch (PortalException e) {
				e.printStackTrace();
			}

		}
		String jsonObj1 = ParamUtil.getString(resourceRequest, "data");
		// System.out.println("jsonObj1 :: "+jsonObj1);

		// resourceRequest.setAttribute("dMSCustVehiclePurchase", DmsVwPoc);
		// getPortletContext().getRequestDispatcher("/jsp/vehicle_customer_details.jsp").include(resourceRequest,resourceResponse);

	}
}