package com.server.cloud.engLeader.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.cloud.command.CusVO;
import com.server.cloud.command.EngineerVO;
import com.server.cloud.command.ProjectInfoVO;
import com.server.cloud.command.QueryVO;
import com.server.cloud.command.ScheduleVO;
import com.server.cloud.command.ServerVO;
import com.server.cloud.engLeader.service.EngLeaderService;

@RestController
@RequestMapping("/api/main/engleader")
public class EngLeaderController {

	@Autowired
	@Qualifier("engLeaderService")
	private EngLeaderService engLeaderService;

	@GetMapping("/main/{leaderId}")
	public ResponseEntity<Map<String,Object>> getMain(@PathVariable String leaderId){
		//팀의 팀원수 , 진행중 프로젝, 총 서버수
		//새로 배정된 프로젝트 리스트
		//이번달에 신규계약, 계약종료
		//여태 작업 내역 중 월별 점검현황 (정기점검, 장애대응, 긴급출동)
		// teamCount,projectCount,serverCount,thisMonthStart,thisMonthEnd

		QueryVO vo = engLeaderService.getAllMain(leaderId); //팀의 팀원수 , 진행중 프로젝, 총 서버수, 이번달에 신규계약수, 계약종료
		List<ProjectInfoVO> list = engLeaderService.getNewProject(leaderId); //신규요청리스트
		List<QueryVO> inspectionList = engLeaderService.getInspection(leaderId); //월별점검내역리스트
		List<Integer> periodic = new ArrayList<>(); //정기
		List<Integer> disability = new ArrayList<>(); //장애
		List<Integer> maintenance = new ArrayList<>(); //유지			
		for(QueryVO vo2 : inspectionList) {
			periodic.add(vo2.getPeriodic()); //월별 정기점검 모음
			disability.add(vo2.getDisability()); //월별 장애대응 모음
			maintenance.add(vo2.getMaintenance()); //월별 유지보수 모음
		}
		Map<String,Object> map = new HashMap<>();
		map.put("vo", vo);
		map.put("list", list);
		map.put("periodic",periodic);
		map.put("disability", disability);
		map.put("maintenance", maintenance);

		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@GetMapping("/requestDetail/{pro_id}")
	public ResponseEntity<Map<String,Object>> requestDetail(@PathVariable String pro_id){
		Map<String,Object> map2 = engLeaderService.getRequestDetail(pro_id);
		List<ServerVO> list = engLeaderService.getRequestServer(pro_id);
		map2.put("list", list);
		return new ResponseEntity<>(map2,HttpStatus.OK);
	}

	@GetMapping("/getTeamEngList/{pro_id}")
	public ResponseEntity<List<EngineerVO>> getTeamEngList(@PathVariable String pro_id){
		List<EngineerVO> list= engLeaderService.getTeamEngList("eng_1");
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@PostMapping("/assignEng")
	public ResponseEntity<String> assignEng(@RequestBody Map<String,Object> data) {
		String eng_enid = data.get("eng_enid").toString();
		String pro_id = data.get("pro_id").toString();
		String server_id = data.get("server_id").toString();
		engLeaderService.assignEng(eng_enid, server_id);
		return new ResponseEntity<>("ok",HttpStatus.OK);	
	}

	@GetMapping("/getAllPro/{leader_id}")
	public ResponseEntity<List<ProjectInfoVO>> getAllPro(@PathVariable String leader_id){	
		List<ProjectInfoVO> list=engLeaderService.getAllPro(leader_id);	
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@GetMapping("/projectDetail/{pro_id}")
	public ResponseEntity<Map<String,Object>> projectDetail(@PathVariable String pro_id){
		Map<String,Object> map2 = engLeaderService.getRequestDetail(pro_id);
		List<ServerVO> list = engLeaderService.getRequestServer2(pro_id);
		map2.put("list", list);
		return new ResponseEntity<>(map2,HttpStatus.OK);
	}
	
	@GetMapping("/getClient/{leader_id}")
	public ResponseEntity<List<CusVO>> getClient(@PathVariable String leader_id){
		List<CusVO> list = engLeaderService.getClient(leader_id);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getEngineerList/{leader_id}")
	public ResponseEntity<List<EngineerVO>> getEngineerList(@PathVariable String leader_id){
		List<EngineerVO> list = engLeaderService.getTeamEngList(leader_id);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getClientInfo/{cus_id}")
	public ResponseEntity<Map<String,Object>> getClientInfo(@PathVariable String cus_id){		
		CusVO vo = engLeaderService.getClientInfo(cus_id);
		List<ProjectInfoVO> list = engLeaderService.clientProjects(cus_id);
		Map<String,Object> map = new HashMap<>();
		map.put("vo", vo);
		map.put("list", list);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@GetMapping("/getEngInfo/{eng_enid}")
	public ResponseEntity<Map<String,Object>> getEngInfo(@PathVariable String eng_enid){
		List<ServerVO> list = engLeaderService.getEngServer(eng_enid);
		Map<String,Object> map = new HashMap<>();
		map.put("serverList", list);
		List<ScheduleVO> sche = engLeaderService.getEngSchedule(eng_enid);
		map.put("scheList",sche);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

}