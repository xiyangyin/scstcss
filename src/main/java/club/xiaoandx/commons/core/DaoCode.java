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
	static final Integer SUCCESS = 1;
	/**
	 * 数据库操作失败
	 */
	static final Integer ERROR = 0;
	/**
	 * 参与内容公开
	 */
	static final int YES_PUBLIC = 0;
	/**
	 * 参与内容不公开
	 */
	static final int NO_PUBLIC = 1;
	
}
