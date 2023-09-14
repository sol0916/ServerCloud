package com.server.cloud.client.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.cloud.command.CusVO;
import com.server.cloud.command.FormDataVO;
import com.server.cloud.command.ProjectInfoVO;
import com.server.cloud.command.ProjectListVO;
import com.server.cloud.command.ProjectDetailVO;
import com.server.cloud.command.ServerVO;

@Service("clientService")
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientMapper clientMapper;
	
	//set 설정을 위한 formDataVO
	FormDataVO dataVO = new FormDataVO();
	
	
	//프로젝트 목록 불러오기
	@Override
	public ArrayList<CusVO> getCusList(String cus_id) {
		
		return clientMapper.getCusList(cus_id);
	}
	
	
	//프로젝트 데이터 입력 
	@Override
	public void proApplyForm(ProjectInfoVO proVO) {
		
		clientMapper.proApplyForm(proVO);
	}
	
	//서버 데이터 입력 
	@Override
	public void serverApplyForm(ArrayList<ServerVO> serverList) {
		
		clientMapper.serverApplyForm(serverList);
	}
	
	//프로젝트 목록 리스트 
	@Override
	public ArrayList<ProjectInfoVO> getProList(String cus_id) {
		
		//int endyear = Integer.parseInt( dataVO.getProInfo().getPro_startdate().substring(0, 4) ) + 1;
		//String enddate = String.valueOf(endyear) + dataVO.getProInfo().getPro_startdate().substring(5, 10);
		
		//dataVO.getProInfo().setPro_enddate(enddate);
		
		return clientMapper.getProList(cus_id);
	}
	
	//프로젝트 세부사항 
	@Override
	public ArrayList<ProjectDetailVO> projectDetail(String pro_id) {
		return clientMapper.projectDetail(pro_id);
	}
	
	
	   @Override
	   public ArrayList<ProjectListVO> projectDetailList() {
	      return clientMapper.projectDetailList();
	   }

	   
	   @Override
	   public ArrayList<ProjectListVO> projectDetailChart() {
	      return clientMapper.projectDetailChart();
	   }

	

}