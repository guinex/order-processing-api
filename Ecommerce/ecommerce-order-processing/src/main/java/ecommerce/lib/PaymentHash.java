package ecommerce.lib;


public class PaymentHash  {
	private String confirmationDate;
	private String methodName;
	private String tnxNumber;
	private Long userId;
	private Long total;
	private String payType;
	public String getConfirmationDate() {
		return confirmationDate;
	}
	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getTnxNumber() {
		return tnxNumber;
	}
	public void setTnxNumber(String tnxNumber) {
		this.tnxNumber = tnxNumber;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public PaymentHash(){

	}
	@Override
	public String toString(){
		return "{confirmationDate: "+confirmationDate +", "
				+"methodName: "+methodName +", "
				+"tnxNumber: "+tnxNumber +", "
				+"userId: "+userId +", "
				+"total: "+total +", "
				+"payType: "+payType +"} ";

	}

}
