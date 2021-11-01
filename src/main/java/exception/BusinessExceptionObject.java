package exception;

import lombok.*;

/**
 * @author salihu h.
 *
 */

@Getter
@Setter(value=AccessLevel.NONE)
@ToString
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(of={"identifier","exceptionItemName","exceptionItemValue","comment"}, callSuper=false)
public final class BusinessExceptionObject implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String identifier;
	String username;
	String exceptionItemName;
	String exceptionItemValue;
	String productCode;
	String productTypeCode;
	String comment;
	String moduleId;
}
