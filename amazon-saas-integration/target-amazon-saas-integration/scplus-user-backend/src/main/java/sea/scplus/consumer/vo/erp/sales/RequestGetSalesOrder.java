package sea.scplus.consumer.vo.erp.sales;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestGetSalesOrder {

	/*
	<sdf:MT_ZSDF_ORDER_CREATE>
    <PI_LEGACY_USER_ID>CPIC_S1921A</PI_LEGACY_USER_ID> <!--0.1 항상 CPIC_S1921A 또는 실제 사용하는 User Front 단의 User ID-->
    <PI_IF_KEY>C7NHJ0WN01</PI_IF_KEY> <!--0.2 Cancel Request 의 Order Item Identifier 번호-->
    <PI_ZSYSNO>SYS1921</PI_ZSYSNO>    <!--0.3 항상 SYS1921  -->
  <PIS_HEADER>
       <SD_DOC_CAT>H</SD_DOC_CAT>    <!--1.1 항상 H     (신규)-->
       <ORD_REASON>SC</ORD_REASON><!--1.2 항상 SC    (신규)-->
       <DOC_TYPE>YR01</DOC_TYPE>        <!--1.3 항상 YR01 (변경)      -->
       <SALES_ORG>3104</SALES_ORG>                 <!--1.4 항상 3104-->
       <DISTR_CHAN>10</DISTR_CHAN>                <!--1.5 항상 10-->
       <DIVISION>Z1</DIVISION>                  <!--1.6 항상 Z1-->
       <PURCH_DATE>20210401</PURCH_DATE>               <!--1.7 계약날짜-->
       <PURCH_NO_C>CG5ZCHTR01</PURCH_NO_C>       <!--1.8 원계약의 Order Item Identifier 번호-->
       <REF_DOC>3000374934</REF_DOC>                          <!--1.9   SD02376  - PE_VBELN_VF (신규)-->
       <REF_DOC_L>3000374934</REF_DOC_L>                  <!--1.10 SD02376 - PE_VBELN_VF (신규)-->
       <REFDOC_CAT>M</REFDOC_CAT>    <!--1.11 항상 M (신규)-->
       <SALES_OFF>3171</SALES_OFF><!--1.8 항상 3171-->
    </PIS_HEADER>
    <PIT_ITEM>
       <ITM_NUMBER>000010</ITM_NUMBER>                 <!--2.1 항상 000010-->
       <ITEM_CATEG></ITEM_CATEG>        <!--2.2 항상 YRS2(변경)-->
       <REF_DOC>3000374934</REF_DOC>                          <!--2.3 SD02376  - PE_VBELN_VF (신규)-->
       <REF_DOC_IT>000010</REF_DOC_IT>                        <!--2.4 항상 000010 (신규)-->
       <MATERIAL>P-CT-1XXX050</MATERIAL>                    <!--2.3 SKU 의 SVC PACK CODE 입력-->
       <PLANT>S302</PLANT>         <!--2.5 항상 S302-->
       <STORE_LOC>WR10</STORE_LOC>  <!--2.6 항상 WR10-->
       <TARGET_QTY>1</TARGET_QTY>                  <!--2.7 항상 1-->
       <TARGET_QU>PC</TARGET_QU>                  <!--2.8 항상 PC-->
       <PURCH_NO_S>CG5ZCHTR01</PURCH_NO_S>                       <!--2.10 원계약의 Order Item Identifier 번호-->
       <REF_DOC_CA>M</REF_DOC_CA>    <!--2.11 항상 M (신규)-->
    </PIT_ITEM>
    <PIT_PARTNER>
       <PARTN_ROLE>AG</PARTN_ROLE>  <!--3.1 항상 AG-->
       <PARTN_NUMB>0003892480</PARTN_NUMB>       <!--3.2 항상 0003892480-->
       </PIT_PARTNER>
     <PIT_PARTNER>
       <PARTN_ROLE>WE</PARTN_ROLE> <!--3.3 항상 WE-->
       <PARTN_NUMB>0003892480</PARTN_NUMB>       <!--3.4 항상 0003892480-->
       <ADDR_LINK>1</ADDR_LINK>            <!--3.5 항상 1-->
     </PIT_PARTNER>
   <PIT_CONDITION>
       <ITM_NUMBER>000010</ITM_NUMBER>                 <!--4.5 항상 000010-->
       <COND_TYPE>6TX1</COND_TYPE>  <!--4.6 항상 6TX1-->
       <CONDVALUE>5.96</CONDVALUE> <!--4.7 Tax 금액-->
       <CURRENCY_2>USD</CURRENCY_2>                          <!--4.8 항상 USD-->
     </PIT_CONDITION>
       <PIT_ADDRESS>
       <ADDR_NO>1</ADDR_NO>                 <!--4.1 항상 1-->
       <NAME>Donghyun Kim</NAME>                   <!--4.2 고객이름-->
       <CITY>Saddlebrook</CITY>                 <!--4.3 고객 City 주소-->
       <DISTRICT>NJ</DISTRICT>                  <!--4.4 고객 State-->
        <POSTL_COD1>07663</POSTL_COD1>                       <!--4.5 고객 Zipcode-->
       <STREET>15 Saddlebrook</STREET> <!--4.6 고객 Street 주소-->
       <COUNTRY>US</COUNTRY>              <!--4.7 고객의 Country-->
       <LANGU>EN</LANGU>          <!--4.8 항상 EN-->
       <REGION>NJ</REGION>       <!--4.9 고객의 State-->
      </PIT_ADDRESS>
      <PIT_SCHEDULE>
       <ITM_NUMBER>000010</ITM_NUMBER>                 <!--6.1 항상 000010-->
       <REQ_QTY>1</REQ_QTY>                   <!--6.2 항상 1-->
     </PIT_SCHEDULE>
     <PIT_ADDITIONAL_DATA>
       <ITM_NUMBER>000010</ITM_NUMBER>                 <!--7.1. 항상 000010-->
       <ADD_FIELD_NAME>REQ_PRICE</ADD_FIELD_NAME>                     <!--7.2 항상 REQ_PRICE-->
       <ADD_FIELD_VALUE>89.99</ADD_FIELD_VALUE>   <!--7.3 Total Value(SKU Price+Tax)-->
     <PIT_TEXT>
       <ITM_NUMBER>000010</ITM_NUMBER>                 <!--8.1 항상 000010-->
       <TEXT_ID>Z334</TEXT_ID>                 <!--8.2 항상 Z334-->
       <TEXT_LINE>CE6789012346</TEXT_LINE>                 <!--8.3 Cancel Approval No-->
     </PIT_TEXT>
    </PIT_ADDITIONAL_DATA>
  </sdf:MT_ZSDF_ORDER_CREATE>
  */
private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String pi_if_key;
	
	@JsonInclude(value = Include.NON_NULL)
	private String doc_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private String doc_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String purch_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String purch_no_c;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ref_doc;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ref_doc_l;
	
	@JsonInclude(value = Include.NON_NULL)
	private String material;
	
	@JsonInclude(value = Include.NON_NULL)
	private String purch_no_s;
	
	@JsonInclude(value = Include.NON_NULL)
	private String condvalue;
	
	@JsonInclude(value = Include.NON_NULL)
	private String name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String zip_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String state;
	
	@JsonInclude(value = Include.NON_NULL)
	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	private String city;
	
	@JsonInclude(value = Include.NON_NULL)
	private String address;
	
	@JsonInclude(value = Include.NON_NULL)
	private String add_field_value;
	
	@JsonInclude(value = Include.NON_NULL)
	private String text_line;

	public String getPi_if_key() {
		return pi_if_key;
	}

	public void setPi_if_key(String pi_if_key) {
		this.pi_if_key = pi_if_key;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getPurch_date() {
		return purch_date;
	}

	public void setPurch_date(String purch_date) {
		this.purch_date = purch_date;
	}

	public String getPurch_no_c() {
		return purch_no_c;
	}

	public void setPurch_no_c(String purch_no_c) {
		this.purch_no_c = purch_no_c;
	}

	public String getRef_doc() {
		return ref_doc;
	}

	public void setRef_doc(String ref_doc) {
		this.ref_doc = ref_doc;
	}

	public String getRef_doc_l() {
		return ref_doc_l;
	}

	public void setRef_doc_l(String ref_doc_l) {
		this.ref_doc_l = ref_doc_l;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getPurch_no_s() {
		return purch_no_s;
	}

	public void setPurch_no_s(String purch_no_s) {
		this.purch_no_s = purch_no_s;
	}

	public String getCondvalue() {
		return condvalue;
	}

	public void setCondvalue(String condvalue) {
		this.condvalue = condvalue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdd_field_value() {
		return add_field_value;
	}

	public void setAdd_field_value(String add_field_value) {
		this.add_field_value = add_field_value;
	}

	public String getText_line() {
		return text_line;
	}

	public void setText_line(String text_line) {
		this.text_line = text_line;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDoc_date() {
		return doc_date;
	}

	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}

	
}
