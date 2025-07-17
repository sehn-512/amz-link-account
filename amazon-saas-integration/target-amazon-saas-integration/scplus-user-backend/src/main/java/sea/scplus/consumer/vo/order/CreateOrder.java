package sea.scplus.consumer.vo.order;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 */
public class CreateOrder implements Serializable {
	
	public CreateOrder() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String first_name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String last_name;
	
	@JsonInclude(value = Include.NON_NULL)
	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cancel_order_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cancel_order_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String order_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String order_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contract_no;
	
	@JsonInclude(value = Include.NON_NULL)
	private String scpus_ret_cd;
	
	@JsonInclude(value = Include.NON_NULL)
	private String scpus_ret_message;
	
	@JsonInclude(value = Include.NON_NULL)
	private String pay_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double unit_price;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double total_price;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double tax_rate;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double tax_amount;
	
	@JsonInclude(value = Include.NON_NULL)
	private String payment_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private String payment_status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contract_start_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String contract_end_date;
	
	@JsonInclude(value = Include.NON_NULL)
	private String order_reference_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_approval_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_reconciliation_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_network_transaction_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_transaction_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_response_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cs_submit_time_utc;
	
	@JsonInclude(value = Include.NON_NULL)
	private String pos_ret_cd;
	
	@JsonInclude(value = Include.NON_NULL)
	private String pos_ret_message;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sales_order_number;
	
	@JsonInclude(value = Include.NON_NULL)
	private String billing_order_number;
	
	@JsonInclude(value = Include.NON_NULL)
	private String ecommerce;
	
	@JsonInclude(value = Include.NON_NULL)
	private String card_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cancel_type;
	
	@JsonInclude(value = Include.NON_NULL)
	private String zip_code;
	
	@JsonInclude(value = Include.NON_NULL)
	private String state;
	
	@JsonInclude(value = Include.NON_NULL)
	private String address;
	
	@JsonInclude(value = Include.NON_NULL)
	private String city;
	
	@JsonInclude(value = Include.NON_NULL)
	private String recon;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<CreateOrderItem> orderItem;

	public String getCancel_order_date() {
		return cancel_order_date;
	}

	public void setCancel_order_date(String cancel_order_date) {
		this.cancel_order_date = cancel_order_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCancel_order_id() {
		return cancel_order_id;
	}

	public void setCancel_order_id(String cancel_order_id) {
		this.cancel_order_id = cancel_order_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getScpus_ret_cd() {
		return scpus_ret_cd;
	}

	public void setScpus_ret_cd(String scpus_ret_cd) {
		this.scpus_ret_cd = scpus_ret_cd;
	}

	public String getScpus_ret_message() {
		return scpus_ret_message;
	}

	public void setScpus_ret_message(String scpus_ret_message) {
		this.scpus_ret_message = scpus_ret_message;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public Double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(Double tax_rate) {
		this.tax_rate = tax_rate;
	}

	public Double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(Double tax_amount) {
		this.tax_amount = tax_amount;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getContract_start_date() {
		return contract_start_date;
	}

	public void setContract_start_date(String contract_start_date) {
		this.contract_start_date = contract_start_date;
	}

	public String getContract_end_date() {
		return contract_end_date;
	}

	public void setContract_end_date(String contract_end_date) {
		this.contract_end_date = contract_end_date;
	}

	public String getOrder_reference_code() {
		return order_reference_code;
	}

	public void setOrder_reference_code(String order_reference_code) {
		this.order_reference_code = order_reference_code;
	}

	public String getCs_id() {
		return cs_id;
	}

	public void setCs_id(String cs_id) {
		this.cs_id = cs_id;
	}
	
	public String getCs_reconciliation_id() {
		return cs_reconciliation_id;
	}

	public void setCs_reconciliation_id(String cs_reconciliation_id) {
		this.cs_reconciliation_id = cs_reconciliation_id;
	}

	public String getCs_approval_code() {
		return cs_approval_code;
	}

	public void setCs_approval_code(String cs_approval_code) {
		this.cs_approval_code = cs_approval_code;
	}

	public String getCs_network_transaction_id() {
		return cs_network_transaction_id;
	}

	public void setCs_network_transaction_id(String cs_network_transaction_id) {
		this.cs_network_transaction_id = cs_network_transaction_id;
	}

	public String getCs_transaction_id() {
		return cs_transaction_id;
	}

	public void setCs_transaction_id(String cs_transaction_id) {
		this.cs_transaction_id = cs_transaction_id;
	}

	public String getCs_response_code() {
		return cs_response_code;
	}

	public void setCs_response_code(String cs_response_code) {
		this.cs_response_code = cs_response_code;
	}

	public String getCs_status() {
		return cs_status;
	}

	public void setCs_status(String cs_status) {
		this.cs_status = cs_status;
	}

	public String getCs_submit_time_utc() {
		return cs_submit_time_utc;
	}

	public void setCs_submit_time_utc(String cs_submit_time_utc) {
		this.cs_submit_time_utc = cs_submit_time_utc;
	}

	public String getPos_ret_cd() {
		return pos_ret_cd;
	}

	public void setPos_ret_cd(String pos_ret_cd) {
		this.pos_ret_cd = pos_ret_cd;
	}

	public String getPos_ret_message() {
		return pos_ret_message;
	}

	public void setPos_ret_message(String pos_ret_message) {
		this.pos_ret_message = pos_ret_message;
	}

	public List<CreateOrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<CreateOrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public String getSales_order_number() {
		return sales_order_number;
	}

	public void setSales_order_number(String sales_order_number) {
		this.sales_order_number = sales_order_number;
	}

	public String getBilling_order_number() {
		return billing_order_number;
	}

	public void setBilling_order_number(String billing_order_number) {
		this.billing_order_number = billing_order_number;
	}

	public String getEcommerce() {
		return ecommerce;
	}

	public void setEcommerce(String ecommerce) {
		this.ecommerce = ecommerce;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRecon() {
		return recon;
	}

	public void setRecon(String recon) {
		this.recon = recon;
	}

	public String getCancel_type() {
		return cancel_type;
	}

	public void setCancel_type(String cancel_type) {
		this.cancel_type = cancel_type;
	}

	
	
}
