package com.yinfeixing.entity.utils.string;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

public class PinYinUtils {
    public PinYinUtils() {
    }

    public static String cleanChar(String chines) {
        chines = chines.replaceAll("[\\p{Punct}\\p{Space}]+", "");
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}<>《》【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(chines);
        chines = matcher.replaceAll("").trim();
        return chines;
    }

    public static String converterToFirstSpell(String chines) {
        if (StringUtils.isNotBlank(chines)) {
            chines = cleanChar(chines);
            String afterProcessStr = "";
            char[] nameChars = chines.toCharArray();
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);

            for(int i = 0; i < nameChars.length; ++i) {
                if (nameChars[i] > 128) {
                    try {
                        afterProcessStr = afterProcessStr + PinyinHelper.toHanyuPinyinStringArray(nameChars[i], defaultFormat)[0].charAt(0);
                    } catch (BadHanyuPinyinOutputFormatCombination var6) {
                        var6.printStackTrace();
                    }
                } else {
                    afterProcessStr = afterProcessStr + nameChars[i];
                }
            }

            return afterProcessStr;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        System.out.println(dateFormat.format(new Date(1530806400020L)));
        System.out.println(String.format("%05d", 2L));
    }
}