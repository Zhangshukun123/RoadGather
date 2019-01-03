package zhonggu.aiper.com.baselibrary.base;

import java.io.Serializable;


public class BaseModel implements Serializable {
    public MetaBean meta;

    public boolean isSuccess() {
        if (null != meta && meta.success) {
            return true;
        }
        return false;
    }

    public String getMessage() {
        return null != meta ? meta.message : "";
    }

    public String code() {
        return null != meta ? meta.code : "";
    }
}
