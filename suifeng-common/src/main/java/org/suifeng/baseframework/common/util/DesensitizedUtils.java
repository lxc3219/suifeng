package org.suifeng.baseframework.common.util;


import org.suifeng.baseframework.model.enums.SensitiveTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据脱敏工具类
 */
@Slf4j
public class DesensitizedUtils {

    /**
     * 数据脱敏
     * @param sourceData
     * @param dataSourceType
     * @return
     */
    public static String dataMask(String sourceData, SensitiveTypeEnum dataSourceType) {
        if (StringUtils.isBlank(sourceData)) {
            return "";
        }
        String targetData = "";
        switch(dataSourceType) {
            case CHINESE_NAME:
                targetData = nameMask(sourceData);
                break;
            case ID_CARD:
                targetData = idCardMask(sourceData);
                break;
            case MOBILE_PHONE:
                targetData = mobilePhoneMask(sourceData);
                break;
            case FIXED_PHONE:
                targetData = fixedPhoneMask(sourceData);
                break;
            case EMAIL:
                targetData = emailMask(sourceData);
                break;
            case BANK_CARD:
                targetData = bankCardMask(sourceData);
                break;
            case OTHER:
                targetData = otherMask(sourceData);
                break;
            default:
                log.error("暂不支持此类数据源脱敏");
        }
        return StringUtils.isNotBlank(targetData) ? targetData : sourceData;
    }

    /**
     * 姓名脱敏
     * 隐藏姓名中的第一个字，如为英文等其他语种，也是隐藏第一个字母。如：*大友、*安、*ahn
     * @return
     */
    public static String nameMask(String fullName) {
        if (StringUtils.isNotBlank(fullName)) {
            String givenName = StringUtils.right(fullName, StringUtils.length(fullName) - 1);
            fullName = StringUtils.leftPad(givenName, StringUtils.length(fullName), "*");
        }
        return fullName;
    }

    /**
     * 身份证号脱敏
     * 普通隐藏规则：显示前1、5、6、7、8、9、10、11、12、18位，其余用*号代替。（凡是系统显示后还需用户检查确认的，可使用这个规则。）
     *  示例：3***23197402*****X
     * @param id 身份证号
     * @return
     */
    public static String idCardMask(String id) {
        if (StringUtils.isNotBlank(id)) {
            // 18位身份证脱敏
            if (StringUtils.length(id) == 18) {
                id = StringUtils.left(id, 1)
                        .concat("***")
                        .concat(StringUtils.mid(id, 4, 8))
                        .concat("*****")
                        .concat(StringUtils.right(id, 1));
            }
        }
        return id;
    }

    /**
     * 手机号码脱敏
     * 显示前3位+****+后4位。如：137****9050
     * @param phoneNum
     * @return
     */
    public static String mobilePhoneMask(String phoneNum) {
        if (StringUtils.isNotBlank(phoneNum)) {
            phoneNum = StringUtils.left(phoneNum, 3)
                    .concat(StringUtils.removeStart(
                            StringUtils.leftPad(StringUtils.right(phoneNum, 4), StringUtils.length(phoneNum), "*"),
                            "***"));
        }
        return phoneNum;
    }

    /**
     * 固定电话号码脱敏
     * 显示区号和后4位，其余用*号代替，如：0571****8709
     * @param phoneNum
     * @return
     */
    public static String fixedPhoneMask(String phoneNum) {
        if (StringUtils.isNotBlank(phoneNum)) {
            // TODO
            StringUtils.leftPad(StringUtils.right(phoneNum, 4), StringUtils.length(phoneNum), "*");
        }
        return phoneNum;
    }

    /**
     * 邮箱脱敏
     * @前面的字符显示3位，3位后显示3个*，@后面完整显示 如：con***@163.com
     * 如果少于三位，则全部显示，@前加***，例如tt@163.com 则显示为tt***@163.com
     * @return
     */
    public static String emailMask(String email) {
        if (StringUtils.isNotBlank(email)) {
            int index = StringUtils.indexOf(email, "@");
            if (index > 0) {
                email = StringUtils.left(email, index >= 3 ? 3 : index)
                        .concat("***")
                        .concat(StringUtils.mid(email, index, StringUtils.length(email)));
            }
        }
        return email;
    }

    /**
     * 银行卡卡号脱敏
     * 显示前6位+ *（实际位数）+后4位。 如：622575******1496
     * @param cardNum 银行卡号
     * @return
     */
    public static String bankCardMask(String cardNum) {
        if (StringUtils.isNotBlank(cardNum)) {
            cardNum = StringUtils.left(cardNum, 6)
                    .concat(StringUtils.removeStart(
                            StringUtils.leftPad(
                                    StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"),
                            "******"));
        }
        return cardNum;
    }

    /**
     * 其他各类敏感信息脱敏
     * 显示前1/3和后1/3段字节，其他用*号代替。
     * @param otherData
     * @return
     */
    public static String otherMask(String otherData) {
        if (StringUtils.isNotBlank(otherData)) {
            int len = Math.round(StringUtils.length(otherData) / 3);
            otherData = StringUtils.left(otherData, len)
                    .concat(StringUtils.repeat("*", StringUtils.length(otherData) - 2 * len))
                    .concat(StringUtils.right(otherData, len));
        }
        return otherData;
    }

}
