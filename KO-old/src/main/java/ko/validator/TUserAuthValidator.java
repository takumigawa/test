package ko.validator;


import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.internal.FacesMessageUtil;
import javax.faces.internal.UIComponentUtil;

import javax.faces.validator.ValidatorException;

import org.seasar.teeda.extension.validator.AbstractCompareValidator;

import ko.common.CommonConstants;
import ko.service.SibServiceAdapter;

public class TUserAuthValidator extends AbstractCompareValidator {
    private static final String MESSAGE_ID = "err.AuthUserMessege";



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

	protected Map<String, String> getServiceValue(String serviceType, Map<String, String> requestParams) throws Exception {

		//サービスアダプタクラスをインスタンスする
		SibServiceAdapter SAdp = new SibServiceAdapter();

		//サービスプロバイダのURLを設定する。
		SAdp.setServiceProvider("http://localhost:8080/sibserviceadapter/view/adapter/adapter.html");
		//サービスプロバイダのタイプを設定する。
		SAdp.setServiceProviderType(serviceType);

		//データをポストし、戻りのマップデータを返却する。
		return SAdp.postData(requestParams);

	}

    private boolean isChk(Object value, Object targetValue){

 //   	SibServiceAdapter SAA = new SibServiceAdapter();

    	try {

			Map<String, String> reqParam = new HashMap<String, String>();
			Map<String, String> resParam;

			reqParam.put("userID", targetValue.toString());
			reqParam.put("userPas", value.toString());

			resParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_USERAUTH,reqParam);

//			resParam = SAA.postData(reqParam);

			if (!CommonConstants.STR_TRUE.equals(resParam.get("userAuth"))) {
				return false;
			}


		} catch (Exception e) {
			return false;
		}


    	return true;

    }
}