/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.pys.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

/**
 * 异常处理器
 *
 */
@RestControllerAdvice
@Slf4j
public class ResExceptionHandler {

	/**
	 * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		log.info("binder.getFieldDefaultPrefix {}",binder.getFieldDefaultPrefix());
		log.info("binder.getFieldMarkerPrefix {}",binder.getFieldMarkerPrefix());
	}

	/**
	 * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
	 */
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("author", "harry");
	}

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(ResException.class)
	public Res handleRRException(ResException e){
		Res r = new Res();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Res handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return Res.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Res handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return Res.error("数据库中已存在该记录");
	}

	@ExceptionHandler(Exception.class)
	public Res handleException(Exception e){
		log.error(e.getMessage(), e);
		return Res.error(e.getMessage());
	}
}
