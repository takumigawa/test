package schedule.validator;

import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.internal.FacesMessageUtil;
import javax.faces.internal.UIComponentUtil;

import javax.faces.validator.ValidatorException;
 
import org.seasar.teeda.extension.validator.AbstractCompareValidator;
 
public class TNewUserReqierdPasValidator extends AbstractCompareValidator {
    private static final String MESSAGE_ID = "err.NewUserPas";
    
    protected void doValidate(FacesContext context, UIComponent component,
            Object value, UIComponent targetComponent, Object targetValue)
            throws ValidatorException {

   
        if (!isChk(value, targetValue)) {
            Object[] args = { UIComponentUtil.getLabel(targetComponent),
                    UIComponentUtil.getLabel(component) };
            String messaId = getMessageId();
            FacesMessage message = FacesMessageUtil.getMessage(context,
                    messaId, args);
            throw new ValidatorException(message, messaId, args);
        }
    }
    
    public String getMessageId() {
        String msg = super.getMessageId();
        return (msg != null) ? msg :MESSAGE_ID;
    }
    
    private boolean isChk(Object value, Object targetValue){

    	try {			
			
			if("-1".equals(targetValue) && "".equals(value)) {
				return false;
			}			
			
		} catch (Exception e) {
			return false;
		}
    	
    	
    	return true;
    	
    }
} 