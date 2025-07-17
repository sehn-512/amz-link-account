package sea.scplus.consumer.common.util.cyber;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SiteCodeUtil {
	@Value("${domain.name}")
	private String domainName;

	@Value("${frame.domain.name}")
	private String frameDomainName;

	@Value("${hq.check.do.pc}")
	private String HQCheckDoPC;
	
	@Value("${hq.check.do.mobile}")
	private String HQCheckDoMobile;
	
	/*
	 * ca or ca_fr
	 */
	public String getSite_code(HttpServletRequest request) {
		String site = "";

		if (request.getSession().getAttribute("site_cd") != null) { // is this
																	// right?
			site = (String) request.getSession().getAttribute("site_cd");
		} else {
			// (1) Check request parameter
			site = request.getParameter("lang");
			site = (null == site) ? "" : site.toLowerCase();

			// (2) Check URL if not in request parameter
			if (site.length() == 0) {
				String url = request.getRequestURL().toString(); // THIS DOES
																	// NOT GET
																	// PARENT
																	// URL IF
																	// YOU'RE IN
																	// AN
																	// IFRAME!!!
																	// WARNING
				if (url != null) {
					if (url.indexOf("/ca/") > -1) {
						site = "ca";
					} else if (url.indexOf("/ca_fr/") > -1) {
						site = "ca_fr";
					}
				}
			}

			// (3) Default to "ca" otherwise
			if (!"ca".equals(site) && !"ca_fr".equals(site)) {
				site = "ca";
			}
		}

		return site;
	}

	/*
	 * en or fr
	 */
	public String getLanguage_code(HttpServletRequest request) {
		String languageCode = "";
		if ("ca".equals(getSite_code(request))) {
			languageCode = "en";
		} else {
			languageCode = "fr";
		}

		return languageCode;
	}

	/*
	 * i.e. www.samsung.com stgweb4.samsung.com
	 * 
	 */
	public String getDomain_name(HttpServletRequest request) {
		return domainName;
	}

	/*
	 * i.e. http://www.samsung.com/ca https://stgweb4.samsung.com/ca_fr
	 * 
	 */
	public String getBase_url(HttpServletRequest request) {
		return request.getScheme() + "://" + domainName + "/" + getSite_code(request);
	}

	/*
	 * i.e. support-ca.samsung.com stgwebca.samsung.com
	 * 
	 */
	public String getFrame_domain_name(HttpServletRequest request) {
		return frameDomainName;
	}

	/*
	 * i.e. http://support-ca.samsung.com/seca https://stgwebca.samsung.com/seca
	 * 
	 */
	public String getFrame_base_url(HttpServletRequest request) {
		return request.getScheme() + "://" + frameDomainName + "/seca";
	}

	/*
	 * i.e. http://www.samsung.com/ca/samsungaccount
	 * 
	 */
	public String getHome_page(HttpServletRequest request) {
		return getBase_url(request) + "/samsungaccount";
	}

	public String getHQCheckUrl(HttpServletRequest request) {
		String ua=request.getHeader("User-Agent").toLowerCase();
		if(ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
		  return HQCheckDoMobile;
		}
		else {
			return HQCheckDoPC;
		}
	}
}