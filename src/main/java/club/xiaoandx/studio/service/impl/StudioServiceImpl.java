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
package club.xiaoandx.studio.service.impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiniu.util.Auth;

import club.xiaoandx.commons.core.Parameter;
import club.xiaoandx.commons.core.PublicErrorCode;
import club.xiaoandx.commons.exception.CommonException;
import club.xiaoandx.commons.utils.IdAndTimeUtil;
import club.xiaoandx.studio.entity.Studio;
import club.xiaoandx.studio.mapper.StudioMapper;
import club.xiaoandx.studio.service.StudioService;

/**
 * <p>
 * studioService接口实现类
 * </p>
 * 
 * @ClassName:StudioServiceImpl
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-14 11:06
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@Service
public class StudioServiceImpl implements StudioService, Parameter {

	@Autowired
	private StudioMapper studioMapper;

	// 七牛云accessKey
	@Value("${qinliu.accessKey}")
	private String accessKey;
	// 七牛云secretKey
	@Value("${qinliu.secretKey}")
	private String secretKey;
	// 七牛云bucket（文件储存库的名字）
	@Value("${qinliu.bucket}")
	private String bucket;

	/**
	 * <p>
	 * Title: getStudioList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return List<Studio> 工作室集合
	 * @see club.xiaoandx.studio.service.StudioService#getStudioList()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Studio> getStudioList() {
		return studioMapper.getStudioList();
	}

	/**
	 * <p>
	 * 获取七牛云上传token凭证
	 * </p>
	 * 
	 * @Title: getToken
	 * @version:V0.1
	 * @return:Map<String,String>
	 */
	public Map<String, String> getToken() {
		String upToken = null;
		try {
			Auth auth = Auth.create(accessKey, secretKey);
			upToken = auth.uploadToken(bucket);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException(PublicErrorCode.OPERATION_EXCEPTION.getIntValue(), "ObtainTokenError");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("uptoken", upToken);
		return map;
	}

	/**
	 * <p>
	 * Title: addStudio
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param studio
	 * @see club.xiaoandx.studio.service.StudioService#addStudio(club.xiaoandx.studio.entity.Studio)
	 */
	@Override
	public void addStudio(Studio studio) {
		try {
			if (null == studio.getSlogoPath()) {
				studio.setSlogoPath(DEFAULT_PATH);
			}
			studio.setStime(IdAndTimeUtil.getNewDate());
			studioMapper.addStudio(studio);
		} catch (Exception e) {
			throw new CommonException(PublicErrorCode.SAVE_EXCEPTION.getIntValue(), "save error");
		}
	}

	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see club.xiaoandx.studio.service.StudioService#findById(java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	@Override
	public Studio findById(String id) {
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(id);
		Integer NewId = Integer.valueOf(new String(bytes));
		return studioMapper.findById(NewId);
	}

	/**
	 * <p>
	 * Title: updateStudio
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param studio
	 * @see club.xiaoandx.studio.service.StudioService#updateStudio(club.xiaoandx.studio.entity.Studio)
	 */
	@Override
	public void updateStudio(Studio studio) {
		studioMapper.updateStudio(studio);
	}

	/**
	 * <p>
	 * Title: deleteStudio
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param sid
	 * @see club.xiaoandx.studio.service.StudioService#deleteStudio(java.lang.Integer)
	 */
	@Override
	public void deleteStudio(Integer sid) {
		studioMapper.deleteStudio(sid);
	}

}
