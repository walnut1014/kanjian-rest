package name.walnut.controller.vo;

public class RegisterVo {
	
	private String mobilephone;
	private String veriCode;
	
	public RegisterVo(String mobilephone, String veriCode) {
		this.mobilephone = mobilephone;
		this.veriCode = veriCode;
	}
	
	
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getVeriCode() {
		return veriCode;
	}
	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

	
}
