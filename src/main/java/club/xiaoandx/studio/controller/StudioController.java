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
package club.xiaoandx.studio.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.xiaoandx.commons.core.Parameter;
import club.xiaoandx.commons.core.PublicErrorCode;
import club.xiaoandx.commons.exception.CommonException;
import club.xiaoandx.studio.entity.Studio;
import club.xiaoandx.studio.service.StudioService;
import club.xiaoandx.studio.vo.StatusMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**  
 * <p>
  *  工作室管理相关模块
 * </p> 
 * @ClassName:StudioController   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-12 14:12
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@RestController
@Api(tags = "工作室管理相关模块")
@RequestMapping("/v1/open/studio")
public class StudioController implements Parameter {
	
	@Autowired
	private StudioService studioService;
	
	/**
	 *<p>查询所有的工作室</p> 
	 * @Title: getStudioList    
	 * @version:V0.1     
	 * @return:List<Studio> 包含所有的工作室集合
	 */
	@GetMapping(value = "/getStudioList",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "查询所有的工作室", notes = "无需参数<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({
		@ApiResponse(code = 400,message = "未传入指定参数"),
		@ApiResponse(code = 404,message = "未找到指定页面")
	})
	public List<Studio> getStudioList() {
		return studioService.getStudioList();
	}
	
	/**
	 *<p> 查询指定id的工作室 </p> 
	 * @Title: findById    
	 * @version:V0.1     
	 * @param id	id
	 * @return:Studio	工作室数据
	 */
	@GetMapping(value = "/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "查询指定id的工作室", notes = "id参数<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({
		@ApiResponse(code = 400,message = "未传入指定参数"),
		@ApiResponse(code = 404,message = "未找到指定页面")
	})
	public Studio findById(@ApiParam(value = "工作室id*必填",required = true)@PathVariable Integer id) {
		if(null != id && ENTER_NUMBER != id) {
			return studioService.findById(id);
		}
		throw new CommonException(PublicErrorCode.PARAM_EXCEPTION.getIntValue(), "error ID IS NULL");
	}
	
	/**
	 *<p>获取七牛云上传图片的凭证token</p> 
	 * @Title: getToken    
	 * @version:V0.1     
	 * @return:Map<String,String>	返回tokenjson字符串
	 */
	@GetMapping(value = "/getToken")
	@ApiOperation(httpMethod = "GET", value = "获取七牛云上传文件token凭证", notes = "返回前端含有token属性的json字符串<b>@autho xiaox.周巍</b>")
	@ApiResponses({
		@ApiResponse(code = 400,message = "未传入指定参数"),
		@ApiResponse(code = 404,message = "未找到指定页面")
	})
	public Map<String, String> getToken() {
		return studioService.getToken();
	}
	
	/**
	 *<p>提交保存新的工作室</p> 
	 * @Title: addStudio    
	 * @version:V0.1     
	 * @param studio 	上传数据实体
	 * @return:StatusMessage 保存状态
	 */
	@PostMapping(value = "/addStudio", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "提交保存新的工作室", notes = "需要传入工作室数据，封装为一个对象传给后端）<br><b>@autho xiaox.周巍</b>")
	@ApiResponses({
		@ApiResponse(code = 400,message = "未传入指定参数"),
		@ApiResponse(code = 404,message = "未找到指定页面")
	})
	public StatusMessage addStudio(@ApiParam(value = "工作室数据对象*必填", required = true) @RequestBody Studio studio) {
		if(null != studio.getSname() && NO_ZIFUCUAN != studio.getSname()) {
			if(ENTER_NUMBER != studio.getSnumber()) {
				studioService.addStudio(studio);
				return new StatusMessage(RESPOSE_SUCCESS, "SUCESS");
			}
			return new StatusMessage(RESPOSE_ERROR, "PeopleNumber is zero.");
		}
		return new StatusMessage(RESPOSE_ERROR, "Name is null.");
	}
}
