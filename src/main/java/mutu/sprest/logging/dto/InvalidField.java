package mutu.sprest.logging.dto;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

public class InvalidField {
	private String fieldName;
	private String mesageCode;
	private String mesage;
	
	public InvalidField() {}
	
	public InvalidField(String fieldName, String mesageCode, String mesage) {
		this.fieldName = fieldName;
		this.mesageCode = mesageCode;
		this.mesage = mesage;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMesageCode() {
		return mesageCode;
	}
	public void setMesageCode(String mesageCode) {
		this.mesageCode = mesageCode;
	}
	public String getMesage() {
		return mesage;
	}
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
}
