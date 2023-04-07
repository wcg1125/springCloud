package com.hln.znbf.constants;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author wcg
 */
public class DsrnhConstants {
    public static Map<String, String> TYPE = new LinkedHashMap<String, String>();
    public static Map<String, String> PKLX = new LinkedHashMap<String, String>();
    public static Map<String, String> ZPYY = new LinkedHashMap<String, String>();
    public static Map<String, String> YHZGX = new LinkedHashMap<String, String>();
    public static Map<String, String> WHCD = new LinkedHashMap<String, String>();
    public static Map<String, String> JKZK = new LinkedHashMap<String, String>();
    public static Map<String, String> ZZMM = new LinkedHashMap<String, String>();
    public static Map<String, String> LDNL = new LinkedHashMap<>();
    public static Map<String, String> PTLX = new LinkedHashMap<>();
    public static Map<String, String> BFLX = new LinkedHashMap<>();
    public static Map<String, String> GWLX = new LinkedHashMap<>();



    public static void initialize() {
       
        TYPE.put("db", "低保户");
        TYPE.put("by", "边缘户");
        TYPE.put("tk", "特困户");

        //====贫困类型=====
        PKLX.put("01", "低保户");
        PKLX.put("02", "其他经济困难户");

        //====致贫原因=====
        ZPYY.put("03", "因残");
        ZPYY.put("0201", "因自然灾害（洪涝灾害）");
        ZPYY.put("0202", "因自然灾害（地质灾害）");
        ZPYY.put("0203", "因自然灾害（旱灾）");
        ZPYY.put("0204", "因自然灾害（生物灾害）");
        ZPYY.put("0205", "因自然灾害（气象灾害）");
        ZPYY.put("0206", "因自然灾害（地震灾害）");
        ZPYY.put("0299", "因自然灾害（其他）");
        ZPYY.put("01", "因病");
        ZPYY.put("09", "因学");
        ZPYY.put("04", "因缺劳动力");
        ZPYY.put("05", "因意外事故");
        ZPYY.put("06", "因产业失败");
        ZPYY.put("07", "因务工就业不稳");
        ZPYY.put("99", "其他");

        //====家庭成员-与户主关系=====
        YHZGX.put("01", "本人");
        YHZGX.put("10", "配偶");
        YHZGX.put("20", "子/婿");
        YHZGX.put("30", "女/媳");
        YHZGX.put("40", "孙子、孙女或外孙子、外孙女");
        YHZGX.put("50", "父母");
        YHZGX.put("60", "祖父母或外祖父母");
        YHZGX.put("70", "兄弟姐妹");
        YHZGX.put("99", "其他");

        //====家庭成员-文化程度=====
        WHCD.put("10", "研究生教育");
        WHCD.put("20", "大学本科教育");
        WHCD.put("30", "专科教育");
        WHCD.put("40", "中等职业教育");
        WHCD.put("50", "普通高级中学教育");
        WHCD.put("60", "初级中学教育");
        WHCD.put("70", "小学教育");
        WHCD.put("90", "其他");

        //====家庭成员-健康状况=====
        JKZK.put("1", "健康");
        JKZK.put("2", "一般（长病）");
        JKZK.put("3", "一般残疾");
        JKZK.put("4", "重度残疾");
        JKZK.put("9", "其他");
        //====家庭成员-政治面貌=====
        ZZMM.put("1", "中共党员");
        ZZMM.put("2", "中共预备党员");
        ZZMM.put("3", "共青团员");
        ZZMM.put("4", "民革会员");
        ZZMM.put("5", "民盟盟员");
        ZZMM.put("6", "民建会员");
        ZZMM.put("7", "民进会员");
        ZZMM.put("8", "农工党党员");
        ZZMM.put("9", "致公党党员");
        ZZMM.put("10", "九三学社社员");
        ZZMM.put("11", "台盟盟员");
        ZZMM.put("12", "无党派民主人士");
        ZZMM.put("13", "群众");
        ZZMM.put("99", "其他");
        //====家庭成员-劳动能力=====
        LDNL.put("01", "有劳动能力");
        LDNL.put("02", "部分丧失劳动能力");
        LDNL.put("03", "完全丧失劳动能力");
        LDNL.put("04", "无劳动能力");

        //========补贴类型=======
        PTLX.put("1", "补助类");
        PTLX.put("2", "救助类");
        PTLX.put("3", "减免类");
        PTLX.put("4", "奖励类");

        //========帮扶类型==========
        BFLX.put("01", "生活帮扶");
        BFLX.put("02", "保障帮扶");
        BFLX.put("03", "金融帮扶");
        BFLX.put("04", "生产帮扶");
        BFLX.put("05", "就业帮扶");
        BFLX.put("06", "社会帮扶");

        //==========岗位类型=========
        GWLX.put("01", "零用散工");
        GWLX.put("02", "公益岗位");
        GWLX.put("03", "社会岗位");

    }
}