package com.pet.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.pet.gettable.model.service.GetTableService;
import com.pet.member.model.domain.Member;
import com.pet.member.model.sevice.MemberService;
import com.pet.petowner.domain.PetOwner;
import com.pet.petowner.domain.PetOwnerForm;
import com.pet.petowner.model.service.PetOwnerService;
import com.pet.taketable.model.service.TakeTableService;

@Controller
@RequestMapping("/web")
@SessionAttributes("log")
public class WebMainController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private GetTableService getTableService;
	@Autowired
	private TakeTableService takeTableService;
	@Autowired
	private PetOwnerService petOwnerService;

	// 업데이트와 등록 둘을 비교해서 처리하기
	// 다녀올개 글등록과 업데이트
	// 전에 포토 서버서 한 양식 입니다
	// 테이블에 들어갈 디티오인 멤버와 사진 담아갈 디티오인 멤버폼 두가지로해서 디티오 만들어야 합니다
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public String logInMember(Member member, Model model) {
		System.out.println(member.getId());
		System.out.println(member.getNick());
		model.addAttribute("log", member);
		return "web/main";
	}

	@RequestMapping(value = "/member/{member_id}", method = RequestMethod.POST)
	public String deleteMember(@PathVariable("member_id") String member_id, HttpSession session) {
		System.out.println(member_id);
		// memberService.delete(member_id);
		petOwnerService.deleteMember(member_id);
		session.removeAttribute("log");
		session.invalidate();
		// takeTableService.deleteMember(member_id);
		return "web/main";
	}

	// 펫 등록
	@RequestMapping(value = "/petowner", method = RequestMethod.POST)
	public String uploadPetOwner(PetOwnerForm petOwnerForm, HttpServletRequest request) {
		System.out.println(petOwnerForm.getName());
		MultipartFile myFile = petOwnerForm.getMyFile();
		System.out.println("file");
		String fileName = myFile.getOriginalFilename();
		// 서버측 공간에 파일저장
		File savedFile = null;
		String path = request.getServletContext().getRealPath("/save_photo/petowner/" + fileName);
		try {
			myFile.transferTo(savedFile = new File(path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PetOwner petOwner = new PetOwner();
		// 빈즈 복사
		BeanUtils.copyProperties(petOwnerForm, petOwner);
		petOwner.setPhoto(fileName);
		System.out.println("사전값" + petOwner.getPetOwner_id());
		if (petOwner.getPetOwner_id() == 0) {
			petOwnerService.insert(petOwner);
			;
			System.out.println("인서트");
		} else {
			petOwnerService.update(petOwner);
		}
		System.out.println("사후값" + petOwner.getPetOwner_id());
		// 파일명을 취득한 member_id로 대체하자
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		savedFile.renameTo(new File(request.getServletContext()
				.getRealPath("/save_photo/petowner/" + petOwner.getPetOwner_id() + "." + ext)));
		System.out.println(petOwner.getPetOwner_id() + "." + ext);
		System.out.println(savedFile.getAbsolutePath());
		return "redirect:/web/petowner";
	}

	// 다녀오개 모두찾기
	@RequestMapping(value = "/petowner", method = RequestMethod.GET)
	public String selectAllPetOwner(Model model) {
		List list = petOwnerService.selectAll();
		model.addAttribute("list", list);
		return "pinter";
	}

	// 글삭제하기
	@RequestMapping(value = "/petowner/{petowner_id}", method = RequestMethod.GET)
	public String deleteOnePetOwner(@PathVariable("petowner_id") int petowner_id) {
		petOwnerService.delete(petowner_id);
		System.out.println("지우기반응");
		return "redirect:/web/petowner";
	}

	/*
	 * @RequestMapping(value = "/taketable", method = RequestMethod.POST) public
	 * String uploadTeketable(TakeTableForm takeTableForm, HttpServletRequest
	 * request) { System.out.println(takeTableForm.getNick()); MultipartFile
	 * myfile = takeTableForm.getMyfile(); System.out.println("file"); String
	 * fileName = myfile.getOriginalFilename(); // 서버측 공간에 파일저장 File savedFile =
	 * null; String path =
	 * request.getServletContext().getRealPath("/save_photo/taketable/" +
	 * fileName); try { myfile.transferTo(savedFile = new File(path)); } catch
	 * (IllegalStateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } TakeTable takeTable = new TakeTable();
	 * // 빈즈 복사 BeanUtils.copyProperties(takeTableForm, takeTable);
	 * takeTable.setFilename(fileName); System.out.println("사전값" +
	 * takeTable.getTaketable_id()); if (takeTable.getTaketable_id() == 0) {
	 * takeTableService.insert(takeTable); ; System.out.println("인서트"); } else {
	 * takeTableService.update(takeTable); } System.out.println("사후값" +
	 * takeTable.getMember_id()); // 파일명을 취득한 member_id로 대체하자 String ext =
	 * fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	 * savedFile.renameTo(new File(request.getServletContext()
	 * .getRealPath("/save_photo/taketable/" + takeTable.getMember_id() + "." +
	 * ext))); return "printer"; }
	 * 
	 * @RequestMapping(value = "/taketable", method = RequestMethod.GET) public
	 * List<TakeTable> selectAllTakeTable() { return
	 * takeTableService.selectAll(); }
	 * 
	 * // 내가쓴글 보기
	 * 
	 * @RequestMapping(value = "/taketable/{member_id}", method =
	 * RequestMethod.GET) public List<TakeTable>
	 * selectMemberTakeTable(@PathVariable("member_id") String member_id) {
	 * return takeTableService.selectMember(member_id); }
	 * 
	 * // 글한건 읽어오기
	 * 
	 * @RequestMapping(value = "/taketable", method = RequestMethod.POST) public
	 * TakeTable selectOneTakeTable(TakeTable takeTable) { return
	 * takeTableService.selectOne(takeTable.getTaketable_id()); }
	 * 
	 * // 글삭제하기
	 * 
	 * @RequestMapping(value = "/taketable/{taketable_id}", method =
	 * RequestMethod.DELETE) public void
	 * selectOneTakeTable(@PathVariable("taketable_id") int taketable_id) {
	 * takeTableService.delete(taketable_id); }
	 * 
	 * // 업데이트와 등록 둘을 비교해서 처리하기 // 다녀올개 글등록과 업데이트 // 전에 포토 서버서 한 양식 입니다 // 테이블에
	 * 들어갈 디티오인 멤버와 사진 담아갈 디티오인 멤버폼 두가지로해서 디티오 만들어야 합니다
	 * 
	 * @RequestMapping(value = "/gettable", method = RequestMethod.POST) public
	 * GetTable uploadGettable(GetTableForm getTableForm, HttpServletRequest
	 * request) { System.out.println(getTableForm.getNick()); MultipartFile
	 * myfile = getTableForm.getMyfile(); System.out.println("file"); String
	 * fileName = myfile.getOriginalFilename(); // 서버측 공간에 파일저장 File savedFile =
	 * null; String path =
	 * request.getServletContext().getRealPath("/save_photo/gettable/" +
	 * fileName); try { myfile.transferTo(savedFile = new File(path)); } catch
	 * (IllegalStateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } GetTable getTable = new GetTable(); //
	 * 빈즈 복사 BeanUtils.copyProperties(getTableForm, getTable);
	 * getTable.setFilename(fileName); System.out.println("사전값" +
	 * getTable.getGettable_id()); if (getTable.getGettable_id() == 0) {
	 * getTableService.insert(getTable); System.out.println("인서트"); } else {
	 * getTableService.update(getTable); } System.out.println("사후값" +
	 * getTable.getMember_id()); // 파일명을 취득한 member_id로 대체하자 String ext =
	 * fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	 * savedFile.renameTo(new File(request.getServletContext()
	 * .getRealPath("/save_photo/gettable/" + getTable.getMember_id() + "." +
	 * ext))); return getTable; }
	 * 
	 * @RequestMapping(value = "/gettable", method = RequestMethod.GET) public
	 * List<GetTable> selectAllGetTable() { return getTableService.selectAll();
	 * }
	 * 
	 * // 내가쓴글 보기
	 * 
	 * @RequestMapping(value = "/gettable/{member_id}", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseStatus(HttpStatus.OK) public List<GetTable>
	 * selectMemberGetTable(@PathVariable("member_id") String member_id) {
	 * return getTableService.selectMember(member_id); }
	 * 
	 * // 글한건 읽어오기
	 * 
	 * @RequestMapping(value = "/gettable", method = RequestMethod.POST) public
	 * GetTable selectOneGetTable(GetTable getTable) { return
	 * getTableService.selectOne(getTable.getGettable_id()); }
	 * 
	 * // 글삭제하기
	 * 
	 * @RequestMapping(value = "/gettable/{gettable_id}", method =
	 * RequestMethod.DELETE) public void
	 * selectOneGetTable(@PathVariable("gettable_id") int gettable_id) {
	 * getTableService.delete(gettable_id); }
	 */

}
