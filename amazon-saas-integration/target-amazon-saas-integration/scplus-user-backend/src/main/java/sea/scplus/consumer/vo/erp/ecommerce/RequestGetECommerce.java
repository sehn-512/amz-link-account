package sea.scplus.consumer.vo.erp.ecommerce;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RequestGetECommerce {

/**
<INV_HEADER>
  	<MANDT>100</MANDT>                        <!--1.2 항상 100-->
  	<TRANS_TYPE>SCPO</TRANS_TYPE>             <!--1.3 일반 SCPO, Return SCPR-->
  	<WEB_ORDNO>CTEU47BD01</WEB_ORDNO>         <!--1.4 CyberSource 랑 대사하는 기본 Key= Order Item Identifier (50)-->
  	<ORD_DATE>20210401</ORD_DATE>             <!--1.5 계약날짜 / YYYYMMDD (SAP)-->
  	<ORD_TIME>00:00:00</ORD_TIME>             <!--1.6 항상 00:00:00-->
  	<ZDEL_FLAG></ZDEL_FLAG>                   <!--1.7 Option - 사용안함 (삭제플래그 )-->
  	<KUNNR>0003892480</KUNNR>                 <!--1.8 항상 0003892480-->
   	<SALES_ORDER>CTEU47BD01</SALES_ORDER>    <!--1.9 SO 번호-->
   	<CS_AUTH_CODE></CS_AUTH_CODE>            <!--1.10 Optional - 사용안함-->
   	<ANETID>CTEU47BD01</ANETID>              <!--1.11 Optional - 사용안함 (TicketNo - Bank 파일을 받을 때 사용)-->
    <TRANS_DATE>20210401</TRANS_DATE>       <!--1.12 계약일 (GFP- 리포트조회가능)-->
    <CCINS>VISA</CCINS>                     <!--1.13 Credit Card Customer Code를 입력 - VISA, MAST, DISC, AMEX 카드 종류에 따라서 4개중 하나선택-->
   	<CCNUM>1111</CCNUM>                      <!--1.14 Credit Card Number  암호화해서 넘기거나 뒤에 네자리, 또는 'Xl4=' 고정값 Option 가능한지 추가확인-->
   	<WAERS>USD</WAERS>                       <!--1.15 항상 USD-->
   	<WRBTR>53.30</WRBTR>                     <!--1.16 Credit Card 결제금액-->
 </INV_HEADER>	
 */
private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String trans_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private String web_ordno;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ord_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sales_order;
	
	@JsonInclude(value = Include.NON_NULL)
	private String anetid;
	
	@JsonInclude(value = Include.NON_NULL)
	private String trans_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ccins;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ccnum;
	
	@JsonInclude(value = Include.NON_NULL)
	private String wrbtr;

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public String getWeb_ordno() {
		return web_ordno;
	}

	public void setWeb_ordno(String web_ordno) {
		this.web_ordno = web_ordno;
	}

	public String getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(String ord_date) {
		this.ord_date = ord_date;
	}

	public String getSales_order() {
		return sales_order;
	}

	public void setSales_order(String sales_order) {
		this.sales_order = sales_order;
	}

	public String getAnetid() {
		return anetid;
	}

	public void setAnetid(String anetid) {
		this.anetid = anetid;
	}

	public String getTrans_date() {
		return trans_date;
	}

	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}

	public String getCcins() {
		return ccins;
	}

	public void setCcins(String ccins) {
		this.ccins = ccins;
	}

	public String getCcnum() {
		return ccnum;
	}

	public void setCcnum(String ccnum) {
		this.ccnum = ccnum;
	}

	public String getWrbtr() {
		return wrbtr;
	}

	public void setWrbtr(String wrbtr) {
		this.wrbtr = wrbtr;
	}

	
}
