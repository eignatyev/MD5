package test.java;

import org.json.JSONObject;

public class RequestDetails {
	
	private String paramText;
	private String paramRandomParam;
	private Integer responseCode;
	private String responseBody;	
	private JSONObject responseJSON;
	private String url;
	
	RequestDetails() {
		this.setUrl("http://md5.jsontest.com/");
	}
	
	/**
	 * @return the responseBody
	 */
	protected String getResponseBody() {
		return responseBody;
	}
	/**
	 * @param responseBody the responseBody to set
	 */
	protected void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	/**
	 * @return the responseJSON
	 */
	public JSONObject getResponseJSON() {
		return responseJSON;
	}
	/**
	 * @param responseJSON the responseJSON to set
	 */
	protected void setResponseJSON(JSONObject responseJSON) {
		this.responseJSON = responseJSON;
	}
	/**
	 * @return query parameter "text"
	 */
	protected String getParamText() {
		return paramText;
	}
	/**
	 * @param paramText to set
	 */
	protected void setParamText(String text) {
		this.paramText = text;
	}
	/**
	 * @return parameter responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode to set
	 */
	protected void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return parameter url
	 */
	protected String getUrl() {
		return url;
	}
	/**
	 * @param url to set
	 */
	protected void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the paramRandomParam
	 */
	protected String getParamRandomParam() {
		return paramRandomParam;
	}

	/**
	 * @param paramRandomParam the paramRandomParam to set
	 */
	protected void setParamRandomParam(String paramRandomParam) {
		this.paramRandomParam = paramRandomParam;
	}
}
