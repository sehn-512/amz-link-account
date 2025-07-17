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
public class CreateOrderItem implements Serializable {
	
	public CreateOrderItem() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cancel_order_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String cancel_order_item_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String order_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String order_item_id;
	
	@JsonInclude(value = Include.NON_NULL)
	private String serial_number;
	
	@JsonInclude(value = Include.NON_NULL)
	private String model_cd;
	
	@JsonInclude(value = Include.NON_NULL)
	private String sku;
	
	@JsonInclude(value = Include.NON_NULL)
	private String svcpacksku;
	
	@JsonInclude(value = Include.NON_NULL)
	private int quantity;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double unit_price;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double total_price;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double tax_rate;
	
	@JsonInclude(value = Include.NON_NULL)
	private Double tax_amount;
	
	public String getCancel_order_id() {
		return cancel_order_id;
	}

	public void setCancel_order_id(String cancel_order_id) {
		this.cancel_order_id = cancel_order_id;
	}

	public String getCancel_order_item_id() {
		return cancel_order_item_id;
	}

	public void setCancel_order_item_id(String cancel_order_item_id) {
		this.cancel_order_item_id = cancel_order_item_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public String getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(String order_item_id) {
		this.order_item_id = order_item_id;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getModel_cd() {
		return model_cd;
	}

	public void setModel_cd(String model_cd) {
		this.model_cd = model_cd;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public String getSvcpacksku() {
		return svcpacksku;
	}

	public void setSvcpacksku(String svcpacksku) {
		this.svcpacksku = svcpacksku;
	}
	
	
	
	
}
