package test.java;

public class Request extends RequestMethods {
	public Request(String text) throws Exception {
		this.setParamText(text);
		this.proceedGETRequest();
	}
}
