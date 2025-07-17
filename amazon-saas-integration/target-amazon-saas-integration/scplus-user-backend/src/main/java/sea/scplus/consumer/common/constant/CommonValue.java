/**
 * Copyright (c) 2016 Samsung SDSA. All Rights Reserved.
 */
package sea.scplus.consumer.common.constant;

public interface CommonValue 
{
	public interface Constant 
	{
		// Constants 
		public static final String EMPTY_STRING = "";
		
		// Path Variables
		public static final String PROF_ID = "prof_id";
		public static final String LANGUAGE = "language";
		
		// Language Options (SITE_CDs)
		public static final String EN = "en";
		public static final String FR = "fr";
		
		// Promotion Status
        public interface CONTRACT{
            public interface STATUS{
            	
            	public static final String ACTIVE = "ACTIVE";
            	public static final String CANCELLING = "Cancellation in progress";
            	public static final String CANCELLED = "CANCELLED";
            	public static final String EXPIRED = "EXPIRED";
            	
            }
        }
        
     // Promotion Status
        public interface CANCEL{
            public interface STATUS{
                public static final String REQUESTED = "REQUESTED";
                public static final String PROCESSED_PG = "PROCESSED_PG";
                public static final String PROCESSED_SCPLUS = "PROCESSED_SCPLUS";
                public static final String PROCESSED_GERP = "PROCESSED_GERP";
                public static final String PROCESSED_RECON= "PROCESSED_RECON";
                public static final String CANCELLED = "CANCELLED";
                public static final String REJECTED = "REJECTED";
            }
        }
	}
	
	// message.properties
	public interface Message 
	{
		// Error Messages 
		public interface Error {
			public static final String SYNTAX = "error.syntax";

			// Default Exception Messages
			public static final String SESSION_EXPIRED = "error.sessionexpired.default";
			
			public static final String INVALID_INPUT_DATA = "error.invalid_input_data";
			public static final String INVALID_RECAPTCHA = "error.invalid_recaptcha";
			public static final String INVALID_CANCELDATE = "error.invalid_canceldate";
			public static final String INVALID_YOUR_DATA = "error.invalid.your.data";
			public static final String NO_DATA = "error.no_data";
			public static final String NO_DATA_PRICE = "error.no_data_price";
			public static final String INVALID_PRICE = "error.invalid_price";
			public static final String INVALID_DIFFERENT_TOTAL_PRICE = "error.invalid_different_total_price";
			public static final String INVALID_DIFFERENT_TOTAL_TAX = "error.invalid_different_total_tax";
			public static final String INVALID_DIFFERENT_SKU_PRICE = "error.invalid_different_sku_price";
			public static final String INVALID_DIFFERENT_ZIPCODE = "error.invalid_different_zip_code";
			public static final String INVALID_DIFFERENT_UNIT_PRICE = "error.invalid_different_unit_price";
			public static final String INVALID_DIFFERENT_MODEL_CODE = "error.invalid_different_model_code";
			public static final String INVALID_DIFFERENT_SKU = "error.invalid_different_sku";
			public static final String INVALID_DIFFERENT_COVERAGE = "error.invalid_different_coverage";
			public static final String INVALID_PAYMENT_ERROR = "error.invalid_payment_error";

			//over due 30 days
			public static final String CANCEL_30 = "error.cancel.30";
			public static final String CANCEL_STATUS = "error.cancel.status";
			public static final String CANCEL_CONTRACT = "error.cancel.contract";
			public static final String CANCEL_PROCESSING = "error.cancel.processing";
			public static final String CANCEL_ALREADY = "error.cancel.already";
			public static final String NO_JOIN_CONTRACT = "error.no.join.contract";
			public static final String CONTACT_CALL_CENTER = "error.contact.call.center";
			public static final String DUP_PAYMENT = "error.dup.payment";
			
		}
		
			
		
