/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package club.xiaoandx.commons.core;

/**  
 * <p>插入数据状态码</p> 
 * @ClassName:AddCode   
 * @author: XIAOX.周巍 
 * @date: 2019年5月19日 下午8:37:36   
 * @since: JDK1.8 
 * @version V2.0
 * @Copyright: 注意：本内容仅限于学习传阅，禁止外泄以及用于其他的商业目
 */
public interface DaoCode {
	
	/**
	 * 数据库操作成功
	 */
	Integer SUCCESS = 1;
	/**
	 * 数据库操作失败
	 */
	Integer ERROR = 0;
	/**
	 * 判断用户总成绩
	 */
	Integer SROCE_NUMBER = 0;
	/**
	 * 用户id为0
	 */
	Integer ZEROUSERID = 0;
	
	/////////金额交易类型(3种)//////
	/**
	 * 赠送金额
	 */
	String INITIALIZATION = "赠送金额";
	//默认赠送的金额数
	double  DEFAULT_MONEY = 200.00;
	
	/**
	 * 发布任务
	 */
	String RELEASE_TASK = "发布任务";
	
	/**
	 * 完成任务
	 */
	String PARTICIPATE_TASK = "完成任务";
	
	/**
	 * 任务余额
	 */
	String MISSION_BALANCE = "任务余额";
	
	////////任务状态//////////
	/**
	 * 发布成功
	 */
	String RELEASE_OK = "发布成功";
	/**
	 * 未支付
	 */
	String UNPAID_ERROR = "未支付";
	/**
	 * 已结束
	 */
	String TASK_END = "已结束";
	
	//////////////参与任务的状态及是否公开////////////////
	String DEFAULT_STA = "待审核";
	
	String OK_STA = "通过";
	
	String NO_STA = "未通过";
	/**
	 * 参与内容公开
	 */
	int YES_PUBLIC = 0;
	/**
	 * 参与内容不公开
	 */
	int NO_PUBLIC = 1;
	
}
