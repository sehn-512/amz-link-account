package sea.scplus.consumer.vo.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
{
    "clientReferenceInformation": {
        "code": "TC50171_3"
    },
    "processingInformation": {
        "capture": true
    },
    "paymentInformation": {
        "card": {
            "number": "4111111111111111",
            "expirationMonth": "12",
            "expirationYear": "2031"
        }
    },
    "orderInformation": {
        "amountDetails": {
            "totalAmount": "102.21",
            "currency": "USD"
        },
        "billTo": {
            "firstName": "John",
            "lastName": "Doe",
            "address1": "1 Market St",
            "address2": "no room",
            "locality": "san francisco",
            "administrativeArea": "CA",
            "postalCode": "94105",
            "country": "US",
            "email": "test@cybs.com",
            "phoneNumber": "4158880000"
        }
    }
}
 *
 */
public class RequestPaymentOrderInformationBillTo {

	@JsonInclude(value = Include.NON_NULL)
	private String firstName;

	@JsonInclude(value = Include.NON_NULL)
	private String lastName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String address1;
	
	@JsonInclude(value = Include.NON_NULL)
	private String address2;

	@JsonInclude(value = Include.NON_NULL)
	private String locality;
	
	@JsonInclude(value = Include.NON_NULL)
	private String administrativeArea;
	
	@JsonInclude(value = Include.NON_NULL)
	private String postalCode;
	
	@JsonInclude(value = Include.NON_NULL)
	private String country;
	
	@JsonInclude(value = Include.NON_NULL)
	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	private String phoneNumber;

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

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getAdministrativeArea() {
		return administrativeArea;
	}

	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
