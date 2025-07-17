package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * 
[
  {
    "COMPANY": "C310",
    "CHANNEL": "SESL",
    "MODELCODE": "SM-N975UZKAXAG",
    "PROGRAM": "POS",
    "TERM": "1",
    "MODELTYPE": "Mobile Phones",
    "MODELFAMILY": "Note 10+",
    "PRICE": 11.99,
    "PBLOW":4000,
    "PBHIGH":5999.99,
    "MSRP": 1099.99,
    "PAYMENTFREQUENCY": "MONTHLY",
    "SKU": "HHP-AD1P-PB24-L01",
    "RENEWALSKU": "HHP-AD1P-PB24-L01-R",
    "DEALERNETASSURANT":163.75,
    "LAUNCHDATE": "23-Aug-19",
    "COLOR": "Black",
    "MEMORY": "256GB",
    "CARRIER": "Generic"
  },
  {
    "COMPANY": "C310",
    "CHANNEL": "SESL",
    "MODELCODE": "SM-N975UZKAXAG",
    "PROGRAM": "POS",
    "TERM": "24",
    "MODELTYPE": "Mobile Phones",
    "MODELFAMILY": "Note 10+",
    "PRICE": 219.99,
    "PBLOW":4000,
    "PBHIGH":5999.99,
    "MSRP": 1099.99,
    "PAYMENTFREQUENCY": "SINGLEPAY",
    "SKU": "HHP-AD24P-PB36-L01",
    "DEALERNETASURION":163.75,
    "LAUNCHDATE": "23-Aug-19",
    "DISPLAYMODELNAME":"(4) Expand Premium w/ QLED",
    "SVCPACKSKU":"P-UT-3XXX600C",
    "COLOR": "Black",
    "MEMORY": "256GB",
    "CARRIER": "Generic",
    "CATEGORY":"REF",
    "SHORTMODEL":"RF18HFENBSG",
    "HADESCRIPTION":"3-DR",
    "MODELFAMILYSERIES":"RF18HFEN",
    "SKUDESC":"Bottom Freezer Refrigerator 3 yr Single Pay POS"
  }
]
 *
 */
@JsonIgnoreProperties
public class ResponseSpcValidation implements Serializable {
	
	public ResponseSpcValidation() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String CATEGORY;
	
	@JsonInclude(value = Include.NON_NULL)
	private String SHORTMODEL;
	
	@JsonInclude(value = Include.NON_NULL)
	private String HADESCRIPTION;
	
	@JsonInclude(value = Include.NON_NULL)
	private String MODELFAMILYSERIES;
	
	@JsonInclude(value = Include.NON_NULL)
	private String SKUDESC;
	
	@JsonInclude(value = Include.NON_NULL)
	private String UPC;
	
	@JsonInclude(value = Include.NON_NULL)
	private String COMPANY;
	
	@JsonInclude(value = Include.NON_NULL)
	private String CHANNEL;
	
	@JsonInclude(value = Include.NON_NULL)
	private String MODELCODE;
	
	@JsonInclude(value = Include.NON_NULL)
	private String PROGRAM;
	
	@JsonInclude(value = Include.NON_NULL)
	private String TERM;
	
	@JsonInclude(value = Include.NON_NULL)
	private String MODELTYPE;
	
	@JsonInclude(value = Include.NON_NULL)
	private String MODELFAMILY;
	
	@JsonInclude(value = Include.NON_NULL)
	private String PRICE;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double PBLOW;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double PBHIGH;
	
	@JsonInclude(value = Include.NON_NULL)
	private String MSRP;
	
	@JsonInclude(value = Include.NON_NULL)
	private String PAYMENTFREQUENCY;
	
	@JsonInclude(value = Include.NON_NULL)
	private String SKU;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double DEALERNETASURION;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double DEALERNETASSURANT;
	
	@JsonInclude(value = Include.NON_NULL)
	private String RENEWALSKU;
	
	@JsonInclude(value = Include.NON_NULL)
	private String LAUNCHDATE;
	
	@JsonInclude(value = Include.NON_NULL)
	private String DISPLAYMODELNAME;
	
	@JsonInclude(value = Include.NON_NULL)
	private String SVCPACKSKU;
	
	@JsonInclude(value = Include.NON_NULL)
	private String COLOR;
	
