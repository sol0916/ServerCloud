package com.server.cloud.engLeader.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.cloud.command.CusVO;
import com.server.cloud.command.EngineerVO;
import com.server.cloud.command.ProjectInfoVO;
import com.server.cloud.command.QueryVO;
import com.server.cloud.command.ScheduleVO;
import com.server.cloud.command.ServerVO;

@Service("engLeaderService")
public class EngLeaderServiceImpl implements EngLeaderService{
	
	@Autowired
	private EngLeaderMapper engLeaderMapper;


	@Override
	public List<ProjectInfoVO> getNewProject(String leader_id) {	
		return engLeaderMapper.getNewProject(leader_id);
	}


	@Override
	public List<QueryVO> getInspection(String leader_id) {
		return engLeaderMapper.getInspection(leader_id);
	}

	@Override
	public QueryVO getAllMain(String leader_id) {		
		return engLeaderMapper.getAllMain(leader_id);
	}

	@Override
	public Map<String, Object> getRequestDetail(String pro_id) {
		return engLeaderMapper.getRequestDetail(pro_id);
	}

	@Override
	public List<ServerVO> getRequestServer(String pro_id) {
		return engLeaderMapper.getRequestServer(pro_id);
	}
	
	@Override
	public List<ServerVO> getRequestServer2(String pro_id) {
		return engLeaderMapper.getRequestServer2(pro_id);
	}

	@Override
	public List<EngineerVO> getTeamEngList(String leader_id) {
		return engLeaderMapper.getTeamEngList(leader_id);
	}

	@Override
	public void assignEng(String eng_enid, String server_id) {
		engLeaderMapper.assignEng(eng_enid, server_id);		
	}


	@Override
	public List<ProjectInfoVO> getAllPro(String leader_id) {
		return engLeaderMapper.getAllPro(leader_id);		
	}


	@Override
	public List<CusVO> getClient(String leader_id) {		
		return engLeaderMapper.getClient(leader_id);
	}


	@Override
	public CusVO getClientInfo(String cus_id) {
		return engLeaderMapper.getClientInfo(cus_id);
	}


	@Override
	public List<ProjectInfoVO> clientProjects(String cus_id) {
		return engLeaderMapper.clientProjects(cus_id);
	}


	@Override
	public List<ServerVO> getEngServer(String eng_enid) {
		return engLeaderMapper.getEngServer(eng_enid);
	}


	@Override
	public List<ScheduleVO> getEngSchedule(String eng_enid) {
		return engLeaderMapper.getEngSchedule(eng_enid);
	}
	
	
	
	
	

}