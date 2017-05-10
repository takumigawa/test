package schedule.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.internal.FacesMessageUtil;
import javax.faces.internal.UIComponentUtil;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.seasar.framework.util.AssertionUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.teeda.extension.exception.ExtendValidatorException;
import org.seasar.teeda.extension.util.TargetCommandUtil;
import org.seasar.teeda.extension.validator.ValidationTargetSelectable;

import schedule.dao.TSettingDao;
import schedule.entity.TSetting;
import schedule.web.common.CommonConstants;

public class TUseKengenCodeValidator implements Validator, StateHolder,
ValidationTargetSelectable {

    public static final String VALIDATOR_ID = "javax.faces.Required";

    public static final String REQUIRED_MESSAGE_ID = "javax.faces.component.UIInput.REQUIRED";

    protected boolean transientValue = false;

    protected String target;

    protected String[] targets;

    protected String messageId;
    
    public TSettingDao setDao;

    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        AssertionUtil.assertNotNull("context", context);
        AssertionUtil.assertNotNull("component", component);
        if (!isTargetCommandValidation(context, targets)) {
            return;
        }
        if (!chkValue(value)) {
            Object[] args = new Object[] { UIComponentUtil.getLabel(component) };
            String msgId = (getMessageId() != null) ? getMessageId()
                    : REQUIRED_MESSAGE_ID;
            FacesMessage message = FacesMessageUtil.getMessage(context, msgId,
                    args);
            throw new ExtendValidatorException(message, new String[] { msgId });
        }
    }
    
    private boolean chkValue(Object value) {
    	
    	TSetting ety_set = setDao.selectById(CommonConstants.k1_system , CommonConstants.k2_authority, value.toString());
    	
    	if (ety_set == null) {
    		return true;
    	}
    	
    	return false;
    }

    public boolean isTransient() {
        return transientValue;
    }

    public void setTransient(boolean transientValue) {
        this.transientValue = transientValue;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
        if (StringUtil.isEmpty(target)) {
            return;
        }
        targets = StringUtil.split(target, ", ");
    }

    public String getMessageId() {
        return !StringUtil.isEmpty(messageId) ? messageId : REQUIRED_MESSAGE_ID;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Object saveState(FacesContext context) {
        Object[] values = new Object[2];
        values[0] = target;
        values[1] = messageId;
        return values;
    }

    public void restoreState(FacesContext context, Object state) {
        Object[] values = (Object[]) state;
        setTarget((String) values[0]);
        messageId = (String) values[1];
    }

    public boolean isTargetCommandValidation(FacesContext context,
            String[] targets) {
        return TargetCommandUtil.isTargetCommand(context, targets);
    }

    public String[] getTargets() {
        return targets;
    }
}