	@JsonInclude(value = Include.NON_NULL)
	private String MEMORY;
	
	@JsonInclude(value = Include.NON_NULL)
	private String CARRIER;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ACCESSORYINDICATOR;

	@JsonInclude(value = Include.NON_NULL)
	private String TICKETNO;

	@JsonInclude(value = Include.NON_NULL)
	private String ASCNO;

	@JsonInclude(value = Include.NON_NULL)
	private String TICKETSTATUS;

	@JsonInclude(value = Include.NON_NULL)
	private String TECHNICIANID;

	@JsonInclude(value = Include.NON_NULL)
	private Double DISCOUNTAMOUNT;

	@JsonProperty("SVCPACKSKU")
	public String getSVCPACKSKU() {
		return SVCPACKSKU;
	}

	public void setSVCPACKSKU(String sVCPACKSKU) {
		SVCPACKSKU = sVCPACKSKU;
	}

	@JsonProperty("DEALERNETASURION")
	public Double getDEALERNETASURION() {
		return DEALERNETASURION;
	}

	public void setDEALERNETASURION(Double dEALERNETASURION) {
		DEALERNETASURION = dEALERNETASURION;
	}

	@JsonProperty("DISPLAYMODELNAME")
	public String getDISPLAYMODELNAME() {
		return DISPLAYMODELNAME;
	}

	public void setDISPLAYMODELNAME(String dISPLAYMODELNAME) {
		DISPLAYMODELNAME = dISPLAYMODELNAME;
	}

	@JsonProperty("COMPANY")
	public String getCOMPANY() {
		return COMPANY;
	}

	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}

	@JsonProperty("CHANNEL")
	public String getCHANNEL() {
		return CHANNEL;
	}

	public void setCHANNEL(String cHANNEL) {
		CHANNEL = cHANNEL;
	}

	@JsonProperty("MODELCODE")
	public String getMODELCODE() {
		return MODELCODE;
	}

	public void setMODELCODE(String mODELCODE) {
		MODELCODE = mODELCODE;
	}

	@JsonProperty("PROGRAM")
	public String getPROGRAM() {
		return PROGRAM;
	}

	public void setPROGRAM(String pROGRAM) {
		PROGRAM = pROGRAM;
	}

	@JsonProperty("TERM")
	public String getTERM() {
		return TERM;
	}

	public void setTERM(String tERM) {
		TERM = tERM;
	}

	@JsonProperty("MODELTYPE")
	public String getMODELTYPE() {
		return MODELTYPE;
	}

	public void setMODELTYPE(String mODELTYPE) {
		MODELTYPE = mODELTYPE;
	}

	@JsonProperty("MODELFAMILY")
	public String getMODELFAMILY() {
		return MODELFAMILY;
	}

	public void setMODELFAMILY(String mODELFAMILY) {
		MODELFAMILY = mODELFAMILY;
	}

//	@JsonProperty("PRICE")
//	public double getPRICE() {
//		return PRICE;
//	}
//
//	public void setPRICE(Double pRICE) {
//		PRICE = pRICE;
//	}
	
	@JsonProperty("PBLOW")
	public Double getPBLOW() {
		return PBLOW;
	}

	public void setPBLOW(Double pBLOW) {
		PBLOW = pBLOW;
	}

	@JsonProperty("PBHIGH")
	public Double getPBHIGH() {
		return PBHIGH;
	}

	public void setPBHIGH(Double pBHIGH) {
		PBHIGH = pBHIGH;
	}

