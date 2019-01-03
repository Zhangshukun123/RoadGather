package zhonggu.aiper.com.baselibrary.Utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UrLMdUtlis {

    private static String encryptionAlgorithm = "SHA-1";

    /**
     * 方法作用，生成加在URL后面的4个参数中一个，LOL简介
     * @param strSrc 把参数转换成Json后的字符串
     * @param encName 默认MD5
     * @return
     */
    public static String digest(String strSrc, String encName) {
        String strDes = null;
        try {
            String res = AESOperator.getInstance().encrypt(strSrc);
            strDes = res.substring(0, 20);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return strDes;
    }


    /****
     * 方法作用，生成加在URL后面的4个参数中一个，signature 签名
     * @param appid 服务器提供
     * @param token 服务器提供
     * @param lol  通过方法生成，简介
     * @param millis get直接获取 时间锉
     * @return
     */
    public static String generateSignature(String appid, String token, String lol, long millis) {
        String timestamp = String.valueOf(millis);
        String signature = null;
        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(timestamp) && !TextUtils.isEmpty(appid)) {
            List<String> srcList = new ArrayList<String>();
            srcList.add(timestamp);
            srcList.add(appid);
            srcList.add(token);
            srcList.add(lol);
            // 按照字典序逆序拼接参数
            Collections.sort(srcList);
            Collections.reverse(srcList);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < srcList.size(); i++) {
                sb.append(srcList.get(i));
            }
            signature = digest(sb.toString(), encryptionAlgorithm);
            srcList.clear();
            srcList = null;
        }
        return signature;
    }
}
