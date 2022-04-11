package com.xiaomi.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmailTaskDetail implements Serializable {
    /** 语言版本(zh,en) */
	@Excel(name = "语言版本", width = 15, addressList = true, replace = {"中文_zh", "英文_en"})
	private String languageType; // 必须
	/** 收件人名称 */
	@Excel(name = "收件人名称", width = 20)
	private String recieveName; // 必须
	/** 收件人邮箱 */
    // 需要加入此注解
	@Excel(name = "收件人邮箱", width = 20)
	private String recieveEmail; // 非必须
	/** 邮件内容 */
	private String emailContent; // 非必须
}