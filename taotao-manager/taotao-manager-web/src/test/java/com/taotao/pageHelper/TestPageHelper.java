package com.taotao.pageHelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {

	@Test
	public void testPageHelper() throws Exception{
		//获得mapper代理对象
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		//设置分页
		PageHelper.startPage(1, 30);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem>list = tbItemMapper.selectByExample(example);
		
		//取分页后的结果 //PageInfo的信息
		PageInfo<TbItem>pageInfo = new PageInfo<>(list);
		long total =  pageInfo.getTotal();
		System.out.println("总共"+total+"记录"); //3096
		int page = pageInfo.getPages();
		System.out.println("pages"+page+"页"); //总共104页面
		
		pageInfo.getPageSize();//每一页30
	}
	
	

}
