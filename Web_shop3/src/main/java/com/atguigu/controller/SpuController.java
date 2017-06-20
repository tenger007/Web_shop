package com.atguigu.controller;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SpuServiceImp;
import com.atguigu.util.MyFileUploadUtil;


@Controller
public class SpuController {

	@Autowired
	SpuServiceImp spuServiceImp;

	@RequestMapping(value = "spu_publish", method = RequestMethod.POST)
	public ModelAndView spu_publish(T_MALL_PRODUCT spu, @RequestParam("files") MultipartFile[] files) {

		// 图片组上传
		List<String> image_list = MyFileUploadUtil.imgs_upload(files);

		// 调用商品spu信息发布业务
		spuServiceImp.spu_publish(spu, image_list);

		// URLDecoder
		// URLEncoder

	String success = "恭喜操作成功";

		ModelAndView modelAndView = new ModelAndView("redirect:background_manager/index/.do");

		modelAndView.addObject("success", success);
//modelAndView.addObject("url","goto_spu_publish");
//modelAndView.addObject("title","spu商品管理");
		return modelAndView;
	}

	@RequestMapping("goto_spu_publish/{success}")
	public String goto_spu_publish(@PathVariable String success) {

		return "background_manager/manager_spu_publish";

	}

}
