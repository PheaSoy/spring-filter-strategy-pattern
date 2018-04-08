# spring-filter-strategy-pattern
## We are trying to added filter in doFilter dynamic using strategy design parttern.

----

    public abstract class AbstractFilterPolicy {
	public abstract boolean applyDoFilter(HttpServletRequest request, HttpServletResponse response) throws IOException;

	private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws IOException {
		String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
		return serialized.getBytes();
	}

	protected void replyFilterFail(HttpServletResponse response, String keyMessage, int code) throws IOException {

		String[] msg = keyMessage.split(",");
		if (msg.length == 0) {
			msg[0] = "unknown";
			msg[1] = "Unknow message";
		}
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setError(msg[0]);
		errorResponse.setError_discription(msg[1]);
		byte[] responseToSend = restResponseBytes(errorResponse);
		response.setStatus(code);
		response.getOutputStream().write(responseToSend);
		response.addHeader("Content-Type", "application/json");
	}}
----
All the class extend then want add filter we can extend this main class by provide the logics in their own function.
