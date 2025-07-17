package sea.scplus.consumer.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
{
  "accountIdentifier": "string",
  "associateName": "A lil Samsung shop",
  "contractEndDate": "2022-02-10",
  "contractStartDate": "2020-02-10",
  "customer": {
    "address1": "105 Challenger Rd.",
    "address2": "8th Floor",
    "city": "Ridgefield Park",
    "country": "US",
    "email": "michael.j@jackson5.com",
    "firstName": "Jackson",
    "lastName": "Michael",
    "phone": 2010009999,
    "state": "NJ",
    "zipcode": "07660"
  },
  "mobile": true,
  "mobileNumber": 2010009999,
  "model": "RF23R6301SR/AA",
  "orderDate": "2019-11-20",
  "orderIdentifier": "SamsungSCMP20191111190030",
  "orderItemIdentifier": "SamsungSCMP20191111190030-01",
  "purchaseDate": "2019-11-21",
  "retailItemAmount": 11.99,
  "serialNumber": 11111111111115,
  "sku": "REB-DOP36P-PB6",
  "spcNetPrice": 10.99,
  "spcPayDate": "2019-11-23",
  "spcPaymentStatus": "G",
  "spcPaymentType": "S",
  "spcTaxAmt": 0.69,
  "spcTaxRate": 6.25,
  "storeName": "A lil Samsung shop"
}
 *
 */
public class RequestContractCreateCustomerInfo implements Serializable {
	
	public RequestContractCreateCustomerInfo() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	private String addressType;
	
	@JsonInclude(value = Include.NON_NULL)
	private String address1;
	
	@JsonInclude(value = Include.NON_NULL)
	private String address2;
	
	@JsonInclude(value = Include.NON_NULL)
	private String city;
	
	@JsonInclude(value = Include.NON_NULL)
	private String country;
	
	@JsonInclude(value = Include.NON_NULL)
	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	private String firstName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String lastName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String phone;
	
	@JsonInclude(value = Include.NON_NULL)
	private String state;
	
	@JsonInclude(value = Include.NON_NULL)
	private String zipcode;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	

}
