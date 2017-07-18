/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.qk.module.font.exitsub.entity;
import com.qk.core.ibatis.annotation.po.TableName;
import com.qk.core.ibatis.annotation.po.FieldName;
import com.qk.core.ibatis.beans.Po;
import com.qk.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 提取表table1  数据实体类
 * @author bing.wang
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="biz_exit_sub")
public class ExitSub  extends Po{  
      
	    /**  
	     * 主键Id  
	     */ 
    	@FieldName(name="Rid")
	    private Integer rid;  
	    /**  
	     * 提取人id  
	     */ 
    	@FieldName(name="exit_Master_id")
	    private Integer exitMasterId;  
	    /**  
	     * 提取金额  
	     */ 
    	@FieldName(name="exit_Sub_amount")
	    private Integer exitSubAmount;  
	    /**  
	     * 提取状态  
	     */ 
    	@FieldName(name="exit_sub_state")
	    private String exitSubState;  
	    /**  
	     * 匹配id  
	     */ 
    	@FieldName(name="Match_id")
	    private Integer matchId;  
	    /**  
	     * 创建时间  
	     */ 
    	@FieldName(name="Create_time")
	    private Date createTime;  
	    /**  
	     * 子优先级  
	     */ 
    	@FieldName(name="Sub_pri")
	    private Integer subPri;  
	    /**  
	     * 是否锁定  
	     */ 
    	@FieldName(name="Is_locked")
	    private String isLocked;  
	    /**  
	     * 提取人员  
	     */ 
    	@FieldName(name="exit_member")
	    private Integer exitMember;  
	    /**  
	     * 提取类型  
	     */ 
    	@FieldName(name="Exit_type")
	    private String exitType;  
	    /**  
	     * 匹配次数  
	     */ 
    	@FieldName(name="match_count")
	    private Integer matchCount;  
 
	    public void setRid(Integer rid) {  
	        this.rid = rid;  
	    }  
	      
	    public Integer getRid() {  
	        return this.rid;  
	    }  
	    public void setExitMasterId(Integer exitMasterId) {  
	        this.exitMasterId = exitMasterId;  
	    }  
	      
	    public Integer getExitMasterId() {  
	        return this.exitMasterId;  
	    }  
	    public void setExitSubAmount(Integer exitSubAmount) {  
	        this.exitSubAmount = exitSubAmount;  
	    }  
	      
	    public Integer getExitSubAmount() {  
	        return this.exitSubAmount;  
	    }  
	    public void setExitSubState(String exitSubState) {  
	        this.exitSubState = exitSubState;  
	    }  
	      
	    public String getExitSubState() {  
	        return this.exitSubState;  
	    }  
	    public void setMatchId(Integer matchId) {  
	        this.matchId = matchId;  
	    }  
	      
	    public Integer getMatchId() {  
	        return this.matchId;  
	    }  
	    public String getCreateTimeString() {  
	        return DateUtil.formatDatetime(getCreateTime());  
	    }  
	    public void setCreateTime(Date createTime) {  
	        this.createTime = createTime;  
	    }  
	      
	    public Date getCreateTime() {  
	        return this.createTime;  
	    }  
	    public void setSubPri(Integer subPri) {  
	        this.subPri = subPri;  
	    }  
	      
	    public Integer getSubPri() {  
	        return this.subPri;  
	    }  
	    public void setIsLocked(String isLocked) {  
	        this.isLocked = isLocked;  
	    }  
	      
	    public String getIsLocked() {  
	        return this.isLocked;  
	    }  
	    public void setExitMember(Integer exitMember) {  
	        this.exitMember = exitMember;  
	    }  
	      
	    public Integer getExitMember() {  
	        return this.exitMember;  
	    }  
	    public void setExitType(String exitType) {  
	        this.exitType = exitType;  
	    }  
	      
	    public String getExitType() {  
	        return this.exitType;  
	    }  
	    public void setMatchCount(Integer matchCount) {  
	        this.matchCount = matchCount;  
	    }  
	      
	    public Integer getMatchCount() {  
	        return this.matchCount;  
	    }  
 
}