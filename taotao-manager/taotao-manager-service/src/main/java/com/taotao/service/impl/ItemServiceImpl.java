package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	
	
	@Override
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		//return item;
		TbItemExample example = new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list != null && list.size() >0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub
		//分页拦截器
		PageHelper.startPage(page, rows);
		//查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list =  itemMapper.selectByExample(example);
		PageInfo<TbItem>pageInfo = new PageInfo<>(list);
		
		EasyUIDataGridResult dataGridResult  =new EasyUIDataGridResult();
		dataGridResult.setTotal(pageInfo.getTotal());
		dataGridResult.setRows(list);
		return dataGridResult;
	}

}