//	@JsonProperty("MSRP")
//	public double getMSRP() {
//		return MSRP;
//	}
//
//	public void setMSRP(Double mSRP) {
//		MSRP = mSRP;
//	}

	@JsonProperty("PAYMENTFREQUENCY")
	public String getPAYMENTFREQUENCY() {
		return PAYMENTFREQUENCY;
	}

	public void setPAYMENTFREQUENCY(String pAYMENTFREQUENCY) {
		PAYMENTFREQUENCY = pAYMENTFREQUENCY;
	}

	@JsonProperty("SKU")
	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}
	
	@JsonProperty("RENEWALSKU")
	public String getRENEWALSKU() {
		return RENEWALSKU;
	}

	public void setRENEWALSKU(String rENEWALSKU) {
		RENEWALSKU = rENEWALSKU;
	}

	@JsonProperty("LAUNCHDATE")
	public String getLAUNCHDATE() {
		return LAUNCHDATE;
	}

	public void setLAUNCHDATE(String lAUNCHDATE) {
		LAUNCHDATE = lAUNCHDATE;
	}

	@JsonProperty("COLOR")
	public String getCOLOR() {
		return COLOR;
	}

	public void setCOLOR(String cOLOR) {
		COLOR = cOLOR;
	}

	@JsonProperty("MEMORY")
	public String getMEMORY() {
		return MEMORY;
	}

	public void setMEMORY(String mEMORY) {
		MEMORY = mEMORY;
	}

	@JsonProperty("CARRIER")
	public String getCARRIER() {
		return CARRIER;
	}

	public void setCARRIER(String cARRIER) {
		CARRIER = cARRIER;
	}

	@JsonProperty("DEALERNETASSURANT")
	public Double getDEALERNETASSURANT() {
		return DEALERNETASSURANT;
	}

	public void setDEALERNETASSURANT(Double dEALERNETASSURANT) {
		DEALERNETASSURANT = dEALERNETASSURANT;
	}

	@JsonProperty("CATEGORY")
	public String getCATEGORY() {
		return CATEGORY;
	}

	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}

	@JsonProperty("SHORTMODEL")
	public String getSHORTMODEL() {
		return SHORTMODEL;
	}

	public void setSHORTMODEL(String sHORTMODEL) {
		SHORTMODEL = sHORTMODEL;
	}

	@JsonProperty("HADESCRIPTION")
	public String getHADESCRIPTION() {
		return HADESCRIPTION;
	}

	public void setHADESCRIPTION(String hADESCRIPTION) {
		HADESCRIPTION = hADESCRIPTION;
	}

	@JsonProperty("MODELFAMILYSERIES")
	public String getMODELFAMILYSERIES() {
		return MODELFAMILYSERIES;
	}

	public void setMODELFAMILYSERIES(String mODELFAMILYSERIES) {
		MODELFAMILYSERIES = mODELFAMILYSERIES;
	}

	@JsonProperty("SKUDESC")
	public String getSKUDESC() {
		return SKUDESC;
	}

	public void setSKUDESC(String sKUDESC) {
		SKUDESC = sKUDESC;
	}

	@JsonProperty("UPC")
	public String getUPC() {
		return UPC;
	}

	public void setUPC(String uPC) {
		UPC = uPC;
	}

	@JsonProperty("ACCESSORYINDICATOR")
	public String getACCESSORYINDICATOR() {
		return ACCESSORYINDICATOR;
	}

	public void setACCESSORYINDICATOR(String aCCESSORYINDICATOR) {
		ACCESSORYINDICATOR = aCCESSORYINDICATOR;
	}

	@JsonProperty("PRICE")
	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}

	@JsonProperty("MSRP")
	public String getMSRP() {
		return MSRP;
	}

	public void setMSRP(String mSRP) {
		MSRP = mSRP;
	}

	@JsonProperty("TICKETNO")
	public String getTICKETNO() {
		return TICKETNO;
	}

	public void setTICKETNO(String TICKETNO) {
		this.TICKETNO = TICKETNO;
	}

	@JsonProperty("ASCNO")
	public String getASCNO() {
		return ASCNO;
	}

	public void setASCNO(String ASCNO) {
		this.ASCNO = ASCNO;
	}

	@JsonProperty("TICKETSTATUS")
	public String getTICKETSTATUS() {
		return TICKETSTATUS;
	}

	public void setTICKETSTATUS(String TICKETSTATUS) {
		this.TICKETSTATUS = TICKETSTATUS;
	}

	@JsonProperty("TECHNICIANID")
	public String getTECHNICIANID() {
		return TECHNICIANID;
	}

	public void setTECHNICIANID(String TECHNICIANID) {
		this.TECHNICIANID = TECHNICIANID;
	}

	@JsonProperty("DISCOUNTAMOUNT")
	public Double getDISCOUNTAMOUNT() {
		return DISCOUNTAMOUNT;
	}

	public void setDISCOUNTAMOUNT(Double DISCOUNTAMOUNT) {
		this.DISCOUNTAMOUNT = DISCOUNTAMOUNT;
	}
}
