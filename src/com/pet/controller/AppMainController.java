package com.pet.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pet.exception.DidnotException;
import com.pet.gettable.domain.GetTable;
import com.pet.gettable.domain.GetTableForm;
import com.pet.gettable.model.service.GetTableService;
import com.pet.member.model.sevice.MemberService;
import com.pet.petowner.domain.PetOwner;
import com.pet.petowner.domain.PetOwnerForm;
import com.pet.petowner.model.service.PetOwnerService;
import com.pet.taketable.domain.TakeTable;
import com.pet.taketable.domain.TakeTableForm;
import com.pet.taketable.model.service.TakeTableService;

//회원 가입과 로그인 처리에 대한것 먼저 보냅니다
//웹과 비동기를 제이슨 형태로 해서 같이 연동시 에러가 나므로 웹메인컨트롤러는 아직 풀지 말아 주세요
//조인문은 왠만하면 쓰지 않고 보낼 생각입니다 
//테이블에 들어갈 디티오인 테이블명 디티오와 사진 담아갈 디티오인 디티오폼 두가지로해서 디티오 만들어야 합니다
//비동기 실험은 웹컨텐트 안에 웹 안에 레지스트 제이에스피로 하면 됩니다
@Controller
@RequestMapping("/app")
@SessionAttributes("login")
public class AppMainController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private GetTableService getTableService;
	@Autowired
	private TakeTableService takeTableService;
	@Autowired
	private PetOwnerService petOwnerService;

	// 아이디 유효성 체크, 제이슨
	// 이건 파싱 안해도 됩니다
	// 제이슨이 아닌 바로 문자로 갑니다
	// 통과시 트루를 주고 닉창에 닉 입력되어서 트루일때만 게시판으로 넘어가기로
	// 멤버가 없을시 그냥 폴스로 해서 닉을 입력하게 하기
	// 닉 입력해서 로그인 시도시 인서트 메서드 하는데 아이디를 기준으로 찾아서 닉이 없을시만 인서트 되게하고
	// 멤버를 반환 닉이 중복시 인서트 못되게 하고 빈 멤버를 반환
	/*
	 * @RequestMapping(value="/member/{id}", method=RequestMethod.POST,
	 * produces={"application/json;charset=UTF-8"})
	 * 
	 * @ResponseStatus(HttpStatus.OK) public @ResponseBody Member
	 * idCheck(@PathVariable("id") String id){ System.out.println(id); Member
	 * member=memberService.selectId(id); return member; } //닉네임 없을시 닉네임 설정후 할것
	 * 
	 * @RequestMapping(value="/member", method=RequestMethod.POST,
	 * produces={"application/json;charset=UTF-8"})
	 * 
	 * @ResponseStatus(HttpStatus.OK) public @ResponseBody Member
	 * idCheck(@RequestBody Member dto){ System.out.println(dto.getId()); Member
	 * member=memberService.selectNick(dto.getNick()); if(member!=null){
	 * memberService.insert(dto);
	 * member=memberService.selectNick(dto.getNick()); }else{ member=null; }
	 * return member; }
	 * 
	 * @RequestMapping(value="/member", method=RequestMethod.PUT,
	 * produces={"application/json;charset=UTF-8"})
	 * 
	 * @ResponseStatus(HttpStatus.OK) public @ResponseBody Member
	 * idUpdate(@RequestBody Member dto){ System.out.println(dto.getId());
	 * Member member=memberService.selectNick(dto.getNick()); if(member!=null){
	 * memberService.update(dto);
	 * member=memberService.selectNick(dto.getNick()); }else{ member=null; }
	 * return member; }
	 */
	@RequestMapping(value = "/member/{member_id}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMember(@PathVariable("member_id") String member_id) {
		System.out.println(member_id);
		// memberService.delete(member_id);
		getTableService.deleteMember(member_id);
		takeTableService.deleteMember(member_id);
	}

	// 아이디 유효성 체크, 웹
	/*
	 * @RequestMapping(value="/member/{id}", method=RequestMethod.POST) public
	 * String idCheck(@PathVariable("id") String id, Model model){
	 * System.out.println(id); String msg=idCheck(id); model.addAttribute("msg",
	 * msg); return "web/idCheck"; }
	 */
	// 로그인 처리, 제이슨
	// 이것도 아이디가 존재하거나 하면 문자로 갑니다
	// 한글이 비동기서 깨지는 현상 발견??
	/*
	 * @RequestMapping(value="/login", method=RequestMethod.POST,
	 * produces={"application/json;charset=UTF-8"})
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public @ResponseBody Member
	 * selectId(@RequestBody Member dto){ Member
	 * member=memberService.selectId(dto); if(member.getId()==null){ throw new
	 * DidnotException("아이디가없습니다"); } if
	 * (!(dto.getPwd().equals(member.getPwd()))) { throw new
	 * DidnotException("비밀번호불일치합니다"); } return member; }
	 */
	/*
	 * @RequestMapping(value="/login", method=RequestMethod.POST) public String
	 * selectid(Member dto,Model model){ Member
	 * member=memberService.selectId(dto); if(member==null){
	 * model.addAttribute("nono", "아이디가없습니다"); }else
	 * if(!(dto.getPwd().equals(member.getPwd()))){ model.addAttribute("nono",
	 * "비밀번호가없어요"); }else{ model.addAttribute("login", member); } return
	 * "web/main"; }
	 */
	// 업데이트와 등록 둘을 비교해서 처리하기
	// 다녀올개 글등록과 업데이트
	// 전에 포토 서버서 한 양식 입니다
	// 테이블에 들어갈 디티오인 멤버와 사진 담아갈 디티오인 멤버폼 두가지로해서 디티오 만들어야 합니다
	@RequestMapping(value = "/taketable", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody TakeTable uploadTeketable(TakeTableForm takeTableForm, HttpServletRequest request) {
		System.out.println(takeTableForm.getNick());
		MultipartFile myfile = takeTableForm.getMyfile();
		System.out.println("file");
		String fileName = myfile.getOriginalFilename();
		// 서버측 공간에 파일저장
		File savedFile = null;
		String path = request.getServletContext().getRealPath("/save_photo/taketable/" + fileName);
		try {
			myfile.transferTo(savedFile = new File(path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TakeTable takeTable = new TakeTable();
		// 빈즈 복사
		BeanUtils.copyProperties(takeTableForm, takeTable);
		takeTable.setFilename(fileName);
		System.out.println("사전값" + takeTable.getTaketable_id());
		if (takeTable.getTaketable_id() == 0) {
			takeTableService.insert(takeTable);
			;
			System.out.println("인서트");
		} else {
			takeTableService.update(takeTable);
		}
		System.out.println("사후값" + takeTable.getMember_id());
		// 파일명을 취득한 member_id로 대체하자
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		savedFile.renameTo(new File(request.getServletContext()
				.getRealPath("/save_photo/taketable/" + takeTable.getMember_id() + "." + ext)));
		return takeTable;
	}

	@RequestMapping(value = "/taketable", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<GetTable> selectAllTakeTable() {
		return takeTableService.selectAll();
	}

	// 내가쓴글 보기
	@RequestMapping(value = "/taketable/{member_id}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<TakeTable> selectMemberTakeTable(@PathVariable("member_id") String member_id) {
		return takeTableService.selectMember(member_id);
	}

	// 글한건 읽어오기
	@RequestMapping(value = "/taketable", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TakeTable selectOneTakeTable(@RequestBody TakeTable takeTable) {
		return takeTableService.selectOne(takeTable.getTaketable_id());
	}

	// 글삭제하기
	@RequestMapping(value = "/taketable/{taketable_id}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOneTakeTable(@PathVariable("taketable_id") int taketable_id) {
		takeTableService.delete(taketable_id);
	}

	// 업데이트와 등록 둘을 비교해서 처리하기
	// 다녀올개 글등록과 업데이트
	// 전에 포토 서버서 한 양식 입니다
	// 테이블에 들어갈 디티오인 멤버와 사진 담아갈 디티오인 멤버폼 두가지로해서 디티오 만들어야 합니다
	@RequestMapping(value = "/gettable", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody GetTable uploadGettable(GetTableForm getTableForm, HttpServletRequest request) {
		System.out.println(getTableForm.getNick());
		MultipartFile myfile = getTableForm.getMyfile();
		System.out.println("file");
		String fileName = myfile.getOriginalFilename();
		// 서버측 공간에 파일저장
		File savedFile = null;
		String path = request.getServletContext().getRealPath("/save_photo/gettable/" + fileName);
		try {
			myfile.transferTo(savedFile = new File(path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GetTable getTable = new GetTable();
		// 빈즈 복사
		BeanUtils.copyProperties(getTableForm, getTable);
		getTable.setFilename(fileName);
		System.out.println("사전값" + getTable.getGettable_id());
		if (getTable.getGettable_id() == 0) {
			getTableService.insert(getTable);
			System.out.println("인서트");
		} else {
			getTableService.update(getTable);
		}
		System.out.println("사후값" + getTable.getMember_id());
		// 파일명을 취득한 member_id로 대체하자
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		savedFile.renameTo(new File(request.getServletContext()
				.getRealPath("/save_photo/gettable/" + getTable.getMember_id() + "." + ext)));
		return getTable;
	}

	@RequestMapping(value = "/gettable", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<GetTable> selectAllGetTable() {
		return getTableService.selectAll();
	}

	// 내가쓴글 보기
	@RequestMapping(value = "/gettable/{member_id}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<GetTable> selectMemberGetTable(@PathVariable("member_id") String member_id) {
		return getTableService.selectMember(member_id);
	}

	// 글한건 읽어오기
	@RequestMapping(value = "/gettable", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody GetTable selectOneGetTable(@RequestBody GetTable getTable) {
		return getTableService.selectOne(getTable.getGettable_id());
	}

	// 글삭제하기
	@RequestMapping(value = "/gettable/{gettable_id}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void selectOneGetTable(@PathVariable("gettable_id") int gettable_id) {
		getTableService.delete(gettable_id);
	}
	// 웹상 멤버 등록과 업데이트
	/*
	 * @RequestMapping(value="/memberw",method=RequestMethod.POST) public String
	 * uploadMember(MemberForm memberForm,HttpServletRequest request,Model
	 * model){ Member member=uploadMember(memberForm,request);
	 * model.addAttribute("login", member); return "redirect:/web/main.jsp"; }
	 */

	// 멤버 업데이트 처리
	/*
	 * @RequestMapping(value="/member",method=RequestMethod.POST)
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public void
	 * updateMember(MemberForm memberForm,HttpServletRequest request) {
	 * System.out.println(memberForm.getId()); MultipartFile myfile =
	 * memberForm.getMyfile(); System.out.println("file"); String fileName =
	 * myfile.getOriginalFilename(); // 서버측 공간에 파일저장 File savedFile = null;
	 * String path = request.getServletContext().getRealPath("/save_photo/user"
	 * + fileName); try { myfile.transferTo(savedFile = new File(path)); } catch
	 * (IllegalStateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } Member member = new Member(); // 빈즈 복사
	 * BeanUtils.copyProperties(memberForm, member);
	 * member.setFilename(fileName); System.out.println("사전값" +
	 * member.getMember_id()); memberService.update(member);
	 * System.out.println("사후값" + member.getMember_id()); // 파일명을 취득한 member_id로
	 * 대체하자 String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
	 * fileName.length()); savedFile.renameTo( new
	 * File(request.getServletContext().getRealPath("/data/user" +
	 * member.getMember_id() + "." + ext))); }
	 */
	// 회원 탈퇴하기
	/*
	 * @RequestMapping(value="/member/{member_id}",method=RequestMethod.DELETE)
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public void
	 * deleteMember(@PathVariable("member_id") int member_id){
	 * memberService.delete(member_id); petService.deleteMember(member_id);
	 * registService.deleteMember(member_id);
	 * replyService.deleteMember(member_id); }
	 */

	// 내 펫 등록하기
	/*
	 * @RequestMapping(value="/pet",method=RequestMethod.POST)
	 * 
	 * @ResponseStatus(HttpStatus.OK) public @ResponseBody Pet uploadPet(PetForm
	 * petForm,HttpServletRequest request) {
	 * System.out.println(petForm.getMember_id()); MultipartFile myfile =
	 * petForm.getMyfile(); System.out.println("file"); String fileName =
	 * myfile.getOriginalFilename(); // 서버측 공간에 파일저장 File savedFile = null;
	 * String path = request.getServletContext().getRealPath("/save_photo/pet" +
	 * fileName); try { myfile.transferTo(savedFile = new File(path)); } catch
	 * (IllegalStateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } Pet pet = new Pet(); // 빈즈 복사
	 * BeanUtils.copyProperties(petForm, pet); pet.setFilename(fileName);
	 * System.out.println("사전값" + pet.getMember_id()); System.out.println("사전펫값"
	 * + pet.getPet_id()); if(pet.getPet_id()==0){ petService.insert(pet);
	 * }else{ petService.update(pet); } System.out.println("사후값" +
	 * pet.getMember_id()); System.out.println("사후펫값" + pet.getPet_id()); //
	 * 파일명을 취득한 member_id로 대체하자 String ext =
	 * fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	 * savedFile.renameTo( new
	 * File(request.getServletContext().getRealPath("/data/user" +
	 * pet.getPet_id() + "." + ext))); return pet; } //펫 한건 가져오기
	 * 
	 * @RequestMapping(value="/pet/{pet_id}",method=RequestMethod.GET,
	 * produces={"application/json"})
	 * 
	 * @ResponseStatus(HttpStatus.OK) public @ResponseBody Pet
	 * selectPet(@PathVariable("pet_id") int pet_id){ Pet
	 * pet=petService.selectOne(pet_id); return pet; } //내가 보유한 펫 정보 가져오기
	 * 
	 * @RequestMapping(value="/mypet/{member_id}",method=RequestMethod.GET,
	 * produces={"application/json"})
	 * 
	 * @ResponseStatus(HttpStatus.OK) public @ResponseBody List<Pet>
	 * selectMyPet(@PathVariable("member_id") int member_id){ List
	 * list=petService.selectMember(member_id); return list; } //펫 삭제하기
	 * 
	 * @RequestMapping(value="/mypet/{pet_id}", method=RequestMethod.DELETE,
	 * produces={"application/json"})
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public void
	 * deletePet(@PathVariable("pet_id") int pet_id){ petService.delete(pet_id);
	 * } //글등록하기
	 * 
	 * @RequestMapping(value="/regist",method=RequestMethod.POST)
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public void
	 * uploadRegist(RegistForm reigstForm,HttpServletRequest request) {
	 * System.out.println(reigstForm.getMember_id()); MultipartFile myfile =
	 * reigstForm.getMyfile(); System.out.println("file"); String fileName =
	 * myfile.getOriginalFilename(); // 서버측 공간에 파일저장 File savedFile = null;
	 * String path =
	 * request.getServletContext().getRealPath("/save_photo/regist" + fileName);
	 * try { myfile.transferTo(savedFile = new File(path)); } catch
	 * (IllegalStateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } Regist regist = new Regist(); // 빈즈 복사
	 * BeanUtils.copyProperties(reigstForm, regist);
	 * regist.setFilename(fileName); System.out.println("사전값" +
	 * regist.getMember_id()); System.out.println("사전레지스트값" +
	 * regist.getRegist_id()); if(regist.getPet_id()==0){
	 * registService.insert(regist); }else{ registService.update(regist); }
	 * System.out.println("사후값" + regist.getMember_id());
	 * System.out.println("사후펫값" + regist.getRegist_id()); // 파일명을 취득한
	 * member_id로 대체하자 String ext = fileName.substring(fileName.lastIndexOf(".")
	 * + 1, fileName.length()); savedFile.renameTo( new
	 * File(request.getServletContext().getRealPath("/data/user" +
	 * regist.getPet_id() + "." + ext)));
	 * 
	 * }
	 */
	//
	// 익셉션 처리
	// 오류메세지를 제이슨으로 아직 익셉션은 멤버중 마이바티스멤버디에이오와 멤버서비스임플만 적용했습니다
	/*
	 * @ExceptionHandler(DidnotException.class) public @ResponseBody String
	 * handle(DidnotException e){ String msg=e.getMessage();
	 * System.out.println(msg); return msg; }
	 */
	@ExceptionHandler(DidnotException.class)
	public ModelAndView handle(DidnotException e) {
		ModelAndView mav = new ModelAndView("web/idCheck");
		mav.addObject("msg", e.getMessage());
		return mav;
	}

	// 업데이트와 등록 둘을 비교해서 처리하기
	// 다녀올개 글등록과 업데이트
	// 전에 포토 서버서 한 양식 입니다
	// 테이블에 들어갈 디티오인 멤버와 사진 담아갈 디티오인 멤버폼 두가지로해서 디티오 만들어야 합니다
	@RequestMapping(value = "/petowner", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void uploadPetOwner(PetOwnerForm petOwnerForm, HttpServletRequest request) {
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
				.getRealPath("/save_photo/taketable/" + petOwner.getPetOwner_id() + "." + ext)));
	}

	// 다녀오개 모두찾기
	@RequestMapping(value = "/petowner", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<PetOwner> selectAllPetOwner() {
		return petOwnerService.selectAll();
	}

	// 내가쓴글 보기
	@RequestMapping(value = "/petowner/{member_id}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<PetOwner> selectMemberPetOwner(@PathVariable("member_id") String member_id) {
		return petOwnerService.selectMember(member_id);
	}

	// 글한건 읽어오기
	/*
	 * @RequestMapping(value = "/petowner", method = RequestMethod.POST,
	 * produces = { "application/json;charset=UTF-8" })
	 * 
	 * @ResponseStatus(HttpStatus.OK) public @ResponseBody PetOwner
	 * selectOnePetOwner(@RequestBody PetOwner petowner) { return
	 * petOwnerService.selectOne(petowner.getPetOwner_id()); }
	 */

	// 글삭제하기
	@RequestMapping(value = "/petowner/{petowner_id}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOnePetOwner(@PathVariable("petowner_id") int petowner_id) {
		petOwnerService.delete(petowner_id);
	}
}
