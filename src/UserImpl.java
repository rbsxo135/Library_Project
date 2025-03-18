import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements User {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private List<UserVO> list = new LinkedList<UserVO>();
	private UserVO uservo;
	
	public UserImpl() {
		UserVO vo = new UserVO();
		vo.setId("admin");
		vo.setPwd("admin");
		vo.setName("관리자");
		vo.setBirth("2000-10-10");
		vo.setPhone("010-1234-5678");
		list.add(vo);
	}
	
	@Override
	public void join() {
		System.out.println("\n회원 가입...");					
		try {
			UserVO vo = new UserVO();

			System.out.print("아이디 ? ");
			vo.setId(br.readLine());

			if (readUser(vo.getId()) != null) {
				System.out.println("등록된 아이디 입니다.\n");
				return;
			}

			System.out.print("패스워드 ? ");
			vo.setPwd(br.readLine());

			System.out.print("이름 ? ");
			vo.setName(br.readLine());

			System.out.print("생년월일 ? ");
			vo.setBirth(br.readLine());
			
			System.out.print("폰번호 ? ");
			vo.setPhone(br.readLine());

			list.add(vo);
			System.out.println("회원 가입을 축하합니다. 로그인 하세요\n");

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void logIn() {									
		System.out.println("\n회원 로그인...");
		String id, pwd;
		
		try {
			System.out.print("아이디 ? ");
			id=br.readLine();
			
			System.out.print("패스워드 ? ");
			pwd=br.readLine();
			
			UserVO vo = readUser(id);
			if(vo!=null) {
				if(vo.getPwd().equals(pwd)) {
					if(vo.getPenalty() > 0)
					{
						System.out.printf("벌점 %d점이므로 %d일동안 로그인할 수 없습니다.\n");
						return;
					}
					uservo = vo;
					return;
				}
			}
			
			System.out.println("아이디 또는 패스워드가 일치하지 않습니다.\n");
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}

	@Override
	public void logOut() {									
		uservo = null;
		
		System.out.println("로그아웃 되었습니다.\n");
	}

	@Override
	public void userSecrch() {							
		System.out.println("\n이름 검색...");
		try {
			System.out.println("찾을 이름을 입력하세요. ");
			String s1= br.readLine();
			UserVO vo=null;
			
			for(UserVO data : list) {
				if(data.getName().equals(s1)) {
					vo=data;
					break;
				}
			}
			
			System.out.print(vo.getName() + "\t\t");
			System.out.print(vo.getBirth() + "\t\t");
			System.out.print(vo.getId() + "\t\t");
			System.out.print(vo.getPhone() + "\n");
			
			System.out.println("처음메뉴로 돌아갑니다.");

		} catch (Exception e) {
			
		}
	}

	@Override
	public void printUserList() {								
		System.out.println("\n회원가입 명단");
		System.out.println("전체 인원수 : " + list.size());
		for (UserVO vo : list) {
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getBirth() + "\t");
			System.out.print(vo.getId() + "\t");
			System.out.print(vo.getPwd() + "\t");
			System.out.print(vo.getPhone() + "\n");
			
		}
		System.out.println();
		
	}
	

	@Override
	public void userUpdate() {									
		if (uservo == null) {
			System.out.println("\n로그인이 필요 합니다.");
			return;
		}

		System.out.println("\n회원 정보 수정...");

		try {
			UserVO vo = readUser(uservo.getId());

			System.out.print("패스워드를 다시 입력 하세요 ? ");
			String pwd = br.readLine();

			if (!vo.getPwd().equals(pwd)) {
				System.out.println("패스워드가 일치하지 않습니다.\n");
				return;
			}

			System.out.print("수정할 패스워드 ? ");
			vo.setPwd(br.readLine());

			System.out.print("수정할 폰번호? ");
			vo.setPhone(br.readLine());

			System.out.println("회원 정보를 수정했습니다.\n");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public void userDelete() {									
		
		UserVO vo1=readUser(uservo.getId());
		UserVO vo2=readUser("admin");
		
		try {
			if(uservo.getId()!="admin") {
				System.out.println("\n회원 탈퇴");
				
				System.out.print("패스워드를 다시 입력 하세요 ? ");
				String pwd = br.readLine();
				
				if(! vo1.getPwd().equals(pwd)) {
					System.out.println("패스워드가 일치하지 않습니다.\n");
					return;
				}
				
			} else if (uservo.getId()=="admin") {
				System.out.println("\n회원 삭제");
				
				System.out.println("삭제할 아이디? ");
				vo1=readUser(br.readLine());
				if (vo1==null) {
					System.out.println("아이디가 존재하지 않습니다.");
					return;
				}
				System.out.print(vo1.getId()+"\t");
				System.out.print(vo1.getName()+"\t");
				System.out.print(vo1.getPhone()+"\t\n");
				System.out.println("삭제할 아이디가 맞나요? [Y/N] ");
				String s1 = br.readLine();
				
				if (s1.equals("Y") || s1.equals("y") ) {
					System.out.print("관리자 패스워드를 입력 하세요 ? ");
					String pwd = br.readLine();
					
					if(! vo2.getPwd().equals(pwd)) {
						System.out.println("패스워드가 일치하지 않습니다.\n");
						return;
					}
					
				} else {
					System.out.println("삭제를 취소합니다.");
					return;
				}
			}
			
			list.remove(vo1);
			
			if(uservo.getId()!="admin") {
				System.out.println("회원탈퇴가 처리 되었습니다.\n");
			} else {
				System.out.println("회원삭제가 처리 되었습니다.\n");
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		uservo = null;
	}

	@Override
	public void controlPenalty() {
		Scanner scan = new Scanner(System.in);
		System.out.printf("\n1. 벌점 추가\n2. 벌점 제거\n3. 벌점 리셋");

		int cnt, ch = scan.nextInt();
		System.out.println("찾을 이름을 입력하세요. ");

		try {
			String s1 = br.readLine();
			UserVO vo = null;

			for (UserVO data : list) {
				if (data.getName().equals(s1)) {
					vo = data;
					break;
				}
			}
			if(vo == null) {
				System.out.println("존재하지 않는 유저입니다.");
				return;
			}
			System.out.printf("\n%s유저의 벌점은 %d점입니다.",vo.getName(), vo.getPenalty());
			
			switch (ch) {

			case 1:
				System.out.println("몇 점 추가하시겠습니까?");
				cnt = scan.nextInt();
				vo.setPenalty(vo.getPenalty() + cnt);
				break;
			case 2:
				System.out.println("몇 점 제거하시겠습니까?");
				cnt = scan.nextInt();
				vo.setPenalty(vo.getPenalty() - cnt);
				break;
			case 3:
				System.out.println("벌점이 초기화 되었습니다.");
				vo.setPenalty(0);
				break;
			default:
				System.out.println("잘못된 접근입니다.");
				break;
			}
			
			scan.close();
		} catch (Exception e) {

		}
	}

	private UserVO readUser(String id) {
		UserVO vo=null;
		
		for(UserVO data : list) {
			if(data.getId().equals(id)) {
				vo=data;
				break;
			}
		}
		
		return vo;
	}
	

	public UserVO loginMember() {
		return uservo;
	}
	
	
}