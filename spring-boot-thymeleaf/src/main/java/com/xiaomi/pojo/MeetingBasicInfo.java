package com.xiaomi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeetingBasicInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 会议议程 */
	private String meetingYicheng; 
	/** 会议议程英文 */
	private String meetingYichengEn; 
	/** 会议id */ 
	private String id; 
	/** 生成邀请码随机数 */ 
	private String meetingRand; 
	/** 排序权重 */ 
	private String meetingSort; 
	/** 会议年度类型 */ 
	private String meetingYearplanType; 
	/** 会议年度类型代码 */ 
	private String meetingYearplanTypeDict; 
	/** 会议类型 */
	private String meetingType; 
	/** 会议类型代码 */
	private String meetingTypeDict; 
	/** 会议图片URL */ 
	private String meetingPicUrl; 
	/** 会议名称 */
	private String meetingName; 
	/** 会议名称(英文) */
	private String meetingNameEn; 
	/** 会议开始时间 */
	private Date meetingStartDate; 
	/** 会议结束时间 */
	private Date meetingEndDate; 
	/** 会议预计时间(YYYY-MM) */ 
	private String expectedYearMouth; 
	/** 会议发布时间 */
	private Date meetingReleaseDate;
	/** 主办方 */
	private String meetingSponsor; 
	/** 主办方(英文) */
	private String meetingSponsorEn; 
	/** 主要内容 */
	private String meetingContent; 
	/** 主要内容(英文) */
	//@Excel(name = "主要内容(英文)",width = 50, height = 25)
	private String meetingContentEn; 
	/** 会议简介 */
	private String meetingIntroduction; 
	/** 会议简介(英文) */
	private String meetingIntroductionEn; 
	/** 会议状态  */ 
	private String meetingStatus; 
	/** 会议状态编码 */ 
	private String meetingStatusDict; 
	/** 月份 */ 
	private String meetingMouth; 
	/** 年份 */ 
	private String meetingYear; 
	/** 会议是否开启报名(01是,02否) */ 
	private String meetingIsEnroll; 

	/** 会议信息是否在发展合作平台网站展示(01是,02否) 英文 */
	private String meetingIsShowEn; 

	/** 会议信息是否在发展合作平台网站展示(01是,02否) */ 
	private String meetingIsShow; 
	/** 主办会议形式 */
	private String meetingMainForm; 
	/** 主办会议形式代码 */
	private String meetingMainFormDict; 
	/** 主办会议形式(英文) */
	private String meetingMainFormEn; 
	/** 主办会议形式代码(英文) */
	private String meetingMainFormDictEn; 
	/** 主办会议拟合办方 */
	private String meetingOrganizer; 
	/** 主办会议拟合办方(英文) */
	private String meetingOrganizerEn; 
	/** 主办会议会议地点 */
	private String meetingPlace; 
	/** 主办会议会议地点(英文) */
	private String meetingPlaceEn; 
	/** 主办会议会议规模 */
	private String meetingScale; 
	/** 主办会议填报部门 */
	private String meetingFillInDept; 
	/** 主办会议填报部门代码 */
	private String meetingFillInDeptDict; 
	/** 主办会议牵头部门 */
	private String meetingHeadDept; 
	/** 主办会议牵头部门代码 */
	private String meetingHeadDeptDict; 
	/** 主办会议参会人员 */
	private String meetingMen; 
	/** 主办会议天数 */
	private String meetingDay; 
	/** 主办会议预算 */
	private String meetingBudget; 
	/** 外部会议时间 */
	private String meetingOutDate; 
	/** 外部会议形式 */ 
	private String meetingOutMainForm; 
	/** 外部会议会议频次 */ 
	private String meetingRate; 
	/** 外部会议会议网址 */ 
	private String meetingUrl; 
	/** 外部会议主办方类别 */ 
	private String meetingSponsorType; 
	/** 外部会议主办方类别代码 */ 
	private String meetingSponsorTypeDict; 
	/** 创建人 */ 
	private String createBy; 
	/** 创建时间 */ 
	private Date createDate; 
	/** 修改人 */ 
	private String updateBy; 
	/** 修改时间 */ 
	private Date updateDate; 
	/** 是否显示中文线上报名入口(Yes,No) */
	private String isShowZhOnline; 
	/** 是否显示英文现场报名入口(Yes,No) */
	private String isShowEnOnsite; 
	/** 是否显示英文线上报名入口(Yes,No) */
	private String isShowEnOnline; 
	/** 是否显示中文现场报名入口(Yes,No) */
	private String isShowZhOnsite; 
	/** 是否显示中文(现场)参会意向入口(Yes,No) */
	private String isShowZhOnsiteIntention; 
	/** 是否显示中文(网络)参会意向入口(Yes,No) */
	private String isShowZhOnlineIntention; 
	/** 是否显示英文(现场)参会意向入口(Yes,No) */
	private String isShowEnOnsiteIntention; 
	/** 是否显示英文(网络)参会意向入口(Yes,No) */
	private String isShowEnOnlineIntention;
	/** app会议详情背景色 */
	private String backgroundcolorForApp;
}