		// Info Messages 
		public interface Warn {
			public static final String EMPTY_MODELCODE = "warn.empty_modelcode";
			public static final String EMPTY_SERIALNUMBER = "warn.empty_serialnumber";
			public static final String EMPTY_CARDNUMBER = "warn.empty_cardnumber";
			public static final String EMPTY_PRODUCTDATA = "warn.empty_productdata";
			public static final String EMPTY_CVV = "warn.empty_cvv";
			public static final String EMPTY_EXPIRATIONMONTH = "warn.empty_expirationmonth";
			public static final String EMPTY_EXPIRATIONYEAR = "warn.empty_expirationyear";
			public static final String EMPTY_PRICE = "warn.empty_price";
			public static final String EMPTY_CURRENCY = "warn.empty_currency";
			public static final String EMPTY_ADDRESS = "warn.empty_address";
			public static final String EMPTY_STATE = "warn.empty_state";
			public static final String EMPTY_COUNTRY = "warn.empty_country";
			public static final String EMPTY_EMAIL = "warn.empty_email";
			public static final String EMPTY_FIRSTNAME = "warn.empty_firstname";
			public static final String EMPTY_LASTNAME = "warn.empty_lastname";
			public static final String EMPTY_CITY = "warn.empty_city";
			public static final String EMPTY_PHONENUMBER = "warn.empty_phonenumber";
			public static final String EMPTY_POSTALCODE = "warn.empty_postalcode";
			public static final String EMPTY_CANCELDATE = "warn.empty_canceldate";
			public static final String EMPTY_CONTRACTNO = "warn.empty_contractno";
			public static final String EMPTY_CANCELREASON = "warn.empty_cancelreason";
			public static final String WRONG_POSTALCODE = "warn.wrong_postalcode";
			
			public static final String ELIGIBILITY_303 = "warn.eligibility.303";
			public static final String ELIGIBILITY_304 = "warn.eligibility.304";
			public static final String ELIGIBILITY_305 = "warn.eligibility.305";
			
			public static final String PAYMENT_ERROR = "warn.payment.error";
			
			public static final String SYNTAX_FIELD_SIZE = "warn.syntax.field";
			
            public static final String AVS_FAILED = "warn.avs_failed";
			public static final String CONTACT_PROCESSOR = "warn.contact_processor";
			public static final String EXPIRED_CARD = "warn.expired_card";
			public static final String PROCESSOR_DECLINED = "warn.processor_declined";
			public static final String INSUFFICIENT_FUND = "warn.insufficient_fund";
			public static final String STOLEN_LOST_CARD = "warn.stolen_lost_card";
			public static final String ISSUER_UNAVAILABLE = "warn.issuer_unavailable";
			public static final String UNAUTHORIZED_CARD = "warn.unauthorized_card";
			public static final String CVN_NOT_MATCH = "warn.cvn_not_match";
			public static final String EXCEEDS_CREDIT_LIMIT = "warn.exceeds_credit_limit";
			public static final String INVALID_CVN = "warn.invalid_cvn";
			public static final String DECLINED_CHECK = "warn.declined_check";
			public static final String BLACKLISTED_CUSTOMER = "warn.blacklisted_customer";
			public static final String SUSPENDED_ACCOUNT = "warn.suspended_account";
			public static final String PAYMENT_REFUSED = "warn.payment_refused";
			public static final String CV_FAILED = "warn.cv_failed";
			public static final String INVALID_ACCOUNT = "warn.invalid_account";
			public static final String GENERAL_DECLINE = "warn.general_decline";
			public static final String INVALID_MERCHANT_CONFIGURATION = "warn.invalid_merchant_configuration";
			public static final String DECISION_PROFILE_REJECT = "warn.decision_profile_reject";
			public static final String SCORE_EXCEEDS_THRESHOLD = "warn.score_exceeds_threshold";
			public static final String PENDING_AUTHENTICATION = "warn.pending_authentication";
			public static final String ACH_VERIFICATION_FAILED = "warn.ach_verification_failed";
			public static final String DECISION_PROFILE_REVIEW = "warn.decision_profile_review";
			public static final String CONSUMER_AUTHENTICATION_REQUIRED = "warn.consumer_authentication_required"; 
			public static final String CONSUMER_AUTHENTICATION_FAILED = "warn.consumer_authentication_failed";

			public static final String NO_PAGEURL = "warn.no_pageurl";
		}
		
		// Info Messages 
		public interface Info {
			public static final String CANCEL_REQUESTED = "info.cancel.requested";
			
		}
	}

}
