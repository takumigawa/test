package ko.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.internal.FacesMessageUtil;
import javax.faces.internal.UIComponentUtil;
import javax.faces.validator.ValidatorException;

import ko.dao.MKanjoDao;

import org.seasar.teeda.extension.validator.AbstractCompareValidator;

public class TChkjuhukuValidator extends AbstractCompareValidator {
	private static final String MESSAGE_ID = "err.JuhukuMes";
	public MKanjoDao Mkao;

	protected void doValidate(FacesContext context, UIComponent component,
			Object value, UIComponent targetComponent, Object targetValue)
			throws ValidatorException {

		Object[] args = { UIComponentUtil.getLabel(targetComponent),
                UIComponentUtil.getLabel(component) };
		String messaId = getMessageId();
		String test = Mkao.getOrderSEQ(value);
		FacesMessage message = FacesMessageUtil.getMessage(context,
                messaId, args);
		if(test != null){
			throw new ValidatorException(message, messaId, args);
		}
	}

	public String getMessageId() {
		String msg = super.getMessageId();
		return (msg != null) ? msg : MESSAGE_ID;
	}

}
