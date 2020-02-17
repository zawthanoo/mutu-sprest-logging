package mutu.sprest.logging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvalidField {
	private String fieldName;
	private String mesageCode;
	private String mesage;
}
