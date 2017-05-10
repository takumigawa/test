package serviceadapter.validator;


import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.internal.FacesMessageUtil;
import javax.faces.internal.UIComponentUtil;

import javax.faces.validator.ValidatorException;
 
import org.seasar.teeda.extension.validator.AbstractCompareValidator;

import serviceadapter.common.CommonConstants;
import serviceadapter.dao.MAdapterDao;
import serviceadapter.entity.MAdapter;
import serviceadapter.service.SibServiceAdapter;
 
public class TUserAuthValidator extends AbstractCompareValidator {
    private static final String MESSAGE_ID = "err.AuthUserMessege";
    
    public MAdapterDao adpDao;
    
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
    	
    	SibServiceAdapter SAA = new SibServiceAdapter();

    	try {
    		
    		MAdapter ety_Adp = adpDao.selectById(CommonConstants.SERVICE_TYPE_USERAUTH);
    		
	    	Map<String, String> reqParam = new HashMap<String, String>();
	    	Map<String, String> resParam;
	    	
	    	reqParam.put("userID", targetValue.toString());
	    	reqParam.put("userPas", value.toString());
	    	
	    	SAA.setServiceProvider(ety_Adp.getServiceurl());

			resParam = SAA.postData(reqParam);
			
			if (!CommonConstants.STR_TRUE.equals(resParam.get("userAuth"))) {
				return false;
			} 
			
		} catch (Exception e) {
			return false;
		}
    	
    	
    	return true;
    	
    }
} 